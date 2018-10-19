package com.framework.testes;

import com.framework.dao.PessoaDao;
import com.framework.model.Pessoa;

public class TestaPersistencia {

	static PessoaDao pessoaDao = PessoaDao.getInstance();

	public static void main(String[] args) {

	}

	private static void incluirPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("01757964169");
		pessoa.setNome("Isadora");

		pessoaDao.persist(pessoa);
	}
}