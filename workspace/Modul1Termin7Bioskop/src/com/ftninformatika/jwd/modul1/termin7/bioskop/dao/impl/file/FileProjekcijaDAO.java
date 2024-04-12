package com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ProjekcijaDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Projekcija;

public class FileProjekcijaDAO implements ProjekcijaDAO {

	@Override
	public Projekcija get(long id) throws Exception {
		return Bioskop.getProjekcije().remove(id);
	}

	@Override
	public Collection<Projekcija> getAll() throws Exception {
		return Bioskop.getProjekcije().values();
	}

	@Override
	public void add(Projekcija projekcija) throws Exception {
		long id = Bioskop.nextProjekcijaId();
		
		Projekcija novaProjekcija = new Projekcija(id, 
				projekcija.getDatumIVreme(), 
				projekcija.getFilm(), 
				projekcija.getSala(), 
				projekcija.getTip(), 
				projekcija.getCenaKarte());
		Bioskop.getProjekcije().put(novaProjekcija.getId(), novaProjekcija);
		Bioskop.sacuvaj();
	}

	@Override
	public void update(Projekcija projekcija) throws Exception {
		Projekcija postojecaProjekcija = Bioskop.getProjekcije().get(projekcija.getId());
		postojecaProjekcija.setDatumIVreme(projekcija.getDatumIVreme());
		postojecaProjekcija.setFilm(projekcija.getFilm());
		postojecaProjekcija.setSala(projekcija.getSala());
		postojecaProjekcija.setTip(projekcija.getTip());
		postojecaProjekcija.setCenaKarte(projekcija.getCenaKarte());
		Bioskop.sacuvaj();
	}

	@Override
	public void delete(long id) throws Exception {
		Projekcija postojecaProjekcija = Bioskop.getProjekcije().get(id);
		postojecaProjekcija.setFilm(null);
		
		Bioskop.getProjekcije().remove(id);
		Bioskop.sacuvaj();
	}

}
