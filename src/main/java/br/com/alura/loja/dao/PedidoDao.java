package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

public class PedidoDao {

	private EntityManager entityManager;

	public PedidoDao(EntityManager em) {
		this.entityManager = em;

	}

	public void cadastrar(Pedido pedido) {

		this.entityManager.persist(pedido);

	}

	// CONSULTA COM FUNCOES SUM SQL

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM (p.valorTotal) FROM  Pedido p";
		return this.entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();

	}
	
	//EXEMPLOS DE QUERY DE CONSULTA PARA RELATORIO
	
	public List<Object []> relatorioDeVendas(){
		
		String jpql = "SELECT produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return this.entityManager.createQuery(jpql, Object[].class).getResultList();
	}
	
	//CASO NÃO QUEIRA DEVOLVER UM ARRAY DE OBJ DAR UM NEW NA STRING JPQL CONFORME ABAIXO COM ISSO GERA UMA CLASSE APENAS COM OS 
	//ATRIBUTOS QUE DESEJA DEVOLVER
	
	public List<RelatorioVendasVo> relatorioDeVendasDevolvendoUmaClasse(){
		
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return this.entityManager.createQuery(jpql, RelatorioVendasVo.class).getResultList();
		
	}
	
	//EXEMPLO FAZENDO CONSULTA POR MEIO DE NAMEDQUERIE
	
	public List<Pedido> buscarPedidoPorNamedQuerie(){
		
		return entityManager.createNamedQuery("P.pedido", Pedido.class).getResultList();
	}
	
	

}
