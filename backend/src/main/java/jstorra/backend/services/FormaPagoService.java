package jstorra.backend.services;

import jstorra.backend.exceptions.DuplicateEntry;
import jstorra.backend.exceptions.InvalidFormat;
import jstorra.backend.exceptions.ResourceNotFound;
import jstorra.backend.models.FormaPago;
import jstorra.backend.repositories.FormaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagoService {
    @Autowired
    FormaPagoRepository formaPagoRepository;

    public List<FormaPago> obtenerFormasPago() {
        return formaPagoRepository.findAll();
    }

    public FormaPago obtenerFormaPagoPorId(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            return formaPagoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La forma de pago no existe"));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public FormaPago crearFormaPago(FormaPago formaPago) {
        if (formaPagoRepository.findByNombre(formaPago.getNombre()) != null)
            throw new DuplicateEntry("La forma de pago ya existe");

        return formaPagoRepository.save(formaPago);
    }

    public FormaPago editarFormaPago(Object id, FormaPago formaPago) {
        try {
            int newId = Integer.parseInt(id.toString());

            FormaPago formaToUpdate = formaPagoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La forma de pago no existe"));

            formaToUpdate.setNombre(formaPago.getNombre());

            return crearFormaPago(formaToUpdate);
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }

    public void eliminarFormaPago(Object id) {
        try {
            int newId = Integer.parseInt(id.toString());

            formaPagoRepository.delete(formaPagoRepository.findById(newId)
                    .orElseThrow(() -> new ResourceNotFound("La forma de pago no existe")));
        } catch (NumberFormatException e) {
            throw new InvalidFormat("El parametro tiene un formato invalido");
        }
    }
}
