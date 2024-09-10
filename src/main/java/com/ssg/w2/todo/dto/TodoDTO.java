package com.ssg.w2.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {//데이터를 담을 모델 객체

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
