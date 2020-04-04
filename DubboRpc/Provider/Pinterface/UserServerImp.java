package DubboRpc.Provider.Pinterface;

import DubboRpc.Provider.pojo.User;

public class UserServerImp implements UserServcie {
    @Override
    public String getUserinfo(Integer userId) {
        User user =new User();
        user.setUsername("张宏耀");
        user.setPassword("123445");
        return user.toString();
    }
}
