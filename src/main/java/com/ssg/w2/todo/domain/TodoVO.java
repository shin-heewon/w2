package com.ssg.w2.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
// //getter, setter, toString, equals, hashCode 컴파일할 때 자동 생성해준다.
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {//원래 VO는 읽기 전용으로 사용해서 setter는 사용자ㅣ 않는다.

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
