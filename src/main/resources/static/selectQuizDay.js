//퀴즈 유형을 선택후 DAY선택하면 퀴즈풀기 페이지로 이동

function selectQuizType(type) {
    location.href= `/quiz?type=${type}`;
}

document.addEventListener("DOMContentLoaded", function () {
 const buttons = document.querySelectorAll(".day-choose-button");

    // 현재 URL에서 type 값을 가져옴
    const urlParams = new URLSearchParams(window.location.search);
    const type = urlParams.get("type");

    buttons.forEach(button => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            const day = button.getAttribute("data-day");

            if (type && day) {
                // 원하는 주소로 이동
                window.location.href =  `/quiz/${type}/${day}`;
            } else {
                alert("타입 또는 데이 값이 없습니다.");
            }
        });
    });

});