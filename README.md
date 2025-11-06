# Scheduler Project

Spring Boot 학습을 위해 진행한 일정 관리 프로젝트입니다.  
일정(Schedule)과 댓글(Reply)에 대한 CRUD 기능을 구현하며 JPA, 예외 처리, 유효성 검증을 실습했습니다.

---

### SchedulerProject API 명세서
https://documenter.getpostman.com/view/49694924/2sB3WqvLtQ

### ERD
![img.png](img.png)

---

## 개발 환경
| 항목 | 내용 |
|------|------|
| Language | Java (JDK 17+) |
| Framework | Spring Boot |
| Database | H2 / MySQL |
| Build Tool | Gradle |
| IDE | IntelliJ IDEA |
| 실행 방식 | REST API (Postman) |

---

## 주요 기능 요약

### Lv1. 일정 생성
- 제목, 내용, 작성자명, 비밀번호, 작성/수정일 저장
- ID 자동 생성, 작성일·수정일은 JPA Auditing으로 관리
- 응답 시 비밀번호 제외

### Lv2. 일정 조회(단건 + 다건)
- 전체 일정 조회
    - 작성자명으로 일정 목록 조회 가능 (없으면 전체 조회)
    - 수정일 기준 내림차순 정렬
- 단건 일정 조회
    - 일정의 고유 ID로 조회
- 응답 시 비밀번호 제외

### Lv3. 일정 수정
- 제목, 작성자명만 수정 가능
- 비밀번호 검증 후 수정
- 수정 시 수정일 자동 갱신

### Lv4. 일정 삭제
- 비밀번호 검증 후 삭제

### Lv5. 댓글 생성
- 일정에 댓글 작성
- 내용, 작성자명, 비밀번호, 작성/수정일 저장
- 일정당 최대 10개 제한

### Lv6. 일정 단건 조회 업그레이드
- 일정 단건 조회 시 댓글 목록 함께 반환

### Lv7. 유효성 검증
- 제목(≤30자), 내용(≤200자), 댓글(≤100자)
- 작성자명, 비밀번호 필수
- 비밀번호 불일치 시 예외 발생

---

## 사용 기술
- Spring Boot (Web, JPA, Validation)
- Lombok
- JPA Auditing
- @RestControllerAdvice
- Gradle, H2 / MySQL