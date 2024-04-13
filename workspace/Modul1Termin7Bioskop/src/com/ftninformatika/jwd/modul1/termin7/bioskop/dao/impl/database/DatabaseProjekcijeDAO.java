package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ProjekcijaDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Projekcija;
import com.ftninformatika.jwd.modul1.util.Konzola;

public class DatabaseProjekcijeDAO implements ProjekcijaDAO {

	private final Connection conn;

	public DatabaseProjekcijeDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Projekcija get(long id) throws Exception {
		Projekcija projekcija = null;
		String sql = "SELECT datumIVreme, filmId, sala, tip, cenaKarte FROM projekcije WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					LocalDateTime datumIVreme = rset.getTimestamp(++k).toLocalDateTime();
					long filmId = rset.getLong(++k);
					Film film = Bioskop.getFilmovi().get(filmId);
					int sala = rset.getInt(++k);
					String tip = rset.getString(++k);
					double cenaKarte = rset.getBigDecimal(++k).doubleValue();

					projekcija = new Projekcija(id, datumIVreme, film, sala, tip, cenaKarte);
				}
			}
		}

		return projekcija;
	}

	@Override
	public Collection<Projekcija> getAll() throws Exception {
		Collection<Projekcija> projekcije = new ArrayList<>();

		String sql = "SELECT id, datumIVreme, filmId, sala, tip, cenaKarte FROM projekcije";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int k = 0;
					long id = rset.getLong(++k);
					LocalDateTime datumIVreme = rset.getTimestamp(++k).toLocalDateTime();
					long filmId = rset.getLong(++k);
					Film film = Bioskop.getFilmovi().get(filmId);
					int sala = rset.getInt(++k);
					String tip = rset.getString(++k);
					double cenaKarte = rset.getBigDecimal(++k).doubleValue();

					Projekcija projekcija = new Projekcija(id, datumIVreme, film, sala, tip, cenaKarte);
					projekcije.add(projekcija);

				}

			}
		}

		return projekcije;
	}

	@Override
	public void add(Projekcija projekcija) throws Exception {
		String sql = "INSERT INTO projekcije (datumIVreme, filmId, sala, tip, cenaKarte) VALUES(?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setTimestamp(++p, Timestamp.valueOf(projekcija.getDatumIVreme()));
			stmt.setLong(++p, 1);
			stmt.setInt(++p, projekcija.getSala());
			stmt.setString(++p, projekcija.getTip());
			stmt.setBigDecimal(++p, BigDecimal.valueOf(projekcija.getCenaKarte()));

			stmt.executeUpdate();
		}

	}

	@Override
	public void update(Projekcija projekcija) throws Exception {
		String sql = "UPDATE projekcije SET datumIVreme = ?, filmId = ?, sala = ?, tip = ?, cenaKarte = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setTimestamp(++p, Timestamp.valueOf(projekcija.getDatumIVreme()));
			stmt.setLong(++p, 1);
			stmt.setInt(++p, projekcija.getSala());
			stmt.setString(++p, projekcija.getTip());
			stmt.setBigDecimal(++p, BigDecimal.valueOf(projekcija.getCenaKarte()));
			stmt.setLong(++p, projekcija.getId());

			stmt.executeUpdate();
		}

	}

	@Override
	public void delete(long id) throws Exception {
		String sql = "DELETE FROM projekcije WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setLong(++p, id);

			stmt.executeUpdate();
		}

	}

}
