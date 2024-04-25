package jstorra.backend.repositories;

import jstorra.backend.models.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
    FormaPago findByNombre(String nombre);
}
