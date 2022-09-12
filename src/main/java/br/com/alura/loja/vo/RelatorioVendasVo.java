package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioVendasVo {

	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate ultimaVenda;

	public RelatorioVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate ultimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.ultimaVenda = ultimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Long quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public LocalDate getUltimaVenda() {
		return ultimaVenda;
	}

	public void setUltimaVenda(LocalDate ultimaVenda) {
		this.ultimaVenda = ultimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioVendasVo [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", ultimaVenda=" + ultimaVenda + "]";
	}

}
