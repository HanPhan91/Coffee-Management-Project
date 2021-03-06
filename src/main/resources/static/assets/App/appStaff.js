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
            required: "T??n ????ng nh????p kh??ng ????????c ?????? tr????ng",
            minlength: "T??n ????ng nh????p pha??i co?? ?????? da??i n????m trong khoa??ng 5-30 ky?? t????",
            maxlength: "T??n ????ng nh????p pha??i co?? ?????? da??i n????m trong khoa??ng 5-30 ky?? t????",
        },
        passCreForStaff: {
            required: "M????t kh????u kh??ng ????????c ?????? tr????ng",
            pattern: "M????t kh????u pha??i co?? tr??n 6 ky?? t????, bao g????m i??t nh????t 1 ch???? HOA, 1 ch???? th??????ng, 1 s???? va?? 1 ky?? t???? ??????c bi????t",
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