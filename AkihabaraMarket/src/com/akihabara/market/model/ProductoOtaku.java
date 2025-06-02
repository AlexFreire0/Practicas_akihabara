package com.akihabara.market.model;

public class ProductoOtaku {
//Atributos de los produtos:
	private int id;
	private String nombre;
	private String categoria;
	private double precio;
	private int stock;
	//Constructor sin parametros
	public ProductoOtaku() {
	}
	
	//Constructor completo con parametros
	public ProductoOtaku(int id, String nombre, String categoria, double precio, int stock){
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	public int getId() {
		return this.stock;
	}
	
	public String setId(int id) {
		this.id = id;
		return "Id cambiado con exito.";
	}
	//Getters y setters de todos los campos del producto:
	public String getNombre() {
		return this.nombre;
	}
	
	public String setNombre(String nombre) {
		this.nombre = nombre;
		return "Nombre cambiado con exito.";
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public String setCategoria(String categoria) {
		this.categoria = categoria;
		return "Categoria cambiada con exito.";
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public String setPrecio(double precio) {
		this.precio = precio;
		return "Precio cambiado con exito.";
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public String setStock(int stock) {
		this.stock = stock;
		return "Stock cambiado con exito.";
	}
	//Metodo to String para mostrar el objeto con un formato claro:
	public String toString() {
		return "Id: " + this.id + "\nNombre del producto: " + this.nombre + "\nCategoria: " + this.categoria + "\nPrecio: " + this.precio + "\nStock: " + this.stock;
	}
}

