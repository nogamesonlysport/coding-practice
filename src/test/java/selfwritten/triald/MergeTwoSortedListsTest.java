package selfwritten.triald;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTwoSortedListsTest {

    @Test
    public void testMergedLists(){
        var list1 = List.of(1, 3, 5, 7, 9);
        var list2 = List.of(2, 4, 6, 8, 10);
        MergeTwoSorderLists mergeTwoSorderLists = new MergeTwoSorderLists();
        var mergedList = mergeTwoSorderLists.mergeLists(list1, list2);
        assertEquals(mergedList.size(), (list1.size() + list2.size()));
    }
}
