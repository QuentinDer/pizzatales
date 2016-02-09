package pizzatales;

public class RifleBullet extends Projectile {
	public RifleBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 480;
		this.width = 5;
		this.height = 5;
		this.damage = 2;
	}
}