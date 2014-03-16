import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;

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
			Graph theGraph = new Graph(vertex);

			String textEdge = bufferedReader.readLine();
			do {
				System.out.println(textEdge);
				theGraph.addEdge(textEdge);
				textEdge = bufferedReader.readLine();
				// theGraph.findWayFromAToB(1, 7);

			} while (textEdge != null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

// Graph theGraph = new Graph(vertex);
// String edge1 = "(v1-v2)=5";
// theGraph.addEdge(edge1);
//
// theGraph.findWayFromAToB(1, 7);
// } catch (FileNotFoundException | e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

