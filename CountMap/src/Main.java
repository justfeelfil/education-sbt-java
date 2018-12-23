import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        map.printMap();

        CountMap<Integer> map2 = new CountMapImpl<>();
        map2.add(1);

        map2.addAll(map);
        map2.printMap();
    }
}
