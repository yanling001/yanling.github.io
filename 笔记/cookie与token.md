## Cookie
#### cookie机制采用的是在客户端保持状态的方案，而session机制采用的是在服务器端保持状态的方案；
#### 服务器通过在Http响应头中加上一行特殊指令，浏览器按照指示生成相应的cookie。
#### cookie的内容主要包括：名字，值，过期时间，路径和域；
#### 如果不设置过期时间，则表示这个cookie的生命周期为浏览器会话期间，关闭浏览器窗口，cookie就小时，这样cookie一般不存储在硬盘中，而是存储在内存的。
#### 若是设置了过期时间，浏览器就会把cookie保存到硬盘上，关闭后再次打开浏览器，cookie仍然有效直到超过设定时间。存储在硬盘上的cookie可以在不同的浏览器之间共享进程，比如两个浏览器窗口。
## Session
#### session机制是在服务端的一种机制；
#### 它维护了一个hash表（map结构）来保存信息；
#### 当请求端需要为某个客户端的请求创建一个session时，服务器首先检查这个客户端的请求里是否已经包含了一个session标示（sessionID），如果包含则说明以前已经为此客户创建过session，服务器就按照sessionID把这个session检索出来使用（检索不到，会新建一个）；
#### 如果客户端请求不包含sessionID，则为此客户端创建一个session并生成一个session相关联的sessionID；
#### sessionID的值应该是一个既不会重复，又不容易找到规律用来仿造的字符串；
#### 这个sessionID将被在本次请求中返回给客户端保存，保存这个sessionID的方式可以采用cookie；
#### 在客户端与服务端交互中，浏览器会自动按照规则把这个标示发送给服务器；
## cookie被禁用
#### URL重写，把sessionid直接附加在URL路径后面；
#### 单表隐藏字段，服务器会自动修改表单，添加一个隐藏字段，以便提交表单时候能够把sessionID传递回服务器；
## 区别
1. cookie数据存放在客户浏览器上，在本地，session数据放在服务器上；
2. cookie不安全，别人可以存放在本地的cookie；
3. session回一定时间保存在服务器上，当访问增多，会比较占用资源；
4. 单个cookie保存的数据不能超过4k，很多浏览器限制一个站点最多保存20个cookie；
## token
### 流程：
#### 1.客户端使用用户名和密码请求登陆；
#### 2.服务端收到请求，验证用户名和密码；
#### 3.验证成功后，服务端会生成一个token，然后把这个token发送给客户端；
#### 4.客户端收到token后把它存储起来，可以放在cookie或者本地存储
#### 5.客户端每次向服务端发送请求的时候都需要带上服务端发送的token；
#### 6.服务端收到请求，然后去验证客户端请求里面带着token，如果验证成功，就向客户端返回请求的数据。


# Cookie详解

## 1. Cookie的属性
#### （1）name：必须的；
#### （2）value：必须的；
#### （3）comment：可选的；
#### （4）path：可选的，如果不设置路径，那么只有设置该cookie的URI及其子路径可以访问。
```
写cookie的程序的访问路径是:http://localhost:8080/java/servlet/cookieDemo
其中：localhost是域名；
java/servlet就是当前cookie的path

若是访问的地址的URI包含着cookie的路径，则客户端将该cookie带给服务器。
比如浏览器存的cookie的路径是java
访问地址是：http://localhost:8080/java/servlet/cookieDemo  则携带cookie
访问地址是：http://localhost:8080/java/cookieDemo 则携带cookie

若浏览器存的cookie的路径是java/servlet
访问的地址是:http://localhost:8080/java/servlet/cookieDemo 则携带cookie
访问的地址是:http://localhost:8080/java/cookieDemo 则不携带cookie

如果一个cookie的路径设置成了/java，意味着浏览器访问当前应用下的所有资源时都会带着该cookie给服务器。

```
#### （5）domain：可选的。该Cookie所属的网站域名。（apache.org）默认值；
#### （6）maxumum age：可选的。不设置就是会话过程（存在浏览器的内存中）。单位是秒。如果是0，则说明需要删除。

#### （7）version：可选的。

# Cookie写入
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HttpServletResponse对象.addCookie（javax.servlet.http.Cookie对象）（就是写了一个相应消息头：Set-Cookie:cookie的信息）。Servlet规范中的Cookie API提供了setMaxAge  setPath   setDomain等方法，可以对Cookie状态进行控制。

#### 特点：一个浏览器针对一个网站最多存20个Cookie；最多存300个Cookie，每个Cookie的长度不能超过4KB（稀缺资源）。不同的浏览器有不同的策略。

```
public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cookie=new Cookie("name","Tom");
        //设置Maximum Age
        cookie.setMaxAge(1000);
        //设置cookie路径为当前项目路径
        cookie.setPath(request.getContextPath());
        //添加cookie
        response.addCookie(cookie);
    }
```

# 服务器获取客户端传来的Cookie：
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在Java中利用Servlet或者JSP scriptlet可以向浏览器端写入Cookie，同样，利用Servlet或者JSP scriptlet也可以读取到Cookie信息。Servlet规范中的Cookie API同样存在getMaxAge getPath getDomain等方法，可以获得相应状态。

#### 读取Cookie时，除了key和value外，其他值获取都为null。
#### 原因：Cookie从服务器端发送到客户端时，信息是完整的，Cookie从客户端发送到服务器端时，信息只剩下key，value了。因为Domain不对的Cookie，Path不对的Cookie，过期的Cookie，客户端是不会发送过来的。

#### Java中提供的相应get方法是为了在生成Cookie之后，尚未发送到客户端的时候使用。


## Cookie的区分是通过domain+path+name来区分的。




