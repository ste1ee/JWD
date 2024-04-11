package com.ftninformatika.jwd.modul1.termin6.automobili.ui;

import java.util.ArrayList;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin6.automobili.model.Automobil;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.AutomobilNaGorivo;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.ElektricniAutomobil;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.HibridniAutomobil;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.impl.AutomobilNaGorivoImpl;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.impl.ElektricniAutomobilImpl;
import com.ftninformatika.jwd.modul1.termin6.automobili.model.impl.HibridniAutomobilImpl;
import com.ftninformatika.jwd.modul1.util.Tabela;

public class AutomobiliUI {

	private static final Tabela<Automobil> TABELA = new Tabela<>("%-25s %15s %20s %10s %20s %10s", new Object[] {
			"Model", "Snaga motora", "Kapacitet rezervoara", "Tip goriva", "Kapacitet baterije", "Domet" }

	) {

		@Override
		protected List<Object[]> uredi(Automobil vrednost) {
			String kRezervoara = "X";
			String tGoriva = "X";
			if (vrednost instanceof AutomobilNaGorivo aGorivo) {
				kRezervoara = String.valueOf(aGorivo.getKapacitetRezervoara());
				tGoriva = aGorivo.getTipGoriva();
			}

			String kBaterije = "X";
			String domet = "X";
			if (vrednost instanceof ElektricniAutomobil aElektricni) {
				kBaterije = String.valueOf(aElektricni.getKapacitetBaterije());
				domet = String.valueOf(aElektricni.getDomet());
			}

			if (vrednost instanceof HibridniAutomobil
					|| vrednost instanceof AutomobilNaGorivo && vrednost instanceof ElektricniAutomobil) {
				HibridniAutomobil aHibridni = (HibridniAutomobil) vrednost;
				kRezervoara = String.valueOf(aHibridni.getKapacitetRezervoara());
				tGoriva = aHibridni.getTipGoriva();
				kBaterije = String.valueOf(aHibridni.getKapacitetBaterije());
				domet = String.valueOf(aHibridni.getDomet());
			}
			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.getModel(), vrednost.getSnagaMotora(), kRezervoara, tGoriva, kBaterije,
					domet });
			return rezultat;
		}

	};

	public static void main(String[] args) {
		AutomobilNaGorivo aG = new AutomobilNaGorivoImpl("2022 Honda Accord", 192.0, 14.8, "BMB 95");
		ElektricniAutomobil aE = new ElektricniAutomobilImpl("Fiat 500e", 118.0, 42.0, 315.0);
		HibridniAutomobil aH = new HibridniAutomobilImpl("Mercedes C 300e", 313.0, 50.0, "BMB 95", 25.4, 111.0);

		List<Automobil> automobili = new ArrayList<>();
		automobili.add(aG);
		automobili.add(aE);
		automobili.add(aH);

		TABELA.prikazi(automobili);
		/*
		 * for (Automobil itAutomobil: automobili) { System.out.println(itAutomobil); }
		 */
	}

}
