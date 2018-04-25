package controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.util.UtilFactory;

@Controller
@RequestMapping(value = "/special/")
public class SpecialCtl {

    @RequestMapping("conBase")
    public @ResponseBody void conBase() {
        Session session = UtilFactory.getSession();
        session.close();
//        System.out.println("conBase");
    }
}
