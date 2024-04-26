const buttonEvents = (uri, config, btnsEditar, btnsEliminar) => {
    btnsEditar.forEach((button) => {
        button.addEventListener("click", () => {
            console.log(button.parentNode.parentNode.parentNode.parentNode);
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
