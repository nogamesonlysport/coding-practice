package selfwritten.twopointerproblems;

import java.util.Arrays;
import java.util.stream.Collectors;

//https://www.hellointerview.com/learn/code/two-pointers/move-zeroes
public class MoveZeroes {
    public static void main(String[] args) {
        var nums = new int[] {2,0,4,0,9};
        moveZeros(nums);
        System.out.println(String.join(",", Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.toList())));
    }

    private static void moveZeros(int[] nums) {
        var zero = 0;
        for (int i = 0; i<(nums.length); i++) {
            if (nums[i] != 0) {
                swap(nums, i, zero);
                zero++;
            }
        }
    }

    private static void swap(int[] nums, int i, int zero) {
        var tmp = nums[i];
        nums[i] = nums[zero];
        nums[zero] = tmp;
    }
}
