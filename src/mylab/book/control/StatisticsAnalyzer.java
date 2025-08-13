package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {

    // 1) 타입별 평균 가격
    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> sum = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();

        for (Publication p : publications) {
            String type = getPublicationType(p);
            sum.put(type, sum.getOrDefault(type, 0) + p.getPrice());
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avg = new HashMap<>();
        for (String type : sum.keySet()) {
            avg.put(type, sum.get(type) / (double) cnt.get(type));
        }
        return avg;
    }

    // 2) 출판물 유형 분포(백분율)
    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> cnt = new HashMap<>();
        for (Publication p : publications) {
            String type = getPublicationType(p);
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }
        Map<String, Double> dist = new HashMap<>();
        int total = publications.length;
        for (String type : cnt.keySet()) {
            dist.put(type, (cnt.get(type) * 100.0) / total);
        }
        return dist;
    }

    // 3) 특정 연도 비율
    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int hit = 0;
        for (Publication p : publications) {
            String y = (p.getPublishDate() != null && p.getPublishDate().length() >= 4)
                    ? p.getPublishDate().substring(0, 4) : "";
            if (year.equals(y)) hit++;
        }
        return (hit * 100.0) / publications.length;
    }

    // 4) 타입 문자열
    public String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    // 5) 전체 통계 출력
    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        System.out.println("===== 출판물 통계 분석 =====");

        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
        for (String type : avg.keySet()) {
            System.out.println("   - " + type + ": " + df.format(avg.get(type)) + "원");
        }
        System.out.println();

        System.out.println("2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (String type : dist.keySet()) {
            System.out.println("   - " + type + ": " + df.format(dist.get(type)) + "%");
        }
        System.out.println();

        System.out.println("3. 2007년에 출판된 출판물 비율: "
                + df.format(calculatePublicationRatioByYear(publications, "2007")) + "%");
    }
}