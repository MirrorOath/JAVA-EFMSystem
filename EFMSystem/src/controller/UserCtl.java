package controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String register(Model model, HttpSession session, UserInfo userInfo) {
        System.out.println("/user/register.action");
        System.out.println("user_name:" + userInfo.getUser_name() + "\r\npassword:" + userInfo.getPassword());

        if (userDao.register(userInfo) != null) {
            // session.setAttribute("registerRt", "注册成功");
            return "redirect:../user/registerSuccess.jsp";
        } else
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
                if ("admin".equals(user_name))
                    return "redirect:../index.jsp";
                session.setAttribute("userInfo", userInfo);
                session.setAttribute("unameNext", "退出登录");
                return searchRecords(model, session);
            } else {
                session.setAttribute("signInRt", "密码错误");
                break;
            }
        } while (false);
        return "redirect:../index.jsp";
    }

    @RequestMapping(value = "adminSignIn")
    public String adminSignIn(Model model, HttpSession session, String user_name, String password) {
        UserInfo userInfo = userDao.getUserByName(user_name);
        do {
            if (userInfo == null) {
                session.setAttribute("signInRt", "用户不存在");
                break;
            }
            if (userInfo.getPassword().equals(password)) {
                if (!"admin".equals(user_name))
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
        if (tactics == null || tactics == 0)
            return 0;
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
                // totalPrice += curPrice;
                System.out.println("Cur hour: " + hour + "\tCur used: " + eachHourUsed + "\tCur price: " + price[hour]
                        + "\tCur totalprice: " + totalPrice);
                // System.out.println(hour);
                hour++;
                if (hour == 24)
                    hour = 0;
            }
            BigDecimal b = new BigDecimal(totalPrice);
            totalPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Record.setCost(totalPrice);
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
        if (Records.isEmpty())
            return "redirect:../user/Homepage.jsp";
        for (UseResources i : Records) {
            System.out.println(i.toString());
        }
        session.setAttribute("Records", Records);
        // System.out.println("tactics 1: " + totalPrice(Records, 1));
        // System.out.println("tactics 2: " + totalPrice(Records, 2));
        session.setAttribute("tactics1", totalPrice(Records, 1));
        session.setAttribute("tactics2", totalPrice(Records, 2));
        totalPrice(Records, userInfo.getTactics());
        return "redirect:../user/Homepage.jsp";
    }

    @RequestMapping(value = "updateUserInfo")
    public String updateUserInfo(Model model, HttpSession session, UserInfo userInfo) {
        UserInfo oldUserInfo = userDao.getUserByName(userInfo.getUser_name());
        if (oldUserInfo == null || !oldUserInfo.getPassword().equals(userInfo.getOldPassword())) {
            return "redirect:../user/Homepage.jsp";
        }
        userDao.update(oldUserInfo.getId(), userInfo);
        return signIn(model, session, userInfo.getUser_name(), userInfo.getPassword());
    }

    @RequestMapping(value = "rg_lg_do")
    public String rg_lg_do(Model model, HttpSession session, String rorl) {
        // 登录状态
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo != null) {
            session.setAttribute("unameNext", "退出登录");
            // 点击用户名
            if ("login".equals(rorl)) {
                if ("admin".equals(userInfo.getUser_name()))
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

    private List<UseResources> delPastRecords(List<UseResources> Records) {
        Date date = new Date();
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        System.out.println(date);
        for (int i = Records.size() - 1; i >= 0; i--) {
            UseResources item = Records.get(i);
            if (date.after(item.getRcd_time())) {
                Records.remove(item);
            }
        }
        return Records;
    }

    @SuppressWarnings("deprecation")
    private BillingTable getBilTb(List<UseResources> Records, Integer tactics) {
        if (Records == null || tactics == null || tactics == 0)
            return null;
        Integer[] price = getPriceByTactics(tactics);
        Records = delPastRecords(Records);
        sortRecords(Records);
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
                // totalPrice += curPrice;
                System.out.println("Cur hour: " + hour + "\tCur used: " + eachHourUsed + "\tCur price: " + price[hour]
                        + "\tCur totalprice: " + totalPrice);
                // System.out.println(hour);
                hour++;
                if (hour == 24)
                    hour = 0;
            }
            BigDecimal b = new BigDecimal(totalPrice);
            totalPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Record.setCost(totalPrice);
            // System.out.println(off / (3600 * 1000));
            lastRecord = Record;
        }

        UseResources first = Records.get(0);
        UseResources last = Records.get(Records.size() - 1);
        BillingTable bt = new BillingTable();
        bt.setMonth(new Date().getMonth() + 1);
        bt.setTactics(tactics);
        bt.setCost(totalPrice);
        bt.setUsed(last.getCur_used() - first.getCur_used());
        return bt;
    }

    @RequestMapping(value = "MEBilling")
    public @ResponseBody BillingTable MEBilling(Model model, HttpSession session) {
        BillingTable rt = new BillingTable();
        rt.setMonth(0);
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null || userDao.getUserByID(userInfo.getId()) == null) {
            System.out.println("userInfo is null");
            return rt;
        }
        System.out.println(userInfo.toString());
        List<UseResources> urs = URDao.getRecordsByUserId(userInfo.getId());
        BillingTable bt = getBilTb(urs, userInfo.getTactics());
        System.out.println(bt);
        return bt;
    }

}
