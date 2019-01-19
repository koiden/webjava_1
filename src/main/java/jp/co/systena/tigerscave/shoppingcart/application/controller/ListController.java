package jp.co.systena.tigerscave.shoppingcart.application.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcart.application.model.Item;
import jp.co.systena.tigerscave.shoppingcart.application.model.ListForm;
import jp.co.systena.tigerscave.shoppingcart.application.model.ListService;
import jp.co.systena.tigerscave.shoppingcart.application.model.Order;

@Controller
public class ListController {

  @Autowired
  HttpSession session;                  // セッション管理

  @RequestMapping(value="/listview", method = RequestMethod.GET)  // URLとのマッピング
  public ModelAndView show(ModelAndView mav) {

    // Viewに渡すデータを設定
    // セッション情報から保存したデータを取得してメッセージを生成
    ListForm listForm = (ListForm) session.getAttribute("form");
    session.removeAttribute("form");
    if (listForm != null) {
      mav.addObject("message", listForm.getItem()+"をカートに追加しました");
    }
    mav.addObject("listForm", new ListForm());  // 新規クラスを設定

    //セッション情報をOrderに格納
    List<Order> orders = (List<Order>) session.getAttribute("orderList");
    if( orders == null) {
      orders = new ArrayList<Order>();
        session.setAttribute("orderList", orders);
    }
    mav.addObject("orders", orders);

    //商品一覧の固定
    ListService listService = new ListService();              // 一覧取得サービス
    List<Item> items = listService.getItemList();
    mav.addObject("items",items);

    //合計金額の計算
    int totalPrice = 0;
    for (Order item : orders) {
      totalPrice += listService.getTotalPrice(item.getItem().toString(),item.getNum());
    }
    mav.addObject("total",totalPrice);

    mav.setViewName("listview");

    return mav;
  }

  @RequestMapping(value="/listview", method = RequestMethod.POST)  // URLとのマッピング
  private ModelAndView order(ModelAndView mav, @Valid ListForm listForm, BindingResult bindingResult, HttpServletRequest request) {

    //セッション情報をOrderに格納
    List<Order> orders = (List<Order>)session.getAttribute("orderList");
    if( orders == null) {
      orders = new ArrayList<Order>();
      session.setAttribute("orderList", orders);
    }

    if (bindingResult.getAllErrors().size() > 0) {
      // エラーがある場合はそのまま戻す
      mav.addObject("listForm",listForm);  // 新規クラスを設定
      mav.addObject("orders", orders);
      // Viewのテンプレート名を設定
      mav.setViewName("listview");
      return mav;

    }

    Order order = new Order();
    order.setItem(listForm.getName());
    order.setNum(listForm.getNum());
    orders.add(order);

//    Cart cart = new Cart();
//    cart.setOrderlist(orders);

    // データをセッションへ保存
    session.setAttribute("form", listForm);

    return new ModelAndView("redirect:/listview");        // リダイレクト
  }
}
