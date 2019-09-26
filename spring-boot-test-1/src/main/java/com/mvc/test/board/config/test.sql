DROP TABLE TB_CUSTOMER;

CREATE TABLE TB_CUSTOMER(
	ID VARCHAR2(100) PRIMARY KEY,
	PASSWORD VARCHAR2(1000) NOT NULL,
	NAME VARCHAR2(100) NOT NULL,
	AUTHORITY VARCHAR2(100) NOT NULL,
	ISACCOUNTNONEXPIRED VARCHAR2(100) NOT NULL,
	ISACCOUNTNONLOCKED VARCHAR2(100) NOT NULL,
	ISCREDENTIALNONEXPIRED VARCHAR2(100) NOT NULL,
	LISENABED VARCHAR2(100) NOT NULL
);

INSERT INTO TB_CUSTOMER VALUES('admin','1234','관리자');
INSERT INTO TB_CUSTOMER VALUES('user','1234','유저');

SELECT ID, PASSWORD, NAME, ISACCOUNTNONEXPIRED, ISACCOUNTNONLOCKED, ISCREDENTIALNONEXPIRED, ISENABLED FROM TB_CUSTOMER;
==============================================================================
DROP TABLE AUTHORITIES;

CREATE TABLE AUTHORITIES(
	ID VARCHAR2(100),
	AUTHORITY VARCHAR2(100),
	CONSTRAINT FK_ID FOREIGN KEY(ID)
	REFERENCES TB_CUSTOMER(ID)
);

INSERT INTO AUTHORITIES VALUES('admin','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('user','ROLE_USER');

SELECT ID, AUTHORITY FROM AUTHORITIES;


