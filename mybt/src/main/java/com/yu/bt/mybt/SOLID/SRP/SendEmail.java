package com.yu.bt.mybt.SOLID.SRP;

public class SendEmail {

    private Book book;

    public SendEmail(Book book){
        this.book = book;
    }

    public void sendAsEmailTo(String address) {
        // Convert to email body
        // Prepare email details
        // Send
    }
}
