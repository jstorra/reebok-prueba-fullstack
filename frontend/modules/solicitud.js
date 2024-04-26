const solicitud = async (uri, config, element, success, error) => {
    const res = await fetch(uri + element.dataset.action, config);
    let message = "";

    if (config.method !== "DELETE") { message = await res.json() };

    if (res.ok) {
        Swal.fire({
            icon: "success",
            title: `¡${success}!`,
        }).then(() => {
            window.location.reload();
        });
    } else {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: `${error ?? message.message}`
        }).then(() => {
            if (!error) window.location.reload();
        });
    }
}

export default solicitud;
