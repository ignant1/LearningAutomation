package assignment1;

public class MathOperations {

	public int Square(int sq) {
		return (sq*sq);
	}
	
	public int Cube(int cb) {
		return (cb*cb*cb);
	}
	
	public boolean isEven (int en) {
		if(en % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
