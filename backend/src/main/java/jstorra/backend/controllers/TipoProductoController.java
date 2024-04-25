package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.TipoProducto;
import jstorra.backend.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiposproducto")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class TipoProductoController {
    @Autowired
    TipoProductoService tipoProductoService;

    @GetMapping
    public List<TipoProducto> obtenerTipos() {
        return tipoProductoService.obtenerTipos();
    }

    @GetMapping("/{id}")
    public TipoProducto obtenerTipoPorId(@PathVariable Object id) {
        return tipoProductoService.obtenerTipoPorId(id);
    }

    @PostMapping
    public TipoProducto crearTipo(@RequestBody TipoProducto tipoProducto) {
        return tipoProductoService.crearTipo(tipoProducto);
    }

    @PutMapping("/{id}")
    public TipoProducto editarTipo(@PathVariable Object id, @RequestBody TipoProducto tipoProducto) {
        return tipoProductoService.editarTipo(id, tipoProducto);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipo(@PathVariable Object id) {
        tipoProductoService.eliminarTipo(id);
    }
}
