package com.ssg.w2.todo.service;


import com.ssg.w2.todo.dao.MemberDAO;
import com.ssg.w2.todo.domain.MemberVO;
import com.ssg.w2.todo.dto.MemberDTO;
import com.ssg.w2.todo.util.ModelUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {

    INSTANCE;

    private ModelMapper modelMapper;
    private MemberDAO dao;

    MemberService(){

        dao = new MemberDAO();
        modelMapper = ModelUtil.INSTANCE.get();
    }

    public MemberVO login(MemberDTO dto){

        log.info("MemberService................");

        try {
            log.info("dto :"+dto);
            MemberVO vo = modelMapper.map(dto, MemberVO.class);
            //return dao.getWithPassword(vo.getMid(), vo.getMpw());
            return dao.getWithPassword(vo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
