package taxi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Vozilo {
	private final long id;

	private String broj;
	private String vozac;

	private final Set<Poziv> pozivi = new LinkedHashSet<>(); // many-to-one veza
	
	public Vozilo(long id, String broj, String vozac) {
		this.id = id;
		this.broj = broj;
		this.vozac = vozac;
	}

	// metode
	public Set<Poziv> getPozivi() {
		return Collections.unmodifiableSet(pozivi);
	}
	
	public void addPoziv(Poziv poziv) {
		this.pozivi.add(poziv);
	}

	public void addAllPozivi(Collection<Poziv> pozivi) {
		this.pozivi.addAll(pozivi);
	}

	// metode za podršku izveštavanja
	public Collection<Poziv> getPoziviIzUliceUVremenskomOpsegu(String ulica, LocalDateTime pocetak, LocalDateTime kraj) {
		Collection<Poziv> poziviUOpsegu = new ArrayList<>();
		for (Poziv itPoziv: pozivi) {
			if (itPoziv.getUlica().equals(ulica) && itPoziv.isDatumIVremeUOpsegu(pocetak, kraj)) {
				poziviUOpsegu.add(itPoziv);
			}
		}
		return poziviUOpsegu;
	}

	// Object metode
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
		Vozilo other = (Vozilo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Vozilo [id=" + id + ", broj=" + broj + ", vozac=" + vozac + "]";
	}

	// getter-i i setter-i
	public long getId() {
		return id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

}
