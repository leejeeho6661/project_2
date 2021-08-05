create table member_join(
ID varchar2(40) CONSTRAINT member_join_ID_pk primary key,
NAME varchar2(40) CONSTRAINT member_join_NAME_nn not null,
BIRTH varchar2(20) CONSTRAINT member_join_BIRTH_nn not null,
SEX char(2) CONSTRAINT member_join_SEX_nn not null,
NICKNAME varchar2(10) CONSTRAINT member_join_NICKNAME_nn not null,
PHONE varchar2(15) CONSTRAINT member_join_PHONE_nn_nu not null UNIQUE,
EMAIL varchar2(50) CONSTRAINT member_join_EMAIL_nn_nu not null UNIQUE,
ADDRESS_ZIP varchar2(5),
ADDRESS varchar2(300),
INTEREST varchar2(300),
JOINDATE DATE default sysdate);

drop table member_join;

COMMENT ON COLUMN member_join.id is '아이디';
COMMENT ON COLUMN member_join.name is '이름';
COMMENT ON COLUMN member_join.BIRTH IS '생일';
COMMENT ON COLUMN member_join.SEX is '성별';
COMMENT ON COLUMN member_join.nickname is '닉네임';
COMMENT ON COLUMN member_join.PHONE is '연락처';
COMMENT ON COLUMN member_join.ADDRESS_ZIP IS '우편번호';
COMMENT ON COLUMN member_join.ADDRESS is '주소';
COMMENT ON COLUMN member_join.INTEREST IS '관심분야';
COMMENT ON COLUMN member_join.JOINDATE IS '가입일';

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL primary key,
  password VARCHAR(60) NOT NULL,
  enabled number(1) DEFAULT 1
 );

CREATE TABLE user_roles (
  user_role_id NUMBER(11) NOT NULL,
  username VARCHAR(45) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT fk_username FOREIGN KEY (username)
     REFERENCES users (username)
);

CREATE SEQUENCE user_roles_seq
    START WITH 1
    INCREMENT BY 1
    maxvalue 99999
    nocycle;

--remember_me 항목(csrf token) 사용시 저장 선택적 생성
create table presisten_logins(
username varchar(64) not null,
series varchar(64) primary key,
token varchar(64) not null,
last_used timestamp not null);