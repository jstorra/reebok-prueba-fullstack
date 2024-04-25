package jstorra.backend.services;

import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.*;
import jstorra.backend.models.dtos.InventarioDTO;
import jstorra.backend.repositories.InventarioRepository;
import jstorra.backend.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    public List<Inventario> obtenerInventarios() {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerInventarioPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return inventarioRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El inventario no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public Inventario crearInventario(InventarioDTO inventarioDTO) {
        Inventario inventario = new Inventario();

        inventario.setId(inventarioDTO.getId()); // necesario para editar inventarios y no para crear
        inventario.setCantidad(inventarioDTO.getCantidad());

        Producto producto = productoRepository.findById(inventarioDTO.getIdProducto())
                .orElseThrow(() -> new ResourceNotFound("El producto no existe"));

        inventario.setProducto(producto);

        return inventarioRepository.save(inventario);
    }

    public Inventario editarInventario(Object id, InventarioDTO inventarioDTO) {
        try {
            int newId = Integer.parseInt(id.toString());

            inventarioRepository.findById(newId).orElseThrow(() -> new ResourceNotFound("El inventario no existe"));

            inventarioDTO.setId(newId);

            return crearInventario(inventarioDTO);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarInventario(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            inventarioRepository.delete(inventarioRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El inventario no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
