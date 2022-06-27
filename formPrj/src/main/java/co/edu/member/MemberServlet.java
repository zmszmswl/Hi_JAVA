package co.edu.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();

	}
	// get 방식 요청 :  조회
	// post 방식 요청 :  입력, 수정, 삭제 (form 테그로 해야함)
	// 서블릿 처리하는데 2가지 방법
	// 1) form 태그 (jsp 수업때. 서블릿 => 화면.jsp)  폼은 name 으로 파라메터 넘김
	// 2) ajax (Single Page Application)xhtp.open ('get','member?cmd=insert&name=Ajax&addr=incheon')
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 응답정보에 한글처리.
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		String cmd = request.getParameter("cmd"); // 페이지 이름에 파라메타 이름이 cmd 가 오면
		Gson gson = new GsonBuilder().create(); // json 데이터 생성
		MemberDAO dao = new MemberDAO();
		
		
		if (cmd.equals("list")) { // http://localhost/formPrj/member?cmd=list
			// 전체 리스트 => json 화면 보여주기. 
			List<MemberVO> list = dao.memberList();// 전체 건수를 리턴해주는 메소드의 주소값을 list 변수값으로 받아옴 
			response.getWriter().print(gson.toJson(list));
			
		} else if (cmd.equals("insert")) {
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");
			
			MemberVO vo = new MemberVO();
			vo.setMembName(name);
			vo.setMembAddr(addr);
			dao.insertMember(vo);
			response.getWriter().print(gson.toJson(vo));
			
		} else if (cmd.equals("update")) {
			// 19 - 전화번호
			String numb = request.getParameter("no"); // 파라메터 이름과 변수 이름을 달리해도 괜찮다.
			String phone = request.getParameter("ph");
			
			MemberVO vo = new MemberVO();
			vo.setMembNo(Integer.parseInt(numb)); // "19"
			vo.setMembPhone(phone);
			
			if(dao.updateMember(vo)) { // http://localhost/formPrj/member?cmd=update&no=7&ph=010-8888-0000
				// {"retCode" : "Success"}
				response.getWriter().print("{\"retCode\" : \"Success\"}");
			}else {
				// {"retCode" : "Fail"}
				response.getWriter().print("{\"retCode\" : \"Fail\"}");
			}
		
		} else if (cmd.equals("delete")) { // 
			String delNo = request.getParameter("delNumber");
			if(dao.deleteMember(Integer.parseInt(delNo))) {
				response.getWriter().print("{\"retCode\" : \"Success\"}");
			}else {
				// {"retCode" : "Fail"}
				response.getWriter().print("{\"retCode\" : \"Fail\"}"); // 컨텍스트를 다시 로드하는 것을 완료했습니다.
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 처리.
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		// post 방식의 요청이 되면 실행할 메소드.
		String cmd = request.getParameter("cmd");
		
		String membName = request.getParameter("name");
		String membAddr = request.getParameter("addr");
		String membPhon = request.getParameter("phone");
		String membBirt = request.getParameter("birth");
		String membImag = request.getParameter("image");

		MemberVO vo = new MemberVO();
		vo.setMembName(membName);
		vo.setMembAddr(membAddr);
		vo.setMembPhone(membPhon);
		vo.setMembBirth(membBirt);
		vo.setMembImage(membImag);
		
		MemberDAO dao = new MemberDAO();
		
		Gson gson = new GsonBuilder().create();
		
//		response.getWriter();
		
		PrintWriter out = response.getWriter();
		JsonObject obj = new JsonObject();
		// 1. 입력
		if(cmd.equals("add")) {
			dao.insertMember(vo);
			out.print(gson.toJson(vo)); // json
			
			// 2. 수정
		} else if (cmd.equals("modify")) {
			String mNo = request.getParameter("membNo");
			vo.setMembNo(Integer.parseInt(mNo));
			
			if(dao.updateMember(vo)) {
				// {"membNo" : "mNo" , "membName" : "membName", "membAddr" : "membAddr", "membPhone" : "membPhon", "membBirth" : "membBirt", "retCode": " Success"}
				// {"mNo" : ? , "mName" : ? , "mAddr" :?, ... } json 타입 데이터를 => 자바스크립트 object 타입으로 변경 (parsing)
				// out.print("{\"retCode\" : \"Success\"}");
				// out.print("{\"membNo\" : \""+mNo+"\" , \"membName\" : \""+membName+"\", \"membAddr\" : \""+membAddr+"\", \"membPhone\" : \""+membPhon+"\", \"membBirth\" : \""+membBirt+"\", \"retCode\": \" Success\"}");
				// {"name": "홍길동", "age":20} json데이터타입
				obj.addProperty("membNo", mNo); // {"membNo" : 20}
				obj.addProperty("membName", membName); // {"membNo" : 20, "membName" : "홍길동"}
				obj.addProperty("membAddr", membAddr);
				obj.addProperty("membPhone", membPhon);
				obj.addProperty("membBirth", membBirt);
				obj.addProperty("retCode", "Success");
				
			} else {
				
				obj.addProperty("retCode", "Fail");
			}
			System.out.println(gson.toJson(obj));
			out.print(gson.toJson(obj)); // gson메소드중에 제이슨 문자열로 받는거 쓰겠다. 오브젝트타입을 제이슨타입으로 바꾸겠습니다. 오브젝트 => 문자열
			
			// 3. 삭제
		} else if (cmd.equals("remove")) {
			String delNo = request.getParameter("delNo");
			if(dao.deleteMember(Integer.parseInt(delNo))) {
				out.print("{\"retCode\" : \"Success\"}");
			} else {
				out.print("\"{\"retCode\" : \"Fail\"}");
			}
		}
	}
}
