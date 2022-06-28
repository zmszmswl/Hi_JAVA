package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO extends DAO {

	// 일정정보
	public List<CalendarVO> getSchedule() {
		// 전체 일정 정보를 가지고 오도록 메소드를 완성하세요
		List<CalendarVO> calen = new ArrayList<>();
		getConnect();
		String sql = "select * from full_calendar";
		try {
			psmt = conn.prepareStatement(sql); // 쿼리실행
			rs = psmt.executeQuery(); // 쿼리실행한걸 rs에 씀
			while (rs.next()) {
				CalendarVO cal = new CalendarVO();
				cal.setTitle(rs.getString("title")); // 1 
				cal.setStartDate(rs.getString("start_date")); // 2
				cal.setEndDate(rs.getString("end_date")); // 3
				calen.add(cal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return calen;
	}

	// 부서정보, 인원정보
	public Map<String, Integer> getDeptCnt() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		getConnect();
		String sql = "select department_name, count(1)\r\n" + "from employees e , departments d\r\n"
				+ "where e.department_id = d.department_id\r\n" + "group by department_name";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return map;
	}

	// class => 복합적인 데이터를 하나의 변수에 넣고 싶어(자바)
	// 사원번호, 이름, 이메일, 직무 => class 작성 : 필드로 선언.
//	public void insertEmp(int eId, String name, String email, String job) {
	public void insertEmp(Employee emp) {
		getConnect();
		String sql = "insert into employees(employee_id, last_name, job_id, hire_date) vlaues(employees_seq.nextval,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName()); // 물음표 순서 중요함 .4개라 ?가 4개
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getJobId());
			psmt.setString(4, emp.getHireDate());
			int r = psmt.executeUpdate(); // 실제 쿼리를 진행해줌.
			System.out.println(r + "건 입력.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public List<String> getNames() {
		getConnect();
		String sql = "select first_name from employees";
		List<String> list = new ArrayList<>(); // 인터페이스
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("first_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	} // end of getName()

	public List<Employee> empList() {
		getConnect();
		String sql = "select * from employees";
		List<Employee> list = new ArrayList<>(); // 인터페이스
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmail(rs.getString("email"));
				emp.setEmpId(rs.getInt("employee_id"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setLastName(rs.getString("last_name"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	} // end of List<Employee>
}
