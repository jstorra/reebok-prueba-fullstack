package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.Venta;
import jstorra.backend.models.dtos.VentaDTO;
import jstorra.backend.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerVentas() {
        return ventaService.obtenerVentas();
    }

    @GetMapping("/{id}")
    public Venta obtenerVentaPorId(@PathVariable Object id) {
        return ventaService.obtenerVentaPorId(id);
    }

    @PostMapping
    public Venta crearVenta(@RequestBody VentaDTO ventaDTO) {
        return ventaService.crearVenta(ventaDTO);
    }

    @PutMapping("/{id}")
    public Venta editarVenta(@PathVariable Object id, @RequestBody VentaDTO ventaDTO) {
        return ventaService.editarVenta(id, ventaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Object id) {
        ventaService.eliminarVenta(id);
    }
}
