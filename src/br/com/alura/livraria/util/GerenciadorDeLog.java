package br.com.alura.livraria.util;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class GerenciadorDeLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object calculaTempoDeExecucao(InvocationContext contexto) throws Exception{
		
		long antes = System.currentTimeMillis();

		Object resultado = contexto.proceed();

		long depois = System.currentTimeMillis();
		System.out.println("Metodo invocado: " + contexto.getMethod().getName());
		System.out.println("Tempo de exeucao: " + (depois - antes));
		
		return resultado;
	}
	
	
}
