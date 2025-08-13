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
        System.out.println(item.getTitle() + "이(가) 장바구니에 추가되었습니다.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "이(가) 장바구니에서 제거되었습니다.");
                return true;
            }
        }
        System.out.println("해당 제목의 출판물을 찾을 수 없습니다.");
        return false;
    }

    public void displayCart() {
        NumberFormat won = NumberFormat.getIntegerInstance(Locale.KOREA);
        System.out.println("====== 장바구니 내용 ======");
        int idx = 1;
        for (Publication p : items) {
            System.out.println(idx++ + ". " + p.getTitle() + " - " + won.format(p.getPrice()) + "원");
        }
        int total = calculateTotalPrice();
        int discounted = calculateDiscountedPrice();
        System.out.println("총 가격: " + won.format(total) + "원");
        System.out.println("할인 적용 가격: " + won.format(discounted) + "원");
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
                total += item.getPrice() * 0.90; // 10% 할인
            } else if (item instanceof Novel) {
                total += item.getPrice() * 0.85; // 15% 할인
            } else if (item instanceof ReferenceBook) {
                total += item.getPrice() * 0.80; // 20% 할인
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
        System.out.println("====== 장바구니 통계 ======");
        System.out.println("잡지: " + magazine + "권");
        System.out.println("소설: " + novel + "권");
        System.out.println("참고서: " + ref + "권");
        System.out.println("총 출판물: " + items.size() + "권");
    }

    // 샘플 실행용 main
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new mylab.book.entity.Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"));
        cart.addItem(new mylab.book.entity.Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"));
        cart.addItem(new mylab.book.entity.Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"));
        cart.addItem(new mylab.book.entity.Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"));
        cart.addItem(new mylab.book.entity.ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"));

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("빠삐용");
        cart.displayCart();
    }
}