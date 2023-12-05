
function mostrarPopup() {

  document.getElementById('overlay').style.display = 'flex';
  document.getElementById('popup').style.display = 'block';
}

function eliminar(citaId, event) {
  // Preguntamos al usuario si está seguro
  if (confirm('¿Estás seguro que deseas eliminar el registro?')) {
    // Lógica para eliminar el elemento
    alert('Elemento eliminado correctamente.');

    // Agregamos aquí la lógica para redirigir o realizar la acción de eliminación
    // window.location.href = '/cita/usuario/delete/' + citaId;

    // Ocultamos el PopUp
    ocultarPopup();
  } else {
    // Si el usuario hace clic en "No", no hacemos nada
    // Puedes agregar más lógica aquí si es necesario
  }

  // Detenemos la acción predeterminada del enlace
  if (event) {
    event.preventDefault();
  }
}

function ocultarPopup() {
  // Ocultar el overlay y el popup
  document.getElementById('overlay').style.display = 'none';
  document.getElementById('popup').style.display = 'none';
}

function eliminar() {
  // Lógica para eliminar el elemento
  alert('Elemento eliminado correctamente.');
  ocultarPopup();
}
