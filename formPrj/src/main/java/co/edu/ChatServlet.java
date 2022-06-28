package co.edu;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getDeptCnt(); // bd 결과값 리턴

		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(map));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
