document.addEventListener("DOMContentLoaded", () => {

    const dayButtons = document.querySelectorAll(".day-choose-button"); //날짜선택버튼
    const quizPopup = document.getElementById("nbr-of-quiz-popup");  //문제수 선택 팝업창
    const quizNbrSpan = document.getElementById("nbr-of-quiz-display"); //총 문제수 span

    const urlParams = new URLSearchParams(window.location.search); //현재 URL의 쿼리스트링을 가져와서 키와 값 쌍으로 저장 (http://localhost:8080/quiz?type=korean)
    const quizType = urlParams.get("type");  //가져온 URL 쿼리스트링에서 type 저장

    let selectedDay = null;

    //팝업 열기 함수
    function openPopup() {
    quizPopup.style.display = "flex";
    }

    //날짜를 클릭하면 팝업이 열리는 이벤트
    dayButtons.forEach(button => {
        button.addEventListener("click", async (e) => { //서버에서 데이터를 받아올 때까지 기다려야 하므로 async 사용
            e.preventDefault();

            //해당 날짜에 단어가 몇개인지 가져오기 위해 날짜 저장
            selectedDay = button.getAttribute("data-day"); //사용자 정의 값으로 html에서 지정해 줬던 data-day 값 가져옴
            openPopup(); //팝업 열기

            // 서버에서 해당 날짜+유형에 맞는 퀴즈 개수 받아오기
           try {
           //해당 값에 해당하는 데이터를 받아오기 위해 쿼리스트링을 생성해서 서버로 전송 (퀴즈타입(한글?영어?)와 선택날짜(day1))
            const response = await fetch(`/quiz/quiz-data?type=${quizType}&day=${selectedDay}`); // '/quiz/quiz-data
            if(!response.ok) throw new Error("데이터 불러오기 실패");
            //받아온 데이터를 JSON형태로 변환후 quizData에 담음
            const quizData = await response.json(); // { "깎다": "mow", ... }
            const quizCount = Object.keys(quizData).length; //받은 데이터의 총 개수를 받음

            quizNbrSpan.textContent = `총 문제 수: ${quizCount}문제`; //화면에 출력
           } catch (error) {
            quizNbrSpan.textContent = "문제 수를 불러오는 데 실패했습니다.";
            console.error(error);
           }
        });
    });

	//문제 개수 선택 버튼
    const countButtons = document.querySelectorAll(".quiz-count-btn");

    //각 버튼이 눌렸을 때의 이벤트
    countButtons.forEach(button => {
        button.addEventListener("click", () => {
            const n = button.getAttribute("data-count"); //사용자 정의로 준 지정된 버튼 값 (10문제? 20문제? 전체문제?)

                    if(!selectedDay || !quizType) {
                        alert("날짜나 유형이 선택되지 않았습니다.");
                        return;
                    }
            let quizCount = (n === 'all') ? 0 : parseInt(n, 10); //만약 n(전체문제)면 0으로 바꿈(전부 숫자로 처리해주기 위해 문자를 숫자로)
            let url = `/quiz/${quizType}/${selectedDay}?n=${quizCount}`; //url지정
            location.href = url; //해당 url로 페이지 이동
        });
    });
});