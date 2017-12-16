package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import dao.AlunoDao;
import model.Aluno;
import model.Parcela;
import model.Relatorio;
import model.Turma;
import model.Unidade;

@ManagedBean
@SessionScoped
public class AlunoBean {

	private Aluno aluno; // usado para imprimir os valores no formulÃ¡rio
	private Aluno alunoCadastrado; // usado para capturar os dados do formulÃ¡rio
	private Aluno alunoMatriculado;
	private Turma turma;
	private Unidade unidade;
	private List<Aluno> alunos;
	private boolean skip;
	private Exception ultimaExcecao;

	public void adicionar() {
		this.aluno = new Aluno();
		this.aluno.setTurma(new Turma());

	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirmar";
		} else {
			return event.getNewStep();
		}
	}

	public String salvar() {
		try {
			AlunoDao alunoDao = new AlunoDao();
			alunoDao.salvar(this.aluno);
			System.out.println("Chamando o método salvar na classe AlunoDao");
			FacesMessage msg = new FacesMessage("O aluno foi cadastrado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("Id adicionado = " + this.aluno.getId());
			this.alunoCadastrado = new Aluno();
			this.alunoCadastrado = this.aluno;
			return "/views/aluno/adicionado";

		} catch (

		Exception ex) {
			System.out.println("[AlunoBean.adicionar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/aluno/erro.xhtml";

		}

	}

	public String matricular() {
		try {
			AlunoDao alunoDao = new AlunoDao();
			alunoDao.matricular(this.aluno);

			System.out.println("Chamando o método prepararRecebimento da Classe AlunoDao");

			FacesMessage msg = new FacesMessage("Matricula foi cadastrada:");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.alunoMatriculado = new Aluno();
			this.alunoMatriculado = this.aluno;
			return "/views/aluno/matriculado.xhtml";

		} catch (Exception ex) {
			FacesMessage msg = new FacesMessage("Matricula NÂO foi cadastrada:");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			System.out.println("[Matricula.adicionar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/aluno/erro.xhtml";
		}

	}

	public String buscar() {
		AlunoDao alunoDao = new AlunoDao();
		try {

			if (this.aluno.getNome().trim().equals("")) {
				this.alunoCadastrado = new Aluno();
				this.alunoCadastrado = alunoDao.buscar(aluno.getId());

			} else {
				this.alunoCadastrado = new Aluno();
				this.alunoCadastrado = alunoDao.buscar(aluno.getNome());
			}

			if (this.alunoCadastrado.getStatus().equals("Desativado"))
				return "/views/aluno/localizadoDesativado";
			else
				return "/views/aluno/localizado";

		} catch (Exception ex) {
			System.out.println("[AlunoBean.buscar] Ocorreu o erro:" + ex.getMessage() + " , no metodo buscarBean");
			this.ultimaExcecao = ex;
			return "/views/aluno/erro";
		}
	}

	public String desativar() {
		try {

			AlunoDao alunoDao = new AlunoDao();
			alunoDao.desativar(this.alunoCadastrado.getId());
			System.out.println("Aluno sendo desativado = " + this.alunoCadastrado.getNome());
			this.alunoCadastrado.setStatus("Desativado");
			this.alunoCadastrado.setDataCancelamento(new java.util.Date());
			return "/views/aluno/desativado.xhtml";

		} catch (Exception ex) {
			System.out.println("[AlunoBean.desativar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/aluno/erro.xhtml";
		}
	}

	public void buscarTodos() {
		alunos = new ArrayList<>();
		try {
			System.out.println("Iniciou metodo buscarTodos()");

			AlunoDao alunoDao = new AlunoDao();
			this.alunos = alunoDao.buscarTodos();
			System.out.println("Retornou " + alunos.size() + " alunos...");

		} catch (Exception ex) {
			System.out.println("[AlunoBean.buscarTodos] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			this.alunos = null;
			erroBuscarTodos(ex);
		}
	}

	public String modificar() {
		try {

			AlunoDao alunoDao = new AlunoDao();
			alunoDao.modificar(this.alunoCadastrado);
			System.out.println("Nome sendo modificados = " + this.alunoCadastrado.getNome());
			return "/views/aluno/modificado.xhtml";

		} catch (Exception ex) {
			System.out.println("[AlunoBean.modificando] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			return "/views/aluno/erro.xhtml";
		}
	}

	public String erroBuscarTodos(Exception ex) {
		return "/views/aluno/erro.xhtml";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAlunoCadastrado() {
		return alunoCadastrado;
	}

	public void setAlunoCadastrado(Aluno alunoCadastrado) {
		this.alunoCadastrado = alunoCadastrado;
	}

	public Aluno getAlunoMatriculado() {
		return alunoMatriculado;
	}

	public void setAlunoMatriculado(Aluno alunoMatriculado) {
		this.alunoMatriculado = alunoMatriculado;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Exception getUltimaExcecao() {
		return ultimaExcecao;
	}

	public void setUltimaExcecao(Exception ultimaExcecao) {
		this.ultimaExcecao = ultimaExcecao;
	}

}
