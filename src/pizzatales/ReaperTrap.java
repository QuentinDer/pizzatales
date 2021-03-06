package pizzatales;

import java.awt.Image;

public class ReaperTrap extends Item {

	public static Image pizzatrap, potiontrap, potiontrap1;
	
	private Image sprite;
	
	public ReaperTrap(int x, int y, int deltapx, int deltapy,
			boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		int random = (int)(Math.random()*20);
		if (random < 10)
			sprite = potiontrap;
		else if (random < 19)
			sprite = potiontrap1;
		else
			sprite = pizzatrap;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
		if (StartingClass.map[posx-1][posy] == null) {
			int postx = posx-1;
			int posty = posy;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx+1][posy] == null) {
			int postx = posx+1;
			int posty = posy;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx][posy-1] == null) {
			int postx = posx;
			int posty = posy-1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx][posy+1] == null) {
			int postx = posx;
			int posty = posy+1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx-1][posy-1] == null) {
			int postx = posx-1;
			int posty = posy-1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx-1][posy+1] == null) {
			int postx = posx-1;
			int posty = posy+1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx+1][posy-1] == null) {
			int postx = posx+1;
			int posty = posy-1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		if (StartingClass.map[posx+1][posy+1] == null) {
			int postx = posx+1;
			int posty = posy+1;
			int tcenterX = 50*postx+25+StartingClass.getBg().getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*posty+25+StartingClass.getBg().getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
		}
		CarolinaReaper.trapscount--;
		CarolinaReaper.notifytrap = true;
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (Math.abs(p.getCenterX()-centerX) < 20 && Math.abs(p.getCenterY()-centerY) < 20) {
			doEffect(p);
			return true;
		} else
			return false;
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

	@Override
	protected int getEffectCenterX() {
		return 0;
	}

	@Override
	protected int getEffectCenterY() {
		return 0;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
		// TODO Auto-generated method stub
		
	}
}
