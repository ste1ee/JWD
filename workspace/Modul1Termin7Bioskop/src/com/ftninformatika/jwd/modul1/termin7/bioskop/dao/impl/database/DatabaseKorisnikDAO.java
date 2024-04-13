package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.KorisnikDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Korisnik;

public class DatabaseKorisnikDAO implements KorisnikDAO {

	private final Connection conn;

	public DatabaseKorisnikDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Korisnik get(String korisnickoIme) throws Exception {
		Korisnik korisnik = null;
		String sql = "SELECT lozinka, eMail, pol, administrator FROM korisnici WHERE korisnickoIme = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, korisnickoIme);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					String lozinka = rset.getString(++kolona);
					String eMail = rset.getString(++kolona);
					String pol = rset.getString(++kolona);
					boolean administrator = rset.getBoolean(++kolona);

					korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator);
				}
			}
		}

		return korisnik;
	}

	@Override
	public Collection<Korisnik> getAll() throws Exception {
		Collection<Korisnik> korisnici = new ArrayList<>();
		String sql = "SELECT korisnickoIme, lozinka, eMail, pol, administrator FROM korisnici";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					String korisnickoIme = rset.getString(++kolona);
					String lozinka = rset.getString(++kolona);
					String eMail = rset.getString(++kolona);
					String pol = rset.getString(++kolona);
					boolean administrator = rset.getBoolean(++kolona);

					Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator);
					korisnici.add(korisnik);

				}

			}
		}

		return korisnici;
	}

	@Override
	public void add(Korisnik korisnik) throws Exception {
		String sql = "INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES(?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, korisnik.getKorisnickoIme());
			stmt.setString(++p, korisnik.getLozinka());
			stmt.setString(++p, korisnik.geteMail());
			stmt.setString(++p, korisnik.getPol());
			stmt.setBoolean(++p, korisnik.getAdministrator());

			stmt.executeUpdate();
		}

	}

	@Override
	public void update(Korisnik korisnik) throws Exception {
		String sql = "UPDATE korisnici SET lozinka = ?, eMail = ?, pol = ?, administrator = ? WHERE korisnickoIme = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, korisnik.getLozinka());
			stmt.setString(++p, korisnik.geteMail());
			stmt.setString(++p, korisnik.getPol());
			stmt.setBoolean(++p, korisnik.getAdministrator());
			stmt.setString(++p, korisnik.getKorisnickoIme());

			stmt.executeUpdate();
		}

	}

	@Override
	public void delete(String korisnickoIme) throws Exception {
		String sql = "DELETE FROM korisnici WHERE korisnickoIme = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int p = 0;
			stmt.setString(++p, korisnickoIme);

			stmt.executeUpdate();
		}

	}

}
