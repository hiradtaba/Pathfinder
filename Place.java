package Project5;
public abstract class Place {
	
	private String name;
	private double x;
	private double y;
	
	public Place(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getDistance(Place destination)
	{
		double dx = Math.abs(x - destination.getX());
		double dy = Math.abs(y - destination.getY());
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	public boolean goTo(Expedition e, Place destination)
	{
		if (leave(e))
			if (e.walk(getDistance(destination)))
				return destination.arrive(e);
		return false;
	}
	
	public boolean leave(Expedition e)
	{
		return true;
	}
	
	public boolean arrive(Expedition e)
	{
		return true;
	}
}
