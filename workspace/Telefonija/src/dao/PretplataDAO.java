package dao;

import java.util.Collection;
import model.Pretplata;

public interface PretplataDAO {
  
  public Collection<Pretplata> getAll() throws Exception;
  public Pretplata get(long id) throws Exception;
  public Pretplata getPoBroju(String broj) throws Exception;
  public void add(Pretplata pretplata) throws Exception;

}
