$(document).ready(function () {

    // Validated Form Create
    $("#frmCreateMaterial").validate({
        rules: {
            nameMaterialCre: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            nameMaterialCre: {
                required: "Tên danh mục nguyên liệụ không được để trống",
                minlength: "Tên danh mục nguyên liệụ phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục nguyên liệụ phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#createMaterial .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#createMaterial .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#createMaterial .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#createMaterial .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#createMaterial input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createMaterial();
        }
    });
    // Validate Form Edit
    $("#frmEditMaterial").validate({
        rules: {
            nameMaterialEdit: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            nameMaterialEdit: {
                required: "Tên danh mục nguyên liệu không được để trống",
                minlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#editMaterial .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#editMaterial .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#editMaterial .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#editMaterial .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#editMaterial input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            editMaterial();
        }
    });
})