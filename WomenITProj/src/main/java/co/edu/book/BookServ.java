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
		
		// 한글 처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		Gson gson = new Gson();
		BookDAO dao = new BookDAO();
		
		List<BookVO> list = dao.memberList();
		response.getWriter().print(gson.toJson(list));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글 처리 (request 정보에 한글 포함 시)
		request.setCharacterEncoding("utf-8");

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		BookDAO dao = new BookDAO();
		Gson gson = new GsonBuilder().create();
		PrintWriter out = response.getWriter();
		
			String cmd = request.getParameter("cmd");
			String bookNo = request.getParameter("bookNo");
			String bookName = request.getParameter("bookName");
			String writer = request.getParameter("writer");
			String publisher = request.getParameter("publisher");
			String money = request.getParameter("money");
		

		// JsonObject obj = new JsonObject();

		// 입력
		if (cmd.equals("add")) {
			
			BookVO vo = new BookVO();
			vo.setBookNo(Integer.parsInt(bookNo));
			vo.setBookName(bookName);
			vo.setMoney(money);
			vo.setPublisher(publisher);
			vo.setWriter(writer);

			dao.insertMember(vo);
			out.print(gson.toJson(vo));
		
		// 삭제	
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
