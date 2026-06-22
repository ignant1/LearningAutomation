package assignment2;

public class AssignmentTwo {
	public static int a = 7;
	public static int b = 63;
	
	public static void main(String[] args) {
		Car vehicle = new Car();
		Student pupil = new Student();
		ParameterTypes data = new ParameterTypes();
		Calculator abacus = new Calculator();
		
		System.out.println("Car brand is " + vehicle.getBrand());
		System.out.println("Car model is " + vehicle.getModel());
		System.out.println("Car year is " + vehicle.getYear());
		
		pupil.displayInfo();
		
		System.out.println("The integer is " + data.getInteger());
		System.out.println("The float is " + data.getFloat());
		System.out.println("The char is " + data.getChar());
		System.out.println("The String is " + data.getString());
		System.out.println("The boolean is " + data.getBoolean());
		
		System.out.println(a + " + " + b + " = " + abacus.Addition(a, b));
		System.out.println(a + " - " + b + " = " + abacus.Subtraction(a, b));
		System.out.println(a + " * " + b + " = " + abacus.Multiplication(a, b));
		System.out.println(a + " / " + (float)b + " = " + abacus.Division(a, (float)b));
	}
}
