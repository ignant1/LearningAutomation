package assignment4;

public class AssignmentFour {
	public static void main(String[] args) {
		EmployeeSalary es = new EmployeeSalary();
		es.setName("Samir Gupta");
		es.setEmployeeId(45135);
		es.setSalary(85000);
		
		System.out.println("Employee, " + es.getName() + ", id# " + es.getEmployeeId() + ", has a salary of $" + es.getSalary());
	}

}
