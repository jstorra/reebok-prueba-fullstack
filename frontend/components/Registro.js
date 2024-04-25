const Registro = (uri, ruta) => {
    const form = document.querySelector("form");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const body = Object.fromEntries(new FormData(e.target));
        body["tipo"] = "cliente";

        const res = await fetch(uri + "/usuarios", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        })

        const message = await res.json();

        if (res.ok) {
            const res = await fetch(uri + "/usuarios/ingresar", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    correo: body["correo"],
                    contraseña: body["contraseña"]
                })
            })

            const message = await res.json();

            if (res.ok) {
                sessionStorage.setItem("token", message.token);
                sessionStorage.setItem("idUsuario", message.idUsuario);
                window.location.href = ruta + "views/cliente.html";
            }
        } else {
            Swal.fire({
                icon: "error",
                title: `${message.message}`,
            });
        }
    })
}

export default Registro;
