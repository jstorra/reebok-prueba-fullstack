package jstorra.backend.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp fecha;
    private int unidades;
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_producto_fk")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente_fk")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "id_forma_pago_fk")
    private FormaPago formaPago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }
}
