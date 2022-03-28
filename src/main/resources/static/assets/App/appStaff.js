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
                limitDay= `${today.getFullYear() - 16}-12-31`;
                $("#bodStaffEdit").attr("max", limitDay);
                $("#bodStaffEdit").val(data.birthDay);
                let str="";
                for (let i = 0; i < positons.length; i++) {
                    let position = positons[i];
                    if (positons[i].id == data.position.id){
                        str+=`<option value="${position.id}" selected>${position.name}</option>`;
                    }
                    else {
                        str+=`<option value="${position.id}">${position.name}</option>`;
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