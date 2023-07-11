package org.example.book;

public class Book {
    private int number;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;
    private boolean isAvailable;
    private int loanCount;

    public Book(int number, String title, String author, String genre, String subGenre, String publisher) {
        this.number = number;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.isAvailable = true;
        this.loanCount = 0;
    }

    public Book() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int i) {
        this.loanCount = loanCount;
    }

    public void incrementLoanCount() {
        loanCount++;
    }

    @Override
    public String toString() {
        return number + ". " + "\'" +
                title + '\'' + " by " +
                author + "." +
                " Category: " + genre + "/" + subGenre + ". " +
                "Published by " +
                publisher + ".";
    }

}
