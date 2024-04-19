package taxi.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.model.dao.PozivDAO;

public class DatabasePozivDAO implements PozivDAO {

	private final Connection conn;
	
	public DatabasePozivDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Poziv> find(Vozilo vozilo) throws Exception {
		Collection<Poziv> pozivi = new ArrayList<>();

		String sql = "SELECT id, datumIVreme, ulica, broj FROM pozivi WHERE voziloId = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, vozilo.getId());
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					LocalDateTime datumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					String ulica = rset.getString(++kolona);
					int broj = rset.getInt(++kolona);
					
					Poziv poziv = new Poziv(id, datumIVreme, ulica, broj, vozilo);
					pozivi.add(poziv);
				}
			}
		}
		return pozivi;
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
