package Project5;

//Name: Hirad Tabatabaei
//Class: ECE 122
//Instructor: Professor Marzulo
//Due date: March 5, 2018

public class Obstacle extends Place{

	private double ropeLength;

	public Obstacle(String name, double x, double y, double ropeLength) {
		super(name, x, y);
		this.ropeLength = ropeLength;
	}

	public double getRopeLength() {
		return ropeLength;
	}

	public boolean arrive(Expedition e)
	{
		return ropeLength<=e.getRopeLength();
	}
}
