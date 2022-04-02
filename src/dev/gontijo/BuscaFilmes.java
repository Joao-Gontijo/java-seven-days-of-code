package dev.gontijo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.gontijo.consts.Constantes;

public class BuscaFilmes {
	public static void main(String[] args) throws IOException, InterruptedException {
		Constantes apiKey = new Constantes();
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey.getApiKey());
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String filmes = response.body();
		
		JSONObject obj = new JSONObject(filmes);
		JSONArray arr = obj.getJSONArray("items");
		
		String[] titulos = new String[arr.length()];
		String[] links = new String[arr.length()];
		
		for(int i = 0; i < arr.length(); i++) {
			titulos[i] = arr.getJSONObject(i).getString("title");
			links[i] = arr.getJSONObject(i).getString("image");
			System.out.println("Titulo: " + titulos[i]);
			System.out.println("URL: " + links[i]);
		}
	}
}
