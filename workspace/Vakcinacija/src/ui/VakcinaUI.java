package ui;

import java.util.Collection;

import dao.VakcinaDAO;
import model.Vakcina;
import util.Konzola;

public class VakcinaUI {

	private static VakcinaDAO vakcinaDAO;

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		VakcinaUI.vakcinaDAO = vakcinaDAO;
	}
	
	public static Vakcina pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite id vakcine");

		Vakcina vakcina = vakcinaDAO.get(id);
		if (vakcina == null)
			Konzola.prikazi("Vakcina nije pronadjena!");

		return vakcina;
	}

	public static void prikazSvih() {
		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			System.out.println();
			for (Vakcina v : vakcine)
				System.out.println(v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
