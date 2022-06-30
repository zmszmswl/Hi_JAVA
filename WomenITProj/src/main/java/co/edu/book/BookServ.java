package co.edu.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.edu.book.DAO;
import co.edu.book.BookVO;

@WebServlet("/BookServ")
public class BookServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookServ() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		String cmd = request.getParameter("cmd");
		Gson gson = new GsonBuilder().create();
		BookDAO dao = new BookDAO();

		if (cmd.equals("list")) { // 조회 http://localhost/formPrj/member?cmd=list
			// 전체 리스트 => json 화면 보여주기.
			List<BookVO> list = dao.memberList();// 전체 건수를 리턴해주는 메소드의 주소값을 list 변수값으로 받아옴
			response.getWriter().print(gson.toJson(list));

		} else if (cmd.equals("insert")) { // 추가
			String bookName = request.getParameter("bookName");

			BookVO vo = new BookVO();
			vo.setBookName(bookName);

			dao.insertMember(vo);

			response.getWriter().print(gson.toJson(vo));
			
		} else if (cmd.equals("delete")) { // 삭제
			String delNo = request.getParameter("delNumber");
			if (dao.deleteMember(Integer.parseInt(delNo))) {
				response.getWriter().print("{\"retCode\" : \"Success\"}");
			} else {
				// {"retCode" : "Fail"}
				response.getWriter().print("{\"retCode\" : \"Fail\"}"); // 컨텍스트를 다시 로드하는 것을 완료했습니다.
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		String cmd = request.getParameter("cmd");

		String bookNo = request.getParameter("bookNo");
		String bookName = request.getParameter("bookName");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		String money = request.getParameter("money");

		BookVO vo = new BookVO();
		vo.setBookName(bookName);
		vo.setMoney(money);
		vo.setPublisher(publisher);
		vo.setWriter(writer);

		BookDAO dao = new BookDAO();

		Gson gson = new GsonBuilder().create();
		PrintWriter out = response.getWriter();
		JsonObject obj = new JsonObject();

		// 1. 입력
		if (cmd.equals("add")) {
			dao.insertMember(vo);
			out.print(gson.toJson(vo));

		} else if (cmd.equals("remove")) {

			String delNo = request.getParameter("delNo");
			
			if (dao.deleteMember(Integer.parseInt(delNo))) {
				out.print("{\"retCode\" : \"Success\"}");
			} else {
				out.print("\"{\"retCode\" : \"Fail\"}");
			}
		}
	}
}
