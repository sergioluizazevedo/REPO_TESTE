package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Unidade;

public class UnidadeDao {
	private static final String SQL_INSERT = "insert into tb_unidade (nome, endereco, dataCadastro, status) value (?,?,?,?)";
	private static final String SQL_SELECT_ALL = "select * from tb_unidade";
	private static final String SQL_SELECT_BY_ID = "select id, nome, endereco, dataCadastro, status from tb_unidade where id=?";
	private static final String SQL_SELECT_BY_NOME = "select id, nome, endereco, dataCadastro, status from tb_unidade where nome=?";
	private final String SQL_SELECT_STATUS_TURMA_BY_UNIDADE_ID = "select status from tb_turma where idUnidade=?";
	private static final String SQL_UPDATE = "update tb_unidade set nome=?, endereco=? where id=?";
	private static final String SQL_DESATIVAR = "update tb_unidade set ativo_s_n =?, dt_cancelame =? where id=?";
	private static final String SQL_UPDATE_STATUS = "update tb_unidade set status =? where id=?";

	private static final String SQL_DELETE_BY_ID = "delete from tb_unidade where id=?";
	private static final String SQL_DELETE_ALL = "delete from tb_unidade";

	private static Connection conexao;
	private static PreparedStatement stm;
	private static ResultSet rs;

	public UnidadeDao() {
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = ConnectionFactory.getConnection();
				System.out.println("conexao aberta pelo unidadeDao");
			}
		} catch (SQLException e) {
			System.out.println("erro ao tentar abrir a conexao com o unidadeDao");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// Adicionar
	public void adicionar(Unidade unidade) {
		try {
			// criar uma data atual do tipo util
			java.util.Date dataUtil = new java.util.Date();
			// configurar uma data atual para uma unidade
			unidade.setDataCadastro(dataUtil);
			// configurando um status para unidade
			unidade.setStatus("Ativada");

			stm = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());

			// criar uma data sql passando uma data util no formato de long
			Date dataSql = new java.sql.Date(unidade.getDataCadastro().getTime());
			stm.setDate(3, dataSql);

			stm.setString(4, unidade.getStatus());

			int linhas = stm.executeUpdate();
			System.out.println("Foram modificadas, " + linhas + " com sucesso!");

			if (linhas == 1) {
				try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						unidade.setId((generatedKeys.getInt(1)));

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

	public Unidade buscar(Integer id) {
		Unidade unidade;
		try {
			stm = conexao.prepareStatement(SQL_SELECT_BY_ID);
			stm.setInt(1, id);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");

				unidade = new Unidade();

				unidade.setId(rs.getInt("id"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setDataCadastro(rs.getDate("dataCadastro"));
				unidade.setStatus(rs.getString("status"));
			} else {
				System.out.println("nao encontrado");
				unidade = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no m√©todo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return unidade;
	}

	public Unidade buscar(String nome) {
		Unidade unidade;
		try {
			stm = conexao.prepareStatement(SQL_SELECT_BY_NOME);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				System.out.println("Encontrado");

				unidade = new Unidade();

				unidade.setId(rs.getInt("id"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setDataCadastro(rs.getDate("dataCadastro"));
				unidade.setStatus(rs.getString("status"));
			} else {
				System.out.println("nao encontrado");
				unidade = null;
				throw new Exception("Registro nao encontrado. ");
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no m√©todo buscar por id");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return unidade;
	}

	public void modificar(Unidade unidade) {
		try {
			stm = conexao.prepareStatement(SQL_UPDATE);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setInt(3, unidade.getId());

			int linhas = stm.executeUpdate();
			System.out.println("Foram atualizadas, " + linhas + " com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo modificar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}

	public List<Unidade> buscarTodos() {
		List<Unidade> unidades = new ArrayList<Unidade>();
		Unidade unidade;

		try {
			stm = conexao.prepareStatement(SQL_SELECT_ALL);
			rs = stm.executeQuery();

			while (rs.next()) {

				unidade = new Unidade();

				unidade.setId(rs.getInt("id"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setDataCadastro(rs.getDate("dataCadastro"));
				unidade.setStatus(rs.getString("status"));

				unidades.add(unidade);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarTodos");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

		}
		ConnectionFactory.closeAll(conexao, stm, rs);
		return unidades;
	}

	public void desativar(Unidade unidade) {
		List<String> todosStatus = new ArrayList<>();
		String status;
		try {
			stm = conexao.prepareStatement(SQL_SELECT_STATUS_TURMA_BY_UNIDADE_ID);
			stm.setInt(1, unidade.getId());
			rs = stm.executeQuery();
			while (rs.next()) {
				status = rs.getString("status");
				if (status.equals("Ativada")) {
					System.err.println("Turma Ativa, n„o È possÌvel desativar essa unidade.");
					ConnectionFactory.closeAll(conexao, stm, rs);
					throw new SQLException();
				}
			}
			unidade.setStatus("Desativada");
			stm = conexao.prepareStatement(SQL_UPDATE_STATUS);
			stm.setString(1, unidade.getStatus());
			stm.setInt(2, unidade.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo desativar");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ConnectionFactory.closeAll(conexao, stm, rs);
	}
}