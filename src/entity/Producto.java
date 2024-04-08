package entity;

public class Producto {
    private int id;
    private String nombre;
    private Double precio;
    private int stock;
    private int id_tienda;
    private Tienda objTienda;

    public Producto() {
    }

    public Producto( String nombre, Double precio, int id_tienda) {

        this.nombre = nombre;
        this.precio = precio;
        this.id_tienda = id_tienda;
    }
    public Producto( String nombre, Double precio,int stock, int id_tienda, Tienda objTienda) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_tienda = id_tienda;
        this.objTienda = objTienda;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    @Override
    public String toString() {
        return "Producto " +
                ", nombre = '" + nombre + '\'' +
                ", precio = " + precio +
                ", Stock Producto = " + stock +
                ", Tienda = " + objTienda.getNombre() +
                '}';
    }

}
