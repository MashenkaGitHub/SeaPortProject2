import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: Job.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2 
 * UMUC CMSC 335 
 * Job class extends Thing 
 * This class will be used in further
 * parts of the project (Part 3 and 4)
 */
public class Job extends Thing {
	private double duration;
	private ArrayList<String> requirements;
	public Job(Scanner sc) {
		super(sc);
		if (sc.hasNext())
			setDuration(sc.nextDouble());
		if(sc.hasNext()) sc.next();
	}
	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public ArrayList<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<String> requirements) {
		this.requirements = requirements;
	}
	
	@Override
	public String toString() {
		return "Job: " + super.toString() + " " + duration;
	}// end method toString
}
