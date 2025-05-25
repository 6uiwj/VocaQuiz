let quizData = [];
let currentIndex = 0;
let showNextEnabled = false;
let incorrectAnswerList = [];

const type = document.getElementById("quizType").value; //영어맞히기?한글맞히기?
const day = document.getElementById("quizDay").value; //며칠 데이터?
const quizEnd = document.getElementById("quizEnd");

    // 1. 서버에서 JSON 데이터 받아오기
    fetch(`/quiz/quiz-data?type=${type}&day=${day}`) //서버에 데이터 요청
        .then(res => res.json())
        .then(data => {
            // Map이 JSON으로 오면 Object로 변환됨
            quizData = Object.entries(data); // [ [key, value], [key, value], ... ]
            showNextQuestion();
        });

    // 2. 문제 보여주기
    function showNextQuestion() {
        if (currentIndex < quizData.length) { //Map 요소가 남아있으면 다음문제 출력
            const span = document.createElement("span");
            span.innerText = `${quizData[currentIndex]}/${quizData.length}`;
            const question = quizData[currentIndex][0]; //map의 0번인덱스 = key 출력
            document.getElementById("question").innerText =
                `Q. ${question}`; //문제에 map의 key 출력
            document.getElementById("answerInput").value = ""; //정답입력 칸 비우기
            document.getElementById("result").innerText = ""; //정답칸 비우기
            document.getElementById("nextButton").style.display = "none"; //다음문제 버튼 숨기기
            showNextEnabled = false;

             //진행률 표시
                    const total = quizData.length;
                    const current = currentIndex + 1;
                    document.getElementById("progress").innerText = `${current} / ${total}`;

            document.getElementById("answerInput").style.display = "inline-block";
            document.getElementById("submitButton").style.display = "inline-block";


        } else { //map의 모든 요소를 다돌았을 경우
            document.getElementById("question").innerText = "퀴즈 끝!"; //퀴즈 끝 출력
            document.getElementById("answerInput").style.display = "none";
            document.getElementById("submitButton").style.display = "none";
            document.getElementById("nextButton").style.display = "none";
            document.getElementById("home").style.display="inline-block";

            document.getElementById("result").innerText = "";

            const rightAnswer = quizData.length - incorrectAnswerList.length;
            document.getElementById("score").innerText = `맞힌 문제 수 : ${rightAnswer} / ${quizData.length}`;

            const tableBody = document.querySelector("#incorrectWordList tbody");
            if(incorrectAnswerList.length ===0) {
                const endComment = document.createElement("span");
                endComment.innerText = "축하합니다. 다 맞히셨네요!";
                quizEnd.appendChild(endComment);
            } else {
                document.getElementById("incorrectWordForm").style.display = "block"; // 틀린 문제 목록 폼 보이기

                //틀린 단어 목록 DB에서 가져와 보여주기
                    incorrectAnswerList.forEach((wrongKey, idx) => {
                        const row = document.createElement("tr");

                        const noCell = document.createElement("td");
                        noCell.innerText = idx + 1;

                        const wordCell = document.createElement("td");
                        wordCell.innerText = wrongKey;

                        const meaningCell = document.createElement("td");

                       // quizData에서 [key, value] 찾기
                        const found = quizData.find(([key]) => key === wrongKey);
                        const key = found ? found[0] : "알 수 없음";
                        const value = found ? found[1] : "알 수 없음";

                        if (type === "english") {
                            // 문제: 한글 의미 (key), 정답: 영어 단어 (value)
                            wordCell.innerText = value;
                            meaningCell.innerText = key;
                        } else {
                            // 문제: 영어 단어 (key), 정답: 한글 의미 (value)
                            wordCell.innerText = key;
                            meaningCell.innerText = value;
                        }

                        row.appendChild(noCell);
                        row.appendChild(wordCell);
                        row.appendChild(meaningCell);
                        tableBody.appendChild(row);
                    });
            }

              }
        }

    // 3. 정답 확인
    document.getElementById("quizForm").addEventListener("submit", checkAnswer);

    function checkAnswer(e) {
        e.preventDefault();

        const userAnswer = document.getElementById("answerInput").value.trim(); //정답 input에 입력된 값을 가져옴
        const correctAnswer = quizData[currentIndex][1]; //정답 : map의 value 값
    // 여러 정답 중 하나라도 맞으면 정답 처리
    const acceptableAnswers = correctAnswer.split("/").map(a => a.trim()); //acceptableAnswers는 sting타입 배열임 , 정답에서 "/"기호 기준으로 앞뒤 모두 정답으로 인정

        if (acceptableAnswers.includes(userAnswer)) {

            document.getElementById("result").innerText = "정답입니다!";
        } else {
            document.getElementById("result").innerText = `오답입니다. 정답: ${correctAnswer}`;
            incorrectAnswerList.push(quizData[currentIndex][0]);
            }
        document.getElementById("answerInput").style.display = "none";
        document.getElementById("submitButton").style.display = "none";


        document.getElementById("nextButton").style.display = "inline-block";
        showNextEnabled = true; //다음문제 버튼 생성
    }
    // 4. 다음 문제로 이동
    function goToNext() {
        if (showNextEnabled) { //다음문제버튼이 있으면
            currentIndex++; //map의 index를 하나 더하고
            showNextQuestion(); //다음 문제로 이동
        }
    }

    //다음 문제 이동 버튼 enter키로도 눌리도록 설정
    document.addEventListener("keydown", function (event) {
        if (event.key === "Enter" && showNextEnabled) {
            event.preventDefault();
            goToNext();
        }
    });