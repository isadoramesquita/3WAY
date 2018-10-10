package projetoJSF;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "pessoa")
public class Pessoa {

	private int idade;
	private String sexo;
	private String nomeCompleto;

	List<Pessoa> pessoas = new ArrayList<Pessoa>();

	@PostConstruct
	public void init() {

		idade = 25;
		sexo = "Feminino";
		nomeCompleto = "Isadora Mesquita";

	}

	public String getMessage() {
		return "Cadastros";

	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
