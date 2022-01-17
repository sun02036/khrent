-- 회원 테이블 생성
CREATE TABLE member (
	member_id	varchar2(20)		NOT NULL,
	member_pwd	varchar2(300)		NOT NULL,
	member_role	char(1)	DEFAULT 'U' 	NOT NULL,
	member_name	 varchar2(50)		NOT NULL,
	phone	char(11)		NOT NULL,
	mileage 	number	DEFAULT 0	NOT NULL,
	reg_date	date	DEFAULT sysdate	NOT NULL,
	issue_date varchar2(10),
	license_type varchar2(20),
	license_no char(12),
   
    constraint pk_member_id primary key(member_id),
    constraint ck_member_role check (member_role in ('U', 'A'))
);

select * from member;

update member set member_role = 'A' where member_id = 'honggd';

-- 탈퇴 회원(member_del)테이블 생성
create table member_del
as
select
    m.*,
    systimestamp del_Date
from
    member m
where
    1 = 0;

select * from member_del;

-- 탈퇴 회원 트리거
create or replace trigger trg_member_del
    before delete on member
    for each row
begin
    insert into member_del (member_id, member_pwd, member_role, member_name, phone, mileage, reg_date, issue_date, license_type, license_no, del_date)
    values (
    :old.member_id,
    :old.member_pwd,
    :old.member_role,
    :old.member_name,
    :old.phone,
    :old.mileage,
    :old.reg_date,
    :old.issue_date,
    :old.license_type,
    :old.license_no,
    sysdate);
end;
/

-- 트리거 비활성화
ALTER TRIGGER trg_member_del DISABLE;

-- 트리거 활성화
ALTER TRIGGER trg_member_del ENABLE;

drop table reservation;
drop table car_list;
drop table car_info;

drop sequence seq_car_info_no;
drop sequence seq_car_list_no;


create sequence seq_car_info_no;
create sequence seq_car_list_no;

create table car_info (
    car_info_no number,
    car_name varchar2(50) not null,
    maker varchar2(20) null,
    fuel varchar2(10) not null,
    car_size varchar2(20) null,
    img varchar2(50) not null,
    assess_cnt number,
    avg_score number,
    reserv_cnt number,

    constraint pk_car_info_no primary key(car_info_no),
    constraint uq_car_name unique(car_name)
);


create table car_list (
    car_code varchar2(30) default 'khrent-'||lpad(seq_car_list_no.nextval, 4, '0'),
    car_name varchar2(50) not null,
    release_year varchar2(10),
    car_option  varchar2(500),
    price number,
    number_plate varchar2(30) not null,

    constraint pk_car_list_car_code primary key(car_code),
    constraint fk_car_list_car_name foreign key(car_name) references car_info(car_name) on delete cascade
);

select * from car_list;

create sequence seq_reservation_no;
drop sequence seq_reservation_no;

-- car join
--select * from (select row_number() over(order by car_code asc) rnum, b.car_code, car_name, a.maker, a.fuel, a.car_size, b.release_year, a.img, b.car_option, b.price, b.number_plate, a.assess_cnt, a.avg_score, a.reserv_cnt from  car_info a join car_list b using(car_name) where number_plate like '%7' order by car_code) where rnum between 1 and 5;

--select count(*) from (select  b.car_code,  car_name,  a.maker,  a.fuel,  a.car_size,  b.release_year,  a.img, b.car_option, b.price, b.number_plate, a.assess_cnt, a.avg_score, a.reserv_cnt from  car_info a join car_list b using(car_name) where car_name like '%아반떼%' order by car_code);


--select 
--* 
--from
--    (select 
--        row_number() 
--        over(order by car_code asc) rnum, 
--        c.* 
--        from 
--        car_list c ) 
--c where rnum between ? and ?




--  예약(reservation)테이블 생성
CREATE TABLE reservation (
    reserv_no    varchar2(30) default 'reserv-'||lpad(seq_reservation_no.nextval, 4, '0'),
    member_id varchar2(50)    NOT NULL,
    car_code varchar2(30) not null,
    car_name     varchar2(50)    NOT NULL,
    start_date date not null,
    end_date date NOT NULL,
    price number,
    insurance_type char(1) NOT NULL,
    issue_date varchar2(10) not null,
    license_type varchar2(20) not null,
    review_status char(1) default 'N',
    return_status char(1) default 'N',

    constraint pk_reservation_reserv_no primary key(reserv_no),
    constraint fk_reservation_member_id foreign key(member_id) references member(member_id) on delete cascade,
    constraint fk_reservation_car_code foreign key(car_code) references car_list(car_code) on delete cascade
);

insert into reservation values ('reserv-0001', 'kh123', 'khrent-0009', '아반떼 CN7', '21-11-07', '21-11-08', '60000', 'Y', '19-04-27', 'auto', 'N', 'N');
insert into reservation values ('reserv-0002', 'youquiz12', 'khrent-0022', '모하비 더 마스터', '21-11-07', '21-11-08', '180400', 'Y', '20-07-21', 'auto', 'N', 'N');
insert into reservation values ('reserv-0003', 'dbwotjr12', 'khrent-0029', '쏘나타 뉴라이즈', '21-11-08', '21-11-09', '65400', 'Y', '14-06-13', 'auto', 'N', 'N');
insert into reservation values ('reserv-0004', 'qwerty', 'khrent-0045', '스포티지 NQ5', '21-11-08', '21-11-09', '128700', 'Y', '13-08-04', 'auto', 'N', 'N');
insert into reservation values ('reserv-0005', 'wltnwls00', 'khrent-0057', 'BMW i8', '21-11-09', '21-11-10', '100', 'Y', '12-12-26', 'auto', 'N', 'N');
insert into reservation values ('reserv-0006', 'tnwlsdl125', 'khrent-0130', '더 뉴 카니발 하이리무진', '21-11-09', '21-11-10', '100', 'Y', '21-09-20', 'auto', 'N', 'N');
insert into reservation values ('reserv-0007', 'jangwon12', 'khrent-0131', '더 뉴 카니발 하이리무진', '21-11-11', '21-11-12', '100', 'Y', '21-09-20', 'auto', 'N', 'N');
insert into reservation values ('reserv-0008', 'king123', 'khrent-0132', '더 뉴 카니발 하이리무진', '21-11-11', '21-11-12', '100', 'Y', '21-09-20', 'auto', 'N', 'N');
insert into reservation values ('reserv-0009', 'admin', 'khrent-0133', '더 뉴 카니발 하이리무진', '21-11-13', '21-11-14', '100', 'Y', '21-09-20', 'auto', 'N', 'N');

-- 공지사항(notice) 테이블 추가
CREATE TABLE notice (
    notice_no number NOT NULL, 
    notice_title varchar2(30)    NOT NULL,
    notice_content    varchar2(4000)    NOT NULL,
    reg_date    date        DEFAULT sysdate NOT NULL,
    read_count number    NOT NULL,
    
    -- 제약조건 추가
    constraint pk_notice_no primary key(notice_no)
);

-- 공지사항 테이블 시퀀스 추가
create sequence seq_notice_no;

select * from notice;


--  자유게시판(community) 테이블 추가
CREATE TABLE community (
    community_no    number    NOT NULL,
    community_title    varchar2(30)    NOT NULL,
    community_writer varchar2(20) NOT NULL,
    community_content    varchar2(4000)    NOT NULL,
    reg_date    date     DEFAULT sysdate NOT NULL,
    read_count    number    NOT NULL,
    
    -- 제약조건
    constraint pk_community_no primary key(community_no),
    constraint fk_community_writer foreign key(community_writer) references member(member_id) on delete cascade
);

--  community_notice_no sequence 생성
create sequence seq_community_no;

insert into community values(1, '제목입니다.', 'sun02036', '내용입니다.', sysdate, 0);


--  자유게시판 댓글(community_comment) 테이블 추가
CREATE TABLE community_comment (
    no    number    NOT NULL,
    community_no    number    NOT NULL,
    writer    varchar2(20)    NOT NULL,
    content    varchar2(4000)    NOT NULL,
    reg_date    date     DEFAULT sysdate NOT NULL,
    comment_level     number     DEFAULT 1 NOT NULL,
    comment_ref    number    NOT NULL,
    
    -- 제약조건
    constraint pk_community_comment_no primary key(no),
    constraint fk_community_comment_writer foreign key(writer) references member(member_id) on delete cascade,
    constraint fk_community_comment_notice_no foreign key(community_no) references community(community_no) on delete cascade,
    constraint fk_community_comment_ref foreign key(comment_ref) references community_comment(no) on delete cascade
);

--  자유게시판 댓글 시퀀스 생성
create sequence seq_community_comment_no;

--  자유게시판 첨부파일(community_attach) 테이블 추가
CREATE TABLE community_attach (
    no    number    NOT NULL,
    community_no    number    NOT NULL,
    original_filename    varchar2(255) NOT NULL,
    renamed_filename    varchar2(255) NOT NULL,
    reg_date    date     DEFAULT sysdate NOT NULL,

      -- 제약조건
    constraint pk_community_attach_no primary key(no),
    constraint fk_community_attach_community_no foreign key(community_no) references community(community_no)

);

--  community_attach_no sequence 생성
create sequence seq_community_attach_no;



--  문의사항(qna_board)테이블 생성
CREATE TABLE qna_board (
    qna_no    number    NOT NULL,
    qna_writer    varchar2(20)    NOT NULL,
    qna_title    varchar2(30)    NOT NULL,
    qna_content    varchar2(4000)    NOT NULL,
    reg_date    date     DEFAULT sysdate     NOT NULL,
    answer_status    char(1)    DEFAULT 'N' NOT NULL,

    ------ 제악조건
    constraint pk_qna_no primary key(qna_no),
    constraint fk_qna_writer foreign key(qna_writer) references member(member_id) on delete cascade
);


--  qna_board sequence 생성
create sequence seq_qna_no;

-- 문의 사항 댓글(qna_comment) 테이블 추가
CREATE TABLE qna_comment (
    no    number    NOT NULL,
    qna_no    number    NOT NULL,
    writer    varchar2(30)    NOT NULL,
    content    varchar2(4000)    NOT NULL,
    reg_date    date    NOT NULL,
    comment_level    number    NOT NULL,
    comment_ref    number    NOT NULL,

    -- 제약조건 추가
    constraint pk_qna_comment_no primary key(no),
    constraint fk_qna_comment_qna_no foreign key(qna_no) references qna_board(qna_no) on delete cascade,
    constraint fk_qna_comment_writer foreign key(writer) references member(member_id) on delete cascade,
    constraint fk_qna_comment_comment_ref foreign key(comment_ref) references qna_comment(no) on delete cascade
);

alter table review_comment modify reg_date date default sysdate;
alter table qna_comment modify reg_date date default sysdate;
alter table community_comment modify reg_date date default sysdate;

alter table qna_comment modify comment_ref null;

--  qna_comment_no sequence 생성
create sequence seq_qna_comment_no;
    
-- 문의 사항 첨부파일(qna_attach) 테이블 추가
CREATE TABLE  qna_attach (
    no number,
    qna_no    number    NOT NULL,
    original_filename    varchar2(255) NOT NULL,
    renamed_filename    varchar2(255) NOT NULL,
    reg_date    date     DEFAULT sysdate NOT NULL,
       
    -- 제약조건 추가
    constraint pk_qna_attach_no primary key(no),
    constraint fk_qna_attach_qna_no foreign key(qna_no) references qna_board(qna_no) on delete cascade
);

alter table qna_board add read_count number;

    --  qna_board sequence 생성
create sequence seq_qna_attach_no;

drop table review_comment;
drop table review_attach;
drop table review_board;

--  이용후기(review_board) 테이블 생성
CREATE TABLE review_board (
    review_no number,
    reserv_no varchar2(30),
    review_writer    varchar2(100)    NOT NULL,
    review_title    varchar2(30)    NOT NULL,
    review_content    varchar2(4000)    NOT NULL,
    car_name     varchar2(30)    NOT NULL,
    reg_date    date     DEFAULT sysdate NOT NULL,
    read_count number NOT NULL,
    score number NOT NULL,
    
    -- 제약조건 생성
    constraint pk_review_no primary key(review_no),
    constraint fk_reserv_no foreign key(reserv_no) references reservation(reserv_no) on delete cascade,
    constraint fk_review_writer foreign key(review_writer) references member(member_id) on delete cascade,
    constraint fk_review_car_name foreign key(car_name) references car_info(car_name) on delete cascade
);

-- alter table review_board add constraint fk_review_car_name foreign key (car_name) references car_list(car_name) on delete cascade;


-- 이용후기 테이블 sequence 생성
create sequence seq_review_no;

-- 이용 후기 댓글 테이블 생성
CREATE TABLE review_comment (
    no    number    NOT NULL,
    review_no    number    NOT NULL,
    writer    varchar2(30)    NOT NULL,
    content    varchar2(4000)    NOT NULL,
    reg_date    date default sysdate NOT NULL,
    comment_level     number     NOT NULL,
    comment_ref    number,
    
    ---제약 조건
    constraint pk_review_comment_no primary key(no),
    constraint fk_review_comment_review_no foreign key(review_no) references review_board(review_no) on delete cascade,
    constraint fk_review_comment_writer foreign key(writer) references member(member_id) on delete cascade,
    constraint fk_review_comment_comment_ref foreign key(comment_ref) references review_comment(no) on delete cascade
);

-- 이용후기 댓글 테이블 sequence 생성
create sequence seq_review_comment_no;

--  이용후기 첨부파일(Review_board_attach) 테이블 생성
CREATE TABLE review_attach (
	no	number,
	review_no number	NOT NULL,
	original_filename	varchar2(255)	NOT NULL,
	renamed_filename	varchar2(255)	NOT NULL,
	reg_date	date	 DEFAULT sysdate,
    
     -- 제약조건 생성
    constraint pk_review_attach_no primary key(no),
    constraint fk_review_attach_review_no foreign key(no) references review_board(review_no)
);

select * from car_info;

--
--ALTER TABLE review_attach
--RENAME CONSTRAINT fk_review_attach_board_no TO
--                  fk_review_attach_review_no;

-- 이용후기 첨부파일 테이블 sequence 생성
create sequence seq_review_attach_no;

-- 최신가입일순
select
    rnum,
    m.*
from (
    select
        rownum rnum,
        m.*
    from (
        select
            *
        from
            member
        order by
            reg_date desc
        ) m
    ) m;

-- window함수 row_number
select
    *
from(
    select  
        row_number() over(order by reg_date desc) rnum,
        m.*
    from
        member m
    ) m;
    
select count(*) cnt from member;

select 
* 
from 
    (select 
    row_number() 
    over(order by reg_date desc) rnum, m.* 
    from
    member m 
    where member_id like '%s%') 
where rnum between 1 and 1;

alter table community_comment modify comment_ref number null;
alter table qna_comment modify comment_ref number null;
alter table review_comment modify comment_ref number null;

-- 전체 테이블 조회
select 
    *
from 
    dba_tables
where 
    owner = 'KHRENT';
    
-- car_info와 car_list에 넣을 데이터


-- <car_info>

--형구
insert into car_info values(seq_car_info_no.nextval, '람보르기니 우라칸',  '람보르기니', '휘발유', '수입', '람보르기니 우라칸.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '그랜드 스타렉스',  '현대', '경유', '승합', '그랜드 스타렉스.jpg', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '벤츠 E클래스',  '벤츠', '휘발유', '수입', '벤츠 e클래스.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '벤츠 GT43AMG 4MATIC',  '벤츠', '휘발유', '수입', '벤츠 GT43 AMG 4MATIC.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '아반떼 CN7', '현대', '휘발유', '소형', '아반떼 CN7.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '캐딜락 에스컬레이드', '캐딜락', '휘발유', '수입', '캐딜락 에스컬레이드.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '코나 일렉트릭', '현대', '전기', '소형', '코나 일렉트릭.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '팰리세이드', '현대', '휘발유', 'SUV', '팰리세이드.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '포르쉐 박스터', '포르쉐', '휘발유', '수입', '포르쉐 박스터.png', 0, 0, 0);





--수진
insert into car_info values (seq_car_info_no.nextval, '쏘나타 DN8', '현대', 'LPG', '중형', '쏘나타 DN8.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '쏘나타 뉴라이즈', '현대', 'LPG', '중형', '쏘나타 뉴라이즈.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '올 뉴 K3', '기아', '휘발유', '소형', '올 뉴 k3.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, 'k7 프리미어', '기아', '휘발유', '대형', 'k7 프리미어.jpg', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '벤츠 C클래스', '벤츠', '휘발유', '수입', '벤츠 C클래스.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '벤츠 E클래스 카브리올레', '벤츠', '경유', '수입', '벤츠 E클래스 카브리올레.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '제네시스 G70', '제네시스', '휘발유', '중형', '제네시스 G70.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '스포티지 NQ5', '기아', '경유', 'SUV', '스포티지 NQ5.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '베뉴', '현대', '휘발유', 'SUV', '베뉴.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, 'SM5', '르노삼성', 'LPG', '중형', 'SM5.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, 'SM6', '르노삼성', 'LPG', '중형', 'SM6.jpg', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '티볼리 베리 뉴', '쌍용', '경유', 'SUV', '티볼리 베리 뉴.png', '0', '0', '0');
insert into car_info values (seq_car_info_no.nextval, '재규어XF', '재규어', '경유', '수입', '재규어XF.png', '0', '0', '0');


--찬영
insert into car_info values(seq_car_info_no.nextval, 'BMW i8', 'BMW', '휘발유', '수입', 'BMW I8.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, 'BMW x6', 'BMW', '휘발유', '수입', 'BMW X6.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, 'g80 3세대', '현대', '휘발유', '중형', 'G80 3세대.png' , 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '레이', '기아', '휘발유', '소형', '레이.jpg', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '레인지로버 이보크', '랜드로버', '휘발유', '수입', '레인지로버 이보크.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '마세라티 그란카브리오 스포츠', '마세라티', '휘발유', '수입', '마세라티 그란카브리오 스포츠.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '벤틀리 컨티넨탈 GT', '벤틀리', '휘발유', '수입', '벤틀리 컨티넨탈 GT.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '벤틀리 플라잉스퍼', '벤틀리', '휘발유', '수입', '벤틀리 플라잉스퍼.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '아반떼 ad', '현대', '휘발유', '소형', '아반떼 AD.jpg', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '코나', '현대', '경유', 'SUV', '코나.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '테슬라 3', '테슬라', '전기', '수입', '테슬라 3.png', 0,0,0);
insert into car_info values(seq_car_info_no.nextval, '아반떼 ag', '현대', '휘발유', '소형', '아반떼 AG.jpg', 0,0,0);



-- 태영
insert into car_info values(seq_car_info_no.nextval,'투싼','현대','가솔린','SUV','투싼.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'트위지','르노삼성','전기','소형','트위지.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'아우디R8','아우디','휘발유','수입','아우디R8.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'엑센트','현대','가솔린','소형','엑센트.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'올 뉴 카니발','현대','가솔린','소형','올 뉴 카니발.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'셀토스','기아','가솔린','소형','셀토스.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'스토닉','기아','가솔린','소형','스토닉.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'스파크','쉐보레','가솔린','소형','스파크.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'모닝','기아','가솔린','소형','모닝.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'맥라렌 570S Coupe','멕라렌','가솔린','수입','맥라렌 570S Coupe.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'K5 블랙','기아','가솔린','중형','K5 블랙.png',0,0,0);
insert into car_info values(seq_car_info_no.nextval,'K5 DL3','기아','가솔린','중형','K5 DL3.png',0,0,0);


-- <car_list>

-- 형구
insert into car_list values(default, '람보르기니 우라칸', '2016', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 1900000, '23하 3452');

insert into car_list values(default, '그랜드 스타렉스', '2018', '일반시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서', 115800, '38허 4865');
insert into car_list values(default, '그랜드 스타렉스', '2017', '일반시트 네비게이션 블랙박스 후방카메라 후방센서', 110000, '39호 4865');
insert into car_list values(default, '그랜드 스타렉스', '2016', '일반시트 하이패스 네비게이션 블랙박스 후방센서', 105800, '40하 4865');
insert into car_info values(seq_car_info_no.nextval, '포르쉐 파나메라', '포르쉐', '휘발유', '수입', '포르쉐 파나메라.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '그랜저 IG', '현대', 'LPG', '대형', '그랜저IG.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '모하비 더 마스터', '기아', '경유', 'SUV', '모하비 더 마스터.png', 0, 0, 0);
insert into car_info values(seq_car_info_no.nextval, '폭스바겐 티구안', '폭스바겐', '경유', '수입', '폭스바겐 티구안.png', 0, 0, 0);

insert into car_list values(default, '벤츠 E클래스', '2018', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 300000, '41하 6384');
insert into car_list values(default, '벤츠 E클래스', '2017', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키', 280000, '42허 2345');

insert into car_list values(default, '벤츠 GT43AMG 4MATIC', '2020', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 543700, '25허 5678');

insert into car_list values(default, '아반떼 CN7', '2020', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 70000, '34호 4152');
insert into car_list values(default, '아반떼 CN7', '2020', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 65000, '36하 4152');
insert into car_list values(default, '아반떼 CN7', '2020', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 ', 60000, '38호 4282');

insert into car_list values(default, '캐딜락 에스컬레이드', '2018', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 530000, '64하 5242');

insert into car_list values(default, '코나 일렉트릭', '2021', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 10000, '42허 4556');
insert into car_list values(default, '코나 일렉트릭', '2018', '열선 통풍시트 하이패스 블랙박스 후방카메라 후방센서 스마트키 블루투스', 110000, '46하 9556');


insert into car_list values(default, '팰리세이드', '2021', '열선시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 140000, '45허 8452');
insert into car_list values(default, '팰리세이드', '2021', '열선시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 ', 120000, '95호 2345');


insert into car_list values(default, '포르쉐 박스터', '2018', '오픈카 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 470000, '91호 4722');
insert into car_list values(default, '포르쉐 박스터', '2016', '오픈카 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 ', 440000, '24하 6722');

insert into car_list values(default, '포르쉐 파나메라', '2017', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 580000, '72허 5826');

insert into car_list values(default, '그랜저 IG', '2019', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 160400, '18호 6782');
insert into car_list values(default, '그랜저 IG', '2018', '통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 140400, '74하 0782');
insert into car_list values(default, '그랜저 IG', '2017', '일반시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키', 110400, '38허 6722');

insert into car_list values(default, '모하비 더 마스터', '2020', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 180400, '73호 0672');
insert into car_list values(default, '모하비 더 마스터', '2019', '열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 160400, '81허 8412');
insert into car_list values(default, '모하비 더 마스터', '2018', '통풍시트 하이패스 네비게이션 후방카메라 후방센서 스마트키 블루투스', 140400, '04하 1035');

insert into car_list values(default, '모하비 더 마스터', '2020', '썬루프 열선시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 크루즈 컨트롤', 125800, '15허 3972');

-- 수진

insert into car_list values (default, '쏘나타 DN8', '2020', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 70000, '62허 3518');
insert into car_list values (default, '쏘나타 DN8', '2020',  '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 70000, '10허 1234');
insert into car_list values (default, '쏘나타 DN8', '2020',  '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 70000, '38호 4104');

insert into car_list values (default, '쏘나타 뉴라이즈', '2019', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블 1일 500km 200원', 65400, '02허 6157');
insert into car_list values (default, '쏘나타 뉴라이즈', '2018', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 45400, '67허 3208');
insert into car_list values (default, '쏘나타 뉴라이즈', '2018', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 47700, '67호 8016');

insert into car_list values (default, '올 뉴 K3', '2020', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스', 80100, '19호 7777');
insert into car_list values (default, '올 뉴 K3', '2020', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 45400, '52허 3108');
insert into car_list values (default, '올 뉴 K3', '2020', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 47700, '67허 8016');

insert into car_list values (default, 'k7 프리미어', '2020', '파노라마 열선,통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 1일500km 200원', 131600, '45하 6492');
insert into car_list values (default, 'k7 프리미어', '2020', '파노라마 열선,통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 1일500km 200원', 131600, '52허 3108');
insert into car_list values (default, 'k7 프리미어', '2020', '파노라마 열선,통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스', 115800, '152호 3018');

insert into car_list values (default, '벤츠 E클래스 카브리올레', '2018', '파노라마 열선 전동시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 1일250km 900원', 296300, '08하 7554');

insert into car_list values (default, '벤츠 C클래스', '2020', '오픈카 열선 전동시트 네비게이션 후방카메라 후방센서 스마트키 블루투스', 243400, '57호 1454');
insert into car_list values (default, '벤츠 C클래스', '2020', '파노라마 열선 통풍시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 1일 200km 1,000원', 413200, '97허 5748');

insert into car_list values (default, '제네시스 G70', '2019', '파노라마 열선 전동 통풍시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 145800, '78호 5248');
insert into car_list values (default, '제네시스 G70', '2019', '파노라마 열선 전동 통풍시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스', 139800, '98하 5789');

insert into car_list values (default, '스포티지 NQ5', '2021', '파노라마 열선 통풍시트 하이패스 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 1일500km 200원', 118900, '93하 6872');
insert into car_list values (default, '스포티지 NQ5', '2021', '열선 전동 통풍시트 하이패스 10.24 인치 UVO 전후면 후방카메라 후방센서 스마트키 블루투스 1일500km 200원 스마트 크루즈 컨트롤', 130400, '87호 2487');
insert into car_list values (default, '스포티지 NQ5', '2021', '열선 전동 통풍시트 하이패스 10.24 인치 UVO 전후면 후방카메라 후방센서 스마트키 블루투스 1일500km 200원', 128700, '97허 5487');

insert into car_list values (default, 'SM6', '2018', '열선시트 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 65000, '124하 4689');
insert into car_list values (default, 'SM6', '2018', '열선시트 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 65000, '14허 5798');
insert into car_list values (default, 'SM6', '2018', '열선시트 네비게이션 블랙박스 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 65000, '75호 2213');

insert into car_list values (default, '베뉴', '2019', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블 1일200km 500원', 80000, '82호 3521');
insert into car_list values (default, '베뉴', '2019', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블 1일200km 500원 스마트 크루즈 컨트롤', 65000, '88호 1412');
insert into car_list values (default, '베뉴', '2019', '열선시트 하이패스 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블 스마트 크루즈 컨트롤', 76000, '24허 3551');

insert into car_list values (default, 'SM5', '2016', '열선시트 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 70000, '78허 7351');
insert into car_list values (default, 'SM5', '2016', '열선시트 네비게이션 전후면 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 70000, '15호 1241');

insert into car_list values (default, '티볼리 베리 뉴', '2019', '열선 전동 통풍시트 하이패스 네비게이션 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 95000, '97하 5497');

insert into car_list values (default, '재규어XF', '2019', '썬루프 열선 전동 통풍시트 하이패스 네비게이션 후방카메라 후방센서 스마트키 블루투스 AUX케이블', 200000, '14하 6346');
insert into car_list values (default, '재규어XF', '2019', '썬루프 열선 전동 통풍시트 하이패스 네비게이션 후방카메라 후방센서 스마트키 블루투스', 189000, '64허 2733');

-- 찬영

insert into car_list values(default, 'BMW i8','2021', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 640000, '64하1754');
insert into car_list values(default, 'BMW i8','2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 640000, '64허1202');

insert into car_list values(default, 'BMW x6', '2021', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 640000, '33하1514');
insert into car_list values(default, 'BMW x6', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 640000, '64하5841');

insert into car_list values(default, 'g80 3세대', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 281200, '71호1254');
insert into car_list values(default, 'g80 3세대', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 281200, '71호1255');
insert into car_list values(default, 'g80 3세대', '2018', '하이패스 네비게이션 후방카메라 후방센서 블루투스 흡연차', 270000, '71하1256');

insert into car_list values(default, '레이', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 61000, '50호1154');
insert into car_list values(default, '레이', '2015', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 50000, '75호1255');
insert into car_list values(default, '레이', '2018', '하이패스 네비게이션 후방카메라 후방센서 블루투스 흡연차', 59000, '76하1116');

insert into car_list values(default, '레인지로버 이보크', '2014', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 290000, '89호1784');

insert into car_list values(default, '마세라티 그란카브리오 스포츠', '2013', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 1004100, '91하5465');

insert into car_list values(default, '벤틀리 컨티넨탈 GT', '2019', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 1760400, '45호1236');

insert into car_list values(default, '벤틀리 플라잉스퍼', '2015', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 800000, '46호2236');

insert into car_list values(default, '아반떼 ad', '2016', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차',70400, '56호2236');
insert into car_list values(default, '아반떼 ad', '2017', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 80400, '66호3236');
insert into car_list values(default, '아반떼 ad', '2018', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 90400, '46호4236');

insert into car_list values(default, '아반떼 ag', '2019', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차',85000, '56호2237');
insert into car_list values(default, '아반떼 ag', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 90000, '66호3237');
insert into car_list values(default, '아반떼 ag', '2021', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 95000, '46호4237');

insert into car_list values(default, '코나', '2019', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차',89800, '56호2238');
insert into car_list values(default, '코나', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 79400, '76호3238');
insert into car_list values(default, '코나', '2021', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 95000, '46하4238');

insert into car_list values(default, '테슬라 3', '2019', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차',470000, '56하1289');
insert into car_list values(default, '테슬라 3', '2020', '하이패스 네비게이션 후방카메라 후방센서 블루투스 금연차', 480000, '12호5087');

-- 태영
insert into car_list values(default,'투싼','2017','네비게이션 블루투스 하이패스 후방센서 통풍시트',120000, '13하7595');
insert into car_list values(default,'투싼','2018','네비게이션 블루투스 하이패스 후방센서 통풍시트',121000, '11허7195');
insert into car_list values(default,'투싼','2019','네비게이션 블루투스 하이패스 후방센서 통풍시트',123000, '18호8895');

insert into car_list values(default,'트위지','2019','네비게이션 블루트스 후방센서',60000, '16하3555');
insert into car_list values(default,'트위지','2020','네비게이션 블루투스 후방센서',63000, '12허9797');

insert into car_list values(default,'아우디R8','2014','썬루프 통풍시트 하이패스 블루트스 후방센서',750000, '13하5555');
insert into car_list values(default,'아우디R8','2014','썬루프 통풍시트 하이패스 블루트스 후방센서',740000, '19호1122');

insert into car_list values(default,'엑센트','2015','네비게이션 블루투스 하이패스 후방센서 통풍시트',80000, '11호1212');
insert into car_list values(default,'엑센트','2017','네비게이션 블루투스 하이패스 후방센서 통풍시트',82000, '10하4423');
insert into car_list values(default,'엑센트','2019','네비게이션 블루투스 하이패스 후방센서 통풍시트',85000, '66하3456');

insert into car_list values(default,'올 뉴 카니발','2015','일반시트 블루투스 후방센서 네비게이션',155000, '10허1112');
insert into car_list values(default,'올 뉴 카니발','2016','일반시트 블루투스 후방센서 네비게이션',155800, '10하4423');
insert into car_list values(default,'올 뉴 카니발','2019','일반시트 블루투스 후방센서 네비게이션',108000, '62하4545');

insert into car_list values(default,'셀토스','2019','열선시트 블루투스 후방센서 네비게이션',75000, '88허1102');
insert into car_list values(default,'셀토스','2020','열선시트 블루투스 후방센서 네비게이션',80000, '12호4060');

insert into car_list values(default,'스토닉','2019','열선시트 블루투스 후방센서 네비게이션',55000, '82허5467');
insert into car_list values(default,'스토닉','2019','열선시트 블루투스 후방센서 네비게이션',62000, '12하1235');

insert into car_list values(default,'스파크','2019','일반시트 블루투스 후방센서 네비게이션',54000, '46허4845');
insert into car_list values(default,'스파크','2019','일반시트 블루투스 후방센서 네비게이션',61000, '87호1456');
insert into car_list values(default,'스파크','2019','일반시트 블루투스 후방센서 네비게이션',61000, '22호4287');

insert into car_list values(default,'모닝','2018','열선시트 블루투스 후방센서 네비게이션',32000, '09하8975');
insert into car_list values(default,'모닝','2019','열선시트 블루투스 후방센서 네비게이션',31000, '87호8520');
insert into car_list values(default,'모닝','2020','열선시트 블루투스 후방센서 네비게이션',30000, '21허8666');

insert into car_list values(default,'맥라렌 570S Coupe','2018','파노라마열선 블루투스 후방센서 네비게이션',1300000, '01하1111');

insert into car_list values(default,'K5 블랙','2018','열선시트 하이패스 블루투스 후방센서 네비게이션',65000, '15호8888');
insert into car_list values(default,'K5 블랙','2019','열선시트 하이패스 블루투스 후방센서 네비게이션',70000, '22하2232');
insert into car_list values(default,'K5 블랙','2019','열선시트 하이패스 블루투스 후방센서 네비게이션',71000, '21허86586');

insert into car_list values(default,'K5 DL3','2020','통풍시트 하이패스 블루투스 후방센서 네비게이션',69000, '33하7898');
insert into car_list values(default,'K5 DL3','2020','통풍시트 하이패스 블루투스 후방센서 네비게이션',68000, '55호6669');
insert into car_list values(default,'K5 DL3','2020','통풍시트 하이패스 블루투스 후방센서 네비게이션',69000, '87허2288');

select * from car_info;
select * from car_list;

select * from(select row_number() over(order by car_code desc) rnum, c.* from car_list c ) c where rnum between 1 and 5;

select * from (select row_number() over(order by car_code asc) rnum, c.* from car_list c where number_plate like '%23하 3452%') where rnum between 1 and 5;
select count(*) from car_list where number_plate like '%23하 3452%';

select * from reservation where to_char(end_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd');

select * from(select row_number() over(order by reserv_no desc) rnum, r.* from reservation r where (to_char(end_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')) and ((to_char(end_date, 'yyyymmdd') <= to_char(sysdate, 'yyyymmdd'))) and return_status = 'n')  r where rnum between 1 and 5;

alter table review_attach add constraint fk_review_attach_review_no foreign key(review_no) references review_board(review_no) on delete cascade;
alter table review_attach drop constraint fk_review_attach_review_no;
alter table review_attach add constraint fk_review_attach_review_no foreign key(review_no) references review_board(review_no) on delete cascade;

select * from review_board;

update car_info set avg_score = 1 where car_name = '람보르기니 우라칸';
update car_info set avg_score = 2 where car_name = '그랜드 스타렉스';
update car_info set avg_score = 3 where car_name = '벤츠 E클래스';
update car_info set avg_score = 4 where car_name = '코나 일렉트릭';
update car_info set avg_score = 5 where car_name = '포르쉐 박스터';
update car_info set avg_score = 6 where car_name = '팰리세이드';