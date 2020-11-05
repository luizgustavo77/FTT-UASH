package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Pagamento;

public class PagamentoDao implements Dao<Pagamento> {
	private Connection connection;

	// Constructor
	public PagamentoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Pagamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Pagamento (Valor, Servico, Descricao, Comentario" + ") values (?,?,?,?)");

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

			return -1;
		}
	}

	@Override
	public void update(Pagamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Pagamento set Valor=?, Servico=?, Descricao=?, Comentario=?  where Id=?");

			preparedStatement.setFloat(1, obj.getValor());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setString(3, obj.getDescricao());
			preparedStatement.setString(4, obj.getComentario());
			preparedStatement.setInt(5, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Pagamento obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Pagamento where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pagamento find(Pagamento obj) {
		// TODO Auto-generated method stub
		Pagamento pagamento = new Pagamento();

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
	public ArrayList<Pagamento> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Pagamento");

			while (rs.next()) {
				Pagamento pagamento = new Pagamento();

				pagamento.setId(rs.getInt("Id"));
				pagamento.setValor(rs.getFloat("Valor"));
				pagamento.setServico(rs.getInt("Servico"));
				pagamento.setDescricao(rs.getString("Descricao"));
				pagamento.setComentario(rs.getString("Comentario"));

				pagamentos.add(pagamento);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return pagamentos;
	}

}
