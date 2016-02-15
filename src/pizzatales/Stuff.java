package pizzatales;

public abstract class Stuff extends Point {

	int speedX = 0;
	int speedY = 0;
	Player player = StartingClass.getPlayer();
	
	public Stuff(int centerX, int centerY) {
		super(centerX, centerY);
	}
	
	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public void update() {
		centerX += speedX;
		centerY += speedY - player.getScrollingSpeed();
	}
}
