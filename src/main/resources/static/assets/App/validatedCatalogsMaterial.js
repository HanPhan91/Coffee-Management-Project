$(document).ready(function () {

    // Validated Form Create
    $("#frmCreateCatalogsMaterials").validate({
        rules: {
            nameCatalogsMaterialCre: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            nameCatalogsMaterialCre: {
                required: "Tên danh mục nguyên liệụ không được để trống",
                minlength: "Tên danh mục nguyên liệụ phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục nguyên liệụ phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#create .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#create .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#create .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#create .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#create input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createCatalogsMaterial();
        }
    });
    // Validate Form Edit
    $("#frmEdit").validate({
        rules: {
            nameCatalogsMaterialEdit: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            nameCatalogsMaterialEdit: {
                required: "Tên danh mục nguyên liệu không được để trống",
                minlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#edit .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#edit .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#edit .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#edit .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#edit input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            editCatalogMaterial();
        }
    });

    //Validated Form Restore

    $("#frmRestore").validate({
        rules: {
            nameCatalogsMaterialRestore: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            nameCatalogsMaterialRestore: {
                required: "Tên danh mục nguyên liệu không được để trống",
                minlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự"
            }
        },
        errorLabelContainer: '#restore .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#restore .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#restore .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#restore .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#restore input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            restoreCatalogsMaterial();
        }
    });
})