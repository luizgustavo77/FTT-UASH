package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Trabalho;

public class TrabalhoDao implements Dao<Trabalho> {
	private Connection connection;

	// Constructor
	public TrabalhoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Trabalho obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Trabalho (Colaborador, Usuario, DataAbertura, DataFechamento, Status, NotaServico"
							+ ") values (?,?,?,?,?,?)");

			preparedStatement.setInt(1, obj.getColaborador());
			preparedStatement.setInt(1, obj.getUsuario());
			preparedStatement.setDate(1, (java.sql.Date) obj.getDataAbertura());
			preparedStatement.setDate(1, (java.sql.Date) obj.getDataFechamento());
			preparedStatement.setInt(1, obj.getStatus());
			preparedStatement.setInt(1, obj.getNotaServico());

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
	public void update(Trabalho obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Trabalho set Colaborador=?, Usuario=?, DataAbertura=?, DataFechamento=?, Status=?, NotaServico=? where Id=?");

			preparedStatement.setInt(1, obj.getColaborador());
			preparedStatement.setInt(1, obj.getUsuario());
			preparedStatement.setDate(1, (java.sql.Date) obj.getDataAbertura());
			preparedStatement.setDate(1, (java.sql.Date) obj.getDataFechamento());
			preparedStatement.setInt(1, obj.getStatus());
			preparedStatement.setInt(1, obj.getNotaServico());
			preparedStatement.setInt(2, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Trabalho obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Trabalho where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Trabalho find(Trabalho obj) {
		// TODO Auto-generated method stub
		Trabalho trabalho = new Trabalho();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Trabalho where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				trabalho.setId(rs.getInt("Id"));
				trabalho.setColaborador(rs.getInt("Colaborador"));
				trabalho.setUsuario(rs.getInt("Usuario"));
				trabalho.setDataAbertura(rs.getDate("DataAbertura"));
				trabalho.setDataFechamento(rs.getDate("DataFechamento"));
				trabalho.setStatus(rs.getInt("Status"));
				trabalho.setNotaServico(rs.getInt("NotaServico"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trabalho;
	}

	@Override
	public ArrayList<Trabalho> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Trabalho> trabalhos = new ArrayList<Trabalho>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Trabalho");

			while (rs.next()) {
				Trabalho trabalho = new Trabalho();

				trabalho.setId(rs.getInt("Id"));
				trabalho.setColaborador(rs.getInt("Colaborador"));
				trabalho.setUsuario(rs.getInt("Usuario"));
				trabalho.setDataAbertura(rs.getDate("DataAbertura"));
				trabalho.setDataFechamento(rs.getDate("DataFechamento"));
				trabalho.setStatus(rs.getInt("Status"));
				trabalho.setNotaServico(rs.getInt("NotaServico"));

				trabalhos.add(trabalho);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return trabalhos;
	}

}
