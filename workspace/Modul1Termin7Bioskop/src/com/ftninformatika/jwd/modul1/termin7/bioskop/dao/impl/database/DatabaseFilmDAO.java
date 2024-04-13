package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.FilmDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;

public class DatabaseFilmDAO implements FilmDAO {

	private final Connection conn;

	public DatabaseFilmDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Film get(long id) throws Exception {
		Film film = null;

		String sql = "SELECT naziv, trajanje FROM filmovi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);

			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					String naziv = rset.getString(++k);
					int trajanje = rset.getInt(++k);

					film = new Film(id, naziv, trajanje);

				}
			}
		}

		return film;
	}

	@Override
	public Collection<Film> getAll() throws Exception {
		Collection<Film> filmovi = new ArrayList<>();

		String sql = "SELECT id, naziv, trajanje FROM filmovi";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long id = rset.getLong(++k);
					String naziv = rset.getString(++k);
					int trajanje = rset.getInt(++k);

					Film film = new Film(id, naziv, trajanje);
					filmovi.add(film);
				}

			}
		}

		return filmovi;
	}

	@Override
	public void add(Film film) throws Exception {
		String sql = "INSERT INTO filmovi (naziv, trajanje) VALUES(?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, film.getNaziv());
			stmt.setInt(++p, film.getTrajanje());

			stmt.executeUpdate();
		}

	}

	@Override
	public void update(Film film) throws Exception {
		String sql = "UPDATE filmovi SET naziv = ?, trajanje = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, film.getNaziv());
			stmt.setInt(++p, film.getTrajanje());
			stmt.setLong(++p, film.getId());

			stmt.executeUpdate();
		}

	}

	@Override
	public void delete(long id) throws Exception {
		String sql = "DELETE FROM filmovi WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);

			stmt.executeUpdate();
		}

	}

}
