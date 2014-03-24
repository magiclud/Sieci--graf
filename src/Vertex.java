import java.util.ArrayList;

public class Vertex {
	private int value;
	private ArrayList<Vertex> neighboursOfTheVertex;
	public boolean wasVisited;

	public Vertex(int value) {
		this.value = value;
		wasVisited = false;
		neighboursOfTheVertex = new ArrayList<Vertex>();
	}

	public ArrayList<Vertex> getNeighboursOfTheVertex() {
		return neighboursOfTheVertex;
	}

	public int getValue() {
		return value;
	}

	public void addNeighbour(Vertex v) {
		neighboursOfTheVertex.add(v);
	}
}
