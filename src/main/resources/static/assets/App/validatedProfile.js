$(document).ready(function () {

    $("#frmChangePass").validate({
        rules: {
            changePass: {
                required: true,
                minlength: 6,
            },
            changePassAgain:{
                minlength : 6,
                equalTo: "#changePass"
            }
        },
        messages: {
            changePass: {
                required: "Yêu cầu phải nhập mật khẩu",
                minlength: "Mật khẩu phải có độ dài lớn hơn 6 ký tự",
            },
            changePassAgain:{
                minlength: "Mật khẩu phải có độ dài lớn hơn 6 ký tự",
                equalTo: "Xác nhận mật khẩu không đúng",
            }
        },
        errorLabelContainer: '#modalChangePass .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalChangePass .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalChangePass .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalChangePass .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalChangePass input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            changePass();
        }
    });
})