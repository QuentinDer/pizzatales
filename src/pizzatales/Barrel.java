package pizzatales;

public class Barrel extends DestroyableTile {

	public Barrel(int x, int y) {
		super(x, y, 2);
	}

	@Override
	public void doOnDestruction() {
		StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
	}
}
