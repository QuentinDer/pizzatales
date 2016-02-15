package pizzatales;

public class HitPoint {

	private Point holder;
	public int timer;
	private int relativex;
	private int relativey;
	
	public HitPoint(Point holder, int relativex, int relativey) {
		this.holder = holder;
		timer = 10;
		this.relativex = relativex;
		this.relativey = relativey;
	}
	
	public int getCenterX() {
		return holder.getCenterX() + relativex;
	}
	
	public int getCenterY() {
		return holder.getCenterY() + relativey;
	}
}
