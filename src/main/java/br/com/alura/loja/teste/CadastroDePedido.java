package br.com.alura.loja.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {

		cadastrarProduto();

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1L);

		Cliente cliente = new Cliente("Joao Junior", 123456);
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastrar(cliente);
		
		Pedido pedido = new Pedido(cliente);
		
		
		pedido.adicionarItem(new ItemPedido(2, pedido, produto));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		em.close();

		System.out.println("###INSERIDO REGISTRO COM SUCESSO");
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("celulares");

		Produto celular = new Produto("xiaomi", "celular muito bom", new BigDecimal(800), celulares);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		// inicia a transação ao JPA
		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();

		System.out.println("produto cadastrado com sucesso");
		em.close();
	}

}
