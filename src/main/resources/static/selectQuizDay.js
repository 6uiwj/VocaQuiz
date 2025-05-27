////퀴즈 유형을 선택후 DAY선택하면 퀴즈풀기 페이지로 이동
//
//function selectQuizType(type) {
//    location.href= `/quiz?type=${type}`;
//}

//document.addEventListener("DOMContentLoaded", function () {
// const buttons = document.querySelectorAll(".day-choose-button");
//
//    // 현재 URL의 쿼리스트링에서 type 값을 가져옴
//    const urlParams = new URLSearchParams(window.location.search); //현재 URL의 쿼리스트링을 가져와서 키와 값 쌍으로 저장
//    const type = urlParams.get("type"); //저장된 쿼리스트링 요소 중 키가 type은 요소의 값 가져오기
//
//    buttons.forEach(button => { //각 날짜 버튼마다
//        button.addEventListener("click", function (e) {
//            e.preventDefault(); //클릭되면 링크 넘어가는 기본 a 태그 동작 제거
//            const day = button.getAttribute("data-day"); //html의 data-day 정보 받아오기
//
//            if (type && day) { //퀴즈타입과 날짜가 존재하면
//                // 주소로 이동 -> 주소 /quiz/유형/날짜 형식으로 지정
//                window.location.href =  `/quiz/${type}/${day}`;
//            } else {
//                alert("타입 또는 데이 값이 없습니다.");
//            }
//        });
//    });
//
//
//});


document.addEventListener("DOMContentLoaded", () => {
    const dayButtons = document.querySelectorAll(".day-choose-button");
    const quizPopup = document.getElementById("nbr-of-quiz-popup");
    const quizNbrSpan = document.getElementById("nbr-of-quiz-display");

    const urlParams = new URLSearchParams(window.location.search); //현재 URL의 쿼리스트링을 가져와서 키와 값 쌍으로 저장
    const quizType = urlParams.get("type");

    let selectedDay = null;

    function openPopup() {
    quizPopup.style.display = "flex";
    }

    function closePopup() {
    quizPopup.style.display = "none";
    }

    dayButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            e.preventDefault();

            selectedDay = button.getAttribute("data-day");
            openPopup();

            // 서버에서 해당 날짜+유형에 맞는 퀴즈 개수 받아오기
           try {
            const response = await fetch(`/quiz/quiz-data?type=${quizType}&day=${selectedDay}`);
            if(!response.ok) throw new Error("데이터 불러오기 실패");

            const quizData = await response.json(); // { "깎다": "mow", ... }
            const quizCount = Object.keys(quizData).length;

            quizNbrSpan.textContent = `총 문제 수: ${quizCount}문제`;
            //quizPopup.style.display = "block";
           } catch (error) {
            quizNbrSpan.textContent = "문제 수를 불러오는 데 실패했습니다.";
            //quizPopup.style.display = "block";
            console.error(error);
           }
        });
    });

    window.selectQuizNbr = function(n) {
        if(!selectedDay || !quizType) {
            alert("날짜나 유형이 선택되지 않았습니다.");
            return;
        }

       //all -> 0으로 변환해서 처리 (문제수는 다 숫자로 통일해서 처리하기 위해)
       let quizCount = null;

       if(n === 'all') {
        quizCount = 0;
       } else {
        quizCount = parseInt(n,10);
       }

       let url = `/quiz/${quizType}/${selectedDay}?n=${quizCount}`;
       location.href = url;
    }
});