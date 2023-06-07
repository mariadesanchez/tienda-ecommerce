

let form = document.querySelector('#add_paciente');
form.addEventListener('submit' , function(event) {
event.preventDefault();

const data = {
nombre : document.querySelector('#nombre').value,
apellido : document.querySelector('#apellido').value,
mail: document.querySelector('#mail') };

const setting = {
method : 'POST',
headers :  {'Content-Type' : 'application/json'} ,
body : JSON.stringify(data)
}

fetch( '/pacientes', setting )
.then(response => response.json())
.then( dataResponse => {
                         console.log(response.status)
                         console.log(dataResponse.status)})
.catch(error => console.log(error))
} )