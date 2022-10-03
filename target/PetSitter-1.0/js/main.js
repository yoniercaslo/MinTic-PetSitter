var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getMascota().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);

        getCuidadores(false, "ASC");

        $("#ordenar-ciudad").click(ordenarCuidadores);
    });
});


async function getMascota() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getCuidadores(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCuidadorListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarCuidadores(parsedResult);
            } else {
                console.log("Error recuperando los datos de los Cuidadores");
            }
        }
    });
}
function mostrarCuidadores(cuidadores) {

    let contenido = "";

    $.each(cuidadores, function (index, cuidador) {

        cuidador = JSON.parse(cuidador);
        let precio;

        if (cuidador.horas > 0) {
            
            if (user.estado) {

                if (cuidador.estado) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (cuidador.estado) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            }

            contenido += '<tr><th scope="row">' + cuidador.id + '</th>' +
                    '<td>' + cuidador.cedula + '</td>' +
                    '<td>' + cuidador.nombre + '</td>' +
                    '<td>' + cuidador.apellidos + '</td>' +
                    '<td>' + cuidador.ciudad + '</td>' +
                    '<td>' + cuidador.direccion + '</td>' +
                    '<td>' + cuidador.telefono + '</td>' +
                    '<td>' + cuidador.correo + '</td>' +
                    '<td>' + cuidador.contrasena + '</td>' +
                    '<td>' + cuidador.horas + '</td>' +
                    '<td><input type="checkbox" name="estado" id="estado' + cuidador.id + '" disabled ';
            if (cuidador.estado) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="reservarCuidador(' + cuidador.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.edad < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#cuidadores-tbody").html(contenido);
}

function ordenarCuidadores() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getCuidadores(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getCuidadores(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getCuidadores(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function reservarCuidador(id, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCuidadorReservar",
        data: $.param({
            id: id,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en la reserva del Cuidador");
            }
        }
    });
}


/*async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}*/