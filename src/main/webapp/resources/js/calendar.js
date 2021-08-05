// 달력 컴포넌트 한글/현실화
    /*
    $("#memberBirth").datepicker({  
        changeYear:true,  
        changeMonth:true,
        dateFormat:"yy-mm-dd"      
    });  
    */
$(function() { 
	
    $.datepicker.regional['ko'] = {
          autoSize : true,
          closeText: '닫기',
          prevText: '이전달',
          nextText: '다음달',
          currentText: '오늘',
          yearRange: "1930:2019", // 1930 ~ 2019
          // yearRange: "-36:-20", // 36년전부터 20년전까지
          monthNames: ['1월','2월','3월','4월','5월','6월',
          '7월','8월','9월','10월','11월','12월'],
          monthNamesShort: ['1월','2월','3월','4월','5월','6월',
          '7월','8월','9월','10월','11월','12월'],
          dayNames: ['일','월','화','수','목','금','토'],
          dayNamesShort: ['일','월','화','수','목','금','토'],
          dayNamesMin: ['일','월','화','수','목','금','토'],
          weekHeader: 'Wk',
          dateFormat: 'yy-mm-dd', // 날짜 포맷
          firstDay: 0,
          minDate: new Date(1930, 1 - 1, 1),
          isRTL: false,
          showMonthAfterYear: true,
          yearSuffix: '년',
          // 버그 패치 : 컴포넌트 겹침 현상 해소 ex) 성별 필드와의 겹침
          beforeShow: function() {
             setTimeout(function(){
                $('.ui-datepicker').css('z-index', 10);
             }, 0);
          }
      };
       
      $.datepicker.setDefaults($.datepicker.regional['ko']);
     
      $("#memberBirth").datepicker({changeYear:true, changeMonth:true});
   
    ////////////////////////////////////////////////
   
   
});