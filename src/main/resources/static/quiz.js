document.addEventListener("DOMContentLoaded", function () {

    const quizType = document.querySelector("#selectQuizType"); //퀴즈풀기버튼
    const closeBtn = document.querySelector("#close-popup"); //팝업닫기버튼

    const quizKr = document.querySelector("#quizKr"); //한글로 풀기
    const quizEng = document.querySelector("#quizEng"); //영어로 풀기

        function openPopup() { //팝업 열기
        document.getElementById("quiz-popup").style.display = "flex";
        }

        function closePopup() { //팝업 닫기
        document.getElementById("quiz-popup").style.display = "none";
        }


        function selectQuizType(type) { //퀴즈유형이 선택되면 페이지 이동
            location.href= `/quiz?type=${type}`; //이동할 URL주소(type값을 저장해서 이동)
        }

        quizType.addEventListener("click", function(e) { //'퀴즈풀기'버튼 누르면 팝업열림
        e.preventDefault();
        openPopup();
        });

        closeBtn.addEventListener("click", function() { //'닫기'버튼 누르면 팝업 닫힘
        closePopup();
        });

        quizKr.addEventListener("click", function() { //한글로풀기 클릭하면 type=korean 가지고 페이지 이동
            selectQuizType('korean');
        });
        quizEng.addEventListener("click", function() { //영어로풀기 클릭하면 type=english 가지고 페이지 이동
            selectQuizType('english');
        });

});