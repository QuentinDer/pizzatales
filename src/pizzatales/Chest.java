package pizzatales;

public class Chest extends DestroyableTile {

	public Chest(int x, int y) {
		super(x, y, 12);
	}

	@Override
	public void doOnDestruction() {
		int tcenterX = 50*posx+25+bg.getCenterX()-StartingClass.bginitx;
		int tcenterY = 50*posy+25+bg.getCenterY()-StartingClass.bginity;
		Tile t = new Tile(tcenterX,tcenterY);
		t.setTileImage(StartingClass.tileChestOpen);
		StartingClass.getTilearray().add(t);
	}
}
