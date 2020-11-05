package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ftt.ec.beans.Equipamento;

public class EquipamentoDao implements Dao<Equipamento> {
	private Connection connection;

	// Constructor
	public EquipamentoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Equipamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Equipamento (Modelo, Marca, Comentario, Colaborador, Situacao, Funcoes) values (?,?,?,?,?,?)");

			preparedStatement.setString(1, obj.getModelo());
			preparedStatement.setString(2, obj.getMarca());
			preparedStatement.setString(3, obj.getComentario());
			preparedStatement.setInt(4, obj.getColaborador());
			preparedStatement.setInt(5, obj.getSituacao());
			preparedStatement.setInt(6, obj.getFuncoes());

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
	public void update(Equipamento obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Equipamento set Modelo=?, Marca=?, Comentario=?, Colaborador=?, Situacao=?, Funcoes=? where Id=?");

			preparedStatement.setString(1, obj.getModelo());
			preparedStatement.setString(2, obj.getMarca());
			preparedStatement.setString(3, obj.getComentario());
			preparedStatement.setInt(4, obj.getColaborador());
			preparedStatement.setInt(5, obj.getSituacao());
			preparedStatement.setInt(6, obj.getFuncoes());
			preparedStatement.setInt(7, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Equipamento obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Equipamento where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Equipamento find(Equipamento obj) {
		// TODO Auto-generated method stub
		Equipamento equipamento = new Equipamento();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from FTT.Equipamento where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				equipamento.setId(rs.getInt("Id"));
				equipamento.setModelo(rs.getString("Modelo"));
				equipamento.setMarca(rs.getString("Marca"));
				equipamento.setComentario(rs.getString("Comentario"));
				equipamento.setColaborador(rs.getInt("Colaborador"));
				equipamento.setSituacao(rs.getInt("Situacao"));
				equipamento.setFuncoes(rs.getInt("Funcoes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return equipamento;
	}

	@Override
	public ArrayList<Equipamento> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Equipamento");

			while (rs.next()) {
				Equipamento equipamento = new Equipamento();

				equipamento.setId(rs.getInt("Id"));
				equipamento.setModelo(rs.getString("Modelo"));
				equipamento.setMarca(rs.getString("Marca"));
				equipamento.setComentario(rs.getString("Comentario"));
				equipamento.setColaborador(rs.getInt("Colaborador"));
				equipamento.setSituacao(rs.getInt("Situacao"));
				equipamento.setFuncoes(rs.getInt("Funcoes"));

				equipamentos.add(equipamento);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return equipamentos;
	}

}
