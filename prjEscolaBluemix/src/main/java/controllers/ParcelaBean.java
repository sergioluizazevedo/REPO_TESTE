package controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AlunoDao;
import dao.ParcelaDao;
import model.Aluno;
import model.Parcela;
import model.Periodo;

@ManagedBean
@SessionScoped
public class ParcelaBean {

	private Parcela parcela;
	private Parcela parcelaSelecionada;
	private Aluno aluno;
	private Periodo periodo;
	private List<Parcela> parcelas;
	private Exception ultimaExcecao;

	public void adicionar() {
		this.periodo = new Periodo();
		this.aluno = new Aluno();
		this.parcela = new Parcela();
		parcela.setPeriodo(periodo);
		parcela.setAluno(aluno);

	}

	public String buscarContasAreceber() {
		System.out.println("Bateu no m�todo!");
		System.out.println(this.parcela.getPeriodo().getDataInicio().getTime());
		System.out.println(this.parcela.getPeriodo().getDataFinal().getTime());
		try {
			System.out.println("Iniciou metodo buscarContasAreceber()");
			ParcelaDao parcelaDao = new ParcelaDao();
			this.parcelas = parcelaDao.buscarContasAreceber(parcela.getPeriodo());
			System.out.println("Retornou " + parcelas.size() + " parcelas...");

		} catch (Exception ex) {
			System.out.println("[AlunoBean.buscarContasaReceber] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			return "/views/parcela/erro";
		}

		return "/views/parcela/exibirContasAreceber.xhtml";
	}

	public String buscarContasRecebidas() {
		System.out.println("Bateu no m�todo!");
		System.out.println(this.parcela.getPeriodo().getDataInicio().getTime());
		System.out.println(this.parcela.getPeriodo().getDataFinal().getTime());
		try {
			System.out.println("Iniciou metodo buscarContasRecebidas()");
			ParcelaDao parcelaDao = new ParcelaDao();
			this.parcelas = parcelaDao.buscarContasRecebidas(parcela.getPeriodo());
			System.out.println("Retornou " + parcelas.size() + " parcelas...");

		} catch (Exception ex) {
			System.out.println("[AlunoBean.buscarContasRecebidas] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			return "/views/parcela/erro";
		}

		return "/views/parcela/exibirContasRecebidas.xhtml";
	}

	public String buscarContasAreceberDoAluno() {
		System.out.println("Bateu no m�todo!");
		System.out.println(this.parcela.getAluno().getId());
		System.out.println(this.parcela.getAluno().getNome());
		try {
			ParcelaDao parcelaDao = new ParcelaDao();

			if (parcela.getAluno().getNome().trim().equals("")) {
				this.parcelas = parcelaDao.buscarContasAreceberDoAluno(parcela.getAluno().getId());
				System.out.println("Buscar pelo id");
			} else {
				this.parcelas = parcelaDao.buscarContasAreceberDoAluno(parcela.getAluno().getNome());
				System.out.println("Buscar pelo Nome");
			}

			System.out.println("Retornou " + parcelas.size() + " parcelas...");
		} catch (Exception ex) {
			System.out.println("[ParcelaBean.buscarContasAreceberDoAluno] Ocorreu o erro:" + ex.getMessage()
					+ " , no metodo buscarContasAreceberDoAluno()");
			this.ultimaExcecao = ex;
			return "/views/parcela/erro";
		}
		return "/views/parcela/exibirContasAreceberDoAluno.xhtml";
	}

	public String buscarContasRecebidasDoAluno() {
		System.out.println("Bateu no m�todo!");
		System.out.println(this.parcela.getAluno().getId());
		System.out.println(this.parcela.getAluno().getNome());
		try {
			ParcelaDao parcelaDao = new ParcelaDao();

			if (parcela.getAluno().getNome().trim().equals("")) {
				this.parcelas = parcelaDao.buscarContasRecebidasDoAluno(parcela.getAluno().getId());
				System.out.println("Buscar pelo id");
			} else {
				this.parcelas = parcelaDao.buscarContasRecebidasDoAluno(parcela.getAluno().getNome());
				System.out.println("Buscar pelo Nome");
			}

			System.out.println("Retornou " + parcelas.size() + " parcelas...");
		} catch (Exception ex) {
			System.out.println("[ParcelaBean.buscarContasRecebidasDoAluno] Ocorreu o erro:" + ex.getMessage()
					+ " , no metodo buscarContasRecebidasDoAluno()");
			this.ultimaExcecao = ex;
			return "/views/parcela/erro";
		}
		return "/views/parcela/exibirContasRecebidasDoAluno.xhtml";
	}

	public void buscarContasRecebidasHoje() {
		System.out.println("Bateu no m�todo!");
		try {
			System.out.println("Iniciou metodo buscarContasRecebidasHoje()");
			ParcelaDao parcelaDao = new ParcelaDao();
			this.parcelas = parcelaDao.buscarContasRecebidasHoje();
			System.out.println("Retornou " + parcelas.size() + " parcelas...");

		} catch (Exception ex) {
			System.out.println("[AlunoBean.buscarContasRecebidasHoje()] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;
			erroBuscarTodos(ex);

		}

	}

	public String erroBuscarTodos(Exception ex) {
		return "/views/parcela/erro.xhtml";
	}
	
	public String baixarContaAreceber() {
		System.out.println(this.parcelaSelecionada);
		return "/views/parcela/contaRecebida.xhtml";
		/*try {
			Parcela parcelaDao = new ParcelaDao();
			alunoDao.salvar(this.aluno);
			System.out.println("Chamando o m�todo salvar na classe AlunoDao");
			FacesMessage msg = new FacesMessage("O aluno foi cadastrado na turma:" + this.aluno.getTurma().getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("Id adicionado = " + this.aluno.getId());
			this.alunoCadastrado = new Aluno();
			this.alunoCadastrado = this.aluno;
			return "/views/aluno/adicionado";

		} catch (

		Exception ex) {
			System.out.println("[AlunoBean.adicionar] Ocorreu o erro: " + ex.getMessage());
			this.ultimaExcecao = ex;

			

		}
*/
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public Exception getUltimaExcecao() {
		return ultimaExcecao;
	}

	public void setUltimaExcecao(Exception ultimaExcecao) {
		this.ultimaExcecao = ultimaExcecao;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Parcela getParcelaSelecionada() {
		return parcelaSelecionada;
	}

	public void setParcelaSelecionada(Parcela parcelaSelecionada) {
		this.parcelaSelecionada = parcelaSelecionada;
	}
	
	

}
