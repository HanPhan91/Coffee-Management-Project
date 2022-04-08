$(document).ready(function () {
    // Validated Form Create
    $("#frmCreateDrink").validate({
        rules:{
            drinkNameCreate: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            priceDrinkCreate:{
                required: true,
            }
        },
        messages:{
            drinkNameCreate: {
                required: "Tên thức uống không được để trống",
                minlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
                maxlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
            },
            priceDrinkCreate:{
                required: true,
            }
        },
        errorLabelContainer: '#modalCreateDrink .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalCreateDrink .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreateDrink .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreateDrink .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalCreateDrink input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createDrink();
        }
    });

    // Validated Form Update
    $("#frmUpdateDrink").validate({
        rules:{
            drinkNameUpdate: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            priceDrinkUpdate:{
                required: true,
            }
        },
        messages:{
            drinkNameUpdate: {
                required: "Tên thức uống không được để trống",
                minlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
                maxlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
            },
            priceDrinkUpdate:{
                required: true,
            }
        },
        errorLabelContainer: '#modalUpdateDrink .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalUpdateDrink .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalUpdateDrink .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalUpdateDrink .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalUpdateDrink input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            updateDrink();
        }
    });

    // Validated Form Restore
    $("#frmRestoreDrink").validate({
        rules:{
            drinkNameRestore: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            priceDrinkRestore:{
                required: true,
            }
        },
        messages:{
            drinkNameRestore: {
                required: "Tên thức uống không được để trống",
                minlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
                maxlength: "Tên thức uống phải nằm trong khoảng 5-50 ký tự",
            },
            priceDrinkRestore:{
                required: true,
            }
        },
        errorLabelContainer: '#modalRestoreDrink .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalRestoreDrink .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalRestoreDrink .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalRestoreDrink .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalRestoreDrink input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            restoreDrink();
        }
    });
});