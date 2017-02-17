package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;
import bitcamp.java89.ems.server.context.RequestHandlerMapping.RequestHandler;

public class RequestThread extends Thread {
  private Socket socket;
  private PrintStream out;
  private Scanner in;
  private RequestHandlerMapping handlerMapping;
  
  public RequestThread(Socket socket, RequestHandlerMapping handlerMapping) {
    this.socket = socket;
    this.handlerMapping = handlerMapping;
  }
  
  @Override
  public void run() {
    // 스레드가 독립적으로 실행할 코드를 두는 곳.
    try {
      in = new Scanner(
          new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(
          new BufferedOutputStream(socket.getOutputStream()), true);
      
      out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
      
      while(true) {
        out.println("명령>");
        out.println();
        
        // 클라이언트가 보낸 명령문을 분석하여 명령어와 파라미터 분리한다.
        String[] command = in.nextLine().split("\\?");
        
        // 파라미터를 분석하여 HashMap에 보관한다.
        HashMap<String,String> paramMap = new HashMap<>();
        if (command.length == 2) {
          String[] params = command[1].split("&");
          for (String value : params) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
          }
        }

        RequestHandler requestHandler = handlerMapping.getRequestHandler(command[0]);
        
        if (requestHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("올바른 명령어가 아닙니다. 다시 입력하세요.");
          continue;
        }
        
        try {
          requestHandler.method.invoke(requestHandler.obj, paramMap, out);
        } catch (Exception e) {
          out.println("작업 중 오류가 발생했습니다.");
          e.printStackTrace();
        }
      } // while
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }
  
  private boolean doQuit() {
    System.out.println("클라이언트 연결 종료!");
    return true;
  }
}
