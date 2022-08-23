package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager entityManager;

	public ProdutoDao(EntityManager em) {
		this.entityManager = em;

	}

	public void cadastrar(Produto produto) {

		this.entityManager.persist(produto);

	}

	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);

	}

	public List<Produto> listarTodosProdutos() {

		String jpql = "SELECT p FROM Produto p";

		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}

	// FAZENDO JOIN ///////

	public List<Produto> listarTodosProdutosPorCategoria(String categoria) {

		String jpql = "SELECT p FROM Produto p WHERE p.categoria.categoria = :nome";

		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", categoria).getResultList();
	}

	// FILTRANDO O RETORNO PARA NAO RETORNAR TODOS ATRIBUTOS /////////

	public BigDecimal listarTodosProdutoEspecificoRetornoPorPreco(String categoria) {

		String jpql = "SELECT p.preco FROM Produto p WHERE p.categoria.categoria = :nome";

		return entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", categoria).getSingleResult();
	}

}
