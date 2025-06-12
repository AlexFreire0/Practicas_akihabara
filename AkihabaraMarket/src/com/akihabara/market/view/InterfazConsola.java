package com.akihabara.market.view;

import com.akihabara.market.model.ProductoOtaku;
import java.util.List;
import java.util.Scanner;
import com.akihabara.market.llm.LlmService;
public class InterfazConsola {

    private Scanner scanner = new Scanner(System.in);

    // Mostrar el menú principal
    public void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Listar productos por nombre");
        System.out.println("5. Listar productos por categoría");
        System.out.println("6. Actualizar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Salir");
        System.out.print("Elija una opción: ");
    }

    // Leer la opción válida del menú
    public int leerOpcion() {
        int opcion = -1;
        while (opcion < 1 || opcion > 8) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 1 || opcion > 8) {
                    System.out.print("Opción no válida. Por favor elija una opción entre 1 y 8: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Por favor ingrese un número: ");
            }
        }
        return opcion;
    }

    // Leer los datos de un nuevo producto
    public ProductoOtaku pedirDatosProducto() {
        LlmService llmService = new LlmService();
        System.out.print("Ingrese el tipo de producto (ej. figura, camiseta): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese la franquicia (ej. Naruto, One Piece): ");
        String franquicia = scanner.nextLine();

        String nombreSugerido = llmService.sugerirNombreProducto(tipo, franquicia);
        System.out.println("Nombre sugerido por la IA: " + nombreSugerido);

        System.out.print("¿Desea usar este nombre sugerido? (s/n): ");
        String eleccion = scanner.nextLine();

        String nombre;
        if (eleccion.equalsIgnoreCase("s")) {
            nombre = nombreSugerido;
        } else {
            System.out.print("Ingrese el nombre del producto: ");
            nombre = scanner.nextLine();
        }

        System.out.print("Ingrese la categoría del producto: ");
        String categoria = scanner.nextLine();

        double precio = -1;
        while (precio <= 0) {
            try {
                System.out.print("Ingrese el precio del producto: ");
                precio = Double.parseDouble(scanner.nextLine());
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. El precio debe ser un número.");
            }
        }
        int stock = -1;
        while (stock < 0) {
            try {
                System.out.print("Ingrese el stock del producto: ");
                stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. El stock debe ser un número entero.");
            }
        }

        return new ProductoOtaku(0, nombre, categoria, precio, stock);
    }

    // Leer ID para consultar o eliminar producto
    public int pedirIdProducto() {
        int id = -1;
        while (id <= 0) {
            try {
                System.out.print("Ingrese el ID del producto: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("El ID debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. El ID debe ser un número entero.");
            }
        }
        return id;
    }

    // Leer nombre de producto para búsqueda
    public String pedirNombreProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        return scanner.nextLine();
    }

    // Leer categoría de producto para búsqueda
    public String pedirCategoriaProducto() {
        System.out.print("Ingrese la categoría del producto: ");
        return scanner.nextLine();
    }

    // Mostrar un mensaje de confirmación
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Mostrar lista de productos
    public void mostrarProductos(List<ProductoOtaku> productos) {
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            for (ProductoOtaku producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }
}

