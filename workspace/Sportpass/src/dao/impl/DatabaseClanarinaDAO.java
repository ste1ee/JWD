package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import dao.ClanarinaDAO;
import model.Clanarina;
import model.Paket;

public class DatabaseClanarinaDAO implements ClanarinaDAO {

	private final Connection conn;

	public DatabaseClanarinaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Clanarina> getAll() throws Exception {
		Collection<Clanarina> clanarine = new ArrayList<>();
		String sql = "SELECT c.id, c.paketId, c.korisnik, c.pocetak, p.naziv, p.brtr, p.meseci, p.cena FROM clanarine c\r\n"
				+ "JOIN paketi p ON c.paketId = p.id";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long cId = rset.getLong(++k);
					long cPaketId = rset.getLong(++k);
					String cKorisnik = rset.getString(++k);
					LocalDate cPocetak = rset.getDate(++k).toLocalDate();

					if (cPaketId != 0) {
						String pNaziv = rset.getString(++k);
						int pBrtr = rset.getInt(++k);
						int pMeseci = rset.getInt(++k);
						double pCena = rset.getDouble(++k);

						Paket paket = new Paket(cPaketId, pNaziv, pBrtr, pMeseci, pCena);

						Clanarina clanarina = new Clanarina(cId, paket, cKorisnik, cPocetak);
						clanarine.add(clanarina);
					}
				}
			}
		}
		return clanarine;
	}

	@Override
	public void add(Clanarina clanarina) throws Exception {
		String sql = "INSERT INTO clanarine (paketId, korisnik, pocetak) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, clanarina.getPaket().getId());
			stmt.setString(++param, clanarina.getKorisnickoIme());
			stmt.setDate(++param, Date.valueOf(clanarina.getPocetak()));

			stmt.executeUpdate();
		}
	}

}
