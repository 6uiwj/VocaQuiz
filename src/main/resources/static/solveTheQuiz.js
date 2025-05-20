  let quizData = [];
    let currentIndex = 0;
let showNextEnabled = false;

const type = document.getElementById("quizType").value;
const day = document.getElementById("quizDay").value;

    // 1. 서버에서 데이터 받아오기
    fetch(`/quiz/quiz-data?type=${type}&day=${day}`)
        .then(res => res.json())
        .then(data => {
            // Map이 JSON으로 오면 Object로 변환됨
            quizData = Object.entries(data); // [ [key, value], [key, value], ... ]
            showNextQuestion();
        });

    // 2. 문제 보여주기
    function showNextQuestion() {
        if (currentIndex < quizData.length) {
            const question = quizData[currentIndex][0];
            document.getElementById("question").innerText =
                `Q. ${question}`;
            document.getElementById("answerInput").value = "";
            document.getElementById("result").innerText = "";
            document.getElementById("nextButton").style.display = "none";
            showNextEnabled = false;

        } else {
            document.getElementById("question").innerText = "퀴즈 끝!";
            document.getElementById("answerInput").style.display = "none";
            document.getElementById("checkButton").style.display = "none";
            document.getElementById("nextButton").style.display = "none";
              }
        }

    // 3. 정답 확인
    function checkAnswer() {
        const userAnswer = document.getElementById("answerInput").value.trim();
        const correctAnswer = quizData[currentIndex][1];
    // 여러 정답 중 하나라도 맞으면 정답 처리
    const acceptableAnswers = correctAnswer.split("/").map(a => a.trim());

        if (acceptableAnswers.includes(userAnswer)) {
            document.getElementById("result").innerText = "정답입니다!";
        } else {
            document.getElementById("result").innerText = `오답입니다. 정답: ${correctAnswer}`;
            }

        document.getElementById("nextButton").style.display = "inline-block";
        showNextEnabled = true;
    }
    // 4. 다음 문제로 이동
    function goToNext() {
        if (showNextEnabled) {
            currentIndex++;
            showNextQuestion();
        }
    }