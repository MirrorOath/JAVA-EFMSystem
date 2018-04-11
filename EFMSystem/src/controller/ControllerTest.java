package controller;

//import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserInfo;
import dao.UserInfoDao;

@Controller
@RequestMapping(value = "/user/")
public class ControllerTest {
    @Autowired
    private UserInfoDao userDao;

    @RequestMapping(value = "hi")
    public String hi(Model model, HttpSession session, String rorl) {
        System.out.println("you called hi");
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("test");
        userInfo.setUser_id(0xFFFF);
        userInfo.setPassword("test");
        System.out.println(userInfo.toString());
        userDao.Test(userInfo);
        if (session.getAttribute("test") == null) {
            model.addAttribute("test", "test");
        }
        return "../index.jsp";
    }

}
