package kr.del.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	private int tot; // 전체 레코드 수
	private int pList = 5; // 한페이지당 5개씩 출력하는 행수
	private int pageSu; // 전체 페이지 수

	public ReplyMgr() { //드라이버 불러오기
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");// env까지는 같고 jdbc_maria는 context.xml안에 name값
		} catch (Exception e) {
			System.out.println("ReplyMgr err : " + e);
		}
	}
	
	public ArrayList<ReplyDto> getDataAll(int page, String stype, String sword){
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		String sql = "select * from repleboard";
		try {
			conn = ds.getConnection();
			if(sword == null) { //검색창에 내용 없을때
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
			}else { //검색내용이 있다면 이쪽을 출력함.
				sql += " where " + stype + " like ?";
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%"); //같은 글자 한개라도 있으면 다 출력해줌
			}
			rs = pstmt.executeQuery();
			
			for (int i = 0; i < (page - 1) * pList; i++) {
				rs.next(); //레코드 포인터 위치 확인
			} //1페이지의 0부터 시작해서 끊어주는만큼 끊음 예) 10으로 설정했으면 10까지 보여주고 2페이지에서는 11부터 보여줌
			
			int a = 0;
			while(rs.next() && a < pList) {
				ReplyDto dto = new ReplyDto();
				dto.setNum(rs.getInt("num"));			//글번호
				dto.setName(rs.getString("name"));		//작성자
				dto.setTitle(rs.getString("title"));		//작성자
				dto.setBdate(rs.getString("bdate"));		//비밀번호
				dto.setReadcnt(rs.getInt("readcnt"));		//이메일
				dto.setNested(rs.getInt("nested"));	//글제목
				dto.setStar(rs.getString("star"));
				list.add(dto);
				a++;
			}
			
			
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
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
	
	public int currentGetNum() {
		String sql = "select max(num) from repleboard";
		int count = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("currentGetNum err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;
	}
	
	public void saveData(ReplyBean bean) {
		
		System.out.println(bean.getStar());
		
		try {
			conn = ds.getConnection();
			String sql = "insert into repleboard values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0);
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			pstmt.setString(13, bean.getStar());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveData err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		
	}
	
	
	
	public void totalList() {
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from repleboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			tot = rs.getInt(1);
			
			//System.out.println("전체 건수 : " + tot);
		} catch (Exception e) {
			System.out.println("totalList err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	public int getPageSu() {
		pageSu = tot / pList;
		if(tot / pList > 0) pageSu++;
		return pageSu;
	}
	
	public void updateReadcnt(String num) {
		try {
			conn = ds.getConnection();
			String sql = "update repleboard set readcnt=readcnt + 1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("updateReadcnt err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public ReplyDto getData(String num) {
		ReplyDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from repleboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ReplyDto();
				dto.setNum(Integer.parseInt(num));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setStar(rs.getString("star"));
			}
		} catch (Exception e) {
			System.out.println("getData err : " + e);
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
	
	public ReplyDto getReplyData(String num) {
		ReplyDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from repleboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new ReplyDto();
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
		} catch (Exception e) {
			System.out.println("getReplyData err : " + e);
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
	
	public void updateOnum(int gnum, int onum) {
		try {
			conn = ds.getConnection();
			String sql = "update shopboard set onum=onum+1 where onum >=? and gnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("updateOnum err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void saveReplyData(ReplyBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "insert into repleboard values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum()); //레코드번호
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); //readcnt
			pstmt.setInt(10, bean.getGnum()); //gnum
			pstmt.setInt(11, bean.getOnum()); //onum //여기부분만 saveData랑 다름
			pstmt.setInt(12, bean.getNested()); //nested //이 두개는 값을 받아와야함
			pstmt.setString(13, bean.getStar());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveReplyData err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public boolean checkPass(int num, String new_pass) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select pass from repleboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(new_pass.equals(rs.getString("pass"))) {
					b = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("checkPass err : " + e);
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
	
	public void saveEdit(ReplyBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "update repleboard set name=?,mail=?,title=?,cont=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());
			pstmt.setInt(5, bean.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveEdit err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void delData(String num) {
		try {
			conn = ds.getConnection();
			String sql = "delete from repleboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delData err : " + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
}
