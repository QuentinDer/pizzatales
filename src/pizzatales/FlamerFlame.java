package pizzatales;

public class FlamerFlame extends Projectile {
	public FlamerFlame(int startX, int startY, float vectorX, float vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 100;
		this.width = 5;
		this.height = 5;
		this.damage = 2;
	}
}
