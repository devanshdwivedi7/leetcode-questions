import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << col));
        }
        int maxFamilies = (n - rowToSeats.size()) * 2;
        for (int seats : rowToSeats.values()) {
            boolean leftFree = (seats & 60) == 0;      
            boolean rightFree = (seats & 960) == 0;   
            boolean middleFree = (seats & 240) == 0;  
            
            if (leftFree && rightFree) {
                maxFamilies += 2;
            } else if (leftFree || rightFree || middleFree) {
                maxFamilies += 1;
            }
        }
        
        return maxFamilies;
    }
}