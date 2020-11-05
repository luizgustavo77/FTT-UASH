package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Orcamento;

public class OrcamentoDao implements Dao<Orcamento> {

	private Connection connection;

	// Constructor
	public OrcamentoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Orcamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Orcamento (PagamentoId, EquipamentoId, TrabalhoId, Lavagens, FormaPagamento, Pago, Comentario"
							+ ") values (?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, obj.getPagamentoId());
			preparedStatement.setInt(2, obj.getEquipamentoId());
			preparedStatement.setInt(3, obj.getTrabalhoId());
			preparedStatement.setInt(4, obj.getLavagens());
			preparedStatement.setInt(5, obj.getFormaPagamento());
			preparedStatement.setBoolean(6, obj.isPago());
			preparedStatement.setString(7, obj.getComentarioId());

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
	public void update(Orcamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Orcamento set PagamentoId=?, EquipamentoId=?, TrabalhoId=?, Lavagens=?, FormaPagamento=?, Pago=?, Comentario=? where Id=?");

			preparedStatement.setInt(1, obj.getPagamentoId());
			preparedStatement.setInt(2, obj.getEquipamentoId());
			preparedStatement.setInt(3, obj.getTrabalhoId());
			preparedStatement.setInt(4, obj.getLavagens());
			preparedStatement.setInt(5, obj.getFormaPagamento());
			preparedStatement.setBoolean(6, obj.isPago());
			preparedStatement.setString(7, obj.getComentarioId());
			preparedStatement.setInt(8, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Orcamento obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Orcamento where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Orcamento find(Orcamento obj) {
		// TODO Auto-generated method stub
		Orcamento orcamento = new Orcamento();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Orcamento where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				orcamento.setId(rs.getInt("Id"));
				orcamento.setPagamentoId(rs.getInt("PagamentoId"));
				orcamento.setEquipamentoId(rs.getInt("EquipamentoId"));
				orcamento.setTrabalhoId(rs.getInt("TrabalhoId"));
				orcamento.setLavagens(rs.getInt("Lavagens"));
				orcamento.setFormaPagamento(rs.getInt("FormaPagamento"));
				orcamento.setPago(rs.getBoolean("Pago"));
				orcamento.setComentarioId(rs.getString("Comentario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orcamento;
	}

	@Override
	public ArrayList<Orcamento> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Orcamento> orcamentos = new ArrayList<Orcamento>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Orcamento");

			while (rs.next()) {
				Orcamento orcamento = new Orcamento();

				orcamento.setId(rs.getInt("Id"));
				orcamento.setPagamentoId(rs.getInt("PagamentoId"));
				orcamento.setEquipamentoId(rs.getInt("EquipamentoId"));
				orcamento.setTrabalhoId(rs.getInt("TrabalhoId"));
				orcamento.setLavagens(rs.getInt("Lavagens"));
				orcamento.setFormaPagamento(rs.getInt("FormaPagamento"));
				orcamento.setPago(rs.getBoolean("Pago"));
				orcamento.setComentarioId(rs.getString("Comentario"));

				orcamentos.add(orcamento);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return orcamentos;
	}

}
