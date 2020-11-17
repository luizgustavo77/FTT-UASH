package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.TrabalhoModel;

public class TrabalhoDao implements IDao<TrabalhoModel> {
	private Connection connection;

	public TrabalhoDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(TrabalhoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Trabalho (Colaborador, Usuario, DataAbertura, DataFechamento, Status, NotaServico"
							+ ") values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

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
		}

		return 0;
	}

	@Override
	public int update(TrabalhoModel obj) {
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
			return obj.getId();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public void delete(int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Trabalho where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TrabalhoModel find(TrabalhoModel obj) {
		TrabalhoModel trabalho = new TrabalhoModel();

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
	public ArrayList<TrabalhoModel> findAll() {
		ArrayList<TrabalhoModel> trabalhos = new ArrayList<TrabalhoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Trabalho");

			while (rs.next()) {
				TrabalhoModel trabalho = new TrabalhoModel();

				trabalho.setId(rs.getInt("Id"));
				trabalho.setColaborador(rs.getInt("Colaborador"));
				trabalho.setUsuario(rs.getInt("Usuario"));
				trabalho.setDataAbertura(rs.getDate("DataAbertura"));
				trabalho.setDataFechamento(rs.getDate("DataFechamento"));
				trabalho.setStatus(rs.getInt("Status"));
				trabalho.setNotaServico(rs.getInt("NotaServico"));

				trabalhos.add(trabalho);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trabalhos;
	}
}
