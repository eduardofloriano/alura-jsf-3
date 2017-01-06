package br.com.alura.livraria.model.carga;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.LivroDAO;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.util.JpaUtil;

public class LivroCarga {

	public static void main(String[] args) {

		EntityManager em = new JpaUtil().getEntityManager();
		LivroDAO dao = new LivroDAO(em);

		dao.persist(createLivro("Les", "85-359-0277-1", 99.90, "04/11/1994"));
		dao.persist(createLivro("Hirvar", "85-359-0277-2", 99.90, "25/08/1987"));
		dao.persist(createLivro("Imgordehtoror", "85-359-0277-3", 199.90,
				"11/11/1996"));
		dao.persist(createLivro("Fintari", "85-359-0277-4", 49.90, "18/07/1978"));
		dao.persist(createLivro("Raselta", "85-359-0277-5", 99.90, "01/03/1956"));
		dao.persist(createLivro("Sata", "85-359-0277-6", 69.90, "15/12/2011"));

		em.close();
		System.exit(0);

	}

	private static Livro createLivro(String titulo, String isbn, Double preco,
			String dataLancamento) {
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setIsbn(isbn);
		livro.setPreco(preco);
		livro.setDataLancamento(parseDataDate(dataLancamento));
		return livro;
	}

	private static Calendar parseDataCalendar(String data) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			cal.setTime(sdf.parse(data));
		} catch (Exception e) {
			System.out.println("Erro ao Converter a Data!");
		}
		return cal;
	}
	
	private static Date parseDataDate(String data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(data);
		} catch (Exception e) {
			System.out.println("Erro ao Converter a Data!");
		}
		return date;
	}
	
}
