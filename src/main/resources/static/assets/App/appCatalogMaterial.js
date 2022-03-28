class CatalogsMaterial {
    constructor(name) {
        this.name = name;
    }
}

function handlerActionCatalogsMaterial() {
    $("button.editCatalogsMaterial").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/catalogsMaterial/" + id
        })
            .done(function (data) {
                $("#idCatalogsMaterialEdit").val(data.id);
                $("#nameCatalogsMaterialEdit").val(data.name);
                $("#edit").modal("show");
            })
            .fail(function (resp) {
                window.location = "https://viblo.asia.vn";
            })
    });

    $("button.deleteCatalogsMaterial").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/catalogsMaterial/" + id
        })
            .done(function (data) {
                $("#idCatalogsMaterialDelete").val(data.id);
                $("#nameCatalogsMaterialDelete").val(data.name);
                $("#delete").modal("show");
            })
            .fail(function (resp) {
                alert(" Xóa thất bại");
            })
    });
}