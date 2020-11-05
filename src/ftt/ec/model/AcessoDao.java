package ftt.ec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ftt.ec.beans.Acesso;

public class AcessoDao implements Dao<Acesso> {

	private Connection connection;

	// Constructor
	public AcessoDao() {
		this.connection = DbUtil.getConnection();
	}

	@Override
	public int insert(Acesso obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into FTT.Acesso (Usuario, Senha, PessoaId, PerfilId) values (?,?,?,?)");

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
			
			return -1;
		}	
	}

	@Override
	public void update(Acesso obj) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update FTT.Acesso set Senha=? where Id=?");

			preparedStatement.setString(1, obj.getSenha());
			preparedStatement.setInt(2, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Acesso obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Acesso where Id=?");

			preparedStatement.setInt(1, obj.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Acesso find(Acesso obj) {
		Acesso acesso = new Acesso();

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
	public ArrayList<Acesso> findAll() {
		ArrayList<Acesso> acessos = new ArrayList<Acesso>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Acesso");

			while (rs.next()) {
				Acesso acesso = new Acesso();

				acesso.setId(rs.getInt("Id"));
				acesso.setUsuario(rs.getString("Usuario"));
				acesso.setSenha(rs.getString("Senha"));
				acesso.setPessoaId(rs.getInt("PessoaId"));
				acesso.setPerfilId(rs.getInt("PerfilId"));

				acessos.add(acesso);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try

		return acessos;
	}

}
