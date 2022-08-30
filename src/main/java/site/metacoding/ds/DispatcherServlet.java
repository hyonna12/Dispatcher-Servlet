package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod();
		System.out.println(httpMethod);
		String identifier = req.getRequestURI();
		System.out.println(identifier);
		// 식별자는 send 하는 순간파싱
		
		// 공통 로직 처리
		UserController c = new UserController();
		
		Method[] methodList = c.getClass().getDeclaredMethods();	
		for (Method method : methodList) {
			// System.out.println(method.getName());
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);	
			RequestMapping requestMapping = (RequestMapping) annotation;	// 부모를 리턴하고 다운캐스팅으로 씀, rm의 부모 annotation
			try {
				// System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c);	// invoke 매개변수에 (메모리에 뜬 레퍼런스 주소,메서드의 parameter/없어서 지워줌)
					// requestmapping에 있는 /login만보고 메서드 떄려줌
				}
			} catch (Exception e){
				System.out.println(method.getName()+"은 어노테이션이 없습니다.");
			}
			
			System.out.println(requestMapping.value());
			//런타임시 클래스를 분석하는 방법, 분석하면서 annotation찾음 annotation의 밸류값
			
			// invoke 오류 발생
			// 밸류를 호출했더니 requestmapping 밸류가 null임
			// c는 new된 객체, usercontroller.class한건 클래스의 파일을 찾아서 requestmapping의 밸류값을 호출
			// 이 requestmapping은 메모리에 아직 안뜸 -> c로 불러야함, 메모리에 뜬걸로
		}
		// methodList 크기만큼 for문 돌면서 배열을 넣음
		// 배열을 돌때마다 for문을 돌면서 값이 들어감
		// 0번지가 method들어감, 돌면서 1번지 2번지 들어감
		// usercontroller에 있는 메서드들을 런타임시에 찾음
		
		
		
//		if(identifier.equals("/join")) {
//			c.join();
//		} else if(identifier.equals("/login")){
//			c.login();
//		} else {
//			System.out.println("잘못된 요청입니다.");
//		}
		// 주소를 파싱해서 controller에 메서드를 떄려줌
	}
	
}
