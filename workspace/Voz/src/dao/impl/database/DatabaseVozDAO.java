package dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.VozDAO;
import model.Karta;
import model.Voz;

public class DatabaseVozDAO implements VozDAO {

	private final Connection conn;

	public DatabaseVozDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Voz> getAll() throws Exception {
		Map<Long, Voz> vozovi = new LinkedHashMap<>();
		String sql = "SELECT v.id, v.broj, v.naziv, v.dIVPolaska, v.cenaKarte, v.brojMesta, k.id, k.dIVProdaje, k.kupac, k.razred FROM vozovi v \r\n"
				+ "JOIN karte k ON v.id = k.vozId;";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vBroj = rset.getString(++kolona);
					String vNaziv = rset.getString(++kolona);
					LocalDateTime vDIVPolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					double vCenaKarte = rset.getDouble(++kolona);
					int vBrojMesta = rset.getInt(++kolona);

					Voz voz = vozovi.get(vId);
					if (voz == null) {
						voz = new Voz(vId, vBroj, vNaziv, vDIVPolaska, vCenaKarte, vBrojMesta);
						vozovi.put(voz.getId(), voz);
					}

					long kId = rset.getLong(++kolona);
					if (kId != 0) {
						LocalDateTime kDIVProdaje = rset.getTimestamp(++kolona).toLocalDateTime();
						String kKupac = rset.getString(++kolona);
						int kRazred = rset.getInt(++kolona);

						Karta karta = new Karta(kId, voz, kDIVProdaje, kKupac, kRazred);
						voz.addKarta(karta);
					}
				}
			}
		}

		return vozovi.values();
	}

	@Override
	public Voz get(long vId) throws Exception {
		Voz voz = null;
		String sql = "SELECT v.broj, v.naziv, v.dIVPolaska, v.cenaKarte, v.brojMesta, k.id, k.dIVProdaje, k.kupac, k.razred FROM vozovi v \r\n"
				+ "JOIN karte k ON v.id = k.vozId\r\n" + "WHERE v.Id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, vId);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					String vBroj = rset.getString(++kolona);
					String vNaziv = rset.getString(++kolona);
					LocalDateTime vDIVPolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					double vCenaKarte = rset.getDouble(++kolona);
					int vBrojMesta = rset.getInt(++kolona);
					if (voz == null)
						voz = new Voz(vId, vBroj, vNaziv, vDIVPolaska, vCenaKarte, vBrojMesta);
					long kId = rset.getLong(++kolona);
					if (kId != 0) {
						LocalDateTime kDIVProdaje = rset.getTimestamp(++kolona).toLocalDateTime();
						String kKupac = rset.getString(++kolona);
						int kRazred = rset.getInt(++kolona);

						Karta karta = new Karta(kId, voz, kDIVProdaje, kKupac, kRazred);
						voz.addKarta(karta);
					}

				}
			}
		}

		return voz;
	}

	@Override
	public Voz getPoNazivu(String vNaziv) throws Exception {
		Voz voz = null;
		String sql = "SELECT v.id, v.broj, v.dIVPolaska, v.cenaKarte, v.brojMesta, k.id, k.dIVProdaje, k.kupac, k.razred FROM vozovi v \r\n"
				+ "JOIN karte k ON v.id = k.vozId\r\n" + "WHERE v.naziv = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setString(++param, vNaziv);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vBroj = rset.getString(++kolona);
					LocalDateTime vDIVPolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					double vCenaKarte = rset.getDouble(++kolona);
					int vBrojMesta = rset.getInt(++kolona);
					if (voz == null)
						voz = new Voz(vId, vBroj, vNaziv, vDIVPolaska, vCenaKarte, vBrojMesta);
					long kId = rset.getLong(++kolona);
					if (kId != 0) {
						LocalDateTime kDIVProdaje = rset.getTimestamp(++kolona).toLocalDateTime();
						String kKupac = rset.getString(++kolona);
						int kRazred = rset.getInt(++kolona);

						Karta karta = new Karta(kId, voz, kDIVProdaje, kKupac, kRazred);
						voz.addKarta(karta);
					}

				}
			}
		}

		return voz;
	}
}
