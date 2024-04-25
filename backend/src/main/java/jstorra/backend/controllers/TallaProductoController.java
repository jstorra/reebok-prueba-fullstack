package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.TallaProducto;
import jstorra.backend.services.TallaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tallasproducto")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class TallaProductoController {
    @Autowired
    TallaProductoService tallaProductoService;

    @GetMapping
    public List<TallaProducto> obtenerColores() {
        return tallaProductoService.obtenerTallas();
    }

    @GetMapping("/{id}")
    public TallaProducto obtenerTallaPorId(@PathVariable Object id) {
        return tallaProductoService.obtenerTallaPorId(id);
    }

    @PostMapping
    public TallaProducto crearTalla(@RequestBody TallaProducto tallaProducto) {
        return tallaProductoService.crearTalla(tallaProducto);
    }

    @PutMapping("/{id}")
    public TallaProducto editarTalla(@PathVariable Object id, @RequestBody TallaProducto tallaProducto) {
        return tallaProductoService.editarTalla(id, tallaProducto);
    }

    @DeleteMapping("/{id}")
    public void eliminarTalla(@PathVariable Object id) {
        tallaProductoService.eliminarTalla(id);
    }
}
