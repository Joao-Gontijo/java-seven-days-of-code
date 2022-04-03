package dev.gontijo;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import dev.gontijo.model.Filme;

public class HTMLGenerator {
	
	public void generate(List<Filme> lista, PrintWriter writer) {
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
	
		for (Iterator<Filme> iterator = lista.iterator(); iterator.hasNext();) {
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
	}
}
