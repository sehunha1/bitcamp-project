package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    Scanner keyScan = new Scanner(System.in);
    Book book = new Book();

    System.out.print("서명? ");
    book.title = keyScan.nextLine();

    System.out.print("저자? ");
    book.author = keyScan.nextLine();

    System.out.print("출판사? ");
    book.press = keyScan.nextLine();

    System.out.print("출간일(예:20160101)? ");
    book.date = Integer.parseInt(keyScan.nextLine());

    System.out.print("가격? ");
    book.price = Integer.parseInt(keyScan.nextLine());

    System.out.print("쪽수? ");
    book.page = Integer.parseInt(keyScan.nextLine());

    System.out.print("판매중여부(y/n)? ");
    book.sale = (keyScan.nextLine().equals("y")) ? true : false;

    System.out.printf("서명: %s\n", book.title);
    System.out.printf("저자: %s\n", book.author);
    System.out.printf("출판사: %s\n", book.press);
    System.out.printf("출간일: %d\n", book.date);
    System.out.printf("가격: %d\n", book.price);
    System.out.printf("쪽수: %d\n", book.page);
    System.out.printf("판매중여부: %b\n", book.sale);
  }
}
