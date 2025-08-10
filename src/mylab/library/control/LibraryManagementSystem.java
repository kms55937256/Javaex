package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library lib = new Library("�߾� ������");
        addSampleBooks(lib);

        // ������ ���
        printSummary(lib);

        // �˻� �׽�Ʈ
        testFindBook(lib);

        // ���� �׽�Ʈ
        testCheckOut(lib);

        // �ݳ� �׽�Ʈ
        testReturn(lib);

        // ���� ���� ���
        displayAvailableBooks(lib);
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("�ڹ� ���α׷���", "���ڹ�", "978-89-01-12345-6", 2022));
        library.addBook(new Book("��ü������ ��ǰ� ����", "����ȣ", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("�ڹ��� ����", "���ü�", "978-89-01-14077-4", 2019));
    }

    private static void printSummary(Library lib) {
        System.out.println("===== " + lib.getName() + " =====");
        System.out.println("��ü ���� ��: " + lib.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + lib.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + lib.getBorrowedBooksCount());
        System.out.println();
    }

    private static void testFindBook(Library lib) {
        System.out.println("===== ���� �˻� �׽�Ʈ =====");
        System.out.println("�������� �˻� ���:");
        Book byTitle = lib.findByTitle("�ڹ��� ����");
        if (byTitle != null) System.out.println(byTitle.toString());
        System.out.println();

        System.out.println("���ڷ� �˻� ���:");
        List<Book> byAuthor = lib.findByAuthor("Robert C. Martin");
        if (!byAuthor.isEmpty()) System.out.println(byAuthor.get(0).toString());
        System.out.println();
    }

    private static void testCheckOut(Library lib) {
        System.out.println("===== ���� ���� �׽�Ʈ =====");
        boolean ok = lib.checkOutBook("978-89-01-14077-4");
        System.out.println(ok ? "���� ���� ����!" : "���� ���� ����!");
        if (ok) {
            Book b = lib.findByISBN("978-89-01-14077-4");
            System.out.println("����� ���� ����:");
            System.out.println(b.toString());
        }
        System.out.println();
        System.out.println("������ ���� ����:");
        System.out.println("��ü ���� ��: " + lib.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + lib.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + lib.getBorrowedBooksCount());
        System.out.println();
    }

    private static void testReturn(Library lib) {
        System.out.println("===== ���� �ݳ� �׽�Ʈ =====");
        boolean ok = lib.returnBook("978-89-01-14077-4");
        System.out.println(ok ? "���� �ݳ� ����!" : "���� �ݳ� ����!");
        if (ok) {
            Book b = lib.findByISBN("978-89-01-14077-4");
            System.out.println("�ݳ��� ���� ����:");
            System.out.println(b.toString());
        }
        System.out.println();
        System.out.println("������ ���� ����:");
        System.out.println("��ü ���� ��: " + lib.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + lib.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + lib.getBorrowedBooksCount());
        System.out.println();
    }

    private static void displayAvailableBooks(Library lib) {
        System.out.println("===== ���� ������ ���� ��� =====");
        for (Book b : lib.getAvailableBooks()) {
            System.out.println(b.toString());
            System.out.println("------------------------");
        }
    }
}