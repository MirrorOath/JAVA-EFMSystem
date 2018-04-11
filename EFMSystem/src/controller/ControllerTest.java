package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserInfo;
import dao.UserInfoDao;

@Controller
@RequestMapping(value = "/test/")
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
//        userDao.Test(userInfo);
        UserInfo s = userDao.getUserByName("t");
        if(s != null)
            System.out.println(s.toString());
        else
            System.out.println("This user_name is not true");
        if (session.getAttribute("test") == null) {
            model.addAttribute("test", "test");
        }
        return "../index.jsp";
    }

}
