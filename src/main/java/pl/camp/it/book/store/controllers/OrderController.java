package pl.camp.it.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.services.IOrderService;

@Controller
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
    public String confirmOrder() {
        this.orderService.confirmOrder();
        //TODO zmiana na podstronę z orderami
        return "redirect:/main";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orders(Model model) {
        this.authenticationService.addCommonInfoToModel(model);
        model.addAttribute("orders", this.orderService.getOrdersForCurrentUser());
        return "orders";
    }
}
