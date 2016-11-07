package bitcamp.java89.ems;

import java.util.Scanner;
import java.util.ArrayList;

public class BookController {
  private ArrayList<Book> list;
  private Scanner keyScan;

  public BookController(Scanner keyScan) {
    list = new ArrayList<Book>();
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase();
      try {
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
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        System.out.println("인덱스 값이 잘못되었거나, 실행 중 오류가 발생했습니다.");
      }
    }
  }

  private void doList() {
    for (Book book : list) {
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

  private void doUpdate() {
    System.out.print("변경할 서명의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Book oldBook = list.get(index);

    Book book = new Book();
    System.out.printf("저자(%s)? ", oldBook.author);
    book.author = this.keyScan.nextLine();

    System.out.printf("출판사(%s)? ", oldBook.press);
    book.press = this.keyScan.nextLine();

    System.out.printf("출간일(%d)? ", oldBook.date);
    book.date = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("가격(%d)? ", oldBook.price);
    book.price = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("쪽수(%d)? ", oldBook.page);
    book.page = Integer.parseInt(this.keyScan.nextLine());

    System.out.print("판매중여부(y/n)? ");
    book.sale = (this.keyScan.nextLine().equals("y")) ? true : false;

    System.out.print("저장하시겠습니까(y/n)? ");
    if (keyScan.nextLine().toLowerCase().equals("y")) {
      book.title = oldBook.title;
      list.set(index, book);
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }

  private void doAdd() {
    while (true) {
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
        
      list.add(book);
      
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doView() {
    System.out.print("서명의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Book book = list.get(index);

    System.out.printf("서명: %s\n", book.title);
    System.out.printf("저자: %s\n", book.author);
    System.out.printf("출판사: %s\n", book.press);
    System.out.printf("출간일: %d\n", book.date);
    System.out.printf("가격: %d\n", book.price);
    System.out.printf("쪽수: %d\n", book.page);
    System.out.printf("판매중여부: %s\n", (book.sale) ? "Yes" : "No");
  }

  private void doDelete() {
    System.out.print("삭제할 서명의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Book deletedBook = list.remove(index);

    System.out.printf("%s 교재 정보를 삭제하였습니다.\n", deletedBook.title);
  }
}
