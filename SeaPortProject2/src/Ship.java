import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: Ship.java 
 * Author: Maria Tkacheva 
 * Date: March 24, 2018
 * Assignment:Project 1 
 * UMUC CMSC 335 
 * Ship class extends Thing and has
 * ArrayLists to hold Port details
 */
public class Ship extends Thing {
	private PortTime arrivalTime, dockTime;
	private double draft, length, weight, width;
	private ArrayList<Job> jobs;

	public Ship(Scanner sc) {
		super(sc);
		weight = sc.nextDouble();
		length = sc.nextDouble();
		width = sc.nextDouble();
		draft = sc.nextDouble();
		jobs = new ArrayList<Job>();
		dockTime = new PortTime();

	}

	public PortTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(PortTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public PortTime getDockTime() {
		return dockTime;
	}

	public void setDockTime(PortTime dockTime) {
		this.dockTime = dockTime;
	}

	public double getDraft() {
		return draft;
	}

	public void setDraft(double draft) {
		this.draft = draft;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	public String toString() {

		String st = super.toString();

		if (getJobs().size() == 0)

			return st;

		for (Job mj : getJobs())
			st += "\n       - " + mj.toString();

		return st;

	}// end method toString

}
