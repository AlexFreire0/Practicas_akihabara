package com.akihabara.market.dao;
import com.akihabara.market.model.ProductoOtaku;
import java.sql.*;
import java.util.*;

public class ProductoDAO {

	private DatabaseConnection conexion = new DatabaseConnection();
	
	public void agregarProducto(ProductoOtaku producto) {
	try {
		//Creamos el statement que sea el insert
		PreparedStatement psInsertar = conexion.getConexion().prepareStatement(
	            "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?,?,?,?)");
		//los parametros los metemos en cada interrogación.
	psInsertar.setNString(1, producto.getNombre());
	psInsertar.setString(2, producto.getCategoria());
	psInsertar.setDouble(3, producto.getPrecio());	
	psInsertar.setInt(4, producto.getStock());
	psInsertar.executeUpdate();
	System.out.println("Producto añadido correctamente");
	} catch(SQLException e) { //Recogemos las excepciones
		System.out.println("Ha ocurrido un error al intentar insertar el producto: " + e.getMessage());
	}
	
	}
	public ProductoOtaku obtenerProductoPorId(int id) {
		
		ProductoOtaku producto = null;
		
	    try {
	    	//Preparamos el statement de buscar por id y que devuelva el producto como objeto
	        PreparedStatement psBuscar = conexion.getConexion().prepareStatement(
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
		        PreparedStatement psBuscarTodos = conexion.getConexion().prepareStatement(
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
		Boolean actualizado = false;
		
		 try {
		        // Preparamos el statement UPDATE, actualizando nombre, categoria, precio y stock donde id sea el del producto
		        PreparedStatement psActualizar = conexion.getConexion().prepareStatement(
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
		        System.out.println("Producto actualizado correctamente");
		    } catch(SQLException e) {
		        System.out.println("Error al actualizar el producto: " + e.getMessage());
		    }
		    
		    return actualizado;
		}
	public boolean eliminarProducto(int id) {
		Boolean eliminado = false;
		
		try {
			//hacemos el statement
			 PreparedStatement psEliminar = conexion.getConexion().prepareStatement(
					 "DELETE FROM productos WHERE id = ?");
			 //metemos el valor del id
		        psEliminar.setInt(1, id);
		        //ejecutamos la sentencia
		        int filasAfectadas = psEliminar.executeUpdate();
		        //comprobamos que se han eliminado
		        if (filasAfectadas > 0) {
		            eliminado = true;
		        }
		        //cerramos el statement
		        psEliminar.close();
		        System.out.println("Producto eliminado correctamente");
		} catch(SQLException e) {
			//miostramos el error o excepcion
			System.out.println("Error al eliminar el producto: " + e.getMessage());
		}
		//devolvemos true o false dependiendo de si se ha conseguido eliminar o no
		return eliminado;
	}
	public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
		//creamos la lista
	    List<ProductoOtaku> productos = new ArrayList<>();

	    try {
	    	//hacemos el prepared statement con el sql
	        PreparedStatement psBuscar = conexion.getConexion().prepareStatement("SELECT * FROM productos WHERE nombre LIKE ?");
	        //metemos los parametros introducidos por la funcion
	        psBuscar.setString(1, "%" + nombre + "%");
	        //ejecutamos la sentencia
	        ResultSet rs = psBuscar.executeQuery();
	        //por cada resultado recogemos la informacion de este, la metemos en un objeto y lo metemos en la lista
	        while (rs.next()) {
	            ProductoOtaku producto = new ProductoOtaku();
	            producto.setId(rs.getInt("id"));
	            producto.setNombre(rs.getString("nombre"));
	            producto.setCategoria(rs.getString("categoria"));
	            producto.setPrecio(rs.getDouble("precio"));
	            producto.setStock(rs.getInt("stock"));
	            productos.add(producto);
	        }
	        //cerramos tanto el resultado como el statement
	        rs.close();
	        psBuscar.close();
	    } catch (SQLException e) {
	    	//mostramos el error o excepcion en caso de error
	        System.out.println("Error al buscar productos por nombre: " + e.getMessage());
	    }
	    return productos;
	}

	public List<ProductoOtaku> buscarProductoPorCategoria(String categoria){
		//creamos la lista
		  List<ProductoOtaku> productos = new ArrayList<>();

		    try {
				  //preparamos la sentencia para despues ejecutarla
		        PreparedStatement psBuscar = conexion.getConexion().prepareStatement("SELECT * FROM productos WHERE categoria = ?");
		        //Ponemos la categoria que queremos buscar en el parametro "?"
		        psBuscar.setString(1, categoria);
		        //ejecutamos
		        ResultSet rs = psBuscar.executeQuery();
		        //metemos en un objeto el resultado
		        while (rs.next()) {
		            ProductoOtaku producto = new ProductoOtaku();
		            producto.setId(rs.getInt("id"));
		            producto.setNombre(rs.getString("nombre"));
		            producto.setCategoria(rs.getString("categoria"));
		            producto.setPrecio(rs.getDouble("precio"));
		            producto.setStock(rs.getInt("stock"));
		            //luego lo añadimos a la lista el objeto
		            productos.add(producto);
		        }
		        //cerramos todo para evitar errores posteriormente
		        rs.close();
		        psBuscar.close();
		    } catch (SQLException e) {
		    	//mensaje en caso de error o excepciones.
		        System.out.println("Error al buscar productos por nombre: " + e.getMessage());
		    }
		    //devolvemos la lista
		    return productos;
	}

}
