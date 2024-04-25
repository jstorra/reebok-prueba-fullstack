package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.Inventario;
import jstorra.backend.models.dtos.InventarioDTO;
import jstorra.backend.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventarios")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;

    @GetMapping
    public List<Inventario> obtenerInventarios() {
        return inventarioService.obtenerInventarios();
    }

    @GetMapping("/{id}")
    public Inventario obtenerInventarioPorId(@PathVariable Object id) {
        return inventarioService.obtenerInventarioPorId(id);
    }

    @PostMapping
    public Inventario crearInventario(@RequestBody InventarioDTO inventarioDTO) {
        return inventarioService.crearInventario(inventarioDTO);
    }

    @PutMapping("/{id}")
    public Inventario editarInventario(@PathVariable Object id, @RequestBody InventarioDTO inventarioDTO) {
        return inventarioService.editarInventario(id, inventarioDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarInventario(@PathVariable Object id) {
        inventarioService.eliminarInventario(id);
    }

    @GetMapping("/cantidad-producto/{id}")
    public Map<String, Integer> cantidadProducto(@PathVariable Object id) {
        return inventarioService.cantidadProducto(id);
    }
}
