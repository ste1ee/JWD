package taxi.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import taxi.model.Poziv;
import taxi.model.dao.PozivDAO;

public class DatabasePozivDAO implements PozivDAO {

	private final Connection conn;
	
	public DatabasePozivDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void add(Poziv poziv) throws Exception {
		String sql = "INSERT INTO pozivi (datumIVreme, ulica, broj, voziloId) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(poziv.getDatumIVreme()));
			stmt.setString(++param, poziv.getUlica());
			stmt.setInt(++param, poziv.getBroj());
			stmt.setLong(++param, poziv.getVozilo().getId());

			stmt.executeUpdate();
		}
	}
}
