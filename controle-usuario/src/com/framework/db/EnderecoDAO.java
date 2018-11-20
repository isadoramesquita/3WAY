package com.framework.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.framework.model.Endereco;
import com.framework.model.Usuario;

public class EnderecoDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
	private EntityManager em = factory.createEntityManager();
	private EntityTransaction transaction = em.getTransaction();
	
	
	public boolean inserirEndereco(Endereco endereco) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			Usuario usuario = em.find(Usuario.class, endereco.getUsuario().getId());
			endereco.setUsuario(usuario);
			usuario.setEndereco(endereco);
			em.persist(endereco);
			em.flush();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarEndereco(Endereco endereco) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(endereco);
			em.remove(endereco);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List listarEndereco() {

		Query queryObj = em.createQuery("SELECT u FROM Endereco u");
		List enderecoList = queryObj.getResultList();
		if (enderecoList != null && enderecoList.size() > 0) {
			return enderecoList;
		} else {
			return null;
		}
	}

	public void alterarEndereco(Endereco endereco) {
		transaction.begin();
		em.persist(endereco);
		transaction.commit();
	}

}
