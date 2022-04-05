/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;

/**
 *
 * @author 54375
 */
public class FabricanteDAO extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("El fabricante no puede ser nulo");
            }

            String template = "INSERT INTO fabricante VALUES (NULL, '%s');";
            String sql = String.format(template, fabricante.getNombreFabricante());

            System.out.println("STATEMENT");
            System.out.println(sql);

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar fabricante");
        }

    }

    public void modificarFabricante(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("El fabricante no puede ser nulo");
            }

            String sql = "UPDATE fabricante SET nombre = " + producto.getNombre() + "  WHERE codigo_fabricante = %s;";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar fabricante");
        }
    }

    public void eliminarFabricante(Integer idFabricante) throws Exception {
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = " + idFabricante + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar fabricante");
        }
    }

    public List<Fabricante> obtenerFabricante() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante;";

            queryDatabase(sql);

            List<Fabricante> fabricantes = new ArrayList<>();
            Fabricante fabricante;

            while (resultSet.next()) {
                fabricante = new Fabricante();

                fabricante.setIdFabricante(resultSet.getInt(1));
                fabricante.setNombreFabricante(resultSet.getString(2));

                fabricantes.add(fabricante);
            }

            return fabricantes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener fabricantes");
        } finally {
            disconnectDatabase();
        }
    }

}
