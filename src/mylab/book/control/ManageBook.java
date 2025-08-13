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
            new Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"),
            new Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"),
            new Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"),
            new Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"),
            new ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"),
            new Novel("소년이온다", "2014-05-01", 216, 15000, "한강", "장편소설"),
            new Novel("작별하지않는다", "2021-09-09", 332, 15120, "한강", "장편소설")
        };

        // 1) 도서 정보 출력
        System.out.println("==== 도서 정보 출력 ====");
        for (int i = 0; i < publications.length; i++) {
            System.out.println((i + 1) + ". " + publications[i].toString());
        }
        System.out.println();

        // 2) 가격 변경: 3번째 도서(인덱스 2)
        NumberFormat won = NumberFormat.getIntegerInstance(Locale.KOREA);
        Publication target = publications[6]; // 예시: "작별하지않는다" (샘플 출력과 동일)
        int before = target.getPrice();

        System.out.println("==== 가격 변경 ====");
        System.out.println(target.getTitle() + " 변경 전 가격: " + won.format(before) + "원");
        modifyPrice(target);
        int after = target.getPrice();
        System.out.println(target.getTitle() + " 변경 후 가격: " + won.format(after) + "원");
        System.out.println("차액: " + won.format(before - after) + "원");
        System.out.println();

        // 3) 통계 분석
        StatisticsAnalyzer analyzer = new StatisticsAnalyzer();
        analyzer.printStatistics(publications);
    }

    // 타입별 차등 할인 적용
    public static void modifyPrice(Publication publication) {
        int currentPrice = publication.getPrice();

        if (publication instanceof Magazine) {
            publication.setPrice((int) Math.round(currentPrice * 0.6)); // 40% 할인
        } else if (publication instanceof Novel) {
            publication.setPrice((int) Math.round(currentPrice * 0.8)); // 20% 할인
        } else if (publication instanceof ReferenceBook) {
            publication.setPrice((int) Math.round(currentPrice * 0.9)); // 10% 할인
        }
    }
}
