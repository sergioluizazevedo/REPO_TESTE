package model;

import java.math.BigDecimal;
import java.util.Date;

public class AlunoCancelado {
	private Integer id;
	private Turma turma;
	private String matricula;
	private BigDecimal valor;
	private Integer porCentoDesconto;
	private Integer qtdParcelas;
	private BigDecimal valorDescontoReais;
	private BigDecimal valorComDesconto;
	private BigDecimal valorResidualParcela;
	private Integer diaVencimento;
	private String formaDePagamento;
	private String status;
	private Date dataCancelamento;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getPorCentoDesconto() {
		return porCentoDesconto;
	}
	public void setPorCentoDesconto(Integer porCentoDesconto) {
		this.porCentoDesconto = porCentoDesconto;
	}
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public BigDecimal getValorDescontoReais() {
		return valorDescontoReais;
	}
	public void setValorDescontoReais(BigDecimal valorDescontoReais) {
		this.valorDescontoReais = valorDescontoReais;
	}
	public BigDecimal getValorComDesconto() {
		return valorComDesconto;
	}
	public void setValorComDesconto(BigDecimal valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}
	public BigDecimal getValorResidualParcela() {
		return valorResidualParcela;
	}
	public void setValorResidualParcela(BigDecimal valorResidualParcela) {
		this.valorResidualParcela = valorResidualParcela;
	}
	public Integer getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	
}
