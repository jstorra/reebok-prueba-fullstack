package jstorra.backend.services;

import jstorra.backend.exceptions.DuplicateEntry;
import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.InvalidUser;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.Usuario;
import jstorra.backend.repositories.UsuarioRepository;
import jstorra.backend.security.JWTAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UsuarioService {
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Map<String, Object> ingresar(Map<String, String> credenciales) {
        if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(credenciales.get("correo")).matches())
            throw new InvalidFormat("El correo no tiene un formato valido");

        Usuario usuario = usuarioRepository.findByCorreo(credenciales.get("correo"));
        if (usuario == null || !usuario.getContraseña().equalsIgnoreCase(credenciales.get("contraseña"))) {
            throw new InvalidUser("Las credenciales son incorrectas");
        }
        String token = jwtAuthenticationConfig.getJWTToken(usuario.getCorreo());
        return new LinkedHashMap<>() {{
            put("token", token);
            put("idUsuario", usuario.getId());
        }};
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return usuarioRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El usuario no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(usuario.getCorreo()).matches())
            throw new InvalidFormat("El correo no tiene un formato valido");

        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null)
            throw new DuplicateEntry("El correo ya esta asociado a otro usuario");

        return usuarioRepository.save(usuario);
    }

    public Usuario editarUsuario(Object id, Usuario usuario) {
        try {
            int newId = Integer.parseInt(id.toString());

            Usuario usuarioToUpdate = usuarioRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El usuario no existe"));

            usuarioToUpdate.setNombre(usuario.getNombre());
            usuarioToUpdate.setCorreo(usuario.getCorreo());
            usuarioToUpdate.setContraseña(usuario.getContraseña());
            usuarioToUpdate.setTipo(usuario.getTipo());

            return crearUsuario(usuarioToUpdate);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarUsuario(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            usuarioRepository.delete(usuarioRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El usuario no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
