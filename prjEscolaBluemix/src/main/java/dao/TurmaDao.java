package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Turma;
import model.Unidade;

public class TurmaDao {
	private static final String SQL_INSERT = "insert into tb_turma (idUnidade, nome, status) value (?,?,?)";
	private static final String SQL_SELECT_ALL = "select * from tb_turma";
	private static final String SQL_BUSCAR_STATUS_ALUNO_BY_TURMA_ID = "select status from tb_aluno where id_turma=?";
	private static final String SQL_SELECT_BY_ID = "select id, idUnidade, nome, status from tb_turma where id=?";
	private static final String SQL_SELECT_BY_NOME = "select id, idUnidade, nome, status from tb_turma where nome=?";
	private static final String SQL_UPDATE = "update tb_turma set nome=? where id=?";
	private static final String SQL_DESATIVAR = "update tb_turma set status =? where id=?";
	private static final String SQL_DELETE_BY_ID = "delete from tb_turma where id=?";
	private static final String SQL_DELETE_ALL = "delete from tb_turma";
	private static final String SQL_UPDATE_STATUS = "update tb_turma set status =? where id=?";;

	private static Connection conexao;
	private static PreparedStatement stm;
	private static ResultSet rs;

	public TurmaDao() {
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = ConnectionFactory.getConnection();
				System.out.println("conexao aberta pelo TURMAdAO");
			}
		} catch (SQLException e) {
			System.out.println("erro ao tentar abrir a conexao com o alunodao");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// Adicionar
	public void adicionar(Turma turma) {

		// buscar a unidade no banco
		UnidadeDao unidadeDao = new UnidadeDao();

		Unidade unidadeCadastrada = unidadeDao.buscar(turma.getUnidade().getId());

		// testar se a unidade est√° ativa ou n√£o

		if (unidadeCadastrada.getStatus().equals("Ativada")) {
			turma.setUnidade(unidadeCadastrada);
			turma.setStatus("Ativada");

			try {
				stm = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				stm.setInt(1, turma.getUnidade().getId());
				stm.setString(2, turma.getNome());
				stm.setString(3, turma.getStatus());

				int linhas = stm.executeUpdate();
				System.out.println("Foram modificadas, " + linhas + " com sucesso!");

				if (linhas == 1) {
					try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							turma.setId((generatedKeys.getInt(1)));
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

		} else {
			try {
				throw new Exception("Unidade desativada");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public Turma buscar(Integer id) {
		Turma turma;
		try {
			stm = conexao.prepareStatement(SQL_SELECT_BY_ID);
			stm.setInt(1, id);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");
				Unidade unidade = new Unidade();
				turma = new Turma();
				turma.setUnidade(unidade);
				turma.getUnidade().setId(rs.getInt("idUnidade"));
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setStatus(rs.getString("status"));
			} else {
				System.out.println("nao encontrado");
				turma = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no m√©todo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return turma;
	}

	public Turma buscar(String nome) {
		Turma turma;
		try {
			stm = conexao.prepareStatement(SQL_SELECT_BY_NOME);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");
				Unidade unidade = new Unidade();
				turma = new Turma();
				turma.setUnidade(unidade);

				turma.getUnidade().setId(rs.getInt("idUnidade"));
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setStatus(rs.getString("status"));
			} else {
				System.out.println("nao encontrado");
				turma = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no m√©todo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return turma;
	}

	public void modificar(Turma turma) {
		try {
			stm = conexao.prepareStatement(SQL_UPDATE);
			stm.setString(1, turma.getNome());
			stm.setInt(2, turma.getId());

			int linhas = stm.executeUpdate();
			System.out.println("Foram atualizadas, " + linhas + " com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo modificar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public List<Turma> buscarTodos() {
		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Unidade unidade;

		try {
			stm = conexao.prepareStatement(SQL_SELECT_ALL);
			rs = stm.executeQuery();

			while (rs.next()) {
				unidade = new Unidade();
				unidade.setId(rs.getInt("idUnidade"));
				turma = new Turma();
				turma.setUnidade(unidade);
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setStatus(rs.getString("status"));

				turmas.add(turma);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarTodos");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return turmas;
	}

	public void desativar(Turma turma) {
		String estatus;
		try {
			stm = conexao.prepareStatement(SQL_BUSCAR_STATUS_ALUNO_BY_TURMA_ID);
			stm.setInt(1, turma.getId());
			rs = stm.executeQuery();

			while (rs.next()) {
				estatus = rs.getString("status");
				if (estatus.equals("Matriculado")) {
					System.err.println("Aluno Ativo, n„o È possÌvel desativar essa turma.");
					ConnectionFactory.closeAll(conexao, stm, rs);
					throw new SQLException();
				}
			}
			turma.setStatus("Desativada");
			stm = conexao.prepareStatement(SQL_UPDATE_STATUS);
			stm.setString(1, turma.getStatus());
			stm.setInt(2, turma.getId());
			stm.executeUpdate();

		} catch (

		SQLException e) {
			System.out.println("Ocorreu algum erro no metodo desativarTurma");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

}
