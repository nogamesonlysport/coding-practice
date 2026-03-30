package generated;

import java.util.Arrays;
import java.util.stream.Collectors;

//https://www.hellointerview.com/learn/code/two-pointers/sort-colors
public class SortColours {
    public static void main(String[] args) {
        var nums = new int[] {2,1,2,0,1,0,1,0,1};
        sortColours(nums);
    }

    private static void sortColours(int[] nums) {
        int i = 0;
        while(i<nums.length - 1) {
            int j = nums.length - 1;
            while(i<j) {
                if(nums[i] > nums[j]){
                    swap(nums, i, j);
                } else {
                    j--;
                }
            }
            i++;
        }
        System.out.println(Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }

    private static void swap(int[] nums, int i, int j) {
        var tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
