import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBookTest {
    static PhoneBook sut;

    @BeforeAll
    public static void startedAll() {
        System.out.println("Начало тестов");
    }

    @BeforeEach
    public void InitAndStart() {
        System.out.println("Старт теста");
        sut = new PhoneBook();
    }

    @AfterAll
    public static void finishAll() {
        System.out.println("Все тесты завершены");
    }

    @AfterEach
    public void finished() {
        System.out.println("Тест завершен");
        sut = null;
    }

    @Test
    public void testAddContact() {
        // given:
        Map<String, String> book = new TreeMap<>();//ради готовой сортировки на будущее
        final String NAME = "Константин";
        final String TEL = "12345";
        // можно было просто ввести int expected = 1, но решил пойти таким путем.
        book.put(NAME, TEL);
        int expected = book.size();

        // when:
        int actual = sut.add(NAME, TEL);

        // then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddUniqueContact() {
        // given:
        Map<String, String> book = new TreeMap<>(); //ключ это имя, ключ не меняется. Это гарантия
        final String NAME = "Константин";           //что не будут добавляться повторяющиеся имена
        final String TEL = "12345";

        final String NAME_REPETITION = "Константин";
        final String NEW_TEL = "54321";

        book.put(NAME, TEL);

        if (!book.containsKey(NAME_REPETITION)) {//значение тоже не может быть изменено
            book.put(NAME_REPETITION, NEW_TEL);
        }

        int expected = book.size();

        // when:
        int actual = sut.add(NAME, TEL);

        // then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TestFindByNumber() {
        // given:
        Map<String, String> book = new TreeMap<>();
        final String NAME = "Константин";
        final String TEL = "12345";
        sut.add(NAME, TEL);

        //Первый способ
        // String expected = NAME;

        //Второй позволил понять каким будет метод
        book.put(NAME, TEL);
        String expected = book.entrySet().stream()
                .filter(entry -> TEL.equals(entry.getValue()))
                .findFirst().map(Map.Entry::getKey)
                .orElse(null);

        // when:
        String actual = sut.findByNumber(TEL);

        // then:
        Assertions.assertEquals(expected, actual);
    }
}
