package pizzatales;

public abstract class Point {

	int centerX;
	int centerY;
	public Point(int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public abstract void update();
	
	public int getCenterX() {
		return centerX;
	}
	
	public int getCenterY() {
		return centerY;
	}
	
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
}
