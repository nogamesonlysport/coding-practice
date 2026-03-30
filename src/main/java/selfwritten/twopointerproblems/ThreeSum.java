package selfwritten.twopointerproblems;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        var nums = new int[] {-1,0,1,2,-1,-1};
//        var nums = new int[] {1,1,-2};
        var target = 0;
        computeTriplets(nums, target);
    }

    private static void computeTriplets(int[] nums, int target) {
        //compute de-duplicated list of triplets from nums whose sum adds to target
        Arrays.sort(nums);
        var i = 0;
        List<List<Integer>> result = new ArrayList<>();
        while(i <= (nums.length - 3)) {
            if (i > 0 && nums[i] == nums[i-1]) {
                i++;
                continue;
            }
            var left = i+1;
            var right = nums.length - 1;
            while (left < right) {
                var vleft = nums[left];
                var vright = nums[right];
                var vI = nums[i];
                var sum = vI + vleft + vright;
                if (sum == target) {
                    var qualifyingTriplet = new ArrayList<>(Arrays.asList(vI, vleft, vright));
                    result.add(qualifyingTriplet);
                    System.out.println(qualifyingTriplet);
                    while(left<right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while(left<right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            i++;
        }
        System.out.println("Result: " + result.size());
    }
}
