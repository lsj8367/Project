CREATE TABLE newbook (
   nb_no int(10) PRIMARY KEY AUTO_INCREMENT,
   nb_name VARCHAR(30) NOT NULL,
   nb_author VARCHAR(50) DEFAULT '저자 미상',
   nb_inter VARCHAR(20),
   nb_genre VARCHAR(20) NOT NULL,
   nb_comp VARCHAR(20) DEFAULT '출판사 불명',
   nb_bdate DATEtime,
   nb_stock INT(10) DEFAULT 0,
   nb_price INT(10) DEFAULT 0,
   nb_scount INT(10) DEFAULT 0,
   nb_readcnt INT(10) DEFAULT 0,
   nb_plot text NOT NULL,
   nb_image VARCHAR(100) DEFAULT 'resources/images/notready.jpg'
)CHARSET=UTF8;

create table oldbook(
ob_no int(10) primary key auto_increment, 
ob_name varchar(30) not null, 
ob_author varchar(50) not NULL,
ob_inter VARCHAR(50), 
ob_genre varchar(20) not null, 
ob_comp varchar(20) not null, 
ob_bdate DATETIME,
ob_state varchar(20) default 0, 
ob_price int(10) not null, 
ob_scount int(10) default 0, 
ob_readcnt int(10) default 0, 
ob_donor varchar(20) DEFAULT "익명",
ob_comment varchar(30) default "추천합니다.", 
ob_image varchar(100) DEFAULT 'resources/images/notready.jpg',
ob_ddate DATETIME, 
ob_userid VARCHAR(20) 
)charset=UTF8;

CREATE TABLE USER(
user_id VARCHAR(20) PRIMARY KEY NOT NULL, 
user_name VARCHAR(20) NOT NULL,
user_passwd VARCHAR(30) NOT NULL,
user_tel VARCHAR(20) NOT NULL, 
user_addr VARCHAR(100) NOT NULL,
user_zip VARCHAR(10) NOT NULL,
user_mail VARCHAR(30) NOT NULL,
user_rentcnt INT(10) DEFAULT 0,
user_point INT(20) DEFAULT 2000,
user_birth VARCHAR(20) NOT NULL,
user_penalty INT(3) DEFAULT 0,
user_dcount INT(5) DEFAULT 0
)CHARSET = UTF8;


create table admin(
admin_no  int(10) primary KEY auto_increment, 
admin_id varchar(20) not NULL, 
admin_passwd varchar(20) not NULL, 
admin_name varchar(20) not NULL, 
admin_jik varchar(20),
admin_acc INT(2) DEFAULT 0
)charset=UTF8;

create table review(
review_no int(10) auto_increment primary KEY, 
review_id varchar(20), 
review_bookno int(10), 
review_context TEXT, 
review_date DATETIME,
review_rate INT(3),
review_gonggam INT(5), 
foreign key(review_id) references user(user_id), 
foreign key(review_bookno) references newbook(nb_no))CHARSET=UTF8;

create table cardinfo(
card_ownerid varchar(20), 
card_owner varchar(20), 
card_comp varchar(20), 
card_no varchar(20), 
card_passwd varchar(10), 
foreign key(card_ownerid) references user(user_id))CHARSET=UTF8;


create table rentinfo(
rent_no int(10),
rent_id varchar(20) not NULL, 
rent_sdate datetime not NULL, 
rent_edate datetime not NULL,
rent_ecount INT(2) DEFAULT 0 
)charset=UTF8;

CREATE TABLE INQUERY(
inq_no int(10) auto_increment primary key, 
inq_title VARCHAR(50) not null, 
inq_context text,
inq_ddate DATETIME,
inq_id varchar(20), 
inq_gnum INT(10),
inq_onum INT(10),
inq_nested INT(10),
foreign key(inq_id) references user(user_id))charset=UTF8;

CREATE TABLE FAQBOARD (
FAQ_NO INT(10) PRIMARY KEY AUTO_INCREMENT, 
FAQ_TITLE VARCHAR(50) NOT NULL,
FAQ_CONTENT TEXT NOT NULL,
FAQ_DATE DATETIME NOT NULL, 
FAQ_TYPE VARCHAR(10) NOT NULL)CHARSET=UTF8;

CREATE TABLE orderinfo (
order_no int(10) PRIMARY KEY auto_increment,
orderlist_no varchar(20) NOT NULL, 
order_person varchar(20) NOT NULL, 
order_id varchar(20) null,
order_bookno int(10) not null,
order_booktype varchar(5) not null,  
order_date datetime not NULL, 
order_passwd varchar(20) null, 
order_scount int(10) not null,
order_paytype varchar(5) not null,
order_state varchar(10) not NULL,
order_sum int(10) not null,
order_address VARCHAR(100) not null
)charset=UTF8;


CREATE TABLE ob_file
(   idx INT AUTO_INCREMENT PRIMARY key,                       
    ob_no int NOT NULL,                    
    original_file_name VARCHAR(260) NOT NULL,  
    stored_file_name VARCHAR(36) NOT NULL, 
    file_size int,                     
    ob_rdate DATE DEFAULT SYSDATE() NOT NULL, 
    del_gb VARCHAR(1) DEFAULT 'N' NOT NULL
)CHARSET=utf8;

# 테이블 축약 : newbook oldbook user admin review cardinfo rentinfo faqboard orderinfo //  inquery

# ----------newbook 수정 : 분야는 알파벳 값?

insert into newbook values(default,"당신이 옳다","정혜신","","인문학","해냄","2018-03-26",36,15800,35,54,"안정적인 일상을 위해 스스로 마음의 문제를 해결할 수 있도록 도와주는 공감 행동지침서!","http://image.aladin.co.kr/product/16938/73/cover/8965746663_1.jpg");
insert into newbook values(default,"나미야 잡화점의 기적","히가시노 게이고","양윤옥","소설","현대문학","2012-03-26",48,14800,48,64,"따뜻한 고민 상담실 ‘나미야 잡화점’으로 오세요!","http://image.aladin.co.kr/product/15848/6/cover/k622533431_1.jpg");
insert into newbook values(default,"여행의 이유","김영하","","에세이","문학동네","2019-03-26",17,13500,46,52,"여행이 내 인생이었고, 인생이 곧 여행이었다!","https://image.aladin.co.kr/product/18827/60/cover/8954655971_2.jpg");
insert into newbook values(default,"82년생 김지영","조남주","","소설","민음사","2016-03-26",29,13000,38,67,"공포, 피로, 당황, 놀람, 혼란, 좌절의 연속에 대한 한국 여자의 인생 현장 보고서!","https://image.aladin.co.kr/product/18827/60/cover/8954655971_2.jpg");
insert into newbook values(default,"아몬드","손원평","","소설","창비","2017-03-26",47,12000,22,40,"괴물인 내가 또 다른 괴물을 만났다!","http://image.aladin.co.kr/product/16839/4/cover/k492534773_1.jpg");
insert into newbook values(default,"시간을 파는 상점","김선영","","소설","자음과모음","2012-03-26",27,11000,23,53,"시간의 흐름 속에 숨겨진 마법 같은 비밀!","https://bookthumb-phinf.pstatic.net/cover/068/723/06872343.jpg?type=m1&udate=20170906");
insert into newbook values(default,"아홉 살 마음 사전","박성우","","어린이","창비","2017-03-26",13,11000,43,55,"알듯 말듯 너의 감정을 알려줄게!","http://image.aladin.co.kr/product/10537/49/cover/8936447017_1.jpg");
insert into newbook values(default,"설민석의 조선왕조실록","설민석","","역사","세계사","2016-03-26",49,19800,53,77,"설민석표 강연으로 풀어낸 역사 콘서트","http://image.aladin.co.kr/product/8749/45/cover/8933870695_3.jpg");
insert into newbook values(default,"열두 발자국","정재승","","인문학","어크로스","2018-03-26",12,16800,36,41,"《과학콘서트》 《알쓸신잡》 대한민국을 매혹시킨 KAIST 정재승 교수의 최고의 강연이 펼쳐진다","https://image.aladin.co.kr/product/15072/28/cover/k072533160_1.jpg");

insert into newbook values(default,"인생을 바꾸는 90초","조앤 I. 로젠버그","박선령","성공학","한국경제신문","2020-09-23",39,18000,49,31,"매 순간의 경험을 최대한 많이 의식하고 접촉하겠다는 선택을 하고, 여덟 가지 불쾌한 감정 중 한 가지 이상을 90초 동안 느끼면서 이를 극복할 수 있다면 살면서 자신이 원하는 건 뭐든지 추구할 수 있다.","https://image.aladin.co.kr/product/25218/87/cover500/8947546348_1.jpg");
insert into newbook values(default,"한계전의 명시 읽기","한계전","","시","문학동네","2002-10-05",13,15800,58,52,"40년 이상 대학강단에서 시를 가르쳐온 한계전 교수가 한국의 명시들을 두루두루 훑은 책. 1920년대부터 2000년대까지 한국의 대표 시인 53명의 시 104편을 선정하고 해설을 붙였다. 전체 5부로 구성되어 있다.","https://image.aladin.co.kr/product/38/17/cover500/8982815759_2.jpg");
insert into newbook values(default,"깨끗한 존경","이슬아","","에세이","헤엄","2019-11-13",23,15000,45,31,"이슬아의 첫 번째 인터뷰집. 정혜윤, 김한민, 유진목, 김원영과의 긴 대화가 담겨 있다. 네 사람의 이야기를 보고 들은 뒤 감탄과 절망을 오가며 새로운 자신을 향해 나아간다.","https://image.aladin.co.kr/product/21745/30/cover500/k212636907_1.jpg");
insert into newbook values(default,"나의 집","다비드 칼리","바람숲아이","어린이","봄개울","2020-10-15",33,14000,59,79,"그림책봄 13권. 한 주인공이 나고 자란 고향 마을을 떠나 여러 집을 전전하며 살아가는 인생의 과정을 그리고 있다. 그렇기 때문에 한 인물의 어릴 시절 모습부터 청소년기, 청년기, 장년기, 중년기, 노년기까지 일대기가 담겼다.","https://image.aladin.co.kr/product/25208/26/cover500/k562633708_1.jpg");
insert into newbook values(default,"털털한 아롱이","문명예","","유아","책읽는곰","2020-10-15",46,13000,27,59,"볼로냐 국제아동도서전 올해의 일러스트레이터, 상상력의 돋보기로 세상을 들여다보는 문명예 작가의 유쾌한 그림책.","https://image.aladin.co.kr/product/25325/44/cover500/k092633502_1.jpg");
insert into newbook values(default,"부의 원칙","래리 하이트","노태북","경제경영","한빛비즈","2020-10-05",47,18000,20,67,"《부의 원칙》 저자 래리 하이트는 35년 이상의 투자 경험을 지닌 전설적인 트레이더이자, 민트인베스트먼트를 설립해 최초의 원금 보장 펀드를 선보인 헤지펀드의 대부로 불리는 인물이다. 그는 성공한 투자자로서 업계에서 널리 명성을 쌓았고 잭 슈웨거의 베스트셀러 《시장의 마법사들》에서도 시스템 트레이딩의 선구자로 소개되었다.","https://image.aladin.co.kr/product/25232/34/cover500/k672633903_1.jpg");
insert into newbook values(default,"철학자의 거짓말","프랑수아 누델만","문경자","인문학","낮은산","2020-10-12",26,19000,52,76,"사려 깊은 아버지로 스스로를 소개하며 위대한 교육론을 쓴 루소는 자신의 다섯 아이를 버렸다. 푸코가 진실을 말할 용기를 주장했을 때, 그는 그의 목숨을 앗아갈 에이즈(후천성면역결핍증)를 숨기고 있었다. 보부아르가 《제 2의 성》을 써서 페미니즘의 기초를 마련했던 바로 그때, 그녀는 미국의 한 작가와 사랑을 나누며 순종적 여성의 역할을 자처했다.","https://image.aladin.co.kr/product/24940/91/cover500/k202633302_2.jpg");
insert into newbook values(default,"10일 멘토스 생각영어","E&C","","외국어","멘토스퍼블리싱","2020-08-27",39,22000,27,78,"10개의 섹션으로 나누어 자기 생각을 말할 때 필요한 패턴이나 표현들을 수록하였다. 먼저 말꺼내기, 의사소통용 표현, 자기생각말하기, 찬성과 반대 등으로 나누어 표현들을 정리하였으며 맨 마지막에는 토픽 37개를 선정하여 이에 대해 서로 의견을 주고 받는 실제 대화를 수록하였기에 10일간 학 습한 표현들이 실제로 어떻게 쓰이는지 확인할 수 있도록 구성되어 있다.","https://image.aladin.co.kr/product/25010/85/cover500/k962632723_1.jpg");
insert into newbook values(default,"폭력의 진부함","이라영","","사회과학","갈무리","2020-08-28",32,18000,52,72,"성폭력뿐 아니라 사회의 많은 차별과 폭력은 특별한 사람들에 의해 벌어지는 특별한 사건이 아니라 평범한 사람들에 의해 벌어지는 일상적 현상이다. 이처럼 문화화된 폭력은 폭력을 폭력처럼 보이지 않게 만든다. 제도 바깥에서 일어나는 폭로는 이 문화화된 폭력을 보이게 만들려는 피해자 개개인의 분투이며 최후의 구조요청이다. 이 책은 그렇기에 사회구조에 맞서는 개인의 폭로가 발생하게 된 배경과 그러한 발화가 가지는 맥락을 강조하는 작업이다.","https://image.aladin.co.kr/product/24790/59/cover500/8961952455_1.jpg");
insert into newbook values(default,"NCS 한국전력공사 모의고사","PMG적성검사연구소","","수험서","박문각","2020-10-05",43,12000,23,38,"한국전력공사 직무능력검사에 대비할 수 있도록 모의고사 4회분과 OMR 카드를 함께 봉투형으로 제작하였다. 2020 하반기 필기시험을 대비하여 최신 출제경향에 맞는 문제들을 풀어볼 수 있도록 하였다. 또한 상세한 해설을 함께 실어 혼자서도 이제까지 학습한 내용을 최종적으로 체크하고 부족한 부분은 다시 확인하여 시험에 대한 완벽한 대비를 할 수 있도록 구성하였다.","https://image.aladin.co.kr/product/25228/8/cover500/k152633901_1.jpg");
insert into newbook values(default,"최신 범죄심리학","이수정","","대학교재","학지사","2018-03-20",34,25000,53,49,"연세 대학교 심리학과에서 학사, 석사, 박사를 마치고 미국 아이오와 대학교에서 심리 측정 석사, 박사 과정을 수료했다. 현재 경기 대학교 일반 대학원 범죄 심리학과 교수로 재직 중이다.","https://image.aladin.co.kr/product/14503/68/cover500/8999715167_1.jpg");
insert into newbook values(default,"유튜브 마케팅","임현재","","모바일","아이생각","2018-09-15",42,12000,21,64,"국내외 500여 기업, 1,000개 이상의 유튜브 광고 캠페인을 진행한 저자가 이론적인 지식보다는 필드에서 경험한 다양한 실제 집행 사례를 담았다. 책에는 국내외 주요 기업들이 어떤 영상 콘텐츠로 어떻게 동영상 광고를 하고 있는지 소개되어 있다. 또 유튜브 검색결과, 추천영상, 조회수 등 주요 알고리즘을 설명했다.","https://image.aladin.co.kr/product/16648/18/cover500/8960882356_1.jpg");

# (각 분야 준베스트셀러(10만 ~ 20만 판매부수), 조회수 2위(100~200 조회수), 재고량 2위 (100~200개))
insert into newbook values(default,"페스트","알베르 카뮈","김화영","고전","민음사","2011-03-25",153,13000,168219,189,"위험이 도사리는 폐쇄된 도시에서 극한의 절망과 마주하는 인간 군상을 묘사한 작품이다.","https://image.aladin.co.kr/product/1126/73/cover500/s8937462672_1.jpg");
insert into newbook values(default,"킹 세종 더 그레이트","조 메노스키","정윤희 외 2인","소설","핏북","2020-10-09",192,14000,199961,156,"조 메노스키는 자신의 마음을 훔친 세종대왕의 매력을 세상 사람들에게도 알려주고 싶다는 마음으로 영어로 된 장편소설을 완성했다.","https://image.aladin.co.kr/product/25216/49/cover500/k472633707_2.jpg");
insert into newbook values(default,"좌파 고양이를 부탁해","김봄","","에세이","김영사","2020-08-10",144,13000,127437,184,"문제들이 과연 '좌우'의 시각으로만 판단 내려질 수 있는 것인가 질문하며, 대한민국의 축소판과도 같은 '가족사'를 통해 공생(共生)의 전략과 해법은 없는지 고민하게 한다.","https://image.aladin.co.kr/product/24919/20/cover500/k582632475_1.jpg");
insert into newbook values(default,"설민석의 한국사 대모험 15","설민석, 스토리박스","","어린이","아이휴먼","2020-10-26",162,12000,154327,105,"신라와 백제의 찬란한 문화 이야기로 가득한 <설민석의 한국사 대모험 15>는 어린이 독자들에게 신선한 재미와 유익한 지식을 선사할 것이다.","https://image.aladin.co.kr/product/25259/92/cover500/k052633102_1.jpg");
insert into newbook values(default,"Rain : 비 내리는 날의 기적","샘 어셔","이상희","유아","주니어RHK","2018-06-08",167,12000,159871,148,"비가 그칠까? 창밖을 바라보며 기다리고, 또 기다리고... 비가 그치기를 기다리며 아이는 더 신나는 상상 속으로 빠져 드는데...","https://image.aladin.co.kr/product/15085/90/cover500/8925563401_1.jpg");
insert into newbook values(default,"돈의 속성","김승호","","경제경영","스노우폭스북스","2020-06-15",151,16800,182155,130,"그 누구라도 자신에게 꼭 필요한 조언을 찾을 수 있다는 점이 이 책의 가장 큰 장점이다.","https://image.aladin.co.kr/product/24191/64/cover500/k562639753_1.jpg");
insert into newbook values(default,"노자가 옳았다","김용옥","","인문학","통나무","2020-10-09",138,27000,137473,132,"이 책은 도올 노자철학 50년의 총결산이자 완성판이다. 이 한 권의 책으로 심원한 노자철학의 전모가 우리에게 전달된다.","https://image.aladin.co.kr/product/25301/96/cover500/8982641475_1.jpg");
insert into newbook values(default,"미국 영어발은 무작정 따라하기","오경은","","외국어","길벗이지톡","2015-10-25",141,16000,106451,187,"수록된 예문은 모두 TOEIC, TOEFL 리스닝 시험과 일상회화에 자주 쓰이는 표현으로 엄선하여 단어부터 문장까지 체계적으로 완벽하게 훈련시켜준다.","https://image.aladin.co.kr/product/6893/63/cover500/8960477796_1.jpg");
insert into newbook values(default,"나는 풍요로웠고, 지구는 달라졌다","호프 자런","김은령","사회과학","김영사","2020-09-07",151,15500,117527,179,"견고한 사실과 수치에 기초해 있지만 따듯한 유머가 빛을 발하는 글을 통해 독자를 새로운 이해, 즉 모두가 충분히 풍요로울 수 있는 미래에 대한 새로운 사유로 초대한다.","https://image.aladin.co.kr/product/25039/64/cover500/8934990309_1.jpg");
insert into newbook values(default,"에듀윌 공인중개사 봉투모의고사","에듀윌 교수진","","자격증","에듀윌","2020-08-12",185,9000,191084,111,"실전감각을 100% 끌어올릴 수 있도록 문제지를 실제 시험지와 동일한 스타일로 구성하였고, 답안 마킹까지 제대로 연습할 수 있도록 실제 공인중개사 시험과 동일한 OMR 답안지를 수록하였다.","https://image.aladin.co.kr/product/24802/50/cover500/k742631932_1.jpg");
insert into newbook values(default,"이기적 유전자","리처드 도킨스","홍영남, 이상임","대학교재","을유문화사","2018-10-20",145,20000,137535,172,"세계적 베스트셀러, 과학을 넘어선 우리 시대의 고전, 『이기적 유전자』의 40주년 기념판.","https://image.aladin.co.kr/product/17048/25/cover500/8932473900_1.jpg");
insert into newbook values(default,"카카오톡 이모티콘 만들기","김소희","","모바일","길벗","2020-09-18",189,16000,127381,187,"그림을 정식으로 배우지 않아도 일상에서의 아이디어를 이모티콘으로 발전시켜 만들 수 있다. 온라인 커뮤니케이션의 필수 요소가 된 이모티콘을 직접 만들어 누구나 사용할 수 있도록 도전해보자.","https://image.aladin.co.kr/product/25074/86/cover500/k562632853_1.jpg");

# (각 분야 베스트셀러(20만 ~ 30만 판매부수), 조회수 1위(200~300조회수), 재고량 1위(200~300개))
insert into newbook values(default,"당신의 인생을 정리해드립니다","이지영","","가정","쌤앤파커스","2020-10-08",292,16800,292923,284,"공간을 나에게 맞게 효율적으로 바꾸고 채우는 방법을 알면 맥시멀리스트도 얼마든지 잘 정리된 편안한 공간에서 좋아하는 것들에 둘러싸여 행복해질 수 있다고 강조한다.","https://image.aladin.co.kr/product/25162/16/cover500/k432633578_1.jpg");
insert into newbook values(default,"햄릿","셰익스피어","최종철","희곡","민음사","1998-08-05",200,7000,276201,293,"셰익스피어의 4대 비극 중 가장 유명하고 가장 대중의 사랑을 받는 작품인 『햄릿』","https://image.aladin.co.kr/product/16/80/cover500/s8937460033_1.jpg");
insert into newbook values(default,"부지런한 사랑","이슬아","","에세이","문학동네","2020-10-21",218,16000,225946,207,"<부지런한 사랑>은 글쓰기와 삶에 대한 영감과 사랑으로 가득한 에세이이다.","https://image.aladin.co.kr/product/25349/60/cover500/8954675352_1.jpg");
insert into newbook values(default,"흔한 남매 6","흔한남매","","어린이","아이세움","2020-10-15",269,12000,267742,209,"‘흔한남매’는 유튜브 구독자 수가 193만 명, 누적 조회 수가 12억 회를 넘어서는 인기 크리에이터로, 흔한컴퍼니에 소속되어 있다.","https://image.aladin.co.kr/product/25301/95/cover500/k062633309_1.jpg");
insert into newbook values(default,"알사탕","백희나","","유아","책읽는곰","2017-03-25",274,12000,215138,252,"알사탕이 만드는 공감, 용기, 성장의 마법. 언제나처럼 백희나 표 마법은 따뜻하고 행복하다.","https://image.aladin.co.kr/product/10586/14/cover500/k422530533_1.jpg");
insert into newbook values(default,"트렌드 코리아 2021","김난도 외 8인","","경제경영","미래의창","2020-10-12",253,18000,277000,282,"날뛰는 소를 마침내 길들이는 멋진 카우보이처럼, 시의적절한 전략으로 팬데믹의 위기를 헤쳐나가기를 기원하는 뜻을 담았다.","https://image.aladin.co.kr/product/25208/12/cover500/8959896837_3.jpg");
insert into newbook values(default,"죽은 자의 집 청소","김완","","인문학","김영사","2020-05-30",203,13800,278325,247,"현장에 서 있는 듯한 간접 체험을 제대로 할 수 있는 것은 물론, '어떻게 살 것인가' '어떤 죽음을 맞이할 것인가'를 고민하게 만드는 책.","https://image.aladin.co.kr/product/24131/85/cover500/8934992492_1.jpg");
insert into newbook values(default,"ETS 토익 기출문제집 2(리딩)","ETS(엮은이)","","외국어","YBM","2019-12-16",226,17800,206990,263,"정기시험 최신 기출문제 10세트가 수록되어 있다. 기출문제를 통해 토익 최신경향을 숙지하고 실전 감각을 키워 실제 시험에 확실하게 대비할 수 있다.","https://image.aladin.co.kr/product/22006/4/cover500/s442637116_2.jpg");
insert into newbook values(default,"우리가 우리라고 부를 때","추적단 불꽃","","사회과학","이봄","2020-09-23",253,17000,232180,266,"추적단 불꽃은, 각자 다른 삶을 살아온 우리지만 '우리가 우리를 우리라고 부를 때' 연대가 시작된다고 했다. 우리를 위해 이 책을 함께 읽어주길 권한다.","https://image.aladin.co.kr/product/25217/36/cover500/k862633809_1.jpg");
insert into newbook values(default,"최태성 한국사 기출 500제","최태성","","수험서","이투스북","2020-07-21",248,18000,253713,208,"수험생들의 학습 패턴과 한능검 기출을 철저히 분석해 학습 효과를 극대화할 수 있는 분량, 2020년 47회 시험부터 2018년 38회까지 최신 기출문제 10회분, 총 500문항을 수록하였다.","https://image.aladin.co.kr/product/24629/19/cover500/k562631686_1.jpg");
insert into newbook values(default,"영어 전치사 연구","이기동","","대학교재","교문사","2020-05-18",229,18000,231389,243,"전치사의 쓰임을 다루고 있는 이 책이 영어 학습자 및 영어 교육을 담당하는 이에게 큰 도움이 될 것이다.","https://image.aladin.co.kr/product/24311/2/cover500/8936320483_1.jpg");
insert into newbook values(default,"수학과 함께하는 AI 기초","EBS","","it","한국교육방송공사","2020-09-08",277,12000,205040,226,"수학의 원리로 파이선 프로그래밍을 배우고 일상 속 문제 상황을 해결하는 AI 알고리즘을 실습한다.","https://image.aladin.co.kr/product/25097/4/cover500/8954754074_1.jpg");

# ----------oldbook 수정

INSERT INTO oldbook VALUES(default, '수제비 정보처리기사(실기)','서용욱 외 2인','','자격증','도서출판 건기원','2020-05-05',default,'30000',default, default, '강호동', '이책으로 합격했어요','resources/upload/sujebi.jpg','2020-10-10', 1);
INSERT INTO oldbook VALUES(default, '잘쉬는 기술','클라우디아 해먼드','오수원','행복론','웅진 지식하우스','2020-09-22',default,'20000',default, default, '유재석', '최고의휴식법', 'resources/upload/jalshinun.jpg','2020-10-10', 2);

# (등급 최상도서, ob_scount=0)
# (조회수 상(200~300조회수) 
INSERT INTO oldbook VALUES(default, '진화심리학','데이비드 M. 버스','이충호','인문학','웅진지식하우스','2012-06-13',1,'38000',default, 231, '강호동', '심리학과 진화생물학의 현대적인 원리들의 종합', 'resources/upload/jinhwa.jpg','2020-09-01', 1);
INSERT INTO oldbook VALUES(default, '하르부타 질문 수업','양경윤 외 9명','','사회과학','경향BP','2016-04-15',1,'13500',default, 235, '이하은', '유대인들의 기적의 공부법, 하브루타', 'resources/upload/habruta.jpg','2020-09-22', 6);
INSERT INTO oldbook VALUES(default, '2시간에 끝내는 한글영어 발음천사','Mike Hwang','','외국어','마이클리시','2016-07-04',1,'11400',default, 296, '장하윤', '알파벳도 모르는 분이 처음 영어를 익혀도 쉽다', 'resources/upload/balum.jpg','2020-10-01', 7);
# (조회수 중(100~200조회수))
INSERT INTO oldbook VALUES(default, '기본간호학','간호학 문제집 편집위원','','수험서','대한간호협회 ','2017-05-31',1,'25000',default, 120, '태은희', '간호학 시험 수험서', 'resources/upload/ganho.jpg','2020-10-13', 'ehtae9303');
INSERT INTO oldbook VALUES(default, '거시경제학','Frederic S. Miskin','송병호','대학교재','경문사','2015-02-23',1,'38000',default, 183, '이주원', '거시경제학 대학교 도서', 'resources/upload/macroeconomics.jpg','2020-10-11', 'jwlee9509');
INSERT INTO oldbook VALUES(default, '뉴욕스타일 포토샵','캐트린 아이스만 외 2인','김현서','it','피어슨에듀케이션코리아','2012-12-26',1,'38800',default, 126, '태은희', '뉴욕스타일 포토샵 지침서', 'resources/upload/photoshop.jpg','2020-09-28', 'ehtae9303');

# (등급 상도서, 대여횟수 상(20~30 대여횟수))
# (조회수 상(200~300조회수))
INSERT INTO oldbook VALUES(default, '한라산 편지','오희삼','','에세이','터치아트','2009-11-01',2,'15000',29, 231, '전준서', '표지의 한라산이 에쁜 에세이', 'resources/upload/hanrasan.jpg','2020-10-01', 5);
INSERT INTO oldbook VALUES(default, '신곡 전3권','단테 알리기에리','박상진','시','민음사','2013-08-08',2,'30000',27, 220, '김지후', '단테의 신곡, 유명한 고전', 'resources/upload/shingog.jpg','2020-10-07', 4);
INSERT INTO oldbook VALUES(default, '누구 발이냐옹','PIE International','','에세이','아르누보','2018-02-08',2,'12000',26, 212, '태은희', '귀여운 표지의 에세이', 'resources/upload/nyaong.jpg','2020-09-03', 'ehtae9303');
# (조회수 중(100~200조회수))
INSERT INTO oldbook VALUES(default, '바이러스에서 살아남기','곰돌이 co.','','어린이','아이세움','2015-11-25',2,'19600',29, 110, '장하윤', '어린이에게 바이러스 개념을 쉽게 이해시켜준다', 'resources/upload/virus.jpg','2020-09-19', 7);
INSERT INTO oldbook VALUES(default, '우리 아기 오감발달 동물농장 사운드북','샘 태플린','어스본코리아','유아','어스본코리아','2018-04-11',2,'18000',26, 198, '황건우', '아기의 오감발달에 도움되는 책', 'resources/upload/animalfarm.jpg','2020-09-21', 15);
INSERT INTO oldbook VALUES(default, '주식창업교과서','이승조 외 2인','','경제경영','경향BP','2017-07-12',2,'20000',24, 165, '장하윤', '주식 창업을 위한 가이드북', 'resources/upload/stock.jpg','2020-09-23', 7);

# (등급 중도서, 대여횟수 중(10~20 대여횟수))
# (조회수 중(100~200조회수))
INSERT INTO oldbook VALUES(default, '플라워 아트 컬러링북','아르고나인 스튜디오','','컬러링북','봄봄스쿨','2020-06-10',3,'6800',19, 183, '강호동', '화려한 책', 'resources/upload/flower.jpg','2020-09-12', 1);
INSERT INTO oldbook VALUES(default, '맥베스','윌리엄 셰익스피어','김정환','희곡','아침이슬','2008-08-01',3,'10000',15, 192, '김지후', '셰익스피어가 쓴 세기의 명작', 'resources/upload/macbeth.jpg','2020-10-07', 4);
INSERT INTO oldbook VALUES(default, '열두 시에 라면을 끓인다는 건','정다이','','에세이','경향BP','2018-12-20',3,'13500',16, 159, '이주원', '가볍게 읽기 좋은 에세이', 'resources/upload/ramen.jpg','2020-10-10', 'jwlee9509');
# (조회수 하(0~100조회수))
INSERT INTO oldbook VALUES(default, '체스 교과서','머레이 챈들러 외 2인','송진우','어린이','바이킹','2013-04-10',3,'13800',17, 91, '장하윤', '체스를 잘하고 싶은 사람이 읽기 좋은 책', 'resources/upload/chess.jpg','2020-10-03', 7);
INSERT INTO oldbook VALUES(default, '멜로디 봉봉 : 크리스마스 캐럴','김영아','','유아','스마트베어','2019-11-15',3,'15500',19, 64, '이주원', '아이가 따라부르기 좋은 캐롤이 나오는 책', 'resources/upload/bongbong.jpg','2020-10-01', 'jwlee9509');
INSERT INTO oldbook VALUES(default, '배당주 투자 바이블','안훈민','','경제경영','참돌','2014-10-31',3,'25000',10, 2, '김도현', '배당주 투자에 관한 교과서', 'resources/upload/baedangju.jpg','2020-09-19', 14);

# (등급 하도서, 조회수, 대여횟수는 0)
INSERT INTO oldbook VALUES(default, '남편을 모자로 착각한 여자','피아마 루자티','안느 브리짓 알트','인문학','인벤션','2017-11-23',4,'18000',default, default, '남궁민', '철학적 의문을 던지는 좋은책', 'resources/upload/hathusband.jpg','2020-09-13', 'namgoongmin');
INSERT INTO oldbook VALUES(default, '최고의 인재들','데이비드 핼버스탬','송정은, 황지현','사회과학','글항아리','2014-01-27',4,'48000',default, default, '김도현', '사회과학적 시각을 던져주는 책', 'resources/upload/thebest.jpg','2020-09-12', 14);
INSERT INTO oldbook VALUES(default, '한눈에 보인다 독일어 첫걸음','Mr. Sun 어학연구소','','외국어','올드스테어즈','2020-05-01',4,'18000',default, default, '송은서', '독일어 초심자를 위한 책', 'resources/upload/deutch.jpg','2020-10-12', 18);
INSERT INTO oldbook VALUES(default, '매경TEST 공식 가이드','매일경제 경제경영연구소','','수험서','매일경제신문사','2020-04-15',4,'32000',default, default, '태은희', '매경TEST 관련 공식 추천 도서', 'resources/upload/maegyung.jpg','2020-10-17', 'ehtae9303');
INSERT INTO oldbook VALUES(default, '만들어진 신','리처드 도킨스','이한음','대학교재','김영사','2007-07-20',4,'25000',default, default, '이현우', '심오한 질문을 하게 만드는 책', 'resources/upload/god.jpg','2020-09-17', 12);
INSERT INTO oldbook VALUES(default, '블록체인 무엇인가?','다니엘 드레셔','이병욱','it','이지스퍼블리싱','2018-02-19',4,'15000',default, default, '이수아', '블록체인을 알고 싶은 사람들의 필독서', 'resources/upload/blockchain.jpg','2020-10-12', 19);

# (등급 최하도서)(조회수, 대여횟수는 0)
INSERT INTO oldbook VALUES(default, '더 해빙','이서윤, 홍주연','','성공학','수오서재','2020-03-01',5,'16000',default, default, '남궁민', '제목과 표지가 매우 인상적인 책', 'resources/upload/thehaving.jpg','2020-10-11', 'namgoongmin');
INSERT INTO oldbook VALUES(default, '모모','미하엘 엔데','한미희','소설','비룡소','1999-02-09',5,'12000',default, default, '김도현', '미하엘 엔데의 유명한 고전', 'resources/upload/momo.jpg','2020-10-14', 14);
INSERT INTO oldbook VALUES(default, '아무튼, 메모','정혜윤','','에세이','위고','2020-03-15',5,'9900',default, default, '이하은', '많은 생각을 던져주는 에세이', 'resources/upload/memo.jpg','2020-09-24', 6);
INSERT INTO oldbook VALUES(default, '구름빵','백희나','','어린이','한솔수북','2004-10-01',5,'11000',default, default, '전준서', '아이들 사이에서 인기있는 책', 'resources/upload/gurumbbang.jpg','2020-09-06', 5);
INSERT INTO oldbook VALUES(default, '무지개 물고기','마르쿠스 피스터','공경희','유아','시공주니어','1994-04-01',5,'12000',default, default, '황건우', '아기들이 읽는데 좋은 그림책', 'resources/upload/rainbow.jpg','2020-09-15', 15);
INSERT INTO oldbook VALUES(default, '넛지','리처드 H. 탈러, 캐스 R. 선스타인','안진환','경제경영','리더스북','2009-04-20',5,'15500',default, default, '송은서', '경영학 관련 매우 유명한 책', 'resources/upload/nudge.jpg','2020-10-08', 18);

# (등급 승인대기 도서)
INSERT INTO oldbook VALUES(default, '자존감 수업','윤홍균','','인문학','심플라이프','2016-08-25',default,'14000',default, default, '김도현', '자존감을 되찾는데 도움을 주는 책', 'resources/upload/jajongam.jpg','2020-09-14', 14);
INSERT INTO oldbook VALUES(default, '왜 세계의 절반은 굶주리는가?','장 지글러','유영미 외 2명','사회과학','갈라파고스','2007-03-12',default,'9800',default, default, '이현우', '세상의 부조리함을 느끼게 하는 책', 'resources/upload/hungry.jpg','2020-10-12', 12);
INSERT INTO oldbook VALUES(default, '김영철, 타일러의 진짜 미국식 영어','김영철, 타일러 라쉬','','외국어','위즈덤하우스','2017-12-08',default,'14800',default, default, '이수아', '실전 영어를 알려주는 책', 'resources/upload/american.jpg','2020-09-01', 19);
INSERT INTO oldbook VALUES(default, '단기합격 해커스 NCS','윤종혁, 해커스잡 취업연구소 외 2명','','수험서','해커스 공기업','2019-07-01',default,'19800',default, default, '남궁민', '단기합격을 목표로 보기 좋은 책', 'resources/upload/hackersncs.jpg','2020-10-11', 'namgoongmin');
INSERT INTO oldbook VALUES(default, '죽음의 수용소에서','빅터 프랭클','이시형','대학교재','청아출판사','2005-08-10',default,'12000',default, default, '김지후', '역사 속의 부조리함을 느끼게 하는 책', 'resources/upload/death.jpg','2020-09-27', 4);
INSERT INTO oldbook VALUES(default, '좋아 보이는 것들의 비밀, 인포그래픽','김묘영','','it','길벗','2014-02-25',default,'25000',default, default, '강호동', '인포그래픽에 대해 영감을 주는 책', 'resources/upload/infographic.jpg','2020-10-04', 1);

# ------#----admin 수정

#(빠른 로그인 계정)
UPDATE admin SET admin_acc=2 WHERE admin_no = 1;

insert into admin VALUES(default, 'asd', '123', '방예림', '이사',2);
insert into admin VALUES(default, 'fgh', '123', '황보정현', '대리',2);
insert into admin VALUES(default, 'jkl', '123', '유태희', '대리',2);

insert into admin VALUES(default, 'qwe', '123', '해강민', '사원',1);

insert into admin VALUES(default, 'rty', '123', '이서준', '사원',0);
insert into admin VALUES(default, 'uio', '123', '박예준', '사원',2);
insert into admin VALUES(default, 'zxc', '123', '안도윤', '사원',2);
insert into admin VALUES(default, 'vbn', '123', '임시우', '사원',1);

insert into admin VALUES(default, 'poi', '123', '이서연', '사원',2);
insert into admin VALUES(default, 'lkj', '123', '한서윤', '사원',0);
insert into admin VALUES(default, 'mnb', '123', '최지우', '사원',2);
# ########################
#(문자열아이디)
insert into admin VALUES(default, 'shoh9910', 'ShO43474', '오서현', '이사',1);
insert into admin VALUES(default, 'mskim0622', 'MsK48196', '김민서', '대리',1);

insert into admin VALUES(default, 'orange7641', 'm3J8k18', '김민준', '사원',2);
insert into admin VALUES(default, 'ruby999', 'ruby474', '가온누리', '사원',2);
insert into admin VALUES(default, 'sunwooyongnyu', '340782swyn', '선우용녀', '사원',2);
insert into admin VALUES(default, 'green0713', '231871', '그린나래', '사원',2);
insert into admin VALUES(default, 'nrac9812', 'N2R1i8a34', '누리알찬', '사원',0);

# ----------user

# (빠른 로그인 계정)
INSERT INTO USER VALUES(1, '강호동', '123', '010-1234-5678', '서울시 마포구', '04213', 'gang12@abc.net', DEFAULT, DEFAULT, "810101-1234567", default, DEFAULT);
INSERT INTO USER VALUES(2, '유재석', '123', '010-2345-6789', '강원도 원주시', '26500', 'you12@def.net', DEFAULT, DEFAULT, "770222-1234567", default, DEFAULT);
INSERT INTO USER VALUES(3, '전소민', '123', '010-8765-4321', '서울시 강남구', '06325', 'jeon2@def.net', DEFAULT, DEFAULT, "720317-2234567", default, DEFAULT);

# (user_rentcnt=20~30, user_point = 3000~4000)
INSERT INTO USER VALUES(4, '김지후', '123', '010-6161-1909', '서울시 영등포구', '07229', 'jihukim@abc.net', 20, 3844, "920411-1234567", default, DEFAULT);
INSERT INTO USER VALUES(5, '전준서', '123', '010-1867-2127', '서울시 성동구', '04778', 'jsjun0338@def.net', 24, 3566, "910531-1234567", default, DEFAULT);
INSERT INTO USER VALUES(6, '이하은', '123', '010-8760-0977', '서울시 종로구', '03051', 'helee0919@abc.net', 22, 3595, "930602-2234567", default, DEFAULT);
INSERT INTO USER VALUES(7, '장하윤', '123', '010-5311-2140', '경기도 광주시', '12756', 'hejang777@def.net', 26, 3062, "980701-2234567", default, DEFAULT);

# (user_rentcnt=10~20, user_point = 2000~3000)
INSERT INTO USER VALUES(8, '조윤서', '123', '011-209-9592', '서울시 중구', '04561', 'ysjo1125@def.net', 10, 2129, "920827-2234567", default, DEFAULT);
INSERT INTO USER VALUES(9, '김지민', '123', '010-3295-9171', '경기도 파주시', '10848', 'jmkim0225@def.net', 13, 2950, "760916-2234567", default, DEFAULT);
INSERT INTO USER VALUES(10, '권지유', '123', '010-9397-5462', '경상남도 진주시', '52842', 'jiyukwon12@def.net', 17, 2065, "811001-2234567", default, DEFAULT);

# (페널티 1 + user_dcount=0 또는 1)
INSERT INTO USER VALUES(11, '강준우', '123', '011-666-7954', '부산시 강서구', '46716', 'jwkang0003@abc.net', 9, default, "860128-1234567", 1, 0);
INSERT INTO USER VALUES(12, '이현우', '123', '010-7189-3240', '강원도 속초시', '24838', 'hyunwoolee@def.net', 4, DEFAULT, "740422-1234567", 1, 0);
INSERT INTO USER VALUES(13, '윤지훈', '123', '010-3821-4854', '부산시 북구', '46584', 'jihoonyun@def.net', 2, DEFAULT, "811030-1234567", 1, 0);
INSERT INTO USER VALUES(14, '김도현', '123', '010-7705-2922', '서울시 강남구', '06322', 'dia1234@abc.net', 8, default, "971215-1234567", 1, 1);
INSERT INTO USER VALUES(15, '황건우', '123', '010-9489-3268', '경상남도 진주시', '52843', 'kunwoohwang01@def.net', 2, DEFAULT, "950627-1234567", 1, 1);

# (페널티 2 + user_dcount=0 또는 1)
INSERT INTO USER VALUES(16, '김채원', '123', '010-9665-5185', '경상남도 밀양시', '50416', 'sapp9876@never.com', 7, default, "740218-2234567", 2, 1);
INSERT INTO USER VALUES(17, '서지윤', '123', '010-9634-5554', '강원도 원주시', '26503', 'jiyunseo@deum.net', 4, DEFAULT, "710505-2234567", 2, 1);
INSERT INTO USER VALUES(18, '송은서', '123', '010-6657-6141', '서울시 강남구', '06314', 'eunseosong@never.com', 9, DEFAULT, "000330-2234567", 2, 1);
INSERT INTO USER VALUES(19, '이수아', '123', '010-1172-4889', '전라남도 함평군', '57128', 'apple9463@deum.net', 4, default, "990611-2234567", 2, 0);
INSERT INTO USER VALUES(20, '신다은', '123', '010-7028-6596', '전라남도 영암군', '58434', 'banana777@never.com', 6, DEFAULT, "001230-2234567", 2, 0);

# (문자열아이디)
INSERT INTO USER VALUES('namgoongmin', '남궁민', 's2D6e0', '011-534-9070', '광주시 대덕구', '34301', 'namgoongmin@deum.net', default, default, "001127-1234567", default, DEFAULT);
INSERT INTO USER VALUES('ehtae9303', '태은희', 's2D6e0', '010-6890-0087', '제주도 서귀포시', '63621', 'ehtae9303@never.com', default, DEFAULT, "951109-2234567", default, DEFAULT);
INSERT INTO USER VALUES('mswang0505', '왕민석', 's2D6e0', '010-5031-8123', '광주시 유성구', '34130', 'mswang0505@deum.net', default, DEFAULT, "990827-1234567", default, DEFAULT);
INSERT INTO USER VALUES('chrinyeo333', '여채린', 's2D6e0', '010-4750-0536', '경기도 광주시', '12749', 'chrinyeo333@never.com', default, default, "710531-2234567", default, DEFAULT);
INSERT INTO USER VALUES('dhjin1201', '진도훈', 's2D6e0', '010-9833-9396', '서울시 강남구', '06307', 'dhjin1201@deum.net', default, DEFAULT, "860221-1234567", default, DEFAULT);

# (페널티 3 + user_dcount=0 또는 1)
INSERT INTO USER VALUES('jwlee9509', '이주원', 'zxcv760559884', '010-5608-4545', '서울시 마포구', '04207', 'jwlee9509@kokoa.net', 12, default, "001114-1234567", 3, 1);
INSERT INTO USER VALUES('hjjung1118', '정하준', 'hjj90226', '010-9155-0951', '충청남도 천안시', '31220', 'hjjung1118@kokoa.net', 14, DEFAULT, "990922-1234567", 3, 1);
INSERT INTO USER VALUES('jhnam1022', '남지호', 'jHn349524', '010-8232-1910', '충청북도 제천시', '27207', 'jhnam1022@never.com', 10, DEFAULT, "830420-1234567", 3, 0);

# ----------review

insert into review values(default, '1', '1', '읽기 편한 책','2020-09-22',1,0);
insert into review VALUES(default, '2', '2', '나에게 힘을 주는 책', '2020-09-21',1,0);

# (9번 유저 : 공감수를 가장 많이 받은 회원(5글, 글당 공감수 20~30))



# (문자열 아이디 유저 : 공감수 2위 회원(3글, 글당 공감수 20~30))
insert into review values(default, 'ehtae9303', '8', '글에서 힘이 느껴지는 책','2020-09-29',2,26);
insert into review values(default, 'ehtae9303', '30', '한 줄 한 줄이 주옥같은 책','2020-09-22',2,22);
insert into review values(default, 'ehtae9303', '12', '흡입력 있는 책','2020-10-10',3,27);

# (5번 유저 : 공감수 3위(3글, 글당 공감수 10~20))
insert into review values(default, '5', '41', '문체에서 작가의 고뇌가 묻어나는 책','2020-10-09',1,10);
insert into review values(default, '5', '27', '시간가는 줄 모르는 책','2020-09-18',3,19);
insert into review values(default, '5', '4', '커피와 함께 읽기 좋은 책','2020-10-12',3,14);

# (review_bookno=2 일 때)
insert into review values(default, '3', '2', '소장 욕구 자극하는 책','2020-10-03',2,15);
insert into review values(default, '11', '2', '최근 재조명되고 있는 책','2020-10-11',2,12);
insert into review values(default, 'mswang0505', '2', '내 마음 속 베스트셀러','2020-09-21',1,0);
insert into review values(default, '17', '2', '뒤숭숭한 마음을 풀어주는 책','2020-09-19',3,14);

# (review_bookno=3 일 때)
insert into review values(default, 'dhjin1201', '3', '카페에서 읽기 좋은 책','2020-09-20',1,0);
insert into review values(default, '16', '3', '힐링시켜주는 책','2020-10-11',2,6);
insert into review values(default, '8', '3', '심심풀이로 읽기 좋은 책','2020-10-06',2,5);

# (페널티 3 유저)
insert into review values(default, 'jwlee9509', '1', '좋은 책','2020-10-08',3,0);
insert into review values(default, 'jwlee9509', '32', '매우 좋은 책','2020-10-08',3,0);

# ----------cardinfo
# (간단한 카드번호 및 패스워드)
insert into cardinfo values(1, '강호동', 'BC', 'bc123', '1234');
insert into cardinfo values(2, '유재석', 'KB국민', 'kb123', '1234');
insert into cardinfo values(3, '전소민', '삼성', 'ss123', '1234');
insert into cardinfo values(4, '김지후', '신한', 'sh123', '1234');
insert into cardinfo values(5, '전준서', '우리', 'wr123', '1234');
insert into cardinfo values(6, '이하은', '하나', 'ha123', '1234');
insert into cardinfo values(7, '장하윤', '롯데', 'ro123', '1234');
insert into cardinfo values(8, '조윤서', '현대', 'hd123', '1234');
insert into cardinfo values(9, '김지민', 'NH농협', 'nh123', '1234');
insert into cardinfo values(10, '권지유', 'BC', 'bc1232', '1234');

insert into cardinfo values(11, '강준우', 'BC', '4048951688614078', '1234');
insert into cardinfo values(12, '이현우', 'KB국민', '4579371697943018', '2345');

# (한 사람, 다수의 카드)
insert into cardinfo values(13, '윤지훈', '삼성', '4512622152364971', '3456');
insert into cardinfo values(13, '윤지훈', 'BC', '4048983862521316', '4567');
insert into cardinfo values(13, '윤지훈', '현대', '4181842746587860', '5678');
insert into cardinfo values(13, '윤지훈', 'NH농협', '4854736757195235', '6789');

insert into cardinfo values(14, '김도현', '신한', '4518718272666047', '1234');
insert into cardinfo values(15, '황건우', '우리', '4473169142488656', '2345');
insert into cardinfo values(16, '김채원', '하나', '4119757635463744', '3456');
insert into cardinfo values(17, '서지윤', '롯데', '4124647283392407', '4567');
insert into cardinfo values(18, '송은서', '현대', '4181417744511261', '5678');
insert into cardinfo values(19, '이수아', 'NH농협', '4854894098955221', '6789');
insert into cardinfo values(20, '신다은', 'BC', '4048900870514297', '7890');

insert into cardinfo values('namgoongmin', '남궁민', 'BC', '4048326733395618', '1234');
insert into cardinfo values('ehtae9303', '태은희', '현대', '4181711135134675', '2345');
insert into cardinfo values('mswang0505', '왕민석', '삼성', '4512438790052392', '3456');
insert into cardinfo values('chrinyeo333', '여채린', '롯데', '4124146640483556', '4567');
insert into cardinfo values('dhjin1201', '진도훈', '삼성', '4512671566913635', '5678');
insert into cardinfo values('jwlee9509', '이주원', '삼성', '4512549492255815', '6789');
insert into cardinfo values('hjjung1118', '정하준', '삼성', '4512585389153727', '7890');
insert into cardinfo values('jhnam1022', '남지호', '삼성', '4512372362697358', '1234');

# ----------rentinfo
# (9번(ob_state=2), 16번책과 20번책(ob_state=3)은 대여하지 않음)
insert into rentinfo VALUES(19, '4', '2020-10-01', '2020-10-15',default);
insert into rentinfo VALUES(18, '4', '2020-10-03', '2020-10-24',1);

# (연장 기능 사용 : user 9)
insert into rentinfo VALUES(12, '9', '2020-10-01', '2020-10-15',default);
insert into rentinfo VALUES(13, '9', '2020-10-12', '2020-11-02',1);
insert into rentinfo VALUES(14, '9', '2020-10-11', '2020-10-25',default);
insert into rentinfo VALUES(15, '9', '2020-10-03', '2020-10-24',1);

# (연체자)
insert into rentinfo VALUES(17, '10', '2020-09-19', '2020-10-10',1);
insert into rentinfo VALUES(10, '10', '2020-09-18', '2020-10-09',1);
insert into rentinfo VALUES(11, '11', '2020-08-22', '2020-09-12',1);

# ----------faqboard
# (주문)
insert into faqboard values(DEFAULT,'주문시 기재한 입금자명과 다르게 송금했는데 입금확인 안됩니까?', '주문시 기재하신 입금자명과 실제 입금자명이 다르면 입금확인이 불가능하거나 지연됩니다. 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신고해주시면 확인해드립니다. 신고 전 실제 입금 확인내용을 확인하셔서 표시된 정보 그대로 신고해주세요.', '2020-09-02','주문');
insert into faqboard values(DEFAULT,'주문 후 품절(일시품절/절판) 등으로 못 받을 수도 있습니까?', '주문 후 일부상품은 출판사, 거래처의 실시간 재고변동에 의해 소진될 경우 품절,절판,일시품절 등 서비스 불가능한 경우가 발생할 수 있습니다. 단, 일시품절은 상품페이지 일정에 맞춰 재출간/출시된 이후 유통되면 확보 후 추가로 배송해드립니다. 품절,절판의 경우에는 유통이 불가능해 부득이 취소 및 환불이 진행됩니다.', '2020-09-19','주문');
insert into faqboard values(DEFAULT,'비회원 주문을 이용하는데, 정식 회원으로 전환할 수 있습니까?', '비회원 주문 이메일주소/비밀번호를 정식 회원 계정으로 전환하시는 것은 불가능하며, 우선 비회원 접속해지 후 정식 회원 가입절차를 진행하셔야 합니다. 단 비회원 접속해지 후 비회원 주문내역은 삭제되어 복원이 불가합니다.', '2020-10-01','주문');
insert into faqboard values(DEFAULT,'신용카드 결제만 되고 주문이 확인되지 않아요.', '결제 시스템 연동중 일시장애로 주문이 완료되지 못한 경우입니다. 이 경우 1-2일 내 자동으로 승인취소가 진행됩니다. 번거로우시겠지만, 주문 및 결제를 재진행 해주시기 바랍니다.', '2020-10-05','주문');
insert into faqboard values(DEFAULT,'주문하는 방법을 알고 싶습니다.', '인터넷 주문만 가능하며 전화나 팩스 주문 등은 불가합니다. 회원/비회원 주문 모두 가능하지만, 할인 등 구매 혜택을 이용하시려면 반드시 회원가입을 해 주셔야 합니다. 가급적 회원가입 후 구매 혜택을 이용하시길 권해드립니다.', '2020-10-13','주문');
# (배송)
insert into faqboard values(DEFAULT,'주문 후 입금 전(입금확인 전)인데, 언제쯤 배송됩니까?','입금 전이면 입금확인 완료(입금 후 약 30분-1시간 내 확인)후 해당 시점의 재고 유무, 각 배송사 집하마감 시간 경과 여부 등을 기준으로 주문처리 일정이 재계산되므로, 입금대기 상태의 일정과 달라질 수 있습니다. 입금완료 후 주문 일정을 반드시 참고해주시기 바랍니다.', '2020-09-22','배송');
insert into faqboard values(DEFAULT,'배송료는 얼마죠?', '유료배송 상품이면서 무료배송 기준에 미달되면 주문당 2천원의 배송비가 부과됩니다. 단, 국내배송에 한하며, 해외배송은 별도의 배송비가 부과됩니다.', '2020-09-12','배송');
insert into faqboard values(DEFAULT,'수령예상일이 지났는데 아직 못 받았습니다.', '출고완료 후 통상 1-2일 내에는 배송이 됩니다만, 배송 물량이 급증하거나 해당 지역의 배송 상황에 다른 문제가 있다면 예정일 보다 지연될 수 있습니다. 해당 택배 영업소에 의뢰하시면 자세한 안내와 배송 예상시점 등을 확인하실 수 있습니다. 영업소와의 연락이 어려운 경우에는 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신고해 주시면 신속 배송 되도록 조치해드립니다.', '2020-09-27','배송');
insert into faqboard values(DEFAULT,'회사로 배송신청을 하면 퇴근 전 당일 배송이 되나요?', '가급적 예상시간대 보다 늦게 배송되더라도 수령 가능한 댁으로 주문하시기 바랍니다. 특히, 토요일은 반드시 집으로 주소지정 해 주셔야 당일 수령이 가능합니다. 회사의 경우 퇴근시간 전 배송을 최대한 서두르고 있으나 당일 배송량이나 코스별 교통상황에 따른 지연배송 우려가 늘 존재할 수 있습니다.', '2020-10-03','배송');
insert into faqboard values(DEFAULT,'군부대도 배송이 됩니까?', '배송주소지가 군부대, 교도소 등 민간인 출입 제한 지역인 경우에도 배송이 가능합니다만, 반드시 < 우체국택배 >로 주문해주셔야 합니다. 우체국택배로 선택하시면 부대 혹은 행정시설내 우편물 관리부서 통해 수취인께 전달이 됩니다. 수취인명,계급(혹은 보호실 등) 등 수령인정보를 상세히 기재해주십시오', '2020-10-09','배송');
# (상품)
insert into faqboard values(DEFAULT,'외국도서가 품절인데 구할 수 있습니까?', '외국도서는 국내도서에 비해 재고 확보가 여의치 않아 품절로 표시되는 경우가 많이 있습니다. 그러나 경우에 따라서 소량 입수 가능한 경우도 있으니, 구매 원하시는 외국도서인데 품절로 표시된 경우에는 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 이용해 문의하여 주시기 바랍니다.', '2020-09-18','상품');
insert into faqboard values(DEFAULT,'수령일이 다른 상품들을 함께 주문하면 나누어 배송해주나요?', '상품별 수령예상일이 다르면 가장 늦은 상품 기준으로 주문 전체의 출고,수령예상일이 정해지고, 해당 일정에 맞춰 한꺼번에 출고,배송됩니다.', '2020-09-29','상품');
insert into faqboard values(DEFAULT,'상품 소개글 오탈자 신고는 어디로 접수합니까?', '사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신고해주시면 신속히 수정하겠습니다.', '2020-10-01','상품');
insert into faqboard values(DEFAULT,'주문액 보다 더 많거나 적게 입금했는데 입금확인 됩니까?', '주문액 보다 조금이라도 많거나 적게 입금하신 경우에는 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신고해주셔야 입금 확인이 가능합니다. 과입금시 상품 출고 다음 날 저녁까지 차액을 예치금으로 환불해 드리고 있습니다.', '2020-10-11','상품');
insert into faqboard values(DEFAULT,'상품 분류(카테고리) 정보가 없는데 어떻게 요청합니까?', '분류 정보가 없거나, 부정확하다고 생각하시면 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신고해주시면 적절한 분류 요청이 가능합니다.', '2020-10-14','상품');

# ----------orderinfo(order_booktype=1이면 nb, order_booktype=2이면 ob)
# (비회원주문내역)
# (newbook, 무통장입금)
insert into orderinfo values(default,"20201010-01","서장훈","",15,'1','2020-10-10',"1234",2, '0', 1, 36000, "서울특별시 동작구 동작대로35아길 32, 101호(사당동, 세종프라임)");
insert into orderinfo values(default,"20201012-01","서장훈","",27,'1','2020-10-12',"1234",2, '0', 2, 33600,"서울특별시 동작구 동작대로35아길 32, 101호(사당동, 세종프라임)");
# (oldbook : ob_state=1, ob_no=7과 8은 주문하지 않은 상태)
# (카드결제)
insert into orderinfo values(default,"20201010-02","서장훈","",3,'2','2020-10-10',"1234",1, '1', 3, 38000,"서울특별시 동작구 동작대로35아길 32, 101호(사당동, 세종프라임)");
insert into orderinfo values(default,"20201012-02","서장훈","",4,'2','2020-10-12',"1234",1, '1', 4, 13500,"서울특별시 동작구 동작대로35아길 32, 101호(사당동, 세종프라임)");

# (회원주문내역)
# (newbook, 카드결제)
insert into orderinfo values(default,"20201014-01","김지민","9",17,'1','2020-10-14',"",2, '1', 5, 39600, "경기도 파주시 검산로173번길 89-16");
insert into orderinfo values(default,"20201011-01","김지민","9",33,'1','2020-10-11',"",2, '1', 4, 28800,"경기도 파주시 검산로173번길 89-16");
# (oldbook : ob_state=1, ob_no=7과 8은 주문하지 않은 상태)
# (무통장입금)
insert into orderinfo values(default,"20201014-02","김지민","9",5,'2','2020-10-14',"",1, '0', 2, 11400,"경기도 파주시 검산로173번길 89-16");
insert into orderinfo values(default,"20201011-02","김지민","9",6,'2','2020-10-11',"",1, '0', 1, 25000,"경기도 파주시 검산로173번길 89-16");

# ----------inquery
# (단일 문의 - 답변의 구성)
insert into inquery values(default, '구매처 도장이 찍힌 도서', '혹시 구매처 도장이 찍힌 도서도 판매 가능한가요? 책 상단에 서울서점 도장이 찍혀있는데 판매 불가인가요?', '2020-09-29', 'chrinyeo333', 1, 0, 0);
insert into inquery values(default, '[Re]구매처 도장이 찍힌 도서', '가능합니다. 다만, 증정/드림/비매품/관공서 등 날인이 있는 도서는 판매하실 수 없습니다.', '2020-09-29', 'chrinyeo333', 1, 1, 1);

# (몰린 문의-답변의 구성)
insert into inquery values(default, '교환은 어떤 경우에 가능합니까?', '사 놓고 아직 안 뜯은 새 책이 있는데, 구매한 지는 좀 지났습니다. 다른 시리즈의 책으로 바꿀 의향이 있는데 가능한가요?', '2020-10-09', '11', 3, 0, 0);
insert into inquery values(default, '무통장입금으로 주문하고 입금했는데 입금확인 언제 됩니까?', '제 착오인지 회사 착오인지 모르겠으나 무통장입금 확인이 뜨지 않습니다. 혹시 이 점에 관련해서 확인이 가능한가요?', '2020-10-10', '4', 4, 0, 0);
insert into inquery values(default, '혹시 도서 소득공제 대상 도서 구매시 유료배송료도 소득공제 대상인가요?', '책을 구매할 때 구매비용은 소득공제 대상이라는 것은 알겠는데 제가 부담하는 배송료까지 합해서 소득공제 신청이 가능한지 알고 싶습니다.', '2020-10-11', '8', 5, 0, 0);

insert into inquery values(default, '[Re]교환은 어떤 경우에 가능합니까?', '받으신 상품에 하자가 있는 경우 교환이 가능하나, 다른 상품으로 맞교환 구매는 불가합니다. 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 접수하실 수 있습니다.', '2020-10-12', '11', 3, 1, 1);
insert into inquery values(default, '[Re]무통장입금으로 주문하고 입금했는데 입금확인 언제 됩니까?', '입금정보가 정확하다면 통상 1시간 이내에 입금확인이 됩니다. 입금확인이 되면 바로 안내메일 또는 SMS를 보내드립니다. 단, 입금액, 입금자명, 입금은행명의 세가지중 한가지라도 다른 내용이 있으면 입금확인이 지연될 수 있습니다. 입금하시고 1시간 후에도 입금대기 상태라면 사이트 내에 기재되어 있는 문의전화번호 또는 1:1 문의를 통해 신속한 처리 지원을 받을 수 있습니다.', '2020-10-12', '4', 4, 1, 1);
insert into inquery values(default, '[Re]혹시 도서 소득공제 대상 도서 구매시 유료배송료도 소득공제 대상인가요?', '대상 도서 구매시 상품페이지상 표시된 유료배송료(단,해외배송료 제외)는 대상 금액으로 간주됩니다. 단, 중고 구매 도서의 택배발송 신청시 배송료는, 원하시는 고객만 신청해 이용하시는 편의 서비스 비용이므로, 대상이 아닙니다.', '2020-10-12', '8', 5, 1, 1);