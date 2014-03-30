

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class path {

	public static void main(String[] args) {
		
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
			
			path path = new path();
			path.test(theGraph);
			
			//System.out.println( "Czy istnieje droga? - " + theGraph.findWayFromAToB(8, 1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		@Test
		public void test(Graph2 theGraph) {
			theGraph.findWayFromAToB(1,4);
		}
		
}
