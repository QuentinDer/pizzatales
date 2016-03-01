package pizzatales;

public abstract class DestroyableTile extends Tile {

	public int life;
	
	public DestroyableTile(int x, int y, int life) {
		super(x, y);
		this.life = life;
	}
	
	public boolean damage(int dmg) {
		life -= dmg;
		if (life < 1) {
			StartingClass.getTilearray().remove(this);
			StartingClass.destroyabletiles.remove(this);
			StartingClass.map[posx][posy] = null;
			doOnDestruction();
			return true;
		}
		return false;
	}
	
	protected abstract void doOnDestruction();
}
