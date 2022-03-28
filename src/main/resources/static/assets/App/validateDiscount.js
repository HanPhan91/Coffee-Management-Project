
$(document).ready(function () {

    // Validated Form Create
    $("#frmCreateDiscount").validate({
        rules: {
            codeDiscountCre: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            endedAtCreate: {
                greaterStart: "#createdAtDiscountCre"
            }
        },
        messages: {
            codeDiscountCre: {
                required: "Mã khuyến mại không được để trống",
                minlength: "Mã khuyến mại phải nằm trong khoảng 3-50 ký tự",
                maxlength: "Mã khuyến mại phải nằm trong khoảng 3-50 ký tự"
            }

        },
        errorLabelContainer: '#createDiscount .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#createDiscount .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#createDiscount .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#createDiscount .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#createDiscount input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createDiscount();
        }
    });


    $("#frmEditDiscount").validate({
        rules: {
            codeDiscountEdit: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            endedAtEdit: {
                greaterStart: "#createdAtDiscountEdit"
            }
        },
        messages: {
            codeDiscountEdit: {
                required: "Mã khuyến mãi không được để trống",
                minlength: "Mã khuyến mãi phải nằm trong khoảng 3-50 ký tự",
                maxlength: "Mã khuyến mãi phải nằm trong khoảng 3-50 ký tự"
            }
        },
        errorLabelContainer: '#editDiscount .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#editDiscount .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#editDiscount .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#editDiscount .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#editDiscount input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            editDiscount();
        }
    });

    $("#frmRestoreDiscount").validate({
        rules: {
            codeDiscountRestore: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            codeDiscountRestore: {
                required: "Mã khuyến mại không được để trống",
                minlength: "Mã khuyến mại phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Mã khuyến mạiphải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#restoreDiscount .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#restoreDiscount .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#restoreDiscount .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#restoreDiscount .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#restoreDiscount input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            restoreDiscount();
        }
    });
    // compare date
    jQuery.validator.addMethod("greaterStart", function (value, element, params) {
        return this.optional(element) || new Date(value) >= new Date($(params).val());
    }, 'Must be greater than start date.');
})