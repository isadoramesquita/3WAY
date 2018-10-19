package com.framework.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.framework.model.Pessoa;

public class PessoaDao {

	EntityManager entityManager;
	
	// Nossa classe PessoaDao segure o padr�o de projeto
	//Singleton que grante que apenas uma inst�ncia dessa
	// classe ser� criada durante toda a aplica��o
	private static PessoaDao instance;
	
	public static PessoaDao getInstance() {
		if (instance == null) {
			instance = new PessoaDao();
		}
		return instance;
	}
	
	private PessoaDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hellowordJPA");
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	public void persist(Pessoa Pessoa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(Pessoa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}
