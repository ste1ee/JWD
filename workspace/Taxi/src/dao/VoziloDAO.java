package dao;

import java.util.Collection;

import model.Vozilo;

public interface VoziloDAO {
	
	Vozilo get(long id) throws Exception;
	Collection<Vozilo> getAll() throws Exception;

}
