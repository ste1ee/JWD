package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Karta {

	private long id;
	Voz voz;
	private LocalDateTime dIVProdaje;
	private String kupac;
	private int razred;

	public Karta(long id, Voz voz, LocalDateTime dIVProdaje, String kupac, int razred) {
		this.id = id;
		this.voz = voz;
		this.dIVProdaje = dIVProdaje;
		this.kupac = kupac;
		this.razred = razred;
	}

	@Override
	public String toString() {
		return "Karta [id=" + id + ", dIVProdaje=" + dIVProdaje + ", kupac=" + kupac + ", razred=" + razred + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Karta other = (Karta) obj;
		return id == other.id;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz voz) {
		this.voz = voz;
		voz.addKarta(this);
	}

	public LocalDateTime getdIVProdaje() {
		return dIVProdaje;
	}

	public void setdIVProdaje(LocalDateTime dIVProdaje) {
		this.dIVProdaje = dIVProdaje;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public int getRazred() {
		return razred;
	}

	public void setRazred(int razred) {
		this.razred = razred;
	}

	public long getId() {
		return id;
	}

}
