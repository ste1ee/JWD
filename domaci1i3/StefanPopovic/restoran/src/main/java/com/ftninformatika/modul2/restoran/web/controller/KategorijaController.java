package com.ftninformatika.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/kategorije")
public class KategorijaController {

	private Dostava dostava;

	public KategorijaController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("")
	@ResponseBody
	public String getAll() {
		List<Kategorija> kategorije = new ArrayList<>(dostava.getKategorije().values());
		
		StringBuilder response = new StringBuilder();
		response.append(
				"<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"UTF-8\">\r\n"
				+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"/webjars/bootstrap/css/bootstrap.min.css\"/>\r\n"
				+ "	<title>Kategorije</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-fluid\">\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<div class=\"col\">\r\n"
				+ "				<nav class=\"navbar navbar-expand navbar-dark bg-dark\">\r\n"
				+ "					<a class=\"navbar-brand\" href=\"https://enastava.ftninformatika.com\">\r\n"
				+ "				    	<img src=\"/images/logo.svg\">\r\n"
				+ "					</a>\r\n"
				+ "				    <div class=\"navbar-nav\">\r\n"
				+ "						<a class=\"nav-item nav-link\" href=\"/index.html\">Početna</a>\r\n"
				+ "              		<a class=\"nav-item nav-link\" href=\"/restorani\">Restorani</a>\r\n"
				+ "              		<a class=\"nav-item nav-link\" href=\"/porudzbine\">Porudzbine</a>\r\n"
				+ "             		<a class=\"nav-item nav-link active\" href=\"/kategorije\">Kategorije</a>\r\n"
				+ "             		<a class=\"nav-item nav-link\" href=\"/korisnici\">Korisnici</a>\r\n"
				+ "				    </div>\r\n"
				+ "				</nav>\r\n"
				+ "				<nav class=\"navbar navbar-expand navbar-dark bg-secondary\">\r\n"
				+ "				    <div class=\"navbar-nav\">\r\n"
				+ "						<a class=\"nav-item nav-link active\" href=\"/kategorije\">Prikaz svih</a>\r\n"
				+ "				    </div>\r\n"
				+ "				</nav>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<div class=\"col\">\r\n"
				+ "				<table class=\"table table-striped\">\r\n"
				+ "					<thead>\r\n"
				+ "						<tr>\r\n"
				+ "							<th>Redni broj</th>\r\n"
				+ "							<th>Naziv</th>\r\n"
				+ "						</tr>\r\n"
				+ "					</thead>\r\n"
				+ "					<tbody class=\"table-group-divider\">\r\n"
			);
		for (int it = 0; it < kategorije.size(); it++) {
			Kategorija itKategorija = kategorije.get(it);
			response.append(
					"						<tr>\r\n"
					+ "							<td class=\"text-start\">" + (it + 1) + "</td>\r\n"
					+ "							<td>" + itKategorija.getNaziv() + "</td>\r\n"
					+ "							<td>\r\n"
					+ "                     </tr>\r\n"
				);
		}
		response.append(
				"					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "</html>"
			);
		return response.toString();
	}

}