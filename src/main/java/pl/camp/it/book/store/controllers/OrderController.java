package pl.camp.it.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.services.IOrderService;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
    public String confirmOrder() {
        this.orderService.confirmOrder();
        return "redirect:/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orders(Model model) {
        this.authenticationService.addCommonInfoToModel(model);
        model.addAttribute("orders", this.orderService.getOrdersForCurrentUser());
        return "orders";
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String order(@PathVariable int id, Model model) {
        this.authenticationService.addCommonInfoToModel(model);
        Optional<Order> orderBox = this.orderService.getOrderById(id);
        if(orderBox.isPresent()) {
            List<OrderPosition> orderPositionList = this.orderService.getOrderPositionsByOrderId(id);
            model.addAttribute("order", orderBox.get());
            model.addAttribute("orderPositions", orderPositionList);
            model.addAttribute("orderSum", this.orderService.calculateOrderSum(orderPositionList));
            return "order";
        }
        return "redirect:/main";
    }
}
