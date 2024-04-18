package dao;

import java.util.Collection;

import model.Poziv;

public interface PozivDAO {
	
	Poziv get(long id) throws Exception;
	Collection<Poziv> getAll() throws Exception;
	void add(Poziv poziv) throws Exception;

}
