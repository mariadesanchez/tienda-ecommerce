window.addEventListener('load', function() {
    const deleteId = prompt("ingresa ID a eliminar");
    const url = '/odontologos'+"/"+deleteId ;
    const settings = {
                    method: 'DELETE',

                }

                fetch(url, settings)
                    .then(response => response.json())


      });
