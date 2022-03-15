$(document).ready(function () {

    // Validated Form Create
    $("#frmCreate").validate({
        rules: {
            namePositionCre: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            namePositionCre: {
                required: "Tên chức vụ không được để trống",
                minlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự"
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
            createPosition();
        }
    });
    // Validate Form Edit
    $("#frmEdit").validate({
        rules: {
            namePositionEdit: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            namePositionEdit: {
                required: "Tên chức vụ không được để trống",
                minlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự"
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
            editPosition();
        }
    });

    //Validated Form Restore

    $("#frmRestore").validate({
        rules: {
            namePositionRestore: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
        },
        messages: {
            namePositionRestore: {
                required: "Tên chức vụ không được để trống",
                minlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự",
                maxlength: "Tên chức vụ phải nằm trong khoảng 5-30 ký tự"
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
            restorePosition();
        }
    });
})