const Login = (uri, ruta) => {
    const form = document.querySelector("form");

    const rutaActual = window.location.href;

    if (rutaActual.includes("login.html")) {
        const btnRegistrar = document.querySelector(".registrar");
        btnRegistrar.addEventListener("click", () => {
            window.location.href = ruta + "views/registro.html";
        })
    }

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const body = Object.fromEntries(new FormData(e.target));
        
        const res = await fetch(uri + "/usuarios/ingresar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        })

        const message = await res.json();

        if (res.ok) {
            sessionStorage.setItem("token", message.token);
            sessionStorage.setItem("idUsuario", message.idUsuario);

            const usuario = await (await fetch(uri + "/usuarios/" + message.idUsuario, {
                headers: { "Authorization": message.token }
            })).json();

            if (usuario.tipo === "admin") {
                window.location.href = ruta + "views/admin-manage.html";
            } else {
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

export default Login;
