package dao;

import model.Paket;

public interface PaketDAO {
	public Paket get(long id) throws Exception;

	public Paket getPoNazivu(String naziv) throws Exception;
}
