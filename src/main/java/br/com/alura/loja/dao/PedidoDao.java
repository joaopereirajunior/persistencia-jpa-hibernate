package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;

public class PedidoDao {

	private EntityManager entityManager;

	public PedidoDao(EntityManager em) {
		this.entityManager = em;

	}

	public void cadastrar(Pedido pedido) {

		this.entityManager.persist(pedido);

	}


}
