import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1, list2, list3;

        list1 = CollectionUtils.newArrayList();
        CollectionUtils.add(list1,1);
        CollectionUtils.add(list1,2);
        CollectionUtils.add(list1,3);
        CollectionUtils.add(list1,4);
        CollectionUtils.add(list1,4);
        System.out.println(list1);

        list2 = CollectionUtils.newArrayList();
        CollectionUtils.add(list2,4);
        CollectionUtils.add(list2,5);
        CollectionUtils.add(list2,6);
        CollectionUtils.add(list2,6);
        CollectionUtils.add(list2,3);
        System.out.println(list2);

        list3 = CollectionUtils.newArrayList();

        CollectionUtils.addAll(list2, list3);
        System.out.println(list3);

        System.out.println(CollectionUtils.indexOf(list1, 4));

        System.out.println(CollectionUtils.limit(list2,4));
        CollectionUtils.removeAll(list3,list1);
        System.out.println(list3);
        System.out.println(CollectionUtils.containsAny(list1,list2));
        System.out.println(CollectionUtils.containsAll(list3,list2));

        System.out.println(list1);
        System.out.println(CollectionUtils.range(list1, 0,6));

        Comparator<Integer> comporator = (o1, o2) -> {return o1 - o2;};
        System.out.println(CollectionUtils.range(list1,2,4,comporator));
    }
}
