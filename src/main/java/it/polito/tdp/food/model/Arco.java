package it.polito.tdp.food.model;

public class Arco {
	String Vertice1;
	String Vertice2;
	int peso;
	
	public Arco(String vertice1, String vertice2, int peso) {
		super();
		Vertice1 = vertice1;
		Vertice2 = vertice2;
		this.peso = peso;
	}

	public String getVertice1() {
		return Vertice1;
	}

	public void setVertice1(String vertice1) {
		Vertice1 = vertice1;
	}

	public String getVertice2() {
		return Vertice2;
	}

	public void setVertice2(String vertice2) {
		Vertice2 = vertice2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
