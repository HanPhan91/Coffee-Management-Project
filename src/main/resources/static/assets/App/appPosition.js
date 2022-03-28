class Position {
    constructor(id,name, permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }
}
class Permission{
    constructor(id,permissionAccess,createAt,deleted) {
        this.id = id;
        this.permissionAccess = permissionAccess;
        this.createAt = createAt;
        this.deleted = deleted;
    }
}

function handlerAction() {
    $("button.editPosition").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/positions/" + id
        })
            .done(function (data) {
                $("#idPositionEdit").val(data.id);
                $("#namePositionEdit").val(data.name);
                $("#perPositionEdit").val(data.permission.permissionAccess);
                $("#modalEdit").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });

    $("button.deletePosition").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/positions/" + id
        })
            .done(function (data) {
                $("#idPositionDelete").val(data.id);
                $("#namePositionDelete").val(data.name);
                $("#perPositionDelete").val(data.permission.permissionAccess);
                $("#modalDelete").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });
}