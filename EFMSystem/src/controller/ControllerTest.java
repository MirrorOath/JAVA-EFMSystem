package controller;

//import java.util.List;

import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user/")
public class ControllerTest {

    @RequestMapping(value = "hi")
    public String hi(Model model, HttpSession session, String rorl) {
        System.out.println("you called hi");
        if (session.getAttribute("test") == null) {
            model.addAttribute("test", "test");

        }
        return "redirect:../index.jsp";
    }

}
