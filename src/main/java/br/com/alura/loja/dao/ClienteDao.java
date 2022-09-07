package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {

	private EntityManager entityManager;

	public ClienteDao(EntityManager em) {
		this.entityManager = em;

	}

	public void cadastrar(Cliente cliente) {

		this.entityManager.persist(cliente);

	}

}
