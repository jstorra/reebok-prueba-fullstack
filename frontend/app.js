import uri from "./server/config.js";
import Login from "./components/Login.js";
import Registro from "./components/Registro.js";

const ruta = window.location.href.split("views")[0];

document.addEventListener("DOMContentLoaded", async () => {
    const allowedPages = ["/login.html", "/registro.html", "/admin"];

    if (!allowedPages.includes(window.location.href.split("views")[1])) {
        const res = await fetch(uri + "/validarToken", {
            headers: { "Authorization": sessionStorage.getItem("token") }
        });

        if (!res.ok) window.location.href = ruta + "views/login.html";
    }

    if (window.location.href.includes("login.html") || window.location.href.includes("admin.html")) {
        Login(uri, ruta);
    }

    if (window.location.href.includes("registro.html")) {
        Registro(uri, ruta);
    }

    if (window.location.href.includes("admin-manage.html")) {
        const usuario = await (await fetch(uri + "/usuarios/" + sessionStorage.getItem("idUsuario"), {
            headers: { "Authorization": sessionStorage.getItem("token") }
        })).json();

        if (usuario.tipo !== "admin") window.location.href = ruta + "views/cliente.html";
    }
})
