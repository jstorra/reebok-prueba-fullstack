package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.FormaPago;
import jstorra.backend.services.FormaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formaspago")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class FormaPagoController {
    @Autowired
    FormaPagoService formaPagoService;

    @GetMapping
    public List<FormaPago> obtenerFormasPago() {
        return formaPagoService.obtenerFormasPago();
    }

    @GetMapping("/{id}")
    public FormaPago obtenerFormaPagoPorId(@PathVariable Object id) {
        return formaPagoService.obtenerFormaPagoPorId(id);
    }

    @PostMapping
    public FormaPago crearFormaPago(@RequestBody FormaPago formaPago) {
        return formaPagoService.crearFormaPago(formaPago);
    }

    @PutMapping("/{id}")
    public FormaPago editarFormaPago(@PathVariable Object id, @RequestBody FormaPago formaPago) {
        return formaPagoService.editarFormaPago(id, formaPago);
    }

    @DeleteMapping("/{id}")
    public void eliminarFormaPago(@PathVariable Object id) {
        formaPagoService.eliminarFormaPago(id);
    }
}
