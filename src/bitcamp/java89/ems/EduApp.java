package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    Book[] books = new Book[100];
    int length = 0;

    Scanner keyScan = new Scanner(System.in);

    while (length < books.length) {
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

      books[length++] = book;
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!keyScan.nextLine().equals("y"))
        break;
    }
    printBookList(books, length);
  }

  static void printBookList(Book[] books, int length) {
    for (int i = 0; i < length; i++) {
      Book book = books[i];
      System.out.printf("%s,%s,%s,%d,%d,%d,%s\n",
      book.title,
      book.author,
      book.press,
      book.date,
      book.price,
      book.page,
      ((book.sale) ? "yes" : "no"));
    }
  }
}
