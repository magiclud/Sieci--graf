import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphMain {

	public static void main(String[] args) {
		// String vertex = "v1,v2,v3,v4,v5,v6,v7,v8";

		FileReader file;
		BufferedReader bufferedReader;
		try {
			file = new FileReader("D:\\eclipse\\Semestr4\\Graf\\vertexes.txt");
			bufferedReader = new BufferedReader(file);

			String vertex = bufferedReader.readLine();
			System.out.println(vertex);
			Graph2 theGraph = new Graph2(vertex);

			String textEdge = bufferedReader.readLine();
			do {
				System.out.println(textEdge);
				theGraph.addEdge(textEdge);
				textEdge = bufferedReader.readLine();
			} while (textEdge != null);
		//	theGraph.printAdjacentyMatrix(theGraph.getAdjacencyMatrix());
			theGraph.findWayFromAToB(3, 1);
			//System.out.println( "Czy istnieje droga? - " + theGraph.findWayFromAToB(8, 1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
