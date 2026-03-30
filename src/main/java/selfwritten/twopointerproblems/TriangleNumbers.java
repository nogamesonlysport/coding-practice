package selfwritten.twopointerproblems;

import java.util.Arrays;

public class TriangleNumbers {
    public static void main(String[] args) {
        var nums = new int[] {11,4,9,6,15,18};
        var count = countTriangleTriplets(nums);
        System.out.println(count);
    }

    private static Integer countTriangleTriplets(int[] nums) {
        var length = nums.length;
        var result = 0;
        if (length < 3) {
            return result;
        }
        Arrays.sort(nums);

        for (var i=length-1; i > 1; i--) {
            var left = 0;
            var right = i - 1;
            while(left < right) {
                var vI = nums[i];
                var vLeft = nums[left];
                var vRight = nums[right];

                if(vI < (vLeft + vRight)) {
                    System.out.println(vI + ", " + vLeft + ", " + vRight);
                    result+= right -left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    private static boolean isConditionTrue(int vI, int vLeft, int vRight) {
//        return vI + vLeft > vRight && vLeft + vRight > vI && vI + vRight > vLeft;
        return vI < (vLeft + vRight);
    }
}
