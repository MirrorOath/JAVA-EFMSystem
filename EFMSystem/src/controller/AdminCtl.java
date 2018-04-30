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
import dao.*;
import dao.tables.*;
import dao.util.UtilDao;

@Controller
@RequestMapping(value = "/admin/")
public class AdminCtl {
    @Autowired
    private UtilDao<Billing> bilUDao;
    @Autowired
    private UseResourdsDao URDao;
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
            URDao.addRecord(useResource);
            model.addAttribute("copyMeterRt", "抄表记录  录入成功");
            return "../admin/CopyMeter.jsp";

        } while (false);
        return "../admin/CopyMeter.jsp";

        // return "redirect:../admin/CopyMeter.jsp";
    }

    @RequestMapping(value = "getAllUsers")
    public String getAllUsers(Model model, HttpSession session) {
        List<UserInfo> usersInfo = userDao.getAllUsers();
        session.setAttribute("users", usersInfo);
        return "redirect:../admin/userInfo.jsp";
    }

    @RequestMapping(value = "delUser")
    public String delUser(Model model, HttpSession session, Integer userId) {
        userDao.delUser(userId);
        return getAllUsers(model, session);
    }

    @RequestMapping(value = "easyUIGetUsers")
    public @ResponseBody List<UserInfo> easyUIGetUsers(Model model, HttpSession session, UserInfo userInfo) {
        List<UserInfo> usersInfo = userDao.getAllUsers();
        for (UserInfo obj : usersInfo) {
            if(obj.getRole() == null) {
                obj.setRoleStr("未知");
            }
            else if (obj.getRole() == 1)
                obj.setRoleStr("商家用户");
            else if (obj.getRole() == 0)
                obj.setRoleStr("居民用户");
            else
                obj.setRoleStr("未知");
        }
        return usersInfo;
    }

    @RequestMapping(value = "easyUISaveUser")
    public @ResponseBody UserInfo easyUISaveUser(Model model, HttpSession session, UserInfo userInfo) {
        UserInfo obj = userDao.register(userInfo);
        if (obj.getRole() == 1)
            obj.setRoleStr("商家用户");
        else if (obj.getRole() == 0)
            obj.setRoleStr("居民用户");
        else
            obj.setRoleStr("未知");
        return obj;
    }

    @RequestMapping(value = "easyUIUpdateUser")
    public @ResponseBody UserInfo easyUIUpdateUser(Model model, HttpSession session, UserInfo userInfo) {
        System.out.println(userInfo.toString());
        UserInfo obj = userDao.update(userInfo.getId(), userInfo);
        if (obj.getRole() == 1)
            obj.setRoleStr("商家用户");
        else if (obj.getRole() == 0)
            obj.setRoleStr("居民用户");
        else
            obj.setRoleStr("未知");
        return obj;
    }

    @RequestMapping(value = "easyUIDelUser")
    public @ResponseBody boolean easyUIDelUser(Model model, HttpSession session, Integer id) {
        userDao.delUser(id);
        return true;
    }

    @RequestMapping(value = "easyUIGetMeters")
    public @ResponseBody List<UseRecords> easyUIGetMeters(Model model, HttpSession session, UserInfo userInfo) {
        List<UseRecords> Meters = URDao.getAllMeters();
        for (UseRecords meter : Meters) {
            meter.setUserName(userDao.getUserByID(meter.getUserId()).getUserName());
            System.out.println(meter.toString());
        }
        return Meters;
    }

    private Date stringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            // model.addAttribute("copyMeterRt", "日期格式无法解析,请参照yyyy-MM-dd HH:mm:ss");
            // break;
        }
        return date;
    }

    @RequestMapping(value = "easyUISaveMeter")
    public @ResponseBody UseRecords easyUISaveMeter(Model model, HttpSession session, String userName, Integer curUsed,
            String date) {
        UseRecords meter = new UseRecords();
        meter.setUserId(userDao.getUserByName(userName).getId());
        meter.setCurUsed(curUsed);
        meter.setDate(stringToDate(date));
        UseRecords rt = URDao.addRecord(meter);
        rt.setUserName(userDao.getUserByID(meter.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping(value = "easyUIUpdateMeter")
    public @ResponseBody UseRecords easyUIUpdateMeter(Model model, HttpSession session, String userName,
            Integer curUsed, Integer id, String date) {
        UseRecords meter = new UseRecords();
        meter.setUserId(userDao.getUserByName(userName).getId());
        meter.setCurUsed(curUsed);
        meter.setDate(stringToDate(date));
        System.out.println(meter.toString());
        UseRecords rt = URDao.update(id, meter);
        rt.setUserName(userDao.getUserByID(meter.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping(value = "easyUIDelMeter")
    public @ResponseBody boolean easyUIDelMeter(Model model, HttpSession session, Integer id) {
        userDao.delUser(id);
        return true;
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
            bil.setYear(bil.getDate().getYear() + 1900);
            bil.setMonth(bil.getDate().getMonth());
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
        UserInfo ui = userDao.getUserByID(userInfo.getId());
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
    @RequestMapping(value = "createBilling")
    public String createBilling(Model model, HttpSession session) {
        List<UserInfo> users = userDao.getAllUsers();
        List<MonthUsed> useds = new ArrayList<MonthUsed>();
        for (UserInfo user : users) {
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
            System.out.println(used.getUserId());
            bil.setUserId(used.getUserId());
            bil.setTactics(userDao.getUserByID(used.getUserId()).getTactics());
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

    @RequestMapping("easyUIGetBillings")
    public @ResponseBody List<Billing> easyUIGetTableEndss() {
        List<Billing> bils = bilUDao.getAll("Billing");
        for (Billing bil : bils) {
            bil.setUserName(userDao.getUserByID(bil.getUserId()).getUserName());
        }
        return bils;
    }

    @RequestMapping("easyUISaveBilling")
    public @ResponseBody Billing easyUISaveTableEnds(String userName, Integer tactics, Integer curUsed, Double cost,
            Double exCost, Integer isPaid, String date) {
        Billing obj = new Billing();
        obj.setUserId(userDao.getUserByName(userName).getId());
        obj.setTactics(tactics);
        obj.setCurUsed(curUsed);
        obj.setCost(cost);
        obj.setExCost(exCost);
        obj.setDate(Count.stringToDate(date));
        obj.setIsPaid(isPaid);
        Billing rt = bilUDao.save(obj);
        return rt;
    }

    @RequestMapping("easyUIUpdateBilling")
    public @ResponseBody Billing easyUIUpdateTableEnds(Integer id, String userName, Integer tactics, Integer curUsed,
            Double cost, Double exCost, Integer isPaid, String date) {
        Billing obj = new Billing();
        obj.setUserId(userDao.getUserByName(userName).getId());
        obj.setTactics(tactics);
        obj.setCurUsed(curUsed);
        obj.setCost(cost);
        obj.setExCost(exCost);
        obj.setDate(Count.stringToDate(date));
        obj.setIsPaid(isPaid);
        Billing rt = bilDao.updateById(id, obj);
        rt.setUserName(userDao.getUserByID(rt.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping("easyUIDelBilling")
    public @ResponseBody boolean easyUIDelTableEnds(Integer id) {
        bilUDao.del(bilUDao.getById("Billing", id));
        return true;
    }

}
