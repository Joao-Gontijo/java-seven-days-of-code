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
		Constantes constant = new Constantes();
		String apiKey = constant.getApiKey();
		
		String json = new ImdbApiClient(apiKey).getBody();
		
		List<Filme> listaFilmes = new ImdbMovieJsonParser(json).parse();
		
		File f = new File("html//source.html");
		PrintWriter writer = new PrintWriter(f);
		
		new HTMLGenerator(writer).generate(listaFilmes);
		
		writer.close();
		
		System.out.println("Finalizado");
	}
}
