package selfwritten.triald;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
    public static int identifyUniqueStrings(String [] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> uniqueEmails = new HashSet<>();
        for (String email: emails) {
            var normalizedEmail = getNormalizedEmail(email);
            if (normalizedEmail == null) continue;
            uniqueEmails.add(normalizedEmail);
        }
        return uniqueEmails.size();
    }

    private static String getNormalizedEmail(String email) {
        var emailSplit = email.split("@");
        var localName = emailSplit[0];
        var domain = emailSplit[emailSplit.length - 1];

        if (localName.isEmpty()){
            return null;
        }

        int plusIndex = localName.indexOf('+');
        if (plusIndex!=-1){
            localName = localName.substring(0, plusIndex);
        }
        var cleanedLocalName = localName.replace(".", "");
        return cleanedLocalName + "@" + domain;
    }

    public static boolean allowRequest(String clientId, long timestamp){
        return false;
    }
}
