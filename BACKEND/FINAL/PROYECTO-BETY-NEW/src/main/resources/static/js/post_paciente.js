$(document).ready(function() {
    $("#add_new_paciente").submit(function(evt) {
        evt.preventDefault();

        let formData = {
            nombre : $("#paciente_nombre").val(),
            apellido :  $("#paciente_apellido").val(),
            dni: $("#paciente_dni").val(),
            fechaAlta: $("paciente_fecha").val(),
            domicilio: {
                calle: $("paciente_calle").val(),
                numero: $("paciente_numero").val()
            }
        }

        $.ajax({
            url: '/pacientes',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
                let paciente = response
               console.log(response)
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> paciente agregado </div>'
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
        $("#paciente_nombre").val("");
        $("#paciente_apellido").val("");
        $("#paciente_dni").val("");
        $("paciente_fecha").val();
        $("paciente_calle").val(),
        $("paciente_numero").val()
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});