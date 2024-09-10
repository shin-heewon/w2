package com.ssg.w2.todo.controller;

import com.ssg.w2.todo.domain.MemberVO;
import com.ssg.w2.todo.dto.MemberDTO;
import com.ssg.w2.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Log4j2
@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    //get은 로그인 화면을 보여주고, POST 방식으로 실제 로그인 처리하도록 구성한다.
    // 1. webServlet 해당 컨트롤러 등록 /login
    //2. doGet 메소드를 통해 login.jsp 파일로 포워딩
    //3. login.jsp 파일 만들기 - text(id, pw용 각각), id(mid), pwd(mpw), submit 버튼

    private MemberService memberService = MemberService.INSTANCE;
    private MemberDTO dto;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login.............GET");
        req.getRequestDispatcher("/todo/login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login...................POST");
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        log.info("아이디+비번"+mid+mpw);


        try {
            dto = MemberDTO.builder().mid(mid).mpw(mpw).build();

//        String str = mid + mpw;
//
//        HttpSession session = req.getSession(); //톰캣으로부터 부여받은 세션 값 받아옴
//        session.setAttribute("loginInfo", str);//세션이라는 공간에 key-value 형태로 만든 값 저장

            MemberVO vo = memberService.login(dto);

            log.info("로그인 정보~~~~~~~~~~~~~");
            log.info(vo.toString());

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", vo);


            resp.sendRedirect("/todo/list");//값을 들고 list로 이동
        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");//예외가 발생하면 /login 으로 이동하는데, result라는 파라미터를 전달하여 error가 발생한 사실을 전달
        }

    }
}
