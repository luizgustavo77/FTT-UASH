package ftt.uash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ftt.uash.dao.interfaces.IDao;
import ftt.uash.model.OrcamentoModel;

public class OrcamentoDao implements IDao<OrcamentoModel> {
	private Connection connection;

	public OrcamentoDao() {
		connection = DbUtil.getConnection();
	}

	@Override
	public int insert(OrcamentoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into FTT.Orcamento (PagamentoId, EquipamentoId, TrabalhoId, Lavagens, FormaPagamento, Pago, Comentario"
							+ ") values (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, obj.getPagamentoId());
			preparedStatement.setInt(2, obj.getEquipamentoId());
			preparedStatement.setInt(3, obj.getTrabalhoId());
			preparedStatement.setInt(4, obj.getLavagens());
			preparedStatement.setInt(5, obj.getFormaPagamento());
			preparedStatement.setBoolean(6, obj.isPago());
			preparedStatement.setString(7, obj.getComentario());

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
	public int update(OrcamentoModel obj) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update FTT.Orcamento set PagamentoId=?, EquipamentoId=?, TrabalhoId=?, Lavagens=?, FormaPagamento=?, Pago=?, Comentario=? where Id=?");

			preparedStatement.setInt(1, obj.getPagamentoId());
			preparedStatement.setInt(2, obj.getEquipamentoId());
			preparedStatement.setInt(3, obj.getTrabalhoId());
			preparedStatement.setInt(4, obj.getLavagens());
			preparedStatement.setInt(5, obj.getFormaPagamento());
			preparedStatement.setBoolean(6, obj.isPago());
			preparedStatement.setString(7, obj.getComentario());
			preparedStatement.setInt(8, obj.getId());

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

			PreparedStatement preparedStatement = connection.prepareStatement("delete from FTT.Orcamento where Id=?");

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrcamentoModel find(OrcamentoModel obj) {
		OrcamentoModel orcamento = new OrcamentoModel();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FTT.Orcamento where Id=?");

			preparedStatement.setLong(1, obj.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				orcamento.setId(rs.getInt("Id"));
				orcamento.setPagamentoId(rs.getInt("PagamentoId"));
				orcamento.setEquipamentoId(rs.getInt("EquipamentoId"));
				orcamento.setTrabalhoId(rs.getInt("TrabalhoId"));
				orcamento.setLavagens(rs.getInt("Lavagens"));
				orcamento.setFormaPagamento(rs.getInt("FormaPagamento"));
				orcamento.setPago(rs.getBoolean("Pago"));
				orcamento.setComentario(rs.getString("Comentario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orcamento;
	}

	@Override
	public ArrayList<OrcamentoModel> findAll() {
		ArrayList<OrcamentoModel> orcamentos = new ArrayList<OrcamentoModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM FTT.Orcamento");

			while (rs.next()) {
				OrcamentoModel orcamento = new OrcamentoModel();

				orcamento.setId(rs.getInt("Id"));
				orcamento.setPagamentoId(rs.getInt("PagamentoId"));
				orcamento.setEquipamentoId(rs.getInt("EquipamentoId"));
				orcamento.setTrabalhoId(rs.getInt("TrabalhoId"));
				orcamento.setLavagens(rs.getInt("Lavagens"));
				orcamento.setFormaPagamento(rs.getInt("FormaPagamento"));
				orcamento.setPago(rs.getBoolean("Pago"));
				orcamento.setComentario(rs.getString("Comentario"));

				orcamentos.add(orcamento);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orcamentos;
	}
}
