package com.ssg.w2.todo.controller;

import com.ssg.w2.todo.dto.TodoDTO;
import com.ssg.w2.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Log4j2
@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {


    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("todo/read................ 실행");
        Long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO todoDTO = todoService.get(tno);

            //DB로부터 전달받은 글 하나를 req에다 저장하기(담기)
            req.setAttribute("dto", todoDTO);


            /***쿠키 찾기***/
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todolistStr = viewTodoCookie.getValue();
            boolean exit = false;

            if(todolistStr != null && todolistStr.indexOf(tno+"-") >= 0){
                exit = true;
            }

            log.info("exit" + exit);

            if(!exit){
                todolistStr += tno +"-";
                viewTodoCookie.setValue(todolistStr);
                viewTodoCookie.setMaxAge(60*60);//쿠키 유효기간 1분
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);

            }

            req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        System.out.println("todo/read......실행");
//
//        //todo/read?tno=123 전달하기
//        Long tno = Long.parseLong(req.getParameter("tno"));
//
//        TodoDTO dto = TodoService.INSTANCE.get(tno);
//
//        req.setAttribute("dto", dto);
//        req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);
    }


    private Cookie findCookie(Cookie[] cookies, String cookieName){

        Cookie targetCookie = null;
        if(cookies != null && cookies.length > 0){
            for(Cookie ck : cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }
        if (targetCookie == null) {

            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*1);
        }

        return targetCookie;
    }


}


//1. 현재 요청( request)에 있는 모든 쿠키 중에 조회 목록 쿠키(viewTodos)를 찾아내는 메소드 작성
//2. 특정 tno 쿠키의 내용물이 있는지 확인

//doiGet viewTodos이름의 쿠키를 찾고, 쿠키의 내용물을 검사한 후 만일 없으면 viewTodos 라는 이름의 쿠키를 생성해줌