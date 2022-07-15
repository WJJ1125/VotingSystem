package com.cssl.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cssl.pojo.Statistics;
import com.cssl.pojo.Ticket;
import com.cssl.pojo.User;
import com.cssl.service.StatisticsService;
import com.cssl.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class voteListController {

    @Resource
    private TicketService ticketService;
    @Resource
    private StatisticsService statisticsService;
    /*
    * 当前页数
    * */
    private Integer nowIndex=1;
    /*
    * 总页数
    * */
    private Integer pages=1;
    /*
    * 判断是否是参与投票还是删除修改
    * */
    private Integer pd=1;
    /*
     * 主页分页显示
     * */
    @RequestMapping("/votelist")
    public String voteList( Model model, HttpSession session,String pageindex,String keywords) {
        if (pageindex==null){
            pageindex="1";
        }
        System.out.println("pageindex-----------"+pageindex);
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        if(keywords!=null){
            q.like("t.t_title",keywords);
        }
        IPage<Ticket> list=ticketService.findTicks(new Page<>(Integer.valueOf(pageindex), 4), q);
        model.addAttribute("tickList", list);
        model.addAttribute("pd",pd);
        session.setAttribute("page",list.getPages());
        pages=(int) list.getPages();
        session.setAttribute("index",list.getCurrent());
        return "votelist";
    }
    /*
    * 分页显示下一页
    * */
    @RequestMapping("votelist_xia")
    private String votelist_xia(Model model){
        if(nowIndex!=pages){
            nowIndex=nowIndex+1;
        }
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        IPage<Ticket> list = ticketService.findTicks(new Page<>(nowIndex, 4), q);
        model.addAttribute("tickList", list);
        model.addAttribute("pd",pd);
        return "votelist";
    }
    /*
     * 分页显示上一页
     * */
    @RequestMapping("votelist_sha")
    private String votelist_sha(Model model){
        if(nowIndex!=1){
            nowIndex=nowIndex-1;
        }
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        IPage<Ticket> list = ticketService.findTicks(new Page<>(nowIndex, 4), q);
        model.addAttribute("tickList", list);
        model.addAttribute("pd",pd);
        return "votelist";
    }
    /*
     *查询该主题的投票情况
     * */
    @RequestMapping("findVoteByid")
    public String findVoteByid(String tid, Model model) {
        System.out.println("tid" + tid);
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        q.eq("t.t_id", Integer.valueOf(tid));
        IPage<Ticket> vote = ticketService.findTicks(new Page<>(1, 10), q);
        model.addAttribute("vote", vote);
        return "voteview";
    }

    /*
     * 进入该主题的投票页面
     * */
    @RequestMapping("voteview")
    public String voteView(String tid, Model model) {
        System.out.println("tid" + tid);
        QueryWrapper<Ticket> q = new QueryWrapper<>();
        q.eq("t.t_id", Integer.valueOf(tid));
        IPage<Ticket> vote = ticketService.findTicks(new Page<>(1, 10), q);
        model.addAttribute("vote", vote);
        return "vote";
    }

    /*
     * 投票提交
     * */
    @RequestMapping("voteTp")
    public String voteTp(String uid, String tid, String[] options, Model model) {
        boolean pd = true;
        for (String s : options) {
            System.out.println("循环options--------------" + s);
        }
        if (statisticsService.findCount(Integer.valueOf(uid), Integer.valueOf(tid)) <= 0) {  //没有投票的情况下
            for (String s : options) {
                Statistics statistics = new Statistics();
                statistics.setO_id(Integer.valueOf(s));
                statistics.setT_id(Integer.valueOf(tid));
                statistics.setU_id(Integer.valueOf(uid));
                if (!statisticsService.save(statistics)) {
                    pd = false;
                }
            }
        } else {  //投过票的情况直接返回false
            pd = false;
        }
        if (pd) {
            model.addAttribute("mess", "恭喜！投票成功");
            model.addAttribute("action", "findVoteByid");
            model.addAttribute("tid", tid);
            return "Ok";
        } else {
            model.addAttribute("mess", "对不起，您已经投过票了");
            return "No";
        }
    }
    /*
     *维护主题页面验证用户权限
     * */
    @RequestMapping("weihuVote")
    public String weihuVote(HttpSession session, Model model){
        User user=(User) session.getAttribute("userwjj");
        if(user.getU_type()==1){
            pd=2;
            return "redirect:/votelist";
        }else{
            model.addAttribute("mess","用户权限不足，请联系管理员！");
            return "No";
        }
    }
    /*
    * 跳转主页返回列表
    * */
    @RequestMapping("voteList")
    public String voteList(){
        pd=1;
        return "redirect:/votelist";
    }
}
