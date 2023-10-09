package br.com.alura.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {
	
	private Autor autor = new Autor();
	
	private Integer autorId;
	
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	// método para retornar o autor da url no campo
	public void carregarAutorId() {
		
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}


	public Autor getAutor() {
		return autor;
	}

	/*btn gravar*/
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		/*passando  autor vazio assim que os dados forem gravados*/
		/*limpando campos do input, após preencher o formulário o campo vai ficar vazio*/
		this.autor = new Autor();
		
		return "livro?faces-redirect=true"; /*redirecionamento pag para livro apos cadastro*/
	}
	
	

}
