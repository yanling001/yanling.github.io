package web;

import anno.Controller;
import anno.RequestBody;
import anno.RequestMapping;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexofController {
    @RequestMapping("/add.do")
    public void add(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aadddd");
    }
    @RequestMapping("/select.do")
    @RequestBody
    public User select(User user){
        System.out.println(user);
        return user;
    }
   
    @RequestMapping("/index.do")
    public String index(){
        return "/index.jsp";
    }
}
