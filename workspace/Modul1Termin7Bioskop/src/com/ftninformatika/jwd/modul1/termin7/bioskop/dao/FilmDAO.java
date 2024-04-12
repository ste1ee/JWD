package com.ftninformatika.jwd.modul1.termin7.bioskop.dao;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;

public interface FilmDAO {

	Film get(long id) throws Exception;
	Collection<Film> getAll() throws Exception;
	void add(Film film) throws Exception;
	void update(Film film) throws Exception;
	void delete(long id) throws Exception;

}
