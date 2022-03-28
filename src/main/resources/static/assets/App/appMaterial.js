class Material {
    constructor(name, createdAt, price, quantity, user) {
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
    }
}

function handlerActionMaterial() {
    $("button.editMaterial").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/materials/" + id
        })
            .done(function (data) {
                $("#idMaterialEdit").val(data.id);
                $("#nameMaterialEdit").val(data.name);
                $("#priceMaterialEdit").val(data.price);
                $("#quantityMaterialEdit").val(data.quantity);

                $("#editMaterial").modal("show");
            })
            .fail(function (resp) {
                window.location = "https://viblo.asia.vn";
            })
    });

}