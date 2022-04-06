let listTables = $("#showTable ul");
var OrderId = 0;
var history = {};
var order=[];

function getAllDrink() {
    $.ajax({
        type: "GET",
        url: "/api/drinks",
    })
        .done(function (data) {
            listDrink = data;
            listTables.empty();
            data.forEach(function (item) {
                listTables.append(`
                    <li class="tableAndRoom" style="text-align: center;" tabindex="1" data-id="${item.id}">
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
            });
            handlerShowItemInOrder();
        })
}

class OrderItem {
    constructor( quantity, totalPrice, drink,name){
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.drink = drink;
        this.name = name;
    }

}


// thêm đồ uống vào danh sách
function addOrderItemToOrder(orderId, drinkId) {
    for (let i = 0; i < listDrink.length; i++) {
        if (drinkId == listDrink[i].id) {
            let orderItem = new OrderItem(1,listDrink[i].price,drinkId,listDrink[i].name);
            order.push(orderItem);
            showOrderItem();
        }
    }
}

// hiển thị danh sách đồ uống trong order
function showOrderItem() {
    let str = "";
    let show =$(".product-cart-item");
    // let show = `<div className="product-cart-item" id="${OrderId}">`;
    show.empty();
    for (let i = 0; i < order.length; i++) {
        str += `
        <kv-cashier-cart-item class="row-list row-list-active">
            <div class="cell-action"><a
                    id = "${i}"
                    class="btn-icon btn-trash delete"   href="javascript:void(0);"
                    title="Xóa hàng hóa"><i
                    class="far fa-trash-alt"></i></a></div>
            <div class="cell-order"> ${i+1}
            </div>
            <div class="row-product">
                <div class="cell-name full" title="">
                    <div class="wrap-name">
                        <h4> ${order[i].name}</h4><span class="wrap-icons"></span>
                    </div>
                    <ul class="comboset-list-item"></ul>
                    <div class="list-topping">
                    </div>
                </div>
                <div class="cell-quatity">
                    <div class="cell-quantity-inner">
                        <button class="btn-icon down" type="button"><i
                                class="fas fa-minus-circle"></i></button>
                        <button class="form-control form-control-sm item-quantity">
                            1
                        </button>
                        <button class="btn-icon up" type="button"><i
                                class="fas fa-plus-circle"></i></button>
                    </div>
                </div>
                <div class="cell-warning">
                </div>
                <div class="cell-change-price">
                    <div class="popup-anchor">
                        <button
                                class="form-control form-control-sm"> ${order[i].totalPrice}
                        </button>
                    </div>
                </div>
                <div class="cell-price"> total Amount</div>
                <div class="cell-actions">
                    <div class="btn-group" dropdown="">
                        <button class="dropdown-toggle" type="button"
                                title="Thêm dòng"><i class="far fa-plus"></i>
                        </button>
                    </div>
                </div>
            </div>
        </kv-cashier-cart-item>
        `
    }
    show.append(str);
    deleteItemInOrder();
}

// xoá đồ uống trong order
function deleteItemInOrder(){
    $(".delete").on("click",function(){
        let id = $(this).data("id");
        let index = order.indexOf((id));
        order.splice(index-1,1);
        showOrderItem();
    })
}

// sự kiện khi click vào bàn
function handlerShowItemInOrder() {
    $(".tableAndRoom").on("click", function () {
        let id = $(this).data("id");
        addOrderItemToOrder(OrderId, id);
        showOrderItem();
    });
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
                if (item.used == true){
                    statusTable = `<span style="background: blue"></span>`;
                }
                else {
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
                let idtable = item.id;
                $("#" + idtable).on("click", function () {
                    getAllDrink();
                    OrderId = item.id;


                    // $.ajax({
                    //     type: "POST",
                    //     url: "/api/orders/" + idtable,
                    //
                    // })
                    //     .done(function (data) {
                    //
                    //         OrderId = data.id;
                    //         if (data.orderItem.length == 0) {
                    //             let emptyStr = `<div _ngcontent-aqe-c6="" class="page-empty"><i _ngcontent-aqe-c6="" class="mask mask-food"></i><div _ngcontent-aqe-c6="" class="empty-content" translate=""> Chưa có món nào <span _ngcontent-aqe-c6="" translate="">Vui lòng chọn món trong thực đơn</span></div></div>`
                    //             $("#listCartItem").html(emptyStr);
                    //
                    //         } else {
                    //            //  //Vòng lặp Cart Item
                    //            //  data.forEach(function (item) {
                    //            //
                    //            //      listCartItem.append(`
                    //            //  <div class="cell-action"><a
                    //            //                          class="btn-icon btn-trash" href="javascript:void(0);"
                    //            //                          title="Xóa hàng hóa"><i
                    //            //                          class="far fa-trash-alt"></i></a></div>
                    //            //                  <div class="cell-order"> 1
                    //            //
                    //            //                      <div>
                    //            //                          <button class="btn-icon" type="button"><i
                    //            //                                  class="fa fa-star-o"></i></button>
                    //            //                      </div>
                    //            //                  </div>
                    //            //                  <div class="row-product">
                    //            //                      <div class="cell-name full" title="">
                    //            //                          <div class="wrap-name">
                    //            //                              <h4> ${item.name}
                    //            //
                    //            //
                    //            //                              </h4><span class="wrap-icons">
                    //            //
                    //            // </span>
                    //            //                          </div>
                    //            //
                    //            //                          <ul class="comboset-list-item">
                    //            //
                    //            //                          </ul>
                    //            //                          <div class="wrap-note" href="javascript:void(0)"><label
                    //            //                          >
                    //            //                              <button class="btn-icon has-Update">
                    //            //
                    //            //                                  <span class="note-hint">Ghi chú...</span>
                    //            //                                  <i class="fa fa-pencil"></i>
                    //            //                              </button>
                    //            //                          </label></div>
                    //            //                          <div class="list-topping">
                    //            //
                    //            //                          </div>
                    //            //                      </div>
                    //            //
                    //            //                      <div class="cell-quatity">
                    //            //                          <div class="cell-quantity-inner">
                    //            //                              <button class="btn-icon down" type="button"><i
                    //            //                                      class="fas fa-minus-circle"></i></button>
                    //            //
                    //            //                              <button
                    //            //                                      class="form-control form-control-sm item-quantity">
                    //            //                                  1
                    //            //                              </button>
                    //            //                              <button class="btn-icon up" type="button"><i
                    //            //                                      class="fas fa-plus-circle"></i></button>
                    //            //
                    //            //
                    //            //                          </div>
                    //            //                      </div>
                    //            //                      <div class="cell-warning">
                    //            //
                    //            //                      </div>
                    //            //
                    //            //                      <div class="cell-change-price">
                    //            //                          <div class="popup-anchor">
                    //            //                              <button
                    //            //                                      class="form-control form-control-sm"> ${item.price}
                    //            //                              </button>
                    //            //
                    //            //                          </div>
                    //            //                      </div>
                    //            //                      <div class="cell-price">
                    //            //                      <label for="cartItemPrice" class="form-label fw-bold">Tổng tiền</label>
                    //            //                      <input type="text" class="form-control" id="cartItemPrice" name="cartItemPrice">
                    //            //                       </div>
                    //            //                      <div class="cell-actions">
                    //            //                          <div class="btn-group" dropdown="">
                    //            //                              <button class="dropdown-toggle" type="button"
                    //            //                                      title="Thêm dòng"><i class="far fa-plus"></i>
                    //            //                              </button>
                    //            //                          </div>
                    //            //                      </div>
                    //            //                  </div>
                    //            //  `)
                    //            //  });
                    //             //Hết vòng lặp cart item
                    //         }
                    //     })

                    document.getElementById("showCart").style.display = "block";

                });

            })
        })
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

$("#createOrder").on('click', function () {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "POST",
        url: "/api/orders/create/"+ OrderId + "?discount=",
        data: JSON.stringify(order)
    })
        .done(function (data) {
            swal("thành công", "tạo order thành công","success");
        })
        .fail(function (resp){
            console.log(resp);
        })
});

$("#createBill").on('click',function (){
    $.ajax({
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type:"PUT",
        url: "/api/orders/pay/"+ OrderId,
    })
        .done(function (data) {
            swal("thành công","tạo hoá đơn thành công")
        })
        .fail(function (resp){
            console.log(resp);
        })
});
