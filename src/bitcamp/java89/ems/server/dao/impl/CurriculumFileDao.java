package bitcamp.java89.ems.server.dao.impl;

import java.util.ArrayList;

import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

//@Component
public class CurriculumFileDao extends AbstractFileDao<Curriculum> implements CurriculumDao {
  public CurriculumFileDao() throws Exception {
    this.setFilename("curriculum-v1.9.data");
    try {this.load();} catch (Exception e) {}
  }
  
  public ArrayList<Curriculum> getList() {
    return this.list;
  }
  
  public ArrayList<Curriculum> getListByName(String name) {
    ArrayList<Curriculum> results = new ArrayList<>();
    for (Curriculum curriculum : list) {
      if (curriculum.getName().equals(name)) {
        results.add(curriculum);
      }
    }
    return results;
  }
  
  synchronized public void insert(Curriculum curriculum) {
    list.add(curriculum);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Curriculum curriculum) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getTarget().equals(curriculum.getTarget())) {
        list.set(i, curriculum);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  synchronized public void delete(String target) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getTarget().equals(target)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existTarget(String target) {
    for (Curriculum curriculum : list) {
      if (curriculum.getTarget().toLowerCase().equals(target.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
