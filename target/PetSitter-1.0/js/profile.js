var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        //$("#user-saldo").html("$" + user.saldo.toFixed());

        getReservadas(user.username);
    });

    $("#reservar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaPedir",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.username);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-tipoMascota").val(parsedResult.tipoMascota);
                $("#input-raza").val(parsedResult.raza);
                $("#input-tamano").val(parsedResult.tamano);
                $("#input-color").val(parsedResult.color);
                $("#input-edad").val(parsedResult.edad);
                $("#input-sexoMascota").val(parsedResult.sexoMascota);
                $("#input-estado").prop("checked", parsedResult.estado);

            } else {
                console.log("Error recuperando los datos de la mascota");
            }
        }
    });
}

function getReservadas(username) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCuidadoListar",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult);

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(cuidado) {
    let contenido = "";
    if (cuidado.length >= 1) {
        $.each(cuidado, function (index, cuidados) {
            cuidados = JSON.parse(cuidados);

            contenido += '<tr><th scope="row">' + cuidados.id + '</th>' +
                    '<td>' + cuidados.nombre + '</td>' +
                    '<td>' + cuidados.ciudad + '</td>' +
                    '<td><input type="checkbox" name="estado" id="estado' + cuidados.id
                    + '" disabled ';
            if (cuidados.estado) {
                contenido += 'checked'
            }
            contenido += '></td><td>' + cuidados.fechaAlquiler + '</td>' +
                    '<td><button id="devolver-btn" onclick= "devolverReserva(' + cuidados.id
                    + ');" class="btn btn-danger">Cancelar Reserva</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function devolverReserva(id) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCancelarCuidador",
        data: $.param({
            username: username,
            id: id,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error Cancelando el cuidador");
            }
        }
    });

}

function modificarUsuario() {
    
    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let tipoMascota = $("#input-tipoMascota").val();
    let raza = $("#input-raza").val();
    let tamano = $("#input-tamano").val();
    let color = $("#input-color").val();
    let edad = $("#input-edad").val();
    let sexoMascota = $("#input-sexoMascota").val();
    let estado = $("#input-estado").prop("checked");
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaModificar",
        data: $.param({
            username: username,
                contrasena: contrasena,
                nombre: nombre,
                tipoMascota: tipoMascota,
                raza: raza,
                tamano: tamano,
                color: color,
                edad: edad,
                sexoMascota: sexoMascota,
                estado: estado
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaEliminar",
        data: $.param({
            username: username
        }),
        success: function (result) {

            if (result != false) {

                console.log("Mascota eliminada")

            } else {
                console.log("Error eliminando a la mascota");
            }
        }
    });
}


