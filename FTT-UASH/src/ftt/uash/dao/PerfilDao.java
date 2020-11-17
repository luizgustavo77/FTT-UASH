package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.PerfilModel;

public class PerfilDao implements IDao<PerfilModel> {
	private Connection connection;

	public PerfilDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(PerfilModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Perfil (Tipo, Servico, Disponivel" + ") values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

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
		}

		return 0;
	}

	@Override
	public int update(PerfilModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Perfil set Tipo=?, Servico=?, Disponivel=? where Id=?");

			preparedStatement.setInt(1, obj.getTipo());
			preparedStatement.setInt(2, obj.getServico());
			preparedStatement.setInt(3, obj.getDisponivel());
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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Perfil where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PerfilModel find(PerfilModel obj) {
		PerfilModel perfil = new PerfilModel();

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
	public ArrayList<PerfilModel> findAll() {
		ArrayList<PerfilModel> perfis = new ArrayList<PerfilModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Perfil");

			while (rs.next()) {
				PerfilModel perfil = new PerfilModel();

				perfil.setId(rs.getInt("Id"));
				perfil.setTipo(rs.getInt("Tipo"));
				perfil.setServico(rs.getInt("Servico"));
				perfil.setDisponivel(rs.getInt("Disponivel"));

				perfis.add(perfil);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return perfis;
	}
}
