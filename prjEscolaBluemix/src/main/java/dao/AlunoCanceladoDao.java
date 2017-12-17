package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import model.AlunoCancelado;

public class AlunoCanceladoDao {

	private static Connection conexao;
	private static PreparedStatement stm;
	private static ResultSet rs;

	public AlunoCanceladoDao() {
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = ConnectionFactory.getConnection();
				System.out.println("conexao aberta pelo alunoCanceladoDao");
			}
		} catch (SQLException e) {
			System.out.println("erro ao tentar abrir a conexao com o alunoCanceladoDao");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void gerarCancelamento(AlunoCancelado aluno) {

		try {
			stm = conexao.prepareStatement("select id, status from tb_alunoCancelado where id =?");
			stm.setInt(1, aluno.getId());
			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Encontrou");
				if (rs.getString("status").equals("Cancelado")) {
					System.out.println("Aluno já Cancelado");
					throw new Exception("Aluno já Cancelado");
				} else {
					boolean temParcela;
					int qtdTotalDeParcela = 0, numeroDaParcela;
					Calendar dataVencimento, proximoVencimento = null, dataPagamento, dataCancelamento = null;

					BigDecimal valorPago, valorParcela, valorTotalParcelado = null;

					// configurando o aluno com desconto obtido em reais e o valor do curso com
					// desconto.
					aluno.setValorDescontoReais(aluno.getValor().multiply(new BigDecimal(aluno.getPorCentoDesconto())
							.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_DOWN)));
					aluno.setValorComDesconto(aluno.getValor().subtract(aluno.getValorDescontoReais()));

					// calculando o valor da parcela do curso e da parcela do material
					valorParcela = aluno.getValorComDesconto().divide(new BigDecimal(aluno.getQtdParcelas()), 2,
							BigDecimal.ROUND_DOWN);

					// O residual da parcela do curso é igual ao valor do curso com desconto - valor
					// da parcela do curso multiplicado pela qtd de parcelas do curso
					aluno.setValorResidualParcela(aluno.getValorComDesconto()
							.subtract(valorParcela.multiply(new BigDecimal(aluno.getQtdParcelas()))));

					valorTotalParcelado = aluno.getValor().subtract(aluno.getValorDescontoReais());
					
					
					for (int i = 1; i <= aluno.getQtdParcelas();i++){
						
						System.out.println("Parcela #" + i);
						qtdTotalDeParcela = i;
						valorParcela = new BigDecimal(0.00);
						// Enquanto existir parcelas de curso, vou saber o numero da parcela do curo e
						// o valor da parcela
						if (i <= aluno.getQtdParcelas()) {
							temParcela = true;
							numeroDaParcela = i;
							valorParcela = valorParcela.add(valorParcela);
							
							// Se for a ultima parcela do curso, adicionar ao valor da parcela o residual na
							// última parcela
							if (i == aluno.getQtdParcelas())
								valorParcela = valorParcela.add(aluno.getValorResidualParcelaCurso());
						
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}