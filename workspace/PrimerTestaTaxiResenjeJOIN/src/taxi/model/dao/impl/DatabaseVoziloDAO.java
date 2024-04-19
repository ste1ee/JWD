package taxi.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.model.dao.VoziloDAO;

public class DatabaseVoziloDAO implements VoziloDAO {

	private final Connection conn;
	
	public DatabaseVoziloDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Vozilo> getAll() throws Exception {
		Map<Long, Vozilo> vozila = new LinkedHashMap<>();
		
		String sql = 
				"SELECT v.id, v.broj, v.vozac, p.id, p.datumIVreme, p.ulica, p.broj FROM vozila v " + 
				"JOIN pozivi p ON v.id = p.voziloId";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vBroj = rset.getString(++kolona);
					String vVozac = rset.getString(++kolona);
					
					Vozilo vozilo = vozila.get(vId);
					if (vozilo == null) {
						vozilo = new Vozilo(vId, vBroj, vVozac);
						vozila.put(vozilo.getId(), vozilo);
					}
					long pId = rset.getLong(++kolona);
					if (pId != 0) {
						LocalDateTime pDatumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
						String pUlica = rset.getString(++kolona);
						int pBroj = rset.getInt(++kolona);

						Poziv poziv = new Poziv(pId, pDatumIVreme, pUlica, pBroj, vozilo);
						vozilo.addPoziv(poziv);
					}

				}
			}
		}
		return vozila.values();
	}
	
	@Override
	public Vozilo get(String vBroj) throws Exception {
		Vozilo vozilo = null;
		
		String sql = 
				"SELECT v.id, v.vozac, p.id, p.datumIVreme, p.ulica, p.broj FROM vozila v " + 
				"JOIN pozivi p ON v.id = p.voziloId " + 
				"WHERE v.broj = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, vBroj);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vVozac = rset.getString(++kolona);

					if (vozilo == null) {
						vozilo = new Vozilo(vId, vBroj, vVozac);
					}
					long pId = rset.getLong(++kolona);
					if (pId != 0) {
						LocalDateTime pDatumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
						String pUlica = rset.getString(++kolona);
						int pBroj = rset.getInt(++kolona);
	
						Poziv poziv = new Poziv(pId, pDatumIVreme, pUlica, pBroj, vozilo);
						vozilo.addPoziv(poziv);
					}
				}
			}
		}
		return vozilo;
	}

}
