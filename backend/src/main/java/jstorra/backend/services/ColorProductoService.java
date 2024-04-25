package jstorra.backend.services;

import jstorra.backend.exceptions.DuplicateEntry;
import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.ColorProducto;
import jstorra.backend.repositories.ColorProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorProductoService {
    @Autowired
    ColorProductoRepository colorProductoRepository;

    public List<ColorProducto> obtenerColores() {
        return colorProductoRepository.findAll();
    }

    public ColorProducto obtenerColorPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return colorProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El color no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public ColorProducto crearColor(ColorProducto colorProducto) {
        if (colorProductoRepository.findByNombre(colorProducto.getNombre()) != null)
            throw new DuplicateEntry("El color ya existe");

        return colorProductoRepository.save(colorProducto);
    }

    public ColorProducto editarColor(Object id, ColorProducto colorProducto) {
        try {
            int newId = Integer.parseInt(id.toString());

            ColorProducto colorToUpdate = colorProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El color no existe"));

            colorToUpdate.setNombre(colorProducto.getNombre());

            return crearColor(colorToUpdate);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarColor(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            colorProductoRepository.delete(colorProductoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El color no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
