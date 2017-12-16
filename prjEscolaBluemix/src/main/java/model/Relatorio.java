package model;

import java.math.BigDecimal;
import java.util.Date;

public class Relatorio {
	private Integer id;
	private Integer alunoId;
	private Integer numeroDaParcela;
	private Integer numeroDaParcelaCurso;
	private Integer parcelasCurso;
	private Integer numeroDaParcelaMaterial;
	private Integer parcelasMaterial;
	private Date dataVencimento;
	private BigDecimal valorParcela;
	private BigDecimal valorPago;
	private Date dataPagamento;
	private String formaDePagamento;
	private String matricula;
	private String status;
	private Date dataMatricula;
	private String nome;
	private BigDecimal valorCurso;
	private BigDecimal descontoPorCento;
	private BigDecimal descontoReais;
	private BigDecimal cursoComDesconto;
	private BigDecimal parcelaCurso;
	private BigDecimal residualParcelaCurso;
	private BigDecimal taxaMatricula;
	private BigDecimal valorMaterial;
	private BigDecimal parcelaMaterial;
	private BigDecimal residualParcelaMaterial;
	private BigDecimal valorTotalParcelado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Integer alunoId) {
		this.alunoId = alunoId;
	}

	public Integer getNumeroDaParcela() {
		return numeroDaParcela;
	}

	public void setNumeroDaParcela(Integer numeroDaParcela) {
		this.numeroDaParcela = numeroDaParcela;
	}

	public Integer getNumeroDaParcelaCurso() {
		return numeroDaParcelaCurso;
	}

	public void setNumeroDaParcelaCurso(Integer numeroDaParcelaCurso) {
		this.numeroDaParcelaCurso = numeroDaParcelaCurso;
	}

	public Integer getParcelasCurso() {
		return parcelasCurso;
	}

	public void setParcelasCurso(Integer parcelasCurso) {
		this.parcelasCurso = parcelasCurso;
	}

	public Integer getNumeroDaParcelaMaterial() {
		return numeroDaParcelaMaterial;
	}

	public void setNumeroDaParcelaMaterial(Integer numeroDaParcelaMaterial) {
		this.numeroDaParcelaMaterial = numeroDaParcelaMaterial;
	}

	public Integer getParcelasMaterial() {
		return parcelasMaterial;
	}

	public void setParcelasMaterial(Integer parcelasMaterial) {
		this.parcelasMaterial = parcelasMaterial;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
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

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
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

	public BigDecimal getResidualParcelaCurso() {
		return residualParcelaCurso;
	}

	public void setResidualParcelaCurso(BigDecimal residualParcelaCurso) {
		this.residualParcelaCurso = residualParcelaCurso;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public BigDecimal getParcelaMaterial() {
		return parcelaMaterial;
	}

	public void setParcelaMaterial(BigDecimal parcelaMaterial) {
		this.parcelaMaterial = parcelaMaterial;
	}

	public BigDecimal getResidualParcelaMaterial() {
		return residualParcelaMaterial;
	}

	public void setResidualParcelaMaterial(BigDecimal residualParcelaMaterial) {
		this.residualParcelaMaterial = residualParcelaMaterial;
	}

	public BigDecimal getValorTotalParcelado() {
		return valorTotalParcelado;
	}

	public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
		this.valorTotalParcelado = valorTotalParcelado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
