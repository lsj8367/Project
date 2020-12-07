package kr.del.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.del.order.OrderBean;

public class ProductMgr {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	public ProductMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");//env까지는 같고 jdbc_maria는 context.xml안에 name값
		} catch (Exception e) {
			System.out.println("ProductMgr err : " + e);
		}
	}
	
	public ArrayList<ProductDto> getProductAll(){
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		try {
			conn = ds.getConnection();
			String sql = "select * from my_product order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setNo(rs.getString("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
				list.add(dto);
			}	
		} catch (Exception e) {
			System.out.println("getProductAll err : " + e);
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
	
	public boolean insertProduct(HttpServletRequest request) { //상품 리스트에 추가.
		boolean b = false;
		try {
			// 업로드할 이미지 경로( 절 대 경 로 ) cos.jar이용
			String uploadDir = "C:/Users/dyctd_3y5wr45/web/web_homework/WebContent/food";
			//multipartrequest는 cos.jar로 인한 라이브러리
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			//5*1024*1024 ==> 5MByte
			
			conn = ds.getConnection();
			String sql = "insert into my_product(name, price, detail, sdate, stock, image) values(?,?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name")); //상품명 받기
			pstmt.setString(2, multi.getParameter("price")); //가격 받기
			pstmt.setString(3, multi.getParameter("detail")); //상세정보 받기
			pstmt.setString(4, multi.getParameter("stock")); //재고량 받기
			
			if(multi.getFilesystemName("image") == null) { //상품등록때 사진 선택을 안했을때.
				pstmt.setString(5, "ready.gif"); //upload폴더안에 ready.gif그림 출력하여 보여줌
			}else { //사진 첨부를 했을때.
				pstmt.setString(5, multi.getFilesystemName("image")); //사진불러온것을 출력
			}
			if(pstmt.executeUpdate() > 0) b = true;
			
			
		} catch (Exception e) {
			System.out.println("insertProduct err : " + e);
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
	
	public ProductDto getProduct(String no) { //수정할 상품내역을 가져오는 리스트
		ProductDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from my_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ProductDto();
				dto.setNo(rs.getString("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
			}
			
		} catch (Exception e) {
			System.out.println("getProduct err : " + e);
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
	
	
	
	public boolean updateProduct(HttpServletRequest request) { //상품 수정
		boolean b= false;
		try {
			// 업로드할 이미지 경로( 절 대 경 로 ) cos.jar이용
			String uploadDir = "C:/Users/dyctd_3y5wr45/web/web_myshop1/WebContent/upload";
			//multipartrequest는 cos.jar로 인한 라이브러리
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			//5*1024*1024 ==> 5MByte			
			conn = ds.getConnection();
			
			
			if(multi.getFilesystemName("image") == null) {
				String sql = "update my_product set name=?, price=?, detail=?, stock=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getParameter("no"));
			}else {
				String sql = "update my_product set name=?, price=?, detail=?, stock=?, image=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getFilesystemName("image"));
				pstmt.setString(6, multi.getParameter("no"));
			}
			
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("updateProduct err : " + e);
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
	
	public boolean deleteProduct(String no) { //상품삭제
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "delete from my_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("deleteProduct err : " + e);
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
	
	//고객이 상품 주문 시 주문 수량 만큼 재고에서 빼기
	public void reduceProduct(OrderBean order) {
		try {
			conn = ds.getConnection();
			String sql = "update my_product set stock=(stock - ?) where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getQuantity());
			pstmt.setString(2, order.getProduct_no());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("reduceProduct err : " + e);
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
