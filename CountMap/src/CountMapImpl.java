import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    private Map<E, Integer> container = new HashMap<>();

    // добавляет элемент в этот контейнер.
    @Override
    public void add(E object){
        if (container.containsKey(object)) {
            int numberOfElements = container.get(object);
            container.put(object, numberOfElements + 1);
        } else {
            container.put(object, 1);
        }
    }

    //Возвращает количество добавлений данного элемента
    @Override
    public int getCount(E object) {
        return container.getOrDefault(object, 0);
    }

    //Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(E object) {
        int count = getCount(object);
        container.remove(object);
        return count;
    }

    //количество разных элементов
    @Override
    public int size() {
        return container.size();
    }

    //Добавить все элементы из source в текущий контейнер, при совпадении ключей суммировать значения
    @Override
    public void addAll(CountMap<? extends E> source) {
        Map<? extends E, Integer> sourceMap = source.toMap();
        for (Map.Entry<? extends E, Integer> entry : sourceMap.entrySet()) {
            E key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                this.add(key);
            }
        }
    }

    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    @Override
    public Map<E, Integer> toMap() {
        return container;
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination = container;
    }

    public void printMap(){
        System.out.println(container); }
}
