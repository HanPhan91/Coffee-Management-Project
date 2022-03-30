$(document).ready(function (){
    $("#frmCreateTable").validate({
        rules:{
            tableNameCreate: {
              required: true,
              minlength: 1,
              maxlength: 50,
            },
        },
        messages:{
            tableNameCreate: {
                required: "Tên bàn không được để trống",
                minlength: "Tên bàn phải có ít nhất 1 ký tự",
                maxlength: "Tên bàn không được quá 50 ký tự",
            },
        },
        errorLabelContainer: '#modalCreateTable .modal-body .modal-alert-danger',
        errorPlacement: function (error, element) {
            error.appendTo("#modalCreateTable .modal-body .modal-alert-danger");
        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreateTable .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreateTable .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalCreateTable input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createTable();
        }
    });
})