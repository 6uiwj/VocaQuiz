# 📚 VocaQuiz


영단어를 재미있게 학습할 수 있는 퀴즈 웹 애플리케이션입니다.  
Spring Boot 백엔드와 Vanilla JavaScript 프론트엔드로 구성되어 있습니다.

---

## 🛠 기술 스택

| 구분 | 기술 |
|------|------|
| Backend | Java, Spring Boot, Gradle |
| Frontend | JavaScript, HTML, CSS |

---

## 📁 프로젝트 구조

```
VocaQuiz/
├── src/
│   ├ main
│   │  ├ java
│   │  │   └ EnglishQuiz
│   │  │         ├ exception
│   │  │         │     └ DataNotFoundException.java
│   │  │         ├ quiz
│   │  │         │   ├ DataType.java
│   │  │         │   ├ QuizController.java
│   │  │         │   ├ QuizRepository.java
│   │  │         │   ├ QuizService.java
│   │  │         │   ├ Vocabulary.java
│   │  │         │   └ WordDto.java
│   │  │         └ setting
│   │  │              ├ QuizData.java
│   │  │              ├ SettingController.java
│   │  │              ├ SettingService.java
│   │  │              └ WordApiController.java
│   │  └── resources/
│   │           ├── static/    # JS, CSS 정적 파일
│   │           │      ├ quiz.css
│   │           │      ├ quiz.js
│   │           │      ├ selectQuizDay.js
│   │           │      ├ setting.css
│   │           │      ├ setting.js
│   │           │      ├ solveTheQuiz.js
│   │           │      └ style.css
│   │           └── templates/ # HTML 템플릿
│   │                  ├ header.html
│   │                  ├ index.html
│   │                  ├ layout.html
│   │                  ├ quizType.html
│   │                  ├ selectQuizDay.html
│   │                  ├ setting.html
│   │                  ├ solveTheQuiz.html
│   │                  └ wordList.html
│   └── test/
│         └ com
│            └ EnglishQuiz
│                  └ getDayOfWordList.java
├── build.gradle
├── gradlew
└── settings.gradle
```

---

## 🚀 실행 방법

### 요구 사항

- Java 17 이상
- Gradle (또는 포함된 `gradlew` 사용)

### 로컬 실행

```bash
# 저장소 클론
git clone https://github.com/6uiwj/VocaQuiz.git
cd VocaQuiz

# 빌드 및 실행
./gradlew bootRun
```

브라우저에서 `http://localhost:8080` 접속

---

## 🎮 주요 기능
- 메인화면
 <img width="982" height="858" alt="image" src="https://github.com/user-attachments/assets/394b16f8-94fa-4258-a27e-826c70b23df5" />


- 테스트할 영단어 저장 - 날짜별 저장 가능
  - '/' 문자를 이용하여 의미를 여러개 저장할 수 있습니다.
<img width="1468" height="794" alt="image" src="https://github.com/user-attachments/assets/512931b3-ea13-4ffe-8c2f-1edd36ee53b8" />

- 퀴즈풀기
  - 영어 문제&한글 뜻 맞히기와 한글 문제&영단어 맞히기 두가지 퀴즈를 제공합니다.   
<img width="1149" height="846" alt="image" src="https://github.com/user-attachments/assets/bcf67a6e-d8b2-4cea-94a7-4f120a3da277" />

  - 저장한 영단어를 Day별로 공부할 수 있습니다.
  <img width="1429" height="862" alt="image" src="https://github.com/user-attachments/assets/89946a8a-1005-473d-9ea1-4b8b2a723166" />
  - 문제 수를 선택할 수 있습니다.
   <img width="1383" height="851" alt="image" src="https://github.com/user-attachments/assets/f0ac9eec-e0ff-4818-aaf2-a93845a9c592" />


   <br/>
      <br/>

  - 퀴즈를 시작하면 문제를 풀 수 있습니다.
  - 문제를 틀릴 경우 하단에 정답을 보여줍니다.

<img width="2728" height="1804" alt="제목 없는 다이어그램-페이지-3 (3)" src="https://github.com/user-attachments/assets/7d085480-7ba7-46a4-8c33-414d3946beb0" />


- 정답 확인 및 점수 집계
  - 퀴즈 종료 후 틀린 단어 목록 출력합니다.
<img width="1048" height="830" alt="image" src="https://github.com/user-attachments/assets/1329a74c-df0c-4e79-9908-c7c3c043031e" />

---



## 🛠️ 향후 업데이트 예정 기능

- 틀린 단어 즐겨찾기 기능 -> 틀린 단어 중 원하는 단어를  즐겨찾기 하여 '내 단어장'에 등록합니다.
- 원하는 DAY의 단어 시험지를 생성하고 출력하는 기능을 제공합니다.(PDF)

---
