package jp.co.systena.tigerscave.shoppingcart.application.model;

public class Order {

//商品名
 private Item item;

 //個数
 private int num;

public Item getItem() {
  return item;
}

public void setItem(Item item) {
  this.item = item;
}

public int getNum() {
  return num;
}

public void setNum(int num) {
  this.num = num;
}

public void setItem(String name) {
  this.item.setName(name);
}

}
