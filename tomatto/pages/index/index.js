//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    infomation: '番茄管家致力于为微商打造最方便的算账工具，下面是我们全部功能的介绍',
    intros: [{
      title: '初级功能',
      items: [{
        memo: '如何注册用户'
      }, {
        memo: '如何进行算账'
      }, {
        memo: '如何进行商品管理'
      }, {
        memo: '如何管理客户'
      }, {
        memo: '如何管理客户订单'
      }]
    }, {
      title: '进阶功能',
      items: [{
        memo: '如何管理库存'
      }, {
        memo: '如何为下级代理分享商品'
      }, {
        memo: '如何管理库存'
      }, {
        memo: '如何为下级代理分享商品'
      }, {
        memo: '如何管理库存'
      }, {
        memo: '如何为下级代理分享商品'
      }, {
        memo: '如何管理库存'
      }, {
        memo: '如何为下级代理分享商品'
      }]
    }]
  },
  onLoad: function () {

  }
})
