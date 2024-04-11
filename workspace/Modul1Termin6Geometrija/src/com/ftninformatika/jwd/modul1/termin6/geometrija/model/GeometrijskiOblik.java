package com.ftninformatika.jwd.modul1.termin6.geometrija.model;

public interface GeometrijskiOblik {

	default String naziv() {
		return getClass().getSimpleName();
	}

	double obim();

	double povrsina();

}
