package jstorra.backend.services;

import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.*;
import jstorra.backend.models.dtos.VentaDTO;
import jstorra.backend.repositories.FormaPagoRepository;
import jstorra.backend.repositories.ProductoRepository;
import jstorra.backend.repositories.UsuarioRepository;
import jstorra.backend.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FormaPagoRepository formaPagoRepository;

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentaPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return ventaRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La venta no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public Venta crearVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();

        venta.setId(ventaDTO.getId()); // necesario para editar ventas y no para crear
        venta.setFecha(ventaDTO.getFecha());
        venta.setUnidades(ventaDTO.getUnidades());
        venta.setTotal(ventaDTO.getTotal());

        Producto producto = productoRepository.findById(ventaDTO.getIdProducto())
                .orElseThrow(() -> new ResourceNotFound("El producto no existe"));

        venta.setProducto(producto);

        Usuario cliente = usuarioRepository.findById(ventaDTO.getIdCliente())
                .orElseThrow(() -> new ResourceNotFound("El cliente no existe"));

        venta.setCliente(cliente);

        FormaPago formaPago = formaPagoRepository.findById(ventaDTO.getIdFormaPago())
                .orElseThrow(() -> new ResourceNotFound("La forma de pago no existe"));

        venta.setFormaPago(formaPago);

        return ventaRepository.save(venta);
    }

    public Venta editarVenta(Object id, VentaDTO ventaDTO) {
        try {
            int newId = Integer.parseInt(id.toString());

            ventaRepository.findById(newId).orElseThrow(() -> new ResourceNotFound("La venta no existe"));

            ventaDTO.setId(newId);

            return crearVenta(ventaDTO);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarVenta(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            ventaRepository.delete(ventaRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La venta no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
