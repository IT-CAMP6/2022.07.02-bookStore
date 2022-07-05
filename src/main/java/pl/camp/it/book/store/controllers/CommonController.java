package pl.camp.it.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.services.IBookService;
import pl.camp.it.book.store.services.impl.BookService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IBookService bookService;

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        if(this.sessionObject.getPattern() != null) {
            model.addAttribute("books", this.bookService.getFilteredBooks(this.sessionObject.getPattern()));
            model.addAttribute("pattern", this.sessionObject.getPattern());
        } else {
            model.addAttribute("books", this.bookService.getAllBooks());
        }
        this.authenticationService.addCommonInfoToModel(model);
        return "main";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String main(@RequestParam String pattern) {
        this.sessionObject.setPattern(pattern);
        return "redirect:/main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        this.authenticationService.addCommonInfoToModel(model);
        return "contact";
    }
}
