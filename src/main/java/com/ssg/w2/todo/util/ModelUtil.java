package com.ssg.w2.todo.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;


public enum ModelUtil {

    INSTANCE;//싱글턴 패턴 사용!

    private ModelMapper modelMapper;


    //private ModelMapper modelMapper;//별도의 캐스팅 과정 없이 DTO ⇒ VO , VO⇒DTO 변환하기 위해 사용함!

    ModelUtil(){
        this.modelMapper = new ModelMapper();//생성자를 통해 인스턴스 하고
        this.modelMapper.getConfiguration()//모델매퍼에 대한 환경 설정을 같이 하겠다.
                .setFieldMatchingEnabled(true)//DTO ⇒ VO , VO⇒DTO 변환하는 것을 허락하겠다~
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);//변환할 때 각각의 필드들의 순서나 속성 이름 등등이 완벽하게 일치할 때만~
        System.out.println("매퍼 실행됨!!!!!");
        /* ModelUtil의 생성자
이 생성자는 ModelMapper 객체를 초기화하고, 필요한 설정을 합니다.
this.modelMapper = new ModelMapper(): ModelMapper 객체를 생성합니다.
setFieldMatchingEnabled(true): 객체 간 필드 이름이 동일할 경우, 이를 자동으로 매칭하도록 설정합니다. 즉, 필드 이름이 같으면 DTO와 VO 간에 자동 변환이 이루어집니다.
setFieldAccessLevel(Configuration.AccessLevel.PRIVATE): 필드 접근 수준을 PRIVATE으로 설정합니다. 즉, 객체의 private 필드도 변환할 수 있도록 허용합니다.
setMatchingStrategy(MatchingStrategies.STRICT): 매칭 전략을 STRICT로 설정하여 필드 이름이 정확하게 일치해야만 매핑이 되도록 합니다. 이 방식은 변환의 정확성을 높여줍니다.*/

    }

    public ModelMapper get(){
        return modelMapper; //get()메소드를 호출하여 DTO ⇒ VO , VO⇒DTO 변환이 주로 일어나는 service 영역에서 사용하게 된다!!


    }
}
