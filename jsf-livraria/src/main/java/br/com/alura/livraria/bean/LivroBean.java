package br.com.alura.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	
	private Integer autorId;
	
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros() {
        return new DAO<Livro>(Livro.class).listaTodos();
    }
	/*criando metodo listar para usar no formulario 
	 * select atores  <f:selectItems value="#{livroBean.autores}" var="autor"
		     itemLabel="#{autor.nome}" itemValue="#{autor.id}"/>*/
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.getAutores();
	}
   
	public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());
        
        if(livro.getAutores().isEmpty()) {
        	//throw new RuntimeException("Livro deve ter pelo menos um Autor");
        	
        	FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
        	return;
        }
        
        if(this.livro.getId() == null) {
        	
        	 new DAO<Livro>(Livro.class).adiciona(this.livro); //add livro q ã existe    
        	 
        }else {
        	
        	new DAO<Livro>(Livro.class).atualiza(livro);  //atualiza livro do BTN ALTERAR  
        }  
        
    }
	
	public void remover(Livro livro) {
		// colocar um modal perguntando ao usuario se ele realmente que excluir
		System.out.println("Removendo livro"  + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void carregar(Livro livro) {
		System.out.println("Carregando Livro..." + livro.getTitulo());
		this.livro = livro;
	}
	public String formAutor() {
		System.out.println("Chamando o formulario autor");
		return "autor?faces-redirect=true";
	}
	//FacesContext fc permite receber DADOS do campo recebida no momento
	//UIComponent component componente da view que esta sendo validado
	//Object e é um objeto que representa o valor digitado pelo usuário. 
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			
			throw new ValidatorException(new FacesMessage("ISBN deve começar com número 1"));
		}
	}
	
	 public void gravarAutor() {
     	
     	Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
     	this.livro.adicionaAutor(autor);
     	
     	System.out.println("Livro escrito por :" + autor.getNome());
     }
	
	

}

//O primeiro parâmetro é do tipo javax.faces.context.FacesContext e permite obter informações da view processada no momento.

//O segundo parâmetro é do tipo javax.faces.component.UIComponent e é um referencia ao componente que está sendo validado, normalmente um UIInput.

//O terceiro parâmetro é do tipo java.lang.Object e é um objeto que representa o valor digitado pelo usuário. O objeto já foi convertido.

//Usando o atributo validator e passando uma expression language como parâmetro.