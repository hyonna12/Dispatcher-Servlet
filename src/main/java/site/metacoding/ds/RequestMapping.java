package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})	// 메서드, 클래스, 필드(변수)
// 배열로 타켓을 여러 개 지정
// 이 Requestmapping은 메서드 위에서 작동하도록

@Retention(RetentionPolicy.RUNTIME)	// RUNTIME(런타임시), SOURCE(컴파일시)
//언제동작할지를 결정할수있음
//동작타이밍을 결정할수있음
public @interface RequestMapping {
	String value();	// value 메서드 고정, 키값
	// annotation쓸 때 괄호안에 들어갈 값을 이걸 적어야 쓸수있음
}
