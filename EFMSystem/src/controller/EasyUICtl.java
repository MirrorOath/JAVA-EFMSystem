package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.util.Count;
import dao.BillingDao;
import dao.UseRecordsDao;
import dao.UserInfoDao;
import dao.tables.Billing;
import dao.tables.UseRecords;
import dao.tables.UserInfo;

@Controller
@RequestMapping(value = "/easyUI/")
public class EasyUICtl {
    @Autowired
    private UseRecordsDao URDao;
    @Autowired
    private UserInfoDao userDao;
    @Autowired
    private BillingDao bilDao;

    @RequestMapping(value = "easyUIGetUsers")
    public @ResponseBody List<UserInfo> easyUIGetUsers(UserInfo userInfo) {
        List<UserInfo> usersInfo = userDao.getAll();
        for (UserInfo obj : usersInfo) {
            switch (obj.getRole()) {
            case 1:
                obj.setRoleStr("商家用户");
                break;
            case 0:
                obj.setRoleStr("居民用户");
                break;
            default:
                obj.setRoleStr("未知");
                break;
            }
        }
        return usersInfo;
    }

    @RequestMapping(value = "easyUISaveUser")
    public @ResponseBody UserInfo easyUISaveUser(UserInfo userInfo) {
        // 如果按用户名查询有结果,则返回false,注册失败
        if (userDao.getUserByName(userInfo.getUserName()) != null)
            return null;
        UserInfo obj = userDao.register(userInfo);
        switch (obj.getRole()) {
        case 1:
            obj.setRoleStr("商家用户");
            break;
        case 0:
            obj.setRoleStr("居民用户");
            break;
        default:
            obj.setRoleStr("未知");
            break;
        }
        return obj;
    }

    @RequestMapping(value = "easyUIUpdateUser")
    public @ResponseBody UserInfo easyUIUpdateUser(UserInfo userInfo) {
        UserInfo obj = userDao.update(userInfo.getId(), userInfo);
        switch (obj.getRole()) {
        case 1:
            obj.setRoleStr("商家用户");
            break;
        case 0:
            obj.setRoleStr("居民用户");
            break;
        default:
            obj.setRoleStr("未知");
            break;
        }
        return obj;
    }

    @RequestMapping(value = "easyUIDelUser")
    public @ResponseBody boolean easyUIDelUser(Integer id) {
        userDao.del(userDao.getById(id));
        return true;
    }

    @RequestMapping(value = "easyUIGetMeters")
    public @ResponseBody List<UseRecords> easyUIGetMeters() {
        List<UseRecords> Meters = URDao.getAll();
        for (UseRecords meter : Meters) {
            meter.setUserName(userDao.getById(meter.getUserId()).getUserName());
        }
        return Meters;
    }

    @RequestMapping(value = "easyUISaveMeter")
    public @ResponseBody UseRecords easyUISaveMeter(String userName, Integer curUsed, String date) {
        UseRecords meter = new UseRecords();
        meter.setUserId(userDao.getUserByName(userName).getId());
        meter.setCurUsed(curUsed);
        meter.setDate(Count.stringToDate(date));
        UseRecords rt = URDao.save(meter);
        rt.setUserName(userDao.getById(meter.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping(value = "easyUIUpdateMeter")
    public @ResponseBody UseRecords easyUIUpdateMeter(Integer id, String userName, Integer curUsed, String date) {
        UseRecords meter = URDao.getById(id);
        meter.setUserId(userDao.getUserByName(userName).getId());
        meter.setCurUsed(curUsed);
        meter.setDate(Count.stringToDate(date));
        UseRecords rt = URDao.update(id, meter);
        rt.setUserName(userDao.getById(meter.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping(value = "easyUIDelMeter")
    public @ResponseBody boolean easyUIDelMeter(Integer id) {
        userDao.del(userDao.getById(id));
        return true;
    }

    @RequestMapping("easyUIGetBillings")
    public @ResponseBody List<Billing> easyUIGetTableEndss() {
        List<Billing> bils = bilDao.getAll("Billing");
        for (Billing bil : bils) {
            bil.setUserName(userDao.getById(bil.getUserId()).getUserName());
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
        Billing rt = bilDao.save(obj);
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
        rt.setUserName(userDao.getById(rt.getUserId()).getUserName());
        return rt;
    }

    @RequestMapping("easyUIDelBilling")
    public @ResponseBody boolean easyUIDelTableEnds(Integer id) {
        bilDao.del(bilDao.getById("Billing", id));
        return true;
    }
}
