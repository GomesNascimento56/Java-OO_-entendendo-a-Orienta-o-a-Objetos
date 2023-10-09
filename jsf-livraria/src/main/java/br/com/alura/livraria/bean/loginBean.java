package br.com.alura.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class loginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String efetuaLogin() {
		
		boolean existe = new UsuarioDAO().existe(this.usuario);
		
		if(existe) {
			System.out.println("Usuario" + this.usuario.getEmail() + "logado com sucesso!");
			
			return "livro?faces-redirect=true";
			
		}
		
		return null;
		
	}
}
