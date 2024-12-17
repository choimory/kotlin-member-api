# 개요

- 회원 API를 개발하며 백엔드로 다룰수 있는 많은 내용들을 다뤄보자

# 인증

- 회원 가입 및 로그인 패스워드 암호화
- CORS
- bcrypt
    - Spring security
- JWT or Session
    - JJWT
- OAuth2
    - Spring security

# 권한

- 회원 권한별 접근처리
- Spring security

# 타입

- 타입을 DB화하여 관리할 수도 있고, 코드의 enum으로 관리할 수도 있다
- 회원 역할(DB), 이미지 타입(enum)
- Java Kotlin enum

# 파일 입출력, 스토리지, CDN

- 회원 프로필 사진, 게시물 첨부 이미지 파일 업로드/다운로드 구현
- Spring MultiPartFile
- 스토리지 저장
- 리사이징
- CDN

# 이메일 인증

- 회원가입 인증메일
- Java mail sender
- Redis
    - 인증번호를 TTL(time to live)걸어서 저장시키면 시간내에 안올시 세션 죽음

# 배치

- 이용정지회원 정지해제 배치처리
- Spring batch

# 연관 관계

- 1:1 -
- 1:N, N:1 - 회원 정지내역, 게시물 이미지
- N:N - 회원 팔로우, 회원 역할(1:N 설계도 가능하긴 함), 역할 권한, 게시물 좋아요

# 계층형 테이블

- 게시물 댓글

# 복합키 엔티티

- 다대다 중간 테이블

# Redis

- 캐싱
- 세션
- 이메일, OTP 인증번호 일정시간 저장
- 로그인 실패횟수 IP 저장
- 활동이력 저장

# MongoDB

- TODO

# 웹소켓

- 채팅

# 폴링

- 알림

# 그 외

- 식별자로 tsid 사용 → UUIDv7으로 변경 (uuidv7도 시간정렬 지원하며 nestjs에서 검증 등 지원함)
- user와 role 설계
    - 역할과 권한의 관계는 케이스마다 다른데, 보통 다대다가 일반적이며 여기서도 다대다로 설계하기로 함
    1. user와 role을 N:N으로 설계함
        - 왜 다대다로 설계하는가
        - 역할은 항상 or가 아닌 and인 케이스도 나오기 때문
        - member||admin이 아닌 member&&admin이 나올수 있고 member&&moderator도 나올수 있음
        - 학생이자 게이머일수 있고, 가수이면서 알바생일수도 있다.
        - 역할들을 모듈화해서, 해당하는 역할들을 다 가지는것
        - role 중심의 설계
        - 해당 user의 role이 해당 액션을 수행할 수 있는 role을 보유하고 있는지를 확인함
    2. user와 role을 1:1로 설계함
        - 무조건 단일한 역할만 필요할때
        - role이 계층적일때 (guest<member<admin…)
        - 추후 변경시 유연히 대처하지 못할수 있고 확장이 곤란할 수 있음
        - permission 중심의 설계
        - 해당 user의 role이 해당 액션을 수행할 permisson을 가지고 있는지를 확인함
    - permission 설계
        - member의 권한이 123, member plus의 권한이 1234, admin의 권한이 1234567일때
        1. permisson을 member(123), member plus(4), admin(567)로 저장한다
            - user와 role이 N:N일때만 가능
            - e.g. member123+member_plus+admin567을 모두 저장해서 1234567을 모두 가지게 함
        2. permission을 member(123), member plus(1234), admin(1234567)로 저장한다
            - user와 role이 1:1, N:N일때 모두 가능
            - member123 + member_plus1234 + admin1234567을 저장해서 1234567을 모두 가지게함

# 엔티티

- Member 회원(user)
    - id pk
    - email uk 이메일
    - nickname uk 닉네임
    - password notnull 비밀번호
    - image 프로필 사진
    - introduce 소개글
- MemberSuspension 회원 정지내역 1:N
    - id pk
    - member_id fk
    - reason 사유
    - suspended_at notnull 정지일
    - suspended_to notnull 해지일
- Classification 역할(role)
    - id pk
    - name uk 역할명 (enum MEMBER, ADMIN…)
    - describe 설명
- MemberClassification 회원 역할 N:N
    - member_id pk
    - classification_id pk
    - status (enum ACTIVE, REVOKE…)
- Allowance 권한(permission)
    - id pk
    - action (enum WRITE, MODIFY, DELETE, NO_AD…)
- ClassificationAllowance 역할 권한 N:N
    - classification_id pk
    - allowance_id pk
    - menu (enum ARTICLE, MEMBER, COMMENT…)
- Follow 팔로우 회원N:N
    - follower_id pk
    - followee_id pk
    - status notnull (enum WAIT, ACCEPT, DENY..)
- Article  게시글
    - id pk
    - member_id fk
    - title
    - content
- ArticleImage 게시글 이미지 1:N
    - id pk
    - article_id fk
    - type (enum IMAGE, THUMB_NAIL)
    - original_name
    - name
    - path
    - size
    - resize_name
    - resize_path
    - resize_size
- ArticleComment 게시글 댓글 1:N hierarchy
    - id pk
    - article_id fk
    - member_id fk
    - content
- Todo 개인 TODO / MongoDB