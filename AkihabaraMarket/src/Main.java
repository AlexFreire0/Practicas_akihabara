import com.akihabara.market.dao.DatabaseConnection;
import com.akihabara.market.model.ProductoOtaku;

public class Main {
	public static void main(String[] args) {
		//Creamos el objeto
		ProductoOtaku figuraDeZerotwo = new ProductoOtaku("Figura de Zero Two", "Figura", 249.99, 2);
		//utilizamos el metodo toString para imprimir la in
		System.out.println(figuraDeZerotwo.toString());
	}
}
