package co.edu.book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// import co.edu.DAO;

public class BookDAO extends DAO {

	// DB 전체 조회
	public List<BookVO> memberList() {
		getConnect(); // 로그인 db연결

		List<BookVO> list = new ArrayList<BookVO>();

		try {
			psmt = conn.prepareStatement("select * from book");
			rs = psmt.executeQuery(); // 조회 : executeQuery 추가,수정,삭제 : executeUpdate

			while (rs.next()) {
				BookVO vo = new BookVO();

				vo.setBookNo(rs.getInt("book_code"));
				vo.setBookName(rs.getString("book_name"));
				vo.setWriter(rs.getString("book_auth"));
				vo.setPublisher(rs.getString("book_pres"));
				vo.setMoney(rs.getString("book_amt"));

				list.add(vo);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return list;
	}

	// DB 입력
	public BookVO insertMember(BookVO vo) {
		getConnect();
		String sql = "insert into book (book_code, book_name, book_auth, book_pres, book_amt)" + "values(?,?,?,?,?)";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBookNo());
			psmt.setString(2, vo.getBookName());
			psmt.setString(3, vo.getWriter());
			psmt.setString(4, vo.getPublisher());
			psmt.setString(5, vo.getMoney());

			int r = psmt.executeUpdate(); // 구문을 실행하세요 db에 실행된 건수를 타입에 맞춰 반환해줌
			
			System.out.println(r + "건 입력.");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}

		return vo;
	}

	// DB 삭제
	public boolean deleteMember(int bookNo) {
		getConnect();
		String sql = "delete from book where book_code = ?";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNo);

			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}

		return false;
	}

}
