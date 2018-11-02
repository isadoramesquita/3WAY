package com.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="cod", nullable=false, unique=true)
	private int cod;
	
	@Column(name="descricao", nullable=false, unique=true)
	private String descricao;
	
	@Column(name="fornecedor", nullable=false, unique=true)
	private String fornecedor;
	
	@Column(name="valor", nullable=false, unique=true)
	private double valor;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
