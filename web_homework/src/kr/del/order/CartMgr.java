package kr.del.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable hCart = new Hashtable();
	
	public void addCart(OrderBean obean) { //장바구니에 추가, 해당세션만 볼수있음. 
		String product_no = obean.getProduct_no();
		int quantity = Integer.parseInt(obean.getQuantity());
		//System.out.println(product_no + " 주문 수량 : " + quantity + " id : " + obean.getId());
		
		if(quantity > 0) {
			//동일 상품 주문인 경우에는 주문 수만 증가
			if(hCart.containsKey(product_no)) {
				OrderBean temp = (OrderBean)hCart.get(product_no); //기존 카트에있는 주문수
				quantity += Integer.parseInt(temp.getQuantity()); //주문수 = 기존주문수 + 새로추가한 주문수
				temp.setQuantity(Integer.toString(quantity));
				hCart.put(product_no, temp); //key, value 담기
				//System.out.println("동일 상품인 경우의 주문수 : " + quantity);
			}else { //여기는 기존 카트에 없는부분이라 고객이 새상품
				hCart.put(product_no, obean); //key, value 담기
			}
		}
	}
	
	public Hashtable getCartList() { //목록보기
		return hCart;
	}
	
	public void updateCart(OrderBean obean) { //장바구니 수정하기
		String product_no = obean.getProduct_no();
		hCart.put(product_no, obean); //덮어쓰기해서 최신화
		
	}
	
	public void deleteCart(OrderBean obean) { //장바구니 삭제하기 (orderproc) dto클래스
		String product_no = obean.getProduct_no();
		hCart.remove(product_no); //요소 제거
	}
	
}
