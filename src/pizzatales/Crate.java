package pizzatales;

public class Crate extends DestroyableTile {

	public Crate(int x, int y) {
		super(x, y, 2);
	}

	@Override
	public void doOnDestruction() {
		//StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
	}
}

