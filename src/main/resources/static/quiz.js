
function selectQuizType(type) {
    location.href= `/quiz?type=${type}`;
}

document.addEventListener("DOMContentLoaded", function () {

    const quizType = document.querySelector("#selectQuizType");
    const closeBtn = document.querySelector("#close-popup");

        function openPopup() {
        document.getElementById("quiz-popup").style.display = "flex";
        }

        function closePopup() {
        document.getElementById("quiz-popup").style.display = "none";
        }

        quizType.addEventListener("click", function(e) {
        e.preventDefault();
        openPopup();
        });

        closeBtn.addEventListener("click", function() {
        closePopup();
        });




});