let listTables = $("#showTable ul");

function getAllDrink(){
    $.ajax({
        type: "GET",
        url: "/api/drinks",
    })
        .done(function(data){
                listTables.empty();
                data.forEach(function (item) {
                    listTables.append(`
                    <li class="tableAndRoom" style="text-align: center;" tabindex="1" id="${item.id}">
                        <div class="tableroom-actions"></div>
                        <a container="body" placement="right top" skip-disable=""
                           triggers="mouseenter:mouseleave" class="">
                            <div class="table-room"><span></span></div>
                            <div class="product-info">
                            <span class="product-name">${item.name}</span>
                            <span class = "product-price">${item.price}</span>
                                <div class="wrap-note" href="javascript:void(0)">
                                    <label>
                                        <button class="btn-icon">
                                            <span class="note-hint">Ghi chú...</span>
                                            <i class="fa fa-pencil"></i>
                                        </button>
                                    </label>
                                </div>
                            </div>
                        </a>
                    </li>
                    `);
                })
        })
}

function getAllTable() {
    $.ajax({
        type: "GET",
        url: "/api/tables",

    })
        .done(function (data) {
            listTables.empty();
            data.forEach(function (item) {
                listTables.append(`
                    <li class="tableAndRoom" style="text-align: center;" tabindex="1" id="${item.id}">
                        <div class="tableroom-actions"></div>
                        <a container="body" placement="right top" skip-disable=""
                           triggers="mouseenter:mouseleave" class="">
                            <div class="table-room"><span></span></div>
                            <div class="product-info">
                            <span class="product-name">${item.name}</span>
                                <div class="wrap-note" href="javascript:void(0)">
                                    <label>
                                        <button class="btn-icon">
                                            <span class="note-hint">Ghi chú...</span>
                                            <i class="fa fa-pencil"></i>
                                        </button>
                                    </label>
                                </div>
                            </div>
                        </a>
                    </li>
                    `);
                let id = item.id;
                $("#" + id).on("click", function () {
                    getAllDrink();
                    document.getElementById("showCart").style.display = "block";

                });


                function showCart(){


                }


            })
        })
}

function handlerShowModalCreate(){

}


$(".kv-tabs a").click(function () {
    $(".kv-tabs a").removeClass("active");
    $(this).addClass("active");
    let tabIndex = parseInt($(this).attr("data-tab-index"))

    switch (tabIndex) {
        case 1:
            getAllTable();

            break;
        case 2:
            getAllDrink();
            break;
        default:
            break;
    }
});


$(document).ready(function () {
    getAllTable();
    getAllDrink();
    document.getElementById("showCart").style.display = "none";

});


