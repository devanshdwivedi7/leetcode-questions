class Solution {
    public int removeDuplicates(int[] arr) {
        if(arr.length==0)
        return 0;
        int i=0;
        int count=1;
        int j=i+1;
        while(j<arr.length){
            if(arr[i]==arr[j]){
                j++;
            }
            else{
                arr[i+1]=arr[j];
                count++;
                i++;
                j++;
            }
       

        }
             return count;

    }
}