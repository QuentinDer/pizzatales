package pizzatales;

public abstract class Stuff extends Point {

	float speedX = 0;
	float speedY = 0;
	float fcenterX;
	float fcenterY;
	Player player = StartingClass.getPlayer();
	
	public Stuff(int centerX, int centerY) {
		super(centerX, centerY);
		fcenterX = centerX;
		fcenterY = centerY;
	}
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	
	@Override
	public void setCenterX(int centerX) {
		super.setCenterX(centerX);
		fcenterX = centerX;
	}
	
	@Override
	public void setCenterY(int centerY) {
		super.setCenterY(centerY);
		fcenterY = centerY;
	}
	
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public void update() {
		fcenterX += speedX - player.getScrollingSpeedX();
		fcenterY += speedY - player.getScrollingSpeedY();
		centerX = (int)fcenterX;
		centerY = (int)fcenterY;
	}
}
