# Book Management CNA

MSA(마이크로서비스 아키텍처) 기반의 도서 출판 관리 플랫폼입니다.
작가 등록부터 출판 심사, 구독자 관리, DALL-E 3 기반 도서 표지 자동 생성까지 전체 출판 워크플로우를 지원합니다.

---

## 시스템 아키텍처

```
[Frontend: React.js]
        │
        ▼
[API Gateway: Spring Cloud Gateway :8088]
        │
   ┌────┼────┬────────┐
   ▼    ▼    ▼        ▼
Author  Sub  Service  Admin
  └──────────────────────┘
           │ Kafka (Event-driven)
           ▼
       Dashboard
```

---

## 주요 기능

- **작가 관리** : 작가 등록, 출판 신청 및 상태 관리
- **구독자 관리** : 구독자 가입, 포인트, 도서 구독
- **출판 서비스** : 도서 등록, 베스트셀러 관리
- **관리자** : 출판 승인/반려 처리
- **대시보드** : 이벤트 기반 실시간 현황 집계
- **AI 표지 생성** : DALL-E 3 API를 활용한 도서 표지 자동 생성

---

## 기술 스택

![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)
![AWS](https://img.shields.io/badge/AWS_EKS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)

---

## 실행 방법

**1. Kafka 실행**
```bash
cd infra
docker-compose up
```

**2. 백엔드 마이크로서비스 실행**

각 서비스 디렉토리(`author management`, `subscribers management`, `service`, `admin`) 내부 README 참고

**3. API Gateway 실행**
```bash
cd gateway
mvn spring-boot:run
```

**4. 프론트엔드 실행**
```bash
cd frontend
npm install
npm run serve
```

브라우저에서 `localhost:8088` 접속
