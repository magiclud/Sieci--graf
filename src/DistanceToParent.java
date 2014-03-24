
public class DistanceToParent {

	public int distance; //odleglosc od poczatkowego elementu
	public int parentVertex; //bie¿acy rodzicdanego wierzcholka
	
	public DistanceToParent(int parent, int distance){
		this.parentVertex = parent;
		this.distance = distance;
	}
}
