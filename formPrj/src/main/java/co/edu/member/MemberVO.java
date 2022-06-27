package co.edu.member;

public class MemberVO {
	// 필드
	private int membNo;
	private String membName;
	private String membPhone;
	private String membAddr;
	private String membBirth;
	private String membImage;
	
	// getter setter
	public int getMembNo() {
		return membNo;
	}
	public void setMembNo(int membNo) {
		this.membNo = membNo;
	}
	public String getMembName() {
		return membName;
	}
	public void setMembName(String membName) {
		this.membName = membName;
	}
	public String getMembPhone() {
		return membPhone;
	}
	public void setMembPhone(String membPhone) {
		this.membPhone = membPhone;
	}
	public String getMembAddr() {
		return membAddr;
	}
	public void setMembAddr(String membAddr) {
		this.membAddr = membAddr;
	}
	public String getMembBirth() {
		return membBirth;
	}
	public void setMembBirth(String membBirth) {
		this.membBirth = membBirth;
	}
	public String getMembImage() {
		return membImage;
	}
	public void setMembImage(String membImage) {
		this.membImage = membImage;
	}
	
	// toString
	@Override
	public String toString() {
		return "MemberVO [membNo=" + membNo + ", membName=" + membName + ", membPhone=" + membPhone + ", membAddr="
				+ membAddr + ", membBirth=" + membBirth + ", membImage=" + membImage + "]";
	}
	
	
	
}
