package com.ssg.w2.todo.controller;

import com.ssg.w2.todo.dto.TodoDTO;
import com.ssg.w2.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Log4j2
@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo...list야......잘 왔어");

        try{
            List<TodoDTO> dtoList = todoService.listAll();

            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("/todo/list.jsp").forward(req, resp);

        }catch (Exception e){
            log.info(e.getMessage());
            throw  new ServletException("list error");}


        //코드버전 1
//        System.out.println("/todo/list.........call");
//        List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
//        req.setAttribute("dtolist", dtoList);
       // req.getRequestDispatcher("/todo/list.jsp").forward(req, resp); // list.jsp로 이동할 때 req, resp 들고 이동해라~
    }



}
