import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private Map<String, String> book;// имя это ключ, ключ мапы не изменен - гарантия уникальности имени

    public PhoneBook() {
        this.book = new TreeMap<>();
    }

    public int add(String name, String tel) {//  добавил: значение тоже не может быть изменено
        if (!book.containsKey(name)) {
            book.put(name, tel);
        }
        return book.size();
    }

    public String findByNumber(String tel) {
        return book.entrySet().stream()
                .filter(entry -> tel.equals(entry.getValue()))
                .findFirst().map(Map.Entry::getKey)
                .orElse(null);
    }

    public String findByName(String name) {
        return book.get(name);
    }

    public Collection<String> printAllNames() {
        Collection<String> names = book.keySet();
        for (String name : names) { // по условиям задачи должен выводить имена
            System.out.println(name);
        }
        return book.keySet(); // для возможности теста должен отдавать значение
    }
}
