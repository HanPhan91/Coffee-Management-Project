$(document).ready(function () {

    // Validated Form Create
    $("#frmCreateCatalog").validate({
        rules: {
            catalogNameCreate: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
        },
        messages: {
            catalogNameCreate: {
                required: "Tên danh mục̣ không được để trống",
                minlength: "Tên danh mục phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#modalCreateCatalog .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalCreate .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreateCatalog .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreateCatalog .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalCreateCatalog input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createCatalog();
        }
    });

    $("#frmUpdateCatalog").validate({
        rules: {
            catalogNameUpdate: {
                required: true,
                minlength: 1,
                maxlength: 30
            },
        },
        messages: {
            catalogNameUpdate: {
                required: "Tên chức vụ không được để trống",
                minlength: "Tên chức vụ phải nằm trong khoảng 1-30 ký tự",
                maxlength: "Tên chức vụ phải nằm trong khoảng 1-30 ký tự"
            }
        },
        errorLabelContainer: '#modalUpdateCatalog .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalUpdateCatalog .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalUpdateCatalog .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalUpdateCatalog .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalUpdateCatalog input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            updateCatalog();
        }
    });
})
