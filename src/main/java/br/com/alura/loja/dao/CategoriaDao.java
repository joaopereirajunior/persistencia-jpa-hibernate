package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

public class CategoriaDao {

	private EntityManager entityManager;

	public CategoriaDao(EntityManager em) {
		this.entityManager = em;

	}

	public void cadastrar(Categoria categoria) {

		this.entityManager.persist(categoria);

	}

	public void atualizar(Produto produto) {

		this.entityManager.merge(produto);
	}

	public Categoria buscarPorId(BigDecimal id) {

		return entityManager.find(Categoria.class, id);

	}

	public List<Categoria> buscarTodasCategorias() {
		String jpql = "SELECT c FROM Categoria c";

		return entityManager.createQuery(jpql, Categoria.class).getResultList();

	}

}
