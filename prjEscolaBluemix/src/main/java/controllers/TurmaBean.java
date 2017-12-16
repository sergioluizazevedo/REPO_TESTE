package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import dao.TurmaDao;
import model.Turma;
import model.Unidade;

@ManagedBean
@SessionScoped
public class TurmaBean {
	private Turma turma;
	private List<Turma> turmas;
	private Unidade unidade;
	private Exception ultimaExcecao;
	private boolean skip;

	public void adicionar() {

		this.unidade = new Unidade();
		this.turma = new Turma();
		this.turma.setUnidade(unidade);
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
			// this.turma.setStatus("Ativada");
			TurmaDao turmaDao = new TurmaDao();
			turmaDao.adicionar(this.turma);
			System.out.println("Id adicionado = " + this.turma.getId());

			FacesMessage msg = new FacesMessage("A turma foi cadastrada com sucesso! " + this.turma.getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// this.turmaCadastrada = new Turma();
			// this.turmaCadastrada = this.turma;
			return "/views/turma/adicionado";

		} catch (

		Exception ex) {
			System.out.println("[TurmaBean.adicionar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/turma/erro.xhtml";

		}

	}

	public String buscar() {
		TurmaDao turmaDao = new TurmaDao();
		Turma turmaCadastrada;
		try {

			if (this.turma.getNome().trim().equals("")) {
				turmaCadastrada = turmaDao.buscar(turma.getId());

			} else {
				turmaCadastrada = turmaDao.buscar(turma.getNome());
			}

			this.turma = turmaCadastrada;

			if (turmaCadastrada.getStatus().equals("Desativada")) {
				return "/views/turma/localizadoDesativada";
			} else {
				return "/views/turma/localizado";
			}

		} catch (Exception ex) {
			System.out.println("[TurmaBean.buscar] Ocorreu o erro:" + ex.getMessage() + " , no método buscarBean");
			this.ultimaExcecao = ex;
			return "/views/turma/erro";
		}
	}

	public String modificar() {
		try {
			TurmaDao turmaDao = new TurmaDao();
			turmaDao.modificar(this.turma);
			System.out.println("Nome sendo modificado = " + this.turma.getNome());
			return "/views/turma/modificado.xhtml";

		} catch (Exception ex) {
			System.out.println("[TurmaBean.modificando] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/turma/erro.xhtml";
		}
	}

	public void buscarTodos() {
		turmas = new ArrayList<>();
		try {
			System.out.println("Iniciou metodo buscarTodos()");

			TurmaDao turmaDao = new TurmaDao();
			this.turmas = turmaDao.buscarTodos();
			System.out.println("Retornou " + turmas.size() + " turmas...");

		} catch (Exception ex) {
			System.out.println("[TurmaBean.buscarTodos] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			this.unidade = null;
			erroBuscarTodos(ex);
		}
	}

	public String desativar() {
		TurmaDao turmaDao = new TurmaDao();
		try {
			turmaDao.desativar(this.turma);
			System.out.println("Id sendo desativado = " + this.turma.getId());
			return "/views/turma/desativada.xhtml";
		} catch (Exception ex) {
			System.out.println("[TurmaBean.desativando] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			return "/views/turma/erro.xhtml";
		}
	}

	/**
	 * 
	 * 
	 * 
	 * public String desativar() { try { // this.turma.setStatus("Desativado");
	 * TurmaDao turmaDao = new TurmaDao(); turmaDao.desativar(this.turma);
	 * System.out.println("Id sendo desativado = " + this.turma.getId());
	 * 
	 * return "/views/turma/desativada.xhtml"; } catch (Exception ex) {
	 * System.out.println("[TurmaBean.desativando] Ocorreu o erro: " +
	 * ex.getMessage()); this.ultimaExcecao = ex; return "/views/turma/erro.xhtml";
	 * }
	 * 
	 * }
	 */
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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

}
