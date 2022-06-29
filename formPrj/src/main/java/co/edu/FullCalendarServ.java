package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/FullCalendarServ")
public class FullCalendarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FullCalendarServ() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		EmpDAO dao = new EmpDAO();
		response.setCharacterEncoding("utf-8");
		response.setContentType("test/json;charset=utf-8");  // content type을 한글로
		List<CalendarVO> schedules = dao.getSchedule();
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(schedules));
		
		// [{title, startDate, endDate}, {title, startDate, endDate}] 위를 실행하면 출력 스트림에 이런 json 형태의 배열이 나오도록  
 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 입력정보 한글포함
		request.setCharacterEncoding("utf-8");
		
		// 파라미터정보 : cmd=insert, title=입력한값, start=입력값, end=입력값 파라메터 이름들...
		String cmd, title, start, end;
		
		cmd = request.getParameter("cmd");
		title = request.getParameter("title");
		start = request.getParameter("start");
		end = request.getParameter("end");
		
		EmpDAO dao = new EmpDAO();
		
		if(cmd.equals("insert")) {
			
			CalendarVO vo = new CalendarVO();
			// 사용자 입력값을 vo 셋팅.
			vo.setTitle(title);
			vo.setStartDate(start);
			vo.setEndDate(end);
			
			if(dao.insertSchedule(vo)) {
				response.getWriter().print("{\"retCode\":\"Sucess\"}");
			} else {
				response.getWriter().print("{\"retCode\":\"Fail\"}");
			}
		} else if (cmd.equals("delete")) {
			
			CalendarVO vo = new CalendarVO();
			// 사용자 입력값을 vo 셋팅.
			vo.setTitle(title);
			vo.setStartDate(start);
			vo.setEndDate(end);
			
			if(dao.deleteSchedule(vo)) {
				response.getWriter().print("{\"retCode\":\"Sucess\"}");
			} else {
				response.getWriter().print("{\"retCode\":\"Fail\"}");
			}
		}
				
	}
}
