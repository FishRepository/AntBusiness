// pages/order/orderDetail/orderDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderDetail: {
      userinfo: {
        head: '/images/order/icon_user.jpg',
        name: '钟宇光',
        phone: '15972934871',
        address: '湖北省武汉市武昌区中南路9号中南花园酒店22座3001',
        memo: '快递给点力，不可以退货'
      },
      date: '2018年03月12日',
      totalNum: 8,
      totalPrice: 9999999999,
      outPrice: -10,
      items: [{
        brand: 'ONLY',
        desp: '一级代理',
        pro: [{
          name: '星辰化妆品【大】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【大】',
          num: 2,
          unitPrice: 999
        }]
      },
      {
        brand: 'A.T',
        desp: '零售价',
        pro: [{
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }, {
          name: '星辰化妆品【中】',
          num: 1,
          unitPrice: 999
        }]
      }]
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  }
})