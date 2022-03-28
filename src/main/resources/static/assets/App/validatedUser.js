$(document).ready(function () {
    // Validated Form Create
    $("#frmCreateUser").validate({
        rules: {
            usernameCre: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
            passCre: {
                required: true,
                minlength: 3,
            },
        },
        messages: {
            usernameCre: {
                required: "Tên đăng nhập không được để trống",
                minlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
            passCre: {
                required: "Mật khẩu không được để trống",
                minlength: "Mật khẩu phải dài hơn trong khoảng 3 ký tự",
            },
        },
        errorLabelContainer: '#modalCreate .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalCreate .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreate .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreate .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalCreate input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createUser();
        }
    });

    // Validated Form Edit
    $("#frmEditUser").validate({
        rules: {
            usernameEdit: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
        },
        messages: {
            usernameEdit: {
                required: "Tên đăng nhập không được để trống",
                minlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
        },
        errorLabelContainer: '#modalEdit .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalEdit .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalEdit .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalEdit .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalEdit input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            editUser();
        }
    });

    $("#frmRestoreUser").validate({
        rules: {
            usernameRestore: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
            passRestore: {
                required: true,
                minlength: 3,
            },
        },
        messages: {
            usernameRestore: {
                required: "Tên đăng nhập không được để trống",
                minlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
            passRestore: {
                required: "Mật khẩu không được để trống",
                minlength: "Mật khẩu phải dài hơn trong khoảng 3 ký tự",
            },
        },
        errorLabelContainer: '#modalRestore .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalRestore .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalRestore .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalRestore .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalRestore input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            restoreUser();
        }
    });
})