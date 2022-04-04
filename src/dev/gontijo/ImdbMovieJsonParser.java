package dev.gontijo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.gontijo.model.Filme;

public class ImdbMovieJsonParser {
	
	private String json;
	
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}
	
	public List<Filme> parse() {
		List<Filme> listaFilmes = new ArrayList<Filme>();
		
		JSONObject obj = new JSONObject(this.json);
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
		return listaFilmes;
	}
}
