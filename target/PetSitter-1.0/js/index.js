$(document).ready(function () {

    $("#form-login").submit(function (event) {

        event.preventDefault();
        autenticarMascota();
    });

    $("#form-register").submit(function (event) {

        event.preventDefault();
        registrarMascota();
    });


});

function autenticarMascota() {

    let username = $("#usuario").val();
    let contrasena = $("#contrasena").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaLogin",
        data: $.param({
            username: username,
            contrasena: contrasena
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                $("#login-error").addClass("d-none");
                let username = parsedResult['username'];
                document.location.href = "home.html?username=" + username;
            } else {
                $("#login-error").removeClass("d-none");
            }
        }
    });
}

function registrarMascota() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let contrasenaConfirmacion = $("#input-contrasena-repeat").val();
    let nombre = $("#input-nombre").val();
    let tipoMascota = $("#input-tipoMascota").val();
    let raza = $("#input-raza").val();
    let tamano = $("#input-tamano").val();
    let color = $("#input-color").val();
    let edad = $("#input-edad").val();
    let sexoMascota = $("#input-sexoMascota").val();
    let estado = $("#input-estado").prop("checked");

    if (contrasena == contrasenaConfirmacion) {

        $.ajax({
            type: "GET",
            dataType: "html",
            url: "./ServletMascotaRegister",
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
                let parsedResult = JSON.parse(result);

                if (parsedResult != false) {
                    $("#register-error").addClass("d-none");
                    let username = parsedResult['username'];
                    document.location.href = "home.html?username=" + username;
                } else {
                    $("#register-error").removeClass("d-none");
                    $("#register-error").html("Error en el registro del usuario");
                }
            }
        });
    } else {
        $("#register-error").removeClass("d-none");
        $("#register-error").html("Las contraseñas no coinciden");
    }
}

