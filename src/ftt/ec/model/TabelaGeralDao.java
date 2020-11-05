package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.TabelaGeral;

public class TabelaGeralDao implements Dao<TabelaGeral> {
	private Connection connection;

	// Constructor
	public TabelaGeralDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(TabelaGeral obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.TabelaGeral (Descricao" + ") values (?)");

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

			return -1;
		}

	}

	@Override
	public void update(TabelaGeral obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.TabelaGeral set Descricao=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setInt(2, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TabelaGeral obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.TabelaGeral where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TabelaGeral find(TabelaGeral obj) {
		// TODO Auto-generated method stub
		TabelaGeral tabelaGeral = new TabelaGeral();

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
	public ArrayList<TabelaGeral> findAll() {
		// TODO Auto-generated method stub
		ArrayList<TabelaGeral> tabelasGerais = new ArrayList<TabelaGeral>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.TabelaGeral");

			while (rs.next()) {
				TabelaGeral tabelaGeral = new TabelaGeral();

				tabelaGeral.setId(rs.getInt("Id"));
				tabelaGeral.setDescricao(rs.getString("Descricao"));

				tabelasGerais.add(tabelaGeral);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return tabelasGerais;
	}
}
