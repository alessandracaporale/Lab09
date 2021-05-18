package it.polito.tdp.borders.model;

import java.util.*;
import it.polito.tdp.borders.db.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

public class Model {
	
	BordersDAO dao = new BordersDAO();
	private Graph<Country, DefaultEdge> grafo; 

	public Model() {
	}
	
	public List<Country> loadAllCountries() {
		return dao.loadAllCountries();
	}
	
	public List<Border> getCountryPairs(int anno) {
		return dao.getCountryPairs(anno);
	}
	
	//creazione del grafo
	public void creaGrafo(int anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		List<Country> paesi = new ArrayList<>();
		
		for (Border b : this.getCountryPairs(anno)) {
			boolean esiste1 = false;
			boolean esiste2 = false;
			for (Country c : paesi) {
				if(b.getC1().equals(c)) {
					esiste1 = true;
				}
				if(b.getC2().equals(c)) {
					esiste2 = true;
				}
			}
			if (!esiste1) {
				paesi.add(b.getC1());
			}
			if (!esiste2) {
				paesi.add(b.getC2());
			}
		}
	
		Graphs.addAllVertices(grafo, paesi);
		
		for (Border b : this.getCountryPairs(anno)) {
			//Graphs.addEdge(grafo, b.getC1(), b.getC2(),0);
			grafo.addEdge(b.getC1(), b.getC2());
		}
	
	}
	
	
	
	public String elencoConfiniDatoAnno (int anno) {
		String s = "";
		this.creaGrafo(anno);
		
		for (Country c : grafo.vertexSet()) {
			int degree = grafo.degreeOf(c);
			s = s + c.toString() + " --> Stati confinanti = " + degree + "\n"; 
		}
		return s;
	}
}
