package com.ssg.w2.todo.dao;


import com.ssg.w2.todo.domain.MemberVO;
import com.ssg.w2.todo.dto.MemberDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Log4j2
public class MemberDAO {
    public MemberVO getWithPassword(MemberVO vo) throws Exception {

        String sql = "select * from tbl_member where mid =? and mpw=?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, vo.getMid());
        pstmt.setString(2, vo.getMpw());

        @Cleanup ResultSet rs = pstmt.executeQuery();


        rs.next();
        MemberVO vo2 = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString(2))
                .mname(rs.getString(3))
                .build();


        log.info("MEMBERDAO 실행, vo2 : "+vo2.toString());

        return vo2;
    }
}
