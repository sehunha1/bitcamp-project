package bitcamp.java89.ems;

import java.util.Scanner;

public class BookController {
  private Book[] books = new Book[100];
  private int length = 0;
  private Scanner keyScan;

  public BookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase();
      switch (command) {
        case "add" :
          this.doAdd();
          break;

        case "list" :
          this.doList();
          break;

        case "view" :
          this.doView();
          break;

        case "delete" :
          this.doDelete();
          break;

        case "update" :
          this.doUpdate();
          break;

        case "main" :
          break loop;

        default :
          System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  private void doList() {
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

  private void doAdd() {
    while (length < this.books.length) {
      Book book = new Book();
      System.out.print("서명?(문자로) ");
      book.title = this.keyScan.nextLine();
      System.out.print("저자?(문자로) ");
      book.author = this.keyScan.nextLine();
      System.out.print("출판사?(문자로) ");
      book.press = this.keyScan.nextLine();
      System.out.print("출간일?(예:20160101) ");
      book.date = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("가격?(숫자로) ");
      book.price = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("쪽수?(숫자로) ");
      book.page = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("판매중여부?(y/n) ");
      book.sale = (this.keyScan.nextLine().equals("y")) ? true : false;
      books[length++] = book;
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doView() {
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

  private void doDelete() {
    System.out.print("삭제할 서명은? ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (title.equals(this.books[i].title)) {
        for (int x = i + 1; x < this.length; x++, i++) {
          this.books[i] = this.books[x];
        }
        this.books[--length] = null;
        System.out.printf("%s를 삭제하였습니다.\n", title);
        return;
      }
    }
    System.out.printf("%s가 없습니다.\n", title);
  }

  private void doUpdate() {
    System.out.print("변경할 서명은? ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (title.equals(this.books[i].title)) {
        String temp1, temp2;
        int temp3, temp4, temp5;
        boolean temp6;
        System.out.print("저자?(문자로) ");
        temp1 = this.keyScan.nextLine();
        System.out.print("출판사?(문자로) ");
        temp2 = this.keyScan.nextLine();
        System.out.print("출간일?(예:20160101) ");
        temp3 = Integer.parseInt(this.keyScan.nextLine());
        System.out.print("가격?(숫자로) ");
        temp4 = Integer.parseInt(this.keyScan.nextLine());
        System.out.print("쪽수?(숫자로) ");
        temp5 = Integer.parseInt(this.keyScan.nextLine());
        System.out.print("판매중여부?(y/n) ");
        temp6 = (this.keyScan.nextLine().equals("y")) ? true : false;
        System.out.print("저장하시겠습니까?(y/n) ");
        String title2 = this.keyScan.nextLine().toLowerCase();
        if (title2.equals("y")) {
          this.books[i].author = temp1;
          this.books[i].press = temp2;
          this.books[i].date = temp3;
          this.books[i].price = temp4;
          this.books[i].page = temp5;
          this.books[i].sale = temp6;
          System.out.println("저장하였습니다.");
        } else {
          System.out.println("변경을 취소하였습니다.");
        }
        return;
      }
    }
    System.out.printf("%s라는 서명이 없습니다.\n", title);
  }
}
