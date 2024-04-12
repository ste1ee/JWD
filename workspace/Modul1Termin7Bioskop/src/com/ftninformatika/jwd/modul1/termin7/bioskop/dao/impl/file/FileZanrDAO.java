package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ZanrDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Zanr;

public class FileZanrDAO implements ZanrDAO {

	@Override
	public Zanr get(long id) throws Exception {
		return Bioskop.getZanrovi().get(id);
	}

	@Override
	public Collection<Zanr> getAll() throws Exception {
		return Bioskop.getZanrovi().values();
	}

	@Override
	public void add(Zanr zanr) throws Exception {
		long id = Bioskop.nextZanrId();
		
		Zanr noviZanr = new Zanr(id, zanr.getNaziv());

		Bioskop.getZanrovi().put(noviZanr.getId(), noviZanr);
		Bioskop.sacuvaj();
	}

	@Override
	public void update(Zanr zanr) throws Exception {
		Zanr postojeciZanr = Bioskop.getZanrovi().get(zanr.getId());
		postojeciZanr.setNaziv(zanr.getNaziv());
		Bioskop.sacuvaj();
	}

	@Override
	public void delete(long id) throws Exception {
		Zanr postojeciZanr = Bioskop.getZanrovi().get(id);
		postojeciZanr.removeAllFilmovi();

		Bioskop.getZanrovi().remove(id);
		Bioskop.sacuvaj();
	}

}
