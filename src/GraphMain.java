import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GraphMain {

	public static void main(String[] args) {
		// String vertex = "v1,v2,v3,v4,v5,v6,v7,v8";
		Scanner scanIn;

		FileReader file;
		BufferedReader bufferedReader;
		try {
			file = new FileReader("D:\\eclipse\\Semestr4\\Graf\\vertexes.txt");
			bufferedReader = new BufferedReader(file);

			String vertex = bufferedReader.readLine();
			Graph2 theGraph = new Graph2(vertex);
			String lineLoad = bufferedReader.readLine();
			do {
				theGraph.addEdge(lineLoad);
				lineLoad = bufferedReader.readLine();
			} while (lineLoad != null);
			theGraph.printAdjacentyMatrix(theGraph.getAdjacencyMatrix());
			System.out.println("Enter something here (e.g. v1->v2:20) : ");
			scanIn = new Scanner(System.in);
			lineLoad = scanIn.nextLine();
			while (!lineLoad.equals("end")) {
				if (!lineLoad.equals("")) {
					String path = lineLoad;
					theGraph.findWayFromAToB(path);
					theGraph.displayVeritlesOnTheWay(path);

					System.out.println("Liczba rund: " + theGraph.nrOfRounds());
					for (int i = 0; i < theGraph.nrOfRounds(); i++) {
						theGraph.increaseWeightAtOne();

						lineLoad = scanIn.nextLine();
						if (lineLoad.equals("end") || !lineLoad.endsWith("")) {
							break;
						}
						theGraph.printAdjacentyMatrix(theGraph
								.getAdjacencyMatrix());
					}
					lineLoad = scanIn.nextLine();
				}

			}

			scanIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
