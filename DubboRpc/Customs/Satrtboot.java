package DubboRpc.Customs;

import DubboRpc.Customs.Pinterface.UserServcie;
import DubboRpc.Provider.pojo.User;

public class Satrtboot {
    public static void main(String[] args) throws InterruptedException {
        CustomClient.startClient(8090);
        DubboRpc.Customs.Pinterface.UserServcie userServcie =(DubboRpc.Customs.Pinterface.UserServcie) CustomClient.getBean(DubboRpc.Customs.Pinterface.UserServcie.class,"userService#getUserinfo#");
        System.out.println( userServcie.getUserinfo(1));
    }
}
