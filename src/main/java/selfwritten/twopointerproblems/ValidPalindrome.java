package selfwritten.twopointerproblems;

public class ValidPalindrome {
    public boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        var chars = str.toCharArray();
        while(i<j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
