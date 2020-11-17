package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.PagamentoModel;

public class PagamentoDao implements IDao<PagamentoModel> {
	private Connection connection;

	public PagamentoDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(PagamentoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Pagamento (Valor, Servico, Descricao, Comentario" + ") values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setFloat(1, obj.getValor());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setString(3, obj.getDescricao());
			preparedStatement.setString(7, obj.getComentario());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			int lastInsertedId = 1;
			
			if (resultSet.next()) {
				lastInsertedId = resultSet.getInt(1);
			}

			return lastInsertedId;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(PagamentoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Pagamento set Valor=?, Servico=?, Descricao=?, Comentario=?  where Id=?");

			preparedStatement.setFloat(1, obj.getValor());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setString(3, obj.getDescricao());
			preparedStatement.setString(4, obj.getComentario());
			preparedStatement.setInt(5, obj.getId());

			preparedStatement.executeUpdate();
			return obj.getId();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public void delete(int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Pagamento where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PagamentoModel find(PagamentoModel obj) {
		PagamentoModel pagamento = new PagamentoModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Pagamento where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				pagamento.setId(rs.getInt("Id"));
				pagamento.setValor(rs.getFloat("Valor"));
				pagamento.setServico(rs.getInt("Servico"));
				pagamento.setDescricao(rs.getString("Descricao"));
				pagamento.setComentario(rs.getString("Comentario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pagamento;
	}

	@Override
	public ArrayList<PagamentoModel> findAll() {
		ArrayList<PagamentoModel> pagamentos = new ArrayList<PagamentoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Pagamento");

			while (rs.next()) {
				PagamentoModel pagamento = new PagamentoModel();

				pagamento.setId(rs.getInt("Id"));
				pagamento.setValor(rs.getFloat("Valor"));
				pagamento.setServico(rs.getInt("Servico"));
				pagamento.setDescricao(rs.getString("Descricao"));
				pagamento.setComentario(rs.getString("Comentario"));

				pagamentos.add(pagamento);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pagamentos;
	}
}
