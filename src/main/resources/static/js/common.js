// 공통 에러 핸들러
const defaultAjaxError = (xhr, status, err) => {
    console.error("요청 실패 :", err);
    location.href = '/error/500';
};

// GET - 파라미터X
function ajaxGet(url, onSuccess) {
    $.ajax({
        url: url,
        type: 'GET',
        success: function (res) {
            if (res.success) {
                onSuccess(res.data);
            } else {
                alert(res.message);
            }
        },
        error: defaultAjaxError
    });
}

// GET - 파라미터O
function ajaxGetWithParams(url, params, onSuccess) {
    $.ajax({
        url: url,
        type: 'GET',
        data: params,
        success: function (res) {
            if (res.success) {
                onSuccess(res.data);
            } else {
                alert(res.message);
            }
        },
        error: defaultAjaxError
    });
}

// POST - 파라미터X
function ajaxPost(url, onSuccess) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        success: function (res) {
            if (res.success) {
                onSuccess(res.data);
            } else {
                alert(res.message);
            }
        },
        error: defaultAjaxError
    });
}

// POST - 파라미터O
function ajaxPostWithParams(url, data, onSuccess) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (res) {
            if (res.success) {
                onSuccess(res.data);
            } else {
                alert(res.message);
            }
        },
        error: defaultAjaxError
    });
}

// 멀티 파일 업로드
function ajaxFileUpload(url, formData, onSuccess) {
    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        contentType: false, // multipart/form-data 자동 설정
        processData: false, // FormData 그대로 전송 (쿼리스트링 변환 X)
        success: function (res) {
            if (res.success) {
                onSuccess(res.data);
            } else {
                alert(res.message);
            }
        },
        error: defaultAjaxError
    });
}

