$(document).ready(function() {
    $("#add_new_turno").submit(function(evt) {
        evt.preventDefault();

        let formData = {
            pacienteDni : null,
            odontologoMat : null,
            fecha: $("#turno_fecha").val(),
        }

        let pacienteDni = $("#turno_paciente_dni").val();
        let odontologoMat = $("#turno_odontologo_matricula").val();

        $.ajax({
            url: '/pacientes/d/' + pacienteDni,
            type: 'GET',
            success: function(response) {
                formData.paciente = response;
                $.ajax({
                    url: '/odontologos/m/' + odontologoMat,
                    type: 'GET',
                    success: function(response) {
                        formData.odontologo = response;
                        f();
                    }
                })
            }
        })

        const f = () => $.ajax({
            url: '/turnos',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
                let turno = response
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> turno agregado </div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            }
        });
    });

    function resetUploadForm(){
        $("#turno_paciente_dni").val("");
        $("#turno_odontologo_matricula").val("");
        $("#turno_fecha").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});