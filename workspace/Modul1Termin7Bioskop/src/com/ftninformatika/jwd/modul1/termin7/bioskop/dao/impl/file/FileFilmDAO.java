package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.FilmDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;

public class FileFilmDAO implements FilmDAO {

	@Override
	public Film get(long id) throws Exception {
		return Bioskop.getFilmovi().get(id);
	}

	@Override
	public Collection<Film> getAll() throws Exception {
		return Bioskop.getFilmovi().values();
	}

	@Override
	public void add(Film film) throws Exception {
		long id = Bioskop.nextFilmId();

		Film noviFilm = new Film(id, film.getNaziv(), film.getTrajanje());
		noviFilm.addAllZanrovi(film.getZanrovi());
		noviFilm.addAllProjekcije(film.getProjekcije());

		Bioskop.getFilmovi().put(noviFilm.getId(), noviFilm);
		Bioskop.sacuvaj();
	}

	@Override
	public void update(Film film) throws Exception {
		Film postojeciFilm = Bioskop.getFilmovi().get(film.getId());
		postojeciFilm.setNaziv(film.getNaziv());
		postojeciFilm.setTrajanje(film.getTrajanje());
		Bioskop.sacuvaj();
	}

	@Override
	public void delete(long id) throws Exception {
		Film postojeciFilm = Bioskop.getFilmovi().get(id);
		postojeciFilm.removeAllZanrovi();
		postojeciFilm.removeAllProjekcije();

		Bioskop.getFilmovi().remove(id);
		Bioskop.sacuvaj();
	}

}
