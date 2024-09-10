package com.ssg.w2.todo.service;

import com.ssg.w2.todo.dao.TodoDAO;
import com.ssg.w2.todo.domain.TodoVO;
import com.ssg.w2.todo.dto.TodoDTO;
import com.ssg.w2.todo.util.ModelUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Log4j2
public enum TodoService {

    INSTANCE; // 이 통로를 이용해야지만 서비스를 이용할 수 있도록 함

    private TodoDAO dao;
    private ModelMapper modelMapper;
    TodoService(){

        dao = new TodoDAO(); // 직접 dao 주입
        modelMapper = ModelUtil.INSTANCE.get();

    }


    public void register(TodoDTO dto) throws Exception{
        TodoVO vo = modelMapper.map(dto, TodoVO.class);

        //System.out.println("todoVO : "+vo); -> 이런 정보를 log4j2를 통해 콘솔 출력 가능함
        log.info(vo);
        dao.insert(vo);
    }

//    public void register(TodoDTO dto){//하나의 글을 등록하는 기능
//        System.out.println("DEBUG................"+dto);
//    }

    public List<TodoDTO> listAll() throws Exception {//등록된 글 목록 반환하는 기능

        List<TodoVO> voList = dao.selectAllList();
        log.info(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream().map(vo->modelMapper.map(vo, TodoDTO.class)).collect(Collectors.toList());

        return dtoList;

    }

    public List<TodoDTO> getList(){//등록된 글 목록 반환하는 기능

        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i->{//10개의 TodoDTO(글)을 만들어서 리스트 객체로 반환

            TodoDTO dto = new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("Todo..."+i);
            dto.setDueDate(LocalDate.now());
            return dto;
        }).collect(Collectors.toList());//list로 변환해 줌

        return todoDTOS;
    }


    public TodoDTO get(Long tno) throws Exception{
//        TodoDTO dto = new TodoDTO();
//        dto.setTno(tno);
//        dto.setTitle("Sample Todo");
//        dto.setDueDate(LocalDate.now());
//        dto.setFinished(true);
        log.info("tno"+tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO dto = modelMapper.map(todoVO, TodoDTO.class);


        return dto;
    }


    public void remove(Long tno) throws Exception{
        log.info(tno);
        dao.deletOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception{
        log.info("todoDTO"+ todoDTO);
        TodoVO vo = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(vo);

    }






}
