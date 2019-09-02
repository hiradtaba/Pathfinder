package Project5;
public class Accommodation extends Place {
	private int capacity;
	public Accommodation(String name, double x, double y, int capacity) {
		super(name, x, y);
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}	
	public boolean arrive(Expedition e)
	{
		return (capacity >= e.getPeople()); 
	}
}
