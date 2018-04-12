package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BillingStrategy;
import dao.BillingStrategyDao;
import dao.UseResources;
import dao.UseResourcesDao;
import dao.UserInfo;
import dao.UserInfoDao;

@Controller
@RequestMapping(value = "/user/")
public class UserCtl {
    @Autowired
    private UseResourcesDao URDao;
    @Autowired
    private UserInfoDao userDao;
    @Autowired
    private BillingStrategyDao BilSDao;

    @RequestMapping(value = "register")
    public String register(Model model, HttpSession session, String user_name, String password) {
        System.out.println("/user/register.action");
        System.out.println("user_name:" + user_name + "\r\npassword:" + password);

        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(user_name);
        userInfo.setPassword(password);
        if (userDao.register(userInfo))
            session.setAttribute("registerRt", "注册成功");
        else
            session.setAttribute("registerRt", "注册失败");

        return "redirect:../index.jsp";
    }

    @RequestMapping(value = "signIn")
    public String signIn(Model model, HttpSession session, String user_name, String password) {
        UserInfo userInfo = userDao.getUserByName(user_name);
        do {
            if (userInfo == null) {
                session.setAttribute("signInRt", "用户不存在");
                break;
            }
            if (userInfo.getPassword().equals(password)) {
                session.setAttribute("signInRt", "验证通过");
                session.setAttribute("userInfo", userInfo);
                return "redirect:../user/Homepage.jsp";
            } else {
                session.setAttribute("signInRt", "密码错误");
                break;
            }
        } while (false);
        return "redirect:../index.jsp";
    }

    private Integer[] getPriceByTactics(Integer tactics) {
        List<BillingStrategy> rules = BilSDao.getRulesByTactics(tactics);
        Integer[] price = new Integer[24];
        for (BillingStrategy i : rules) {
            for (Integer j = 0; j < 24; j++) {
                if (j >= i.getTime_start() && j < i.getTime_end())
                    price[j] = i.getPrice();
            }
        }
        return price;
    }

    private void sortRecords(List<UseResources> Records) {
        Collections.sort(Records, new Comparator<UseResources>() {
            public int compare(UseResources o1, UseResources o2) {
                UseResources stu1 = (UseResources) o1;
                UseResources stu2 = (UseResources) o2;
                if (stu1.getRcd_time().after(stu2.getRcd_time())) {
                    return 1;
                } else if (stu1.getRcd_time().equals(stu2.getRcd_time())) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
    }

    @SuppressWarnings({ "deprecation" })
    private double totalPrice(List<UseResources> Records, Integer tactics) {
        Integer[] price = getPriceByTactics(tactics);
        // Integer j = 0;
        // for(Integer i:price) {
        // System.out.println("hours" + j++ + "\t: " + price[i]);
        // }
        sortRecords(Records);
        // for(UseResources Record : Records) {
        // System.out.println(Record.toString());
        // }
        UseResources lastRecord = null;
        double totalPrice = 0;
        double eachHourUsed = 0;
        int hour = 0;
        long off = 0;
        for (UseResources Record : Records) {
            if (lastRecord == null) {
                lastRecord = Record;
                hour = Record.getRcd_time().getHours();
                // System.out.println(hour);
                continue;
            }
            off = Record.getRcd_time().getTime() - lastRecord.getRcd_time().getTime();
            System.out.println("This cross time used: " + (Record.getCur_used() - lastRecord.getCur_used()));
            eachHourUsed = (double) (Record.getCur_used() - lastRecord.getCur_used()) / (double) (off / (3600 * 1000));
            for (int s = 0; s < off; s += (3600 * 1000)) {
                totalPrice += price[hour] * eachHourUsed;
                System.out.println("Cur hour: " + hour + "\tCur used: " + eachHourUsed + "\tCur price: " + price[hour]
                        + "\tCur totalprice: " + totalPrice);
                // System.out.println(hour);
                hour++;
                if (hour == 24)
                    hour = 0;
            }
            // System.out.println(off / (3600 * 1000));
            lastRecord = Record;
        }
        return totalPrice;
    }

    @RequestMapping(value = "searchRecords")
    public String searchRecords(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null)
            return "redirect:../index.jsp";
        session.removeAttribute("Records");
        List<UseResources> Records = URDao.getRecordsByUserId(userInfo.getId());
        for (UseResources i : Records) {
            System.out.println(i.toString());
        }
        session.setAttribute("Records", Records);
        System.out.println("tactics 1: " + totalPrice(Records, 1));
        System.out.println("tactics 2: " + totalPrice(Records, 2));
        session.setAttribute("tactics1", totalPrice(Records, 1));
        session.setAttribute("tactics2", totalPrice(Records, 2));
        return "redirect:../user/Homepage.jsp";
    }

}
