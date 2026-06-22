package assignment3;

public class Manager extends Employee{
	@Override
	public String calculateSalary(float rate, int allowance) {
		return (String.format("%.2f", (rate * 40 * 52) + allowance + 20000));
	}
}
