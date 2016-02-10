package pizzatales;

import java.util.HashMap;
import java.util.Map.Entry;

public class PathFinder {

	public int getDirection(int fromx, int fromy, int tox, int toy, int maxmp) {
		if (Math.abs(fromx-tox)+Math.abs(fromy-toy)>maxmp)
			return 0;
		boolean [][] map = StartingClass.mapmatrix;
		int width = map.length;
		int height = map[0].length;
		HashMap<Integer,Integer> g_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> direction = new HashMap<Integer,Integer>();
		int goal = tox*height + toy;
		if (0 != fromx) {
			int left = height*(fromx-1) + fromy;
			g_score.put(left, 1);
			f_score.put(left, Math.abs(fromy-toy)+Math.abs(fromx-1-tox)+1);
			direction.put(left, 1);
		}
		if (width - 1 != fromx) {
			int right = height*(fromx+1) + fromy;
			g_score.put(right, 1);
			f_score.put(right, Math.abs(fromy-toy)+Math.abs(fromx+1-tox)+1);
			direction.put(right, 3);
		}
		if (0 != fromy) {
			int up = height*fromx + fromy-1;
			f_score.put(up, Math.abs(fromy-1-toy)+Math.abs(fromx-tox)+1);
			g_score.put(up, 1);
			direction.put(up, 2);
		}
		if (height -1 != fromy) {
			int down = height*fromx + fromy+1;
			f_score.put(down, Math.abs(fromy+1-toy)+Math.abs(fromx-tox)+1);
			g_score.put(down, 1);
			direction.put(down, 4);
		}
		while (!f_score.isEmpty()) {
			/*String f = "f_score:";
			for (Entry<Integer,Integer> entry : f_score.entrySet())
				f += " " + entry.getKey() + "-" + entry.getValue();
			System.out.println(f);
			String g = "g_score:";
			for (Entry<Integer,Integer> entry : g_score.entrySet())
				g += " " + entry.getKey() + "-" + entry.getValue();
			System.out.println(g);
			String d = "direction:";
			for (Entry<Integer,Integer> entry : direction.entrySet())
				d += " " + entry.getKey() + "-" + entry.getValue();
			System.out.println(d);*/
			int current = getMin(f_score);
			if (current == goal)
				return direction.get(current);
			f_score.remove(current);
			if (g_score.get(current) == maxmp)
				continue;
			int currentx = current/height;
			int currenty = current % height;
			//System.out.println("Current: " + currentx+"-"+currenty);
			if (0 != currentx) {
				int left = height*(currentx-1) + currenty;
				if (!g_score.containsKey(left) && map[currentx-1][currenty]) {
					g_score.put(left, g_score.get(current)+1);
					f_score.put(left, Math.abs(currenty-toy)+Math.abs(currentx-1-tox)+g_score.get(current));
					direction.put(left, direction.get(current));
				}
			}
			if (width - 1 != currentx) {
				int right = height*(currentx+1) + currenty;
				if (!g_score.containsKey(right) && map[currentx+1][currenty]) {
					g_score.put(right, g_score.get(current)+1);
					f_score.put(right, Math.abs(currenty-toy)+Math.abs(currentx+1-tox)+g_score.get(current));
					direction.put(right, direction.get(current));
				}
			}
			if (0 != currenty) {
				int up = height*currentx + currenty-1;
				if (!g_score.containsKey(up) && map[currentx][currenty-1]) {
					f_score.put(up, Math.abs(currenty-1-toy)+Math.abs(currentx-tox)+g_score.get(current));
					g_score.put(up, g_score.get(current)+1);
					direction.put(up, direction.get(current));
				}
			}
			if (height -1 != currenty) {
				int down = height*currentx + currenty+1;
				if (!g_score.containsKey(down) && map[currentx][currenty+1]) {
					f_score.put(down, Math.abs(currenty+1-toy)+Math.abs(currentx-tox)+g_score.get(current));
					g_score.put(down, g_score.get(current)+1);
					direction.put(down, direction.get(current));
				}
			}
		}
		return 0;
	}
	
	private int getMin(HashMap<Integer,Integer> map) {
		int min = 100;
		int ans = 0;
		for (Entry<Integer,Integer> entry : map.entrySet()) {
			if (entry.getValue() < min) {
				min = entry.getValue();
				ans = entry.getKey();
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		StartingClass.mapmatrix = new boolean[8][16];
		StartingClass.mapmatrix[3][5] = true;
		StartingClass.mapmatrix[4][5] = true;
		StartingClass.mapmatrix[5][5] = true;
		StartingClass.mapmatrix[6][5] = true;
		PathFinder pathfinder = new PathFinder();
		System.out.println(pathfinder.getDirection(5, 6, 5, 3, 20));
	}
}
