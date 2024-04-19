package dao;

import java.util.Collection;

import model.Voz;

public interface VozDAO {
	
	public Voz get(long id) throws Exception;
	public Collection<Voz> getAll() throws Exception;
	public Voz getPoNazivu(String naziv) throws Exception;

}
