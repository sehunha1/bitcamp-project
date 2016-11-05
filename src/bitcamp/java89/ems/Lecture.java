package bitcamp.java89.ems;

public class Lecture {
  String name;        //강좌 이름
  String dstart;      //강좌 시작일
  String dend;        //강좌 종료일
  String time;        //교육 시간
  String company;     //강좌 센터명
  String location;    //강좌 센터위치
  String teachname;   //강사 이름
  int money;          //강좌 등록비
  int num;            //강좌 모집정원
  int no;          //강좌 기수
  boolean government; //국비지원여부

  public Lecture () {}

  public Lecture(String name, String company, int money, boolean government) {
    this.name = name;
    this.company = company;
    this.money = money;
    this.government = government;
  }
}
