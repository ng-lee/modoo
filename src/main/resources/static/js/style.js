$(document).ready(function(){
	if ($(window).width() > 1024) {
		innerMinHeight();
	}else {
		MobileInnerMinHeight();	
	}

	$(window).resize(function(){
		if ($(window).width() > 1024) {
			innerMinHeight();
		}else {
			MobileInnerMinHeight();	
		}
	});

	$(".no_event").click(function(event){
		event.preventDefault();
		event.stopPropagation();
	});
	
	// 맨위로 이동버튼
	$('.page_up_btn').on('click',function(e){
		e.preventDefault();
		$("html, body").animate({scrollTop:0}, 400);
	});

	// 셀렉트 활성화	
	$(".select li.select_option:not(.select_default) a").each(function(i){
		var title = $(this).parents(".select").children("p").text();
		var title_result = title.trim();
		var option_text = $(this).text();
		if (title_result === option_text) {
			$(this).parents(".select").addClass("selected");
		}
	});
	$(document).on("click", ".select:not(.disabled) li", function(){
		$(this).parents(".select").children("p").css("color","#4D525C");
		$(this).parents(".select").children("p").addClass("navy_border");
		// 셀렉트 (아무것도 선택되지 않은 상태)
		if($(this).hasClass("select_default")){
			$(this).parents(".select").children("p").css("color", "#B5BAC4");
			$(this).parents(".select").children("p").removeClass("navy_border");
		}
	});	

	// [공통] select - custom
	$(".select > ul").hide();
	$(".select:not(.disabled) > p").click(function(){
		var this_div = $(this).parents(".select");
		var this_ul = $(this).next("ul");
		$(".select").not(this_div).removeClass("on");
		$(".select ul").not(this_ul).slideUp(200);
		this_div.toggleClass("on");
		this_ul.slideToggle(200);
		$(".common_dim").toggleClass("on");

		if($(this).closest('.table_wrap')){
			$('.table_wrap .select p').each(function(){
				var pop_length = $('.table_wrap .select p').length;
				for(let i = 0; i < pop_length; i++){
					var this_h = $(this).outerHeight();
					var this_x = $(this).offset().left;
					var this_y = $(this).offset().top + this_h;
					$(this).next('ul').css({'left': this_x, 'top': this_y, 'width': $(this).outerWidth()})
				}
			})
		}
	});

	// [공통] select 값 넣기
	$(".select:not(.disabled) li").click(function () {
		var value = $(this).children("a").html();
		$(this).parents(".select:not(.disabled)").removeClass("on");
		$(this).parents(".select:not(.disabled)").children("ul").slideUp();
		$(this).parents(".select:not(.disabled)").children("p").html(value);
		$(".common_dim").removeClass("on");
	});

	// [공통] popup close
	$(".pop_dim, .pop_close").click(function(){
		$(this).closest(".pop_wrap").removeClass("on");
		$("body").css({"height": "auto", "overflowY": "visible"});
	})
	
	// [공통] popup open
	$("*[name='pop_btn']").click(function(e){
		var btn_name = $(this).attr("class");
		var strArray = btn_name.split(" ");
		for (i=0 ; strArray.length > i ; i++ )	{
			var pos1 = strArray[i].indexOf("_open");
			if (pos1 > -1) {
				var button = strArray[i].split("_open").join("");
				$("."+button+"_pop").addClass("on");
			}
		}
		$("body").css({"height": "100%", "overflowY": "hidden"});
	})

	// [공통] tab
	$(".tab_pager li, .tab_ul.location_none li").click(function(){
		var i = $(this).index();
		$(".tab_pager li, .tab_ul.location_none li").removeClass("on");
		$(this).addClass("on");
		$(this).parent(".tab_pager, .tab_ul").next(".tab_wrap").children(".tab_cont").removeClass("on");
		$(this).parent(".tab_pager, .tab_ul").next(".tab_wrap").children(".tab_cont").eq(i).addClass("on");
	});

	// [공통] div.container_inner > min-height 설정
	var min_h;
	function innerMinHeight(){
		var window_h = $(window).height();
		var header_h = $('header').outerHeight();
		var footer_h = $('footer').outerHeight();

		min_h = window_h - header_h - footer_h;
		$('.container_inner').css({'min-height' : min_h});
	};
	function MobileInnerMinHeight(){
		var window_h = $(window).height();
		var header_h = $('.mb_header').outerHeight();
		var footer_h = $('footer').outerHeight();

		min_h = window_h - header_h - footer_h;
		$('.container_inner').css({'min-height' : min_h});
	};

	// [공통] GNB - 대메뉴 hover시 중메뉴 노출
	$(".gnb > li").mouseenter(function(){
		$(this).addClass("on");
		$(".gnb .sub").addClass("open");
		$(".gnb_bg").addClass("on");
	});
	$(".gnb > li").mouseleave(function(){
		$(this).removeClass("on");
		$(".gnb .sub").removeClass("open");
		$(".gnb_bg").removeClass("on");
	});
	$(".gnb_bg").mouseenter(function(){
		$(this).addClass("on");
		$(".gnb .sub").addClass("open");
	});
	$(".gnb_bg").mouseleave(function(){
		$(this).removeClass("on");
		$(".gnb .sub").removeClass("open");
	});

	// [공통] 모바일 GNB - gnb 호출
	$(".hamburger_btn").click(function(){
		$(".mb_nav").addClass("on");
		$(".pop_dim.type2").css({"z-index":"99", "display":"block"});
	});
	$(".pop_dim.type2").click(function(){
		$(".mb_nav").removeClass("on");
		$(this).css("display","none");
	});

	// [공통] 모바일 GNB - 대메뉴 click시 중메뉴 노출
	$(".mb_gnb > li").click(function(){
		$(this).toggleClass("on");
		$(".mb_gnb .mb_sub").addClass("open");
		if ($(this).hasClass("on")){
			$(this).siblings().removeClass("on");
		}
	});

	$(".mb_nav .close_btn").click(function(){
		$(".mb_nav").removeClass("on");
		$(".pop_dim").css("display","none");
	})

	// [공통] input 입력검색
	$(".search_input_area input").on("change keyup paste", function(){
		console.log($(this).val());
		if ($(this).val() === ""){
			$(this).parents(".search_input_area").removeClass("on");
		} else {
			$(this).parents(".search_input_area").addClass("on");
		}
	})
	$("#optionbox li").click(function(){
		var text = $(this).text();
		$(this).parents(".search_option").prev("input").val(text);
		$(".search_input_area").removeClass("on");
	});
	$(".search_dim").click(function(){
		$(".search_input_area").removeClass("on");
	});


	// [공통] datepicker
	$("input.datepicker_input").datepicker({
    	language: "ko",
		autoClose: true,
		onSelect: function(formattedDate, date, inst) {
			$(inst.el).trigger("change");
		}
    });

	$("input.datepicker_input").focus(function(){
    	if($(this).parents('div').hasClass('pop_wrap')){
			$('.datepicker').css({'z-index': 1000})
		}
    }).blur(function(){
		$('.datepicker').css({'z-index': 99})
    });

	$("input.datepicker_input").on("change", function() {
		var selected = $(this).val();
		if (selected === ""){
			$(this).removeClass("picked");
		} else {
			$(this).addClass("picked");
		}
	});

	// [공통] year+monthpicker
	$("input.monthpicker_input").datepicker({
    	language: "ko",
		view: "months",
		minView: "months",
		dateFormat: "yyyy-mm",
		autoClose: true,
		onSelect: function(formattedDate, date, inst) {
			$(inst.el).trigger("change");
		}
    })

	$("input.monthpicker_input").on("change", function() {
		var selected = $(this).val();
		if (selected === ""){
			$(this).removeClass("picked");
		} else {
			$(this).addClass("picked");
		}
	});

	// [공통] timepicker
	$("input.timepicker_input").datepicker({
    	language: "ko",
		timepicker: true,
		onlyTimepicker: true,
		timeFormat: "hh:mm"
    });

	// [공통] date+timepicker
	$("input.datetimepicker_input").datepicker({
    	language: "ko",
		timepicker: true,
		timeFormat: "hh:mm"
    });

	//전체 체크
	$("#all_chk").change(function(){
		var checked = $(this).is(":checked");
		if (checked === true){
			$("input[name='chk']").prop("checked",true);
		}else {
			$("input[name='chk']").prop("checked",false);
		}
	});
	$("input[name='chk']").change(function(){
		var input_length = $("input[name='chk']").length;
		var chk_length = $("input[name='chk']:checked").length;

		if (input_length == chk_length){
			$("#all_chk").prop("checked",true);
		}else {
			$("#all_chk").prop("checked",false);
		}
	});

	// 메인 슬라이드
    var swiper = new Swiper(".main_vsl .swiper", {
		pagination: {
			el: ".swiper-pagination",
				clickable: true,
				renderBullet: function (index, className) {
				return '<span class="' + className + '">0' + (index + 1) + "</span><div class='slide_progress_bar page" + (index + 1) + "'><div class='gauge animation'></div></div>";
			},
		},
		autoplay: {
			delay: 3000,
			disableOnInteraction: false,
		},
		//breakpoints: {
		//	1280: {
		//		slidesPerView: 5,
		//		spaceBetween: 50,
		//	},
		//}
		//현재 페이지 번호 갱신
		on: {
			slideChange: () => {
				$(".slide_progress_bar .gauge").removeClass('animation');
				setTimeout(function() {
				   $(".slide_progress_bar .gauge").addClass('animation');
				},1);
				//$(".gauge").css({"width":"0", "transition":"all 0.4s"})
				//$(".gauge").css("width","100%")
				//num.html(`<strong>${swiper.realIndex + 1}</strong> / ${slideCount}`);
			}
		}
    });

	// 메인 슬라이드 프로그레스 바 위치조정
	if ($(".container").hasClass("index")) {
		var page_left = 0;
		setInterval(() => {
			page_left = $(".swiper-pagination-bullet-active").offset().left + 30;
			$(".slide_progress_bar").css("left",page_left)
		});
	}

	// 고객사 사례 슬라이드
    var swiper = new Swiper(".example_list .swiper", {
		loop: true,
		slidesPerView: 'auto',
		autoHeight: true,
		navigation: {
			nextEl: ".swiper-button-next",
			prevEl: ".swiper-button-prev",
		},
		spaceBetween: 50,
		centeredSlides: true,
		breakpoints: {
			1600: {
				slidesPerView: 5,
			},
			1280: {
				slidesPerView: 3.5,
				spaceBetween: 20,
			},
			1024: {
				slidesPerView: 2,
			},
			0: {
				slidesPerView: 1.3,
				spaceBetween: 20,
			},
		}
    });

	// 스폰서 슬라이드
    var swiper = new Swiper(".sponsor_list .swiper", {
		slidesPerView: 'auto',
		autoHeight: true,
		//slidesPerView: 4.5,
    });

	// 카운트
	//$(window).scroll(function(){	
	//	if ($(this).scrollTop() > $(".section6").offset().top - $(".section6").outerHeight() / 2)	{
	//		// 애니메이션 시간 설정 (밀리초 단위)
	//		const duration = 1000;
	//		// 시작 값과 목표 값 설정
	//		const startValue = 0;
	//		$(".count").each(function(){
	//			const targetValue = $(this).attr('data-value');
	//			$(this).animate({
	//				count: targetValue
	//			}, {
	//				duration: duration,
	//				step: function (now) {
	//				// 각 단계에서 호출되는 콜백 함수
	//				$(this).text(Math.round(now).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	//			},
	//			complete: function () {
	//				// 애니메이션 완료 후 호출되는 콜백 함수
	//				$(this).text(Number(targetValue).toLocaleString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	//			}
	//			});
	//		});
	//	}else {
	//		return false;
	//	}
	//});
	//$(window).trigger("scroll");

	// 로그인, 회원가입, 아이디/비밀번호찾기 배경색
	if($('section').hasClass('form_page')){
		$('body').addClass('form_gray_bg');
	};

	// Input File 이름 노출
	$("#file_upload").change(function(){
		var fileValue = $("#file_upload").val().split("\\");
		var fileName = fileValue[fileValue.length-1]; // 파일명
		$("#file_name").attr("value", fileName);
	});

	// header 내정보 팝업 노출
	$(".common_dim, .header_dim").click(function(){
		$(".side_info .my_info, .inner_pop").removeClass("on");
		$(".common_dim, .header_dim").removeClass("on");
	});

	// header 내정보 팝업 노출
	$(".common_dim").click(function(){
		$(".select").removeClass("on");
		$(".select ul").stop().slideUp();
	});

	$(".side_info .my_info").click(function(){
		$(this).toggleClass("on");
		$(this).find(".inner_pop").toggleClass("on");
		$(".common_dim, .header_dim").addClass("on");
	});

	//input 내 delete 관련 스크립트
	$("input").on("change keyup paste", function(){
		var val = $(this).val();
		if (val === ""){
			$(this).parent(".text_input").removeClass("on");
		} else {
			$(this).parent(".text_input").addClass("on");
		}
	})
	$("button[type=reset]").click(function(){
		$(this).siblings("input").val("");
		$(this).parent(".text_input").removeClass("on"); 
	});

	//password show/hide
	$(".eye").click(function(){
		$(this).parent(".password").toggleClass("show")
		if ($(this).parent(".password").hasClass("show")){
			$(this).siblings("input").attr("type","text");
		} else {
			$(this).siblings("input").attr("type","password");
		}
	});

	// faq
	$(".faq tr").click(function(){
		// 클래스 debriefer가 붙은 tr을 클릭할 시, 접근제한 popup이 뜨고 아코디언이 열리지 않습니다.
		if($(this).hasClass("debriefer") == false){
			$(this).find(".faq_arrow").toggleClass("on");
			$(this).find(".faq_text").toggleClass("on");
			$(this).toggleClass("mb_border_none");
			//$(this).find(".faq_text").slideToggle(300);
		}else{
			$(".faq .pop_wrap").addClass('on');
		}
	})

	$(".faq_text").click(function(event){
		event.preventDefault();
		event.stopPropagation();
	});

	// heart
	$(".fixbtn_ul .heart").click(function(){
		$(".fixbtn_ul .heart").parent("li").toggleClass("on");
	})

	//더보기 팝업
	$("button.more").click(function(){
		$(this).parents(".more_area").toggleClass("on");
	});

	//결제 > 날짜 선택 팝업
	$(".schedule_wrap > ul > li").on("click",function(){
		$(".schedule_wrap > ul > li").removeClass("on");
		$(this).toggleClass("on");
		$(this).find($("input[name='select']")).prop("checked",true);
	})

	//모바일 버크만 솔루션 fixed_info 버튼
	$(".fixed_info_btn").click(function(){
		$(this).toggleClass("on");
		$(".fixed_info_contents").toggleClass("on");

		if($(".fixed_info_contents").hasClass("on")){
			$(".fixed_info_btn.round").addClass("on")
		}else{
			$(".fixed_info_btn.round").removeClass("on")
		}
	});

	//모바일 좋아요&공유하기 버튼 위치고정
	$(window).resize(function(){
		if($(window).width() < 1025) {
			var footer_top = $(".footer").offset().top;
			var window_height = $(window).height();

			$(window).scroll(function(){
				var footer_top = $(".footer").offset().top;
				var scroll_top = $(window).scrollTop();
				var scroll_bottom = scroll_top + window_height;
				
				if(footer_top <= scroll_bottom) {
					$(".container_inner").css("position", "relative");
					$(".fixbtn_ul").addClass("abs");
				} else {
					$(".container_inner").css("position", "initial");
					$(".fixbtn_ul").removeClass("abs");
				}
			});
		}
	});		
	$(window).trigger("resize");

	//모바일 공유하기 팝업 위치
	$(window).resize(function(){
		if($(window).width() < 1025) {
			$(".share_pop").addClass("center_pop");
		} else {
			$(".share_pop").removeClass("center_pop");
		}
	});		
	$(window).trigger("resize");

	//lnb toggle
	$(".depth_1 > li").click(function(){
		if ($(this).hasClass("open")){
			$(this).removeClass("open");
		} else {
			$(this).addClass("open");
		}
	});

	//var top0 = $(".sticky_cont > div").eq(0).offset().top;
	//var top1 = $(".sticky_cont > div").eq(1).offset().top;
	//var top2 = $(".sticky_cont > div").eq(2).offset().top;
	//var top3 = $(".sticky_cont > div").eq(3).offset().top;
	//var top4 = $(".sticky_cont > div").eq(4).offset().top;
	var top = [];
	var cnt = 0;
	$(".sticky_cont > div").each(function(){
		top[cnt] = $(this).offset().top;
		cnt++
	});
	//console.log(top, "top")


	$(window).scroll(function(){
		var scroll_top = $(window).scrollTop() + 170;
		if ($(".sticky_wrap").hasClass("langth_5")) {
			if (scroll_top > top[1] && scroll_top < top[2]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(1).addClass("on");
			}else if (scroll_top > top[2] && scroll_top < top[3]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(2).addClass("on");
			}else if (scroll_top > top[3] && scroll_top < top[4]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(3).addClass("on");
			}else if (scroll_top > top[4]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(4).addClass("on");
			} else {
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(0).addClass("on");
			}
		} else if ($(".sticky_wrap").hasClass("langth_4")) {
			if (scroll_top > top[1] && scroll_top < top[2]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(1).addClass("on");
			}else if (scroll_top > top[2] && scroll_top < top[3]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(2).addClass("on");
			}else if (scroll_top > top[3]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(3).addClass("on");
			} else {
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(0).addClass("on");
			}
		} else if ($(".sticky_wrap").hasClass("langth_3")) {
			if (scroll_top > top[1] && scroll_top < top[2]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(1).addClass("on");
			}else if (scroll_top > top[2]){
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(2).addClass("on");
			} else {
				$(".sticky_wrap .tab_pager li").removeClass("on");
				$(".sticky_wrap .tab_pager li").eq(0).addClass("on");
			}
		}
		
	});
	$(window).trigger("scroll")
	$(".sticky_wrap .tab_pager li").click(function(){
		var index = $(this).index();
		var top = $(".sticky_cont > div").eq(index).offset().top - 160;
		$("html, body").animate({scrollTop: top}, 400);
	});

	//툴팁 관련 스크립트
	$(".alert_btn").click(function(){
		$(this).parents(".alert_area").addClass("on");
	});
	$(".alert_dim").click(function(){
		$(".alert_area").removeClass("on");
	});

	//셀렉트 기타 선택시 input 창 노출
	$(".other_input_wrap .select a").click(function(){
		var this_text = $(this).text();
		if (this_text === "기타"){
			$(this).parents(".other_input_wrap").addClass("on");
		}else {
			$(this).parents(".other_input_wrap").removeClass("on");
		}
	});
	
	// 진단관리 > 진단단체관리 > 수정/상세 > 이전 진단 대상자 불러오기 팝업
	$(".prev_member_detail_pop .target_set input[name='target']").change(function(){
		var text = $(this).next("label").text();
		if(text === "단체명으로 일괄 대상자 넣기"){
			$(".prev_member_detail_pop .render2").removeClass("on")
			$(".prev_member_detail_pop .render1").addClass("on")
		} else{
			$(".prev_member_detail_pop .render1").removeClass("on")
			$(".prev_member_detail_pop .render2").addClass("on")
		}
	});
});
