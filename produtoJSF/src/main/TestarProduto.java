package main;

import com.framework.db.ProdutoDAO;
import com.framework.model.Produto;

public class TestarProduto {

	public static void main(String[] args) {

		ProdutoDAO dao = new ProdutoDAO();

		Produto produto = new Produto();
		
		//produto.setCod(2);
		produto.setDescricao("Camiseta");
		produto.setFornecedor("Adidas");
		produto.setValor(90.90);
		
		dao.inserirProduto(produto);
	}

}
