package com.ftninformatika.jwd.modul1.termin7.bioskop.dao;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Projekcija;

public interface ProjekcijaDAO {

	Projekcija get(long id) throws Exception;
	Collection<Projekcija> getAll() throws Exception;
	void add(Projekcija projekcija) throws Exception;
	void update(Projekcija projekcija) throws Exception;
	void delete(long id) throws Exception;
	
}
