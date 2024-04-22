package dao;

import java.util.Collection;

import model.Clanarina;

public interface ClanarinaDAO {
	public Collection<Clanarina> getAll() throws Exception;

	public void add(Clanarina clanarina) throws Exception;
}
