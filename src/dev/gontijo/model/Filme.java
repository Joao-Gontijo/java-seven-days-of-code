package dev.gontijo.model;

public class Filme {
	
	private String titulo;
	private String urlImagem;
	private double nota;
	private int ano;
	
	public Filme(String titulo, String urlImagem, double nota, int ano) {
		this.titulo = titulo;
		this.urlImagem = urlImagem;
		this.nota = nota;
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public double getNota() {
		return nota;
	}

	public int getAno() {
		return ano;
	}

}
