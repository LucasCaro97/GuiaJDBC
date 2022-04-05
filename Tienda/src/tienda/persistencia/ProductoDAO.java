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

public class ProductoDAO extends DAO {

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("La persona no puede ser nula");
            }

            String template = "INSERT INTO producto VALUES (NULL, '%s', '%s', '%s');";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(), producto.getCodigoFabricante().getIdFabricante());

            System.out.println("STATEMENT");
            System.out.println(sql);

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar producto");
        }

    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("El fabricante no puede ser nulo");
            }

            String template = "UPDATE producto SET nombre = '%s', precio = '%s' WHERE codigo_fabricante = %s;";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(), producto.getCodigoFabricante());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar producto");
        }
    }

    public void eliminarProducto(Integer idProducto) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = " + idProducto + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar producto");
        }
    }

    public List<Producto> obtenerProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto INNER JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo;";

            queryDatabase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto;

            while (resultSet.next()) {
                producto = new Producto();

                producto.setIdProducto(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));

                Fabricante fabricante = new Fabricante();
                fabricante.setIdFabricante(resultSet.getInt(4));
                fabricante.setNombreFabricante(resultSet.getString(6));

                //Asigno el fabricante al producto
                producto.setCodigoFabricante(fabricante);

                productos.add(producto);
            }

            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener productos");
        } finally {
            disconnectDatabase();
        }
    }

    public List<Producto> obtenerProductosEntre(int min, int max) throws Exception {
        try {
            //CONSULTA PARAMETRIZADA
            String template = "SELECT * FROM producto WHERE precio BETWEEN %s AND %s";
            String sql = String.format(template, min, max );
            queryDatabase(sql);
            
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while( resultSet.next()){
                producto = new Producto();
                producto.setIdProducto(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                productos.add(producto);
            }
            return productos;
            
        } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception("Error al obtener los nombres y precios de los productos");
        } finally{
            disconnectDatabase();
        }

    }
    
    public List<Producto> obtenerPortatiles() throws Exception{
            try{        
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%portatil%'";
            queryDatabase(sql);
            
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            
            while(resultSet.next()){
                producto = new Producto();
                producto.setIdProducto(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                
                productos.add(producto);
            }
            return productos;
            
            } catch(Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Error al obtener los portatiles");
            } finally{
                disconnectDatabase();
            }
            
    }
    
    public Producto obtenerMasBarato() throws Exception{
        try{
            String sql = "SELECT * FROM producto WHERE precio = (SELECT MIN(precio) FROM producto); ";
            queryDatabase(sql);
            
            Producto producto = new Producto();
            while(resultSet.next()){
                producto.setIdProducto(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
            }
            return producto;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener el producto mas barato");
        }finally{
            disconnectDatabase();
        }
    }

    public List<Fabricante> obtenerFabricantes() throws Exception {
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

    public Fabricante obtenerFabricantePorId(Integer idFabricante) throws Exception {

        try {
            String sql = "SELECT * FROM fabricante WHERE codigo = '" + idFabricante + "';";
            queryDatabase(sql);

            Fabricante fabricante = new Fabricante();

            while (resultSet.next()) {
                fabricante.setIdFabricante(resultSet.getInt(1));
                fabricante.setNombreFabricante(resultSet.getString(2));
            }
            return fabricante;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al buscar el fabricante");
        }

    }
    
    
    
}
