package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.EnderecoModel;

public class EnderecoDao implements IDao<EnderecoModel> {
	private Connection connection;

	public EnderecoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(EnderecoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Endereco (CEP, Rua, Numero, Cidade, Estado, Complemento) values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

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
			// e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
		}

		return 0;
	}

	@Override
	public int update(EnderecoModel obj) {
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
			return obj.getId();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public void delete(int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Endereco where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EnderecoModel find(EnderecoModel obj) {
		EnderecoModel endereco = new EnderecoModel();

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
	public ArrayList<EnderecoModel> findAll() {
		ArrayList<EnderecoModel> enderecos = new ArrayList<EnderecoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Endereco");

			while (rs.next()) {
				EnderecoModel endereco = new EnderecoModel();

				endereco.setId(rs.getInt("Id"));
				endereco.setCep(rs.getString("CEP"));
				endereco.setRua(rs.getString("Rua"));
				endereco.setNumero(rs.getString("Numero"));
				endereco.setCidade(rs.getString("Cidade"));
				endereco.setEstado(rs.getString("Estado"));
				endereco.setComplemento(rs.getString("Complemento"));

				enderecos.add(endereco);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return enderecos;
	}

}
