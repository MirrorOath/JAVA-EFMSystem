package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.jspused.MonthUsed;
import controller.jspused.OneDayInfo;
import controller.util.Count;
import dao.BillingDao;
import dao.UseRecordsDao;
import dao.UserInfoDao;
import dao.tables.Billing;
import dao.tables.UseRecords;
import dao.tables.UserInfo;

@Controller
@RequestMapping(value = "/admin/")
public class AdminCtl {
    @Autowired
    private UseRecordsDao URDao;
    @Autowired
    private UserInfoDao userDao;
    @Autowired
    private BillingDao bilDao;

    @RequestMapping(value = "copyMeter")
    public String copyMeter(Model model, HttpSession session, String user_name, String dateStr, String copyNumber) {
        System.out.println("copyMeter:" + user_name + " " + dateStr + " " + copyNumber);
        UserInfo userInfo = userDao.getUserByName(user_name);
        do {
            if (userInfo == null) {
                model.addAttribute("copyMeterRt", "用户名不存在,录表失败");
                break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setLenient(false);
            Date date = null;
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                model.addAttribute("copyMeterRt", "日期格式无法解析,请参照yyyy-MM-dd HH:mm:ss");
                break;
            }
            System.out.println(date);

            System.out.println(userInfo.toString());
            UseRecords useResource = new UseRecords();
            useResource.setUserId(userInfo.getId());
            useResource.setDate(date);
            useResource.setCurUsed(Integer.valueOf(copyNumber));
            System.out.println(useResource.toString());
            URDao.save(useResource);
            model.addAttribute("copyMeterRt", "抄表记录  录入成功");
            return "../admin/CopyMeter.jsp";

        } while (false);
        return "../admin/CopyMeter.jsp";

        // return "redirect:../admin/CopyMeter.jsp";
    }

    @RequestMapping(value = "getAllUsers")
    public String getAllUsers(Model model, HttpSession session) {
        List<UserInfo> usersInfo = userDao.getAll();
        session.setAttribute("users", usersInfo);
        return "redirect:../admin/userInfo.jsp";
    }

    @RequestMapping(value = "delUser")
    public String delUser(Model model, HttpSession session, Integer userId) {
        userDao.del(userDao.getById(userId));
        return getAllUsers(model, session);
    }


    @SuppressWarnings("deprecation")
    @RequestMapping(value = "getBillings")
    public @ResponseBody List<Billing> getBillings(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null)
            return null;
        List<Billing> bils = bilDao.getByUserId(userInfo.getId());
        for (Billing bil : bils) {
            bil.setUserName(userInfo.getUserName());
            long days = (new Date().getTime() - bil.getDate().getTime()) / (3600 * 24 * 1000);
            System.out.println("days: " + days);
            if (bil.getIsPaid() != 1)
                bil.setExCost(days * bil.getCost() / 1000);
            if(bil.getDate().getMonth() > 0) {
                bil.setYear(bil.getDate().getYear() + 1900);
                bil.setMonth(bil.getDate().getMonth());
            }
            else {
                bil.setYear(bil.getDate().getYear() + 1900 - 1);
                bil.setMonth(12);
            }
        }
        return bils;
    }

    @RequestMapping(value = "payNow")
    public @ResponseBody boolean payNow(Model model, HttpSession session, Integer id, Double exCost) {
        if (id == null || exCost == null) {
            System.out.println("payNow argvs error");
            return false;
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Billing bil = bilDao.getById(id);
        if (userInfo == null || bil == null)
            return false;
        if (userInfo.getMoney() < (bil.getCost() + bil.getExCost()))
            return false;
        userInfo.setMoney(userInfo.getMoney() - bil.getCost() - bil.getExCost());
        userDao.update(userInfo.getId(), userInfo);
        bil.setIsPaid(1);
        bil.setExCost(exCost);
        bilDao.updateById(id, bil);
        return true;
    }

    @RequestMapping(value = "pushMoney")
    public @ResponseBody boolean pushMoney(Model model, HttpSession session, Integer Number) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null || Number == null)
            return false;
        userInfo.setMoney(userInfo.getMoney() + Number);
        userDao.update(userInfo.getId(), userInfo);
        return true;
    }

    private static Date ss(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "forDate")
    public @ResponseBody OneDayInfo forDate(Model model, HttpSession session, String date) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        OneDayInfo oi = new OneDayInfo();
        if (userInfo == null || date == null) {
            oi.setSuccess(false);
            return oi;
        }
        Date d = ss(date);
        // System.out.println(Count.getStringDate(d));
        Integer userId = userInfo.getId();
        UseRecords r0 = URDao.getRecordsByDateAndUserId(Count.getStringDate(d), userId);
        d.setHours(6);
        UseRecords r6 = URDao.getRecordsByDateAndUserId(Count.getStringDate(d), userId);
        d.setHours(22);
        UseRecords r22 = URDao.getRecordsByDateAndUserId(Count.getStringDate(d), userId);
        d.setHours(0);
        d.setDate(d.getDate() + 1);
        UseRecords n0 = URDao.getRecordsByDateAndUserId(Count.getStringDate(d), userId);
        oi.setLowUsed((r6.getCurUsed() - r0.getCurUsed()) + (n0.getCurUsed() - r22.getCurUsed()));
        oi.setHighUsed(r22.getCurUsed() - r6.getCurUsed());
        oi.setR0(r0);
        oi.setR6(r6);
        oi.setR22(r22);
        oi.setN0(n0);
        oi.setSuccess(true);
        return oi;
    }

    @RequestMapping(value = "getUserInfo")
    public @ResponseBody UserInfo getUserInfo(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null)
            return null;
        UserInfo ui = userDao.getById(userInfo.getId());
        session.removeAttribute("userInfo");
        session.setAttribute("userInfo", ui);
        System.out.println(ui);
        return ui;
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
        for (int i = 0; i <= 12; i++) {
            if(i > 0)
                date.setMonth(i - 1);
            else {
                date.setYear(date.getYear() - 1);
                date.setMonth(11);
            }
            lastMonth = URDao.getRecordsByDateAndUserId(getStringDate(date), userId);
            date.setYear(year);
            if(lastMonth == null)
                continue;
            date.setMonth(i);
            thisMonth = URDao.getRecordsByDateAndUserId(getStringDate(date), userId);
            if (thisMonth == null)
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
    @RequestMapping(value = "createBilling")
    public String createBilling(Model model, HttpSession session) {
        System.out.println("开始获得所有用户");
        List<UserInfo> users = userDao.getAll();
        List<MonthUsed> useds = new ArrayList<MonthUsed>();
        for (UserInfo user : users) {
            System.out.println("正在读取 " + user.getUserName() + " 的抄表记录");
            useds.addAll(getMonthUsed(new Date().getYear() - 1, user.getId()));
            useds.addAll(getMonthUsed(new Date().getYear(), user.getId()));
        }
        Billing bil = new Billing();
        Date date = new Date();
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        for (MonthUsed used : useds) {
            System.out.println("正在生成userID： " + used.getUserId() + "的账单");
            bil.setUserId(used.getUserId());
            bil.setTactics(userDao.getById(used.getUserId()).getTactics());
            date.setYear(used.getYear());
            date.setMonth(used.getMonth());
            bil.setDate(date);
            bil.setIsPaid(0);
            bil.setExCost(0.0);
            bil.setCurUsed(used.getUsed());
            if (bil.getTactics() == 2)
                bil.setCost(used.getUsed() * 2.0);
            else
                bil.setCost(used.getUsed() * 1.66);
            bilDao.save(bil);
        }
        return "redirect:../admin/control.jsp";
    }


}
