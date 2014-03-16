
public class Vertex {
	private int value;

	public boolean wasVisited;
	
	public Vertex(int value){
		this.value = value;
		wasVisited = false;
	}
	
	public int getValue() {
		return value;
	}
}
