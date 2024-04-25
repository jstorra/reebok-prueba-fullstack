package jstorra.backend.repositories;

import jstorra.backend.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    @Query("SELECT SUM(i.cantidad) FROM Inventario i WHERE i.producto.id = ?1")
    int cantidadProducto(int id);
}
