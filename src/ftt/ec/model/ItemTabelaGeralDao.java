package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.ItemTabelaGeral;

public class ItemTabelaGeralDao implements Dao<ItemTabelaGeral> {

	private Connection connection;

	// Constructor
	public ItemTabelaGeralDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(ItemTabelaGeral obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.ItemTabelaGeral (Descricao, Sigla, TabelaGeralId) values (?,?,?)");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setString(2, obj.getSigla());
			preparedStatement.setInt(3, obj.getTabelaGeralId());

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
	public void update(ItemTabelaGeral obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.ItemTabelaGeral set Descricao=?, Sigla=?, TabelaGeralId=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setString(2, obj.getSigla());
			preparedStatement.setInt(3, obj.getTabelaGeralId());
			preparedStatement.setInt(4, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ItemTabelaGeral obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from FTT.ItemTabelaGeral where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ItemTabelaGeral find(ItemTabelaGeral obj) {
		// TODO Auto-generated method stub
		ItemTabelaGeral itemTabelaGeral = new ItemTabelaGeral();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from FTT.ItemTabelaGeral where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				itemTabelaGeral.setId(rs.getInt("Id"));
				itemTabelaGeral.setDescricao(rs.getString("Descricao"));
				itemTabelaGeral.setSigla(rs.getString("Sigla"));
				itemTabelaGeral.setTabelaGeralId(rs.getInt("TabelaGeralId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTabelaGeral;
	}

	@Override
	public ArrayList<ItemTabelaGeral> findAll() {
		// TODO Auto-generated method stub
		ArrayList<ItemTabelaGeral> itensTabelaGeral = new ArrayList<ItemTabelaGeral>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.ItemTabelaGeral");

			while (rs.next()) {
				ItemTabelaGeral itemTabelaGeral = new ItemTabelaGeral();

				itemTabelaGeral.setId(rs.getInt("Id"));
				itemTabelaGeral.setDescricao(rs.getString("Descricao"));
				itemTabelaGeral.setSigla(rs.getString("Sigla"));
				itemTabelaGeral.setTabelaGeralId(rs.getInt("TabelaGeralId"));

				itensTabelaGeral.add(itemTabelaGeral);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return itensTabelaGeral;
	}

}
