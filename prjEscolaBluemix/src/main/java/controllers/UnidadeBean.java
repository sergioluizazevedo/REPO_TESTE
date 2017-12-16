package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import dao.TurmaDao;
import dao.UnidadeDao;
import model.Turma;
import model.Unidade;

@ManagedBean
@SessionScoped
public class UnidadeBean {
	private Unidade unidade;
	private List<Unidade> unidades;
	private Exception ultimaExcecao;
	private boolean skip;

	public void adicionar() {
		this.unidade = new Unidade();
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirmar";
		} else {
			return event.getNewStep();
		}
	}

	public String erroBuscarTodos(Exception ex) {
		return "/views/aluno/erro.xhtml";
	}

	public String salvar() {
		try {
			// java.util.Date dataUtil = new java.util.Date();
			// this.unidade.setDataCadastro(dataUtil);
			// this.unidade.setStatus("Ativada");
			UnidadeDao unidadeDao = new UnidadeDao();
			unidadeDao.adicionar(this.unidade);

			System.out.println("Id adicionado = " + this.unidade.getId());

			FacesMessage msg = new FacesMessage("A unidade foi cadastrada com sucesso! " + this.unidade.getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// this.unidadeCadastrada = new Unidade();
			// this.unidadeCadastrada = this.unidade;
			return "/views/unidade/adicionado";

		} catch (

		Exception ex) {
			System.out.println("[UnidadeBean.adicionar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/unidade/erro.xhtml";

		}

	}

	public String buscar() {
		UnidadeDao unidadeDao = new UnidadeDao();
		Unidade unidadeCadastrada;
		try {

			if (this.unidade.getNome().trim().equals("")) {
				unidadeCadastrada = unidadeDao.buscar(unidade.getId());

			} else {
				unidadeCadastrada = unidadeDao.buscar(unidade.getNome());
			}

			this.unidade = unidadeCadastrada;

			if (unidadeCadastrada.getStatus().equals("Desativada")) {
				return "/views/unidade/localizadoDesativada";
			} else {
				return "/views/unidade/localizado";
			}

		} catch (Exception ex) {
			System.out.println("[UnidadeBean.buscar] Ocorreu o erro:" + ex.getMessage() + " , no mÃ©todo buscarBean");
			this.ultimaExcecao = ex;
			return "/views/unidade/erro";
		}
	}

	public String modificar() {
		try {

			UnidadeDao unidadeDao = new UnidadeDao();
			unidadeDao.modificar(this.unidade);
			System.out.println("Nome sendo modificado = " + this.unidade.getNome());
			return "/views/unidade/modificado.xhtml";

		} catch (Exception ex) {
			System.out.println("[UnidadeBean.modificando] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/unidade/erro.xhtml";
		}
	}

	public void buscarTodos() {
		unidades = new ArrayList<>();
		try {
			System.out.println("Iniciou metodo buscarTodos()");

			UnidadeDao unidadeDao = new UnidadeDao();
			this.unidades = unidadeDao.buscarTodos();
			System.out.println("Retornou " + unidades.size() + " unidades...");

		} catch (Exception ex) {
			System.out.println("[UnidadeBean.buscarTodos] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			this.unidade = null;
			erroBuscarTodos(ex);
		}
	}

	public String desativar() {
		UnidadeDao unidadeDao = new UnidadeDao();
		try {
			unidadeDao.desativar(this.unidade);
			System.out.println("Id sendo desativado = " + this.unidade.getId());
			return "/views/unidade/desativada.xhtml";
		} catch (Exception ex) {
			System.out.println("[UnidadeBean.desativando] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			return "/views/unidade/erro.xhtml";
		}
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public Exception getUltimaExcecao() {
		return ultimaExcecao;
	}

	public void setUltimaExcecao(Exception ultimaExcecao) {
		this.ultimaExcecao = ultimaExcecao;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	/**
	 * public String desativar() { TurmaDao turmaDao = new TurmaDao(); List<Turma>
	 * turmas = new ArrayList<>(); try { turmas =
	 * turmaDao.buscarTurmasPorIdUnidade(this.unidade.getId()); for (Turma turma :
	 * turmas) { if (turma.getStatus().equals("Ativada")) {
	 * System.err.println("Turma Ativa, não é possível desativar essa unidade.");
	 * 
	 * throw new SQLException(); } }
	 * 
	 * } catch (Exception ex) { System.out.println("[UnidadeBean.desativando]
	 * Ocorreu o erro: " + ex.getMessage()); this.ultimaExcecao = ex; return
	 * "/views/unidade/erro.xhtml"; }
	 * 
	 * try { // this.unidade.setStatus("Desativado"); UnidadeDao unidadeDao = new
	 * UnidadeDao(); unidadeDao.desativar(this.unidade); System.out.println("Id
	 * sendo desativado = " + this.unidade.getId()); return
	 * "/views/unidade/desativada.xhtml"; } catch (Exception ex) {
	 * System.out.println("[UnidadeBean.desativando] Ocorreu o erro: " +
	 * ex.getMessage()); this.ultimaExcecao = ex; return
	 * "/views/unidade/erro.xhtml"; }
	 * 
	 * }
	 * 
	 */

}
