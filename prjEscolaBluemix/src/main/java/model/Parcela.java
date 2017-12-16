package model;

import java.math.BigDecimal;
import java.util.Date;

public class Parcela {
	private Periodo periodo;
	private Aluno aluno;
	private Integer id;
	private Integer qtdTotalDeParcela;
	private Integer numeroDaParcelaCurso;
	private Integer numeroDaParcelaMaterial;
	private Date dataVencimento;
	private BigDecimal valorPago;
	private Date dataPagamento;
	private BigDecimal valorParcelaCurso;
	private BigDecimal valorParcelaMaterial;
	private BigDecimal valorTotalParcelado;
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtdTotalDeParcela() {
		return qtdTotalDeParcela;
	}

	public void setQtdTotalDeParcela(Integer qtdTotalDeParcela) {
		this.qtdTotalDeParcela = qtdTotalDeParcela;
	}

	public Integer getNumeroDaParcelaCurso() {
		return numeroDaParcelaCurso;
	}

	public void setNumeroDaParcelaCurso(Integer numeroDaParcelaCurso) {
		this.numeroDaParcelaCurso = numeroDaParcelaCurso;
	}

	public Integer getNumeroDaParcelaMaterial() {
		return numeroDaParcelaMaterial;
	}

	public void setNumeroDaParcelaMaterial(Integer numeroDaParcelaMaterial) {
		this.numeroDaParcelaMaterial = numeroDaParcelaMaterial;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorParcelaCurso() {
		return valorParcelaCurso;
	}

	public void setValorParcelaCurso(BigDecimal valorParcelaCurso) {
		this.valorParcelaCurso = valorParcelaCurso;
	}

	public BigDecimal getValorParcelaMaterial() {
		return valorParcelaMaterial;
	}

	public void setValorParcelaMaterial(BigDecimal valorParcelaMaterial) {
		this.valorParcelaMaterial = valorParcelaMaterial;
	}

	public BigDecimal getValorTotalParcelado() {
		return valorTotalParcelado;
	}

	public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
		this.valorTotalParcelado = valorTotalParcelado;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	

}
