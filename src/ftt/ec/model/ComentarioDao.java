package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ftt.ec.beans.Comentario;

public class ComentarioDao implements Dao<Comentario> {
	private Connection connection;

	// Constructor
	public ComentarioDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Comentario obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Comentario (Descricao, Trabalho, Usuario) values (?,?,?)");

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
			
			return -1;
		}	
	}

	@Override
	public void update(Comentario obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update FTT.Comentario set Descricao=?, Trabalho=?, Usuario=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setInt(2, obj.getTrabalho());
			preparedStatement.setInt(3, obj.getUsuario());
			preparedStatement.setInt(4, obj.getId());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Comentario obj) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Comentario where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Comentario find(Comentario obj) {
		Comentario comentario = new Comentario();

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
	public ArrayList<Comentario> findAll() {
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Comentario");

			while (rs.next()) {
				Comentario comentario = new Comentario();

				comentario.setId(rs.getInt("Id"));
				comentario.setDescricao(rs.getString("Descricao"));
				comentario.setTrabalho(rs.getInt("Trabalho"));
				comentario.setUsuario(rs.getInt("Usuario"));

				comentarios.add(comentario);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return comentarios;
	}
}
