package Project5;

public class Camp extends Accommodation {
	public Camp(String name, double x, double y, int capacity) {
		super(name, x, y, capacity);
	}
	public boolean arrive(Expedition e)
	{
		return (super.arrive(e) && e.getHasTents());
	}
}
