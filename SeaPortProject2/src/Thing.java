import java.util.Scanner;
/**
 * File: Thing.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2 
 * UMUC CMSC 335 
 * Thing class is extended by other classes and has shared properties such as name, index and parent
 */
public class Thing implements Comparable<Thing> {
	private int index, parent;
	private String name;

	public Thing() {
	}

	public Thing(Scanner sc) {

		this.name = sc.next();
		this.index = sc.nextInt();
		this.parent = sc.nextInt();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Thing o) {
		
		return 0;
	}//end method compareTo

	@Override
	public String toString() {

		return name + " " + index;
	}//end method toString

}
