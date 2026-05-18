package selfwritten.triald;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSorderLists {

    public List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        var mergedList = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while(i < list1.size() && j < list2.size()) {
            var element1 = list1.get(i);
            var element2 = list2.get(j);
            if(element1 <= element2){
                mergedList.add(element1);
                i++;
            } else {
                mergedList.add(element2);
                j++;
            }
        }
        while(i < list1.size()){
            mergedList.add(list1.get(i));
            i++;
        }
        while(j < list2.size()){
            mergedList.add(list2.get(j));
            j++;
        }
        return mergedList;
    }
}
