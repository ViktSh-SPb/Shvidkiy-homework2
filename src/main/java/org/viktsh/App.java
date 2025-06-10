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

        List<Student> students = new ArrayList<>();
        students.add(new Student("Архипова Алиса", List.of(books[1],books[3],books[5],books[7],books[9])));
        students.add(new Student("Лебедев Иван", List.of(books[2],books[4],books[6],books[8],books[0])));
        students.add(new Student("Григорьев Святослав", List.of(books[1],books[4],books[7],books[0],books[3])));
        students.add(new Student("Зайцева Татьяна", List.of(books[2],books[5],books[8],books[1],books[4])));
        students.add(new Student("Крылов Максим", List.of(books[0],books[3],books[6],books[9],books[2])));

        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear()>2020)
                .limit(3)
                .peek(book -> System.out.println(book.getYear()))
                .findFirst()
                .ifPresentOrElse(book -> System.out.println(book.getYear()), ()-> System.out.println("Книга отсутствует"));

    }
}

