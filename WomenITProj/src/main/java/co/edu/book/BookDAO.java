package co.edu.book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



// import co.edu.DAO;

public class BookDAO extends DAO {

	// 전체조회.()
	public List<BookVO> memberList() {
		getConnect(); // 로그인 db연결
		List<BookVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from book" );
			rs = psmt.executeQuery(); // 조회 : executeQuery 추가,수정,삭제 : executeUpdate
			while (rs.next()) {
				BookVO mem = new BookVO();

				mem.setBookNo(rs.getInt("book_code"));
				mem.setBookName(rs.getString("book_name"));
				mem.setWriter(rs.getString("book_auth"));
				mem.setPublisher(rs.getString("book_pres"));
				mem.setMoney(rs.getString("book_amt"));

				list.add(mem);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 조회 완료 후 꼭 해야하는 작업 로그아웃!
			disconnect();
		}
		return list;
	}
	
	// 입력.(MemberVO)
		public BookVO insertMember(BookVO vo) {
			getConnect(); // db연결
			String sql = "insert into book (book_code, book_name, book_auth, book_pres, book_amt)"
					+"values(?,?,?,?,?)"; // 파라메터
			try {
				// 시퀀스. 쿼리 실행
				// 입력 처리.
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
		// 삭제.(memb_no) 1건씩
		public boolean deleteMember(int bookco) {
			getConnect();
			String sql = "delete from book where book_code = ?";

			try {
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, bookco);

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

 