//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  //登录
  doLogin: function (e) {
    console.log(e.detail.errMsg)
    console.log(e.detail.userInfo)
    console.log(e.detail.rawData)

    wx.login({
      success: function (res) {
        console.log(res)
        //获取登录的临时凭证
        var code = res.code;
        //调用后端，获取微信的session_key,secret
        wx.request({
         // url: 'http://192.168.137.1:8080/wechat/wxLogin?code=' + code,
          url: 'http://192.168.137.1:8080/wechat/wxLogin',
          method: "GET",
          data: {
            code: code,
            userInfo: e.detail.rawData
          },
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (result) {
            console.log(result);
           // app.setGlobalUserInfo(e.detail.userInfo);
            wx.redirectTo({
              url: '../userConsole/userConsole'
            })
          }
        })
      }
    })
  }

})














































