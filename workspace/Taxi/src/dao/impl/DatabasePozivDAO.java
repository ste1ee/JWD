package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import dao.PozivDAO;
import model.Izvestaj;
import model.Poziv;
import model.Vozilo;

public class DatabasePozivDAO implements PozivDAO {

	private final Connection conn;

	public DatabasePozivDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Poziv get(long id) throws Exception {
		Poziv poziv = null;
		String sql = "SELECT datumIVreme, ulica, broj, voziloID FROM pozivi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					LocalDateTime datumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					String ulica = rset.getString(++kolona);
					int broj = rset.getInt(++kolona);
					long voziloId = rset.getLong(++kolona);
					Vozilo vozilo = Izvestaj.getVozila().get(voziloId);

					poziv = new Poziv(id, datumIVreme, ulica, broj, vozilo);
				}
			}
		}

		return poziv;
	}

	@Override
	public Collection<Poziv> getAll() throws Exception {
		Collection<Poziv> pozivi = new ArrayList<>();
		String sql = "SELECT id, datumIVreme, ulica, broj, voziloId FROM pozivi";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					LocalDateTime datumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					String ulica = rset.getString(++kolona);
					int broj = rset.getInt(++kolona);
					long voziloId = rset.getLong(++kolona);
					Vozilo vozilo = Izvestaj.getVozila().get(voziloId);

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
