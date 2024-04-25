package jstorra.backend.services;

import jstorra.backend.exceptions.DuplicateEntry;
import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.TipoProducto;
import jstorra.backend.repositories.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoService {
    @Autowired
    TipoProductoRepository tipoProductoRepository;

    public List<TipoProducto> obtenerTipos() {
        return tipoProductoRepository.findAll();
    }

    public TipoProducto obtenerTipoPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());
            return tipoProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El tipo de producto no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public TipoProducto crearTipo(TipoProducto tipoProducto) {
        if (tipoProductoRepository.findByNombre(tipoProducto.getNombre()) != null)
            throw new DuplicateEntry("El tipo de producto ya existe");

        return tipoProductoRepository.save(tipoProducto);
    }

    public TipoProducto editarTipo(Object id, TipoProducto tipoProducto) {
        try {
            int newId = Integer.parseInt(id.toString());

            TipoProducto tipoToUpdate = tipoProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El tipo de producto no existe"));

            tipoToUpdate.setNombre(tipoProducto.getNombre());

            return crearTipo(tipoToUpdate);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarTipo(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            tipoProductoRepository.delete(tipoProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El tipo de producto no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
