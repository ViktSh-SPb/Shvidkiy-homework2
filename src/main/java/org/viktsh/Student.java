package org.viktsh;

import java.util.List;

/**
 * @author Viktor Shvidkiy
 */
public class Student {
    private String name;
    private List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student\n")
                .append("\tname: " + name + "\n")
                .append("\tbooks:");
        for(Book book:books){
            sb.append("\n\t\t").append(book);
        }
        return sb.toString();
    }
}
