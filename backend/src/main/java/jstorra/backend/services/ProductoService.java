package jstorra.backend.services;

import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.*;
import jstorra.backend.models.dtos.ProductoDTO;
import jstorra.backend.repositories.ColorProductoRepository;
import jstorra.backend.repositories.ProductoRepository;
import jstorra.backend.repositories.TallaProductoRepository;
import jstorra.backend.repositories.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    TipoProductoRepository tipoProductoRepository;

    @Autowired
    ColorProductoRepository colorProductoRepository;

    @Autowired
    TallaProductoRepository tallaProductoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return productoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El producto no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();

        producto.setId(productoDTO.getId()); // necesario para editar productos y no para crear
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());

        TipoProducto tipoProducto = tipoProductoRepository.findById(productoDTO.getIdTipo())
                .orElseThrow(() -> new ResourceNotFound("El tipo de producto no existe"));

        producto.setTipo(tipoProducto);

        ColorProducto colorProducto = colorProductoRepository.findById(productoDTO.getIdColor())
                .orElseThrow(() -> new ResourceNotFound("El color no existe"));

        producto.setColor(colorProducto);

        TallaProducto tallaProducto = tallaProductoRepository.findById(productoDTO.getIdTalla())
                .orElseThrow(() -> new ResourceNotFound("La talla no existe"));

        producto.setTalla(tallaProducto);

        return productoRepository.save(producto);
    }

    public Producto editarProducto(Object id, ProductoDTO productoDTO) {
        try {
            int newId = Integer.parseInt(id.toString());

            productoRepository.findById(newId).orElseThrow(() -> new ResourceNotFound("El producto no existe"));

            productoDTO.setId(newId);

            return crearProducto(productoDTO);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarProducto(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            productoRepository.delete(productoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("El producto no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
