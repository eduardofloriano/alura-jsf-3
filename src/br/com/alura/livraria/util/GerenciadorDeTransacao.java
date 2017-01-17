package br.com.alura.livraria.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executarTransacao(InvocationContext context) throws Exception{
		
		em.getTransaction().begin();
		
		Object resultado = context.proceed();
		
		em.getTransaction().commit();
		
		return resultado;
		
	}

}
