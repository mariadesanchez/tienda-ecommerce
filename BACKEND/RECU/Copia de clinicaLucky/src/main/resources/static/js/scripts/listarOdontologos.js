window.addEventListener('load', function() {

const form = document.querySelector("#list_odontologo");

 form.addEventListener('onclick', function(event) {
        event.preventDefault();

        const url = '/odontologos';

         const settings = {
                    method: 'GET',

                }
                fetch(url, settings)
                    .then(response => response.json())
                    .then(data => {
                  const table = document.querySelector("#getAll_odontologos");

                    for(ontology of data){
                     table.innerHTML += `
                     <tr><td>

                                   ontology.id
                                    </td><td>
                                    ontology.id
                                    </td><td>
                                   ontology.id

                                    </td></tr>`

                    }

                       }).catch(error => alert("Error: "+error));

      });
          });


