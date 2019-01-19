package jp.co.systena.tigerscave.shoppingcart.application.model;

import java.util.ArrayList;
import java.util.List;

public class ListService {

  String[] itemlist = {"消しゴム","シャーペン","定規","ノート"};
  int[] price = {30,150,100,40};

  public List<Item> getItemList() {



    List<Item> items = new ArrayList<>();

    for(int i = 0; i < itemlist.length; i++) {
      Item item = new Item();
      item.setName(itemlist[i]);
      item.setPrice(price[i]);

      items.add(item);
    }

    return items;
  }

  public int getTotalPrice(String item, int num){
    int total = 0;
    for(int i = 0; i < 4; i++) {
      if(item == itemlist[i]) {
         return price[i]*num;
      }
    }



    return total;
  }

}
