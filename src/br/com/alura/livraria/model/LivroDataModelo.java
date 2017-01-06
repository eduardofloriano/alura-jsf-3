package br.com.alura.livraria.model;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.livraria.dao.LivroDAO;
import br.com.alura.livraria.util.JpaUtil;

public class LivroDataModelo extends LazyDataModel<Livro>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7720950825668029561L;

	private EntityManager em = new JpaUtil().getEntityManager();
	LivroDAO livroDAO = new LivroDAO(em);
	
	
	
	public LivroDataModelo() {
		super.setRowCount(livroDAO.obterTodosLivros().size());		
	}
	
	
	
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros ){		
		return livroDAO.obterTodosLivrosPaginado(inicio, quantidade);
		
	}
	
	
	
}
