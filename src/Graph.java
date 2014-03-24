import java.util.ArrayList;

public class Graph {

	ArrayList<Vertex> vertexList;/* lista wierzcholkow */
	private int adjacencyMatrix[][]; /* macierz przlegania */
	private int numberOfVertexes; /* liczba wierzcholkow */
	ArrayList<Vertex> neighboursOfTheVertex;
	Stack theStack;
	private DistanceToParent shortPath[]; /* tablica danych najkrotszej sciezki */

	public Graph(String ver) {

		addVertex(ver);// oddawanie wierzcholkow do listy wierzcholkow
		// ustawnieie liczby wszystkich wierzcholkow
		numberOfVertexes = vertexList.size();
		// zerowanie macierzy przylegania
		adjacencyMatrix = new int[numberOfVertexes][numberOfVertexes];
		for (int i = 0; i < numberOfVertexes; i++) {
			for (int j = 0; j < numberOfVertexes; j++) {
				adjacencyMatrix[j][i] = -1;
				// -1 dla odróznienia od isniejacych krawedzi, gdzyz waga
				// krawedzi jest liczba dodatnia
			}
		}
		// najkrotsze sciezki
		shortPath = new DistanceToParent[numberOfVertexes + 1];

		theStack = new Stack(); // stos
	}

	public void addVertex(String ver) {
		String[] clean = ver.split(":");
		String[] listOfVertex = clean[1].split(",");
		this.vertexList = new ArrayList<Vertex>();
		for (int i = 0; i < listOfVertex.length; i++) {
			String[] digits = listOfVertex[i].split("v");
			vertexList.add(new Vertex(Integer.parseInt(digits[1])));
		}
	}

	public void addEdge(String edge1) {
		String[] getWeight = edge1.split("=");
		int weight = Integer.parseInt(getWeight[1]);
		char[] edge = new char[getWeight.length];// dlugosc 2
		// zczytuje odpowiednie wierzcholki tworzace krawedz
		getWeight[0].getChars(2, 3, edge, 0);
		// piwerwszy wierzcholek krawedzi jest w edge[0], a drugi w edge[1]
		getWeight[0].getChars(5, 6, edge, 1);
		createAdjacencyMatrixAndSetANeighourhood(edge, weight);
	}

	private void createAdjacencyMatrixAndSetANeighourhood(char[] edge,
			int weight) {
		// przypisanie wartosci krawedzi o wierzcholkach edge[0] i edge[1]
		int i = Character.getNumericValue(edge[0]);
		int j = Character.getNumericValue(edge[1]);
		adjacencyMatrix[i - 1][j - 1] = weight;
		// przypisane odwronie wartosci - inaczej bylyby to wierzcholki
		// skierowane, a tak macierz bedzie symetryczna
		adjacencyMatrix[j - 1][i - 1] = weight;

		// ustawianie sasiadow
		vertexList.get(i - 1).addNeighbour(vertexList.get(j - 1));// dodanie do
																	// j do
																	// listy
																	// sasiadow
																	// i
		vertexList.get(j - 1).addNeighbour(vertexList.get(i - 1));
	}

	/**
	 * znajduje czy istnieje polaczenie miedzy dwoma wierzcholkami
	 * 
	 * @param i
	 *            - wartosc pierwszego wierzcholka
	 * @param j
	 *            - wartosc drugiego wierzcholka
	 * @return "true" gdy istnieje jakiekolwiek polaczenie miedzy wierzcholkami
	 */
	public boolean findWayFromAToB(int i, int j) {
		neighboursOfTheVertex = new ArrayList<Vertex>();
		// A- miejsce w tablicy wierzcholkow gdzie znajduje sie punkt startowy;
		// B - miejsce w tablicy wierzcholkow gdzie znajduje sie punkt koncowy;
		int A = findVertexInTheList(i);// A- adres i
		int B = findVertexInTheList(j); // B- adres j
		if (A < 0 || B < 0) {
			// throw nie ma takich wierzcholkow w grafie//TODO
		}
		System.out.println("Start z wierzcholka "
				+ vertexList.get(i - 1).getValue());

		// i - oznaczam jako odwiedzony
		vertexList.get(A).wasVisited = true;

		// dodaje nazwe wierzcholeka do stosu
		theStack.push(i);

		while (!theStack.isEmpty()) {// do momentu oproznienia stosu

			// pobieram adres nieodwiedzonego wierzcholka przyleglego do
			// wierzcholka
			// i
			int neighbour = getAdjacentyUnvisitedVertex(theStack.peek());

			if (neighbour == -1) {
				// gdy wierzcholek o adresie A nie ma sasiadow
				theStack.pop();
			} else {
				// oznaczam sasiada jako odwiedzony
				vertexList.get(neighbour).wasVisited = true;
				// tu powinnam zliczac wage //TODO

				if (vertexList.get(neighbour).getValue() == j) {

					setVisitedOnFalse();
					return true;
				}

				System.out.println("wierzcholki po drodze "
						+ vertexList.get(neighbour).getValue());
				// zapisz wartosc sasiada na stos
				theStack.push(vertexList.get(neighbour).getValue());
			}
		}
		setVisitedOnFalse();

		return false;
	}

	private void setVisitedOnFalse() {
		// przywracam wejsciowe ustawienia - tzn kazdy wierzcholek na
		// nieodwiedzony
		for (Vertex x : vertexList) {
			x.wasVisited = false;
		}
	}

	/**
	 * 
	 * @param ver
	 *            - wierzcholek, ktorego sasiad jest wyszukiwany
	 * @return - adres nieodwiedzonego sasiada danego wierzcholka
	 */
	private int getAdjacentyUnvisitedVertex(int ver) {
		for (int j = 0; j < numberOfVertexes; j++) {
			if (adjacencyMatrix[ver - 1][j] > -1
					&& vertexList.get(j).wasVisited == false) {
				return j;
			}
		}
		return -1;
	}

	private int findVertexInTheList(int i) {
		for (Vertex x : vertexList) {
			if (x.getValue() == i) {
				System.out.println("Index of v " + vertexList.indexOf(x));
				return vertexList.indexOf(x);

			}
		}
		return -1;
	}

	public static void printAdjacentyMatrix(int[][] adjacenyMatrix) {
		for (int i = 0; i < adjacenyMatrix.length; i++) {
			for (int j = 0; j < adjacenyMatrix.length; j++)
				System.out.print(adjacenyMatrix[i][j] + " ");
			System.out.println();
		}
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}
}
