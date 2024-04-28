package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.PaketDAO;
import model.Paket;

public class DatabasePaketDAO implements PaketDAO {

	private final Connection conn;

	public DatabasePaketDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Paket get(long id) throws Exception {
		Paket paket = null;
		String sql = 
				"SELECT naziv, brtr, meseci, cena FROM paketi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					String naziv = rset.getString(++k);
					int brtr = rset.getInt(++k);
					int meseci = rset.getInt(++k);
					double cena = rset.getDouble(++k);

					paket = new Paket(id, naziv, brtr, meseci, cena);
				}
			}
		}
		return paket;
	}

	@Override
	public Paket getPoNazivu(String naziv) throws Exception {
		Paket paket = null;
		String sql = 
				"SELECT id, brtr, meseci, cena FROM paketi WHERE naziv = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setString(++param, naziv);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long id = rset.getLong(++k);
					int brtr = rset.getInt(++k);
					int meseci = rset.getInt(++k);
					double cena = rset.getDouble(++k);

					paket = new Paket(id, naziv, brtr, meseci, cena);
				}
			}
		}
		return paket;
	}

}
