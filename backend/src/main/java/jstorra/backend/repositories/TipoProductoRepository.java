package jstorra.backend.repositories;

import jstorra.backend.models.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
    TipoProducto findByNombre(String nombre);
}
