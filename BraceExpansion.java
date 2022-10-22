import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Time Complexity : O(l power k) where l = number of groups, k = average number of elements in the group (child nodes)
// Space Complexity : O(n) where n = length of string
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//1087. Brace Expansion (Medium) - https://leetcode.com/problems/brace-expansion/
// Time Complexity : O(l power k) where l = number of groups, k = average number of elements in the group (child nodes)
// Space Complexity : O(n) where n = length of string
class Solution {

	List<String> result;

	public String[] expand(String s) {
		result = new ArrayList<>();
		List<List<Character>> blocks = new ArrayList<>();
		int i = 0;

		while (i < s.length()) {
			char ch = s.charAt(i);
			List<Character> block = new ArrayList<>();

			if (ch == '{') {
				i++;
				while (s.charAt(i) != '}') {
					if (s.charAt(i) != ',') {
						block.add(s.charAt(i));
					}
					i++;
				}
			} else {
				block.add(s.charAt(i));
			}

			Collections.sort(block);
			blocks.add(block);
			i++;
		}

		backtrack(blocks, 0, new StringBuilder());

		return result.stream().toArray(String[]::new);
	}

	private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
		// base
		if (index >= blocks.size()) {
			result.add(sb.toString());
			return;
		}

		// logic
		List<Character> block = blocks.get(index);

		for (int i = 0; i < block.size(); i++) {
			char ch = block.get(i);

			// action
			sb.append(ch);

			// recurse
			backtrack(blocks, index + 1, sb);

			// backtrack
			sb.setLength(sb.length() - 1);
		}
	}
}