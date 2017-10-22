/* given a m * n 2D grid initialized with three possible value.
-1 a wall or an obstacle.
0 a gate;
Inf infinity means an empty room. 
File each empty room with the distance to its nearest gate. if it is impossible to reach a gate it should be filled with Inf.
given 2D grid

Inf -1 0 Inf
Inf Inf Inf -1
Inf -1 Inf -1
0 -1 Inf Inf
*/
//since we need to find the shortest distance from multi rooms to a gate. 
//then that can be changed to find the shorest path from door to room. 
//because we have lots of door, then we assume we have a super dummy node that connect to all doors. 
//then we can treat all those doors as the neighors of the super dummy node and add them in to the queue, and do bfs.
public class Solution {
	static final int INF = 2147483647;
	public void wallsAndGates(int[][] rooms) {
		if (rooms == null) {
			return;
		}
		int xLength = rooms.length;
		int yLength = rooms[0].length;
		if (xLength == 0) {
			return;
		}
		if (yLength == 0) {
			return;
		}
		Queue<Integer> queueX = new LinkedList<>();
		Queue<Integer> queueY = new LinkedList<>();

		for (int x = 0; x < xLength; x++) {
			for (int y = 0; y < yLength; y++) {
				if (rooms[x][y] == 0) {
					queueX.add(x);
					queueY.add(y);
				}
			}
		}

		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		int dis = 0;
		while (!queueX.isEmpty()) {
			int xInd = queueX.poll();
			int yInd = queueY.poll();
			for (int i = 0; i < 4; i++) {
				int newX = xInd + dx[i];
				int newY = yInd + dy[i];
				if (newX >= 0 && newX < xLength && newY >= 0 && newY< yLength && rooms[newX][newY] == INF) {
					queueX.add(newX);
					queueY.add(newY);
					room[newX][newY] = room[xInd][yInd] + 1;
				}
			}
		}	
	}
}