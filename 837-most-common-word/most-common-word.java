import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> ban = new HashSet<>();

        for (String word : banned) {
            ban.add(word.toLowerCase());
        }

        paragraph = paragraph.toLowerCase();

        String[] words = paragraph.split("[^a-z]+");

        HashMap<String, Integer> map = new HashMap<>();

        String answer = "";
        int max = 0;

        for (String word : words) {

            if (word.length() == 0 || ban.contains(word)) {
                continue;
            }

            int count = map.getOrDefault(word, 0) + 1;
            map.put(word, count);

            if (count > max) {
                max = count;
                answer = word;
            }
        }

        return answer;
    }
}