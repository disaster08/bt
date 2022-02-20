package com.yu.bt.mybt.SOLID.SRP;

import java.nio.file.Path;

public class SaveAsTXT implements ExportFiles{

    private Book book;

    public SaveAsTXT(Book book){
        this.book = book;
    }

    @Override
    public void export(Path path) {
        // Prepare content
        // Save to file
    }
}
