package com.ssg.w2.todo.dao;

import com.ssg.w2.todo.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public void insert(TodoVO vo) throws Exception{

        String sql = "insert into tbl_todo(tno, title, dueDate, finished) values(null,?,?,?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt. setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());

        pstmt.executeUpdate();
    }


    public List<TodoVO> selectAllList() throws Exception {
        String sql = "select * from tbl_todo";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while(rs.next()){
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
            .build();

            list.add(vo);
        }
        return list;
    }

    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "select * from tbl_todo where tno =?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, tno);

        @Cleanup ResultSet rs = pstmt.executeQuery();

            rs.next();
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

        return vo;
    }


    //삭제 기능은 조회와 비슷하지만 쿼리(select)가 아니다. 삭제할 글번호 가 필요하다.
    public void deleteOne(long tno) throws Exception{
        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, tno);

        pstmt.executeUpdate();
    }

    public void updateOne(TodoVO vo) throws Exception{

        String sql = "update tbl_todo set title=?, dueDate=?, finished=? where tno = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());
        pstmt.setLong(4, vo.getTno());

        pstmt.executeUpdate();

    }


    public void deletOne(Long tno) throws Exception{
        String sql = "select * from tbl_tod where tno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setLong(1, tno);
        psmt.executeUpdate();
    }
}
