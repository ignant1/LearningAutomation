package assignment3;

public class Employee {
	public String calculateSalary(float rate) {
		return (String.format("%.2f", rate * 40 * 52));
	}
	
	public String calculateSalary(float rate, int allowance) {
		return (String.format("%.2f", (rate * 40 * 52) + allowance));
	}
}
