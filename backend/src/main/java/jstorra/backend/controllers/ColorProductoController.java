package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.ColorProducto;
import jstorra.backend.services.ColorProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coloresproducto")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class ColorProductoController {
    @Autowired
    ColorProductoService colorProductoService;

    @GetMapping
    public List<ColorProducto> obtenerColores() {
        return colorProductoService.obtenerColores();
    }

    @GetMapping("/{id}")
    public ColorProducto obtenerColorPorId(@PathVariable Object id) {
        return colorProductoService.obtenerColorPorId(id);
    }

    @PostMapping
    public ColorProducto crearColor(@RequestBody ColorProducto colorProducto) {
        return colorProductoService.crearColor(colorProducto);
    }

    @PutMapping("/{id}")
    public ColorProducto editarColor(@PathVariable Object id, @RequestBody ColorProducto colorProducto) {
        return colorProductoService.editarColor(id, colorProducto);
    }

    @DeleteMapping("/{id}")
    public void eliminarColor(@PathVariable Object id) {
        colorProductoService.eliminarColor(id);
    }
}
