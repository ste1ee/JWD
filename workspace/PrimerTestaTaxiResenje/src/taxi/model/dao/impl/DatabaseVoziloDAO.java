package taxi.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.model.dao.PozivDAO;
import taxi.model.dao.VoziloDAO;

public class DatabaseVoziloDAO implements VoziloDAO {

	private final Connection conn;
	private PozivDAO pozivDAO;
	
	public DatabaseVoziloDAO(Connection conn, PozivDAO pozivDAO) {
		this.conn = conn;
		this.pozivDAO = pozivDAO;
	}

	@Override
	public Vozilo get(String broj) throws Exception {
		Vozilo vozilo = null;

		String sql = "SELECT id, vozac FROM vozila WHERE broj = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setString(++param, broj);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String vozac = rset.getString(++kolona);

					vozilo = new Vozilo(id, broj, vozac);
					
					Collection<Poziv> pozivi = pozivDAO.find(vozilo);
					vozilo.addAllPozivi(pozivi);
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
					String broj = rset.getString(++kolona);
					String vozac = rset.getString(++kolona);
					
					Vozilo vozilo = new Vozilo(id, broj, vozac);
					vozila.add(vozilo);
					
					Collection<Poziv> pozivi = pozivDAO.find(vozilo);
					vozilo.addAllPozivi(pozivi);
				}
			}
		}
		return vozila;
	}

}
