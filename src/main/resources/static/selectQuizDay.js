//퀴즈 유형을 선택후 DAY선택하면 퀴즈풀기 페이지로 이동

function selectQuizType(type) {
    location.href= `/quiz?type=${type}`;
}

document.addEventListener("DOMContentLoaded", function () {
 const buttons = document.querySelectorAll(".day-choose-button");

    // 현재 URL의 쿼리스트링에서 type 값을 가져옴
    const urlParams = new URLSearchParams(window.location.search); //현재 URL의 쿼리스트링을 가져와서 키와 값 쌍으로 저장
    const type = urlParams.get("type"); //저장된 쿼리스트링 요소 중 키가 type은 요소의 값 가져오기

    buttons.forEach(button => { //각 날짜 버튼마다
        button.addEventListener("click", function (e) {
            e.preventDefault(); //클릭되면 링크 넘어가는 기본 a 태그 동작 제거
            const day = button.getAttribute("data-day"); //html의 data-day 정보 받아오기

            if (type && day) { //퀴즈타입과 날짜가 존재하면
                // 주소로 이동 -> 주소 /quiz/유형/날짜 형식으로 지정
                window.location.href =  `/quiz/${type}/${day}`;
            } else {
                alert("타입 또는 데이 값이 없습니다.");
            }
        });
    });

});