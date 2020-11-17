package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.ComentarioModel;

public class ComentarioDao implements IDao<ComentarioModel> {
	private Connection connection;

	public ComentarioDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(ComentarioModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Comentario (Descricao, Trabalho, Usuario) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setInt(2, obj.getTrabalho());
			preparedStatement.setInt(3, obj.getUsuario());

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
	public int update(ComentarioModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Comentario set Descricao=?, Trabalho=?, Usuario=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setInt(2, obj.getTrabalho());
			preparedStatement.setInt(3, obj.getUsuario());
			preparedStatement.setInt(4, obj.getId());

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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Comentario where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ComentarioModel find(ComentarioModel obj) {
		ComentarioModel comentario = new ComentarioModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Comentario where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				comentario.setId(rs.getInt("Id"));
				comentario.setDescricao(rs.getString("Descricao"));
				comentario.setTrabalho(rs.getInt("Trabalho"));
				comentario.setUsuario(rs.getInt("Usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comentario;
	}

	@Override
	public ArrayList<ComentarioModel> findAll() {
		ArrayList<ComentarioModel> comentarios = new ArrayList<ComentarioModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Comentario");

			while (rs.next()) {
				ComentarioModel comentario = new ComentarioModel();

				comentario.setId(rs.getInt("Id"));
				comentario.setDescricao(rs.getString("Descricao"));
				comentario.setTrabalho(rs.getInt("Trabalho"));
				comentario.setUsuario(rs.getInt("Usuario"));

				comentarios.add(comentario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comentarios;
	}
}
