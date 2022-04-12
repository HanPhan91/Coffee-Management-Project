class Discount{
    constructor(code, description, percentDiscount, createdAt, endedAt, quantity) {
        this.code = code;
        this.description = description;
        this.percentDiscount = percentDiscount;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
        this.quantity = quantity;
    }
}

function handlerActionDiscount() {
    $("button.editDiscount").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/discount/" + id
        })
            .done(function (data) {
                $("#idDiscountEdit").val(data.id);
                $("#codeDiscountEdit").val(data.code);
                $("#descriptionDisCountEdit").val(data.description);
                $("#percentDiscountEdit").val(data.percentDiscount);
                $("#createdAtDiscountEdit").val(data.createdAt);
                $("#endedAtDiscountEdit").val(data.endedAt);
                $("#quantityDiscountEdit").val(data.quantity);
                $("#editDiscount").modal("show");
            })
            .fail(function (resp) {
                window.location = "https://viblo.asia.vn";
            })
    });

    $("button.deleteDiscount").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/discount/" + id
        })
            .done(function (data) {
                $("#idDiscountDelete").val(data.id);
                $("#codeDiscountDelete").val(data.code);
                $("#descriptionDisCountDelete").val(data.description);
                $("#percentDiscountDelete").val(data.percentDiscount);
                $("#createdAtDisCountDelete").val(data.createdAt);
                $("#endedAtDiscountDelete").val(data.endedAt);
                $("#quantityDisCountDelete").val(data.quantity);

                $("#deleteDiscount").modal("show");
            })
            .fail(function (resp) {
                alert("Tải modal Xóa thất bại");
            })
    });
}