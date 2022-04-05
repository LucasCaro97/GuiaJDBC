

package tienda.entidad;



public class Fabricante {
    
    
    private int idFabricante;
    private String nombreFabricante;

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public Fabricante() {
    }

    public Fabricante(int idFabricante, String nombreFabricante) {
        this.idFabricante = idFabricante;
        this.nombreFabricante = nombreFabricante;
    }

    @Override
    public String toString() {
        return "Fabricante{" + "idFabricante=" + idFabricante + ", nombreFabricante=" + nombreFabricante + '}';
    }
        
    
}
