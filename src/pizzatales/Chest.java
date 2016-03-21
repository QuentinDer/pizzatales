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
		for (int h = 0; h <= StartingClass.heightitemmap[posx][posy]; h++) {
			StartingClass.items[nposx][nposy][h] = StartingClass.items[posx][posy][h];
			StartingClass.items[posx][posy][h] = null;
			StartingClass.items[nposx][nposy][h].setCenterX(tcenterX+50*(nposx-posx));
			StartingClass.items[nposx][nposy][h].setCenterY(tcenterY+50*(nposy-posy));
			StartingClass.items[nposx][nposy][h].r.setBounds(StartingClass.items[nposx][nposy][h].getCenterX() - 22, StartingClass.items[nposx][nposy][h].getCenterY() - 22, 45, 45);
			StartingClass.items[nposx][nposy][h].posx = nposx;
			StartingClass.items[nposx][nposy][h].posy = nposy;
		}
		int tmp = StartingClass.heightitemmap[posx][posy];
		StartingClass.heightitemmap[posx][posy] = -1;
		StartingClass.heightitemmap[nposx][nposy] = tmp;
	}
}
