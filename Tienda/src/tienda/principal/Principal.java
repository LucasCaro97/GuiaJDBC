/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.principal;

import tienda.entidad.Fabricante;
import tienda.servicio.FabricanteServicio;
import tienda.servicio.ProductoServicio;

/**
 *
 * @author 54375
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ProductoServicio productoserv = new ProductoServicio();
        FabricanteServicio fabricanteserv = new FabricanteServicio();
        
        try{
            productoserv.modificarProducto();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
