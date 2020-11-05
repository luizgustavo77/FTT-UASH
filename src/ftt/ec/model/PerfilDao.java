package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Perfil;

public class PerfilDao implements Dao<Perfil> {
	private Connection connection;

	// Constructor
	public PerfilDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Perfil obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Perfil (Tipo, Servico, Disponivel" + ") values (?,?,?)");

			preparedStatement.setInt(1, obj.getTipo());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setInt(3, obj.getDisponivel());

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
	public void update(Perfil obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Perfil set Tipo=?, Servico=?, Disponivel=? where Id=?");

			preparedStatement.setInt(1, obj.getTipo());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setInt(3, obj.getDisponivel());
			preparedStatement.setInt(4, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Perfil obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Perfil where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Perfil find(Perfil obj) {
		// TODO Auto-generated method stub
		Perfil perfil = new Perfil();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Perfil where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				perfil.setId(rs.getInt("Id"));
				perfil.setTipo(rs.getInt("Tipo"));
				perfil.setServico(rs.getInt("Servico"));
				perfil.setDisponivel(rs.getInt("Disponivel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return perfil;
	}

	@Override
	public ArrayList<Perfil> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Perfil> perfis = new ArrayList<Perfil>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Perfil");

			while (rs.next()) {
				Perfil perfil = new Perfil();

				perfil.setId(rs.getInt("Id"));
				perfil.setTipo(rs.getInt("Tipo"));
				perfil.setServico(rs.getInt("Servico"));
				perfil.setDisponivel(rs.getInt("Disponivel"));

				perfis.add(perfil);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return perfis;
	}

}
