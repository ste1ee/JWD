package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ZanrDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Zanr;

public class DatabaseZanrDAO implements ZanrDAO {

	private final Connection conn;

	public DatabaseZanrDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Zanr get(long id) throws Exception {
		Zanr zanr = null;

		String sql = "SELECT naziv FROM zanrovi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);

			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					String naziv = rset.getString(++k);

					zanr = new Zanr(id, naziv);
				}
			}
		}

		return zanr;
	}

	@Override
	public Collection<Zanr> getAll() throws Exception {
		Collection<Zanr> zanrovi = new ArrayList<>();

		String sql = "SELECT id, naziv FROM zanrovi";
		try (PreparedStatement smtm = conn.prepareStatement(sql)) {
			try (ResultSet rset = smtm.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long id = rset.getLong(++k);
					String naziv = rset.getString(++k);

					Zanr zanr = new Zanr(id, naziv);
					zanrovi.add(zanr);
				}

			}
		}

		return zanrovi;
	}

	@Override
	public void add(Zanr zanr) throws Exception {
		String sql = "INSERT INTO zanrovi (naziv) VALUES (?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, zanr.getNaziv());

			stmt.executeUpdate();
		}

	}

	@Override
	public void update(Zanr zanr) throws Exception {
		String sql = "UPDATE zanrovi SET naziv = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, zanr.getNaziv());
			stmt.setLong(++p, zanr.getId());

			stmt.executeUpdate();
		}

	}

	@Override
	public void delete(long id) throws Exception {
		String sql = "DELETE FROM zanrovi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);

			stmt.executeUpdate();
		}

	}

}
