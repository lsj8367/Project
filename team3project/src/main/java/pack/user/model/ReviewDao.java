package pack.user.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.review.model.ReviewDto;
@Repository
public class ReviewDao extends SqlSessionDaoSupport {
	
	public ReviewDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	//리뷰 목록
	public List<ReviewDto> selectNewbookReviewList(int nb_no) {
		return getSqlSession().selectList("selectNewbookReviewList", nb_no);
	}
	//리뷰작성
	public boolean insertNewbookReview(ReviewDto reviewDto) {
		try {
			getSqlSession().insert("insertNewbookReview", reviewDto);
			return true;
		}catch (Exception e) {
			System.out.println("insertNewbookReview" + e);
			return false;
		}
	}
	
	public ReviewDto selectNewbookReview(int review_no) {
		return getSqlSession().selectOne("selectNewbookReview", review_no);
	}
	//리뷰 추천
	public boolean plusGonggam(int review_no) {
		try {
			getSqlSession().update("plusGonggam", review_no);
			return true;
		}catch (Exception e) {
			System.out.println("plusGonggam" + e);
			return false;
		}
	}

	public boolean deleteReview(int review_no) {
		try {
			getSqlSession().delete("deleteReview", review_no);
			return true;
		}catch (Exception e) {
			System.out.println("plusGonggam" + e);
			return false;
		}
	}
}
