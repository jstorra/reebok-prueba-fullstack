package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.Producto;
import jstorra.backend.models.dtos.ProductoDTO;
import jstorra.backend.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Object id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.crearProducto(productoDTO);
    }

    @PutMapping("/{id}")
    public Producto editarProducto(@PathVariable Object id, @RequestBody ProductoDTO productoDTO) {
        return productoService.editarProducto(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Object id) {
        productoService.eliminarProducto(id);
    }
}
