package Project5;

public class Lodge extends Accommodation{

	private double costPerPerson;
	public Lodge(String name, double x, double y, int capacity, double costPerPerson) {
		super(name, x, y, capacity);
		this.costPerPerson = costPerPerson; 
	}
	
	public double getCostPerPerson()
	{
		return costPerPerson;
	}
	
	public boolean leave(Expedition e)
	{
		double lodgeCost = e.getPeople()*costPerPerson;
		if (e.getMoney() >= lodgeCost)
		{
			e.pay(lodgeCost);
			return true;
		}
		return false;
	}
}
