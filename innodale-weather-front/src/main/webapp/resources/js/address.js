function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
//                addr = data.roadAddress;
            	addr = data.jibunAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            
            //관심지역 select에 추가 및 selected
            $('#selist').prepend("<option value='"+ addr + "'>"+ addr + "</option>");
            $('#selist option:eq(0)').prop("selected",true);
            
            //체크된다음 바로 변경사항 place변수에 넣어줌 그리고 그 지역으로 다시 조회를 해야한다.
            refreshAll();
        }
    }).open();
}