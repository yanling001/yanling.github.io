package web;


import anno.Controller;
import anno.RequestBody;
import anno.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(value = "/*.do",loadOnStartup = 1)//拦截所有
public class DispatcherServlet extends HttpServlet{
    Map<String,Method> map=new HashMap<>();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取url
        String uri=req.getRequestURI();
        //从map中获取方法
        Method method=map.get(uri);
        if (method!=null){

            Object obj=null;
            try {
                //获取method所属的类（多例：每执行依次创建一次对象）
                 obj=method.getDeclaringClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //获取方法参数类型
             Parameter [] parameters= method.getParameters();
            //参数数组
            Object param[]=new Object[parameters.length];
            for (int i=0;i<parameters.length;i++){//遍历方法参数并填充参数
                String pname=parameters[i].getName();//获取参数名称
                Class clazz=parameters[i].getType();//获取参数类型
                if (clazz==HttpServletRequest.class){
                    param[i]=req;
                }else if (clazz==HttpServletResponse.class){
                    param[i]=resp;
                }else if (clazz==String.class){
                    param[i]=req.getParameter(pname);
                }else {//如果参数是pojo对象
                    Object object=null;
                    try {
                        //实例化参数对象
                        object=clazz.newInstance();
                        //System.out.println(object.getClass().getName());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //获取pojo对象的字段
                    Field[] fields=clazz.getDeclaredFields();
                  //  System.out.println(fields.length+"  fields");
                    for (Field field:fields){
                        field.setAccessible(true);//设置权限允许反射操作
                        try {
                            //System.out.println(req.getParameter(field.getName()));
                            field.set(object,req.getParameter(field.getName()));//从参数中获取pojo对象的字段值并设置
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        param[i]=object;

                    }
                }
            }
            Object ret=null;
            try {
                //执行方法并获取返回值
               ret= method.invoke(obj,param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //根据方法的返回值（返回给前端页面）加了RequestBody注解返回tostring（本该返回josn序列化这里拿tosting代替）
            if (ret!=null&&method.isAnnotationPresent(RequestBody.class)) {
                resp.getWriter().println(ret.toString());
            }else {
                //如果返回的是字符串进行请求转发
                if (ret!=null&&ret.getClass()==String.class){
                      req.getRequestDispatcher("/"+(String)ret).forward(req,resp);
                }
            }
        }else{
            //找不到方法返回404
            resp.setStatus(404);
        }


    }

    @Override
    public  void init() throws ServletException {
        System.out.println("init....");
       //加载controller并将方法的加载（map（key：requestmapping的value，method对象））
        /*获取controller的字节码文件的名称（默认controller在DispatcherServlet的同级目录）
             1. 获取类路径
             2. 遍历文件通过反射获取类
             3. 获取类的方法判断是否加了注解
         */
        //map（key：requestmapping的value，method对象）存储加了requestmapping注解的方法

        // 1. 获取类路径（省去了xml解析所以controller在DispatcherServlet的同级目录）
        String rootpath=this.getClass().getResource("").getPath();
      //  System.out.println(rootpath);  //rootpath=/C:/Users/hongyao/IdeaProjects/MyMVC/out/production/MyMVC/web/
        File file=new File(rootpath);
        // 2. 遍历文件通过反射获取类
        for (File temp:file.listFiles()){
           String[] pathlist= rootpath.split("/");

                //获取类
               // System.out.println(pathlist[pathlist.length - 1] + "." + temp.getName().replaceAll(".class", ""));
                Class clazz = null;
                try {
                    clazz = Class.forName(pathlist[pathlist.length - 1] + "." + temp.getName().replaceAll(".class", ""));
                   // System.out.println(clazz.getName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //判断是否加了Controller注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //加了注解获取类中的方法
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        //判断方法是否加了RequestMapping注解
                        if (method.isAnnotationPresent(RequestMapping.class)) {//方法加了RequestMapping注解
                     /*
                         如果不指定注解的生命周期此处map是空
                           注解的生命周期：
                           1.只存在源码中不保存在.class文件中
                           2.保存在.class文件中.但在运行时擦除
                           3.保存在.class文件中.但在运行时不擦除
                      */
                            //获取注解对象
                            RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                            //以requestMapping.value()为key和method为value放入map在请求的时候根据uri在map中查找method
                            map.put(requestMapping.value(), method);

                        }
                    }
                }


        }
    }

}
