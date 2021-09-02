# Project
## Java-Project ↓↓↓
### 팀프로젝트(네책내책)
## [포트폴리오](https://github.com/lsj8367/Project/blob/master/%ED%8F%AC%ED%8A%B8%ED%8F%B4%EB%A6%AC%EC%98%A4.pdf)
* 담당 역할
  * 새책과 중고서적의 메인페이지 전반
  * 장르별 조회 구매(대여)하기
  * 구매내역 상세보기
  * 비회원 주문조회
  * 중고서적 구매페이지 결제화면
  * header에 로그인 세션 (include)
  * footer 레이아웃 만들기
  * 구매페이지 결제 후 주문번호 생성기
  * 베스트 도서와 다독왕
  * 새책페이지 리뷰 지우기

## 2021-04-02 수정
* ## ListController, RentBookListController 리팩토링
  * Controller 2개로 구현한 로직 수정

  * ListController
    * 같은내용 중복코드 삭제
      * if문 중첩 장르선택 -> Map으로 메소드 추가
      * List생성하는 메소드 추가
      * ModelAndView 담당 메소드 추가

  * RentBookListController의 경우
    * 같은내용 중복코드 삭제
      * if문 중첩 장르선택 -> Map으로 메소드 추가
      * List생성하는 메소드 추가
      * ModelAndView 담당 메소드 추가(다독왕, 베스트 대여도서)

* ## Lombok 추가 
  * DTO, BEAN 파일 getter, setter 삭제
  * @Getter, @Setter, @NoArgsConstructor 추가
  
* 불필요한 `System.out.println()` 삭제

## 2021-07-28 마이그레이션
- Spring 5.x 버전 SpringBoot 2.x 로 마이그레이션

## 2021-08-21 마이그레이션 2
- SpringBoot 의존성 중복 제거
- Bean 중복 제거

마이그레이션에 관한 블로그 포스팅 [Wiki](https://github.com/lsj8367/Project/wiki)
