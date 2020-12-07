package kr.del.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	public OrderMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");//env까지는 같고 jdbc_maria는 context.xml안에 name값
		} catch (Exception e) {
			System.out.println("OrderMgr err : " + e);
		}
	}
	
	public void insertOrder(OrderBean order) { //카트리스트의 주문내역 저장 메소드
		try {
			conn = ds.getConnection();
			String sql = "insert into my_order(product_no,quantity,sdate,state,id) values(?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getProduct_no());
			pstmt.setString(2, order.getQuantity());
			pstmt.setString(3, "1");
			pstmt.setString(4, order.getId());
			int re = pstmt.executeUpdate();
			System.out.println(re);
		} catch (Exception e) {
			System.out.println("insertOrder err : " + e);
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
	
	public ArrayList<OrderDto> getOrder(String id){ //주문하기
		ArrayList<OrderDto> list = new ArrayList<OrderDto>();
		try {
			conn = ds.getConnection();
			String sql = "select * from my_order where id=? order by no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setNo(rs.getString("no"));
				dto.setProduct_no(rs.getString("product_no"));
				dto.setQuantity(rs.getString("quantity"));
				dto.setSdate(rs.getString("sdate"));
				dto.setState(rs.getString("state"));
				dto.setId(rs.getString("id"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getOrder err : " + e);
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
	
	public ArrayList<OrderDto> getOrderAll(){ //주문정보
		ArrayList<OrderDto> list = new ArrayList<OrderDto>();
		
		
		try {
			conn = ds.getConnection();
			String sql = "select * from my_order order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setNo(rs.getString("no"));
				dto.setProduct_no(rs.getString("product_no"));
				dto.setQuantity(rs.getString("quantity"));
				dto.setSdate(rs.getString("sdate"));
				dto.setState(rs.getString("state"));
				dto.setId(rs.getString("id"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("getOrderAll err : " + e);
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
	
	public OrderDto getOrderDetail(String no) { //주문상세정보
		OrderDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from my_order where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new OrderDto();
				dto.setNo(rs.getString("no"));
				dto.setProduct_no(rs.getString("product_no"));
				dto.setQuantity(rs.getString("quantity"));
				dto.setSdate(rs.getString("sdate"));
				dto.setState(rs.getString("state"));
				dto.setId(rs.getString("id"));
			}
			
		} catch (Exception e) {
			System.out.println("getOrderDetail err : " + e);
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
	
	public boolean updateOrder(String no, String state) { //주문수정하기 배송,입금확인 등등...
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "update my_order set state=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setString(2, no);
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("updateOrder err : " + e);
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
	
	public boolean deleteOrder(String no) { //상태정보 삭제
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "delete from my_order where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if(pstmt.executeUpdate() > 0) b = true;
			
			//상품의 재고 수를 원상복구 시키는 작업 필요 한데 생략.
		} catch (Exception e) {
			System.out.println("deleteOrder err : " + e);
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
	
}
