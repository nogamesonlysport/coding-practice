package generated;

import selfwritten.Utils;

public class UtilsTest {
    public void testGenerateArray() {
        int[] result = Utils.generateArray();
        assert result.length == 5;
    }

    public static void main(String[] args) {
        new UtilsTest().testGenerateArray();
        System.out.println("UtilsTest passed!");
    }
}
