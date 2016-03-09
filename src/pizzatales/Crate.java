package pizzatales;

public class Crate extends DestroyableTile {

	public Crate(int x, int y) {
		super(x, y, 8);
	}

	@Override
	public void doOnDestruction() {
		StartingClass.getExplosions().add(new CrateExplosion(this.centerX,this.centerY));
	}
}

