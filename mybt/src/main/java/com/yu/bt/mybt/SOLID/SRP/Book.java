package com.yu.bt.mybt.SOLID.SRP;

import java.nio.file.Path;
import java.util.List;

public class Book {

    private final List<String> pages;

    public Book(List<String> pages) {
        this.pages = pages;
    }


    public static void main(String[] args) {
        Book pages = new Book(List.of(
                "Page1",
                "Page2",
                "Page3"
        ));

        ExportFiles exportFiles = new SaveAsPDF(pages);
        exportFiles.export(Path.of("C:/pages"));


    }
}
