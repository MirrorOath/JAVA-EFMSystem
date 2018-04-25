package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.*;
import dao.tables.*;

@Controller
@RequestMapping(value = "/admin/")
public class AdminCtl {
    @Autowired
    private UseResourdsDao URDao;
    @Autowired
    private UserInfoDao userDao;

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
            UseResourds useResource = new UseResourds();
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
        List<UserInfo> usersInfo = userDao.users();
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
        List<UserInfo> usersInfo = userDao.users();
        return usersInfo;
    }

    @RequestMapping(value = "easyUISaveUser")
    public @ResponseBody UserInfo easyUISaveUser(Model model, HttpSession session, String user_name, String password,
            Integer age, Integer tactics, String address) {
        UserInfo us = new UserInfo();
        us.setAddress(address);
        us.setUserName(user_name);
        us.setPassword(password);
        us.setTactics(tactics);
        UserInfo usersInfo = userDao.register(us);
        return usersInfo;
    }

    @RequestMapping(value = "easyUIUpdateUser")
    public @ResponseBody UserInfo easyUIUpdateUser(Model model, HttpSession session, String user_name, String password,
            Integer age, Integer tactics, Integer id, String address) {
        UserInfo us = new UserInfo();
        us.setId(id);
        us.setAddress(address);
        us.setUserName(user_name);
        us.setPassword(password);
        us.setTactics(tactics);
        System.out.println(us.toString());
        UserInfo usersInfo = userDao.update(id, us);
        return usersInfo;
    }

    @RequestMapping(value = "easyUIDelUser")
    public @ResponseBody boolean easyUIDelUser(Model model, HttpSession session, Integer id) {
        userDao.delUser(id);
        return true;
    }

    @RequestMapping(value = "easyUIGetMeters")
    public @ResponseBody List<UseResourds> easyUIGetMeters(Model model, HttpSession session, UserInfo userInfo) {
        List<UseResourds> Meters = URDao.getAllMeters();
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
//            model.addAttribute("copyMeterRt", "日期格式无法解析,请参照yyyy-MM-dd HH:mm:ss");
//            break;
        }
        return date;
    }

    @RequestMapping(value = "easyUISaveMeter")
    public @ResponseBody UseResourds easyUISaveMeter(Model model, HttpSession session, Integer user_id,
            Integer cur_used, String rcd_time) {
        UseResourds us = new UseResourds();
        us.setUserId(user_id);
        us.setCurUsed(cur_used);
        us.setDate(stringToDate(rcd_time));
        UseResourds ur = URDao.addRecord(us);
        return ur;
    }

    @RequestMapping(value = "easyUIUpdateMeter")
    public @ResponseBody UseResourds easyUIUpdateMeter(Model model, HttpSession session, Integer user_id,
            Integer cur_used, Integer id, String rcd_time) {
        UseResourds us = new UseResourds();
        us.setId(id);
        us.setUserId(user_id);
        us.setCurUsed(cur_used);
        us.setDate(stringToDate(rcd_time));
        System.out.println(us.toString());
        UseResourds ur = URDao.update(id, us);
        return ur;
    }

    @RequestMapping(value = "easyUIDelMeter")
    public @ResponseBody boolean easyUIDelMeter(Model model, HttpSession session, Integer id) {
        userDao.delUser(id);
        return true;
    }

}
