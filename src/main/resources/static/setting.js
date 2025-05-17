
    document.addEventListener("DOMContentLoaded", function () {
        // Day별 단어 가져오기 Start
        const daySelect = document.getElementById("dayChoose");
        const tableBody = document.querySelector("#list tbody");


        const urlParams = new URLSearchParams(window.location.search);
        const dayFromQuery = urlParams.get("day");
        if (dayFromQuery) {
        daySelect.value = dayFromQuery; //쿼리스트링 값으로 select 박스 설정
        }

        daySelect.addEventListener("change", function () {
            const selectedDay = this.value;

            const newUrl = `${window.location.pathname}?day=${selectedDay}`;
            window.history.pushState({}, "", newUrl); //select option이 바뀌면 URL에 option을 쿼리스트링으로

            fetch(`/api/words?day=${selectedDay}`)
                .then(response => response.json())
                .then(data => {
                    tableBody.innerHTML = ""; // 기존 테이블 비우기

                    if (data.length === 0) {
                        const row = document.createElement("tr");
                        const td = document.createElement("td");
                        td.colSpan = 4;
                        td.textContent = "단어가 존재하지 않습니다.";
                        row.appendChild(td);
                        tableBody.appendChild(row);
                    } else {
                        data.forEach((voca, index) => {
                            const row = document.createElement("tr");

                            const wordNo = document.createElement("td");
                            wordNo.textContent = index + 1;
                            row.appendChild(wordNo);

                            const wordTd = document.createElement("td");
                            wordTd.textContent = voca.word;
                            row.appendChild(wordTd);

                            const meaningTd = document.createElement("td");
                            meaningTd.textContent = voca.meaning;
                            row.appendChild(meaningTd);

                            const controlTd = document.createElement("td");

                            //수정하기 링크
                            const editLink = document.createElement("a");
                            editLink.href = `/setting/edit/${voca.id}`; // 경로 수정해야함
                            editLink.textContent = "수정하기";
                            editLink.classList.add("button-second");



                            // 삭제하기 링크
                            const deleteLink = document.createElement("a");
                            deleteLink.href = `/setting/delete/${voca.id}`; //경로 수정해야함
                            deleteLink.textContent = "삭제하기";
                            deleteLink.classList.add("button-second");


                            //둘다 controlId에 추가
                            controlTd.appendChild(editLink);
                            controlTd.appendChild(deleteLink);

                            row.appendChild(controlTd);

                            tableBody.appendChild(row);
                        });
                    }
                })
                .catch(error => {
                    console.error("에러 발생:", error);
                });
        });

        // 페이지 처음 로딩 시에도 동작하도록 트리거
        daySelect.dispatchEvent(new Event("change"));
        //Day별 단어 가져오기 End

//        //Day별 단어 저장하기 Start
//        //seletedDay : 선택된 날짜
//
//
//        const addButton = document.questSelector("#setting-quiz-add-button"); //단어 추가하기 버튼
//
//        //'추가하기'버튼이 클릭되면 이벤트 발생
//        addButton.addEventListener("click", function () {
//                const selectedDay = daySelect.value; //select의 선택된 option
//                const addVoca = document.querySelector("input#voca");
//                const addMeaning = document.querySelector("input#meaning");
//
//
//
//        });
    });

