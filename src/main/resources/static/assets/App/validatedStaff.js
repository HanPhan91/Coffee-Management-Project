$(document).ready(function () {
    // Validated Form Create
    $("#frmCreateStaff").validate({
        rules: {
            nameStaffCre: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
            addressStaffCre: {
                required: true,
                minlength: 3,
            },
            phoneStaffCre:{
                required: true,
            },
            bodStaffCre:{
                required: true,
            }
        },
        messages: {
            nameStaffCre: {
                required: "Tên nhân viên không được để trống",
                minlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
            addressStaffCre: {
                required: "Địa chỉ nhân viên không được để trống",
                minlength: "Địa chỉ nhân viên phải dài hơn trong khoảng 3 ký tự",
            },
            phoneStaffCre:{
                required: "Số điên thoạỉ nhân viên không được để trống",
            },
            bodStaffCre:{
                required: "Ngày sinh nhân viên không được để trống",
                max: "Ngày sinh không hợp lệ",
            }
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
            createStaff();
        }
    });
    // Validate Form Edit
    $("#frmEditStaff").validate({
        rules: {
            nameStaffCre: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
            addressStaffCre: {
                required: true,
                minlength: 3,
            },
            phoneStaffCre:{
                required: true,
            },
            bodStaffCre:{
                required: true,
            }
        },
        messages: {
            nameStaffCre: {
                required: "Tên nhân viên không được để trống",
                minlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
            addressStaffCre: {
                required: "Địa chỉ nhân viên không được để trống",
                minlength: "Địa chỉ nhân viên phải dài hơn trong khoảng 3 ký tự",
            },
            phoneStaffCre:{
                required: "Số điên thoạỉ nhân viên không được để trống",
            },
            bodStaffCre:{
                required: "Ngày sinh nhân viên không được để trống",
                max: "Ngày sinh không hợp lệ",
            }
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
            editStaff();
        }
    });
    //
    //Validated Form Restore

    $("#frmRestoreStaff").validate({
        rules: {
            nameStaffCre: {
                required: true,
                minlength: 5,
                maxlength: 30,
            },
            addressStaffCre: {
                required: true,
                minlength: 3,
            },
            phoneStaffCre:{
                required: true,
            },
            bodStaffCre:{
                required: true,
            }
        },
        messages: {
            nameStaffCre: {
                required: "Tên nhân viên không được để trống",
                minlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự",
            },
            addressStaffCre: {
                required: "Địa chỉ nhân viên không được để trống",
                minlength: "Địa chỉ nhân viên phải dài hơn trong khoảng 3 ký tự",
            },
            phoneStaffCre:{
                required: "Số điên thoạỉ nhân viên không được để trống",
            },
            bodStaffCre:{
                required: "Ngày sinh nhân viên không được để trống",
                max: "Ngày sinh không hợp lệ",
            }
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
            restoreStaff();
        }
    });
})