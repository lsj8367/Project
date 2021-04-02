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

## [미니프로젝트](https://docs.google.com/presentation/d/1OyKlIPCRzBzq5KCkeT07Q6R82I8qC3NX8GlDImhr_fo/edit?ts=60333cc2#slide=id.p)
