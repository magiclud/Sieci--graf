
public class Stack {

	private int  stackSize =20; /*rozmiar stosu*/
	private int[] st;/*stos*/
	
	private int ptr;/*wskaznik stosu*/
	
	public Stack(){
		st = new int[stackSize];
		ptr= -1;
	}
	
	/**
	 * dodawanie elementu do stosu
	 * @param ver adres wierzcholka 
	 */
	public void push(int ver){
		ptr ++;
		st[ptr]= ver;
	}
	
	/**
	 * pobranie elementu ze stosu
	 * @return adres wierzcholka bedacego na stosie uprzednio na stosie
	 */
	public int pop(){
		ptr--;
		return st[ptr+1];
	}
	
	/**
	 * pobranie wartosci ze szczytu stosu
	 * @return adres pierwszego wierzcholka bedacego na stosie
	 */
	public int peek(){
		return st[ptr];
	}
	
	/**
	 * sprawdzenie czy na stosie sa jakiekolwiek wartosci
	 * @return 'prawda' jesli stos jest pusty
	 */
	public boolean isEmpty(){
		if(ptr ==-1){
			return true;
		}
		return false;
	}
}
