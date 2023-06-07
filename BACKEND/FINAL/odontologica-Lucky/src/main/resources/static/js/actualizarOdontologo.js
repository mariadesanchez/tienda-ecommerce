window.addEventListener('load', function() {
    /* ---------------------- obtenemos variables globales ---------------------- */
    const form = document.querySelector("#put_odontologo");
    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    const matricula = document.querySelector('#inputMatricula');

    const url = '/odontologos/1';

    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        //creamos el cuerpo de la request
        const payload = {
            nombre: nombre.value,
            apellido: apellido.value,
            matricula: matricula.value,

        }
        //configuramos la request del Fetch
        const settings = {
            method: 'PUT',
            body: JSON.stringify(payload),
            headers: {
                'Content-Type': 'application/json'
            }
        }
       fetch(`${url}`, settings)
                   .then(response => response.json())
                   .then(data => {
                   alert("Odontólogo Actualizado");

 form.reset();
    }).catch(err => {
                        alert("Ocurrió un Error, Intente Nuevamente");
                   });




    });
      });
