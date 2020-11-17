package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.FotoModel;

public class FotoDao implements IDao<FotoModel> {
	private Connection connection;

	public FotoDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(FotoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Foto (Arquivo, Extensao, Nome) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, obj.getArquivo());
			preparedStatement.setString(2, obj.getExtensao());
			preparedStatement.setString(3, obj.getNome());

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
	public int update(FotoModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Foto set Arquivo=?, Extensao=?, Nome=? where Id=?");

			preparedStatement.setString(1, obj.getArquivo());
			preparedStatement.setString(2, obj.getExtensao());
			preparedStatement.setString(3, obj.getNome());
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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Foto where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FotoModel find(FotoModel obj) {
		FotoModel foto = new FotoModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Foto where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				foto.setId(rs.getInt("Id"));
				foto.setArquivo(rs.getString("Arquivo"));
				foto.setExtensao(rs.getString("Extensao"));
				foto.setNome(rs.getString("Nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foto;
	}

	@Override
	public ArrayList<FotoModel> findAll() {
		ArrayList<FotoModel> fotos = new ArrayList<FotoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Foto");

			while (rs.next()) {
				FotoModel foto = new FotoModel();

				foto.setId(rs.getInt("Id"));
				foto.setArquivo(rs.getString("Arquivo"));
				foto.setExtensao(rs.getString("Extensao"));
				foto.setNome(rs.getString("Nome"));

				fotos.add(foto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fotos;
	}
}
