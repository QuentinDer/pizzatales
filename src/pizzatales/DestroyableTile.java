package pizzatales;

public abstract class DestroyableTile extends Tile {

	public int life;
	
	public DestroyableTile(int x, int y, int life) {
		super(x, y);
		this.life = life;
	}
	
	public void damage(int dmg) {
		life -= dmg;
		if (life < 0) {
			StartingClass.getTilearray().remove(this);
			StartingClass.map[posx][posy] = null;
			doOnDestruction();
		}
	}
	
	protected abstract void doOnDestruction();
}
