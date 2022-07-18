package pl.camp.it.book.store.database.memory;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDatabase implements IBookDAO {
    private List<Book> books = new ArrayList<>();

    public BookDatabase() {
        books.add(new Book(1, "Profesjonalne programowanie w Pythonie. Poznaj najlepsze praktyki kodowania i zaawansowane koncepcje programowania. Wydanie IV", "Michał Jaworski, Tarek Ziadé", "Python cechuje się dużą prostotą, a przy tym jest wszechstronny. Ma bardzo szeroki zakres zastosowania, przez co coraz więcej osób podejmuje naukę programowania w tym języku. Python należy do języków najczęściej używanych przez programistów, którzy tworzą w nim gry i aplikacje webowe. Świetnie sprawdza się ponadto w pracy z wykorzystaniem sztucznej inteligencji i uczenia maszynowego. Tym, co programiści doceniają w Pythonie, jest też obiektywność. Ucząc się, przyswajamy bowiem również zasady programowania obiektywnego, a więc koncepcji dla wielu innych języków.", 76.30, "978-83-283-8743-0", 5));
        books.add(new Book(2, "Algorytmy i struktury danych. Kurs video. Java, JavaScript, Python", "Artur Kulesza", "Algorytm jest pojęciem ściśle związanym z programowaniem. Jak podaje definicja, algorytm to skończony ciąg jasno zdefiniowanych czynności koniecznych do wykonania pewnego rodzaju zadań. W efekcie ich wykonania następuje rozwiązanie postawionego problemu. Brzmi informatycznie? Oczywiście - wszak algorytm może zostać zaimplementowany w postaci programu komputerowego. Może, ale nie musi. Myślenie algorytmiczne jest przede wszystkim myśleniem matematycznym, nastawionym na rozwiązanie zadania. Dopiero potem informatycznym.\n" +
                "\n" +
                "Czy wiesz, że jednym z pierwszych etapów rekrutacji do największych na świecie firm technologicznych, takich jak Google, Facebook, Microsoft czy Amazon, jest rozwiązywanie problemu algorytmicznego na tablicy? Bez użycia komputera... Nie dzieje się tak przypadkiem. Kto bowiem potrafi myśleć algorytmicznie i udowodnić to kredą czy mazakiem na tablicy, ten poradzi sobie z każdym zadaniem programistycznym. I ten może nazywać siebie programistą wszechstronnym. Programistą analitycznym. Bardzo pożądanym na rynku pracy.", 49.00, "978-83-283-8242-8", 7));
        books.add(new Book(3, "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", "Język Java jest konsekwentnie udoskonalany i unowocześniany dzięki zaangażowaniu wielu ludzi. Nowoczesny język Java staje się coraz bardziej wieloparadygmatowy, co oznacza, że stosowanie najlepszych praktyk w coraz większym stopniu determinuje jakość kodu. Obecnie napisanie kodu, który prawidłowo działa i może być łatwo zrozumiany przez innych programistów, nie wystarczy - należy zbudować program w taki sposób, aby można było go łatwo modyfikować. Jako że Java stała się obszerną i złożoną platformą, konieczne stało się uaktualnienie najlepszych praktyk.\n" +
                "\n" +
                "Ta książka jest kolejnym, trzecim wydaniem klasycznego podręcznika programowania w Javie. Poszczególne rozdziały zostały gruntownie przejrzane, zaktualizowane i wzbogacone o sporo ważnych treści. Znalazło się tu wiele wartościowych porad dotyczących organizowania kodu w taki sposób, aby stał się przejrzysty, co ułatwi przyszłe modyfikacje i usprawnienia. Poza takimi zagadnieniami, jak programowanie zorientowane obiektowo czy korzystanie z różnych typów, obszernie omówiono stosowanie lambd i strumieni, zasady obsługi wyjątków, korzystania ze współbieżności i serializacji. Książka składa się z dziewięćdziesięciu tematów pogrupowanych w dwanaście rozdziałów. Taki układ pozwala na szybkie odnalezienie potrzebnego rozwiązania.", 55.30, "978-83-283-4576-8", 5));
        books.add(new Book(4, "Java od zera. Kurs video. Programuj obiektowo!", "Piotr Chudzik", "Kto się decyduje na karierę programisty, musi wybrać swój „ulubiony” język. Ten, w którym będzie się specjalizować, doskonalić, pracować. Oczywiście nie musi to być jeden jedyny język. Chodzi po prostu o język pierwszego wyboru, w którego zakresie będzie się ekspertem. Wielu programistów wybiera do tej roli Javę, czyniąc ją tym samym jednym z najpopularniejszych języków programowania. W Javie realizuje się naprawdę wiele projektów. Nic w tym dziwnego - jej podstawy są stosunkowo proste do opanowania, a dzięki dużej i aktywnej społeczności online łatwo jest znaleźć odpowiedzi na najczęściej pojawiające się pytania. Ponadto - co nie jest bez znaczenia - programiści Javy naprawdę dobrze zarabiają.", 129.00, "978-83-283-9011-9", 5));
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        throw new NotImplementedException();
    }
}
