package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println("/user/register.action");
        System.out.println("user_name:" + userInfo.getUserName() + "\r\npassword:" + userInfo.getPassword());

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
                return "redirect:../user/Homepage.jsp";
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

/*    private Integer[] getPriceByTactics(Integer tactics) {
        List<Billing> rules = BilSDao.getRulesByTactics(tactics);
        Integer[] price = new Integer[24];
        for (Billing i : rules) {
            for (Integer j = 0; j < 24; j++) {
                if (j >= i.getTime_start() && j < i.getTime_end())
                    price[j] = i.getPrice();
            }
        }
        return price;
    }*/

/*    private void sortRecords(List<UseResourds> Records) {
        Collections.sort(Records, new Comparator<UseResourds>() {
            public int compare(UseResourds o1, UseResourds o2) {
                UseResourds stu1 = (UseResourds) o1;
                UseResourds stu2 = (UseResourds) o2;
                if (stu1.getRcd_time().after(stu2.getRcd_time())) {
                    return 1;
                } else if (stu1.getRcd_time().equals(stu2.getRcd_time())) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
    }*/
/*
    @SuppressWarnings({ "deprecation" })
    private double totalPrice(List<UseResourds> Records, Integer tactics) {
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
        UseResourds lastRecord = null;
        double totalPrice = 0;
        double eachHourUsed = 0;
        int hour = 0;
        long off = 0;
        for (UseResourds Record : Records) {
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
    }*/
/*
    @RequestMapping(value = "searchRecords")
    public String searchRecords(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null)
            return "redirect:../index.jsp";
        session.removeAttribute("Records");
        List<UseResourds> Records = URDao.getRecordsByUserId(userInfo.getId());
        if (Records.isEmpty())
            return "redirect:../user/Homepage.jsp";
        for (UseResourds i : Records) {
            System.out.println(i.toString());
        }
        session.setAttribute("Records", Records);
        // System.out.println("tactics 1: " + totalPrice(Records, 1));
        // System.out.println("tactics 2: " + totalPrice(Records, 2));
        session.setAttribute("tactics1", totalPrice(Records, 1));
        session.setAttribute("tactics2", totalPrice(Records, 2));
        totalPrice(Records, userInfo.getTactics());
        return "redirect:../user/Homepage.jsp";
    }*/

    @RequestMapping(value = "updateUserInfo")
    public String updateUserInfo(Model model, HttpSession session, UserInfo userInfo) {
        UserInfo oldUserInfo = userDao.getUserByName(userInfo.getUserName());
        if (oldUserInfo == null || !oldUserInfo.getPassword().equals(userInfo.getOldPassword())) {
            return "redirect:../user/Homepage.jsp";
        }
        userDao.update(oldUserInfo.getId(), userInfo);
        return signIn(model, session, userInfo.getUserName(), userInfo.getPassword());
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

    @SuppressWarnings("deprecation")
    private List<UseResourds> delPastRecords(List<UseResourds> Records) {
        Date date = new Date();
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        System.out.println(date);
        for (int i = Records.size() - 1; i >= 0; i--) {
            UseResourds item = Records.get(i);
            if (date.after(item.getDate())) {
                Records.remove(item);
            }
        }
        return Records;
    }
/*
    @SuppressWarnings("deprecation")
    private BillingTable getBilTb(List<UseResourds> Records, Integer tactics) {
        if (Records == null || tactics == null || tactics == 0)
            return null;
        Integer[] price = getPriceByTactics(tactics);
        Records = delPastRecords(Records);
        sortRecords(Records);
        UseResourds lastRecord = null;
        double totalPrice = 0;
        double eachHourUsed = 0;
        int hour = 0;
        long off = 0;
        for (UseResourds Record : Records) {
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

        UseResourds first = Records.get(0);
        UseResourds last = Records.get(Records.size() - 1);
        BillingTable bt = new BillingTable();
        bt.setMonth(new Date().getMonth() + 1);
        bt.setTactics(tactics);
        bt.setCost(totalPrice);
        bt.setUsed(last.getCur_used() - first.getCur_used());
        return bt;
    }*/

/*    @RequestMapping(value = "MEBilling")
    public @ResponseBody BillingTable MEBilling(Model model, HttpSession session) {
        BillingTable rt = new BillingTable();
        rt.setMonth(0);
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null || userDao.getUserByID(userInfo.getId()) == null) {
            System.out.println("userInfo is null");
            return rt;
        }
        System.out.println(userInfo.toString());
        List<UseResourds> urs = URDao.getRecordsByUserId(userInfo.getId());
        BillingTable bt = getBilTb(urs, userInfo.getTactics());
        System.out.println(bt);
        return bt;
    }*/

/*    @RequestMapping(value = "pushMoney")
    public @ResponseBody boolean pushMoney(Model model, HttpSession session, Integer Number) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        System.out.println("Number: " + Number);
        if (userInfo == null || userDao.getUserByID(userInfo.getId()) == null)
            return false;
        userInfo.setMoney(userInfo.getMoney() + Number);
        System.out.println(userInfo.toString());
        userDao.update(userInfo.getId(), userInfo);
        return true;
    }

    @RequestMapping(value = "getMoney")
    public @ResponseBody MoneyTable getMoney(Model model, HttpSession session) {
        MoneyTable rt = new MoneyTable();
        rt.setSuccess(false);
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null || userDao.getUserByID(userInfo.getId()) == null) {
            System.out.println("userInfo is null");
            return rt;
        }
        rt.setHistory(userInfo.getMoney());
        BigDecimal b = new BigDecimal(rt.getHistory() - totalPrice(URDao.getRecordsByUserId(userInfo.getId()), userInfo.getTactics()));
        
        rt.setCur_money(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        
        BillingTable bt = getBilTb(URDao.getRecordsByUserId(userInfo.getId()), userInfo.getTactics());
        rt.setThismonthcost(bt.getCost());

        rt.setSuccess(true);
        return rt;
    }*/

}
