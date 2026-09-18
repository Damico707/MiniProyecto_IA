const API_URL = "http://localhost:8080/api/correos";

async function cargarPorPeriodo(periodo) {

    try {

        const respuesta = await fetch(
            `http://localhost:8080/api/correos?periodo=${periodo}`
        );

        const correos =
            await respuesta.json();

        mostrarCorreos(correos);


        // GENERAR RESUMEN CON IA
        cargarResumen(periodo);


    } catch (error) {

        console.error(
            "Error cargando correos:",
            error
        );

    }

}


function mostrarCorreos(correos) {

    const lista = document.getElementById("listaCorreos");

    lista.innerHTML = "";

    correos.forEach(correo => {

        const fila = document.createElement("div");

        fila.classList.add("tabla", "correo-row");

        fila.innerHTML = `

            <div>
                <input type="checkbox">
            </div>

            <div class="remitente">

                <div class="mini-avatar azul">
                    ${correo.remitente.charAt(0)}
                </div>

                <span>
                    ${correo.remitente}
                </span>

            </div>

            <div>
                ${correo.asunto}
            </div>

            <div class="preview">
                ${correo.contenido}
            </div>

            <div class="fecha">
                ${correo.fecha}
            </div>

            <div class="star">
                ${correo.destacado ? "★" : "☆"}
            </div>

        `;

        lista.appendChild(fila);

    });

}

async function cargarResumen(periodo) {

    const resumen =
        document.getElementById("textoResumen");

    resumen.textContent =
        "Analizando correos...";

    try {

        const respuesta = await fetch(
            "http://localhost:5678/webhook/resumen-correos",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    periodo: periodo
                })
            }
        );


        if (!respuesta.ok) {

            throw new Error(
                "No se pudo generar el resumen"
            );

        }


        const datos =
            await respuesta.json();


        resumen.textContent =
            datos.resumen;


    } catch (error) {

        console.error(error);

        resumen.textContent =
            "No se pudo generar el resumen.";

    }

}


// IMPORTANTE
cargarPorPeriodo("semana");