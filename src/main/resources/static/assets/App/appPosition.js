class Position {
    constructor(name) {
        this.name = name;
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
                $("#modalEdit").modal("show");
            })
            .fail(function (resp) {
                window.location = "https://viblo.asia.vn";
            })
    });

    $("button .deletePosition").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/positions/" + id
        })
            .done(function (data) {

            })
            .fail(function (resp) {
                alert("Tải modal Xóa thất bại");
            })
    });
}