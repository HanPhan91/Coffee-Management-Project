$(document).ready(function () {
    $("#frmChangePass").validate({
        rules: {
            password: {
                required: true,
                minlength: 6,
                pattern: "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$"
            },
        },
        messages: {
            password: {
                required: "Yêu cầu phải nhập mật khẩu",
                minlength: "Mật khẩu phải có độ dài lớn hơn 6 ký tự",
                pattern: "Mật khẩu phải có trên 6 ký tự, bao gồm ít nhất 1 chữ HOA, 1 chữ thường, 1 số và 1 ký tự đặc biệt",
            },
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