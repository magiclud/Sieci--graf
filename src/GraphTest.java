import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

	private String vertex = "v1,v2,v3,v4,v5,v6,v7,v8";
	Graph theGraph;

	
	//dodawanie poprawne, test sprawdzajacy to juz nie 
	@Test
	public void testTworzeniaListyWierzcholkow() {
		String listaOczekiwana[] = { "v1", "v2", "v3", "v4", "v5", "v6", "v7",
				"v8" };
		theGraph = new Graph(vertex);
		assertEquals(listaOczekiwana, theGraph.vertexList);
	}

}
