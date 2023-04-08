// Call the dataTables jQuery plugin
$(document).ready(function() {
// on ready
});

async function iniciarSesion(){
   let datos = {};
   datos.email = document.getElementById('txtEmail').value;
   datos.password = document.getElementById('txtContraseña').value;

   const request = await fetch('api/login', {
      method: 'POST',
      headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
   });
   const respuesta = await request.text();
   if(respuesta != 'fail') {
      localStorage.token = respuesta; //guarda el token en el brwoser
      localStorage.email = datos.email;
//   The window.location object can be used to get the current page address
//   (URL) and to redirect the browser to a new page.
      window.location.href = 'usuarios.html'; //window.location.href returns the href (URL) of the current page
   } else {
   alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
   }
}
