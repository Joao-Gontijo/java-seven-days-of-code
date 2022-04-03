package dev.gontijo;

import java.io.PrintWriter;
import java.util.List;

import dev.gontijo.model.Filme;

public class HTMLGenerator {
	
	public HTMLGenerator(PrintWriter writer) {
		
	}
	
	public void generate(List<Filme> lista) {
		String head =
				"""
				<head>
					<meta charset=\"utf-8\">
					<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
					<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
						+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
				</head>
				""";
		
	}
}
