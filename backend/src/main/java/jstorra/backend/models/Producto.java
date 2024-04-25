package jstorra.backend.models;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "id_tipo_fk")
    private TipoProducto tipo;

    @ManyToOne
    @JoinColumn(name = "id_color_fk")
    private ColorProducto color;

    @ManyToOne
    @JoinColumn(name = "id_talla_fk")
    private TallaProducto talla;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public ColorProducto getColor() {
        return color;
    }

    public void setColor(ColorProducto color) {
        this.color = color;
    }

    public TallaProducto getTalla() {
        return talla;
    }

    public void setTalla(TallaProducto talla) {
        this.talla = talla;
    }
}
