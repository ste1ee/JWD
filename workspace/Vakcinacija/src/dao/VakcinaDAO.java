package dao;

import java.util.Collection;

import model.Vakcina;

public interface VakcinaDAO {
	
	public Vakcina get(long id) throws Exception;
	public Collection<Vakcina> getAll() throws Exception;
}
