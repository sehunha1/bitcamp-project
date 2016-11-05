package bitcamp.java89.ems;

import java.util.Scanner;

public class LectureController {
  private Box head = new Box();
  private Box tail = head;
  private int count = 0;
  private Scanner keyScan;

  public LectureController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      Lecture lecture = new Lecture();
      System.out.print("강좌관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": this.doAdd(); break;
        case "list": this.doList(); break;
        case "view": this.doView(); break;
        case "delete": this.doDelete(); break;
        case "update": this.doUpdate(); break;
        case "main": break loop;
        default: System.out.println("지원하지 않는 명령어 입니다"); break;
      }
    }
  }


  private void doAdd() {
    while (true) {
      Lecture lecture = new Lecture();
      System.out.print("강좌명 : ");
      lecture.name = this.keyScan.nextLine();

      System.out.print("강좌기수 (예 : 89) : ");
      lecture.no = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("강좌시작일 (예 : 2016-10-25) : ");
      lecture.dstart = this.keyScan.nextLine();

      System.out.print("강좌종료일 (예 : 2017-03-25) : ");
      lecture.dend = this.keyScan.nextLine();

      System.out.print("교육시간 (예 : 09:00~18:00) : ");
      lecture.time = this.keyScan.nextLine();

      System.out.print("강좌 센터명 : ");
      lecture.company = this.keyScan.nextLine();

      System.out.print("강좌 센터 위치 (예 : 2호선 강남역 5번출구) : ");
      lecture.location = this.keyScan.nextLine();

      System.out.print("강좌 담당 강사 : ");
      lecture.teachname = this.keyScan.nextLine();

      System.out.print("강좌 등록비 : ");
      lecture.money = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("강좌 모집 정원 : ");
      lecture.num = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("국비지원여부 (y/n) : ");
      lecture.government = (this.keyScan.nextLine().equals("y")) ? true : false;

      tail.value = lecture;
      tail.next = new Box();
      tail = tail.next;
      count++;

      System.out.print("계속 입력하시겠습니까? (y/n) : ");

      if (!keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doList() {
    Box now = head;
    while (now != null && now != tail) {
      Lecture lecture = (Lecture)now.value;
      System.out.printf("%s %s %s %s %s %s %s %s %d %d %s\n",
      lecture.name, lecture.no, lecture.dstart, lecture.dend, lecture.time, lecture.company
      , lecture.location, lecture.teachname, lecture.money, lecture.num, (lecture.government)? "yes" : "No");
      now = now.next;
    }
  }
/*
    private void doView() {
      System.out.print("조회할 강좌의 이름은? ");
      String name = this.keyScan.nextLine().toLowerCase();

      for (int i = 0; i < length; i++) {
        if (lectures[i].name.toLowerCase().equals(name)) {
          System.out.println("======================================================");
          System.out.printf("강좌명 : %s\n", this.lectures[i].name);
          System.out.printf("강좌기수 : %d기\n", this.lectures[i].no);
          System.out.printf("강좌시작일 : %s\n", this.lectures[i].dstart);
          System.out.printf("강좌종료일 : %s\n", this.lectures[i].dend);
          System.out.printf("교육시간 : %s\n", this.lectures[i].time);
          System.out.printf("강좌 센터명 : %s\n", this.lectures[i].company);
          System.out.printf("강좌 센터 위치 : %s\n", this.lectures[i].location);
          System.out.printf("강좌 담당 강사 : %s\n", this.lectures[i].teachname);
          System.out.printf("강좌 등록비 : %d원\n", this.lectures[i].money);
          System.out.printf("강좌 모집 정원 : %d명\n", this.lectures[i].num);
          System.out.printf("국비지원여부 : %s\n", (this.lectures[i].government)? "yes" : "No");
          System.out.println("======================================================");
          break;
      }
    }
  }

  private void doDelete() {
    System.out.print("삭제할 강좌의 이름은? ");
    String name = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if (lectures[i].name.toLowerCase().equals(name)) {
        for (int x = i + 1; x < this.length; x++) {
          this.lectures[i] = this.lectures[x];
        }
        this.lectures[--length] = null;
        System.out.printf("%s의 강좌 정보를 삭제하였습니다.\n", name);
        return;
      }
    }
   System.out.printf("%s라는 강좌가 없습니다.\n", name);

  }

  private void doUpdate() {

      System.out.print("변경할 강좌의 이름은? ");
      String name = this.keyScan.next().toLowerCase();

      for (int i = 0; i < this.length; i++) {
      Lecture lectureup = new Lecture();

        if (lectures[i].name.toLowerCase().equals(name)) {
          lectureup.name = this.keyScan.nextLine();

          System.out.print("강좌이름 : ");
          lectureup.name = this.keyScan.nextLine();

          System.out.print("강좌기수 (예 : 89) : ");
          lectureup.no = Integer.parseInt(this.keyScan.nextLine());

          System.out.print("강좌시작일 (예 : 2016-10-25) : ");
          lectureup.dstart = this.keyScan.nextLine();

          System.out.print("강좌종료일 (예 : 2017-03-25) : ");
          lectureup.dend = this.keyScan.nextLine();

          System.out.print("교육시간 (예 : 09:00~18:00) : ");
          lectureup.time = this.keyScan.nextLine();

          System.out.print("강좌 센터명 : ");
          lectureup.company = this.keyScan.nextLine();

          System.out.print("강좌 센터 위치 (예 : 2호선 강남역 5번출구) : ");
          lectureup.location = this.keyScan.nextLine();

          System.out.print("강좌 담당 강사 : ");
          lectureup.teachname = this.keyScan.nextLine();

          System.out.print("강좌 등록비 : ");
          lectureup.money = Integer.parseInt(this.keyScan.nextLine());

          System.out.print("강좌 모집 정원 : ");
          lectureup.num = Integer.parseInt(this.keyScan.nextLine());

          System.out.print("국비지원여부 (y/n) : ");
          lectureup.government = (this.keyScan.nextLine().equals("y")) ? true : false;

          System.out.print("저장 하시겠습니까? (y/n) : ");

          if (this.keyScan.nextLine().equals("y")) {
            lectures[i].name = lectureup.name;
            lectures[i].no = lectureup.no;
            lectures[i].dstart = lectureup.dstart;
            lectures[i].dend = lectureup.dend;
            lectures[i].time = lectureup.time;
            lectures[i].company = lectureup.company;
            lectures[i].location = lectureup.location;
            lectures[i].teachname = lectureup.teachname;
            lectures[i].money = lectureup.money;
            lectures[i].num = lectureup.num;
            lectures[i].government = lectureup.government;
            //break;
          } else {
              System.out.println("취소 되었습니다");
          }
       }
       else
         System.out.println("없는 강좌명 입니다."); {
           break;
         }
      }
    }
  }
  */
}
