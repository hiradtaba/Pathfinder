package Project5;

//Name: Hirad Tabatabaei
//Class: ECE 122
//Instructor: Professor Marzulo
//Due date: March 5, 2018

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Adventurer {

	public static Scanner s;
	public static ArrayList<Place> map;
	public static ArrayList<ArrayList<Place>> routes;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		
		map = createMap();
		routes = createRoutes(map);
		
		System.out.println("All routes:");
		printRoutes(routes);
		
		System.out.println();
		System.out.println();
		Expedition e = createExpedition();
		System.out.println();
		System.out.println();
		
		removeBadRoutes(routes, e);
		
		printRoutes(routes);
		
		
		s.close();
	}
	
	/**
	 * Removes routes that could not be taken by an expedition due to lack of resources.
	 * @param routes a list of routes (permutations of places on map)
	 * @param e Expedition
	 */
	public static void removeBadRoutes(ArrayList<ArrayList<Place>> routes, Expedition e) {
		//TODO implement this method.
		ArrayList<ArrayList<Place>> list = (ArrayList<ArrayList<Place>>) routes.clone();
		routes.clear();
		for(int i=0; i<list.size(); i++) {
			if(isGoodRoute(list.get(i), e)) {
				routes.add(list.get(i));
			}
		}
	}
	
	/** Would expedition succeed when taking that route? 
	 * @param r route
	 * @param e expedition
	 * @return boolean indicating success.
	 */
	public static boolean isGoodRoute(ArrayList<Place> r, Expedition e) {
		Expedition ne = e.clone();
		Place src = r.get(0);
		Place dst;
		int i;
		for (i=1; i<r.size(); i++)
		{
			dst = r.get(i);
			if (src.goTo(ne, dst))
			{
				src = dst;
			}
			else
			{
				break;
			}
		}
		if (i != r.size())
		{
			return false;
		}
		return true;
	}
	
	/** Swaps 2 places in a list (used for permutation)
	 * 
	 * @param map list of paces
	 * @param i one place to be swapped
	 * @param j the other place to be swapped
	 */
	public static void swap(ArrayList<Place> map, int i, int j) {
		//TODO implement this method.
		Place pl = map.get(i);
		map.set(i, map.get(j));
		map.set(j, pl);
	}
	
	/** Recursive method that calculate possible permutations on a list of places
	 * 
	 * @param map list of places
	 * @param routes list of routes (will be filled in as paths in recursion tree are traversed)
	 * @param pos current pivot element for permutation. 
	 */
	public static void permute(ArrayList<Place> map, ArrayList<ArrayList<Place>> routes, int pos) {
	//TODO implement this method. You must call swap in your solution
		for(int i=pos; i<map.size(); i++) {
			swap(map,pos,i);
			permute(map,routes,pos+1);
			swap(map,pos,i);
			if(pos==map.size()-1) {
				ArrayList<Place> path =(ArrayList<Place>) map.clone();
				path.add(map.get(0));
				routes.add(path);
			}
		}
		
	}
	
	/** Create all possible routes for given map. 
	 * The cost of taking routes is not taken into account. 
	 * First place listed on map should be starting point and ending point for all routes
	 * 
	 * @param map the list of places
	 * @return list of routes (permutations)
	 */
	public static ArrayList<ArrayList<Place>> createRoutes(ArrayList<Place> map) {
		//TODO implement this method. You must call permute in your solution.
		 ArrayList<ArrayList<Place>> result = new  ArrayList<ArrayList<Place>>();
		 permute(map,result,1); 
		return result;
	}
	
	/** The map!
	 * 
	 * @return List of places
	 */
	public static ArrayList<Place> createMap()
	{
		//A small map like that would already result in 362880 possible routes. Do not add more Places to it.
		ArrayList<Place> route = new ArrayList<Place>();
		Place src = new Camp("Base Camp", 2, 1, 20);
		route.add(src);
		route.add(new Field("Lavander", 0, 0));
		route.add(new Obstacle("Connecticut River", 0, 1, 40));
		route.add(new Obstacle("Sugarloaf", 1, 1, 50));
		route.add(new Camp("Tall Oak Camp", 2, 2, 20));
		route.add(new Field("Corn", 2, 3));
		route.add(new Obstacle("Mt. Moran", 3, 3, 300));
		route.add(new Lodge("The Adventurer Lodge", 5, 5, 50, 100));
		route.add(new Obstacle("Snake Stream", 4, 5, 5));
		route.add(new Field("Soy", 1, 3));
		return route;
	}
	
	/** Print all routes in a list
	 * 
	 * @param routes list of routes
	 */
	public static void printRoutes(ArrayList<ArrayList<Place>> routes) {
		//TODO implement this method. You must call printRoute in your solution.

		for(int i=0; i<routes.size(); i++) {
			printRoute(routes.get(i));
		}
	}
	
	/** Prints one route (from P4)
	 * 
	 * @param route the route.
	 */
	public static void printRoute(ArrayList<Place> route)
	{
		if (route.size()>0)
		{
			double distance=0;
			Place src = route.get(0);
			System.out.println(src + " (starting point)");
			Place dst;
			for (int i=1; i<route.size(); i++)
			{
				dst = route.get(i);
				double d = src.getDistance(dst);
				distance += d;
				System.out.println(dst + " (+ " + d + " miles)");
				src=dst;
			}
			System.out.println("Total distance is " + distance + " miles.");
		}
	}

	/**
	 * Reads a positive (>0) int number
	 * @param msg string representing what is being read. The method will print a message to the user.
	 * @return int number.
	 */
	public static int readPositiveInt(String msg) {
		/* TODO include Exception handling (InputMismatchException). 
		 * You must also verify if input is positive (>0).
		 * You must use recursion to read the value again in case it is not correct
		 */
		
		int val;
		System.out.print(msg + " (must be positive integer): ");
		try {
			val = s.nextInt();
			if(val<=0) {
				throw new IllegalArgumentException();
				
			}
			}
			catch(InputMismatchException e) {
				s.nextLine();
				System.out.println("Non-integers aren't accepted. Only postive integers are accepted. Please try again.");
				val = readPositiveInt(msg);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Non-positive integers aren't accepted. Only positive integers are accepted. Please try again.");
				val = readPositiveInt(msg);
			}
		
		return val;
	}
	
	/**
	 * Reads a non negative (>=0) double number
	 * @param msg string representing what is being read. The method will print a message to the user.
	 * @return double number.
	 */
	public static double readNonNegativeDouble(String msg) {
		/* TODO include Exception handling (InputMismatchException). 
		 * You must also verify if input is >=0.
		 * You must use recursion to read the value again in case it is not correct
		 */
		
		double val;
		System.out.print(msg + " (must be double >=0): ");
		try {
		val = s.nextDouble();
		if(val<0) {
			throw new IllegalArgumentException();
		}
		}
		catch(InputMismatchException e) {
			s.nextLine();
			System.out.println("Non-doubles aren't accepted. Only postive doubles are accepted. Please try again.");
			val = readNonNegativeDouble(msg);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Negative doubles aren't accepted. Only positive doubles are accepted. Please try again.");
			val = readNonNegativeDouble(msg);
		}
		
		return val;
		
	}
	
	/** creates one expedition (user will input information)
	 * 
	 * @return expedition
	 */
	public static Expedition createExpedition()
	{
		int people;
		double money; 
		boolean hasTents;
		double ropeLength;
		double foodSupply;
		double waterSupply;
		
		System.out.println("Please enter expedition information:");
		
		people = readPositiveInt("Number of people");
		System.out.println("Setting number of people to "+people+".");
		
		
		money = readNonNegativeDouble("Money");
		System.out.println("Setting money to "+money+".");
		
		String choice;
		do
		{
			System.out.print("Do you have tents (Y/N): ");
			choice = s.next();
			
		} while (!choice.toUpperCase().equals("Y") && !choice.toUpperCase().equals("N"));
		hasTents = choice.toUpperCase().equals("Y");
		
		
		ropeLength = readNonNegativeDouble("Rope Length");
		System.out.println("Setting Rope Length to "+ropeLength+".");
		
		foodSupply = readNonNegativeDouble("Food Supply");
		System.out.println("Setting Food Supply to "+foodSupply+".");
		
		waterSupply = readNonNegativeDouble("Water Supply");
		System.out.println("Setting Water Supply to "+waterSupply+".");
		
		Expedition e = new Expedition(people, money, hasTents, ropeLength, foodSupply, waterSupply);
		
		return e;
	}
	
	
}
