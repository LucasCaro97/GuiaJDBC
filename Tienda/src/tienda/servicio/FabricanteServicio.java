/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicio;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author 54375
 */
public class FabricanteServicio {

    private final FabricanteDAO fabricanteDAO;
    Scanner entrada = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public FabricanteServicio() {
        fabricanteDAO = new FabricanteDAO();
    }

    public void mostrarFabricantes() throws Exception {
        try {
            List<Fabricante> fabricantes = fabricanteDAO.obtenerFabricante();

            if (fabricantes.isEmpty()) {
                throw new Exception("No existen Fabricantes");
            } else {
                System.out.println("Lista de Fabricantes");
                System.out.printf("%-10s%-15s\n", "ID", "NOMBRE");
                for (Fabricante fabricante : fabricantes) {
                    System.out.printf("%-10s%-15s\n", fabricante.getIdFabricante(), fabricante.getNombreFabricante());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void crearFabricanteNuevo() throws Exception {

        try {

            System.out.println("Ingrese un fabricante: ");
            String nombre = entrada.next();

            Fabricante fabricante = new Fabricante();
            fabricante.setNombreFabricante(nombre);
            fabricanteDAO.guardarFabricante(fabricante);

        } catch (Exception e) {
            throw e;
        }

    }
    

}
