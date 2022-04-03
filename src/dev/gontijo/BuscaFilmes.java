package dev.gontijo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Iterator;
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
		
		
		String head =
				"""
				<head>
					<meta charset=\"utf-8\">
					<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
					<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
						+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
				</head>
				""";
		String titulo = "<div><h1>Top 250 Filmes</h1></div>";
		
		writer.write(head);
		writer.write(titulo);
		
		
		for (Iterator<Filme> iterator = listaFilmes.iterator(); iterator.hasNext();) {
			Filme filme = (Filme) iterator.next();
			String card = "<div class=\"card\" style=\"width: 14rem;\">\r\n"
					+ "  <img class=\"card-img-top\" src=\""+ filme.getUrlImagem() +"\"\" alt=\"Card image cap\">\r\n"
					+ "  <div class=\"card-body\">\r\n"
					+ "    <h5 class=\"card-title\">" + filme.getTitulo() + "</h5>\r\n"
					+ "    <p class=\"card-text\"> Nota: " + filme.getNota() + "- Ano: " + filme.getAno() + "</p>\r\n"
					+ "  </div>\r\n"
					+ "</div>";
			
			writer.write(card);
		}
		
		writer.close();
	}
}
