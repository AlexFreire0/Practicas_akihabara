import com.akihabara.market.dao.DatabaseConnection;
//import com.akihabara.market.model.ProductoOtaku;

public class Main {
	public static void main(String[] args) {
		
		DatabaseConnection ejemplo = new DatabaseConnection();
		ejemplo.getConexion();
		ejemplo.cerrarConexion();
	}
}
