package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Utakmica {

	private int sifra;
	private String datum;
	private String vreme;
	Klub domacin;
	Klub gost;
	private java.util.ArrayList<Sudija> sveSudije = new ArrayList<>();

	public Utakmica(int sifra, String datum, String vreme, Klub domacin, Klub gost, ArrayList<Sudija> sveSudije) {
		super();
		this.sifra = sifra;
		this.datum = datum;
		this.vreme = vreme;
		this.domacin = domacin;
		this.gost = gost;
		this.sveSudije = sveSudije;
	}

	public Utakmica(String datum, String vreme, Klub domacin, Klub gost, ArrayList<Sudija> sveSudije) {
		this(0, datum, vreme, domacin, gost, sveSudije);
	}

	public Utakmica() {
		this(0, "", "", null, null, null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sifra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utakmica other = (Utakmica) obj;
		return sifra == other.sifra;
	}

	@Override
	public String toString() {
		return "Utakmica [sifra=" + sifra + ", datum=" + datum + ", vreme=" + vreme + "]";
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public Klub getDomacin() {
		return domacin;
	}

	public Klub getGost() {
		return gost;
	}

	public int getSifra() {
		return sifra;
	}

	// metode za povezivanje
	public void setDomacin(Klub domacin) {
		this.domacin = domacin;
	}

	public void setGost(Klub gost) {
		this.gost = gost;
	}

	public Collection<Sudija> getSveSudije() {
		return Collections.unmodifiableCollection(sveSudije);
	}

	public void addSudija(Sudija sudija) {
		this.sveSudije.add(sudija);
	}

	public void addAllSudije(ArrayList<Sudija> sudije) {
		this.sveSudije.addAll(sudije);
	}

	public void removeSudija(Sudija sudija) {
		this.sveSudije.remove(sudija);
	}

	public void removeAllSudije() {
		this.sveSudije.clear();
	}

}
