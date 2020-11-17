package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.ItemTabelaGeralModel;

public class ItemTabelaGeralDao implements IDao<ItemTabelaGeralModel> {
	private Connection connection;

	public ItemTabelaGeralDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(ItemTabelaGeralModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.ItemTabelaGeral (Descricao, Sigla, TabelaGeralId) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

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
		}

		return 0;
	}

	@Override
	public int update(ItemTabelaGeralModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.ItemTabelaGeral set Descricao=?, Sigla=?, TabelaGeralId=? where Id=?");

			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setString(2, obj.getSigla());
			preparedStatement.setInt(3, obj.getTabelaGeralId());
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

			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from FTT.ItemTabelaGeral where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ItemTabelaGeralModel find(ItemTabelaGeralModel obj) {
		ItemTabelaGeralModel itemTabelaGeral = new ItemTabelaGeralModel();

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
	public ArrayList<ItemTabelaGeralModel> findAll() {
		ArrayList<ItemTabelaGeralModel> itensTabelaGeral = new ArrayList<ItemTabelaGeralModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.ItemTabelaGeral");

			while (rs.next()) {
				ItemTabelaGeralModel itemTabelaGeral = new ItemTabelaGeralModel();

				itemTabelaGeral.setId(rs.getInt("Id"));
				itemTabelaGeral.setDescricao(rs.getString("Descricao"));
				itemTabelaGeral.setSigla(rs.getString("Sigla"));
				itemTabelaGeral.setTabelaGeralId(rs.getInt("TabelaGeralId"));

				itensTabelaGeral.add(itemTabelaGeral);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itensTabelaGeral;
	}
}
