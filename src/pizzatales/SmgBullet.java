package pizzatales;

public class SmgBullet extends Projectile {
	public SmgBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 180;
		this.width = 5;
		this.height = 5;
		this.damage = 1;
	}
}
