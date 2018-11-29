package com.sistemaweb.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sistemaweb.dao.PessoaDAO;
import com.sistemaweb.model.Pessoa;


@ManagedBean(name = "LoginMB")
@ViewScoped

public class LoginMB {


		private PessoaDAO pessoaDAO = new PessoaDAO();
		private Pessoa pessoa = new Pessoa();

		public String enviar() {
			pessoa = pessoaDAO.getPessoa(pessoa.getNome(), pessoa.getSenha());
			FacesContext context = FacesContext.getCurrentInstance();
			if (pessoa == null) {
				pessoa = new Pessoa();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Essa pessoa não foi encontrada!", "Erro no Login!"));
				context.validationFailed();
				 
				return null;
			} else {
				context.getExternalContext().getSessionMap().put("logado", true);
				
				return "/restrito/listaPessoas.xhtml?faces-redirect=true";
			}
		}

		public String logout() {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().remove("logado");
			return "/login/login.xhtml?faces-redirect=true";
		}

		public PessoaDAO getPessoaDAO() {
			return pessoaDAO;
		}

		public void setPessoaDAO(PessoaDAO pessoaDAO) {
			this.pessoaDAO = pessoaDAO;
		}

		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}

		


}
