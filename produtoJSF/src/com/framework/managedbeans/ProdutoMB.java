package com.framework.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.framework.db.ProdutoDAO;
import com.framework.model.Produto;


@ManagedBean
@ViewScoped
public class ProdutoMB {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto = new Produto();
	private List<Produto> produtoListDb = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String codProduto = (String) facesContext.getExternalContext().getRequestParameterMap().get("cod");
		if (codProduto != null) {
			this.produto = produtoDAO.getProduto(Integer.parseInt(codProduto));
		}
	}


	public List getProdutoListDb() {
		return produtoDAO.listarProdutos();
	}

	public void exlcluirprodutoDb(Produto produto) {
		produtoDAO.deletarProduto(produto);
	}   
	
	public String incluirProdutoDb(Produto produto) {
		if (!produtoDAO.inserirProduto(produto)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Produto já cadastrado", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
		}
		return "/produtoListagem.xhtml?faces-redirect=true";
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public String paginaEditar(Produto produto) {
		return "/editarProduto.xhtml?faces-redirect=true&id="+produto.getCod();
	}
			
	public String editarprodutoDb(Produto produto) {
		produtoDAO.alterarProduto(produto);
		return "/produtoListagem.xhtml?faces-redirect=true";
	}
	
}
