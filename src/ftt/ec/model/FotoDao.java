package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Foto;

public class FotoDao implements Dao<Foto> {

	private Connection connection;

	// Constructor
	public FotoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Foto obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Foto (Arquivo, Extensao, Nome) values (?,?,?)");

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

			return -1;
		}
	}

	@Override
	public void update(Foto obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Foto set Arquivo=?, Extensao=?, Nome=? where Id=?");

			preparedStatement.setString(1, obj.getArquivo());
			preparedStatement.setString(2, obj.getExtensao());
			preparedStatement.setString(3, obj.getNome());
			preparedStatement.setInt(4, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Foto obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Foto where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Foto find(Foto obj) {
		// TODO Auto-generated method stub
		Foto foto = new Foto();

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
	public ArrayList<Foto> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Foto> fotos = new ArrayList<Foto>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Foto");

			while (rs.next()) {
				Foto foto = new Foto();

				foto.setId(rs.getInt("Id"));
				foto.setArquivo(rs.getString("Arquivo"));
				foto.setExtensao(rs.getString("Extensao"));
				foto.setNome(rs.getString("Nome"));

				fotos.add(foto);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return fotos;
	}

}
