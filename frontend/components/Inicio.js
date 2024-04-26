const Inicio = async (uri, config) => {
    const dataContainer = document.querySelector(".data");
    const res = await (await fetch(uri + "/productos", config)).json();
    console.log(res);

    const cards = res.map((producto) => (`
        <div class="content-data producto">
            <div class="img-producto">
                <img src="${producto.urlImagen}" />
            </div>
            <div class="info-producto">
                <h3>${producto.nombre}</h3>
                <div>
                    <p><b>Tipo:</b> ${producto.tipo.nombre}</p>
                </div>
                <div>
                    <p><b>Color:</b> ${producto.color.nombre}</p>
                </div>
                <div>
                    <p><b>Talla:</b> ${producto.talla.nombre}</p>
                </div>
                <button class="añadir">$${producto.precio}<i class='bx bxs-cart-add'></i></button>
            </div>
        </div>
    `)).join("");

    dataContainer.innerHTML = cards;
}

export default Inicio;
