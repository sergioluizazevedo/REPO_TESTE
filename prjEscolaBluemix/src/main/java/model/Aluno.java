package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Turma turma;
	private String matricula;
	private String nome;
	private String cpf;
	private String rg;
	private String orgaoExp;
	private String ufRg;
	private String sexo;
	private Date dataNascimento;
	private String email;
	private String celular;
	private String telefone;
	private String pai;
	private String mae;
	private String cep;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;

	private BigDecimal valorTaxaMatricula;
	private BigDecimal valorCurso;
	private Integer porCentoDesconto;
	private Integer qtdParcelasCurso;
	private BigDecimal valorMaterial;
	private Integer qtdParcelasMaterial;
	private BigDecimal valorDescontoReais;
	private BigDecimal valorCursoComDesconto;
	private BigDecimal valorResidualParcelaCurso;
	private BigDecimal valorResidualParcelaMaterial;
	private Date dataMatricula;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExp() {
		return orgaoExp;
	}

	public void setOrgaoExp(String orgaoExp) {
		this.orgaoExp = orgaoExp;
	}

	public String getUfRg() {
		return ufRg;
	}

	public void setUfRg(String ufRg) {
		this.ufRg = ufRg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValorTaxaMatricula() {
		return valorTaxaMatricula;
	}

	public void setValorTaxaMatricula(BigDecimal valorTaxaMatricula) {
		this.valorTaxaMatricula = valorTaxaMatricula;
	}

	public BigDecimal getValorCurso() {
		return valorCurso;
	}

	public void setValorCurso(BigDecimal valorCurso) {
		this.valorCurso = valorCurso;
	}

	public Integer getPorCentoDesconto() {
		return porCentoDesconto;
	}

	public void setPorCentoDesconto(Integer porCentoDesconto) {
		this.porCentoDesconto = porCentoDesconto;
	}

	public Integer getQtdParcelasCurso() {
		return qtdParcelasCurso;
	}

	public void setQtdParcelasCurso(Integer qtdParcelasCurso) {
		this.qtdParcelasCurso = qtdParcelasCurso;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public Integer getQtdParcelasMaterial() {
		return qtdParcelasMaterial;
	}

	public void setQtdParcelasMaterial(Integer qtdParcelasMaterial) {
		this.qtdParcelasMaterial = qtdParcelasMaterial;
	}

	public BigDecimal getValorDescontoReais() {
		return valorDescontoReais;
	}

	public void setValorDescontoReais(BigDecimal valorDescontoReais) {
		this.valorDescontoReais = valorDescontoReais;
	}

	public BigDecimal getValorCursoComDesconto() {
		return valorCursoComDesconto;
	}

	public void setValorCursoComDesconto(BigDecimal valorCursoComDesconto) {
		this.valorCursoComDesconto = valorCursoComDesconto;
	}

	public BigDecimal getValorResidualParcelaCurso() {
		return valorResidualParcelaCurso;
	}

	public void setValorResidualParcelaCurso(BigDecimal valorResidualParcelaCurso) {
		this.valorResidualParcelaCurso = valorResidualParcelaCurso;
	}

	public BigDecimal getValorResidualParcelaMaterial() {
		return valorResidualParcelaMaterial;
	}

	public void setValorResidualParcelaMaterial(BigDecimal valorResidualParcelaMaterial) {
		this.valorResidualParcelaMaterial = valorResidualParcelaMaterial;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * 
	 * private BigDecimal valorParcela; // Na primeira parcela =
	 * parcelaCurso+taxaMatricula Na segunda = // parcelaCurso+parcelaMaterial
	 * private Integer numeroDaParcela; private Integer numeroDaParcelaCurso;
	 * private Integer numeroDaParcelaMaterial; private BigDecimal
	 * valorTotalParcelado;
	 * 
	 * private Date dataVencimento; private Date dataPagamento;
	 * 
	 * private BigDecimal valorPago;
	 * 
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 * 
	 * public Turma getTurma() { return turma; }
	 * 
	 * public void setTurma(Turma turma) { this.turma = turma; }
	 * 
	 * public String getMatricula() { return matricula; }
	 * 
	 * public void setMatricula(String matricula) { this.matricula = matricula; }
	 * 
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 * 
	 * public String getCpf() { return cpf; }
	 * 
	 * public void setCpf(String cpf) { this.cpf = cpf; }
	 * 
	 * public String getRg() { return rg; }
	 * 
	 * public void setRg(String rg) { this.rg = rg; }
	 * 
	 * public String getOrgaoExp() { return orgaoExp; }
	 * 
	 * public void setOrgaoExp(String orgaoExp) { this.orgaoExp = orgaoExp; }
	 * 
	 * public String getUfRg() { return ufRg; }
	 * 
	 * public void setUfRg(String ufRg) { this.ufRg = ufRg; }
	 * 
	 * public String getSexo() { return sexo; }
	 * 
	 * public void setSexo(String sexo) { this.sexo = sexo; }
	 * 
	 * public Date getDataNascimento() { return dataNascimento; }
	 * 
	 * public void setDataNascimento(Date dataNascimento) { this.dataNascimento =
	 * dataNascimento; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getCelular() { return celular; }
	 * 
	 * public void setCelular(String celular) { this.celular = celular; }
	 * 
	 * public String getTelefone() { return telefone; }
	 * 
	 * public void setTelefone(String telefone) { this.telefone = telefone; }
	 * 
	 * public String getPai() { return pai; }
	 * 
	 * public void setPai(String pai) { this.pai = pai; }
	 * 
	 * public String getMae() { return mae; }
	 * 
	 * public void setMae(String mae) { this.mae = mae; }
	 * 
	 * public String getCep() { return cep; }
	 * 
	 * public void setCep(String cep) { this.cep = cep; }
	 * 
	 * public String getEndereco() { return endereco; }
	 * 
	 * public void setEndereco(String endereco) { this.endereco = endereco; }
	 * 
	 * public String getBairro() { return bairro; }
	 * 
	 * public void setBairro(String bairro) { this.bairro = bairro; }
	 * 
	 * public String getCidade() { return cidade; }
	 * 
	 * public void setCidade(String cidade) { this.cidade = cidade; }
	 * 
	 * public String getEstado() { return estado; }
	 * 
	 * public void setEstado(String estado) { this.estado = estado; }
	 * 
	 * public BigDecimal getTaxaMatricula() { return taxaMatricula; }
	 * 
	 * public void setTaxaMatricula(BigDecimal taxaMatricula) { this.taxaMatricula =
	 * taxaMatricula; }
	 * 
	 * public BigDecimal getValorCurso() { return valorCurso; }
	 * 
	 * public void setValorCurso(BigDecimal valorCurso) { this.valorCurso =
	 * valorCurso; }
	 * 
	 * public BigDecimal getDescontoPorCento() { return descontoPorCento; }
	 * 
	 * public void setDescontoPorCento(BigDecimal descontoPorCento) {
	 * this.descontoPorCento = descontoPorCento; }
	 * 
	 * public Integer getParcelasCurso() { return parcelasCurso; }
	 * 
	 * public void setParcelasCurso(Integer parcelasCurso) { this.parcelasCurso =
	 * parcelasCurso; }
	 * 
	 * public BigDecimal getValorMaterial() { return valorMaterial; }
	 * 
	 * public void setValorMaterial(BigDecimal valorMaterial) { this.valorMaterial =
	 * valorMaterial; }
	 * 
	 * public Integer getParcelasMaterial() { return parcelasMaterial; }
	 * 
	 * public void setParcelasMaterial(Integer parcelasMaterial) {
	 * this.parcelasMaterial = parcelasMaterial; }
	 * 
	 * public BigDecimal getDescontoReais() { return descontoReais; }
	 * 
	 * public void setDescontoReais(BigDecimal descontoReais) { this.descontoReais =
	 * descontoReais; }
	 * 
	 * public BigDecimal getCursoComDesconto() { return cursoComDesconto; }
	 * 
	 * public void setCursoComDesconto(BigDecimal cursoComDesconto) {
	 * this.cursoComDesconto = cursoComDesconto; }
	 * 
	 * public BigDecimal getParcelaCurso() { return parcelaCurso; }
	 * 
	 * public void setParcelaCurso(BigDecimal parcelaCurso) { this.parcelaCurso =
	 * parcelaCurso; }
	 * 
	 * public BigDecimal getParcelaMaterial() { return parcelaMaterial; }
	 * 
	 * public void setParcelaMaterial(BigDecimal parcelaMaterial) {
	 * this.parcelaMaterial = parcelaMaterial; }
	 * 
	 * public BigDecimal getResidualParcelaCurso() { return residualParcelaCurso; }
	 * 
	 * public void setResidualParcelaCurso(BigDecimal residualParcelaCurso) {
	 * this.residualParcelaCurso = residualParcelaCurso; }
	 * 
	 * public BigDecimal getResidualParcelaMaterial() { return
	 * residualParcelaMaterial; }
	 * 
	 * public void setResidualParcelaMaterial(BigDecimal residualParcelaMaterial) {
	 * this.residualParcelaMaterial = residualParcelaMaterial; }
	 * 
	 * public BigDecimal getValorParcela() { return valorParcela; }
	 * 
	 * public void setValorParcela(BigDecimal valorParcela) { this.valorParcela =
	 * valorParcela; }
	 * 
	 * public Integer getNumeroDaParcela() { return numeroDaParcela; }
	 * 
	 * public void setNumeroDaParcela(Integer numeroDaParcela) {
	 * this.numeroDaParcela = numeroDaParcela; }
	 * 
	 * public Integer getNumeroDaParcelaCurso() { return numeroDaParcelaCurso; }
	 * 
	 * public void setNumeroDaParcelaCurso(Integer numeroDaParcelaCurso) {
	 * this.numeroDaParcelaCurso = numeroDaParcelaCurso; }
	 * 
	 * public Integer getNumeroDaParcelaMaterial() { return numeroDaParcelaMaterial;
	 * }
	 * 
	 * public void setNumeroDaParcelaMaterial(Integer numeroDaParcelaMaterial) {
	 * this.numeroDaParcelaMaterial = numeroDaParcelaMaterial; }
	 * 
	 * public BigDecimal getValorTotalParcelado() { return valorTotalParcelado; }
	 * 
	 * public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
	 * this.valorTotalParcelado = valorTotalParcelado; }
	 * 
	 * public Date getDataMatricula() { return dataMatricula; }
	 * 
	 * public void setDataMatricula(Date dataMatricula) { this.dataMatricula =
	 * dataMatricula; }
	 * 
	 * public Integer getDiaVencimento() { return diaVencimento; }
	 * 
	 * public void setDiaVencimento(Integer diaVencimento) { this.diaVencimento =
	 * diaVencimento; }
	 * 
	 * public Date getDataVencimento() { return dataVencimento; }
	 * 
	 * public void setDataVencimento(Date dataVencimento) { this.dataVencimento =
	 * dataVencimento; }
	 * 
	 * public Date getDataPagamento() { return dataPagamento; }
	 * 
	 * public void setDataPagamento(Date dataPagamento) { this.dataPagamento =
	 * dataPagamento; }
	 * 
	 * public String getFormaDePagamento() { return formaDePagamento; }
	 * 
	 * public void setFormaDePagamento(String formaDePagamento) {
	 * this.formaDePagamento = formaDePagamento; }
	 * 
	 * public BigDecimal getValorPago() { return valorPago; }
	 * 
	 * public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago;
	 * }
	 * 
	 * public String getStatus() { return status; }
	 * 
	 * public void setStatus(String status) { this.status = status; }
	 * 
	 * public Date getDataCancelamento() { return dataCancelamento; }
	 * 
	 * public void setDataCancelamento(Date dataCancelamento) {
	 * this.dataCancelamento = dataCancelamento; }
	 * 
	 * public static long getSerialversionuid() { return serialVersionUID; }
	 */
}