package character;


public class Student {

	private String name;
	private String greeting;
	Personality personality;
	
	public Student(String name, String greeting, Personality personality){
		this.setName(name);
		this.setGreeting(greeting);
		this.personality = personality;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public enum Personality{
		NICE, MEAN, STUDIOUS;
	}
	
	
}
