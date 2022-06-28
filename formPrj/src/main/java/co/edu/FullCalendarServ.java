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
		List<CalendarVO> schedules = dao.getSchedule();
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(schedules));
		
		// [{title, startDate, endDate}, {title, startDate, endDate}] 위를 실행하면 출력 스트림에 이런 json 형태의 배열이 나오도록  
 	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
	}

}
