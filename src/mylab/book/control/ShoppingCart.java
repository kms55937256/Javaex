package mylab.book.control;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class ShoppingCart {
    private final List<Publication> items = new ArrayList<>();

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "��(��) ��ٱ��Ͽ� �߰��Ǿ����ϴ�.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "��(��) ��ٱ��Ͽ��� ���ŵǾ����ϴ�.");
                return true;
            }
        }
        System.out.println("�ش� ������ ���ǹ��� ã�� �� �����ϴ�.");
        return false;
    }

    public void displayCart() {
        NumberFormat won = NumberFormat.getIntegerInstance(Locale.KOREA);
        System.out.println("====== ��ٱ��� ���� ======");
        int idx = 1;
        for (Publication p : items) {
            System.out.println(idx++ + ". " + p.getTitle() + " - " + won.format(p.getPrice()) + "��");
        }
        int total = calculateTotalPrice();
        int discounted = calculateDiscountedPrice();
        System.out.println("�� ����: " + won.format(total) + "��");
        System.out.println("���� ���� ����: " + won.format(discounted) + "��");
    }

    public int calculateTotalPrice() {
        int sum = 0;
        for (Publication p : items) sum += p.getPrice();
        return sum;
    }

    public int calculateDiscountedPrice() {
        double total = 0.0;
        for (Publication item : items) {
            if (item instanceof Magazine) {
                total += item.getPrice() * 0.90; // 10% ����
            } else if (item instanceof Novel) {
                total += item.getPrice() * 0.85; // 15% ����
            } else if (item instanceof ReferenceBook) {
                total += item.getPrice() * 0.80; // 20% ����
            } else {
                total += item.getPrice();
            }
        }
        return (int) Math.round(total);
    }

    public void printStatistics() {
        int magazine = 0, novel = 0, ref = 0;
        for (Publication p : items) {
            if (p instanceof Magazine) magazine++;
            else if (p instanceof Novel) novel++;
            else if (p instanceof ReferenceBook) ref++;
        }
        System.out.println("====== ��ٱ��� ��� ======");
        System.out.println("����: " + magazine + "��");
        System.out.println("�Ҽ�: " + novel + "��");
        System.out.println("����: " + ref + "��");
        System.out.println("�� ���ǹ�: " + items.size() + "��");
    }

    // ���� ����� main
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new mylab.book.entity.Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"));
        cart.addItem(new mylab.book.entity.Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"));
        cart.addItem(new mylab.book.entity.Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"));
        cart.addItem(new mylab.book.entity.Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"));
        cart.addItem(new mylab.book.entity.ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"));

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("���߿�");
        cart.displayCart();
    }
}