package org.viktsh;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Viktor Shvidkiy
 */
public class App 
{
    public static void main( String[] args )
    {
        Book[] books = {
                new Book("Философия Java", "Брюс Эккель", 1168, 2015),
                new Book("Head First. Изучаемм Java", "Кэти Сьера, Берт Бейтс", 716, 2022),
                new Book("Java 8, Полное руководство", "Герберт Шилдт", 1376, 2017),
                new Book("Head First. Паттерны проектирования", "Эрик фримен, Элизабет Робсон", 640, 2023),
                new Book("Postgresql. Основы языка SQL", "Е. П. Моргунов", 336, 2019),
                new Book("Структуры данных и алгоритмы Java", "Роберт Лафоре", 704, 2018),
                new Book("Spring Boot по-быстрому", "Марк Хеклер", 352, 2022),
                new Book("Чистый код: создание, анализ и рефакторинг", "Роберт Мартин", 464, 2024),
                new Book("Java Concurrency на практике", "Брайан Гетц", 464, 2024),
                new Book("Экстремальное программирование: разработка через тестирование", "Кент Бек", 224, 2017)
        };

        //Реализовать собственный аналог HashSet с двумя методами (вставить и удалить)
        System.out.println("-----Создаем пустой HashSet-----");
        CustomHashSet<Book> bookSet = new CustomHashSet<>();
        System.out.println(bookSet);
        System.out.println("-----Добавляем два элемента-----");
        bookSet.add(books[0]);
        bookSet.add(books[1]);
        System.out.println(bookSet);
        System.out.println("-----Удаляем второй элемент-----");
        bookSet.remove(books[1]);
        System.out.println(bookSet);

        //Реализовать собственный аналог ArrayList/LinkedList (листы на выбор, но должны быть основные методы add, get, remove, addAll)
        System.out.println("-----Создаем пустой LinkedList-----");
        CustomLinkedList<Book> bookList = new CustomLinkedList<>();
        System.out.println(bookList);
        System.out.println("-----Добавляем 5 элементов-----");
        for(int i=0;i<5;i++){
            bookList.add(books[i]);
        }
        System.out.println(bookList);
        System.out.println("-----Выводим второй элемент-----");
        System.out.println(bookList.get(1));
        System.out.println("-----Удаляем третий элемент-----");
        bookList.remove(2);
        System.out.println(bookList);
        System.out.println("-----Удаляем первый и последний элементы-----");
        bookList.removeFirst();
        bookList.removeLast();
        System.out.println(bookList);
        System.out.println("-----Добавляем список в начало-----");
        bookList.addAll(0, List.of(books));
        System.out.println(bookList);


        // Создать класс Student, обязательное поле класса Student - List<Book> (минимум по 5 книг у каждого)
        // Заполнить коллекцию студентами
        List<Student> students = new ArrayList<>();
        students.add(new Student("Архипова Алиса", List.of(books[1],books[3],books[5],books[7],books[9])));
        students.add(new Student("Лебедев Иван", List.of(books[2],books[4],books[6],books[8],books[0])));
        students.add(new Student("Григорьев Святослав", List.of(books[1],books[4],books[7],books[0],books[3])));
        students.add(new Student("Зайцева Татьяна", List.of(books[2],books[5],books[8],books[1],books[4])));
        students.add(new Student("Крылов Максим", List.of(books[0],books[3],books[6],books[9],books[2])));

        /*
        * ● Вывести в консоль каждого студента (переопределите toString)
        * ● Получить для каждого студента список книг
        * ● Получить книги
        * ● Отсортировать книги по количеству страниц (Не забывайте про условия для сравнения объектов)
        * ● Оставить только уникальные книги
        * ● Отфильтровать книги, оставив только те, которые были выпущены после 2000 года
        * ● Ограничить стрим на 3 элементах
        * ● Получить из книг годы выпуска
        * ● При помощи методов короткого замыкания (почитайте самостоятельно что это такое) вернуть Optional от книги
        * ● При помощи методов получения значения из Optional вывести в консоль год выпуска найденной книги, либо запись о том, что такая книга отсутствует
         */
        System.out.println("\n-----Демонстрация StreamAPI-----");
        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear()>2020)
                .limit(3)
                .peek(book -> System.out.println(book.getYear()))
                .distinct()
                .findFirst()
                .ifPresentOrElse(
                        book -> System.out.println(book.getYear()),
                        ()-> System.out.println("Книга отсутствует"));

    }
}

