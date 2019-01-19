package jp.co.systena.tigerscave.shoppingcart.application.model;

public class ListForm {

  private String name;

//  private String item;

  private Item item;

  private int praice;

  private int num;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getPraice() {
    return praice;
  }

  public void setPraice(int praice) {
    this.praice = praice;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }



}
