package com.framework.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.framework.model.Produto;

public class ProdutoDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("produtoJSF"); //colocar msm nome 
	private EntityManager em = factory.createEntityManager();									// do persistence
	private EntityTransaction transaction = em.getTransaction();

	
	public List listarProdutos() {

		Query queryObj = em.createQuery("SELECT u FROM Produto u");
		List produtoList = queryObj.getResultList();
		if (produtoList != null && produtoList.size() > 0) {
			return produtoList;
		} else {
			return null;
		}
	}
	
	public boolean inserirProduto(Produto produto) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.persist(produto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// essa exception não é necessariamente um erro
			e.printStackTrace(); 
			return false;
		}
	}

	public boolean deletarProduto(Produto produto) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(produto);
			em.remove(produto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void alterarProduto(Produto produto) {
		transaction.begin();
		em.persist(produto);
		transaction.commit();
	}

	
	
	public Produto getProduto(int id) { 
		try {
			return em.find(Produto.class, id); 
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
