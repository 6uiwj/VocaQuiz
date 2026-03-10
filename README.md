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

- 테스트할 영단어 저장
- Day별 영어 단어 퀴즈 출제
- 정답 확인 및 점수 집계
- 퀴즈 종료 후 틀린 단어 목록 출력

---



## 🛠️ 향후 업데이트 예정 기능

- 틀린 단어 즐겨찾기 -> 내 단어장에 등록
- 시험지 생성(PDF)

---