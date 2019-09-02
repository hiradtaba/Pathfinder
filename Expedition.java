package Project5;

public class Expedition {

	private int people;
	private double money;
	private boolean hasTents;
	private double ropeLength;
	private double foodSupply;
	private double waterSupply;	
	
	private static final double FOOD_PER_PERSON_PER_MILE = 0.1;
	private static final double WATER_PER_PERSON_PER_MILE = 0.2;
	
	public Expedition(int people, double money, boolean hasTents, double ropeLength, double foodSupply, double waterSupply) {
		this.people = people;
		this.money = money;
		this.hasTents = hasTents;
		this.ropeLength = ropeLength;
		this.foodSupply = foodSupply;
		this.waterSupply = waterSupply;
	}

	public int getPeople() {
		return people;
	}
	
	public double getMoney() {
		return money;
	}
	
	public boolean getHasTents() {
		return hasTents;
	}
	
	public double getRopeLength() {
		return ropeLength;
	}
	
	public double getFoodSupply() {
		return foodSupply;
	}
	
	public double getWaterSupply() {
		return waterSupply;
	}
	
	public void pay(double amount)
	{
		money-=amount;
	}
	public boolean walk(double distance)
	{
		double requiredFood = distance * FOOD_PER_PERSON_PER_MILE * people;
		double requiredWater = distance * WATER_PER_PERSON_PER_MILE * people;
		if ((requiredFood <= foodSupply) && (requiredWater <= waterSupply))
		{
			foodSupply -= requiredFood;
			waterSupply -= requiredWater;
			return true;
		}
		return false;
	}
	
	public Expedition clone() {
		return new Expedition(people, money, hasTents, ropeLength, foodSupply, waterSupply);
	}
}
