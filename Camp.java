package Project5;

//Name: Hirad Tabatabaei
//Class: ECE 122
//Instructor: Professor Marzulo
//Due date: March 5, 2018

public class Camp extends Accommodation {

	public Camp(String name, double x, double y, int capacity) {
		super(name, x, y, capacity);
	}

	public boolean arrive(Expedition e)
	{
		return (super.arrive(e) && e.getHasTents());
	}
}
