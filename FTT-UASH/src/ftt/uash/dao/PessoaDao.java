package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.PessoaModel;

public class PessoaDao implements IDao<PessoaModel> {
	private Connection connection;

	public PessoaDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(PessoaModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Pessoa (FotoId, Nome, Telefone, Email, CPF, Sexo, EnderecoId"
							+ ") values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

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
		}

		return 0;
	}

	@Override
	public int update(PessoaModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Pessoa set Nome=?, Telefone=?, Email=?, CPF=?, Sexo=? where Id=?");

			preparedStatement.setInt(1, obj.getFotoId());
			preparedStatement.setString(2, obj.getNome());
			preparedStatement.setString(3, obj.getTelefone());
			preparedStatement.setString(4, obj.getEmail());
			preparedStatement.setString(5, obj.getCpf());
			preparedStatement.setString(6, obj.getSexo());
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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Pessoa where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PessoaModel find(PessoaModel obj) {
		PessoaModel pessoa = new PessoaModel();

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
	public ArrayList<PessoaModel> findAll() {
		ArrayList<PessoaModel> pessoas = new ArrayList<PessoaModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Pessoa");

			while (rs.next()) {
				PessoaModel pessoa = new PessoaModel();

				pessoa.setId(rs.getInt("Id"));
				pessoa.setFotoId(rs.getInt("FotoId"));
				pessoa.setNome(rs.getString("Nome"));
				pessoa.setTelefone(rs.getString("Telefone"));
				pessoa.setEmail(rs.getString("Email"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("Sexo"));
				pessoa.setEnderecoId(rs.getInt("EnderecoId"));

				pessoas.add(pessoa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoas;
	}
}
