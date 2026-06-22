package assignment3;

public class AssignmentThree extends Shape{
	public static void main(String[] args) {
		int radius = 3;
		int height = 6;
		int width = 5;
		AssignmentThree at = new AssignmentThree();
		Vehicle vehicle = new Vehicle();
		Car car = new Car();
		Bicycle bicycle = new Bicycle();
		Truck truck = new Truck();
		Employee employee = new Employee();
		Manager manager = new Manager();
		
		System.out.println("Are of a circle with radius " + radius + " is " + at.Area(radius));
		System.out.println("Are of a rectangle with height " + height + " and width " + width + " is " + at.Area(height, width));
		
		vehicle.Speed();
		car.Speed();
		bicycle.Speed();
		truck.Speed();
		
		System.out.println("Employee basic Salary is $" + employee.calculateSalary(45));
		System.out.println("Employee basic Salary plus allowance is $" + employee.calculateSalary(45, 5000));
		System.out.println("Manager Salary is $" + manager.calculateSalary(65,15000));
	}
}
