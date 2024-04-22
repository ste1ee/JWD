package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.VakcinaDAO;
import model.Vakcina;

public class DatabaseVakcinaDAO implements VakcinaDAO {

	private final Connection conn;

	public DatabaseVakcinaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Vakcina> getAll() throws Exception {
		Collection<Vakcina> vakcine = new ArrayList<>();
		String sql = "SELECT id, naziv, tip, tempCuvanja FROM vakcine";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long vId = rset.getLong(++k);
					String vNaziv = rset.getString(++k);
					String vTip = rset.getString(++k);
					int vTempCuvanja = rset.getInt(++k);

					Vakcina vakcina = new Vakcina(vId, vNaziv, vTip, vTempCuvanja);
					vakcine.add(vakcina);

				}
			}
		}

		return vakcine;
	}

	@Override
	public Vakcina get(long id) throws Exception {
		Vakcina vakcina = null;
		String sql = "SELECT naziv, tip, tempCuvanja FROM vakcine WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					String vNaziv = rset.getString(++k);
					String vTip = rset.getString(++k);
					int vTempCuvanja = rset.getInt(++k);

					vakcina = new Vakcina(id, vNaziv, vTip, vTempCuvanja);

				}
			}
		}

		return vakcina;
	}

}
