# 🕐 실시간 웨이팅 시스템

## 📌 프로젝트 소개
매장 방문 시 발생하는 대기열을 실시간으로 관리하기 위한 웹 서비스입니다.  
사용자는 웨이팅을 등록하고, 관리자는 대기 상태를 실시간으로 확인 및 제어할 수 있습니다.

---

## 🛠 기술 스택
- Backend: Spring Boot
- Database: MariaDB
- ORM: JPA (Hibernate)

---

## 🎯 주요 기능
- 웨이팅 등록
- 순번 자동 생성
- 대기 상태 관리 (WAITING / CALLED / ENTERED / CANCELLED)
- 관리자 호출 기능

---

## 🧠 설계 포인트
- 매장 단위로 웨이팅 분리
- 동시 요청 시 순번 충돌 방지
- 상태 기반 데이터 흐름 설계

---

## 🚀 실행 방법
```bash
./gradlew bootRun
