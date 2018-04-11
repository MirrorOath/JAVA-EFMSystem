package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserInfo;
import dao.UserInfoDao;



@Controller
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UserInfoDao userDao;
    
    @RequestMapping(value = "register")
    public String register(Model model, HttpSession session,
            String user_name, String password) {
        System.out.println("/user/register.action");
        System.out.println("user_name:"+user_name+
                "\r\npassword:"+password);
           
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(user_name);
        userInfo.setPassword(password);
        if(userDao.register(userInfo))
            session.setAttribute("registerRt", "注册成功");
        else
            session.setAttribute("registerRt", "注册失败");
        

        return "../index.jsp";
    }
    
    @RequestMapping(value = "signIn")
    public String signIn(Model model, HttpSession session,
            String user_name, String password){
        UserInfo userInfo = userDao.getUserByName(user_name);
        do {
          if(userInfo == null) {
              session.setAttribute("signInRt", "用户不存在");
              break;
          }
          if(userInfo.getPassword().equals(password))  {
              session.setAttribute("signInRt", "验证通过");
              return "redirect:../user.jsp";
          }
          else
          {
              session.setAttribute("signInRt", "密码错误");
              break;
          }
        } while(false);
        return "redirect:../index.jsp";
    }
    
}
