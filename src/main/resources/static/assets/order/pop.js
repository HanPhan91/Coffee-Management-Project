let listTables = $("#showTable ul");
let OrderId = 0;
let nameTable = null;
let NewTable = 0;
let history = {};
let order = [];
let totalAmount = 0;
let subAmount = 0;
let splitOrder = [];
let percent = 0;
let codeDiscount = null;
let listFind = [];

class OrderItem {
    constructor(quantity, price, id, name, discount) {
        this.quantity = quantity;
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}

$("#searchDrink").on('input', function () {
    let str = $(this).val().toLowerCase();
    if (str == '') {
        $("#showTable ul li").remove();
        showAllDrink(listDrink);
    } else {
        listFind = [];
        listDrink.forEach((element) => {
            if (element.name.toLowerCase().includes(str)) {
                listFind.push(element);
            }
            showAllDrink(listFind);
        });
    }

})

function showAllDrink(list) {
    $("#showTable ul li").remove();
    list.forEach(function (item) {
        if (item.imgUrl == null) {
            img = `<img _ngcontent-yaj-c29="" kvfallbackimg="" src="/assets/plugins/images/users/imagedrink.png">`
        } else {
            img = `<img _ngcontent-yaj-c29="" kvfallbackimg="" src="${item.imgUrl}">`;
        }
        listTables.append(`
                    <li class="tableAndRoom" style="text-align: center;" tabindex="2" data-id="${item.id}">
                        <div class="tableroom-actions"></div>
                        <a container="body" placement="right top" skip-disable=""
                           triggers="mouseenter:mouseleave" class="">
                            <div _ngcontent-yaj-c29="" class="product-img" style = "height: calc(100% - 40px);">
                                ${img}
                            </div>
                            <div class="product-info">
                                <span class="product-name">${item.name}</span>
                                <span class = "product-price">${App.formatNumberSpace(item.price)}</span>
                            </div>
                        </a>
                    </li>
                    `);
    });
    // handlerShowAvailableInOrder();
    handlerAddItemInOrder();
    checkStatusOrder();
}


$("#inputVoucher").on('input', function () {
    let str = $(this).val();
    discounts.forEach((element) => {
        if (element.code.toUpperCase() == str.toUpperCase()) {
            $("#percentDiscount").val(element.percentDiscount);
            codeDiscount = element.code.toUpperCase();
        } else {
            $("#percentDiscount").val(0);
        }
    })
    if (subAmount != 0) {
        percent = parseInt($("#percentDiscount").val()) / 100;
        totalAmount = subAmount - (subAmount * percent);
        showAmount();
    }
});
const handlerChooseTable = () => {
    if (OrderId != 0) {
        $("#divNameTable").css('display', 'block');
        $("#nameTable").text(nameTable);
    }
}

function checkStatusOrder() {
    if (order.length == 0) {
        $("#createBill").attr("disabled", "disabled");
        $("#btnSplitOrder").attr("disabled", "disabled");
    } else {
        $("#createBill").removeAttr("disabled");
        $("#btnSplitOrder").removeAttr("disabled");
    }
}

function getAllTable() {
    $.ajax({
        type: "GET",
        url: "/api/tables",
    })
        .done(function (data) {
            listTables.empty();
            let statusTable = '';
            data.forEach(function (item) {
                if (item.used == true) {
                    statusTable = `<span style="background: blue"></span>`;
                } else {
                    statusTable = `<span></span>`;
                }
                listTables.append(`
                    <li class="table" style="text-align: center;" tabindex="1" id="${item.id}">
                        <div class="tableroom-actions"></div>
                        <a container="body" placement="right top" skip-disable=""
                           triggers="mouseenter:mouseleave" class="">
                            <div class="table-room">${statusTable}</div>
                            <div class="product-info">
                            <span class="product-name">${item.name}</span>
                            </div>
                        </a>
                    </li>
                    `);
                let idtable = item.id;
                $("#" + idtable).on("click", function () {
                    getAllDrink();
                    OrderId = item.id;
                    nameTable = item.name;
                    handlerChooseTable();
                    $("a#showDrink").trigger('click');
                    // handlerShowAvailableInOrder();
                    $.ajax({
                        headers: {
                            "Accept": "application/json",
                            "Content-type": "application/json"
                        },
                        type: "GET",
                        url: "/api/orders/" + OrderId,
                    })
                        .done(function (data) {
                            order = data;
                            showOrderItem();
                            checkStatusOrder();
                        })
                        .fail(function (jqXHR) {
                            console.log("get drinks fails");
                        })
                        .done(function (data) {
                            order = data;
                            showOrderItem();
                            // handlerShowSplitOrder();
                        })
                        .fail(function (jqXHR) {
                            console.log("get drinks fails");
                        })
                    // $("#totalAmount").val()
                    $("#tableNumber").text("B??n " + item.name);
                    document.getElementById("showCart").style.display = "block";

                });

            })
        })
}

function getAllDrink() {
    $.ajax({
        type: "GET",
        url: "/api/drinks",
    })
        .done(function (data) {
            listDrink = data;
            listTables.empty();
            showAllDrink(listDrink);
            // let img;
            // data.forEach(function (item) {
            //     if (item.imgUrl == null) {
            //         img = `<img _ngcontent-yaj-c29="" kvfallbackimg="" src="/assets/plugins/images/users/imagedrink.png">`
            //     } else {
            //         img = `<img _ngcontent-yaj-c29="" kvfallbackimg="" src="${item.imgUrl}">`;
            //     }
            //     listTables.append(`
            //         <li class="tableAndRoom" style="text-align: center;" tabindex="2" data-id="${item.id}">
            //             <div class="tableroom-actions"></div>
            //             <a container="body" placement="right top" skip-disable=""
            //                triggers="mouseenter:mouseleave" class="">
            //                 <div _ngcontent-yaj-c29="" class="product-img" style = "height: calc(100% - 40px);">
            //                     ${img}
            //                 </div>
            //                 <div class="product-info">
            //                     <span class="product-name">${item.name}</span>
            //                     <span class = "product-price">${App.formatNumberSpace(item.price)}</span>
            //                 </div>
            //             </a>
            //         </li>
            //         `);
            // });
            // handlerShowAvailableInOrder();
            handlerAddItemInOrder();
        })
}

// th??m ????? u???ng v??o danh s??ch
function addOrderItemToOrder(orderId, drinkId) {
    for (let i = 0; i < listDrink.length; i++) {
        if (drinkId == listDrink[i].id) {
            if (order.length > 0) {
                let drinkExist = false;
                let index = 0;
                for (let j = 0; j < order.length; j++) {
                    if (drinkId == order[j].id) {
                        drinkExist = true;
                        index = j;
                    }
                }
                if (drinkExist) {
                    order[index].quantity += 1;
                } else {
                    let orderItem = new OrderItem(1, listDrink[i].price, drinkId, listDrink[i].name);
                    order.push(orderItem);
                }
            } else {
                let orderItem = new OrderItem(1, listDrink[i].price, drinkId, listDrink[i].name);
                order.push(orderItem);
            }
            showOrderItem();
        }
    }
}

// hi???n th??? ????? u???ng v???a ch???n v??o order
function showOrderItem() {
    let str = "";
    let show = $(".product-cart-item");
    show.empty();
    subAmount = 0;
    totalAmount = 0;
    for (let i = 0; i < order.length; i++) {
        subAmount += order[i].price * order[i].quantity;
        str += `
        <kv-cashier-cart-item class="row-list row-list-active">
            <div class="cell-action"><a
                    id = "${i}"
                    class="btn-icon btn-trash delete" href="javascript:void(0);"
                    title="X??a h??ng h??a"><i
                    class="far fa-trash-alt"></i></a></div>
            <div class="cell-order"> ${i + 1}
            </div>
            <div class="row-product">
                <div class="cell-name full" title="">
                    <div class="wrap-name">
                        <h4> ${order[i].name}</h4>
                        <span class="wrap-icons"></span>
                    </div>
                    <ul class="comboset-list-item"></ul>
                    <div class="list-topping">
                    </div>
                </div>
                <div class="cell-quatity">
                    <div class="cell-quantity-inner">
                        <button class="btn-icon down" type="button" data-id="${order[i].id}">
                            <i class="fas fa-minus-circle"></i>
                        </button>
                        <button class="form-control form-control-sm item-quantity">
                            ${order[i].quantity}
                        </button>
                        <button class="btn-icon up" type="button" data-id="${order[i].id}">
                            <i class="fas fa-plus-circle"></i>
                        </button>
                    </div>
                </div>
                <div class="cell-warning">
                </div>
                <div class="cell-change-price">
                    <div class="popup-anchor">
                        <button class="form-control form-control-sm">
                            ${App.formatNumberSpace(order[i].price)}
                        </button>
                    </div>
                </div>
                <div class="cell-price"> ${App.formatNumberSpace(order[i].quantity * order[i].price)}</div>
                <div class="cell-actions">
                </div>
            </div>
        </kv-cashier-cart-item>
        `
    }
    if (order[0].discount != null) {
        $("#inputVoucher").val(order[0].discount).trigger('input');
    }
    if (parseInt($("#percentDiscount").val()) != 0) {
        percent = parseInt($("#percentDiscount").val()) / 100;
        totalAmount = subAmount - (subAmount * percent);
    } else {
        totalAmount = subAmount;
    }

    show.append(str);
    showAmount();
    deleteItemInOrder();
    handlerUpQuantity();
    handlerDownQuantity();
    // handlerShowSplitOrder();
}

function showAmount() {
    $("#subAmount").text(App.formatNumberSpace(subAmount));
    $("#totalAmount").text(App.formatNumberSpace(totalAmount));
}

// xo?? ????? u???ng trong order
function deleteItemInOrder() {
    $(".delete").on("click", function () {
        let id = $(this).data("id");
        let index = order.indexOf((id));
        order.splice(index - 1, 1);
        showOrderItem();
    })
}

// s??? ki???n khi click v??o b??n
function handlerAddItemInOrder() {
    $(".tableAndRoom").on("click", function () {
        if (OrderId == 0) {
            swal("Warning", "Vui lo??ng cho??n ba??n tr??????c khi cho??n th????c u????ng", "warning");
        } else {
            let id = $(this).data("id");
            addOrderItemToOrder(OrderId, id);
        }
        // showOrderItem();
    });
}


//Hi???n th??? b??n s???n c?? + order hi???n c?? trong b??n


//Hi???n th??? b??n v?? th???c u???ng
$(".kv-tabs a").click(function () {
    $(".kv-tabs a").removeClass("active");
    $(this).addClass("active");
    let tabIndex = parseInt($(this).attr("data-tab-index"))
    switch (tabIndex) {
        case 1:
            getAllTable();
            $("#searchDrink").css('display', 'none');
            break;
        case 2:
            getAllDrink();
            $("#searchDrink").css('display', 'block');
            break;
        default:
            break;
    }
});

//S??? ki???n t??ng gi???m s??? l?????ng
function handlerUpQuantity() {
    $("button.up").on("click", function () {
        let id = $(this).data('id');
        let index = 0;
        for (let j = 0; j < order.length; j++) {
            if (id == order[j].id) {
                index = j;
            }
        }
        order[index].quantity += 1;
        showOrderItem();
    })
}

function handlerDownQuantity() {
    $("button.down").on("click", function () {
        let id = $(this).data('id');
        let index = 0;
        for (let j = 0; j < order.length; j++) {
            if (id == order[j].id) {
                console.log('index = ' + index)
                index = j;
            }
        }
        if (order[index].quantity == 1) {
            order.splice(index, 1);
        } else {
            order[index].quantity -= 1;
        }
        showOrderItem();
    })
}

$(document).ready(function () {
    getAllDrink();
    getAllTable();
    checkStatusOrder();
    handlerChooseTable();
    document.getElementById("showCart").style.display = "none";
    $("#showTables").trigger('click');
});

//T???o order
$("#createOrder").on('click', function () {
    let voucher = $("#inputVoucher").val();
    order.forEach((element) => element.discount = codeDiscount);
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "POST",
        url: "/api/orders/create/" + OrderId + "?discount=" + voucher,
        data: JSON.stringify(order)
    })
        .done(function (data) {
            swal("Th??nh c??ng", "T???o order th??nh c??ng", "success").then(function () {
                location.reload()
            });
        })
        .fail(function (resp) {
            let a = jQuery.parseJSON(resp.responseText);
            swal("L????i", `${a.message}`, "error");
        })
});
// Xu???t bill t??nh ti???n
$("#createBill").on('click', function () {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "PUT",
        url: "/api/orders/pay/" + OrderId,
    })
        .done(function (data) {
            $("label#nameTable").text(nameTable);
            order.forEach((element, index) => {
                $("#billdetails").append(
                    `<tr>
                        <td>${index +1}</td>
                        <td>${element.name}</td>
                        <td style="text-align: end;">${App.formatNumberSpace(element.price)}</td>
                        <td class="text-center">${element.quantity}</td>
                        <td style="text-align: end;">${App.formatNumberSpace(element.price * element.quantity)}</td>
                    </tr>`
                );
            })
            $("#billdetails").append(`
                <tr>
                    <td colspan="3" class="text-center" style="font-weight: bold;">T????ng ti????n (VN??): </td>
                    <td colspan="2" style="text-align: end;">${App.formatNumberSpace(subAmount)}</td>
                </tr>
                <tr>
                    <td class="text-center" colspan="2">Ma?? gia??m gia?? :</td>
                    <td  class="text-center">${codeDiscount != null ? codeDiscount : ''}</td>
                    <td class="text-center">M????c gia??m (%): </td>
                    <td class="text-center">${percent * 100}</td>
                </tr>
                <tr>
                    <td colspan="3" class="text-center" style="font-weight: bold;">Tha??nh ti????n (VN??): </td>
                    <td colspan="2" style="text-align: end;">${App.formatNumberSpace(totalAmount)}</td>
                </tr>
            `)
            $("#modalBillDetail").modal('show');
        })
        .fail(function (resp) {
            console.log(resp);
        })
});

// hi???n th??? form t??ch gh??p ????n
$("button#btnSplitOrder").on("click", function () {
    //Ajax ????? ra b??n chuy???n ?????n
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "/api/tables",
    })
        .done(function (data) {
            for (let i = 0; i < data.length; i++) {
                if (data[i].used == false) {
                    let id = data[i].id;
                    let name = data[i].name;
                    $("#catalogDropDownList").append(`<option value=${id}>${name}</option>`);
                }
            }
            callOrder();
            $("#modalSplitOrder").modal("show");
        })
        .fail(function (jqXHR) {
            console.log("get table fails");
        })
});

function callOrder() {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "/api/orders/" + OrderId,
    })
        .done(function (data) {
            order = data;
            splitOrder = JSON.parse(JSON.stringify(data));
            for (let i = 0; i < splitOrder.length; i++) {
                splitOrder[i].quantity = 0;
            }
            //danh s??ch ????? t??ch
            let newline = "";
            let oldList = $(".list-oldOrder");
            oldList.empty();
            for (let i = 0; i < data.length; i++) {
                newline += `
                                        <tr>
                                    <th scope="row">${i + 1}</th>
                                    <td>${data[i].name}</td>
                                    <td id = "quantity-${data[i].id}">${data[i].quantity}</td>
                                    <td>
                                        <button class="btn-icon downDrink" data-id="${data[i].id}" type="button" >
                                            <svg class="svg-inline--fa fa-circle-minus" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle-minus" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 256C0 114.6 114.6 0 256 0C397.4 0 512 114.6 512 256C512 397.4 397.4 512 256 512C114.6 512 0 397.4 0 256zM168 232C154.7 232 144 242.7 144 256C144 269.3 154.7 280 168 280H344C357.3 280 368 269.3 368 256C368 242.7 357.3 232 344 232H168z"></path></svg><!-- <i class="fas fa-minus-circle"></i> Font Awesome fontawesome.com -->
                                        </button>
                                        <button class="form-control form-control-sm item-quantity " id="splitAmount-${data[i].id}" >0</button>
                                        <button class="btn-icon upDrink" data-id="${data[i].id}"  type="button" >
                                            <svg class="svg-inline--fa fa-circle-plus" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle-plus" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 256C0 114.6 114.6 0 256 0C397.4 0 512 114.6 512 256C512 397.4 397.4 512 256 512C114.6 512 0 397.4 0 256zM256 368C269.3 368 280 357.3 280 344V280H344C357.3 280 368 269.3 368 256C368 242.7 357.3 232 344 232H280V168C280 154.7 269.3 144 256 144C242.7 144 232 154.7 232 168V232H168C154.7 232 144 242.7 144 256C144 269.3 154.7 280 168 280H232V344C232 357.3 242.7 368 256 368z"></path></svg><!-- <i class="fas fa-plus-circle"></i> Font Awesome fontawesome.com -->
                                        </button>
                                    </td>
                               </tr>
                            `
            }
            oldList.append(newline);
            handlerSplitUpDrink()
            handlerSplitDownDrink()

        })
        .fail(function (jqXHR) {
            console.log("get drinks fails");
        })
}

$("#modalSplitOrder").on('hidden.bs.modal', function () {
    $("#catalogDropDownList option").remove();
});

//s??? l?????ng ????? u???ng t??ch b??n
function handlerSplitUpDrink() {
    $("button.upDrink").on("click", function () {
        let id = $(this).data('id');
        let splitAmount = parseInt($("#splitAmount-" + id).text());
        let index = 0;
        for (let j = 0; j < order.length; j++) {
            if (id == order[j].id) {
                index = j;
            }
        }
        if (order[index].quantity > 0) {
            order[index].quantity -= 1;
            splitAmount += 1;
            splitOrder[index].quantity = splitAmount;
            $("#quantity-" + id).replaceWith(`
                <td id = "quantity-${id}">${order[index].quantity}</td>
            `);
            $("#splitAmount-" + id).replaceWith(`
                <button class="form-control form-control-sm item-quantity" id="splitAmount-${id}" >
                    ${splitAmount}
                </button>
            `);
        }
    })
}

function handlerSplitDownDrink() {
    $("button.downDrink").on("click", function () {

        let id = $(this).data('id');
        let splitAmount = parseInt($("#splitAmount-" + id).text());
        if (splitAmount > 0) {

            let index = 0;
            for (let j = 0; j < order.length; j++) {

                if (id == order[j].id) {
                    index = j;
                }
            }
            if (order[index].quantity >= 0) {
                order[index].quantity += 1;
                splitAmount -= 1;
                splitOrder[index].quantity = splitAmount;

                $("#quantity-" + id).replaceWith(`
                <td id = "quantity-${id}">${order[index].quantity}</td>
            `);
                $("#splitAmount-" + id).replaceWith(`
                <button class="form-control form-control-sm item-quantity" id="splitAmount-${id}" >
                    ${splitAmount}
                </button>
            `);
            }
        }

    })

}


$("#catalogDropDownList").on("change", function () {
    NewTable = $("#catalogDropDownList").val();
});

// S??? ki???n g???i ????? u???ng ???? t??ch
$("#confirmSplit").on('click', function () {
    let check = 0;
    if (splitOrder.length == 0) {
        check = 1;
    }
    if (NewTable == 0) {
        check = 2;
    }
    switch (check) {
        case 2:
            alert("Vui lo??ng cho??n ba??n ?????? ta??ch");
            handlerSplitDownDrink();
            handlerSplitUpDrink();
            break;
        case 0:
            for (let i = 0; i < splitOrder.length; i++) {
                if (splitOrder[i].quantity == 0) {
                    splitOrder.splice(i, 1);
                }
            }
            doSplit();
            break;
    }
});

function doSplit() {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "PUT",
        url: "/api/orders/split/" + OrderId + "/" + NewTable,
        data: JSON.stringify(splitOrder)
    })
        .done(function (data) {
            $("#modalSplitOrder").modal("hide");
            swal("Th??nh c??ng", "T???o order th??nh c??ng", "success").then(function () {
                location.reload()
            });
        })
        .fail(function (resp) {
            console.log(resp);
        })
}