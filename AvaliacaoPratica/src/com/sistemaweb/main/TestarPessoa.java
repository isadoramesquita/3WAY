package com.sistemaweb.main;

import com.sistemaweb.dao.PessoaDAO;
import com.sistemaweb.model.Pessoa;

public class TestarPessoa {

	public static void main(String[] args) {

		PessoaDAO DB = new PessoaDAO();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Teste");
		pessoa.setTelefone(62999632520L);
		pessoa.setEmail("teste@teste.com");
		pessoa.setEmpresa("Oobj");
		pessoa.setSenha("senha");
		
		pessoa.setNome("Teste2");
		pessoa.setTelefone(6299968766L);
		pessoa.setEmail("teste2@teste.com");
		pessoa.setEmpresa("None");
		pessoa.setSenha("12345");
		
		
		// ver como colocar pessoa.setUltimoAcesso(new Date());

		DB.inserirPessoa(pessoa);
	}

}
