import buttonEvents from "../modules/buttonEvents.js";

const Caracteristicas = async (uri) => {
    const config = { headers: { "Authorization": sessionStorage.getItem("token") } }

    const forms = document.querySelectorAll("form");

    forms.forEach((form) => {
        form.addEventListener("submit", async (e) => {
            e.preventDefault()

            const body = Object.fromEntries(new FormData(e.target));

            config["method"] = "POST";
            config["body"] = JSON.stringify(body);
            config.headers["Content-Type"] = "application/json";

            const res = await fetch(uri + form.dataset.action, config)

            const message = await res.json();

            if (res.ok) {
                Swal.fire({
                    icon: "success",
                    title: "¡Registro existoso!",
                }).then(() => {
                    window.location.reload();
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: `${message.message}`,
                }).then(() => {
                    window.location.reload();
                });
            }
        })
    })

    const divTipos = document.querySelector(".tipos-producto");
    const divColores = document.querySelector(".colores-producto");
    const divTallas = document.querySelector(".tallas-producto");

    const tipos = await (await fetch(uri + "/tiposproducto", config)).json()
    const colores = await (await fetch(uri + "/coloresproducto", config)).json()
    const tallas = await (await fetch(uri + "/tallasproducto", config)).json()

    const cardsTipos = tipos.map((tipo) => (`
        <div class="card">
            <span>${tipo.nombre}</span>
            <div class="actions">
                <i data-action="/tiposproducto/${tipo.id}" class='bx bxs-edit-alt editar'></i>
                <i data-action="/tiposproducto/${tipo.id}" class='bx bxs-trash eliminar'></i>
            </div>
        </div>
    `)).join("");

    divTipos.innerHTML = cardsTipos;

    const cardsColores = colores.map((tipo) => (`
        <div class="card">
            <span>${tipo.nombre}</span>
            <div class="actions">
                <i data-action="/coloresproducto/${tipo.id}" class='bx bxs-edit-alt editar'></i>
                <i data-action="/coloresproducto/${tipo.id}" class='bx bxs-trash eliminar'></i>
            </div>
        </div>
    `)).join("");

    divColores.innerHTML = cardsColores;

    const cardsTallas = tallas.map((tipo) => (`
    <div class="card">
        <span>${tipo.nombre}</span>
        <div class="actions">
                <i data-action="/tallasproducto/${tipo.id}" class='bx bxs-edit-alt editar'></i>
                <i data-action="/tallasproducto/${tipo.id}" class='bx bxs-trash eliminar'></i>
        </div>
    </div>
    `)).join("");

    divTallas.innerHTML = cardsTallas;

    const btnsEditar = document.querySelectorAll(".editar");
    const btnsEliminar = document.querySelectorAll(".eliminar");

    buttonEvents(uri, config, btnsEditar, btnsEliminar);
}

export default Caracteristicas;