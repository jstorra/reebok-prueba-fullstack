package jstorra.backend.repositories;

import jstorra.backend.models.ColorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorProductoRepository extends JpaRepository<ColorProducto, Integer> {
    ColorProducto findByNombre(String nombre);
}
