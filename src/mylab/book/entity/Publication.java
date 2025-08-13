package mylab.book.entity;

public class Publication {
    private String title;
    private String publishDate; // yyyy-MM-dd
    private int page;
    private int price;

    // �⺻ ������
    public Publication() {}

    // ��� �ʵ� �ʱ�ȭ ������
    public Publication(String title, String publishDate, int page, int price) {
        this.title = title;
        this.publishDate = publishDate;
        this.page = page;
        this.price = price;
    }

    // getter / setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    // �䱸����: title�� ��ȯ
    @Override
    public String toString() {
        return title;
    }
}