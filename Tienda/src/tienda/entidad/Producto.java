

package tienda.entidad;



public class Producto {
    
        
    private Integer idProducto;
    private String nombre;
    private Double precio;
    private Fabricante codigoFabricante;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public Fabricante getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(Fabricante codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public Producto() {
    }

    public Producto(Integer idProducto, String nombre, Double precio, Fabricante codigoFabricante) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.codigoFabricante = codigoFabricante;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", codigoFabricante=" + codigoFabricante + '}';
    }
    
}
