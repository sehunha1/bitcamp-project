package bitcamp.java89.ems;

import java.util.Scanner;

public class BookController {
  Book[] books = new Book[100];
  int length = 0;
  Scanner keyScan;

  public BookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void doList() {
    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];
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

  public void doAdd() {
    while (length < this.books.length) {
      Book book = new Book();
      System.out.print("서명?(문자로) ");
      book.title = this.keyScan.nextLine();
      System.out.print("저자?(문자로) ");
      book.author = this.keyScan.nextLine();
      System.out.print("출판사?(문자로) ");
      book.press = this.keyScan.nextLine();
      System.out.print("출간일(예:20160101)? ");
      book.date = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("가격?(숫자로) ");
      book.price = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("쪽수?(숫자로) ");
      book.page = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("판매중여부(y/n)? ");
      book.sale = (this.keyScan.nextLine().equals("y")) ? true : false;
      books[length++] = book;
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  public void doView() {
    System.out.print("조회할 서명은? ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (title.equals(this.books[i].title)) {
        System.out.println("-----------------------------------");
        System.out.printf("서명: %s\n", this.books[i].title);
        System.out.printf("저자: %s\n", this.books[i].author);
        System.out.printf("출판사: %s\n", this.books[i].press);
        System.out.printf("출간일: %d\n", this.books[i].date);
        System.out.printf("가격: %d\n", this.books[i].price);
        System.out.printf("쪽수: %d\n", this.books[i].page);
        if (this.books[i].sale == true) {
          System.out.println("판매중여부: yes");
        } else {
          System.out.println("판매중여부: no");
        }
      }
    }
  }
}
