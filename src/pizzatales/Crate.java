package pizzatales;

public class Crate extends DestroyableTile {

	public Crate(int x, int y) {
		super(x, y, 2);
	}

	@Override
	public void doOnDestruction() {
		CrateOpen crateopen = new CrateOpen(posx,posy,0,0,true,0);
		crateopen.setCenterX(50*posx+25+bg.getCenterX()-StartingClass.bginitx);
		crateopen.setCenterY(50*posy+25+bg.getCenterY()-StartingClass.bginity);
		crateopen.r.setBounds(crateopen.getCenterX() - 22, crateopen.getCenterY() - 22, 45, 45);
		StartingClass.leavingitems.add(crateopen);
	}
}

