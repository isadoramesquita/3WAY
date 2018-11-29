package com.sistemaweb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sistemaweb.model.Pessoa;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("AvaliacaoPratica");
	private EntityManager em = factory.createEntityManager();
	private EntityTransaction transaction = em.getTransaction();

	public Pessoa getPessoa(String nomePessoa, String senha) {

		try {
			Pessoa pessoa = (Pessoa) em
					.createQuery("SELECT u from Pessoa u where u.nomePessoa = :name and u.senha = :senha")
					.setParameter("name", nomePessoa).setParameter("senha", senha).getSingleResult();

			return pessoa;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Pessoa getPessoa(int id) {
		try {
			return em.find(Pessoa.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	public void alterarPessoa(Pessoa pessoa) {
		EntityTransaction transaction = em.getTransaction();
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(pessoa);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean inserirPessoa(Pessoa pessoa) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.persist(pessoa);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarPessoa(Pessoa pessoa) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(pessoa);
			em.remove(pessoa);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List listarPessoa() {

		Query queryObj = em.createQuery("SELECT u FROM Pessoa u");
		List pessoasList = queryObj.getResultList();
		if (pessoasList != null && pessoasList.size() > 0) {
			return pessoasList;
		} else {
			return null;
		}

	}

	
}
