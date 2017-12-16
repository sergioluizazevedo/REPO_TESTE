package model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Recebimento {
	private int id;
	private int alunoId;
	private BigDecimal taxaMatricula;
	private BigDecimal valorCurso;
	private BigDecimal descontoPorCento;
	private int parcelasCurso;
	private BigDecimal valorMaterial;
	private int parcelasMaterial;
	private BigDecimal descontoReais;
	private BigDecimal cursoComDesconto;
	private BigDecimal parcelaCurso;
	private BigDecimal parcelaMaterial;
	private BigDecimal residualParcelaCurso;
	private BigDecimal residualParcelaMaterial;
	private BigDecimal valorParcela;
	private int numeroDaParcela;
	private int numeroDaParcelaCurso;
	private int numeroDaParcelaMaterial;
	private BigDecimal valorTotalParcelado;
	// private Date dataVencimento;
	// private Date dataPagamento;
	Calendar dataVencimento;
	Calendar dataPagamento;
	
	private BigDecimal valorPago;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public BigDecimal getValorCurso() {
		return valorCurso;
	}

	public void setValorCurso(BigDecimal valorCurso) {
		this.valorCurso = valorCurso;
	}

	public BigDecimal getDescontoPorCento() {
		return descontoPorCento;
	}

	public void setDescontoPorCento(BigDecimal descontoPorCento) {
		this.descontoPorCento = descontoPorCento;
	}

	public int getParcelasCurso() {
		return parcelasCurso;
	}

	public void setParcelasCurso(int parcelasCurso) {
		this.parcelasCurso = parcelasCurso;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public int getParcelasMaterial() {
		return parcelasMaterial;
	}

	public void setParcelasMaterial(int parcelasMaterial) {
		this.parcelasMaterial = parcelasMaterial;
	}

	public BigDecimal getDescontoReais() {
		return descontoReais;
	}

	public void setDescontoReais(BigDecimal descontoReais) {
		this.descontoReais = descontoReais;
	}

	public BigDecimal getCursoComDesconto() {
		return cursoComDesconto;
	}

	public void setCursoComDesconto(BigDecimal cursoComDesconto) {
		this.cursoComDesconto = cursoComDesconto;
	}

	public BigDecimal getParcelaCurso() {
		return parcelaCurso;
	}

	public void setParcelaCurso(BigDecimal parcelaCurso) {
		this.parcelaCurso = parcelaCurso;
	}

	public BigDecimal getParcelaMaterial() {
		return parcelaMaterial;
	}

	public void setParcelaMaterial(BigDecimal parcelaMaterial) {
		this.parcelaMaterial = parcelaMaterial;
	}

	public BigDecimal getResidualParcelaCurso() {
		return residualParcelaCurso;
	}

	public void setResidualParcelaCurso(BigDecimal residualParcelaCurso) {
		this.residualParcelaCurso = residualParcelaCurso;
	}

	public BigDecimal getResidualParcelaMaterial() {
		return residualParcelaMaterial;
	}

	public void setResidualParcelaMaterial(BigDecimal residualParcelaMaterial) {
		this.residualParcelaMaterial = residualParcelaMaterial;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public int getNumeroDaParcela() {
		return numeroDaParcela;
	}

	public void setNumeroDaParcela(int numeroDaParcela) {
		this.numeroDaParcela = numeroDaParcela;
	}

	public int getNumeroDaParcelaCurso() {
		return numeroDaParcelaCurso;
	}

	public void setNumeroDaParcelaCurso(int numeroDaParcelaCurso) {
		this.numeroDaParcelaCurso = numeroDaParcelaCurso;
	}

	public int getNumeroDaParcelaMaterial() {
		return numeroDaParcelaMaterial;
	}

	public void setNumeroDaParcelaMaterial(int numeroDaParcelaMaterial) {
		this.numeroDaParcelaMaterial = numeroDaParcelaMaterial;
	}

	public BigDecimal getValorTotalParcelado() {
		return valorTotalParcelado;
	}

	public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
		this.valorTotalParcelado = valorTotalParcelado;
	}
/*
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
*/
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}