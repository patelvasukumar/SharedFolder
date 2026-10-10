package ToString;

public class Demo {

	private int age;
	private String name;
	
	public Demo(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public String ToString() {
		return "Person[name = " + name + ", age = " + age + "]";
	}
	
	public static void main(String[] args) {
		Demo person = new Demo(18, "Vasu");
		
		System.out.println(person.ToString());
		
	}
	
}
