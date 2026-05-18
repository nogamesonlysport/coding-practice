package selfwritten.twopointerproblems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPalindromeTest {

    @Test
    public void testPalindrome(){
        var str1 = "abcdcba";
        var str2 = "abcdeba";
        var str3 = "abcddcba";
        var str4 = "abcdecba";
        ValidPalindrome checker = new ValidPalindrome();
        assertTrue(checker.isPalindrome(str1));
        assertFalse(checker.isPalindrome(str2));
        assertTrue(checker.isPalindrome(str3));
        assertFalse(checker.isPalindrome(str4));
    }
}
