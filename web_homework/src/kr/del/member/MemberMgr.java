package kr.del.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public MemberMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");// env까지는 같고 jdbc_maria는 context.xml안에 name값

		} catch (Exception e) {
			System.out.println("BoardMgr err : " + e);
		}
	}

	// 우편번호 검색
	public ArrayList<ZipcodeDto> zipcodeRead(String dongName) {
		ArrayList<ZipcodeDto> list = new ArrayList<ZipcodeDto>();

		String sql = "select * from ziptab where area3 like ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongName + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("zipcode"));
				ZipcodeDto dto = new ZipcodeDto();
				dto.setZipcode(rs.getString("zipcode"));
				dto.setArea1(rs.getString("area1"));
				dto.setArea2(rs.getString("area2"));
				dto.setArea3(rs.getString("area3"));
				dto.setArea4(rs.getString("area4"));
				list.add(dto); // 없는이름은 들어가지않음.
			}
		} catch (Exception e) {
			System.out.println("zipcodeRead err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}

	public boolean checkId(String id) { // 아이디 중복을 판단하는 메소드
		boolean b = false;

		try {
			conn = ds.getConnection();
			String sql = "select id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			b = rs.next(); // 있으면 true 없으면 false
		} catch (Exception e) {
			System.out.println("checkId err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public boolean memberInsert(MemberBean bean) { //회원가입
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPasswd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getJob());
			
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("memberInsert err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b; 
	}
	
	public boolean loginCheck(String id, String passwd) { //로그인 확인 메소드
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select id, passwd from member where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("loginCheck err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public MemberDto getMember(String id) { //회원수정할 회원정보를 받아오는 메소드
		MemberDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setJob(rs.getString("job"));
			}
		} catch (Exception e) {
			System.out.println("getMember err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	public boolean memberUpdate(MemberBean bean, String id) { //회원 수정하는 메소드
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "update member set passwd=?, name=?, email=?, phone=?, zipcode=?, address=?, job=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getPasswd());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getZipcode());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getJob());
			pstmt.setString(8, id);
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("getMember err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	
	public boolean adminLoginCheck(String adminid, String adminpasswd) { //관리자 로그인 메소드
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select * from admin where admin_id=? and admin_passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminid);
			pstmt.setString(2, adminpasswd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("adminLoginCheck err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public ArrayList<MemberDto> getMemberAll() { //관리자로 전체 회원 읽기
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		try {
			conn = ds.getConnection();
			String sql = "select * from member"; //pk 이기 떄문에 이쪽은 안해줘도 무방  order by id asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setJob(rs.getString("job"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("getMemberAll err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
}
