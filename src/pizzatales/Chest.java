package pizzatales;

public class Chest extends DestroyableTile {

	public Chest(int x, int y) {
		super(x, y, 12);
	}

	@Override
	public void doOnDestruction() {
		StartingClass.getExplosions().add(new ChestOpen(this.centerX,this.centerY));
	}
}
