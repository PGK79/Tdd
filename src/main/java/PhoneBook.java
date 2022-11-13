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
}
