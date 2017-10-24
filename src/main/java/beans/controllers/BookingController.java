package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class BookingController {

    @RequestMapping("/check")
    public String helloWorld() {
        return "helloWorld";
    }
}
