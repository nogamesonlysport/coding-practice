package selfwritten.twopointerproblems;

public class TwoPointerImplementations {
    public static void main(String[] args) {
        var nums = new int[] {1,3,4,6,8,10,13};
        var target = 13;
        twoSumBruteForce(nums, target);
        twoSumTwoPointers(nums, target);

        target = 6;
        twoSumBruteForce(nums, target);
        twoSumTwoPointers(nums, target);
    }

    private static void twoSumBruteForce(int[] nums, int target) {
        //determine if there exists a pair of numbers that sum to a given target
        //complexity is O(n2)
        var outcome = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    outcome = true;
                    break;
                }
            }
        }
        System.out.println("Output via brute force: " + outcome);
    }

    private static void twoSumTwoPointers(int[] nums, int target) {
        //determine if there exists a pair of numbers that sum to a given target
        //more efficient O(n)
        var outcome = false;
        var left = 0;
        var right = nums.length - 1;

        while (left < right) {
            var sum = nums[left] + nums[right];
            if (sum == target) {
                outcome = true;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("Output via two pointers method: " + outcome);
    }
}
