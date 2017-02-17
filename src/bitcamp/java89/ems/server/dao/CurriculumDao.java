package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Curriculum;

public interface CurriculumDao {
  ArrayList<Curriculum> getListByName(String name) throws Exception;
  ArrayList<Curriculum> getList() throws Exception;
  void insert(Curriculum curriculum) throws Exception;
  void update(Curriculum curriculum) throws Exception;
  void delete(String target) throws Exception;
  boolean existTarget(String target) throws Exception;
}
