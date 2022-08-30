package site.metacoding.ds;

public class UserController {
	
	@RequestMapping("/join")
	public void join() {
		System.out.println("join 호출됨");
	}
	
	@RequestMapping("/login")	//런타임시
	public void login() {
		System.out.println("login 호출됨");
	}
	// /join 하면 호출되도록 /login 하면 호출되도록
	
	@RequestMapping("/joinForm")	
	public void joinForm() {
		System.out.println("joinForm 호출됨");
	}
	
}
