package mylab.book.control;

import java.text.NumberFormat;
import java.util.Locale;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class ManageBook {

    public static void main(String[] args) {
        Publication[] publications = {
            new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"),
            new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"),
            new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"),
            new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"),
            new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"),
            new Novel("�ҳ��̿´�", "2014-05-01", 216, 15000, "�Ѱ�", "����Ҽ�"),
            new Novel("�ۺ������ʴ´�", "2021-09-09", 332, 15120, "�Ѱ�", "����Ҽ�")
        };

        // 1) ���� ���� ���
        System.out.println("==== ���� ���� ��� ====");
        for (int i = 0; i < publications.length; i++) {
            System.out.println((i + 1) + ". " + publications[i].toString());
        }
        System.out.println();

        // 2) ���� ����: 3��° ����(�ε��� 2)
        NumberFormat won = NumberFormat.getIntegerInstance(Locale.KOREA);
        Publication target = publications[6]; // ����: "�ۺ������ʴ´�" (���� ��°� ����)
        int before = target.getPrice();

        System.out.println("==== ���� ���� ====");
        System.out.println(target.getTitle() + " ���� �� ����: " + won.format(before) + "��");
        modifyPrice(target);
        int after = target.getPrice();
        System.out.println(target.getTitle() + " ���� �� ����: " + won.format(after) + "��");
        System.out.println("����: " + won.format(before - after) + "��");
        System.out.println();

        // 3) ��� �м�
        StatisticsAnalyzer analyzer = new StatisticsAnalyzer();
        analyzer.printStatistics(publications);
    }

    // Ÿ�Ժ� ���� ���� ����
    public static void modifyPrice(Publication publication) {
        int currentPrice = publication.getPrice();

        if (publication instanceof Magazine) {
            publication.setPrice((int) Math.round(currentPrice * 0.6)); // 40% ����
        } else if (publication instanceof Novel) {
            publication.setPrice((int) Math.round(currentPrice * 0.8)); // 20% ����
        } else if (publication instanceof ReferenceBook) {
            publication.setPrice((int) Math.round(currentPrice * 0.9)); // 10% ����
        }
    }
}
