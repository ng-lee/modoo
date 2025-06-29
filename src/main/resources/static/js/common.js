// 시도 리스트 조회
function getSidoList() {
    $.ajax({
        type: "POST",
        url: "/api/region/getSidoList",
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function(response) {
            console.log(response);
        },
        error: function(xhr, status, error) {
            alert(error);
            console.error("상태 코드:", status);
            console.error("응답:", xhr.responseText);
        }
    });
}

// 시군구 리스트 조회
function getSigunguList(selectElement) {
    let sidoCd = selectElement.value;
    const sigunguSelect = document.getElementById("region_sigungu");
    const dongSelect = document.getElementById("region_dong");
    if (isEmpty(sidoCd)) {
        $(selectElement).focus();
        sigunguSelect.innerHTML = '<option value="">시군구 선택</option>';
        dongSelect.innerHTML = '<option value="">읍면동 선택</option>';
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/api/region/getSigunguList",
        data: { sidoCd: sidoCd },
        success: function(response) {
            sigunguSelect.innerHTML = '<option value="">시군구 선택</option>';
            dongSelect.innerHTML = '<option value="">읍면동 선택</option>';

            response.forEach(sigungu => {
                const option = document.createElement("option");
                option.value = sigungu.sigunguCd;
                option.textContent = sigungu.sigunguName;
                sigunguSelect.appendChild(option);
            });

            sigunguSelect.style.display = "inline-block";
        },
        error: function(xhr, status, error) {
            alert(error);
            console.error("상태 코드:", status);
            console.error("응답:", xhr.responseText);
        }
    });
}

// 읍면동 리스트 조회
function getDongList(selectElement) {
    let sigunguCd = selectElement.value;
    const dongSelect = document.getElementById("region_dong");
    if (isEmpty(sigunguCd)) {
        $(selectElement).focus();
        dongSelect.innerHTML = '<option value="">읍면동 선택</option>';
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/api/region/getDongList",
        data: { sigunguCd: sigunguCd },
        success: function(response) {
            dongSelect.innerHTML = '<option value="">읍면동 선택</option>';

            response.forEach(dong => {
                const option = document.createElement("option");
                option.value = dong.dongCd;
                option.textContent = dong.dongName;
                dongSelect.appendChild(option);
            });

            dongSelect.style.display = "inline-block";
        },
        error: function(xhr, status, error) {
            alert(error);
            console.error("상태 코드:", status);
            console.error("응답:", xhr.responseText);
        }
    });
}

// 빈 값 확인
function isEmpty(str) {
    if (typeof str == "undefined" || str === "undefined" || str == null || $.trim(str).length < 1) return true;
    else return false ;
}

// ajax 공통 에러 핸들러
const defaultAjaxError = (xhr, status, err) => {
    console.error("요청 실패 :", err);
    location.href = '/error/500';
};

// ajax 공통 성공/실패 응답 처리
function handleResponse(res, onSuccess, onError) {
    const { success, message, data } = res;

    if (success) {
        if (onSuccess) {
            if (message) {
                alert(message);
                // return setTimeout(() => onSuccess(data), 500); // 0.5초 후 실행
            }
            return onSuccess(data); // 메시지 없으면 바로 실행
        }

        alert(message || "요청이 정상적으로 처리되었습니다.");
    } else {
        if (onError) {
            return onError(res);
        }

        alert(message || "요청 처리 중 문제가 발생했습니다.");
    }
}

// GET - 파라미터X
function ajaxGet(url, onSuccess, onError) {
    $.ajax({
        url: url,
        type: 'GET',
        success: function (res) {
            handleResponse(res, onSuccess, onError);
        },
        error: defaultAjaxError
    });
}

// GET - 파라미터O
function ajaxGetWithParams(url, params, onSuccess, onError) {
    $.ajax({
        url: url,
        type: 'GET',
        data: params,
        success: function (res) {
            handleResponse(res, onSuccess, onError);
        },
        error: defaultAjaxError
    });
}

// POST - 파라미터X
function ajaxPost(url, onSuccess, onError) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        success: function (res) {
            handleResponse(res, onSuccess, onError);
        },
        error: defaultAjaxError
    });
}

// POST - 파라미터O
function ajaxPostWithParams(url, data, onSuccess, onError) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (res) {
            handleResponse(res, onSuccess, onError);
        },
        error: defaultAjaxError
    });
}

// 멀티 파일 업로드
function ajaxFileUpload(url, formData, onSuccess, onError) {
    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        contentType: false, // multipart/form-data 자동 설정
        processData: false, // FormData 그대로 전송 (쿼리스트링 변환 X)
        success: function (res) {
            handleResponse(res, onSuccess, onError);
        },
        error: defaultAjaxError
    });
}

// 서버 유효성 검증 실패 시 data-error-for 클래스에 메시지 노출
function showValidationErrors(errorMap) {
    $('.error-msg').text(''); // 모든 에러 초기화

    Object.entries(errorMap).forEach(([field, message]) => {
        const errorElement = document.querySelector(`[data-error-for="${field}"]`);
        if (errorElement) {
            errorElement.textContent = message;
        }
    });
}

// 파일 미리보기
function previewImages(input) {
    const previewContainer = document.getElementById('preview_container');
    previewContainer.innerHTML = ''; // 기존 미리보기 초기화

    if (input.files && input.files.length > 0) {
        Array.from(input.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.style.width = '200px';
                img.style.marginRight = '10px';
                previewContainer.appendChild(img);
            };
            reader.readAsDataURL(file);
        });
    } else {
        // 파일이 없으면 미리보기 초기화
        previewContainer.innerHTML = '';
    }
}