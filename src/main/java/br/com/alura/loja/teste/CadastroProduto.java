package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		cadastrarProduto();

		EntityManager em = JPAUtil.getEntityManager();
//
//		ProdutoDao dao = new ProdutoDao(em);
//		CategoriaDao categoriaDao = new CategoriaDao(em);

//		Categoria smartWatch = new Categoria();
//		categoriaDao.cadastrar(smartWatch);
//		
//		
//		Categoria categoriaResult = categoriaDao.buscarPorId(new BigDecimal(1));
//		
//		System.out.println(categoriaResult.getCategoria());

//		em.getTransaction().begin();
//		
//		Produto produto = dao.buscarPorId(1l);
//		
//		System.out.println(produto.getNome());
//		

	}

	private static void cadastrarProduto() {
		try {

			Categoria celulares = new Categoria("celulares");

			Produto celular = new Produto("xiaomi", "celular muito bom", new BigDecimal(800), celulares);

			EntityManager em = JPAUtil.getEntityManager();

			ProdutoDao dao = new ProdutoDao(em);
			CategoriaDao categoriaDao = new CategoriaDao(em);

			// inicia a transação ao JPA
			em.getTransaction().begin();

			categoriaDao.cadastrar(celulares);
			dao.cadastrar(celular);

			em.flush();
			em.clear();

			em.merge(celulares);
			em.merge(celular);
			em.flush();

			celulares.setCategoria("123");
			em.flush();
			categoriaDao.atualizar(celular);
			em.flush();

			System.out.println("###INSERIDO REGISTRO COM SUCESSO");

			Produto produto = dao.buscarPorId(1l);
			System.out.println(produto.getNome());

			System.out.println("##############COMO BUSCAR TODOS OS REGISTROS DO BANCO###########");
			List<Produto> produtoLst = dao.listarTodosProdutos();
			produtoLst
					.forEach(p -> System.out.println("ID: " + p.getId() + " NOME: " + p.getNome() + p.getCategoria()));

			// ##############BUSCAR TODAS CATEGORIAS##############
			
			List<Categoria> categoriaLst = categoriaDao.buscarTodasCategorias();
			
			categoriaLst.forEach(c -> System.out.println("categoria : ------->" + c.getCategoria()));

			System.out.println("##############BUSCA POR JOIN###########");
			List<Produto> produtoLst2 = dao.listarTodosProdutosPorCategoria("celulares");

			produtoLst.forEach(p -> System.out.println("ID: " + p.getId() + " NOME: " + p.getNome()));
			
			//#########RETORNANDO APENAS ATRIBUTOS SELECIONADO#######33
			
			BigDecimal precoResult = dao.listarTodosProdutoEspecificoRetornoPorPreco("celulares");
			
			System.out.println("Preço do Produto: " + precoResult);

			em.close();

		} catch (Exception e) {
			System.out.println("NAO PASSOU A CONEXAO#########" + e);
			e.printStackTrace();
		}
	}

}
