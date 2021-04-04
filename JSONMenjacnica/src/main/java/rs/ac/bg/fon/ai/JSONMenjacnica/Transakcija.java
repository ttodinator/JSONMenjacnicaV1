package rs.ac.bg.fon.ai.JSONMenjacnica;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transakcija {
	
	private String izvornaValuta;
	private String krajnjaValuta;
	private double pocetniIznos;
	private double konvertovaniIznos;
	private Date datumTransakcije;
	
	public Transakcija() {
		
	}

	public Transakcija(String izvornaValuta, String krajnjaValuta, double pocetniIznos, double konvertovaniIznos,
			Date datumTransakcije) {
		super();
		this.izvornaValuta = izvornaValuta;
		this.krajnjaValuta = krajnjaValuta;
		this.pocetniIznos = pocetniIznos;
		this.konvertovaniIznos = konvertovaniIznos;
		this.datumTransakcije = datumTransakcije;
	}

	public String getIzvornaValuta() {
		return izvornaValuta;
	}

	public void setIzvornaValuta(String izvornaValuta) {
		this.izvornaValuta = izvornaValuta;
	}

	public String getKrajnjaValuta() {
		return krajnjaValuta;
	}

	public void setKrajnjaValuta(String krajnjaValuta) {
		this.krajnjaValuta = krajnjaValuta;
	}

	public double getPocetniIznos() {
		return pocetniIznos;
	}

	public void setPocetniIznos(double pocetniIznos) {
		this.pocetniIznos = pocetniIznos;
	}

	public double getKonvertovaniIznos() {
		return konvertovaniIznos;
	}

	public void setKonvertovaniIznos(double konvertovaniIznos) {
		this.konvertovaniIznos = konvertovaniIznos;
	}

	public Date getDatumTransakcije() {
		return datumTransakcije;
	}

	public void setDatumTransakcije(Date datumTransakcije) {
		this.datumTransakcije = datumTransakcije;
	}

	@Override
	public String toString() {
		Format f = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		String dat = f.format(datumTransakcije);
		return "Transakcija [izvornaValuta=" + izvornaValuta + ", krajnjaValuta=" + krajnjaValuta + ", pocetniIznos="
				+ pocetniIznos + ", konvertovaniIznos=" + konvertovaniIznos + ", datumTransakcije=" + dat
				+ "]";
	}

}
