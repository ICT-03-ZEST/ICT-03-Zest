document.addEventListener("DOMContentLoaded", function() {
    // 콘솔에 shows 데이터를 출력하여 확인
    console.log("Shows data:", shows);

    // DOM 요소를 변수에 저장
    const calendarDates = document.getElementById("calendarDates");
    const currentMonthElement = document.getElementById("currentMonth");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    let currentMonthsave;
    
    // 현재 연도와 월을 초기화
    let currentYear = new Date().getFullYear();
    let currentMonth = new Date().getMonth();

    // URL에서 연도와 월을 파라미터로 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('curYear') && urlParams.has('curMonth')) {
        currentYear = parseInt(urlParams.get('curYear'), 10);
        currentMonth = parseInt(urlParams.get('curMonth'), 10) - 1;
    }

    // 캘린더를 생성하는 함수
    function Calendar() {
        // 현재 월의 첫 날과 마지막 날을 계산
        const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
        const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
        const startDayOfWeek = firstDayOfMonth.getDay(); // 월의 첫 날이 주의 어떤 요일인지

        // 현재 월과 연도를 캘린더 헤더에 표시
        currentMonthElement.textContent = `${currentYear}년 ${currentMonth + 1}월`;
        calendarDates.innerHTML = "";

        // 요일 배열 생성
        const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
        const calendarDays = document.createElement("div");
        calendarDays.classList.add("calendar-days");

        // 요일 헤더를 캘린더에 추가
        daysOfWeek.forEach(day => {
            const dayElement = document.createElement("div");
            dayElement.classList.add("day");
            dayElement.textContent = day;
            calendarDays.appendChild(dayElement);
        });

        // 날짜를 표시할 그리드를 생성
        const calendarDatesGrid = document.createElement("div");
        calendarDatesGrid.classList.add("calendar-dates");

        // 날짜와 공연 정보를 매핑하기 위한 맵 생성
        let showsMap = new Map();
        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i);
            let formattedDate = formatDate(testDay); // 날짜를 포맷팅

            // 해당 날짜의 공연 정보를 필터링
            let showsForDate = shows.filter(item => item.showDay === formattedDate);
            showsMap.set(i, showsForDate);
        }

        // 첫 주의 빈 날짜 슬롯을 생성하여 배치
        for (let i = 0; i < startDayOfWeek; i++) {
            const emptySlot = document.createElement("div");
            emptySlot.classList.add("date", "empty");
            calendarDatesGrid.appendChild(emptySlot);
        }

        // 각 날짜를 캘린더에 추가
        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i);
            let formattedDate = formatDate(testDay);

            // 날짜를 표시할 컨테이너 생성
            const dateContainer = document.createElement("div");
            dateContainer.classList.add("date-container");

            const dateElement = document.createElement("div");
            dateElement.classList.add("date");

            const dateNumber = document.createElement("span");
            dateNumber.classList.add("date-number");
            dateNumber.textContent = i; // 날짜 숫자 표시

            const dateWeek = document.createElement("span");
            dateWeek.classList.add("date-week");
            dateWeek.textContent = `(${daysOfWeek[testDay.getDay()]})`; // 요일 표시

            dateElement.appendChild(dateNumber);
            dateElement.appendChild(dateWeek);
            dateContainer.appendChild(dateElement);

            // 해당 날짜에 공연이 있는지 확인
            const showsForDate = showsMap.get(i);
            if (showsForDate && showsForDate.length > 0) {
                const showList = document.createElement("div");
                showList.classList.add("show-list");

                // 공연 정보를 리스트로 추가
                showsForDate.forEach(show => {
                    const showNameElement = document.createElement("div");
                    showNameElement.classList.add("show-name");
                    showNameElement.textContent = show.showName;
                    showNameElement.addEventListener("click", () => openModal(show)); // 공연 클릭 시 모달 열기
                    showList.appendChild(showNameElement);
                });
                dateContainer.appendChild(showList);
            }

            calendarDatesGrid.appendChild(dateContainer);
        }

        calendarDates.appendChild(calendarDays);
        calendarDates.appendChild(calendarDatesGrid);
    }

    // 날짜를 YYYY-MM-DD 형식으로 포맷팅하는 함수
    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        currentMonthsave = month;
        return `${year}-${month}-${day}`;
    }

    // 공연의 상세 정보를 모달로 표시하는 함수
    function openModal(show) {
        // show 객체를 사용하여 URL 쿼리를 생성
        const url = `/ict03_fastiCat/showTicket_Detail.do?showNum=${show.showNum}&sendShowDay=${show.showDay}`;
        window.location.href = url;
    }

    // 이전 달 버튼 클릭 시 이벤트 핸들러
    prevBtn.addEventListener("click", () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        updateURLAndReload(); 
    });

    // 다음 달 버튼 클릭 시 이벤트 핸들러
    nextBtn.addEventListener("click", () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateURLAndReload(); 
    });

    // URL을 업데이트하고 페이지를 리로드하는 함수
    function updateURLAndReload() {
        const url = `/ict03_fastiCat/main.do?curYear=${currentYear}&curMonth=${currentMonth + 1}`;
        window.location.href = url;
    }

    // 현재 URL 경로 가져오기
    var currentPath = window.location.pathname;

    // 비교할 경로
    var targetPath = "/ict03_fastiCat/main.do";

    // 비교
    if (currentPath == targetPath) {
        Calendar();
    }
});
