package dao;

import java.util.Collection;

import model.Prijava;

public interface PrijavaDAO {
	
	public Collection<Prijava> getAll() throws Exception;
	public void add(Prijava prijava) throws Exception;

}
