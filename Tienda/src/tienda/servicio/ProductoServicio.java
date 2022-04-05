package tienda.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;
import tienda.servicio.FabricanteServicio;
import tienda.persistencia.ProductoDAO;

public class ProductoServicio {

    private final ProductoDAO productoDAO;

    FabricanteServicio fabricanteserv = new FabricanteServicio();

    Scanner entrada = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public ProductoServicio() {
        productoDAO = new ProductoDAO();
    }

    public void mostrarNombresProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.obtenerProductos();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA PRODUCTOS");
                for (Producto producto : productos) {
                    System.out.println(producto.getNombre());
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarNombreYPrecioProductos() throws Exception {

        try {
            List<Producto> productos = productoDAO.obtenerProductos();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {

                System.out.println("LISTA PRODUCTOS");
                System.out.printf("%-40s%-40s\n", "NOMBRE", "PRECIO");
                for (Producto producto : productos) {

                    System.out.printf("%-40s%-40s\n", producto.getNombre(), producto.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void mostrarProductosRangoPrecio() throws Exception {
        try {
            int min = 120;
            int max = 202;
            List<Producto> productos = productoDAO.obtenerProductosEntre(min, max);

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("PRODUCTOS ENTRE 120 Y 200");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                for (Producto producto : productos) {

                    System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void obtenerPortatiles() throws Exception {
        try {
            List<Producto> productos = productoDAO.obtenerPortatiles();

            if (productos.isEmpty()) {
                System.out.println("No existen productos");
            } else {
                System.out.println("LISTA DE PORTATILES");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                for (Producto producto : productos) {

                    System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al capturar los portatiles");
        }

    }

    public void obtenerProductoMasBarato() throws Exception {
        try {
            Producto producto = productoDAO.obtenerMasBarato();
            if (producto == null) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("PRODUCTO MAS BARATO");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener el producto mas barato");
        }
    }

    public void crearProductoNuevo() throws Exception {

        try {

            System.out.println("Ingrese un nombre: ");
            String nombre = entrada.next();
            System.out.println("Ingrese un precio: ");
            Double precio = entrada.nextDouble();
            System.out.println("Ingrese un codigo de fabricante de la lista: ");
            System.out.println("");
            //MUESTRO LA LISTA DE FABRICANTES
            fabricanteserv.mostrarFabricantes();

            Integer codfabricante = entrada.nextInt();
            //HAGO LA BUSQUEDA POR ID DE FABRICANTE PARA GUARDAR EL OBJETO FABRICANTE EN EL OBJETO PRODUCTO
            Fabricante fabricante = productoDAO.obtenerFabricantePorId(codfabricante);

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(fabricante);

            productoDAO.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }

    }

    public void crearProducto(String nombre, Double precio, Fabricante codigoFabricante) throws Exception {

        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            if (precio == null) {
                throw new Exception("El precio es obligatorio");
            }

            if (codigoFabricante == null) {
                throw new Exception("El fabricante es obligatorio");
            }

            Producto producto = new Producto();

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);

            productoDAO.guardarProducto(producto);

        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.obtenerProductos();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("Lista de Productos");
                System.out.printf("%-20s%-35s%-35s%-40s\n", "ID", "NOMBRE", "PRECIO", "FABRICANTE");
                for (Producto producto : productos) {
                    System.out.printf("%-20s%-35s%-35s%-40s\n", producto.getIdProducto(), producto.getNombre(), producto.getPrecio(), producto.getCodigoFabricante().getNombreFabricante());
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }
    
    public void modificarProducto() throws Exception {
        try {
            
            System.out.println("Seleccione el codigo del producto que desea modificar:");
            System.out.printf("%-50s%-20s\n", "CODIGO", "NOMBRE" );
            List<Producto> productos = productoDAO.obtenerProductos();
            for (Producto producto : productos) {
                System.out.printf("%-50s%-20s\n", producto.getIdProducto(), producto.getNombre() );
            }
            int productomodificar = entrada.nextInt();
            
            System.out.println("Ingrese el nombre:");
            String nombre = entrada.next();
            System.out.println("Ingrese el precio:");
            Double precio = entrada.nextDouble();
            System.out.println("Ingrese un fabricante de la lista:");
            System.out.println("");
            fabricanteserv.mostrarFabricantes();
            Integer codfabricante = entrada.nextInt();
            //HAGO LA BUSQUEDA POR ID DE FABRICANTE PARA GUARDAR EL OBJETO FABRICANTE EN EL OBJETO PRODUCTO
            Fabricante fabricante = productoDAO.obtenerFabricantePorId(codfabricante);
            
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(fabricante);

            productoDAO.guardarProducto(producto);
            
        } catch (Exception e) {
            throw e;
        }
    }
}
