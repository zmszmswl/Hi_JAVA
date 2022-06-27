package co.edu.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// import co.edu.DAO;

public class MemberDAO extends co.edu.DAO {

	// 전체조회.()
	public List<MemberVO> memberList() {
		getConnect(); // 로그인 db연결
		List<MemberVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from member order by memb_no");
			rs = psmt.executeQuery(); // 조회 : executeQuery 추가,수정,삭제 : executeUpdate
			while (rs.next()) {
				MemberVO mem = new MemberVO();

				mem.setMembNo(rs.getInt("memb_no"));
				mem.setMembName(rs.getString("memb_name"));
				mem.setMembPhone(rs.getString("memb_phone"));
				mem.setMembAddr(rs.getString("memb_addr"));
				mem.setMembBirth(rs.getString("memb_birth"));
				mem.setMembImage(rs.getString("memb_image"));

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
	public MemberVO insertMember(MemberVO vo) {
		getConnect(); // db연결
		String sql = "insert into member (memb_no, memb_name, memb_phone ,memb_addr, memb_birth, memb_image)"
				+"values(?,?,?,?,?,?)"; // 파라메터
		String seqSql = "select memb_seq.nextval from dual";
		try {
			// 시퀀스. 쿼리 실행
			int nextSeq = 0;
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				nextSeq = rs.getInt(1); // 회원번호.int 타입으로 가져오겠다
			}

			// 입력 처리.
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextSeq);
			psmt.setString(2, vo.getMembName());
			psmt.setString(3, vo.getMembPhone());
			psmt.setString(4, vo.getMembAddr());
			psmt.setString(5, vo.getMembBirth());
			psmt.setString(6, vo.getMembImage());

			vo.setMembNo(nextSeq);
			int r = psmt.executeUpdate(); // 구문을 실행하세요 db에 실행된 건수를 타입에 맞춰 반환해줌
			System.out.println(r + "건 입력.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	// 수정.(MemberVO)
	public boolean updateMember(MemberVO vo) {
		getConnect();
		String sql = "update member " + "set memb_name = ?," + "    memb_addr = ?," + "    memb_phone = ?,"
				+ "    memb_birth = ?," + "    memb_image = ? " + "where memb_no = ?"; // sql set 조건문 조건 : "where memb_no = ?"
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMembName());
			psmt.setString(2, vo.getMembAddr());
			psmt.setString(3, vo.getMembPhone());
			psmt.setString(4, vo.getMembBirth());
			psmt.setString(5, vo.getMembImage());
			psmt.setInt(6, vo.getMembNo());

			int r = psmt.executeUpdate(); // 수정된 건수를 반환.
			
			if (r > 0) {
				return true; // 정상적으로 변경.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false; // 실패
	}

	// 삭제.(memb_no) 1건씩
	public boolean deleteMember(int membNo) {
		getConnect();
		String sql = "delete from member where memb_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, membNo);

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

	// 조건조회.(memb_no)
	public MemberVO searchMember(int membNo) {
		getConnect();
		MemberVO mem = null; // 클래스 인스턴스 만들어서 초기값을 null줌 참조변수		
		String sql = "select * from member where memb_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, membNo);
			rs = psmt.executeQuery(); 
	
			if (rs.next()) {
				mem = new MemberVO(); // mem인스턴스에 힙영역 객체의 주소값을 담아줌
				mem.setMembNo(rs.getInt("memb_no"));
				mem.setMembName(rs.getString("memb_name"));
				mem.setMembPhone(rs.getString("memb_phone"));
				mem.setMembAddr(rs.getString("mem_addr"));
				mem.setMembBirth(rs.getString("memb_birth"));
				mem.setMembImage(rs.getString("memb_image"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return mem;
	}
}
