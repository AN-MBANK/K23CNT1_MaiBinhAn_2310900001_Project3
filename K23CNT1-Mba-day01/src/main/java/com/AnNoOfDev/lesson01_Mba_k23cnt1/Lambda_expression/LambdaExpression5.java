package com.AnNoOfDev.lesson01_Mba_k23cnt1.Lambda_expression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Book {
    String id;
    String name;
    float price;

    public Book(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class LambdaExpression5 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("B01", "Java Spring", 40.5f));
        books.add(new Book("B02", "NodeJS", 39.5f));
        books.add(new Book("B03", "C#", 29.5f));
        books.add(new Book("B04", "JavaScript", 35.5f));

        // Lọc sách có giá lớn hơn 30.0
        Stream<Book> filter = books.stream().filter(b -> b.price > 30.0f);
        filter.forEach(System.out::println);
    }
}
