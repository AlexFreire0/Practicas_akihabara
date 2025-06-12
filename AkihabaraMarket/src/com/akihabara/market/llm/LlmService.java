package com.akihabara.market.llm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.util.Properties;
import com.google.gson.*;

public class LlmService {

    private String apiKey;

    public LlmService() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            apiKey = prop.getProperty("OPENROUTER_API_KEY");
        } catch (Exception e) {
            System.out.println("Error al cargar la API Key: " + e.getMessage());
        }
    }

    public String sugerirNombreProducto(String tipo, String franquicia) {
    	 String prompt = String.format("Sugiere un nombre llamativo y original para un producto otaku del tipo '%s' basado en la franquicia '%s'. Solo responde con el nombre, sin ninguna otra información, símbolo, comilla, ni descripción extra. Máximo 4 palabras.", tipo, franquicia);

        try {
        	//mandamos el prompt en formato json.
            HttpClient client = HttpClient.newHttpClient();

            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", prompt);

            JsonArray messages = new JsonArray();
            messages.add(message);
            	
            JsonObject body = new JsonObject();
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);
         
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();
            //La respuesta la recogemos mediante el request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String resultado = json	//Devolver la respuesta a la funcion
                    .getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();

            return resultado.trim();

        } catch (Exception e) { //En caso de error
            System.out.println("Error al comunicar con OpenRouter: " + e.getMessage());
            return "Nombre no disponible por error de conexión.";
        }
    }
}
