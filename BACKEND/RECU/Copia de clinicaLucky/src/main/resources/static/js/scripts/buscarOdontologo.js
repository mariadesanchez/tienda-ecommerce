window.addEventListener('load', function() {
    /* ---------------------- obtenemos variables globales ---------------------- */
//    const ocultar = document.querySelector(".form-header").style.display = "none";
    const form = document.querySelector("#get_odontologo");
    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    const matricula = document.querySelector('#inputMatricula');
    const searchId = prompt("ingresa ID");


    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */


        const url = '/odontologos'+"/"+searchId ;
//        event.preventDefault();
         const settings = {
                    method: 'GET',

                }

                fetch(url, settings)
                    .then(response => response.json())
                    .then(data => {
                    nombre.value = data.nombre ;
                    apellido.value = data.apellido;
                    matricula.value = data.matricula ;

                       }).catch(error => alert("Error: "+error));

      });
//        });