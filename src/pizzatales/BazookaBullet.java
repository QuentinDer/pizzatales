package pizzatales;

public class BazookaBullet extends Projectile {
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 450;
		this.width = 5;
		this.height = 5;
		this.damage = 12;
	}
}
