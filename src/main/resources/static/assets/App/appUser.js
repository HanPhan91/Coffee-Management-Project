class User{
    constructor(username, password, staff) {
        this.username = username;
        this.password = password;
        this.staff = staff;
    }
}

function handlerActionUser() {
    $("button.editUser").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/users/" + id
        })
            .done(function (data) {
                $
                $("#modalEdit").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });

    $("button.deleteUser").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/users/" + id
        })
            .done(function (data) {

                $("#modalDelete").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });
}