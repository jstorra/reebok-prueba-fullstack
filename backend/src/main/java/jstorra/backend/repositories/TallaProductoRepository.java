package jstorra.backend.repositories;

import jstorra.backend.models.TallaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TallaProductoRepository extends JpaRepository<TallaProducto, Integer> {
    TallaProducto findByNombre(String nombre);
}
