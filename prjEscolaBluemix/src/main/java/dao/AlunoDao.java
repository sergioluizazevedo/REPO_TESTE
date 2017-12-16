package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Aluno;
import model.Parcela;
import model.Relatorio;
import model.Turma;

public class AlunoDao {
	Aluno novoAluno;
	private static String dataInicio;
	private static String dataFinal;

	private static final String SQL_SALVAR = "insert into tb_aluno (nome, cpf, rg, orgaoExp, ufRg, sexo, dataNascimento, email, celular, telefone, pai, mae, cep, endereco, bairro, cidade, estado, status) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_MATRICULAR = "insert into tb_parcela (idAluno,qtdTotalDeParcela,numeroDaParcelaCurso,numeroDaParcelaMaterial,dataVencimento,valorPago,dataPagamento,valorParcelaCurso,valorParcelaMaterial,valorTotalParcelado)value(?,?,?,?,?,?,?,?,?,?)";
	private static final String ATUALIZA_APOS_MATRICULAR = "update tb_aluno set valorTaxaMatricula=?,valorCurso=?,porCentoDesconto=?,qtdParcelasCurso=?,valorMaterial=?,qtdParcelasMaterial=?,valorDescontoReais=?,valorCursoComDesconto=?,valorResidualParcelaCurso=?,valorResidualParcelaMaterial=?,dataMatricula=?,diaVencimento=?,formaDePagamento=?, status=?, matricula=?, idTurma=? where id =?";
	private static final String SQL_BUSCAR_BY_ID = "select id, matricula, idTurma, nome, cpf, rg, orgaoExp, ufRg, sexo, dataNascimento, email, celular, telefone, pai, mae, cep, endereco, bairro, cidade, estado, status, dataMatricula from tb_aluno where id=?";
	private static final String SQL_BUSCAR_BY_NOME = "select id, matricula, idTurma, nome, cpf, rg, orgaoExp, ufRg, sexo, dataNascimento, email, celular, telefone, pai, mae, cep, endereco, bairro, cidade, estado, status from tb_aluno where nome=?";
	private static final String SQL_BUSCAR_DEBITO = "select valorPago from tb_parcela where idAluno =?";
	private static final String SQL_DESATIVAR = "update tb_aluno set status=?, dataCancelamento =? where id=?";
	private static final String SQL_BUSCAR_TODOS = "select id, matricula, idTurma, nome, cpf, rg, orgaoExp, ufRg, sexo, dataNascimento, email, celular, telefone, pai, mae, cep, endereco, bairro, cidade, estado, status, dataMatricula, dataCancelamento from tb_aluno";
	private static final String SQL_ATUALIZAR = "update tb_aluno set nome=?, cpf=?, rg=?, orgaoExp=?, ufRg=?, sexo=?, dataNascimento=?, email=?, celular=?, telefone=?, pai=?, mae=?, cep=?, endereco=?, bairro=?, cidade=?, estado=? where id=?";
	private static final String SQL_BUSCAR_CONTAS_A_RECEBER = "select tb_parcela.id, tb_parcela.idAluno, tb_aluno.matricula, tb_aluno.nome, tb_parcela.QtdTotalDeParcela, tb_parcela.numeroDaParcelaCurso, tb_parcela.numeroDaParcelaMaterial, tb_parcela.dataVencimento, tb_parcela.valorPago, tb_parcela.dataPagamento, tb_parcela.ValorParcelaCurso, tb_parcela.ValorParcelaMaterial, tb_parcela.valorTotalParcelado from tb_aluno, tb_parcela where tb_aluno.id = tb_parcela.idAluno and tb_parcela.dataVencimento >="
			+ dataInicio + " and tb_parcela.dataVencimento <=" + dataFinal + " order by dataVencimento";

	// private static final String SQL_BUSCAR_CONTAS_A_RECEBER = "select
	// tb_parcela.ID,tb_parcela.idAluno,tb_aluno.matricula, tb_aluno.nome,
	// tb_parcela.QtdTotalDeParcela,tb_parcela.numeroDaParcelaCurso,
	// tb_parcela.numeroDaParcelaMaterial,tb_parcela.dataVencimento,tb_parcela.valorPago,tb_parcela.dataPagamento,tb_parcela.ValorParcelaCurso,
	// tb_parcela.ValorParcelaMaterial,tb_parcela.valorTotalParcelado from
	// tb_aluno,tb_parcela where tb_aluno.id = tb_parcela.idAluno and
	// tb_parcela.dataVencimento >= ? and tb_parcela.dataVencimento <= ? order by
	// dataVencimento";

	private static Connection conexao;
	private static PreparedStatement stm;
	private static ResultSet rs;

	public AlunoDao() {
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = ConnectionFactory.getConnection();
				System.out.println("conexao aberta pelo alunoDAo");
			}
		} catch (SQLException e) {
			System.out.println("erro ao tentar abrir a conexao com o alunodao");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void salvar(Aluno aluno) {
		try {
			stm = conexao.prepareStatement(SQL_SALVAR, PreparedStatement.RETURN_GENERATED_KEYS);			
			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getCpf());
			stm.setString(3, aluno.getRg());
			stm.setString(4, aluno.getOrgaoExp());
			stm.setString(5, aluno.getUfRg());
			stm.setString(6, aluno.getSexo());

			stm.setDate(7, new Date(aluno.getDataNascimento().getTime()));
			stm.setString(8, aluno.getEmail());
			stm.setString(9, aluno.getCelular());
			stm.setString(10, aluno.getTelefone());
			stm.setString(11, aluno.getPai());
			stm.setString(12, aluno.getMae());
			stm.setString(13, aluno.getCep());
			stm.setString(14, aluno.getEndereco());
			stm.setString(15, aluno.getBairro());
			stm.setString(16, aluno.getCidade());
			stm.setString(17, aluno.getEstado());
			aluno.setStatus("Não Matriculado");
			stm.setString(18, aluno.getStatus());

			int linhas = stm.executeUpdate();
			System.out.println("Foram modificadas, " + linhas + " com sucesso!");

			if (linhas == 1) {
				try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						aluno.setId((generatedKeys.getInt(1)));
						// ConnectionFactory.closeAll(conexao, stm, rs);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
			} else
				throw new Exception("Nao foi possivel incluir 1 registro.");

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo adicionar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public void matricular(Aluno aluno) {

		try {
			stm = conexao.prepareStatement("select id, status from tb_aluno where id =?");
			stm.setInt(1, aluno.getId());
			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Encontrou");
				if (rs.getString("status").equals("Matriculado")) {
					System.out.println("Aluno já matriculado");
					throw new Exception("Aluno já matriculado");
				} else {

					boolean temParcelaDeCurso, temParcelaDeMaterial;

					int qtdTotalDeParcela = 0, numeroDaParcelaCurso, numeroDaParcelaMaterial = 0, diferenca;

					Calendar dataVencimento, proximoVencimento = null, dataPagamento, dataMatricula = null,
							dataCancelamento = null;

					BigDecimal valorPago, valorParcelaCurso, valorParcelaMaterial, valorParcela,
							valorTotalParcelado = null;

					// configurando o aluno com desconto obtido em reais e o valor do curso com
					// desconto.
					aluno.setValorDescontoReais(
							aluno.getValorCurso().multiply(new BigDecimal(aluno.getPorCentoDesconto())
									.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_DOWN)));
					aluno.setValorCursoComDesconto(aluno.getValorCurso().subtract(aluno.getValorDescontoReais()));

					// calculando o valor da parcela do curso e da parcela do material
					valorParcelaCurso = aluno.getValorCursoComDesconto()
							.divide(new BigDecimal(aluno.getQtdParcelasCurso()), 2, BigDecimal.ROUND_DOWN);
					valorParcelaMaterial = aluno.getValorMaterial()
							.divide(new BigDecimal(aluno.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

					// O residual da parcela do curso é igual ao valor do curso com desconto - valor
					// da parcela do curso multiplicado pela qtd de parcelas do curso
					aluno.setValorResidualParcelaCurso(aluno.getValorCursoComDesconto()
							.subtract(valorParcelaCurso.multiply(new BigDecimal(aluno.getQtdParcelasCurso()))));
					aluno.setValorResidualParcelaMaterial(aluno.getValorMaterial()
							.subtract(valorParcelaMaterial.multiply(new BigDecimal(aluno.getQtdParcelasMaterial()))));

					valorTotalParcelado = aluno.getValorCurso().add(aluno.getValorTaxaMatricula()
							.add(aluno.getValorMaterial().subtract(aluno.getValorDescontoReais())));

					for (int i = 1; (i <= aluno.getQtdParcelasCurso()
							|| i <= aluno.getQtdParcelasMaterial() + 1); i++) {
						System.out.println("Parcela #" + i);
						qtdTotalDeParcela = i;
						valorParcela = new BigDecimal(0.00);
						// Enquanto existir parcelas de curso, vou saber o numero da parcela do curo e
						// o valor da parcela
						if (i <= aluno.getQtdParcelasCurso()) {
							temParcelaDeCurso = true;
							numeroDaParcelaCurso = i;
							valorParcela = valorParcela.add(valorParcelaCurso);

							// Se for a ultima parcela do curso, adicionar ao valor da parcela o residual na
							// última parcela
							if (i == aluno.getQtdParcelasCurso())
								valorParcela = valorParcela.add(aluno.getValorResidualParcelaCurso());

						} else {
							// Se não exisitir mais parcelas de curso, zero o valor da minha parcela e o
							// numero da parcela do curso.
							// valorParcelaCurso = new BigDecimal(0.00);
							// valorParcela = new BigDecimal(0);
							numeroDaParcelaCurso = 0;
							temParcelaDeCurso = false;

						}

						// se for a 1a parcela, adicionar a taxa de matricula datas e pagamentos
						if (i == 1) {
							// Providenciar Datas
							dataVencimento = Calendar.getInstance();
							dataPagamento = Calendar.getInstance();
							aluno.setDataMatricula(new java.util.Date());
							proximoVencimento = Calendar.getInstance(); // referencia a data atual
							proximoVencimento.set(Calendar.DAY_OF_MONTH, aluno.getDiaVencimento()); // dia que ele
																									// escolheu

							/*
							 * calcular a diferença de dias entre hoje e o dia informado pelo usuário...
							 * considerando que o usuário pode escolher entre 1 e 28 como dias válidos!!!
							 * Exemplo 1: usuario informa 28, hoje é 30, resultado -2 => valor absoluto 2
							 * Exemplo 2: usuario informa 5, hoje é 30, resultado -25 => valor absoluto 25
							 */

							// subtrai o dia de vencimento - a data atual. Por exempo dia vencimento 10 e
							// dia atual 20. Se subtrair 10-20 o resultado será negativo, por isso usamos o
							// método abs, para termos um valor inteiro
							diferenca = Math
									.abs(aluno.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

							if (diferenca >= 20)
								proximoVencimento.add(Calendar.MONTH, 1); // 30 dias adicionais

							// if (aluno.getQtdParcelasCurso().intValue() == 0)
							if (!temParcelaDeCurso)
								valorParcela = valorParcela.add(aluno.getValorCursoComDesconto());

							valorParcela = valorParcela.add(aluno.getValorTaxaMatricula());

							valorPago = valorParcela;

							// configurar a matricula do aluno
							Calendar cal = Calendar.getInstance();
							aluno.setMatricula(String.valueOf(cal.get(Calendar.YEAR))
									+ String.valueOf(aluno.getTurma().getId()) + String.valueOf(aluno.getId()));

							aluno.setStatus("Matriculado");
						} else {
							proximoVencimento.add(Calendar.MONTH, 1); // mais 30 dias
							dataVencimento = proximoVencimento;
							dataPagamento = null;
							valorPago = new BigDecimal(0);
						}

						// Enquanto existir parcelas de material, vou saber o numero da parcela do
						// material e
						// a parcela receberá o valor do material

						if (i > 1 && i <= aluno.getQtdParcelasMaterial() + 1) {
							temParcelaDeMaterial = true;
							numeroDaParcelaMaterial = i - 1;
							valorParcela = valorParcela.add(valorParcelaMaterial);
							// parcelaMaterialFinal = valorParcelaMaterial;

							// acrescentar o residual na última parcela
							if (i == aluno.getQtdParcelasMaterial() + 1)
								valorParcela = valorParcela.add(aluno.getValorResidualParcelaMaterial());

						} else {
							numeroDaParcelaMaterial = 0;
							temParcelaDeMaterial = false;
							// parcelaMaterialFinal = new BigDecimal(0.00);
						}

						try {
							stm = conexao.prepareStatement(SQL_MATRICULAR, PreparedStatement.RETURN_GENERATED_KEYS);
							stm.setInt(1, aluno.getId());
							stm.setInt(2, qtdTotalDeParcela);
							stm.setInt(3, numeroDaParcelaCurso);
							stm.setInt(4, numeroDaParcelaMaterial);
							stm.setDate(5, new java.sql.Date(dataVencimento.getTimeInMillis()));
							stm.setBigDecimal(6, valorPago);

							if (dataPagamento == null)
								stm.setDate(7, null);
							else
								stm.setDate(7, new java.sql.Date(dataPagamento.getTimeInMillis()));

							if (temParcelaDeCurso)
								stm.setBigDecimal(8, valorParcelaCurso);
							else
								stm.setBigDecimal(8, new BigDecimal(0.00));

							if (temParcelaDeMaterial)
								stm.setBigDecimal(9, valorParcelaMaterial);
							else
								stm.setBigDecimal(9, new BigDecimal(0.00));

							stm.setBigDecimal(10, valorParcela);

							int linhas = stm.executeUpdate();

							System.out.println("Foram modificadas, " + linhas + " com sucesso!");
						} catch (Exception e) {
							System.out.println("Ocorreu algum erro no metodo matricular");
							e.printStackTrace();
							throw new RuntimeException(e);
						}

						// atualizar no banco
						if (i == 1) {
							try {
								stm = conexao.prepareStatement(ATUALIZA_APOS_MATRICULAR);
								stm.setBigDecimal(1, aluno.getValorTaxaMatricula());
								stm.setBigDecimal(2, aluno.getValorCurso());
								stm.setInt(3, aluno.getPorCentoDesconto());
								stm.setInt(4, aluno.getQtdParcelasCurso());
								stm.setBigDecimal(5, aluno.getValorMaterial());
								stm.setInt(6, aluno.getQtdParcelasMaterial());
								stm.setBigDecimal(7, aluno.getValorDescontoReais());
								stm.setBigDecimal(8, aluno.getValorCursoComDesconto());
								stm.setBigDecimal(9, aluno.getValorResidualParcelaCurso());
								stm.setBigDecimal(10, aluno.getValorResidualParcelaMaterial());
								stm.setDate(11, new java.sql.Date(aluno.getDataMatricula().getTime()));
								stm.setInt(12, aluno.getDiaVencimento());
								stm.setString(13, aluno.getFormaDePagamento());
								stm.setString(14, aluno.getStatus());
								
								stm.setString(15, aluno.getMatricula());
								stm.setInt(16, aluno.getTurma().getId());
								
								stm.setInt(17, aluno.getId());

								int linhas = stm.executeUpdate();
								System.out.println("Foram modificadas, " + linhas + " com sucesso!");
							} catch (Exception e) {
								System.out.println(
										"Ocorreu algum erro na tentativa de rodar o SQL ATUALIZAR_APOS_MATRICULAR");
								e.printStackTrace();
								throw new RuntimeException(e);
							}
						}

					}

				}
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo matricular na hora de pesquisar o status do aluno");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public Aluno buscar(int id) {

		Aluno aluno;
		Turma turma;
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_BY_ID);
			stm.setInt(1, id);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");

				turma = new Turma();
				aluno = new Aluno();
				aluno.setTurma(turma);

				aluno.setId(rs.getInt("id"));
				turma.setId(rs.getInt("idTurma"));

				if (rs.getString("matricula") == null)
					aluno.setMatricula("Não Matriculado");
				else
					aluno.setMatricula(rs.getString("matricula"));

				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgaoExp"));
				aluno.setUfRg(rs.getString("ufRg"));
				aluno.setSexo(rs.getString("sexo"));

				java.sql.Date dataNascimento = rs.getDate("dataNascimento");
				aluno.setDataNascimento(new java.util.Date(dataNascimento.getTime()));

				aluno.setEmail(rs.getString("email"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));
				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setStatus(rs.getString("Status"));

				if (rs.getDate("dataMatricula") == null)
					aluno.setDataMatricula(null);
				else
					aluno.setDataMatricula(new java.util.Date(rs.getDate("dataMatricula").getTime()));
			} else {
				System.out.println("nao encontrado");
				aluno = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return aluno;
	}

	public Aluno buscar(String nome) {
		Aluno aluno;
		Turma turma;
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_BY_NOME);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");

				turma = new Turma();
				turma.setId(rs.getInt("idTurma"));

				aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setTurma(turma);
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgaoExp"));
				aluno.setUfRg(rs.getString("ufRg"));
				aluno.setSexo(rs.getString("sexo"));

				java.sql.Date dataNascimento = rs.getDate("dataNascimento");
				aluno.setDataNascimento(new java.util.Date(dataNascimento.getTime()));

				aluno.setEmail(rs.getString("email"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));
				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setStatus(rs.getString("Status"));

				if (rs.getDate("dataMatricula") == null)
					aluno.setDataMatricula(null);
				else
					aluno.setDataMatricula(new java.util.Date(rs.getDate("dataMatricula").getTime()));

			} else {
				System.out.println("nao encontrado");
				aluno = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no mÃ©todo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return aluno;
	}

	public void desativar(int id) {
		try {
			Calendar dataAtual = Calendar.getInstance();

			if (dataAtual.get(Calendar.MONTH) > 7) {
				System.err
						.println("No mês " + dataAtual.get(Calendar.MONTH) + " nenhum aluno pode cancelar seu curso.");
				throw new SQLException();
			} else {
				java.util.Date dataUtil = new java.util.Date();
				stm = conexao.prepareStatement(SQL_DESATIVAR);
				stm.setString(1, "Desativado");
				stm.setDate(2, new java.sql.Date(dataUtil.getTime()));
				stm.setInt(3, id);

				int linhas = stm.executeUpdate();
				System.out.println("Foram atualizadas, " + linhas + " com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo desativar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public void modificar(Aluno aluno) {
		try {
			stm = conexao.prepareStatement(SQL_ATUALIZAR);

			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getCpf());
			stm.setString(3, aluno.getRg());
			stm.setString(4, aluno.getOrgaoExp());
			stm.setString(5, aluno.getUfRg());
			stm.setString(6, aluno.getSexo());
			stm.setDate(7, new java.sql.Date(aluno.getDataNascimento().getTime()));
			stm.setString(8, aluno.getEmail());
			stm.setString(9, aluno.getCelular());
			stm.setString(10, aluno.getTelefone());
			stm.setString(11, aluno.getPai());
			stm.setString(12, aluno.getMae());
			stm.setString(13, aluno.getCep());
			stm.setString(14, aluno.getEndereco());
			stm.setString(15, aluno.getBairro());
			stm.setString(16, aluno.getCidade());
			stm.setString(17, aluno.getEstado());
			stm.setInt(18, aluno.getId());

			int linhas = stm.executeUpdate();
			System.out.println("Foram atualizadas, " + linhas + " com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo modificar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public List<Parcela> buscarContasaReceber(java.util.Date dataInicio, java.util.Date dataFinal) {

		AlunoDao.dataInicio = dataInicio.toString();
		AlunoDao.dataFinal = dataFinal.toString();
		Parcela parcela = null;
		List<Parcela> parcelas = new ArrayList<>();
		BigDecimal valorPagoTeste = new BigDecimal("0.00");
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_CONTAS_A_RECEBER);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("valorPago").equals(valorPagoTeste)) {
					System.out.println("Está devendo");

					parcela = new Parcela();

					parcela.setId(rs.getInt("id"));
					parcela.getAluno().setId(rs.getInt("idAluno"));
					parcela.getAluno().setMatricula(rs.getString("matricula"));
					parcela.getAluno().setNome(rs.getString("nome"));
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
					/*
					 * if ((dataVencimento.get(Calendar.DAY_OF_MONTH) >
					 * Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) &&
					 * (dataVencimento.get(Calendar.MONTH) ==
					 * Calendar.getInstance().get(Calendar.MONTH))) {
					 * 
					 * }
					 */ }
			}
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo buscarContasaReceber");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return parcelas;

	}

	public List<Aluno> buscarTodos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno;
		// Turma turma;
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_TODOS);
			rs = stm.executeQuery();

			while (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setTurma(new Turma());
				aluno.getTurma().setId(rs.getInt("idTurma"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgaoExp"));
				aluno.setUfRg(rs.getString("ufRg"));
				aluno.setSexo(rs.getString("sexo"));

				java.sql.Date dataNascimento = rs.getDate("dataNascimento");
				long data = dataNascimento.getTime();

				aluno.setDataNascimento(new java.util.Date(data));

				// aluno.setDataNascimento(new
				// java.util.Date(rs.getDate("dataNascimento").getTime()));
				aluno.setEmail(rs.getString("email"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));
				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setStatus(rs.getString("status"));

				if (rs.getDate("dataMatricula") == null)
					aluno.setDataMatricula(null);
				else
					aluno.setDataMatricula(new java.util.Date(rs.getDate("dataMatricula").getTime()));

				if (rs.getDate("dataCancelamento") == null)
					aluno.setDataCancelamento(null);
				else
					aluno.setDataCancelamento(new java.util.Date(rs.getDate("dataCancelamento").getTime()));

				alunos.add(aluno);

			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarTodos");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return alunos;
	}

	public void desativar1(int id) {
		BigDecimal valorPago;
		BigDecimal valorComparar = new BigDecimal("0.00");
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_DEBITO);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()) {
				valorPago = rs.getBigDecimal("valorPago");
				if (valorPago.equals(valorComparar)) {
					System.err.println("Aluno com contas a pagar, não é possível desativar. ");
					ConnectionFactory.closeAll(conexao, stm, rs);
					throw new SQLException();
				}
			}
			java.util.Date dataAtual = new java.util.Date();
			stm = conexao.prepareStatement(SQL_DESATIVAR);
			stm.setString(1, "Desativado");
			stm.setDate(2, new java.sql.Date(dataAtual.getTime()));
			stm.setInt(3, id);

			int linhas = stm.executeUpdate();
			System.out.println("Foram atualizadas, " + linhas + " com sucesso!");

		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo desativar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}
}
