package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.util.DataSource;
import bitcamp.java89.ems.server.vo.Curriculum;

@Component
public class CurriculumMysqlDao implements CurriculumDao {
  @Autowired DataSource ds;
  
  public ArrayList<Curriculum> getList() throws Exception {
    ArrayList<Curriculum> list = new ArrayList<>();
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("select name, intr, targ, docu from ex_curriculums");
      ResultSet rs = stmt.executeQuery();) {
      
      while (rs.next()) {
        Curriculum curriculum = new Curriculum();
        
        curriculum.setName(rs.getString("name"));
        curriculum.setIntroduce(rs.getString("intr"));
        curriculum.setTarget(rs.getString("targ"));
        curriculum.setDocument(rs.getString("docu"));
        
        list.add(curriculum);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public ArrayList<Curriculum> getListByName(String name) throws Exception {
    ArrayList<Curriculum> list = new ArrayList<>();
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("select intr, name, targ, docu from ex_curriculums where name=?");) {
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
        Curriculum curriculum = new Curriculum();
        
        curriculum.setName(rs.getString("name"));
        curriculum.setIntroduce(rs.getString("intr"));
        curriculum.setTarget(rs.getString("targ"));
        curriculum.setDocument(rs.getString("docu"));
        
        list.add(curriculum);
      }
      rs.close();
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public void insert(Curriculum curriculum) throws Exception {
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("insert into ex_curriculums(name,docu,intr,targ) values(?,?,?,?)");) {
      
      stmt.setString(1, curriculum.getName());
      stmt.setString(2, curriculum.getDocument());
      stmt.setString(3, curriculum.getIntroduce());
      stmt.setString(4, curriculum.getTarget());
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public void update(Curriculum curriculum) throws Exception {
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("update ex_curriculums set intr=?, name=?, docu=? where targ=?");) {
      
      stmt.setString(1, curriculum.getIntroduce());
      stmt.setString(2, curriculum.getName());
      stmt.setString(3, curriculum.getDocument());
      stmt.setString(4, curriculum.getTarget());
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public void delete(String target) throws Exception {
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("delete from ex_curriculums where targ=?");) {
      
      stmt.setString(1, target);
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public boolean existTarget(String target) throws Exception {
    Connection con = ds.getConnection();
    
    try (
      PreparedStatement stmt = con.prepareStatement("select * from ex_curriculums where targ=?");) {
      
      stmt.setString(1, target);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
}
