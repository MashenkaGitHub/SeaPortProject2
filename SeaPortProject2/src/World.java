import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * File: World.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2 
 * UMUC CMSC 335 
 * World class is extends Thing and has
 * methods to construct ports and all internal to the port data, has methods to
 * support search functionality by index, name and skill for specific list
 * Class suppled with sort methods for sorting port data by weight, lenght, width, draft, name, skill, and index
 */
public class World extends Thing {
	private ArrayList<SeaPort> ports;
	private HashMap<Integer, SeaPort> portsMap = new HashMap<Integer, SeaPort>();
	private HashMap<Integer, Dock> docksMap = new HashMap<Integer, Dock>();
	private HashMap<Integer, Ship> shipsMap = new HashMap<Integer, Ship>();
	private HashMap<Integer, Person> personMap = new HashMap<Integer, Person>();
	private PortTime time;


	
	public World(Scanner sc) {
		super(sc);
		ports = new ArrayList<>();

	}

	void process(String st) {
		System.out.println("Processing >" + st + "<");
		Scanner sc = new Scanner(st);
		if (!sc.hasNext())
			return;
		switch (sc.next()) {
		case "port":
			addPort(sc);
			break;
		case "dock":
			addDock(sc);
			break;
		case "pship":
			addPassengerShip(sc);
			break;
		case "cship":
			addCargoShip(sc);
			break;
		case "person":
			addPerson(sc);
			break;
		case "job":
			addJob(sc);
			break;
		}
	}


	public String searchByName(String name) {
		for (int i = 0; i < ports.size(); i++) {
			if (ports.get(i).getName().equalsIgnoreCase(name)) {
				return ports.get(i).toString();
			}
			
			for (int p = 0; p < ports.get(i).getDocks().size(); p++) {
				if (ports.get(i).getDocks().get(p).getName().equalsIgnoreCase(name)) {
					return ports.get(i).getDocks().get(p).toString();
				}
			}
			for (int s = 0; s < ports.get(i).getShips().size(); s++) {
				if (ports.get(i).getShips().get(s).getName().equalsIgnoreCase(name)) {
					return ports.get(i).getShips().get(s).toString();
				}
			}
			for (int s = 0; s < ports.get(i).getPersons().size(); s++) {
				if (ports.get(i).getPersons().get(s).getName().equalsIgnoreCase(name)) {
					return ports.get(i).getPersons().get(s).toString();
				}
			}
		}
		return "Search returned 0 results. \nPlease enter another name.";
	}

	public String searchBySkill(String skill) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ports.size(); i++) {
			for (int s = 0; s < ports.get(i).getPersons().size(); s++) {
				if (ports.get(i).getPersons().get(s).getSkill().equalsIgnoreCase(skill)) {
					sb.append(ports.get(i).getPersons().get(s).toString() + "\n");
				}
			}
		}

		if (sb.equals(null) || (sb.toString().equals(""))) {
			return "Search returned 0 results. \nPlease enter another skill.";
		}
		return sb.toString();
	}

	private void addPerson(Scanner sc) {
		Person person = new Person(sc);
		personMap.put(person.getIndex(), person);
		ports.get(findPortByIndex(person.getParent())).getPersons().add(person);

	}

	private int findPortIndexByDock(int parentIndex) {
		for (int i = 0; i < ports.size(); i++) {
			for (int b = 0; b < ports.get(i).getDocks().size(); b++) {
				if (ports.get(i).getDocks().get(b).getIndex() == parentIndex) {
					System.out.println("Found Dock!!!: " + ports.get(i).getDocks().get(b).getIndex());
					return i;
				}
			}
		}
		return -1;
	}

	private int findPortByIndex(int parentIndex) {
		for (int i = 0; i < ports.size(); i++) {
			if (ports.get(i).getIndex() == parentIndex) {
				return i;
			}
		}

		return -1;
	}

	private void addCargoShip(Scanner sc) {
		CargoShip cship = new CargoShip(sc);
		shipsMap.put(cship.getIndex(), cship);
		if (findPortByIndex(cship.getParent()) == -1) {
			ports.get(findPortIndexByDock(cship.getParent())).getDocks().get(findDockByIndex(cship.getParent()))
					.setShip(cship);
			ports.get(findPortIndexByDock(cship.getParent())).getShips().add(cship);

		} else {
			ports.get(findPortByIndex(cship.getParent())).getQue().add(cship);
			ports.get(findPortByIndex(cship.getParent())).getShips().add(cship);
		}

	}

	private void addPassengerShip(Scanner sc) {
		PassengerShip pship = new PassengerShip(sc);
		shipsMap.put(pship.getIndex(), pship);
		if (findPortByIndex(pship.getParent()) == -1) {
			ports.get(findPortIndexByDock(pship.getParent())).getDocks().get(findDockByIndex(pship.getParent()))
					.setShip(pship);
			ports.get(findPortIndexByDock(pship.getParent())).getShips().add(pship);
		} else {
			ports.get(findPortByIndex(pship.getParent())).getQue().add(pship);
			ports.get(findPortByIndex(pship.getParent())).getShips().add(pship);
		}

	}

	private int findDockByIndex(int parent) {
		for (int i = 0; i < ports.size(); i++) {
			for (int b = 0; b < ports.get(i).getDocks().size(); b++) {
				if (ports.get(i).getDocks().get(b).getIndex() == parent) {
					System.out.println("Found Dock!!!: " + ports.get(i).getDocks().get(b).getIndex());
					return b;
				}
			}
		}
		return -1;
	}

	private void addDock(Scanner sc) {
		Dock dock = new Dock(sc);
		docksMap.put(dock.getIndex(), dock);
		ports.get(findPortByIndex(dock.getParent())).getDocks().add(dock);
	} // end addDock
	
	private void addJob(Scanner sc) {
		Job job = new Job(sc);


	} // end addJob
	/**
	 * method creates new port and add port to HashMap of ports and List of ports
	 * 
	 * @param Scanner
	 *            sc
	 */
	private void addPort(Scanner sc) {
		SeaPort seaPort = new SeaPort(sc);
		portsMap.put(seaPort.getIndex(), seaPort);
		ports.add(seaPort);
	} // end addPort

	public Person getPersonByIndex(int index) {
		return personMap.get(index);
	}// end getPersonByIndex

	public Ship getShipByIndex(int index) {
		return shipsMap.get(index);
	}// end getShipByIndex

	public Dock getDockByIndex(int index) {
		return docksMap.get(index);
	}// end getDockByIndex

	public SeaPort getPortByIndex(int index) {
		return portsMap.get(index);
	} // end getPortByIndex
	/**
	 * method performs sort by index all ports, docks, ships, and people
	 */
	public void sortByIndex() {
		//if more then one port, sort ports by index
		if(getPorts().size()>1) {
		//sort ports by index
		Collections.sort(getPorts(), new Comparator<SeaPort>() {
			public int compare(SeaPort port1, SeaPort port2) {
				return (port1.getIndex()<port2.getIndex() ? -1 : (port1.getIndex() == port2.getIndex() ? 0:1));
			}
		});
		}// end if block
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getIndex()<ship2.getIndex() ? -1 : (ship1.getIndex() == ship2.getIndex() ? 0:1));
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getIndex()<ship2.getIndex() ? -1 : (ship1.getIndex() == ship2.getIndex()? 0:1));
				}
			});
			//sort people in all people list
			Collections.sort(seaPort.getPersons(), new Comparator<Person>() {
				public int compare(Person person1, Person person2) {
					return (person1.getIndex()<person2.getIndex() ? -1 : (person1.getIndex() == person2.getIndex()? 0:1));
				}
			});
			//sort docks in all docks list
			Collections.sort(seaPort.getDocks(), new Comparator<Dock>() {
				public int compare(Dock dock1, Dock dock2) {
					return (dock1.getIndex()<dock2.getIndex() ? -1 : (dock1.getIndex() == dock2.getIndex()? 0:1));
				}
			});
		}
		
	}//end method sortByIndex
	/**
	 * method performs sort of the ships in the Que and in the list of all the ships
	 */
	public void sortByWeight() {
		
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getWeight()<ship2.getWeight() ? -1 : (ship1.getWeight() == ship2.getWeight() ? 0:1));
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getWeight()<ship2.getWeight() ? -1 : (ship1.getWeight() == ship2.getWeight() ? 0:1));
				}
			});
		}
		
	}//end method sortByWeight
	
	/**
	 * method performs sort of the ships in the Que and in the list of all the ships
	 */
	public void sortByLength() {
		
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getLength()<ship2.getLength() ? -1 : (ship1.getLength() == ship2.getLength() ? 0:1));
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getLength()<ship2.getLength() ? -1 : (ship1.getLength() == ship2.getLength() ? 0:1));
				}
			});
		}
		
	}//end method sortByDraft
	/**
	 * method performs sort of the ships in the Que and in the list of all the ships
	 */
	public void sortByDraft() {
		
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getDraft()<ship2.getDraft() ? -1 : (ship1.getDraft() == ship2.getDraft() ? 0:1));
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getDraft()<ship2.getDraft() ? -1 : (ship1.getDraft() == ship2.getDraft()? 0:1));
				}
			});
		}
		
	}//end method sortByDraft
	
	/**
	 * method performs sort of the ships in the Que and in the list of all the ships
	 */
	public void sortByWidth() {
		
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getWidth()<ship2.getWidth() ? -1 : (ship1.getWidth() == ship2.getWidth() ? 0:1));
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					return (ship1.getWidth()<ship2.getWidth() ? -1 : (ship1.getWidth() == ship2.getWidth()? 0:1));
				}
			});
		}
		
	}//end method sortByWidth
	/**
	 * method performs sort of the skills in the people list
	 */
	public void sortBySkill() {

		for (SeaPort seaPort : ports) {

			//sort by people's skills in all people list
			Collections.sort(seaPort.getPersons(), new Comparator<Person>() {
				public int compare(Person person1, Person person2) {
					int res = String.CASE_INSENSITIVE_ORDER.compare(person1.getSkill(), person2.getSkill());
					return (res !=0) ? res: person1.getSkill().compareTo(person2.getSkill());
				}
			});

		}
		
	}//end method sortBySkill
	/**
	 * method performs sort by name for all ports, ships, docks, and people
	 */
	public void sortByName() {
		//if more then one port, sort ports by name
		if(getPorts().size()>1) {
		//sort ports by name
		Collections.sort(getPorts(), new Comparator<SeaPort>() {
			public int compare(SeaPort port1, SeaPort port2) {
				int res = String.CASE_INSENSITIVE_ORDER.compare(port1.getName(), port2.getName());
				return (res !=0) ? res: port1.getName().compareTo(port2.getName());
			}
		});
		}// end if block
		
		
		for (SeaPort seaPort : ports) {
			//sort ships in the Que
			Collections.sort(seaPort.getQue(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					int res = String.CASE_INSENSITIVE_ORDER.compare(ship1.getName(), ship2.getName());
					return (res !=0) ? res: ship1.getName().compareTo(ship2.getName());
				}
			});
			//sort ships in all ships list
			Collections.sort(seaPort.getShips(), new Comparator<Ship>() {
				public int compare(Ship ship1, Ship ship2) {
					int res = String.CASE_INSENSITIVE_ORDER.compare(ship1.getName(), ship2.getName());
					return (res !=0) ? res: ship1.getName().compareTo(ship2.getName());
				}
			});
			//sort people in all people list
			Collections.sort(seaPort.getPersons(), new Comparator<Person>() {
				public int compare(Person person1, Person person2) {
					int res = String.CASE_INSENSITIVE_ORDER.compare(person1.getName(), person2.getName());
					return (res !=0) ? res: person1.getName().compareTo(person2.getName());
				}
			});
			//sort docks in all docks list
			Collections.sort(seaPort.getDocks(), new Comparator<Dock>() {
				public int compare(Dock dock1, Dock dock2) {
					int res = String.CASE_INSENSITIVE_ORDER.compare(dock1.getName(), dock2.getName());
					return (res !=0) ? res: dock1.getName().compareTo(dock2.getName());
				}
			});
		}
		
	}//end method sortByName
	
	public ArrayList<SeaPort> getPorts() {
		return ports;
	}

	public void setPorts(ArrayList<SeaPort> ports) {
		this.ports = ports;
	}

	public PortTime getTime() {
		return time;
	}

	public void setTime(PortTime time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		String st = "The world: ";
		for (SeaPort sp : ports)
			st += sp.toString();
		return st;

	}// end method toString
}
