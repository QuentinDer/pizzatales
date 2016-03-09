package pizzatales;

public class Chest extends DestroyableTile {

	public Chest(int x, int y) {
		super(x, y, 999999);
	}

	@Override
	public void doOnDestruction() {
		//StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
	}
}
