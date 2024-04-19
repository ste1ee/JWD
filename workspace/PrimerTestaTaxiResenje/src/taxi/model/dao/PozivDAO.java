package taxi.model.dao;

import java.util.Collection;

import taxi.model.Poziv;
import taxi.model.Vozilo;

public interface PozivDAO {

	public Collection<Poziv> find(Vozilo vozilo) throws Exception;
	public void add(Poziv poziv) throws Exception;

}
