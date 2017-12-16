package model;

import java.util.Date;

public class Matricula {

	private Integer id;
	private Integer idAluno;
	private Date dataMatricula;
	private Date dataCancelamento;
	private Double taxaMatricula;
	private Double valorCurso;
	private Double descontoCurso;
	private Integer numParcCurso;
	private Double valorMaterial;
	private Integer numParcMaterial;
	private Integer diaVencimento;
	private String formaDePagamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Double getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(Double taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public Double getValorCurso() {
		return valorCurso;
	}

	public void setValorCurso(Double valorCurso) {
		this.valorCurso = valorCurso;
	}

	public Double getDescontoCurso() {
		return descontoCurso;
	}

	public void setDescontoCurso(Double descontoCurso) {
		this.descontoCurso = descontoCurso;
	}

	public Integer getNumParcCurso() {
		return numParcCurso;
	}

	public void setNumParcCurso(Integer numParcCurso) {
		this.numParcCurso = numParcCurso;
	}

	public Double getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(Double valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public Integer getNumParcMaterial() {
		return numParcMaterial;
	}

	public void setNumParcMaterial(Integer numParcMaterial) {
		this.numParcMaterial = numParcMaterial;
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

}
