package taxi.model;

public class StavkaIzvestaja {
	public final String ulica;
	public final Vozilo voziloSaNajvisePoziva;
	public final int brojPoziva;

	public static int compareBrojPoziva(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return -Integer.compare(stavka1.brojPoziva, stavka2.brojPoziva);
	}
	
	public StavkaIzvestaja(String ulica, Vozilo voziloSaNajvisePoziva, int brojPoziva) {
		this.ulica = ulica;
		this.voziloSaNajvisePoziva = voziloSaNajvisePoziva;
		this.brojPoziva = brojPoziva;
	}

	@Override
	public String toString() {
		return "StavkaIzvestaja [ulica=" + ulica + ", voziloSaNajvisePoziva=" + voziloSaNajvisePoziva.getBroj()
				+ ", brojPoziva=" + brojPoziva + "]";
	}
	
}
