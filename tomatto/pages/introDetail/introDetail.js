// pages/introDetail/introDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: null,
    functions: [{
      desp: '微信小程序是一种全新的连接用户与服务的方式，它可以在微信内被便捷地获取和传播，同时具有出色的使用体验。'
    }, {
      desp: '选择“小程序”，点击“查看类型区别”可查看不同类型帐号的区别和优势。'
    }, {
      desp: '请填写未注册过公众平台、开放平台、企业号、未绑定个人号的邮箱。'
    }, {
      desp: '点击激活链接后，继续下一步的注册流程。请选择主体类型选择，完善主体信息和管理员信息。'
    }],
    operates: [{
      img: 'https://mp.weixin.qq.com/debug/wxadoc/dev/image/cat/0.jpg?t=2018315',
      desp: '选择主体类型并完善主体信息'
    }, {
        img: 'https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_plugin3b8804.png',
      desp: '企业类型帐号可选择两种主体验证方式。 方式一：需要用公司的对公账户向腾讯公司打款来验证主体身份。打款信息在提交主体信息后可以查看到。 方式二：通过微信认证验证主体身份，需支付300元认证费。认证通过前，小程序部分功能暂无法使用。'
    }, {
        img: 'https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_login3b8804.png',
      desp: '政府、媒体、其他组织类型帐号，必须通过微信认证验证主体身份。认证通过前，小程序部分功能暂无法使用。微信认证入口：登录小程序 - 设置 - 微信认证详情。'
    }, {
        img: 'https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_smart23b8804.png',
      desp: '登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。'
    },
    {
      img: 'https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_pay3b8804.png',
      desp: '登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。'
    }],
    images: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _title = options.title;
    var _that = this
    _that.setData({
      title: _title
    })
    var list = _that.data.operates
    var ary = new Array
    for (var i = 0, len = list.length; i < len; ++i) {
      ary[i] = list[i].img
    }
    _that.setData({
      images: ary
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 预览图片
   */
  prePhotos: function (e) {
    var index = e.target.dataset.index;
    var _that = this
    wx.previewImage({
      urls: _that.data.images,
      current: _that.data.operates[index].img
    })
  }
})