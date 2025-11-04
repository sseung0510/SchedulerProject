# SchedulerProject API 명세서
- Base URL: `http://localhost:8080`
- Content-Type: `application/json`
- 
## 1. 일정 생성 (POST)
- **엔드포인트**: `/scheduler`
- **설명**: 새로운 일정 생성

### 요청 예시
```json
{
  "title": "1",
  "content": "4",
  "userName": "choi",
  "userPwd": "05101"
}
```

### 응답 예시
```json
{
"id": 1,
"title": "회의",
"content": "백엔드 점검",
"userName": "choi",
"createdAt": "2025-11-04T10:00:00",
"updatedAt": "2025-11-04T10:00:00"
}
```

## 2. 전체/사용자별 일정 조회 (GET)
- **엔드포인트**: `/scheduler`
- **설명**: 전체 목록 또는 작성자명으로 조회
- **쿼리**: userName(선택)

### 요청 예시
```
GET /scheduler
GET /scheduler?userName=choi
```

### 응답 예시
```json
[
  {
    "id": 1,
    "title": "회의",
    "content": "백엔드 점검",
    "userName": "choi",
    "createdAt": "2025-11-04T10:00:00",
    "updatedAt": "2025-11-04T10:00:00"
  }
]
```

## 3. 단 일정 조회 (GET)
- **엔드포인트**: `/scheduler/{scheduleId}`
- **설명**: ID로 단건 조회
- **쿼리**: userName(선택)

### 응답 예시
```json
{
  "id": 1,
  "title": "회의",
  "content": "백엔드 점검",
  "userName": "choi",
  "createdAt": "2025-11-04T10:00:00",
  "updatedAt": "2025-11-04T10:00:00"
}
```

## 4. 일정 수정 (PUT)
- **엔드포인트**: `/scheduler/{scheduleId}`
- **설명**: 기존 일정을 수정한다.  
  작성 시 입력했던 `userPwd`가 동일해야 수정이 가능하다.

### 요청 예
```json
{
  "title": "주간 회의",
  "content": "스프린트 계획",
  "userName": "choi",
  "userPwd": "05101"
}
```

### 응답 예시
```json
{
  "id": 1,
  "title": "주간 회의",
  "content": "스프린트 계획",
  "userName": "choi",
  "createdAt": "2025-11-04T10:00:00",
  "updatedAt": "2025-11-04T11:00:00"
}
```

## 4. 일정 삭제 (DELETE)
- **엔드포인트**: `/scheduler/{scheduleId}`
- **설명**: 지정한 ID의 일정을 삭제한다.
  등록 시 입력한 userPwd가 일치해야 삭제된다.

### 요청 예시
```json
{
  "userName": "choi",
  "userPwd": "05101"
}
```

### 응답 예시
```
204 No Content
```
