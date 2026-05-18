package selfwritten.triald;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqueEmailAddressesTest {


    @Test
    public void testUniqueEmails(){
        assertEquals(2, UniqueEmailAddresses.identifyUniqueStrings(
                new String[] {
                        "test.email+alex@leetcode.com",
                        "test.e.mail+bob.cathy@leetcode.com",
                        "testemail+david@lee.tcode.com"
                }));

        assertEquals(3, UniqueEmailAddresses.identifyUniqueStrings(
                new String[] {
                        "a@leetcode.com", "b@leetcode.com", "c@leetcode.com"
                }));

        assertEquals(3, UniqueEmailAddresses.identifyUniqueStrings(
                new String[] {
                        "a@leetcode.com", "b@leetcode.com", "c@leetcode.com",
                        "@leetcode.com"
                }));
    }
}
