package pizzatales;

public class Crate extends DestroyableTile {

	public Crate(int x, int y) {
		super(x, y, 5);
	}

	@Override
	public void doOnDestruction() {
		int maxheight = 0;
		for (Item it : StartingClass.items) {
			if (it.posx == posx && it.posy == posy) {
				it.height++;
				maxheight = Math.max(maxheight, it.height);
			}
		}
		StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, maxheight);
		StartingClass.heightitemmap[posx][posy] = maxheight;
		CrateOpen crateopen = new CrateOpen(posx,posy,0,0,true,0);
		crateopen.setCenterX(50*posx+25+bg.getCenterX()-StartingClass.bginitx);
		crateopen.setCenterY(50*posy+25+bg.getCenterY()-StartingClass.bginity);
		crateopen.r.setBounds(crateopen.getCenterX() - 22, crateopen.getCenterY() - 22, 45, 45);
		StartingClass.items.add(crateopen);
	}
}

