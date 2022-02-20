package com.yu.bt.mybt.SOLID.SRP;

import java.nio.file.Path;

public class SaveAsPDF implements ExportFiles{

    private Book book;

    public SaveAsPDF(Book book){
        this.book = book;
    }

    @Override
    public void export(Path path) {
        // Convert to pdf
        // Save to file
    }
}
