import java.util.Scanner;

/**
 * File: Person.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2 
 * UMUC CMSC 335 
 * Person class extends Thing and has cargo
 * ship specific variables
 */
public class Person extends Thing {
	private String skill;

	public Person(Scanner sc) {
		super(sc);
		if (sc.hasNext())
			setSkill(sc.next());
	}

	public String getSkill() {
		return skill;
	}

	private void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "Person: " + super.toString() + " " + skill;
	}// end method toString

}
