package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // ��ü ��� ��ȸ
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // ���� �� ���
    public int getTotalBooks() { return books.size(); }

    public int getAvailableBooksCount() {
        int cnt = 0;
        for (Book b : books) if (b.isAvailable()) cnt++;
        return cnt;
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }

    // �߰�
    public void addBook(Book book) {
        books.add(book);
        System.out.println("������ �߰��Ǿ����ϴ�: " + book.getTitle());
    }

    // �˻�
    public Book findByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) return b;
        }
        return null;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equals(author)) result.add(b);
        }
        return result;
    }

    public Book findByISBN(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    // ����/�ݳ�
    public boolean checkOutBook(String isbn) {
        Book b = findByISBN(isbn);
        if (b != null) return b.checkOut();
        return false;
    }

    public boolean returnBook(String isbn) {
        Book b = findByISBN(isbn);
        if (b != null) { b.returnBook(); return true; }
        return false;
    }

    // ���� ���� ���
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }
}