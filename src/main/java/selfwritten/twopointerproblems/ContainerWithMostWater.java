package selfwritten.twopointerproblems;

//https://www.hellointerview.com/learn/code/two-pointers/container-with-most-water
public class ContainerWithMostWater {
    public static void main(String[] args) {
        var heights = new int[]{3, 4, 1, 2, 2, 4, 1, 3, 2};
        containerWithMostWater(heights);
    }
    public static void containerWithMostWater(int[] heights) {
        var left = 0;
        var right = heights.length - 1;
        var area = 0;
        var max = 0;
        while (left < right) {
            var width = right - left;
            var height = Math.min(heights[left], heights[right]);
            area = width * height;
            max = Math.max(area, max);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("max: " + max);
    }

    private static int calculateMaxArea(int[] heights, int max, int left, int right) {
        if (left >= right) {
            return max;
        }
        var area = calculateArea(heights, left, right);
        if (area > max) {
            max = area;
        }
        if (calculateArea(heights, left+1, right) > max) {
            max = calculateMaxArea(heights, max, left+1, right);
        } else if (calculateArea(heights, left, right-1) > max) {
            max = calculateMaxArea(heights, max, left, right-1);
        } else {
            max = calculateMaxArea(heights, max, left+1, right-1);
        }
        return max;
    }

    private static int calculateArea(int[] heights, int left, int right) {
        var width = right - left;
        System.out.println("width: " + width);
        var height = Math.min(heights[left], heights[right]);
        var area = width * height;
        System.out.println("area: " + area);
        return area;
    }
}
