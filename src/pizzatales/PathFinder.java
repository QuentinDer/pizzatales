package pizzatales;

import java.util.HashMap;
import java.util.Map.Entry;

public class PathFinder {
	
	public boolean[][] map;

	public int getDirection(int fromx, int fromy, int tox, int toy, int maxmp, boolean canmoveleft, boolean canmoveup, boolean canmoveright, boolean canmovedown, boolean allowdiag) {
		if (Math.abs(fromx-tox)+Math.abs(fromy-toy)>maxmp)
			return 0;
		if (fromx == tox && fromy == toy)
			return 0;
		int width = map.length;
		int height = map[0].length;
		HashMap<Integer,Integer> g_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> direction = new HashMap<Integer,Integer>();
		int goal = tox*height + toy;
		if (0 != fromx && map[fromx-1][fromy] && canmoveleft) {
			int left = height*(fromx-1) + fromy;
			g_score.put(left, 1);
			f_score.put(left, Math.abs(fromy-toy)+Math.abs(fromx-1-tox)+1);
			direction.put(left, 1);
		}
		if (width - 1 != fromx && map[fromx+1][fromy] && canmoveright) {
			int right = height*(fromx+1) + fromy;
			g_score.put(right, 1);
			f_score.put(right, Math.abs(fromy-toy)+Math.abs(fromx+1-tox)+1);
			direction.put(right, 3);
		}
		if (0 != fromy && map[fromx][fromy-1] && canmoveup) {
			int up = height*fromx + fromy-1;
			f_score.put(up, Math.abs(fromy-1-toy)+Math.abs(fromx-tox)+1);
			g_score.put(up, 1);
			direction.put(up, 2);
		}
		if (height -1 != fromy && map[fromx][fromy+1] && canmovedown) {
			int down = height*fromx + fromy+1;
			f_score.put(down, Math.abs(fromy+1-toy)+Math.abs(fromx-tox)+1);
			g_score.put(down, 1);
			direction.put(down, 4);
		}
		if (allowdiag) {
			if (0 != fromy) {
				if (0 != fromx && map[fromx-1][fromy-1] && (canmoveup || canmoveleft)) {
					int upleft = height*(fromx-1) + fromy-1;
					f_score.put(upleft, Math.abs(fromy-1-toy)+Math.abs(fromx-1-tox));
					g_score.put(upleft, 1);
					direction.put(upleft, 5);
				}
				if (width -1 != fromx && map[fromx+1][fromy-1] && (canmoveup || canmoveright)) {
					int upright = height*(fromx+1) + fromy-1;
					f_score.put(upright, Math.abs(fromy-1-toy)+Math.abs(fromx+1-tox));
					g_score.put(upright, 1);
					direction.put(upright, 6);
				}
			}
			if (height -1 != fromy) {
				if (0 != fromx && map[fromx-1][fromy+1] && (canmovedown || canmoveleft)) {
					int downleft = height*(fromx-1) + fromy+1;
					f_score.put(downleft, Math.abs(fromy+1-toy)+Math.abs(fromx-1-tox));
					g_score.put(downleft, 1);
					direction.put(downleft, 8);
				}
				if (width -1 != fromx && map[fromx+1][fromy+1] && (canmovedown || canmoveright)) {
					int downright = height*(fromx+1)+fromy+1;
					f_score.put(downright, Math.abs(fromy+1-toy)+Math.abs(fromx+1-tox));
					g_score.put(downright, 1);
					direction.put(downright, 7);
				}
			}
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
			if (g_score.get(current) < maxmp) {
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
		}
		return 0;
	}
	
	public int getDirectionToShoot(int fromx, int fromy, int tox1, int toy1, int tox2, int toy2, int maxmp, boolean canmoveleft, boolean canmoveup, boolean canmoveright, boolean canmovedown, boolean allowdiag) {
		if (Math.abs(fromx-tox1)+Math.abs(fromy-toy1)>maxmp || Math.abs(fromx-tox2)+Math.abs(fromy-toy2)>maxmp)
			return 0;
		if ((fromx == tox1 && fromy == toy1) || (fromx == tox2 && fromy == toy2))
			return 0;
		int width = map.length;
		int height = map[0].length;
		HashMap<Integer,Integer> g_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> direction = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> f_score_final = new HashMap<Integer,Integer>();
		int goal1 = tox1*height + toy1;
		int goal2 = tox2*height + toy2;
		//System.out.println("Left:"+map[fromx-1][fromy])
		if (0 != fromx && map[fromx-1][fromy] && canmoveleft) {
			int left = height*(fromx-1) + fromy;
			g_score.put(left, 1);
			f_score.put(left, Math.min(Math.abs(fromy-toy1)+Math.abs(fromx-1-tox1)+1,Math.abs(fromy-toy2)+Math.abs(fromx-1-tox2)+1));
			direction.put(left, 1);
		}
		if ((fromx != width - 1) && map[fromx+1][fromy] && canmoveright) {
			int right = height*(fromx+1) + fromy;
			g_score.put(right, 1);
			f_score.put(right, Math.min(Math.abs(fromy-toy1)+Math.abs(fromx+1-tox1)+1,Math.abs(fromy-toy2)+Math.abs(fromx+1-tox2)+1));
			direction.put(right, 3);
		}
		if (0 != fromy && map[fromx][fromy-1] && canmoveup) {
			int up = height*fromx + fromy-1;
			f_score.put(up, Math.min(Math.abs(fromy-1-toy1)+Math.abs(fromx-tox1)+1,Math.abs(fromy-1-toy2)+Math.abs(fromx-tox2)+1));
			g_score.put(up, 1);
			direction.put(up, 2);
		}
		if ((fromy != height-1) && map[fromx][fromy+1] && canmovedown) {
			int down = height*fromx + fromy+1;
			f_score.put(down, Math.min(Math.abs(fromy+1-toy1)+Math.abs(fromx-tox1)+1,Math.abs(fromy+1-toy2)+Math.abs(fromx-tox2)+1));
			g_score.put(down, 1);
			direction.put(down, 4);
		}
		if (allowdiag) {
			if (0 != fromy) {
				if (0 != fromx && map[fromx-1][fromy-1] && (canmoveup || canmoveleft)) {
					int upleft = height*(fromx-1) + fromy-1;
					f_score.put(upleft, Math.min(Math.abs(fromy-1-toy1)+Math.abs(fromx-1-tox1)+1,Math.abs(fromy-1-toy2)+Math.abs(fromx-1-tox2)+1));
					g_score.put(upleft, 1);
					direction.put(upleft, 5);
				}
				if ((fromx != width - 1) && map[fromx+1][fromy-1] && (canmoveup || canmoveright)) {
					int upright = height*(fromx+1) + fromy-1;
					f_score.put(upright, Math.min(Math.abs(fromy-1-toy1)+Math.abs(fromx+1-tox1)+1,Math.abs(fromy-1-toy2)+Math.abs(fromx+1-tox2)+1));
					g_score.put(upright, 1);
					direction.put(upright, 6);
				}
			}
			if (fromy != height - 1) {
				if (0 != fromx && map[fromx-1][fromy+1] && (canmovedown || canmoveleft)) {
					int downleft = height*(fromx-1) + fromy+1;
					f_score.put(downleft, Math.min(Math.abs(fromy+1-toy1)+Math.abs(fromx-1-tox1)+1,Math.abs(fromy+1-toy2)+Math.abs(fromx-1-tox2)+1));
					g_score.put(downleft, 1);
					direction.put(downleft, 8);
				}
				if ((fromx != width-1) && map[fromx+1][fromy+1] && (canmovedown || canmoveright)) {
					int downright = height*(fromx+1)+fromy+1;
					f_score.put(downright, Math.min(Math.abs(fromy+1-toy1)+Math.abs(fromx+1-tox1)+1,Math.abs(fromy+1-toy2)+Math.abs(fromx+1-tox2)+1));
					g_score.put(downright, 1);
					direction.put(downright, 7);
				}
			}
		}
		while (!f_score.isEmpty()) {
			Entry<Integer, Integer> currententry = getMinEntry(f_score);
			int current = currententry.getKey();
			if (current == goal1 || current == goal2)
				return direction.get(current);
			//int fk = f_score.firstKey();
			f_score.remove(current);
			if (g_score.get(current) >= maxmp) {
				f_score_final.put(current,currententry.getValue());
			} else {
				int currentx = current/height;
				int currenty = current % height;
				/*String scorear = " {";
				for (Entry<Integer,Integer> e : f_score.entrySet())
					scorear += "[" + e.getValue()+ " g:" + g_score.get(e.getValue()) + " f:" + e.getKey() + " d:" + direction.get(e.getValue())+"]";
				System.out.println("Current: " + currentx+"-"+currenty+" g:"+g_score.get(current)+" f:"+fk+" d:"+direction.get(current)+scorear+"}");*/
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
		}
		if (!f_score_final.isEmpty())
			return direction.get(getMin(f_score_final));
		else
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
	
	
	private Entry<Integer,Integer> getMinEntry(HashMap<Integer,Integer> map) {
		Entry<Integer,Integer> ans = null;
		for (Entry<Integer,Integer> entry : map.entrySet()) {
			if (ans == null || entry.getValue() < ans.getValue()) {
				ans = entry;
			}
		}
		return ans;
	}
	
	
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
