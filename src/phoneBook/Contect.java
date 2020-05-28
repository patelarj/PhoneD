package phoneBook;

public class Contect {
	
	private int id;
	private String name;
	private String number;
public Contect( int id, String name, String number) {
	super();
	this.id = id;
	this.name = name;
	this.number = number;
	}	
public Contect(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
		


}
