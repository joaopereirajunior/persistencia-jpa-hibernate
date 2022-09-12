package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
@NamedQuery(name = "P.pedido", query = "SELECT p FROM Pedido p")

public class Pedido {

	// @Column(name = "id") -> como inserir o nome da coluna caso for diferente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	private LocalDate data = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)//fetch lazy serve para deixar a consulta mais performatica, com isso a jpa não irá carregar joins desnecessario
	private Cliente cliente;
    
	//MAPPEDBy SERVE PARA INDICAR O LADO CONTARIO DO JOIN, SENDO ASSIM ELE NAO IRA CRIAR UMA NOVA TABELA , ENTENDE QUE A TABELA DE PEDIDO JA EXISTE
	// CASO NAO COLOCAR ELE IRÁ GERAR UMA TABELA PEDIDO_ITEM_PEDIDO
	
	//CASCADE SERVE PARA SETAR EM CASCATAS UM VALOR COM BASE NO QUE ESTA SENDO SALVO NO MOMENTO
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {

	}

	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		this.valorTotal = item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()));
		this.itens.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", valorTotal=" + valorTotal + ", data=" + data + ", cliente=" + cliente
				+ ", itens=" + itens + "]";
	}
	
	

}
