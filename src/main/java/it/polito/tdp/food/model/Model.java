package it.polito.tdp.food.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao;
	private Graph <String, DefaultWeightedEdge> grafo;
	private List <String> best;
	private int bestPeso;

	public Model() {
		dao = new FoodDao();
	}
	
	public void creaGrafo(int cal) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//Get Vertici
		Graphs.addAllVertices(grafo, dao.getVertex(cal));
		System.out.println("#Vertici: "+grafo.vertexSet().size());
		
		//Get Archi 
		List <Arco> archi = dao.getArchi(cal);
		System.out.println("#Archi-pre: "+archi.size());
		
		for (Arco a : archi) {
			String v1 = a.getVertice1();
			String v2 = a.getVertice2();
			if (grafo.containsVertex(v1) && grafo.containsVertex(v2)) {
				
				if (!grafo.containsEdge(v1, v2)) {
					Graphs.addEdge(grafo, v1, v2, a.getPeso());
				}
			}
			else System.out.println(v1+" - "+v2);
		}
		System.out.println("#Archi-post: "+grafo.edgeSet().size());
	}
	
	public List<String> getVertex(int cal) {
		return dao.getVertex(cal);
	}

	public List<Arco> getCorrelate(String porzione) {
		List <Arco> correlati = new LinkedList<Arco>();
		for (String vicino : Graphs.neighborListOf(grafo, porzione)) {
			DefaultWeightedEdge e = grafo.getEdge(porzione, vicino);
			correlati.add(new Arco(vicino, porzione, (int) grafo.getEdgeWeight(e)));
		}
		
		return correlati;
	}

	public List<String> getListaPesoMax(int n, String value) {
		best = new LinkedList<String>();
		bestPeso = -1;
		
		List <String> attuale = new LinkedList<String>();
		attuale.add(value);
		ricorsione(n, attuale, 0);
		
		return best;
	}

	private void ricorsione(int n, List<String> attuale, int pesoAttuale) {
		if (attuale.size() == n+1) {
			if (pesoAttuale > bestPeso) {
				best = new LinkedList<String>(attuale);
				bestPeso = pesoAttuale;
			}
			System.out.println(best);
			return;
		}
		else if (attuale.size() < n+1) {
			String porzione = attuale.get(attuale.size()-1);
			List <String> vicini = Graphs.neighborListOf(grafo, porzione);
			vicini.removeAll(attuale);
			for (String vicino : vicini) {
				DefaultWeightedEdge e = grafo.getEdge(porzione, vicino);
				int peso = (int) grafo.getEdgeWeight(e);
				
				attuale.add(vicino);
				ricorsione(n, attuale, pesoAttuale+peso);
				attuale.remove(vicino);
			}
		}
		
	}
	
	public int getPesoMax() {
		return bestPeso;
	}
	
}
