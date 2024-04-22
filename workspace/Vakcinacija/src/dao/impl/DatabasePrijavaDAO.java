package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import dao.PrijavaDAO;
import model.Prijava;
import model.Vakcina;

public class DatabasePrijavaDAO implements PrijavaDAO {

	private final Connection conn;

	public DatabasePrijavaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Prijava> getAll() throws Exception {
		Collection<Prijava> prijave = new ArrayList<>();
		String sql = "SELECT p.id, p.jmbg, p.imeIPrezime, p.datum, v.id, v.naziv, v.tip, v.tempCuvanja FROM prijave p\r\n"
				+ "JOIN vakcine v ON v.id = p.vakcinaId";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long pId = rset.getLong(++k);
					String pJmbg = rset.getString(++k);
					String pImeIPrezime = rset.getString(++k);
					LocalDate pDatum = rset.getDate(++k).toLocalDate();

					long vId = rset.getLong(++k);
					String vNaziv = rset.getString(++k);
					String vTip = rset.getString(++k);
					int vTempCuvanja = rset.getInt(++k);

					Vakcina vakcina = new Vakcina(vId, vNaziv, vTip, vTempCuvanja);

					Prijava prijava = new Prijava(pId, pJmbg, pImeIPrezime, pDatum);
					prijava.setVakcina(vakcina);
					prijave.add(prijava);
				}
			}
		}

		return prijave;
	}

	@Override
	public void add(Prijava prijava) throws Exception {
		String sql = "INSERT INTO prijave (jmbg, imeIPrezime, vakcinaId, datum) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, prijava.getJmbg());
			stmt.setString(++p, prijava.getImeIPrezime());
			stmt.setLong(++p, prijava.getVakcina().getId());
			stmt.setDate(++p, Date.valueOf(prijava.getDatum()));

			stmt.executeUpdate();
		}

	}

}
