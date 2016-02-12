package pizzatales;

import java.util.HashMap;
import java.util.Map.Entry;

public class PathFinder {
	
	public boolean[][] map;

	public int getDirection(int fromx, int fromy, int tox, int toy, int maxmp) {
		if (Math.abs(fromx-tox)+Math.abs(fromy-toy)>maxmp)
			return 0;
		int width = map.length;
		int height = map[0].length;
		HashMap<Integer,Integer> g_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> direction = new HashMap<Integer,Integer>();
		int goal = tox*height + toy;
		if (0 != fromx && map[fromx-1][fromy]) {
			int left = height*(fromx-1) + fromy;
			g_score.put(left, 1);
			f_score.put(left, Math.abs(fromy-toy)+Math.abs(fromx-1-tox)+1);
			direction.put(left, 1);
		}
		if (width - 1 != fromx && map[fromx+1][fromy]) {
			int right = height*(fromx+1) + fromy;
			g_score.put(right, 1);
			f_score.put(right, Math.abs(fromy-toy)+Math.abs(fromx+1-tox)+1);
			direction.put(right, 3);
		}
		if (0 != fromy && map[fromx][fromy-1]) {
			int up = height*fromx + fromy-1;
			f_score.put(up, Math.abs(fromy-1-toy)+Math.abs(fromx-tox)+1);
			g_score.put(up, 1);
			direction.put(up, 2);
		}
		if (height -1 != fromy && map[fromx][fromy+1]) {
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
	
	public int getDirectionToShoot(int fromx, int fromy, int tox1, int toy1, int tox2, int toy2, int maxmp) {
		if (Math.abs(fromx-tox1)+Math.abs(fromy-toy1)>maxmp || Math.abs(fromx-tox2)+Math.abs(fromy-toy2)>maxmp)
			return 0;
		int width = map.length;
		int height = map[0].length;
		HashMap<Integer,Integer> g_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> direction = new HashMap<Integer,Integer>();
		if (0 != fromx && map[fromx-1][fromy]) {
			int left = height*(fromx-1) + fromy;
			g_score.put(left, 1);
			f_score.put(left, Math.min(Math.abs(fromy-toy1)+Math.abs(fromx-1-tox1)+1,Math.abs(fromy-toy2)+Math.abs(fromx-1-tox2)+1));
			direction.put(left, 1);
		}
		if (width - 1 != fromx && map[fromx+1][fromy]) {
			int right = height*(fromx+1) + fromy;
			g_score.put(right, 1);
			f_score.put(right, Math.min(Math.abs(fromy-toy1)+Math.abs(fromx+1-tox1)+1,Math.abs(fromy-toy2)+Math.abs(fromx+1-tox2)+1));
			direction.put(right, 3);
		}
		if (0 != fromy && map[fromx][fromy-1]) {
			int up = height*fromx + fromy-1;
			f_score.put(up, Math.min(Math.abs(fromy-1-toy1)+Math.abs(fromx-tox1)+1,Math.abs(fromy-1-toy2)+Math.abs(fromx-tox2)+1));
			g_score.put(up, 1);
			direction.put(up, 2);
		}
		if (height -1 != fromy && map[fromx][fromy+1]) {
			int down = height*fromx + fromy+1;
			f_score.put(down, Math.min(Math.abs(fromy+1-toy1)+Math.abs(fromx-tox1)+1,Math.abs(fromy+1-toy2)+Math.abs(fromx-tox2)+1));
			g_score.put(down, 1);
			direction.put(down, 4);
		}
		while (!f_score.isEmpty()) {
			int current = getMin(f_score);
			if (current / height == tox1 || current % height == toy1 || current / height == tox2 || current % height == toy2)
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
					f_score.put(left, Math.min(Math.abs(currenty-toy1)+Math.abs(currentx-1-tox1)+1,Math.abs(currenty-toy2)+Math.abs(currentx-1-tox2)+g_score.get(current)));
					direction.put(left, direction.get(current));
				}
			}
			if (width - 1 != currentx) {
				int right = height*(currentx+1) + currenty;
				if (!g_score.containsKey(right) && map[currentx+1][currenty]) {
					g_score.put(right, g_score.get(current)+1);
					f_score.put(right, Math.min(Math.abs(currenty-toy1)+Math.abs(currentx+1-tox1)+1,Math.abs(currenty-toy2)+Math.abs(currentx+1-tox2)+g_score.get(current)));
					direction.put(right, direction.get(current));
				}
			}
			if (0 != currenty) {
				int up = height*currentx + currenty-1;
				if (!g_score.containsKey(up) && map[currentx][currenty-1]) {
					f_score.put(up, Math.min(Math.abs(currenty-1-toy1)+Math.abs(currentx-tox1)+1,Math.abs(currenty-1-toy2)+Math.abs(currentx-tox2)+g_score.get(current)));
					g_score.put(up, g_score.get(current)+1);
					direction.put(up, direction.get(current));
				}
			}
			if (height -1 != currenty) {
				int down = height*currentx + currenty+1;
				if (!g_score.containsKey(down) && map[currentx][currenty+1]) {
					f_score.put(down, Math.min(Math.abs(currenty+1-toy1)+Math.abs(currentx-tox1)+1,Math.abs(currenty+1-toy2)+Math.abs(currentx-tox2)+g_score.get(current)));
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
	
	/*
	private Entry<Integer,Integer> getMinEntry(HashMap<Integer,Integer> map) {
		Entry<Integer,Integer> ans = null;
		for (Entry<Integer,Integer> entry : map.entrySet()) {
			if (ans == null || entry.getValue() < ans.getValue()) {
				ans = entry;
			}
		}
		return ans;
	}*/
	/*
	public static void main(String[] args) {
		PathFinder pf = new PathFinder();
		int width = 8;
		int height = 16;
		pf.map = new boolean[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pf.map[i][j] = true;
			}
		}
		//pf.map[3][5] = false;
		//pf.map[4][5] = false;
		//pf.map[5][5] = false;
		//pf.map[6][5] = false;
		System.out.println(pf.getDirectionToShoot(1, 4, 4, 2, 20));
	}*/
}
