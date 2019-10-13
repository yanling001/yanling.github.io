package Servret;

import Service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/*")
public class Myservlet extends HttpServlet {
    UserService service=new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");
      resp.setCharacterEncoding("utf-8");
        String string=req.getParameter("method");
        try {
            Method method=this.getClass().getMethod(string,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public void show(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<User> list =service.show();
        request.setAttribute("users",list);
        request.getRequestDispatcher("/jsp/centor.jsp").forward(request,response);
    }
    public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
       User user=service.find(Integer.parseInt(request.getParameter("id")));
       request.setAttribute("user",user);
       request.getRequestDispatcher("/jsp/update.jsp").forward(request,response);
    }
    public void sumbit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        User user=new User();
        BeanUtils.populate(user,request.getParameterMap());
        service.update(user);
        List<User> list =service.show();
        request.setAttribute("users",list);
        request.getRequestDispatcher("/jsp/centor.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        service.delete(Integer.parseInt(request.getParameter("id")));
        List<User> list =service.show();
        request.setAttribute("users",list);
        request.getRequestDispatcher("/jsp/centor.jsp").forward(request,response);
    }
    public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        User user=new User();
        BeanUtils.populate(user,request.getParameterMap());
        service.add(user);
        List<User> list =service.show();
        request.setAttribute("users",list);
        request.getRequestDispatcher("/jsp/centor.jsp").forward(request,response);
    }
    public void find(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        User user=service.find(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user",user);
        request.getRequestDispatcher("/jsp/find.jsp").forward(request,response);
    }
}
