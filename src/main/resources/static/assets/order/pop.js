let listTables = $("#showTable ul");
var OrderId = 0;
var listDrink;
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





function Drink(id,name,quantity,price){
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
}

for (let i = 0; i < listDrink.length; i++) {
    Drinks.push(new Drink(listDrink[i].id, listDrink[i].drinkName, 0, listDrink[i].price));
}

function showDrink() {
    for (let i = 0; i < Drinks.length; i++) {
        let str =
            `    >
         <!-- hien thi drink -->
                                            <kv-cashier-cart-item class="row-list row-list-active">
                                                <div class="cell-action"><a
                                                        class="btn-icon btn-trash" href="javascript:void(0);"
                                                        title="Xóa hàng hóa"><i
                                                        class="far fa-trash-alt"></i></a></div>
                                                <div class="cell-order"> 1
                                                    <div>
                                                        <button class="btn-icon" type="button"><i
                                                                class="fa fa-star-o"></i></button>
                                                    </div>
                                                </div>
                                                <div class="row-product">
                                                    <div class="cell-name full" title="">
                                                        <div class="wrap-name">
                                                            <h4> ${Drinks[i].name}</h4><span class="wrap-icons"></span>
                                                        </div>
                                                        <ul class="comboset-list-item"></ul>
                                                        <div class="wrap-note" href="javascript:void(0)"></div>
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
                                                                    class="form-control form-control-sm"> 30,000
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="cell-price"> 30,000</div>
                                                    <div class="cell-actions">
                                                        <div class="btn-group" dropdown="">
                                                            <button class="dropdown-toggle" type="button"
                                                                    title="Thêm dòng"><i class="far fa-plus"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </kv-cashier-cart-item>
         <!-- hien thi drink -->
                                        </div>
                <tr id="tr_${Drinks[i].id}">
                    <input value="${Drinks[i].id}" id="idDrinkPre" hidden>
                    <td>${Drinks[i].name}</td>
                    <td class="text-center">
                        <button type="button" class="btn btn-outline-primary addCart"
                                data-id="${Drinks[i].id}">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </td>
                </tr>`;
        $("#tblDrink tbody").append(str);

    }
    handlerButtonAddCart();
}


class Order {
    constructor(id, quantity, totalPrice, drink, table){
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.drink = drink;
        this.table = table;
    }

}
function handlerButtonAddCart() {
    $("button.addCart").on("click", function () {
        let id = $(this).data("id");
        addToCart(id);
    });
}

function addToCart(id) {
    let drink = Drinks.find(function (drink, index) {
        return drink.drinkId == id;
    });
    let check = checkCart(drink);
    if (check == true) {
        drink.drinkQuantity = 1;
        Carts.push(drink);
        showCart(drink);
        submitCart();
    }
}


function showOrderItem() {

    let str = `<div className="product-cart-item" id="${OrderId}">`;
    for (let i = 0; i < order.length; i++) {
        str += `
        <kv-cashier-cart-item class="row-list row-list-active">
                                                <div class="cell-action"><a
                                                        class="btn-icon btn-trash" href="javascript:void(0);"
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
                                                                    class="form-control form-control-sm"> ${order[i].price}
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
    str += `</div>`
}

function addOrderItemToOrder(cartId, drinkId) {
    for (let i = 0; i < listDrink.length; i++) {
        if (drinkId == listDrink[i].id) {
            order.push(listDrink[i]);
            showOrderItem();
        }
    }
}

function handlerShowItemInOrder() {
    $(".tableAndRoom").on("click", function () {
        let id = $(this).data("id");
        addOrderItemToOrder(OrderId, id);
    });
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
                    <li class="table" style="text-align: center;" tabindex="1" id="${item.id}">
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

                        $.ajax({
                            type: "GET",
                            url: "/api/carts/" + id,

                        })
                            .done(function (data) {

                                OrderId = data.id;
                                if (data.cartItem.length == 0) {
                                    let emptyStr = `<div _ngcontent-aqe-c6="" class="page-empty"><i _ngcontent-aqe-c6="" class="mask mask-food"></i><div _ngcontent-aqe-c6="" class="empty-content" translate=""> Chưa có món nào <span _ngcontent-aqe-c6="" translate="">Vui lòng chọn món trong thực đơn</span></div></div>`
                                    $("#listCartItem").html(emptyStr);

                                } else {
                                   //  //Vòng lặp Cart Item
                                   //  data.forEach(function (item) {
                                   //
                                   //      listCartItem.append(`
                                   //  <div class="cell-action"><a
                                   //                          class="btn-icon btn-trash" href="javascript:void(0);"
                                   //                          title="Xóa hàng hóa"><i
                                   //                          class="far fa-trash-alt"></i></a></div>
                                   //                  <div class="cell-order"> 1
                                   //
                                   //                      <div>
                                   //                          <button class="btn-icon" type="button"><i
                                   //                                  class="fa fa-star-o"></i></button>
                                   //                      </div>
                                   //                  </div>
                                   //                  <div class="row-product">
                                   //                      <div class="cell-name full" title="">
                                   //                          <div class="wrap-name">
                                   //                              <h4> ${item.name}
                                   //
                                   //
                                   //                              </h4><span class="wrap-icons">
                                   //
                                   // </span>
                                   //                          </div>
                                   //
                                   //                          <ul class="comboset-list-item">
                                   //
                                   //                          </ul>
                                   //                          <div class="wrap-note" href="javascript:void(0)"><label
                                   //                          >
                                   //                              <button class="btn-icon has-Update">
                                   //
                                   //                                  <span class="note-hint">Ghi chú...</span>
                                   //                                  <i class="fa fa-pencil"></i>
                                   //                              </button>
                                   //                          </label></div>
                                   //                          <div class="list-topping">
                                   //
                                   //                          </div>
                                   //                      </div>
                                   //
                                   //                      <div class="cell-quatity">
                                   //                          <div class="cell-quantity-inner">
                                   //                              <button class="btn-icon down" type="button"><i
                                   //                                      class="fas fa-minus-circle"></i></button>
                                   //
                                   //                              <button
                                   //                                      class="form-control form-control-sm item-quantity">
                                   //                                  1
                                   //                              </button>
                                   //                              <button class="btn-icon up" type="button"><i
                                   //                                      class="fas fa-plus-circle"></i></button>
                                   //
                                   //
                                   //                          </div>
                                   //                      </div>
                                   //                      <div class="cell-warning">
                                   //
                                   //                      </div>
                                   //
                                   //                      <div class="cell-change-price">
                                   //                          <div class="popup-anchor">
                                   //                              <button
                                   //                                      class="form-control form-control-sm"> ${item.price}
                                   //                              </button>
                                   //
                                   //                          </div>
                                   //                      </div>
                                   //                      <div class="cell-price">
                                   //                      <label for="cartItemPrice" class="form-label fw-bold">Tổng tiền</label>
                                   //                      <input type="text" class="form-control" id="cartItemPrice" name="cartItemPrice">
                                   //                       </div>
                                   //                      <div class="cell-actions">
                                   //                          <div class="btn-group" dropdown="">
                                   //                              <button class="dropdown-toggle" type="button"
                                   //                                      title="Thêm dòng"><i class="far fa-plus"></i>
                                   //                              </button>
                                   //                          </div>
                                   //                      </div>
                                   //                  </div>
                                   //  `)
                                   //  });
                                    //Hết vòng lặp cart item
                                }
                            })

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


    // $(document).ready(function () {
        getAllTable();
        getAllDrink();
        document.getElementById("showCart").style.display = "none";

    // });



