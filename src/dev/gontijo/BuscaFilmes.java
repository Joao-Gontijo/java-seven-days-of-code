package dev.gontijo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.gontijo.consts.Constantes;
import dev.gontijo.model.Filme;

public class BuscaFilmes {
	public static void main(String[] args) throws IOException, InterruptedException {
		Constantes apiKey = new Constantes();
		
		List<Filme> listaFilmes = new ArrayList<Filme>();
		
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey.getApiKey());
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		String filmes = response.body();
		
		JSONObject obj = new JSONObject(filmes);
		JSONArray arr = obj.getJSONArray("items");
		
		for(int i = 0; i < arr.length(); i++) {
			Filme filme = new Filme(
					arr.getJSONObject(i).getString("title"),
					arr.getJSONObject(i).getString("image"),
					Double.parseDouble(arr.getJSONObject(i).getString("imDbRating")),
					Integer.parseInt(arr.getJSONObject(i).getString("year"))
				);
			listaFilmes.add(filme);
		}
		
		File f = new File("html//source.html");
		PrintWriter writer = new PrintWriter(f);
		
		HTMLGenerator geradorHtml = new HTMLGenerator();
		geradorHtml.generate(listaFilmes, writer);
		writer.close();
		
	}
}
