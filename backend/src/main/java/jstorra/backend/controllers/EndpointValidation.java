package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.exceptions.ResourceNotFound;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class EndpointValidation {
    List<String> allowedEndpoints = new ArrayList<>(Arrays.asList(
            "tiposproducto", "coloresproducto", "tallasproducto", "formaspago",
            "usuarios", "productos", "inventarios", "ventas", "validarToken"
    ));

    @GetMapping("/{endpoint}")
    public void handleEndpoints(@PathVariable String endpoint) {
        if (!allowedEndpoints.contains(endpoint))
            throw new ResourceNotFound("El endpoint '" + endpoint +"' no esta disponible");
    }
}
