create user ict03_zest identified by tiger default tablespace users;

grant connect, resource, create view to ict03_zest;
grant create session to ict03_zest;

alter user ict03_zest account unlock;

-- �ǹ��� -------------------------------------------------------------------------------
-- �Խ���  ���̺�
DROP TABLE mvc_Notice_TBL  CASCADE CONSTRAINTS;
CREATE TABLE mvc_Notice_TBL(  
  N_Board_Num   NUMBER(7)  PRIMARY KEY,      -- �۹�ȣ
   N_Title       VARCHAR2(50)  NOT NULL,      -- ������
   N_Content     CLOB  NOT NULL,              -- �۳���
   N_Writer      VARCHAR2(30)  NOT NULL,       -- �ۼ���
   N_readCnt     NUMBER(6)   DEFAULT 0,      -- ��ȸ��
   N_WriteDate     DATE  DEFAULT sysdate,       -- �ۼ���
   show         VARCHAR2(10) DEFAULT 'y'
);

--�������� �Է�
INSERT INTO mvc_Notice_TBL(N_Board_Num, N_Title, N_Content, N_Writer, N_WriteDate)
 VALUES((SELECT NVL(MAX(N_Board_Num)+1,1) FROM mvc_Notice_TBL), '�������˾ȳ�' , '2024�� 07�� 15�� ����ð� 22��~24�ñ��� ���� ������ �̷���� �����Դϴ�', '�Ŵ���23', sysdate);
--�������� ��������
SELECT * FROM mvc_Notice_TBL
 WHERE N_Board_Num=1;
--�������� ����
UPDATE mvc_Notice_TBL
   SET N_Title = '���� �����Ͽ� ���� �÷��帳�ϴ�.'
      ,N_Content ='����� ���������� �˷��帮�� ����Ʈ�Դϴ�. �ش� ������ ���Ŵ� ���Ż���Ʈ�� �����ϼż� �����ϼž��մϴ�.'
  WHERE N_Board_Num= 2;
--�������� ���� 
UPDATE mvc_Notice_TBL
   SET show ='n'
 WHERE N_Board_Num = 3;
 
--�������� �����ȸ
SELECT * 
  FROM(
      SELECT A.*,
             N_Board_Num AS rn 
        FROM 
             (SELECT *
                 FROM mvc_Notice_TBL
                WHERE show = 'y'
               ORDER BY N_Board_Num DESC
                ) A    
      ) 
 WHERE rn BETWEEN 1 AND 10;
 
 --�������� ��ȸ�� ����
 UPDATE mvc_Notice_TBL
    SET N_readCnt = N_readCnt+1
  WHERE N_Board_Num= 2;  
  
 --�������� ���� ���ϱ�
 SELECT COUNT(*) AS cnt
 FROM mvc_Notice_TBL;

-- �谡�� -------------------------------------------------------------------------------
-- jsp_pj_ict03���� ����
----------  �������̺� mvc_ad_concert_tbl ���� ----------------------------------------------------
DROP TABLE mvc_ad_concert_tbl CASCADE CONSTRAINTS;
 CREATE TABLE mvc_ad_concert_tbl(
    conNo        NUMBER(7)  PRIMARY KEY,            -- ������ȣ
    conCategory  VARCHAR2(50) NOT NULL,             -- ����ī�װ�
    conName      VARCHAR2(50)  NOT NULL UNIQUE,     -- ������
    conGrade     VARCHAR2(50) NOT NULL,             -- �������
    conTime      VARCHAR2(100) NOT NULL,            -- ������¥/�ð�
    conPlace     VARCHAR2(50) NOT NULL,             -- �������
    conImg       VARCHAR2(100) NOT NULL,            -- �����̹���
    conBuy       VARCHAR2(200) NOT NULL,            -- ��������ó
    conPrice     NUMBER(9) NOT NULL,                -- ��������
    conContent   CLOB,                              -- ��������
    conStatus    VARCHAR2(20) NOT NULL,             -- ���������ڵ�
    conIndate    DATE  DEFAULT sysdate              -- ���������
 );
 
-- ����  ��� ��ȸ
SELECT * FROM mvc_ad_concert_tbl
ORDER BY conNo;

-- ���� ����
SELECT COUNT(*) as cnt FROM mvc_ad_concert_tbl;

-- ���� (���� show Į�� �߰�)
 ALTER TABLE mvc_ad_concert_tbl
   ADD show CHAR(1) DEFAULT 'y';

 SELECT *
   FROM (
         SELECT A.*, 
               rownum AS rn  -- �Ϸú�ȣ ��������
          FROM 
            (
              SELECT *                
                FROM mvc_ad_concert_tbl 
                WHERE show = 'y'
                ORDER BY conNo DESC
            ) A
        )   
 WHERE rn BETWEEN 1 AND 10; 

---- ���� ��� ��ȸ -----
SELECT * FROM mvc_ad_concert_tbl 
 WHERE show = 'y'
 ORDER BY conNo;
   
-- Į�� ����
ALTER TABLE mvc_ad_concert_tbl 
MODIFY conTime VARCHAR2(200);
  
   
-- �������
INSERT INTO mvc_ad_concert_tbl(conNo, conCategory, conName, conGrade, conTime, conPlace, conImg, conBuy, conPrice, conContent, conStatus, conIndate)
 VALUES((SELECT NVL(MAX(conNo)+1, 1) FROM mvc_ad_concert_tbl), 'Ʈ��Ʈ', '�ܼ�Ʈ�̸�1', '��7���̻�', '2024/07/07', 'ü�������', '/js_pj_fasticat/resources/images/����̵޸��.jpg', '����ó', 115000, '��������', '�Ǹ���', sysdate); 

COMMIT;

-- ���� ����(update n)
UPDATE mvc_ad_concert_tbl
SET show = 'n'
WHERE conNo = 1;

-- ���� ����
UPDATE mvc_ad_concert_tbl
SET conCategory = '������'
WHERE conNo = 4;

------------------  �佺Ƽ�� ���̺� ----------------------------------------------------------------------------
-- �佺Ƽ�����̺� mvc_ad_festival_tbl ����
DROP TABLE mvc_ad_festival_tbl CASCADE CONSTRAINTS;
 CREATE TABLE mvc_ad_festival_tbl(
    fesNo        NUMBER(7)  PRIMARY KEY,            -- �佺Ƽ�� ��ȣ
    fesName      VARCHAR2(50)  NOT NULL UNIQUE,     -- �佺Ƽ����
    fesGrade     VARCHAR2(50) NOT NULL,             -- �������
    fesTime      VARCHAR2(200) NOT NULL,            -- �佺Ƽ�� ��¥/�ð�
    fesPlace     VARCHAR2(50) NOT NULL,             -- �佺Ƽ�� ���
    fesImg       VARCHAR2(100) NOT NULL,            -- �佺Ƽ�� �̹���
    fesBuy       VARCHAR2(200) NOT NULL,            -- ����ó
    fesPrice     NUMBER(9) NOT NULL,                -- �佺Ƽ�� ����
    fesContent   CLOB,                              -- �佺Ƽ�� ����
    fesStatus    VARCHAR2(20) NOT NULL,             -- �佺Ƽ�� �����ڵ�
    fesIndate    DATE  DEFAULT sysdate,             -- �佺Ƽ�� �����
    show         CHAR(1) DEFAULT 'y'     
 );
 
-- �佺Ƽ�� ��� ��ȸ
SELECT * FROM mvc_ad_festival_tbl
WHERE show = 'y'
ORDER BY fesNo;

INSERT INTO mvc_ad_festival_tbl(fesNo, fesName, fesGrade, fesTime, fesPlace, fesImg, fesBuy, fesPrice, fesContent, fesStatus, fesIndate)
 VALUES((SELECT NVL(MAX(fesNo)+1, 1) FROM mvc_ad_festival_tbl), '���������佺Ƽ��', '��ü������', '2024/07/07', '�ø��Ȱ���', '/js_pj_fasticat/resources/images/����̵޸��.jpg', '������ũ', 189000, '��������', '�Ǹ���', sysdate); 
COMMIT;


-- �佺Ƽ�� ����
SELECT COUNT(*) as cnt FROM mvc_ad_festival_tbl;


-- �佺Ƽ�� (���� show Į�� �߰�)
 ALTER TABLE mvc_ad_festival_tbl
   ADD show CHAR(1) DEFAULT 'y';

 SELECT *
   FROM (
         SELECT A.*, 
               rownum AS rn  -- �Ϸú�ȣ ��������
          FROM 
            (
              SELECT *                
                FROM mvc_ad_festival_tbl 
                WHERE show = 'y'
                ORDER BY fesNo DESC
            ) A
        )   
 WHERE rn BETWEEN 1 AND 10; 


-- �佺Ƽ�� ������ ����
DELETE mvc_ad_festival_tbl
WHERE fesNo = 9;

-- �佺Ƽ�� ����
UPDATE mvc_ad_festival_tbl
SET fesNo=1, fesName='', fesGrade='', fesTime='', fesPlace='', fesImg='', fesBuy='', fesPrice='', fesContent='', fesStatus='', fesIndate=''
WHERE fesNo=1;

-- �佺Ƽ�� ����(update n)
UPDATE mvc_ad_festival_tbl
SET show = 'n'
WHERE fesNo = 7;

COMMIT;



----------- ������̺� ----------------------------------------------------------
DROP TABLE mvc_ad_banner_tbl CASCADE CONSTRAINTS;
 CREATE TABLE mvc_ad_banner_tbl(
    bannerNo        NUMBER(7)  PRIMARY KEY,            -- ��ʹ�ȣ
    bannerArea      VARCHAR2(50)  NOT NULL UNIQUE,     -- ��ʿ���(����1,2,3)
    bannerImg       VARCHAR2(100) NOT NULL,            -- ��� �̹���
    bannerStatus    VARCHAR2(20) NOT NULL,             -- ��� �����ڵ�(�����,����)
    bannerIndate    DATE  DEFAULT sysdate,              -- �����
    show              CHAR(1) DEFAULT 'y'     
 );
 
-- ��� ��� ��ȸ
SELECT * FROM mvc_ad_banner_tbl
ORDER BY bannerNo;

-- ��� ���
INSERT INTO mvc_ad_banner_tbl(bannerNo, bannerArea, bannerImg, bannerStatus, bannerIndate)
 VALUES((SELECT NVL(MAX(bannerNo)+1, 1) FROM mvc_ad_banner_tbl), '���ι��1', '/js_pj_fasticat/resources/images/����̵޸��.jpg', '�����', sysdate); 
COMMIT;

SELECT * FROM mvc_ad_banner_tbl
 WHERE show = 'y'
 ORDER BY bannerNo;

-- ��� ����
DELETE mvc_ad_banner_tbl
WHERE bannerNo = 3;
COMMIT;

-- ��� ����
UPDATE mvc_ad_banner_tbl
SET bannerArea='���ι��1', bannerImg='/js_pj_fasticat/resources/images/����̵޸��.jpg', bannerStatus='�����', bannerIndate=sysdate
WHERE bannerNo=1;

-- ��� ����(update n)
UPDATE mvc_ad_banner_tbl
SET show = 'y'
WHERE bannerNo = 2;


------ �������� --------------------------------------------------------------------
-- ��������  ���̺�
DROP TABLE mvc_ad_notice_tbl  CASCADE CONSTRAINTS;
CREATE TABLE mvc_ad_notice_tbl(  
   noticeNo          NUMBER(7)  PRIMARY KEY,      -- �������� ��ȣ
   noticeTitle       VARCHAR2(50)  NOT NULL,      -- �������� ����
   noticeContent     CLOB  NOT NULL,              -- �������� ����
   noticeImg         VARCHAR2(100) NOT NULL,      -- ÷���̹���
   noticeWriter      VARCHAR2(30)  NOT NULL,      -- �ۼ���(������)
   noticeReadCnt     NUMBER(6)   DEFAULT 0,       -- ��ȸ��
   noticeRegDate     DATE  DEFAULT sysdate,       -- �ۼ���
   show              CHAR(1) DEFAULT 'y'          
);

-- �������� ��� ��ȸ
SELECT * FROM mvc_ad_notice_tbl
ORDER BY noticeNo;

-- �������� ���
INSERT INTO mvc_ad_notice_tbl(noticeNo, noticeTitle, noticeContent, noticeImg, noticeWriter, noticeReadCnt, noticeRegDate)
 VALUES((SELECT NVL(MAX(noticeNo)+1, 1) FROM mvc_ad_notice_tbl), '����1', '����', '/js_pj_fasticat/resources/images/����̵޸��.jpg', '������1', 0, sysdate); 
COMMIT;

-- �������� (���� show Į�� �߰�)
 ALTER TABLE mvc_ad_notice_tbl
   ADD show CHAR(1) DEFAULT 'y';
   
-- �������� ����
UPDATE mvc_ad_notice_tbl
SET noticeTitle='���ι��1', noticeContent='����' , noticeImg='/js_pj_fasticat/resources/images/����̵޸��.jpg', noticeWriter='�ۼ���', noticeReadCnt='0', noticeRegDate=sysdate
WHERE noticeNo=1;

-- �������� ����
UPDATE mvc_ad_notice_tbl
SET show='n'
WHERE noticeNo=1;

-- �輺�� -------------------------------------------------------------------------------
--2. �Խ��� ����, �佺Ƽ�� �ı� �Խ���/  
DROP TABLE reviewBoard_tbl;
CREATE TABLE reviewBoard_tbl(  
     board_num         NUMBER(7),                  -- �۹�ȣ
     board_category    VARCHAR2(30) DEFAULT '�����ı�',                --ī�װ� 
     board_title       VARCHAR2(50)  NOT NULL,      -- ������
     board_content     CLOB  NOT NULL,              -- �۳���
     board_image       VARCHAR2(1000),                -- �̹�������
     board_writer      VARCHAR2(30)  NOT NULL,      -- �ۼ��� fk (userid)
     board_regDate     DATE  DEFAULT sysdate,       -- �ۼ���
     board_views       NUMBER(6)   DEFAULT 0,       -- ��ȸ��
     board_likes       NUMBER(6)   DEFAULT 0,      -- ���ƿ� 
     board_show        CHAR(1) DEFAULT 'y'
);

-- �ı� �Խ��� ��������, y ����
DROP TABLE reviewBoard_list;
CREATE TABLE reviewBoard_list
AS
    (SELECT r.*, rownum AS rn 
      FROM (
        SELECT * FROM reviewBoard_tbl
         WHERE board_show = 'y'
         ORDER BY board_num DESC  
      ) r 
  );
SELECT * FROM reviewBoard_list;


--���� �Խ��� 
DROP TABLE freeBoard_tbl;
CREATE TABLE freeBoard_tbl(  
     board_num         NUMBER(7),                  -- �۹�ȣ
     board_category    VARCHAR2(30) DEFAULT '����',                --ī�װ� 
     board_title       VARCHAR2(50)  NOT NULL,      -- ������
     board_content     CLOB  NOT NULL,              -- �۳���
     board_image       VARCHAR2(1000),                -- �̹������� ����Ʈ �̹��� �ֱ� (�̹�������)
     board_writer      VARCHAR2(30)  NOT NULL,      -- �ۼ��� fk (userid)
     board_regDate     DATE  DEFAULT sysdate,       -- �ۼ���
     board_views       NUMBER(6)   DEFAULT 0,       -- ��ȸ��
     board_likes       NUMBER(6)   DEFAULT 0,      -- ���ƿ� 
     board_show        CHAR(1) DEFAULT 'y'
);


-- ���� �Խ��� ��������, y ����
DROP TABLE freeBoard_list;
CREATE TABLE freeBoard_list
AS
    (SELECT r.*, rownum AS rn 
      FROM (
        SELECT * FROM freeBoard_tbl
         WHERE board_show = 'y'
         ORDER BY board_num DESC  
      ) r 
  );
SELECT * FROM freeBoard_list;
-- ��ҿ� -------------------------------------------------------------------------------
-- �����ı� �Խ���
DROP TABLE reviewBoard_tbl;
CREATE TABLE reviewBoard_tbl(  
     board_num         NUMBER(7) PRIMARY KEY,           -- �۹�ȣ
     board_category    VARCHAR2(30) DEFAULT '�����ı�',  -- ī�װ� 
     board_title       VARCHAR2(50)  NOT NULL,          -- ������
     board_content     CLOB  NOT NULL,                  -- �۳���
     board_thumnail    VARCHAR2(100),                   -- default ������� �����ȸ�� ������
     board_image       VARCHAR2(100),                   -- �̹�������
     board_writer      VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),-- �ۼ��� 
     board_regDate     DATE  DEFAULT sysdate,           -- �ۼ���
     board_views       NUMBER(6)   DEFAULT 0,           -- ��ȸ��
     board_heart       NUMBER(6)   DEFAULT 0,           -- ���ƿ� 
     board_show        CHAR(1) DEFAULT 'y'
);

-- �����ı� �Խ��� ��Ʈ(toggle)
DROP TABLE heart_reviewBoard_tbl;
CREATE TABLE heart_reviewBoard_tbl(  
     heart_num          NUMBER(6) PRIMARY KEY,
     board_num          NUMBER(7) REFERENCES reviewBoard_tbl(board_num),    -- �۹�ȣ
     board_category     VARCHAR2(30) DEFAULT '�����ı�',                     -- ī�װ�
     userID             VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),   -- �ۼ��� 
     heart              CHAR(1)   DEFAULT 1                                 -- ���ƿ�
);

-- �����ı�Խ��� ���
DROP TABLE reviewComment_tbl;
CREATE TABLE reviewComment_tbl(  
    comment_num     NUMBER(7) PRIMARY KEY,      -- PK, ��� �Ϸù�ȣ
    board_num       NUMBER(7) REFERENCES reviewBoard_tbl(board_num),                   -- FK, �Խñ� ��ȣ
    board_category  VARCHAR2(30) DEFAULT '�����ı�',
    userID          VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),       -- �ۼ���
    content         CLOB  NOT NULL,              -- �۳���
    regDate         Date  DEFAULT sysdate       -- �����
);

-- ���� �Խ���
DROP TABLE freeBoard_tbl;
CREATE TABLE freeBoard_tbl(  
     board_num         NUMBER(7) PRIMARY KEY,           -- �۹�ȣ
     board_category    VARCHAR2(30) DEFAULT '����',  -- ī�װ� 
     board_title       VARCHAR2(50)  NOT NULL,          -- ������
     board_content     CLOB  NOT NULL,                  -- �۳���
     board_thumnail    VARCHAR2(100),                   -- default ������� �����ȸ�� ������
     board_image       VARCHAR2(100),                   -- �̹�������
     board_writer      VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),-- �ۼ��� 
     board_regDate     DATE  DEFAULT sysdate,           -- �ۼ���
     board_views       NUMBER(6)   DEFAULT 0,           -- ��ȸ��
     board_heart       NUMBER(6)   DEFAULT 0,           -- ���ƿ� 
     board_show        CHAR(1) DEFAULT 'y'
);

-- ���� �Խ��� ��Ʈ(toggle)
DROP TABLE heart_freeBoard_tbl;
CREATE TABLE heart_freeBoard_tbl(  
     heart_num          NUMBER(6) PRIMARY KEY,
     board_num          NUMBER(7) REFERENCES freeBoard_tbl(board_num),      -- �۹�ȣ
     board_category     VARCHAR2(30) DEFAULT '����',                         --ī�װ�
     userID             VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),   -- �ۼ��� fk (userid)
     heart              CHAR(1)   DEFAULT 1                                 -- ���ƿ�
);
SELECT * FROM reviewComment_tbl;
-- �����Խ��� ���
DROP TABLE freeComment_tbl;
CREATE TABLE freeComment_tbl(  
    comment_num     NUMBER(7)  PRIMARY KEY,      -- PK, ��� �Ϸù�ȣ
    board_num       NUMBER(7) REFERENCES freeBoard_tbl(board_num),                   -- FK, �Խñ� ��ȣ
    board_category  VARCHAR2(30) DEFAULT '����',
    userID          VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),       -- �ۼ���
    content         CLOB  NOT NULL,              -- �۳���
    regDate         Date  DEFAULT sysdate       -- �����
);
-- ������ -------------------------------------------------------------------------------
DROP TABLE mvc_customer_tbl  CASCADE CONSTRAINTS;
CREATE TABLE mvc_customer_tbl(
   userid     VARCHAR2(20)    PRIMARY KEY,    -- ID
   password   VARCHAR2(20)    NOT NULL,          -- ��й�ȣ
   username   VARCHAR2(20)    NOT NULL,          -- �̸�
   birthday   DATE            NOT NULL,          -- �������    
   address    VARCHAR2(50)    NOT NULL,          -- �ּ�
   hp         VARCHAR2(13),                      -- �ڵ���      
   email      VARCHAR2(50)    NOT NULL,          -- �̸���
   regDate    TIMESTAMP       DEFAULT sysdate    -- ������
); 

INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email)
VALUES('hong', 'h1234', 'ȫ�浿', '2000/01/01','����� ������ ��ġ��', '010-1111-2222','hong@gmail.com');
commit;

SELECT * FROM mvc_customer_tbl;
-- ���ؼ� -------------------------------------------------------------------------------

CREATE TABLE show_tbl(                                                          -- ���� ���̺�
    showNum         NUMBER(6)      PRIMARY KEY,                                 -- ������ȣ
    showName        VARCHAR2(150)   NOT NULL,                                   -- ������
    showPlace       VARCHAR2(150)   NOT NULL,                                   -- �������                                     
    showPrice       NUMBER(20)     ,                                            -- 1�Ŵ� ����
    curCapacity     NUMBER(20)     DEFAULT 0 ,                                  -- �������ο�
    maxCapacity     NUMBER(20)     DEFAULT 50,                                  -- �ִ�����ο�
    showDay         Date           NOT NULL,                                    -- ������¥
    showCHK         char           DEFAULT 'N'                                  -- �����Ⱓ Ȱ��ȭ ����
);
CREATE SEQUENCE show_tbl_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;  
    