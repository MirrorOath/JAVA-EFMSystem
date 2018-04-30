package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.jspused.MonthUsed;
import dao.BillingDao;
import dao.UseResourdsDao;
import dao.UserInfoDao;
import dao.tables.*;

@Controller
@RequestMapping(value = "/user/")
public class UserCtl {
    @Autowired
    private UseResourdsDao URDao;
    @Autowired
    private UserInfoDao userDao;
    @Autowired
    private BillingDao BilSDao;

    @RequestMapping(value = "register")
    public String register(Model model, HttpSession session, UserInfo userInfo) {
        System.out.println(userInfo.toString());
        System.out.println("userName:" + userInfo.getUserName() + "\r\npassword:" + userInfo.getPassword());

        if (userDao.register(userInfo) != null) {
            // session.setAttribute("registerRt", "注册成功");
            return "redirect:../user/registerSuccess.jsp";
        } else
            session.setAttribute("registerRt", "注册失败");

        return "redirect:../index.jsp";
    }

    @RequestMapping(value = "signIn")
    public String signIn(Model model, HttpSession session, String userName, String password) {
        UserInfo userInfo = userDao.getUserByName(userName);
        do {
            if (userInfo == null) {
                session.setAttribute("signInRt", "用户不存在");
                break;
            }
            if (userInfo.getPassword().equals(password)) {
                if ("admin".equals(userName))
                    return "redirect:../index.jsp";
                session.setAttribute("userInfo", userInfo);
                session.setAttribute("unameNext", "退出登录");
                return "redirect:../user/Homepage.jsp";
            } else {
                session.setAttribute("signInRt", "密码错误");
                break;
            }
        } while (false);
        return "redirect:../index.jsp";
    }

    @RequestMapping(value = "adminSignIn")
    public String adminSignIn(Model model, HttpSession session, String userName, String password) {
        UserInfo userInfo = userDao.getUserByName(userName);
        do {
            if (userInfo == null) {
                session.setAttribute("signInRt", "用户不存在");
                break;
            }
            if (userInfo.getPassword().equals(password)) {
                if (!"admin".equals(userName))
                    return "redirect:../index.jsp";
                session.setAttribute("userInfo", userInfo);
                session.setAttribute("unameNext", "退出登录");
                return "redirect:../admin/control.jsp";
            } else {
                session.setAttribute("signInRt", "密码错误");
                break;
            }
        } while (false);
        return "redirect:../index.jsp";
    }

    @RequestMapping(value = "updateUserInfo")
    public String updateUserInfo(Model model, HttpSession session, UserInfo userInfo) {
        UserInfo oldUserInfo = userDao.getUserByName(userInfo.getUserName());
        if (oldUserInfo == null || !oldUserInfo.getPassword().equals(userInfo.getOldPassword())) {
            return "redirect:../user/Homepage.jsp";
        }
        userDao.update(oldUserInfo.getId(), userInfo);
        return signIn(model, session, userInfo.getUserName(), userInfo.getPassword());
    }

    private static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    @SuppressWarnings("deprecation")
    private List<MonthUsed> getMonthUsed(Integer year, Integer userId) {
        Date date = new Date();
        date.setYear(year);
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        UseRecords lastMonth = null;
        UseRecords thisMonth = null;
        List<MonthUsed> mu = new ArrayList<MonthUsed>();
        for (int i = 1; i <= 12; i++) {
            date.setMonth(i - 1);
            lastMonth = URDao.getRecordsByDateAndUserId(getStringDate(date), userId);
            date.setMonth(i);
            thisMonth = URDao.getRecordsByDateAndUserId(getStringDate(date), userId);
            if (lastMonth == null || thisMonth == null)
                continue;
            MonthUsed m = new MonthUsed();
            m.setYear(year);
            m.setMonth(i);
            m.setUsed(thisMonth.getCurUsed() - lastMonth.getCurUsed());
            m.setUserId(userId);
            mu.add(m);
        }
        return mu;
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "searchRecords")
    public String searchRecords(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null)
            return "redirect:../index.jsp";
        List<MonthUsed> mu = getMonthUsed(new Date().getYear(), userInfo.getId());
        for (MonthUsed m : mu) {
            System.out.println(m);
        }
        session.setAttribute("monthsUsed", mu);
        return "../user/Homepage.jsp";
    }

    @RequestMapping(value = "rg_lg_do")
    public String rg_lg_do(Model model, HttpSession session, String rorl) {
        // 登录状态
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo != null) {
            session.setAttribute("unameNext", "退出登录");
            // 点击用户名
            if ("login".equals(rorl)) {
                if ("admin".equals(userInfo.getUserName()))
                    return "redirect:../admin/control.jsp";
                return "redirect:../user/Homepage.jsp";
            }
            // 点击退出登录
            else if ("register".equals(rorl)) {
                session.removeAttribute("userInfo");
                return "redirect:../user/login.jsp";
            } else
                return "redirect:../index.jsp";
        }
        // 非登录状态
        else {
            if ("register".equals(rorl))
                return "redirect:../user/register.jsp";
            else if ("login".equals(rorl)) {
                session.setAttribute("lastUrl", "redirect:../index.jsp");
                return "redirect:../user/login.jsp";
            } else
                return "redirect:../index.jsp";
        }
    }

//    @SuppressWarnings({ "deprecation", "unused" })
//    private List<UseRecords> delPastRecords(List<UseRecords> Records) {
//        Date date = new Date();
//        date.setDate(1);
//        date.setHours(0);
//        date.setMinutes(0);
//        date.setSeconds(0);
//        System.out.println(date);
//        for (int i = Records.size() - 1; i >= 0; i--) {
//            UseRecords item = Records.get(i);
//            if (date.after(item.getDate())) {
//                Records.remove(item);
//            }
//        }
//        return Records;
//    }

}
