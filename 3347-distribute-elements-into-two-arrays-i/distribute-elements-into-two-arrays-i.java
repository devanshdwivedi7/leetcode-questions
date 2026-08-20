class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int count1 = 1;
        int count2 = 1;
        for (int i = 2; i < n; i++) {
            if (arr1[count1 - 1] > arr2[count2 - 1]) {
                arr1[count1++] = nums[i];
            } else {
                arr2[count2++] = nums[i];
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < count1; i++) {
            result[i] = arr1[i];
        }
        for (int i = 0; i < count2; i++) {
            result[count1 + i] = arr2[i];
        }
        
        return result;
    }
}