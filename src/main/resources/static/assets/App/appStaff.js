class Staff {
    constructor(id, name, address, phone, email, birthDay, position) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthDay = birthDay;
        this.position = position;
    }
}

function handlerActionStaff() {
    let positons;
    $.ajax({
        type: "GET",
        url: "/api/positions"
    })
        .done(function (data) {
            positons = data;
        })
        .fail(function () {
            window.location = "/errors";
        })

    $("button.editStaff").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/staffs/" + id
        })
            .done(function (data) {
                $("#idStaffEdit").val(id);
                $("#nameStaffEdit").val(data.name);
                $("#addressStaffEdit").val(data.address);
                $("#phoneStaffEdit").val(data.phone);
                $("#emailStaffEdit").val(data.email);
                let limitDay = "";
                let today = new Date();
                limitDay = `${today.getFullYear() - 16}-12-31`;
                $("#bodStaffEdit").attr("max", limitDay);
                $("#bodStaffEdit").val(data.birthDay);
                let str = "";
                for (let i = 0; i < positons.length; i++) {
                    let position = positons[i];
                    if (positons[i].id == data.position.id) {
                        str += `<option value="${position.id}" selected>${position.name}</option>`;
                    } else {
                        str += `<option value="${position.id}">${position.name}</option>`;
                    }
                }
                $("#positionStaffEdit").prepend(str);
                $("#modalEdit").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })

    });

    $("button.deleteStaff").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/staffs/" + id
        })
            .done(function (data) {
                $("#idStaffDelete").val(id);
                $("#nameStaffDelete").val(data.name);
                $("#addressStaffDelete").val(data.address);
                $("#phoneStaffDelete").val(data.phone);
                $("#emailStaffDelete").val(data.email);
                $("#bodStaffDelete").val(data.birthDay);
                $("#positionStaffDelete").val(data.position.name);
                $("#modalDelete").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });
}


$("#frmCreateUserForStaff").validate({
    rules: {
        usernameCreForStaff: {
            required: true,
            minlength: 5,
            maxlength: 30,
        },
        passCreForStaff: {
            required: true,
            pattern: "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$"
        },
    },
    messages: {
        usernameCreForStaff: {
            required: "Tên đăng nhập không được để trống",
            minlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
            maxlength: "Tên đăng nhập phải có độ dài nằm trong khoảng 5-30 ký tự",
        },
        passCreForStaff: {
            required: "Mật khẩu không được để trống",
            pattern: "Mật khẩu phải có trên 6 ký tự, bao gồm ít nhất 1 chữ HOA, 1 chữ thường, 1 số và 1 ký tự đặc biệt",
        },
    },
    errorLabelContainer: '#modalCreateUser .modal-body .modal-alert-danger',
    errorPlacement: function (error, element) {
        error.appendTo("#modalCreateUser .modal-body .modal-alert-danger");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            $("#modalCreateUser .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
        } else {
            $("#modalCreateUser .modal-body .modal-alert-danger").removeClass("show").addClass("hide").empty();
            $("#modalCreateUser input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        createUserForStaff();
    }
});