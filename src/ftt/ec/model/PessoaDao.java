package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.ec.beans.Pessoa;

public class PessoaDao implements Dao<Pessoa> {
	private Connection connection;

	// Constructor
	public PessoaDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Pessoa obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Pessoa (FotoId, Nome, Telefone, Email, CPF, Sexo, EnderecoId"
							+ ") values (?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, obj.getFotoId());
			preparedStatement.setString(2, obj.getNome());
			preparedStatement.setString(3, obj.getTelefone());
			preparedStatement.setString(4, obj.getEmail());
			preparedStatement.setString(5, obj.getCpf());
			preparedStatement.setString(6, obj.getSexo());
			preparedStatement.setInt(7, obj.getEnderecoId());

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
	public void update(Pessoa obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Pessoa set Nome=?, Telefone=?, Email=?, CPF=?, Sexo=?, EnderecoId=? where Id=?");

			preparedStatement.setInt(1, obj.getFotoId());
			preparedStatement.setString(2, obj.getNome());
			preparedStatement.setString(3, obj.getTelefone());
			preparedStatement.setString(4, obj.getEmail());
			preparedStatement.setString(5, obj.getCpf());
			preparedStatement.setString(6, obj.getSexo());
			preparedStatement.setInt(7, obj.getEnderecoId());
			preparedStatement.setInt(4, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Pessoa obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Pessoa where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pessoa find(Pessoa obj) {
		// TODO Auto-generated method stub
		Pessoa pessoa = new Pessoa();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Pessoa where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				pessoa.setId(rs.getInt("Id"));
				pessoa.setFotoId(rs.getInt("FotoId"));
				pessoa.setNome(rs.getString("Nome"));
				pessoa.setTelefone(rs.getString("Telefone"));
				pessoa.setEmail(rs.getString("Email"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("Sexo"));
				pessoa.setEnderecoId(rs.getInt("EnderecoId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoa;
	}

	@Override
	public ArrayList<Pessoa> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Pessoa");

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();

				pessoa.setId(rs.getInt("Id"));
				pessoa.setFotoId(rs.getInt("FotoId"));
				pessoa.setNome(rs.getString("Nome"));
				pessoa.setTelefone(rs.getString("Telefone"));
				pessoa.setEmail(rs.getString("Email"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("Sexo"));
				pessoa.setEnderecoId(rs.getInt("EnderecoId"));

				pessoas.add(pessoa);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return pessoas;
	}
}
