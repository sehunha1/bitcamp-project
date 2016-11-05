package bitcamp.java89.ems;

import java.util.Scanner;

public class TeacherController {
  private Teacher[] teachers = new Teacher[100];
  private int length = 0;
  private Scanner keyScan;

  public TeacherController(Scanner keyScan) {
    this.keyScan = keyScan;
  }
  public void service() {
    loop:
    while (true) {
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add": this.doAdd(); break;
      case "list": this.doList(); break;
      case "view": this.doView(); break;
      case "delete": this.doDelete(); break;
      case "update": this.doUpdate(); break;
      case "main": break loop;    
        default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  private void doList() {
    Teacher teacher = null;
    for (int i = 0; i < length; i++) {
      teacher = teachers[i];
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
        teacher.name,
        teacher.career,
        teacher.langauge,
        ((teacher.book) ? "yes" : "no"),
        teacher.email,
        teacher.tel);
    }
  }

  private void doAdd() {
    while (length < teachers.length) {
      Teacher teacher = new Teacher();
          System.out.print("이름(예:홍길동): ");
          teacher.name = keyScan.nextLine();

          System.out.print("경력(예:비트캠프학원 강사): ");
          teacher.career = keyScan.nextLine();

          System.out.print("언어사용(예:C,JAVA,PHP): ");
          teacher.langauge = keyScan.nextLine();

          System.out.print("책 발간(y/n): ");
          teacher.book = (keyScan.nextLine().equals("y") ? true : false);

          System.out.print("이메일(예:hong@gmail.com): ");
          teacher.email = keyScan.nextLine();

          System.out.print("전화번호(예:010-1111-2222): ");
          teacher.tel = keyScan.nextLine();
          teachers[length++] = teacher;
          System.out.print("계속 입력 하시겠습니까?(y/n)");
          if (!keyScan.nextLine().equals("y")) {
            break;
          }
    }
  }


  private void doView() {
    System.out.print("조회할 학생의 아이디는? ");
    String name = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (this.teachers[i].name.toLowerCase().equals(name)) {
        System.out.printf("이름: %s\n", this.teachers[i].name);
        System.out.printf("경력: %s\n", this.teachers[i].career);
        System.out.printf("언어 사용: %s\n", this.teachers[i].langauge);
        System.out.printf("책 발간: %s\n", (this.teachers[i].book) ? "Yes" : "No");
        System.out.printf("이메일: %s\n", this.teachers[i].email);
        System.out.printf("전화: %s\n", this.teachers[i].tel);
        break;
      }
    }
  }

  private void doDelete() {
    System.out.print("삭제할 학생의 이름은? ");
    String name = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if (this.teachers[i].name.toLowerCase().equals(name)) {

        for (int x = i + 1; x < this.length; x++, i++) {
          this.teachers[i] = this.teachers[x];
        }
        this.teachers[--length] = null;

        System.out.printf("%s 학생 정보를 삭제하였습니다.\n", name);
        return; // 함수 실행을 종료한다.
      }
    }
    System.out.printf("%s 학생이 없습니다.\n", name);
  }

    private void doUpdate() {
      System.out.print("변경할 학생의 이름은? ");
      String name = this.keyScan.nextLine().toLowerCase();
        for (int i =0; i < this.length; i++){
          if (this.teachers[i].name.toLowerCase().equals(name)) {
          System.out.printf("이름: %s\n", this.teachers[i].name = this.keyScan.nextLine().toLowerCase());
          System.out.printf("경력: %s\n", this.teachers[i].career = this.keyScan.nextLine().toLowerCase());
          System.out.printf("언어사용: %s\n", this.teachers[i].langauge = this.keyScan.nextLine().toLowerCase());
          System.out.printf("책 발간: %s\n", this.teachers[i].book = (keyScan.nextLine().equals("y") ? true : false));
          System.out.printf("이메일: %s\n", this.teachers[i].email = this.keyScan.nextLine().toLowerCase());
          System.out.printf("전화번호: %s\n", this.teachers[i].tel = this.keyScan.nextLine().toLowerCase());
          System.out.println("저장하시겠습니까?(y/n)");
          if (keyScan.nextLine().equals("y")) {
            System.out.println("저장하였습니다.");
          }
          else {
            System.out.println("저장을 취소하였습니다.");
          }
          return
          ;
        }
        else
        System.out.printf("%s 이라는 학생이 없습니다.", name);
        return;
      }



    }
}
