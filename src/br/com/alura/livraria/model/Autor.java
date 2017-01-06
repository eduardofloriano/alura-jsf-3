package br.com.alura.livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQuery(name = "obterTodosAutores", query = "select a from Autor a")
@Entity
@SequenceGenerator(name = "SEQ_AUTOR", sequenceName = "SEQ_AUTOR", initialValue = 1, allocationSize = 1)
public class Autor {

	public static final String OBTER_AUTORES_POR_LIVRO = "obterAutoresPorLivro";
	public static final String OBTER_TODOS_AUTORES = "obterTodosAutores";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTOR")
	private Integer id;
	private String nome;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
