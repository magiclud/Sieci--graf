import java.util.ArrayList;

public class Graph2 {

	ArrayList<Vertex> vertexList;/* lista wierzcholkow */
	private int adjacencyMatrix[][]; /* macierz przlegania */
	private int numberOfVertexes; /* liczba wierzcholkow */
	ArrayList<Vertex> neighboursOfTheVertex;
	Stack theStack;
	ArrayList<Vertex> visitedVertexes;
	private DistanceToParent shortPath[]; /* tablica danych najkrotszej sciezki */

	public Graph2(String ver) {

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
		adjacencyMatrix[j - 1][i - 1] = weight;
		// ustawianie sasiadow
		vertexList.get(i - 1).addNeighbour(vertexList.get(j - 1));// dodanie do
																	// j do
																	// listy
																	// sasiadow
																	// i
		vertexList.get(j - 1).addNeighbour(vertexList.get(i - 1));
	}

	public void findWayFromAToB(int i, int j) {
		neighboursOfTheVertex = new ArrayList<Vertex>();
		// A- miejsce w tablicy wierzcholkow gdzie znajduje sie punkt startowy;
		// B - miejsce w tablicy wierzcholkow gdzie znajduje sie punkt koncowy;
		int A = findVertexInTheList(i);// A- adres i - jego wartosc to i-1, bo
										// tablice numerujemy od 0
		int B = findVertexInTheList(j); // B- adres j
		if (A < 0 || B < 0) {
			// throw nie ma takich wierzcholkow w grafie//TODO
		}
		System.out.println("Adres i: " + A + "  ;    adres j: " + B);
		System.out.println("Start z wierzcholka "
				+ vertexList.get(i - 1).getValue());

		// i - oznaczam jako odwiedzony
		vertexList.get(A).wasVisited = true;

		visitedVertexes = new ArrayList<Vertex>();
		// dodaje adres wierzcholka do listy
		visitedVertexes.add(vertexList.get(A));

		// create shorPath []
		for (int k = 0; k < numberOfVertexes; k++) {
			int temp = adjacencyMatrix[i - 1][k];
			shortPath[k] = new DistanceToParent(i, temp);
		}
		int nTree = 1;
		int currentVert; // bie¿¹cy wierzcho³ek
		int startToCurrent; // odleg³oœæ do bie¿¹cego wierzcho³ka
		// ******************************test**************/
		while (nTree < numberOfVertexes) {// az do zapisania wszystkich
											// wierzcholkow w drzewie
			int indexMin = getMin(i);// pobierz z shortPath adres najblizszego kolejnego wierzcholka
			int minDist = shortPath[indexMin].distance; // wartosc tej drogi

			if (minDist == -1) {// jezeli wszytsie krawedzie saniedostepne, lub
								// w drzewie
				System.out.println("Wierzcholki niedostepne");
				break;
			} else {
				// przypisz adres biezacego wiezcholka
				currentVert = indexMin;//adres wierzcholka najblizszego
				startToCurrent = shortPath[indexMin].distance;
				// biezacy wierzcholek ma najmniejsza odleglosc od poczatku
				// drzewa, odleglosc ta wynosi startToCurrent
			}
			// zapisz wierzcholek w drzewie
			vertexList.get(currentVert).wasVisited = true;
			nTree++;
			adjus_sPath(currentVert, startToCurrent);// uaktualnij tablice
														// shortPath;

			// end while <- liczba wierzcholkow w drzewie == liczba wierzcholkow
		}

		displayPaths(i);// wyswietl zawartosc tablicy
		nTree = 0; // zeruj drzewo
		for (int k = 0; k < numberOfVertexes; k++) {
			vertexList.get(k).wasVisited = false;
		}

		// while (!theStack.isEmpty()) {// do momentu oproznienia stosu
		//
		// // pobieram adres nieodwiedzonego wierzcholka przyleglego do
		// // wierzcholka
		// // i
		// int neighbour = getAdjacentyUnvisitedVertex(theStack.peek());//TODO
		//
		// if (neighbour == -1) {
		// // gdy wierzcholek o adresie A nie ma sasiadow
		// theStack.pop();
		// } else {
		// // oznaczam sasiada jako odwiedzony
		// vertexList.get(neighbour).wasVisited = true;
		//
		//
		// // if (neighbour == B) {
		// // // jesli adres sasiada jest rowny adresowni docelowego
		// // // elementu -j, to polaczenie miedzy elementami i i j
		// // // istnieje
		// //
		// // System.out.println("Istnieje polaczaenie pomiedzy: "
		// // + vertexList.get(A));
		// // for (Vertex x : neighboursOfTheVertex) {
		// // System.out.println(" i " + x);
		// // }
		// System.out.print("wierzcholki po drodze "+
		// vertexList.get(neighbour));
		// // zapisz sasiada na stos
		// theStack.push(neighbour);
		// }
		// //}
		// }
		//
		// // TODO
	}

	private void displayPaths(int i) {
		System.out.println("Najkrótsza odleglosc od wierzcholka "+i + " do poszczegolnych punktow wynosi: ");
		for (int j = 0; j < numberOfVertexes; j++) {
			if(j+1!=i){
			System.out.print(" do "+(j+1)+" = ");
				System.out.print(shortPath[j].distance);
			
			//int parent = vertexList.get(shortPath[j].parentVertex).getValue();
			System.out.println("(przez wierzcholek " + shortPath[j].parentVertex + ")");
			}
		}

	}

	/**
	 * 
	 * @param ver
	 *            - wierzcholek, ktorego sasiad jest wyszukiwany
	 * @return - adres nieodwiedzonego sasiada danego wierzcholka
	 */
	private int getAdjacentyUnvisitedVertex(int ver) {
		for (int j = 0; j < ver; j++) {
			if (adjacencyMatrix[ver][j] > -1
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

	void findTheShortestPath() {

	}

	// test
	public int getMin(int i) {

		int minDistance = 100000;
		int indexMin = -1;
		for (int k = 0; k < numberOfVertexes; k++) {

			if ((shortPath[k].distance != -1) && !vertexList.get(k).wasVisited
					&& shortPath[k].distance < minDistance) {
				minDistance = shortPath[k].distance;
				indexMin = k;// uaktualnij minimum
			}
		}
		return indexMin;

	}

	private void adjus_sPath(int currentVert, int startToCurrent) {
		// popraw wartosci w tablicy najkrotszych sciezek shortPath

		int column = 1;// pomin wierzcholek poczatkowy
		while (column < numberOfVertexes) {// kolejne kolumny
			// jezeli wierzcholek odpowiadajacy tej kolumnie jest juz w drzewie,
			// pomin go //TODO mozliwe ze sie zapetli- skorzystaj z wiadomosci o
			// sasiadach

			if (vertexList.get(column).wasVisited) {
				column++;
				continue;
			}
			// oblicz odleglosc dla jednego wpisu shortPath
			// pobierz krawedz od bie¿cego wierzcholka do wierzcholka column
			int currentToFringe = adjacencyMatrix[currentVert][column];
			if (currentToFringe != -1) {
				// dodaj odleglsc od poczatku
				int startToFringe = startToCurrent + currentToFringe;
				// pobierz odleglosc biezacego wpisy shortPath
				int sPathDist = shortPath[column].distance;

				// porownaj odleglosc od poczatku z wpisem z shortPath
				if (startToFringe < sPathDist || sPathDist==-1) {// je¿eli krotsza uaktualnij
												// shortPath
					shortPath[column].parentVertex = vertexList.get(currentVert).getValue();
					shortPath[column].distance = startToFringe;
				}
			}
			column++;
		}
	}

}
