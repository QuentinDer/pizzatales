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
		int nposx = posx;
		int nposy = posy;
		if (Tile.class.isInstance(StartingClass.map[posx][posy+1])) {
			if (Tile.class.isInstance(StartingClass.map[posx][posy-1])) {
				if (Tile.class.isInstance(StartingClass.map[posx-1][posy])) {
					if (!Tile.class.isInstance(StartingClass.map[posx+1][posy]))
						nposx++;
				} else
					nposx--;
			} else
				nposy--;
		} else
			nposy++;
		for (Item it : StartingClass.items) {
			if (it.posx == posx && it.posy == posy) {
				it.posx = nposx;
				it.posy = nposy;
				it.setCenterX(tcenterX+50*(nposx-posx));
				it.setCenterY(tcenterY+50*(nposy-posy));
				it.r.setBounds(it.getCenterX() - 22, it.getCenterY() - 22, 45, 45);
			}
		}
		int tmp = StartingClass.heightitemmap[posx][posy];
		StartingClass.heightitemmap[posx][posy] = 0;
		StartingClass.heightitemmap[nposx][nposy] = tmp;
	}
}
