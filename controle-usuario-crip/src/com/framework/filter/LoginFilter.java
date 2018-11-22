package com.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession sess = ((HttpServletRequest) request).getSession(true);
		// recuperar o atributo logado na sess�o
		boolean logado = false;
		if (sess.getAttribute("logado") != null) {
			logado = (Boolean) sess.getAttribute("logado");
		}

		// caso a v�riavel logado seja false saberemos que
		// o usu�rio n�o est� logado
		if (!logado) {
			String contextPath = ((HttpServletRequest) request).getContextPath();

			// Redireciona o usu�rio imediatamente
			// para a p�gina de login.xhtml
			((HttpServletResponse) response).sendRedirect(contextPath + "/login/login.xhtml");
		} else {
			// caso ele esteja logado, apenas deixamos que o fluxo continue
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
