package com.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.DAO.UsuarioDAO;
import com.web.model.Usuario;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private static final String INSERIR_OU_EDITAR = "/Usuario.jsp";
	private static final String LISTAR_USUARIOS = "/ListaUsuarios.jsp";

	private UsuarioDAO dao = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("deletar")) {
			int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
			dao.delete(usuarioId);
			forward = LISTAR_USUARIOS;
			request.setAttribute("usuarios", dao.listartodos());
		} else if (action.equalsIgnoreCase("editar")) {
			forward = INSERIR_OU_EDITAR;
			int codigoUsuario = Integer.parseInt(request.getParameter("usuarioId"));
			Usuario usuario = dao.consultar(codigoUsuario);
			request.setAttribute("usuario", usuario);
		} else if (action.equalsIgnoreCase("listarUsuarios")) {
			forward = LISTAR_USUARIOS;
			request.setAttribute("usuarios", dao.listartodos());
		} else {
			forward = INSERIR_OU_EDITAR;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setId(!request.getParameter("UsuarioCodigo").equals("") ? Integer.parseInt(request.getParameter("UsuarioCodigo")) : 0);
		usuario.setLogin(request.getParameter("login"));
		usuario.setMatricula(request.getParameter("matricula")!= null ? Integer.parseInt(request.getParameter("matricula")) : 0);
		usuario.setNome(request.getParameter("nome"));
		String codigoUsuario = request.getParameter("livroCodigo");
		String buscarUsuario = request.getParameter("buscarLivro");

		List<Usuario> usuarios = new ArrayList<>();

		if ((codigoUsuario == null || codigoUsuario.isEmpty()) && buscarUsuario == null) {
			dao.save(usuario);
			usuarios = dao.listartodos();
		} else if (buscarUsuario != null) {
			usuarios = dao.consultar(buscarUsuario);
		} else {
			usuario.setId(Integer.parseInt(codigoUsuario));
			dao.update(usuario);
			usuarios = dao.listartodos();
		}
		request.setAttribute("usuarios", usuarios);
		RequestDispatcher view = request.getRequestDispatcher(LISTAR_USUARIOS);
		view.forward(request, response);

	}

}
