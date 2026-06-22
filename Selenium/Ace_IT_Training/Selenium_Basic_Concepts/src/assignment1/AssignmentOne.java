package assignment1;

public class AssignmentOne {

	public static void main(String[] args) {
		int square = 9;
		int cube = 14;
		int even1 = 27;
		int even2 = 52;
		
		MathOperations mo = new MathOperations();
		new Book();
		new Employee();
		
		System.out.println("The Square of " + square + " is: " + mo.Square(square));
		System.out.println("The Cube of " + cube + " is: " + mo.Cube(cube));
		System.out.println("Is " + even1 + " even? "+ mo.isEven(even1));
		System.out.println("Is " + even2 + " even? "+ mo.isEven(even2));
	}

}
