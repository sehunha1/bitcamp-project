package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

@Component
public class CurriculumController {
  @Autowired CurriculumDao curriculumDao;
  
  @RequestMapping(value="curriculum/add")
  public void add(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (curriculumDao.existTarget(paramMap.get("target"))) {
      out.println("같은 강좌대상이 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Curriculum curriculum = new Curriculum();
    curriculum.setName(paramMap.get("name"));
    curriculum.setIntroduce(paramMap.get("introduce"));
    curriculum.setTarget(paramMap.get("target"));
    curriculum.setDocument(paramMap.get("document"));
    
    curriculumDao.insert(curriculum);
    out.println("등록하였습니다.");
  }
  
  @RequestMapping(value="curriculum/delete")
  public void delete(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (!curriculumDao.existTarget(paramMap.get("target"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    curriculumDao.delete(paramMap.get("target"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
  
  @RequestMapping(value="curriculum/list")
  public void list(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Curriculum> list = curriculumDao.getList();
    if (list.size() == 0) {
      out.println("강좌정보가 없습니다.");
      return;
    }
    for (Curriculum curriculum : list) {
      out.printf("%s,%s,%s,%s\n",
          curriculum.getName(),
          curriculum.getIntroduce(),
          curriculum.getTarget(),
          curriculum.getDocument());
    }
  }
  
  @RequestMapping(value="curriculum/update")
  public void update(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (!curriculumDao.existTarget(paramMap.get("target"))) {
      out.println("강좌대상을 찾지 못했습니다.");
      return;
    }
    
    Curriculum curriculum = new Curriculum();
    curriculum.setTarget(paramMap.get("target"));
    curriculum.setName(paramMap.get("name"));
    curriculum.setIntroduce(paramMap.get("introduce"));
    curriculum.setDocument(paramMap.get("document"));
    
    curriculumDao.update(curriculum);
    out.println("변경 하였습니다.");
  }
  
  @RequestMapping(value="curriculum/view")
  public void view(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Curriculum> list = curriculumDao.getListByName(paramMap.get("name"));
    for (Curriculum curriculum : list) {
      out.println("--------------------------");
      out.printf("강좌명: %s\n", curriculum.getName());
      out.printf("강좌소개: %s\n", curriculum.getIntroduce());
      out.printf("강좌대상: %s\n", curriculum.getTarget());
      out.printf("강좌준비서류: %s\n", curriculum.getDocument());
    }
  }
}