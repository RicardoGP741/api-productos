
const BASE_URL = "http://localhost:8080/api/licencias";

document.addEventListener("DOMContentLoaded", () => {
  listarLicencias();
  
  // Form para crear nueva licencia
  const formNuevaLicencia = document.getElementById("form-nueva-licencia");
  formNuevaLicencia.addEventListener("submit", (e) => {
    e.preventDefault();
    crearLicencia();
  });
});

// Func para listar licencias
async function listarLicencias() {
  try {
    const response = await fetch(BASE_URL); // GET /api/licencias
    if (!response.ok) {
      throw new Error("Error al obtener licencias");
    }
    const licencias = await response.json();
    renderizarLicencias(licencias);
  } catch (error) {
    console.error(error);
    alert("Ocurrió un error al listar las licencias.");
  }
}

// Mostrar lista
function renderizarLicencias(licencias) {
  const tbody = document.querySelector("#tabla-licencias tbody");
  tbody.innerHTML = "";

  licencias.forEach((lic) => {
    const fila = document.createElement("tr");

    // Check si lic.rutaImagen es null o vacío
    let imgSrc = lic.rutaImagen && lic.rutaImagen.trim() !== "" 
      ? lic.rutaImagen 
      : "img/no-image.png"; 

    fila.innerHTML = `
      <td>${lic.id}</td>
      <td>${lic.nombreSoftware}</td>
      <td>${lic.precio}</td>
      <td>${lic.stock}</td>
      <td>
        <img 
          src="${imgSrc}" 
          alt="Imagen Software" 
          class="img-thumbnail" 
          style="width: 80px; height: 80px; object-fit: cover;"
        >
      </td>
      <td>
        <button class="btn btn-warning btn-sm" onclick="editarLicencia(${lic.id})">Editar</button>
        <button class="btn btn-danger btn-sm" onclick="eliminarLicencia(${lic.id})">Eliminar</button>
      </td>
    `;
    tbody.appendChild(fila);
  });
}

// Crear Lic
async function crearLicencia() {
  const nombreSoftware = document.getElementById("nombreSoftware").value;
  const descripcion = document.getElementById("descripcion").value;
  const precio = parseFloat(document.getElementById("precio").value);
  const stock = parseInt(document.getElementById("stock").value);

  const nuevaLicencia = {
    nombreSoftware,
    descripcion,
    precio,
    stock
  };

  try {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(nuevaLicencia),
    });

    if (!response.ok) {
      throw new Error("Error al crear la licencia");
    }
    alert("Licencia creada correctamente.");
    limpiarFormulario();
    listarLicencias();
  } catch (error) {
    console.error(error);
    alert("Ocurrió un error al crear la licencia.");
  }
}

function limpiarFormulario() {
  document.getElementById("nombreSoftware").value = "";
  document.getElementById("descripcion").value = "";
  document.getElementById("precio").value = "";
  document.getElementById("stock").value = "";
}

// Comprar lic func
async function comprarLicencia(id) {
  if (!confirm("¿Deseas comprar esta licencia?")) return;

  try {
    // POST /api/licencias/{id}/comprar
    const response = await fetch(`${BASE_URL}/${id}/comprar`, {
      method: "POST",
    });
    if (!response.ok) {
      throw new Error("Error al comprar la licencia");
    }
    alert("Compra realizada con éxito. Se ha generado la clave de activación.");
    listarLicencias();a
  } catch (error) {
    console.error(error);
    alert("Ocurrió un error al comprar la licencia.");
  }
}

// Func DELETE Lic
async function eliminarLicencia(id) {
  if (!confirm("¿Deseas eliminar esta licencia?")) return;

  try {
    const response = await fetch(`${BASE_URL}/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Error al eliminar la licencia");
    }
    alert("Licencia eliminada correctamente.");
    listarLicencias();
  } catch (error) {
    console.error(error);
    alert("Ocurrió un error al eliminar la licencia.");
  }
}
