package com.cssl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cssl.pojo.Option;
import com.cssl.pojo.Ticket;
import com.cssl.pojo.User;
import com.cssl.service.OptionService;
import com.cssl.service.StatisticsService;
import com.cssl.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class AdminController {
    @Resource
    private TicketService ticketService;
    @Resource
    private OptionService optionService;
    @Resource
    private StatisticsService statisticsService;
    /*
    * 新增主题跳转验证用户权限
    * */
    @RequestMapping("addVote")
    public String addVote(HttpSession session, Model model){
        User user=(User) session.getAttribute("userwjj");
        if(user.getU_type()==1){
            return "addVote";
        }else{
            model.addAttribute("mess","用户权限不足，请联系管理员！");
            return "No";
        }
    }
    /*
    * 新增主题
    * */
    @RequestMapping("xzVote")
    public String xzVote(String type,String[] options,String title,Model model){
        System.out.println("类型"+type);
        System.out.println("标题"+title);
        Ticket t=new Ticket();
        t.setT_title(title);
        t.setT_type(Integer.valueOf(type));
//        Collection<Option> collection=new ArrayList<>();
        if(ticketService.save(t)){
            QueryWrapper<Ticket> q=new QueryWrapper<>();
            q.eq("t_title",title);
            Ticket t1=ticketService.getOne(q);
            for (int i = 0; i < options.length; i++) {
                System.out.println("下标:"+i);
                System.out.println("数据"+options[i]);
                Option option=new Option();
                option.setT_id(t1.getT_id());
                option.setO_name(options[i]);
                option.setO_px(i+1);
                System.out.println(option);
                if(optionService.addd(option)<=0){
                    model.addAttribute("mess","新增失败");
                }
            }
        }
        model.addAttribute("mess","新增成功");
        return "No";
    }
    /*
    * 删除主题
    * */
    @ResponseBody
    @RequestMapping("/delVote")
    public int delVote(String idd){
        System.out.println("idd-----------"+idd);
        QueryWrapper<Ticket> q=new QueryWrapper<>();
        q.eq("t_id",idd);
        if(ticketService.delVote(q)>0){
            return 0;
        }else {
            return 1;
        }
    }
    /*
    * 修改主题t跳转
    * */
    @RequestMapping("weihuByid")
    public String findVote_weihu(String tid,Model model){
        System.out.println("需要修改的id是-----"+tid);
        System.out.println("tid" + tid);
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        q.eq("t.t_id", Integer.valueOf(tid));
        IPage<Ticket> vote = ticketService.findTicks(new Page<>(1, 10), q);
        model.addAttribute("vote", vote);
        return  "upVote";
    }
    /*
    * 修改主题数据提交
    * */
    @RequestMapping("xgVote")
    public String xgVote(Ticket ticket, String[] options,String[] o_id,Model model){
        for (int i = 0; i < options.length; i++) {
            System.out.println("选项名称为:i------"+options[i]);
        }
        System.out.println("o_id.length为"+o_id.length);
        System.out.println("options.length"+options.length);

        System.out.println("更改的主题为————————"+ticket);
        if(ticketService.updateById(ticket)){
            for (int i = 0; i < o_id.length; i++) {
                System.out.println("选项id为:i------"+o_id[i]);
                Option option=new Option();
                option.setO_name(options[i]);
                option.setT_id(ticket.getT_id());
                option.setO_id(Integer.valueOf(o_id[i]));
                option.setO_px(i+1);
                System.out.println("修改第"+i+"个选项"+option);
                if(optionService.up(option)>0){
                    model.addAttribute("mess","修改成功");
                }else {
                    model.addAttribute("mess","修改失败");
                }
            }
            //6个options  4个o_id
            //
            for (int i = o_id.length; i < options.length; i++) {
                Option option=new Option();
                option.setO_name(options[i]);
                option.setT_id(ticket.getT_id());
                option.setO_px(i+1);
                System.out.println("新增第"+i+"个选项"+option);
                if(optionService.addd(option)>0){
                    model.addAttribute("mess","修改成功");
                }else {
                    model.addAttribute("mess","修改失败");
                }
            }
        }else {
            model.addAttribute("mess","修改失败");
        }
        return "No";
    }
}
