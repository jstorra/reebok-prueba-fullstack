package jstorra.backend.services;

import jstorra.backend.exceptions.DuplicateEntry;
import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.TallaProducto;
import jstorra.backend.repositories.TallaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallaProductoService {
    @Autowired
    TallaProductoRepository tallaProductoRepository;

    public List<TallaProducto> obtenerTallas() {
        return tallaProductoRepository.findAll();
    }

    public TallaProducto obtenerTallaPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return tallaProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La talla no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public TallaProducto crearTalla(TallaProducto tallaProducto) {
        if (tallaProductoRepository.findByNombre(tallaProducto.getNombre()) != null)
            throw new DuplicateEntry("La talla ya existe");

        return tallaProductoRepository.save(tallaProducto);
    }

    public TallaProducto editarTalla(Object id, TallaProducto tallaProducto) {
        try {
            int newId = Integer.parseInt(id.toString());

            TallaProducto tallaToUpdate = tallaProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La talla no existe"));

            tallaToUpdate.setNombre(tallaProducto.getNombre());

            return crearTalla(tallaToUpdate);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarTalla(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            tallaProductoRepository.delete(tallaProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La talla no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
