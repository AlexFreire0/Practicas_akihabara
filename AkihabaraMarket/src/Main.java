import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.view.InterfazConsola;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Instanciamos la vista y el DAO
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO productoDAO = new ProductoDAO();

        boolean continuar = true;
        while (continuar) {
            // Mostrar el menú y leer la opción elegida por el usuario
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1: // Añadir producto
                    ProductoOtaku nuevoProducto = vista.pedirDatosProducto();
                    productoDAO.agregarProducto(nuevoProducto);
                    vista.mostrarMensaje("Producto agregado con éxito.");
                    break;

                case 2: // Consultar producto por ID
                    int idConsulta = vista.pedirIdProducto();
                    ProductoOtaku productoPorId = productoDAO.obtenerProductoPorId(idConsulta);
                    if (productoPorId != null) {
                        vista.mostrarProductos(List.of(productoPorId));
                    } else {
                        vista.mostrarMensaje("Producto no encontrado.");
                    }
                    break;

                case 3: // Listar todos los productos
                    List<ProductoOtaku> todosLosProductos = productoDAO.obtenerTodosLosProductos();
                    vista.mostrarProductos(todosLosProductos);
                    break;

                case 4: // Listar productos por nombre
                    String nombreBusqueda = vista.pedirNombreProducto();
                    List<ProductoOtaku> productosPorNombre = productoDAO.buscarProductosPorNombre(nombreBusqueda);
                    vista.mostrarProductos(productosPorNombre);
                    break;

                case 5: // Listar productos por categoría
                    String categoriaBusqueda = vista.pedirCategoriaProducto();
                    List<ProductoOtaku> productosPorCategoria = productoDAO.buscarProductoPorCategoria(categoriaBusqueda);
                    vista.mostrarProductos(productosPorCategoria);
                    break;

                case 6: // Actualizar producto
                    int idActualizar = vista.pedirIdProducto();
                    ProductoOtaku productoActualizar = productoDAO.obtenerProductoPorId(idActualizar);
                    if (productoActualizar != null) {
                        ProductoOtaku nuevosDatos = vista.pedirDatosProducto();
                        productoActualizar.setNombre(nuevosDatos.getNombre());
                        productoActualizar.setCategoria(nuevosDatos.getCategoria());
                        productoActualizar.setPrecio(nuevosDatos.getPrecio());
                        productoActualizar.setStock(nuevosDatos.getStock());
                        boolean actualizado = productoDAO.actualizarProducto(productoActualizar);
                        vista.mostrarMensaje(actualizado ? "Producto actualizado con éxito." : "Error al actualizar el producto.");
                    } else {
                        vista.mostrarMensaje("Producto no encontrado.");
                    }
                    break;

                case 7: // Eliminar producto
                    int idEliminar = vista.pedirIdProducto();
                    boolean eliminado = productoDAO.eliminarProducto(idEliminar);
                    vista.mostrarMensaje(eliminado ? "Producto eliminado con éxito." : "Error al eliminar el producto.");
                    break;

                case 8: // Salir
                    vista.mostrarMensaje("¡Gracias por usar el sistema!");
                    continuar = false;
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
                    break;
            }
        }
    }
}
