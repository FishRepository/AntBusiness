//index.js
//获取应用实例
const app = getApp()
var WebService = require('../../utils/webService.js')

Page({
  data: {
    infomation: '番茄管家致力于为微商打造最方便的算账工具，下面是我们全部功能的介绍',
    intros: [{
      title: null,
      items: []
    }]
  },
  onLoad: function () {
    var _this = this
    wx.request({
      url: WebService.HOST + WebService.FETCH_INTRODUCTIONS_URL,
      success: function (result) {
        _this.setData({
          intros: result.data
        })
      }
    })
  }
})
