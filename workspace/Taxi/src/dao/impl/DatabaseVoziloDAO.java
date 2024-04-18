package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.VoziloDAO;
import model.Vozilo;

public class DatabaseVoziloDAO implements VoziloDAO {

	private final Connection conn;

	public DatabaseVoziloDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Vozilo get(long id) throws Exception {
		Vozilo vozilo = null;
		String sql = "SELECT broj, vozac FROM vozila WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					int broj = rset.getInt(++kolona);
					String vozac = rset.getString(++kolona);

					vozilo = new Vozilo(id, broj, vozac);
				}
			}
		}

		return vozilo;
	}

	@Override
	public Collection<Vozilo> getAll() throws Exception {
		Collection<Vozilo> vozila = new ArrayList<>();
		String sql = "SELECT id, broj, vozac FROM vozila";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					int broj = rset.getInt(++kolona);
					String vozac = rset.getString(++kolona);

					Vozilo vozilo = new Vozilo(id, broj, vozac);
					vozila.add(vozilo);
				}
			}
		}

		return vozila;
	}

}
