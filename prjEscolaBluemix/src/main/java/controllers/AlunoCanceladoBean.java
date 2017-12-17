package controllers;

import dao.AlunoCanceladoDao;
import model.AlunoCancelado;

public class AlunoCanceladoBean {
	AlunoCancelado aluno;

	public void adicionar() {
		this.aluno = new AlunoCancelado();
	}

	public String gerarCancelamento() {
		AlunoCanceladoDao alunoCanceladoDao = new AlunoCanceladoDao();
		alunoCanceladoDao.gerarCancelamento(this.aluno);
		return "";
	}
}
