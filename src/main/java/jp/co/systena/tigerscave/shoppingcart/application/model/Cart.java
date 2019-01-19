package jp.co.systena.tigerscave.shoppingcart.application.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Order> orderlist = new ArrayList<Order>();

  public List<Order> getOrderlist() {
    return orderlist;
  }

  public void setOrderlist(List<Order> orderlist) {
    this.orderlist = orderlist;
  }

}
