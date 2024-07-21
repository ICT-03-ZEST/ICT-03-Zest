DROP SEQUENCE show_tbl_seq;
DROP TABLE show_tbl CASCADE CONSTRAINTS;

CREATE TABLE show_tbl(                                                          -- 공연 테이블
    showNum         NUMBER(6)      PRIMARY KEY,                                 -- 공연번호
    showName        VARCHAR2(150)   NOT NULL,                                   -- 공연명
    showPlace       VARCHAR2(150)   NOT NULL,                                   -- 공연장소                                     
    showPrice       NUMBER(20)     ,                                            -- 1매당 가격
    showTime        NUMBER(5)      ,                                            -- 공연시간
    showAge         NUMBER(3)      ,                                            -- 관람연령
    showBene        VARCHAR2(150)  ,                                            -- 혜택
    curCapacity     NUMBER(20)     DEFAULT 0 ,                                  -- 현수용인원
    maxCapacity     NUMBER(20)     DEFAULT 50,                                  -- 최대수용인원
    showDay         Date           NOT NULL,                                    -- 공연날짜
    showImage       VARCHAR2(150),                                              -- 이미지 이름
    showCHK         char           DEFAULT 'Y'                                  -- 공연기간 활성화 유무
);
CREATE SEQUENCE show_tbl_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;  
--더미 데이터 입력-----------------------------------------------------
DECLARE
    start_date DATE := TO_DATE('2024-06-01', 'YYYY-MM-DD'); -- 6월 1일
    end_date DATE := TO_DATE('2024-08-31', 'YYYY-MM-DD'); -- 8월 31일
    current_day DATE;

BEGIN
    -- 데이터 삽입
    current_day := start_date;

    WHILE current_day <= end_date LOOP
        FOR j IN 1..5 LOOP -- 각 날짜에 5개의 공연 추가
            -- showName을 공연마다 3일씩 지속되도록 설정
            FOR k IN 0..2 LOOP
                INSERT INTO show_tbl(showNum, showName, showPlace, showPrice, curCapacity, maxCapacity, showDay, showCHK, showBene, showAge, showTime)
                VALUES(
                    show_tbl_seq.NEXTVAL,
                    '공연명' || (5 * (current_day - start_date) + j),  -- 공연명
                    '잠실실내체육관',
                    200000 + (j * 100000),  -- 가격
                    CASE
                        WHEN EXTRACT(MONTH FROM current_day) = 6 THEN 150 -- 6월
                        WHEN EXTRACT(MONTH FROM current_day) = 7 THEN 0   -- 7월
                        WHEN EXTRACT(MONTH FROM current_day) = 8 THEN 350 -- 8월
                    END,
                    35000,
                    current_day + k, -- 연속된 날짜 지정
                    'Y',
                    '무이자할부', -- showBene 값
                    7,             -- showAge 값
                    120            -- showTime 값
                );
            END LOOP;
        END LOOP;
        current_day := current_day + 1; -- 다음 날짜로 이동
    END LOOP;
END;
/
---------------------------------------------
SELECT * FROM show_tbl ORDER BY SHOWNUM;
---------------------------------------------
-- 메인화면 달력에 표기될 리스트(공연명)
--      공연번호,공연명    ,현수용인원   ,최대수용인원  , 공연일 ,활성화체크
SELECT showNum, showName, curCapacity, maxCapacity, showDay, showCHK 
FROM show_tbl 
WHERE EXTRACT(MONTH FROM showDay) = EXTRACT(MONTH FROM SYSDATE)
AND EXTRACT(YEAR FROM showDay) = EXTRACT(YEAR FROM SYSDATE)
AND SHOWCHK LIKE 'Y'
ORDER BY SHOWNUM;

SELECT showNum, showName, curCapacity, maxCapacity, showDay, showCHK 
FROM show_tbl 
WHERE LPAD(TO_CHAR(showDay, 'MM'), 2, '0') = LPAD(7, 2, '0') 
AND EXTRACT(YEAR FROM showDay) = EXTRACT(YEAR FROM SYSDATE) 
AND SHOWCHK = 'Y'  
ORDER BY showNum; 

commit;
---------------------------------------------
-- 상세페이지에 표기될 리스트(show_tbl)
--      공연번호,공연명    ,공연장소   ,1매당가격 ,공연시간,관람연령,혜택        ,현수용인원   ,최대수용인원  , 공연일 ,이미지 이름,활성화체크
SELECT showNum, showName,showPlace, showPrice,showTime,showAge,showBene,curCapacity, maxCapacity, showDay,showImage ,showCHK 
FROM show_tbl 
WHERE SHOWCHK LIKE 'Y'
AND showNum = 259 
ORDER BY showNum;

SELECT * FROM show_tbl WHERE shownum = 259 
ORDER BY SHOWNUM;

SELECT * 
FROM show_tbl 
WHERE showName LIKE '공연명259'  
AND SHOWCHK LIKE 'Y'
ORDER BY SHOWNUM;


INSERT INTO show_tbl(showNum, showName,showPlace, showPrice, curCapacity, maxCapacity, showDay, showCHK)
VALUES(
    show_tbl_seq.NEXTVAL,
    '공연명1',
    '잠실실내체육관',
    200000,
    LEAST(NVL((SELECT MAX(curCapacity) + 1 FROM show_tbl), 1), 350),  -- curCapacity가 maxCapacity를 초과하지 않도록 조정
    350,
    SYSDATE,  -- 현재 날짜를 직접 사용
    'Y'
);

update show_tbl 
set showImage = '이문세';

SELECT * FROM show_tbl ORDER BY SHOWNUM;
SELECT * FROM show_tbl where showNum=676 ORDER BY SHOWNUM;

INSERT INTO Reservation_tbl(resNum, resName, customer, customerPhon, customerAddr, place, deadline, maxDeadline, resDay, showCHK)
VALUES(Reservation_seq.NEXTVAL, '티켓명1', '고객명1', '010-1234-5678', '고객 주소', 200000, 500, 1500, TO_DATE(TO_CHAR(SYSDATE, 'YYYY_MM_DD_DY'), 'YYYY-MM-DD-DY'), 'Y');

CREATE TABLE MVC_CUSTOMER_TBL (
    USERID      VARCHAR2(20char)    NOT NULL  PRIMARY KEY,
    PASSWORD    VARCHAR2(20char)    NOT NULL,
    USERNAME    VARCHAR2(20char)    NOT NULL,
    BIRTHDAY    DATE                NOT NULL,
    ADDRESS     VARCHAR2(50char)    NOT NULL,
    HP          VARCHAR2(13char)            ,
    EMAIL       VARCHAR2(50char)    NOT NULL,
    REGDATE     TIMESTAMP           
);

-- 샘플 데이터 삽입
INSERT INTO MVC_CUSTOMER_TBL (USERID, PASSWORD, USERNAME, BIRTHDAY, ADDRESS, HP, EMAIL, REGDATE) VALUES 
('user1', 'password1', '홍길동', TO_DATE('1990-01-01', 'YYYY-MM-DD'), '서울특별시 강남구', '010-1234-5678', 'hong@gmail.com', SYSTIMESTAMP);

INSERT INTO MVC_CUSTOMER_TBL (USERID, PASSWORD, USERNAME, BIRTHDAY, ADDRESS, HP, EMAIL, REGDATE) VALUES 
('user2', 'password2', '이순신', TO_DATE('1985-05-15', 'YYYY-MM-DD'), '부산광역시 해운대구', '010-8765-4321', 'lee@gmail.com', SYSTIMESTAMP);

INSERT INTO MVC_CUSTOMER_TBL (USERID, PASSWORD, USERNAME, BIRTHDAY, ADDRESS, HP, EMAIL, REGDATE) VALUES 
('user3', 'password3', '김유신', TO_DATE('1978-09-10', 'YYYY-MM-DD'), '경기도 성남시', '010-1357-2468', 'kim@gmail.com', SYSTIMESTAMP);

SELECT * FROM MVC_CUSTOMER_TBL;
COMMIT;

DROP SEQUENCE show_Reservation_seq;
DROP TABLE show_Reservation CASCADE CONSTRAINTS;
-- 공연에 대한 예매정보 저장하는 테이블
CREATE TABLE show_Reservation (
    show_ResId  NUMBER(6)           PRIMARY KEY,
    showNum     NUMBER(6)           REFERENCES show_tbl(showNum),
    userID      VARCHAR2(20char)    REFERENCES MVC_CUSTOMER_TBL(USERID),
    totalPrice  NUMBER(20)
);

-- show_seat_grade 테이블을 위한 시퀀스
CREATE SEQUENCE show_Reservation_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
COMMIT;

INSERT INTO show_Reservation (show_ResId, showNum, userID,  totalPrice) 
VALUES (show_Reservation_seq.NEXTVAL, 561, 'user1', 500000);

SELECT * FROM show_Reservation order by show_resid;
SELECT * FROM show_tbl order by showNum;

UPDATE show_tbl 
SET curCapacity = curCapacity+1  
WHERE shownum = 558;

commit;
ROLLBACK;
SELECT * FROM show_tbl where showNum = 558 order by showNum;