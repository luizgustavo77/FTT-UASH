package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ftt.ec.beans.Endereco;

public class EnderecoDao implements Dao<Endereco> {
	private Connection connection;

	// Constructor
	public EnderecoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Endereco obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Endereco (CEP, Rua, Numero, Cidade, Estado, Complemento) values (?,?,?,?,?,?)");

			preparedStatement.setString(1, obj.getCep());
			preparedStatement.setString(2, obj.getRua());
			preparedStatement.setString(3, obj.getNumero());
			preparedStatement.setString(4, obj.getCidade());
			preparedStatement.setString(5, obj.getEstado());
			preparedStatement.setString(6, obj.getComplemento());

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
	public void update(Endereco obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Endereco set CEP=?, Rua=?, Numero=?, Cidade=?, Estado=?, Complemento=? where Id=?");

			preparedStatement.setString(1, obj.getCep());
			preparedStatement.setString(2, obj.getRua());
			preparedStatement.setString(3, obj.getNumero());
			preparedStatement.setString(4, obj.getCidade());
			preparedStatement.setString(5, obj.getEstado());
			preparedStatement.setString(6, obj.getComplemento());
			preparedStatement.setInt(7, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Endereco obj) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Endereco where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Endereco find(Endereco obj) {
		Endereco endereco = new Endereco();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Endereco where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				endereco.setId(rs.getInt("Id"));
				endereco.setCep(rs.getString("CEP"));
				endereco.setRua(rs.getString("Rua"));
				endereco.setNumero(rs.getString("Numero"));
				endereco.setCidade(rs.getString("Cidade"));
				endereco.setEstado(rs.getString("Estado"));
				endereco.setComplemento(rs.getString("Complemento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return endereco;
	}

	@Override
	public ArrayList<Endereco> findAll() {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Endereco");

			while (rs.next()) {
				Endereco endereco = new Endereco();

				endereco.setId(rs.getInt("Id"));
				endereco.setCep(rs.getString("CEP"));
				endereco.setRua(rs.getString("Rua"));
				endereco.setNumero(rs.getString("Numero"));
				endereco.setCidade(rs.getString("Cidade"));
				endereco.setEstado(rs.getString("Estado"));
				endereco.setComplemento(rs.getString("Complemento"));

				enderecos.add(endereco);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return enderecos;
	}

}
