package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.TabelaGeralModel;

public class TabelaGeralDao implements IDao<TabelaGeralModel> {
	private Connection connection;

	public TabelaGeralDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(TabelaGeralModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.TabelaGeral (Descricao" + ") values (?)", Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, obj.getDescricao());

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
	public int update(TabelaGeralModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.TabelaGeral set Descricao=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.TabelaGeral where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TabelaGeralModel find(TabelaGeralModel obj) {
		TabelaGeralModel tabelaGeral = new TabelaGeralModel();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from FTT.TabelaGeral where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				tabelaGeral.setId(rs.getInt("Id"));
				tabelaGeral.setDescricao(rs.getString("Descricao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tabelaGeral;
	}

	@Override
	public ArrayList<TabelaGeralModel> findAll() {
		ArrayList<TabelaGeralModel> tabelasGerais = new ArrayList<TabelaGeralModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.TabelaGeral");

			while (rs.next()) {
				TabelaGeralModel tabelaGeral = new TabelaGeralModel();

				tabelaGeral.setId(rs.getInt("Id"));
				tabelaGeral.setDescricao(rs.getString("Descricao"));

				tabelasGerais.add(tabelaGeral);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tabelasGerais;
	}
}
