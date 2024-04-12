package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.database;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Film> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Film film) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Film film) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
