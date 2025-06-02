package com.akihabara.market.dao;
import com.akihabara.market.model.ProductoOtaku;
import java.sql.*;
import java.util.*;

public class ProductoDAO {

	private DatabaseConnection conexion = new DatabaseConnection();
	
	public void agregarProducto(ProductoOtaku producto) {
	try {
		//Creamos el statement que sea el insert
		PreparedStatement psInsertar = DatabaseConnection.getConexion().prepareStatement(
	            "INSERT INTO productos (id, nombre, categoria, precio, stock) VALUES (?,?,?,?,?)");
		//los parametros los metemos en cada interrogación.
		psInsertar.setInt(4, producto.getId());
	psInsertar.setNString(1, producto.getNombre());
	psInsertar.setString(2, producto.getCategoria());
	psInsertar.setDouble(3, producto.getPrecio());	
	psInsertar.setInt(4, producto.getStock());
	psInsertar.executeQuery();
	
	} catch(SQLException e) { //Recogemos las excepciones
		System.out.println("Ha ocurrido un error al intentar insertar el producto: " + e.getMessage());
	}
	
	}
	public ProductoOtaku obtenerProductoPorId(int id) {
		
		ProductoOtaku producto = null;
		
	    try {
	    	//Preparamos el statement de buscar por id y que devuelva el producto como objeto
	        PreparedStatement psBuscar = DatabaseConnection.getConexion().prepareStatement(
	                "SELECT * FROM productos WHERE id = ?");
	        psBuscar.setInt(1, id);

	        ResultSet rs = psBuscar.executeQuery();
	        // metemos en variables los datos recogidos
	        if (rs.next()) {
	            String nombre = rs.getNString("nombre");
	            String categoria = rs.getString("categoria");
	            double precio = rs.getDouble("precio");
	            int stock = rs.getInt("stock");
	            //metemos los datos en el objeto
	             producto = new ProductoOtaku(id, nombre, categoria, precio, stock);
	        }

	        rs.close();
	        psBuscar.close();
	    } catch (SQLException e) {
	        System.out.println("Error al obtener el producto: " + e.getMessage());
	    }
	    	//devolvemos el objeto producto
	    return producto;
	}

	public List<ProductoOtaku> obtenerTodosLosProductos(){
		//creamos la lista a devolver
		 List<ProductoOtaku> productos = new ArrayList<>();
		 	
		    try {
		        // Preparamos el statement para obtener todos los productos
		        PreparedStatement psBuscarTodos = DatabaseConnection.getConexion().prepareStatement(
		                "SELECT * FROM productos");
		        //ejecutamos la busqueda/query
		        ResultSet rs = psBuscarTodos.executeQuery();
		        // Iteramos por la tabla del resultado y creamos los objetos ProductoOtaku por cada uno;
		        while (rs.next()) {
		        	int id = rs.getInt("id");
		            String nombre = rs.getNString("nombre");
		            String categoria = rs.getString("categoria");
		            double precio = rs.getDouble("precio");
		            int stock = rs.getInt("stock");
		            //metemos los datos en objetos
		            ProductoOtaku producto = new ProductoOtaku(id ,nombre, categoria, precio, stock);
		            //metemos los objetos en la array
		            productos.add(producto);
		        }

		        rs.close();
		        psBuscarTodos.close();
		    } catch (SQLException e) {
		        System.out.println("Error al obtener los productos: " + e.getMessage());
		    }
		    //devolvemos la lista
		    return productos;
	}
	
	public boolean actualizarProducto(ProductoOtaku producto) {
		Boolean actualizado = null;
		
		 try {
		        // Preparamos el statement UPDATE, actualizando nombre, categoria, precio y stock donde id sea el del producto
		        PreparedStatement psActualizar = DatabaseConnection.getConexion().prepareStatement(
		            "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?"
		        );
		        
		        psActualizar.setNString(1, producto.getNombre());
		        psActualizar.setString(2, producto.getCategoria());
		        psActualizar.setDouble(3, producto.getPrecio());
		        psActualizar.setInt(4, producto.getStock());
		        psActualizar.setInt(5, producto.getId()); 
		        
		        int filasAfectadas = psActualizar.executeUpdate(); // usamos executeUpdate para actualizarlo.
		        
		        actualizado = (filasAfectadas > 0);
		        
		        psActualizar.close();
		    } catch(SQLException e) {
		        System.out.println("Error al actualizar el producto: " + e.getMessage());
		    }
		    
		    return actualizado;
		}
	public boolean eliminarProducto(int id) {
		Boolean eliminado = null;
		
		try {
			 PreparedStatement psEliminar = DatabaseConnection.getConexion().prepareStatement(
			            " productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?");
		} catch(SQLException e) {
			System.out.println("Error al actualizar el producto: " + e.getMessage());
		}
	}
	public List<ProductoOtaku> buscarProductosPorNombre(String nombre){
		
	}
	public List<ProductoOtaku> buscarProductoPorCategoria(String categoria){
		
	}

}
