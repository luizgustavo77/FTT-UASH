package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.AcessoModel;

public class AcessoDao implements IDao<AcessoModel> {
	private Connection connection;

	public AcessoDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(AcessoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Acesso (Usuario, Senha, PessoaId, PerfilId) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, obj.getUsuario());
			preparedStatement.setString(2, obj.getSenha());
			preparedStatement.setInt(3, obj.getPessoaId());
			preparedStatement.setInt(4, obj.getPerfilId());

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
	public int update(AcessoModel obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Acesso set Senha=? where Id=?");

			preparedStatement.setString(1, obj.getSenha());
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
			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Acesso where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AcessoModel find(AcessoModel obj) {
		AcessoModel acesso = new AcessoModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Acesso where Id=?");
			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				acesso.setId(rs.getInt("Id"));
				acesso.setUsuario(rs.getString("Usuario"));
				acesso.setSenha(rs.getString("Senha"));
				acesso.setPerfilId(rs.getInt("PessoaId"));
				acesso.setPerfilId(rs.getInt("PerfilId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return acesso;
	}

	@Override
	public ArrayList<AcessoModel> findAll() {
		ArrayList<AcessoModel> acessos = new ArrayList<AcessoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Acesso");

			while (rs.next()) {
				AcessoModel acesso = new AcessoModel();

				acesso.setId(rs.getInt("Id"));
				acesso.setUsuario(rs.getString("Usuario"));
				acesso.setSenha(rs.getString("Senha"));
				acesso.setPessoaId(rs.getInt("PessoaId"));
				acesso.setPerfilId(rs.getInt("PerfilId"));

				acessos.add(acesso);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return acessos;
	}
	
	public boolean findByUserAndPassword(AcessoModel obj) {
		AcessoModel acesso = new AcessoModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Acesso where Usuario=? and Senha=?");
			preparedStatement.setString(1, obj.getUsuario());
			preparedStatement.setString(2, obj.getSenha());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				acesso.setId(rs.getInt("Id"));
				acesso.setUsuario(rs.getString("Usuario"));
				acesso.setSenha(rs.getString("Senha"));
				acesso.setPerfilId(rs.getInt("PessoaId"));
				acesso.setPerfilId(rs.getInt("PerfilId"));
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
