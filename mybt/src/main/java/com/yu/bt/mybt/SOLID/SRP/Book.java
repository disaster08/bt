package com.yu.bt.mybt.SOLID.SRP;

import java.nio.file.Path;
import java.util.List;

public class Book {

    private final List<String> pages;

    public Book(List<String> pages) {
        this.pages = pages;
    }

    public void printPage(int page) {
        // Find page
        // Send to printer
    }



    public static void main(String[] args) {
        new Book(List.of(
           "Page1",
           "Page2",
           "Page3"
        ));
    }

}
