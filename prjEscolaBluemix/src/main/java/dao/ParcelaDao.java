package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Aluno;
import model.Parcela;
import model.Periodo;

public class ParcelaDao {
	private static Connection conexao;
	private static PreparedStatement stm;
	private static ResultSet rs;
	// private static final String SQL_BUSCAR_CONTAS_A_RECEBER_DO_ALUNO = "select
	// tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome,
	// tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso,
	// tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento,
	// tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso,
	// tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from
	// tb_aluno, tb_parcela where tb_aluno.id =? and tb_parcela.idAluno=?";

	public ParcelaDao() {
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = ConnectionFactory.getConnection();
				System.out.println("conexao aberta pelo parcelaDao");
			}
		} catch (SQLException e) {
			System.out.println("erro ao tentar abrir a conexao com o parcelaDao");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Parcela> buscarContasAreceber(Periodo periodo) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(sdf.format(periodo.getDataInicio()));
		System.out.println(sdf.format(periodo.getDataFinal()));

		Parcela parcela;
		Aluno aluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");
		try {
			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id = tb_parcela.idAluno and tb_parcela.dataVencimento >= "
							+ "'" + sdf.format(periodo.getDataInicio()) + "'" + " and tb_parcela.dataVencimento <= "
							+ "'" + sdf.format(periodo.getDataFinal()) + "'" + " order by dataVencimento, tb_aluno.nome");
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").equals(valorPagoTeste)) {
					System.out.println("Possui contas a receber");

					parcela = new Parcela();
					aluno = new Aluno();
					parcela.setId(rs.getInt("id"));
					aluno.setId(rs.getInt("idAluno"));
					parcela.setAluno(aluno);

					parcela.getAluno().setMatricula((rs.getString("matricula")));
					parcela.getAluno().setNome((rs.getString("nome")));
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));

					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));

					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());

					parcela.setValorPago(rs.getBigDecimal("valorPago"));

					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasaReceber");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Parcela> buscarContasRecebidas(Periodo periodo) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(sdf.format(periodo.getDataInicio()));
		System.out.println(sdf.format(periodo.getDataFinal()));

		Parcela parcela;
		Aluno aluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");
		try {
			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id = tb_parcela.idAluno and tb_parcela.dataVencimento >= "
							+ "'" + sdf.format(periodo.getDataInicio()) + "'" + " and tb_parcela.dataVencimento <= "
							+ "'" + sdf.format(periodo.getDataFinal()) + "'"
							+ " order by tb_parcela.dataVencimento, tb_aluno.nome");

			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").floatValue() > valorPagoTeste.floatValue()) {
					System.out.println("Possui contas recebidas");

					parcela = new Parcela();
					aluno = new Aluno();
					parcela.setId(rs.getInt("id"));
					aluno.setId(rs.getInt("idAluno"));
					parcela.setAluno(aluno);

					parcela.getAluno().setMatricula((rs.getString("matricula")));
					parcela.getAluno().setNome((rs.getString("nome")));
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));

					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));

					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());

					parcela.setValorPago(rs.getBigDecimal("valorPago"));

					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasaReceber");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Parcela> buscarContasAreceberDoAluno(int id) {
		Parcela parcela;
		Aluno aluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");

		try {
			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id =? and tb_parcela.idAluno=?");
			stm.setInt(1, id);
			stm.setInt(2, id);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").equals(valorPagoTeste)) {
					System.out.println("Possui contas a receber");
					// configura o aluno
					aluno = new Aluno();
					aluno.setId(rs.getInt("idAluno"));
					aluno.setMatricula((rs.getString("matricula")));
					aluno.setNome((rs.getString("nome")));
					// configuro a parcela
					parcela = new Parcela();
					parcela.setId(rs.getInt("id"));
					parcela.setAluno(aluno);
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));
					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());
					parcela.setValorPago(rs.getBigDecimal("valorPago"));
					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasaReceber");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Parcela> buscarContasAreceberDoAluno(String nome) {
		Parcela parcela;
		Aluno aluno;
		int idAluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");

		try {
			// buscar id do aluno por nome
			stm = conexao.prepareStatement("select id from tb_aluno where nome=?");
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Encontrado");
				idAluno = rs.getInt("id");
			} else {
				System.out.println("nao encontrado");
				throw new Exception("Registro nao encontrado. ");
			}

			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id =? and tb_parcela.idAluno=?");
			stm.setInt(1, idAluno);
			stm.setInt(2, idAluno);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").equals(valorPagoTeste)) {
					System.out.println("Possui contas a receber");
					// configura o aluno
					aluno = new Aluno();
					aluno.setId(rs.getInt("idAluno"));
					aluno.setMatricula((rs.getString("matricula")));
					aluno.setNome((rs.getString("nome")));
					// configuro a parcela
					parcela = new Parcela();

					parcela.setId(rs.getInt("id"));
					parcela.setAluno(aluno);
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));
					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());
					parcela.setValorPago(rs.getBigDecimal("valorPago"));
					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasAreceberDoAluno");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Parcela> buscarContasRecebidasDoAluno(int id) {
		Parcela parcela;
		Aluno aluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");

		try {
			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id =? and tb_parcela.idAluno=?");
			stm.setInt(1, id);
			stm.setInt(2, id);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").floatValue() > valorPagoTeste.floatValue()) {
					System.out.println("Possui contas recebidas");
					// configura o aluno
					aluno = new Aluno();
					aluno.setId(rs.getInt("idAluno"));
					aluno.setMatricula((rs.getString("matricula")));
					aluno.setNome((rs.getString("nome")));
					// configuro a parcela
					parcela = new Parcela();
					parcela.setId(rs.getInt("id"));
					parcela.setAluno(aluno);
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));
					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());
					parcela.setValorPago(rs.getBigDecimal("valorPago"));
					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasaReceber");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Parcela> buscarContasRecebidasDoAluno(String nome) {
		Parcela parcela;
		Aluno aluno;
		int idAluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");

		try {
			// buscar id do aluno por nome
			stm = conexao.prepareStatement("select id from tb_aluno where nome=?");
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Encontrado");
				idAluno = rs.getInt("id");
			} else {
				System.out.println("nao encontrado");
				throw new Exception("Registro nao encontrado. ");
			}

			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id =? and tb_parcela.idAluno=?");
			stm.setInt(1, idAluno);
			stm.setInt(2, idAluno);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").floatValue() > valorPagoTeste.floatValue()) {
					System.out.println("Possui contas recebidas");
					// configura o aluno
					aluno = new Aluno();
					aluno.setId(rs.getInt("idAluno"));
					aluno.setMatricula((rs.getString("matricula")));
					aluno.setNome((rs.getString("nome")));
					// configuro a parcela
					parcela = new Parcela();

					parcela.setId(rs.getInt("id"));
					parcela.setAluno(aluno);
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));
					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());
					parcela.setValorPago(rs.getBigDecimal("valorPago"));
					java.sql.Date dataPagamento = rs.getDate("dataPagamento");

					if (dataPagamento == null)
						parcela.setDataPagamento(null);
					else
						parcela.setDataPagamento(new java.util.Date(dataPagamento.getTime()));

					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					parcelas.add(parcela);

				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasAreceberDoAluno");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	/*
	 * public List<Parcela> buscarContasRecebidasHoje() { Parcela parcela; Aluno
	 * aluno; List<Parcela> parcelas = new ArrayList<>(); // BigDecimal
	 * valorPagoTeste = new BigDecimal("0.00");
	 * 
	 * try { stm = conexao.prepareStatement(
	 * "select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela"
	 * ); rs = stm.executeQuery();
	 * 
	 * while (rs.next()) { java.sql.Date dataSql = rs.getDate("dataPagamento");
	 * 
	 * // se existir algum pagamento hoje if (dataSql != null) { // converter data
	 * sql para data util java.util.Date datapagamentoUtil = (new
	 * java.util.Date(rs.getDate("dataPagamento").getTime()));
	 * 
	 * // converter de data util para calendar Calendar dataPagamentoCalendar =
	 * Calendar.getInstance();
	 * 
	 * dataPagamentoCalendar.setTime(datapagamentoUtil);
	 * 
	 * // recuperar a data do do dataDePagamento e testar se é igual a data atual if
	 * ((dataPagamentoCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance()
	 * .get(Calendar.DAY_OF_MONTH)) & (dataPagamentoCalendar.get(Calendar.MONTH) ==
	 * Calendar.getInstance().get(Calendar.MONTH)) &
	 * (dataPagamentoCalendar.get(Calendar.YEAR) ==
	 * Calendar.getInstance().get(Calendar.YEAR))
	 * 
	 * ) { // configura o aluno aluno = new Aluno();
	 * aluno.setId(rs.getInt("idAluno"));
	 * aluno.setMatricula((rs.getString("matricula")));
	 * aluno.setNome((rs.getString("nome"))); // configuro a parcela parcela = new
	 * Parcela(); parcela.setId(rs.getInt("id")); parcela.setAluno(aluno);
	 * parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
	 * parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
	 * parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
	 * parcela.setDataVencimento(new
	 * java.util.Date(rs.getDate("dataVencimento").getTime())); Calendar
	 * dataVencimento = Calendar.getInstance();
	 * dataVencimento.setTime(parcela.getDataVencimento());
	 * parcela.setValorPago(rs.getBigDecimal("valorPago")); // java.sql.Date
	 * dataPagamento = rs.getDate("dataPagamento"); parcela.setDataPagamento(new
	 * java.util.Date(rs.getDate("dataPagamento").getTime()));
	 * parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
	 * parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
	 * parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));
	 * 
	 * parcelas.add(parcela); } } } } catch (Exception e) {
	 * System.out.println("Ocorreu algum erro no metodo buscarContasRecebidasHoje()"
	 * ); e.printStackTrace(); throw new RuntimeException(e); }
	 * ConnectionFactory.closeAll(conexao, stm, rs); return parcelas; }
	 */

	public List<Parcela> buscarContasRecebidasHoje() {
		Parcela parcela;
		Aluno aluno;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");
		try {
			stm = conexao.prepareStatement(
					"select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela");
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").floatValue() > valorPagoTeste.floatValue()) {
					System.out.println("Existe Contas pagas");

					aluno = new Aluno();
					aluno.setId(rs.getInt("id"));
					aluno.setNome(rs.getString("nome"));
					aluno.setMatricula(rs.getString("matricula"));

					parcela = new Parcela();
					parcela.setId(rs.getInt("id"));
					parcela.setAluno(aluno);
					parcela.setQtdTotalDeParcela(rs.getInt("qtdTotalDeParcela"));
					parcela.setNumeroDaParcelaCurso(rs.getInt("numeroDaParcelaCurso"));
					parcela.setNumeroDaParcelaMaterial(rs.getInt("numeroDaParcelaMaterial"));
					parcela.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));

					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(parcela.getDataVencimento());
					parcela.setValorPago(rs.getBigDecimal("valorPago"));
					// java.sql.Date dataPagamento = rs.getDate("dataPagamento");
					parcela.setDataPagamento(new java.util.Date(rs.getDate("dataPagamento").getTime()));
					parcela.setValorParcelaCurso(rs.getBigDecimal("valorParcelaCurso"));
					parcela.setValorParcelaMaterial(rs.getBigDecimal("valorParcelaMaterial"));
					parcela.setValorTotalParcelado(rs.getBigDecimal("valorTotalParcelado"));

					Calendar dataPagamentoTemp = Calendar.getInstance();
					dataPagamentoTemp.setTime(parcela.getDataPagamento());

					if ((dataPagamentoTemp.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance()
							.get(Calendar.DAY_OF_MONTH))
							&& (dataPagamentoTemp.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)))
						parcelas.add(parcela);
				}
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarNaoPagos");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;
	}
}

// se houve algum pagamento maior do que zero

/*
 * if (rs.getBigDecimal("valorPago").floatValue() > valorPagoTeste.floatValue())
 * { System.out.println("Existe contas recebidas");
 */
