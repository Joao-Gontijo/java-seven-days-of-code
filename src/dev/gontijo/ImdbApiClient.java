package dev.gontijo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiClient {

	private String apiKey;
	private URI uri;
	
	public ImdbApiClient(String apiKey) {
		this.apiKey = apiKey;
		this.uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + this.apiKey);
	}

	public String getBody() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(this.uri).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.body();
	}
}
