import java.util.LinkedList;
import java.util.Queue;

// Time Complexity : O(hw * hwCn) where h = height of grid, w = width of grid, n = number of buildings
// Space Complexity : O(hw) where h = height of grid, w = width of grid
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//Optimal PLacement of Building in a Grid - https://leetcode.com/discuss/interview-question/221639/, https://leetcode.com/discuss/interview-question/933012/oa-sap-labs-on-campus
// Time Complexity : O(hw * hwCn) where h = height of grid, w = width of grid, n = number of buildings
// Space Complexity : O(hw) where h = height of grid, w = width of grid
public class Main {
	public static void main(String[] args) {
		BuildingPlacement bp = new BuildingPlacement();
		System.out.println(bp.placeBuildings(3, 2, 1));
		System.out.println(bp.placeBuildings(5, 4, 3));
	}

	public static class BuildingPlacement {
		int minDistance = Integer.MAX_VALUE;

		public int placeBuildings(int h, int w, int n) {
			int[][] grid = new int[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					grid[i][j] = -1;
				}
			}

			backtrack(grid, 0, 0, h, w, n);

			return minDistance;
		}

		private void backtrack(int[][] grid, int r, int c, int h, int w, int n) {
			// base
			if (n == 0) {
				bfs(grid, h, w);
				return;
			}

			// logic
			if (c == w) {
				r++;
				c = 0;
			}

			for (int i = r; i < h; i++) {
				for (int j = c; j < w; j++) {
					// action
					grid[i][j] = 0;

					// recurse
					backtrack(grid, i, j + 1, h, w, n - 1);

					// backtrack
					grid[i][j] = -1;
				}
				c = 0;
			}
		}

		private void bfs(int[][] grid, int h, int w) {
			boolean[][] visited = new boolean[h][w];
			Queue<int[]> queue = new LinkedList<>();

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (grid[i][j] == 0) {
						queue.add(new int[] { i, j });
						visited[i][j] = true;
					}
				}
			}

			int dist = 0;
			int[][] dirs = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

			while (!queue.isEmpty()) {
				int size = queue.size();

				for (int i = 0; i < size; i++) {
					int[] curr = queue.poll();

					for (int[] dir : dirs) {
						int nr = curr[0] + dir[0];
						int nc = curr[1] + dir[1];

						if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
							queue.add(new int[] { nr, nc });
							visited[nr][nc] = true;
						}
					}
				}
				dist++;
			}

			minDistance = Math.min(minDistance, dist - 1);
		}
	}
}