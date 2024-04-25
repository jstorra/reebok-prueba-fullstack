import crud from "../modules/crud.js";

const Usuario = {
    nombre: "string",
    correo: "string",
    contraseña: "string",
    tipo: "string"
};

export default crud({ endpoint: "/usuarios" , entidad: Usuario });
