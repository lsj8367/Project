# 네책내책
- 중고 서적과 새 서적을 동시에 관리하는 도서관리 프로젝트

팀 프로젝트로 처음에 시작하였으나 혼자 공부하고 배운 내용을 도입하여
적용하며 지속적인 리팩토링을 진행하는 프로젝트

# 📌 기술 스택
- Java 11, **Junit 5**,  **Spring Boot**, **JPA**, MySql, JSP, HTML/CSS, JavaScript

## 현재 진행 사항
- MyBatis -> JPA 마이그레이션
- 각 로직에 대한 Repository 단위 테스트 작성중

## 테이블 완료 현황
- [x] CardInfo
- [x] FaqBoard
- [x] Inquery
- [x] NewBook
- [x] OldBook
- [x] User
- [ ] ObFile
- [ ] OrderInfo
- [ ] Admin
- [ ] RentInfo
- [ ] Review

## 2021-10-15 OrderInfo 리팩토링
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/37)
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/36)
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/35)
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/34)

## 2021-10-07 User테이블 마이그레이션 완료
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/33)

## 2021-10-06 User테이블 리팩토링
- [스테이트 패턴 적용후기](https://lsj8367.github.io/til/TIL-designPattern/)
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/32)

## 2021-09-29 ~ 30 리팩토링
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/30)
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/29)

## 2021-09-27 중고책 마이그레이션
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/27)

## 2021-09-20 새책테이블 마이그레이션
- [변경사항 바로가기](https://github.com/lsj8367/Project/pull/22)

## 2021-09-17 Querydsl 이슈
- [블로그 글 바로가기](https://lsj8367.github.io/til/TIL-querydsl/)

## 2021-08-21 마이그레이션 2
- SpringBoot 의존성 중복 제거
- Bean 중복 제거

마이그레이션에 관한 블로그 포스팅 [Wiki](https://github.com/lsj8367/Project/wiki)

## Lombok 추가
  * DTO, BEAN 파일 getter, setter 삭제
  * @Getter, @Setter, @NoArgsConstructor 추가

* 불필요한 `System.out.println()` 삭제

## 2021-07-28 마이그레이션
- Spring 5.x 버전 SpringBoot 2.x 로 마이그레이션

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
  
- [초창기 포트폴리오](https://github.com/lsj8367/Project/blob/master/%ED%8F%AC%ED%8A%B8%ED%8F%B4%EB%A6%AC%EC%98%A4.pdf)

- 담당 역할
  - 새책과 중고서적의 메인페이지 전반
  - 장르별 조회 구매(대여)하기
  - 구매내역 상세보기
  - 비회원 주문조회
  - 중고서적 구매페이지 결제화면
  - header에 로그인 세션 (include)
  - footer 레이아웃 만들기
  - 구매페이지 결제 후 주문번호 생성기
  - 베스트 도서와 다독왕
  - 새책페이지 리뷰 지우기
