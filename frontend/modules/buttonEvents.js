const buttonEvents = (uri, config, btnsEditar, btnsEliminar) => {
    btnsEditar.forEach((button) => {
        button.addEventListener("click", async () => {
            const btnAgregar = button.parentNode.parentNode.parentNode.parentNode.querySelector("form").querySelector("button");
            const inputs = button.parentNode.parentNode.parentNode.parentNode.querySelector("form").querySelectorAll("input");
            const res = await (await fetch(uri + button.dataset.action, config)).json();
            inputs.forEach((input) => {
                input.value = res[input.name];
                btnAgregar.textContent = "Actualizar";
                btnAgregar.dataset.action = button.dataset.action;
            })
        })
    });

    btnsEliminar.forEach((button) => {
        button.addEventListener("click", async () => {
            config["method"] = "DELETE";
            const res = await fetch(uri + button.dataset.action, config)
            if (res.ok) {
                Swal.fire({
                    icon: "success",
                    title: "¡Registro eliminado!",
                }).then(() => {
                    window.location.reload();
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Puede que el registro ya se encuentre asociado"
                });
            }
        })
    });
}

export default buttonEvents
