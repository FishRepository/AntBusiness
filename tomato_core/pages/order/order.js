// pages/order/order.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabType: 1,
    outOrders: [{
      date: '2018年02月',
      items: [{
        date: '02-27',
        brand: 'A.T',
        price: '999'
      }, {
        date: '02-17',
        brand: 'A.T、DIOR',
        price: '999'
      }, {
        date: '02-07',
        brand: 'DIOR',
        price: '999'
      }]
    }, {
      date: '2017年02月',
      items: [{
        date: '10-27',
        brand: 'A.T',
        price: '999'
      }, {
        date: '09-27',
        brand: 'A.T',
        price: '999'
      }, {
        date: '08-27',
        brand: 'A.T',
        price: '999'
      }]
    }, {
      date: '2018年02月',
      items: [{
        date: '02-27',
        brand: 'A.T',
        price: '999'
      }, {
        date: '02-17',
        brand: 'A.T、DIOR',
        price: '999'
      }, {
        date: '02-07',
        brand: 'DIOR',
        price: '999'
      }]
    }, {
      date: '2018年02月',
      items: [{
        date: '02-27',
        brand: 'A.T',
        price: '999'
      }, {
        date: '02-17',
        brand: 'A.T、DIOR',
        price: '999'
      }, {
        date: '02-07',
        brand: 'DIOR',
        price: '999'
      }]
    }],
    inOrders: [{
      date: '2018年01月',
      items: [{
        date: '08-27',
        brand: '阿斯丹妮',
        price: '699'
      }, {
        date: '07-17',
        brand: '格莱美',
        price: '699'
      }, {
        date: '06-07',
        brand: 'DIOR',
        price: '699'
      }]
    }, {
      date: '2017年01月',
      items: [{
        date: '04-17',
        brand: '丹顿',
        price: '799'
      }, {
        date: '03-20',
        brand: '阿尼玛',
        price: '899'
      }, {
        date: '02-27',
        brand: '鳄鱼',
        price: '899'
      }]
    }],
    startX: 0,
    startY: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var list_out = this.data.outOrders
    for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
      var chile_list_out = list_out[i].items;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        chile_list_out[j].isTouchMove = false
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].items;
      for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
        chile_list_in[j].isTouchMove = false
      }
    }
    this.setData({
      outOrders: list_out,
      inOrders: list_in
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
   * 切换页签
   */
  tabSwitch: function (e) {
    var _that = this
    var nowType = _that.data.tabType;
    if (nowType == 1) {
      _that.setData({
        tabType: 2
      })
    } else {
      _that.setData({
        tabType: 1
      })
    }
    //重置所有滑块
    var list_out = this.data.outOrders
    for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
      var chile_list_out = list_out[i].items;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        if (chile_list_out[j].isTouchMove) {
          chile_list_out[j].isTouchMove = false;
        }
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].items;
      for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
        if (chile_list_in[j].isTouchMove) {
          chile_list_in[j].isTouchMove = false
        }
      }
    }
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      outOrders: list_out,
      inOrders: list_in
    })
  },

  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    var list_out = this.data.outOrders
    for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
      var chile_list_out = list_out[i].items;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        if (chile_list_out[j].isTouchMove) {
          chile_list_out[j].isTouchMove = false;
        }
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].items;
      for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
        if (chile_list_in[j].isTouchMove) {
          chile_list_in[j].isTouchMove = false
        }
      }
    }
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      outOrders: list_out,
      inOrders: list_in
    })
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this,
      list_out = that.data.outOrders,
      list_in = that.data.inOrders,
      nowTab = that.data.tabType,//当前页签
      p_index = e.currentTarget.dataset.pindex,//当前父索引
      c_index = e.currentTarget.dataset.cindex,//当前索引
      startX = that.data.startX,//开始X坐标
      startY = that.data.startY,//开始Y坐标
      touchMoveX = e.changedTouches[0].clientX,//滑动变化坐标
      touchMoveY = e.changedTouches[0].clientY,//滑动变化坐标
      //获取滑动角度
      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    if (1 == nowTab) {
      var chile_list_out = list_out[p_index].items;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        chile_list_out[j].isTouchMove = false
        //滑动超过30度角 return
        if (Math.abs(angle) > 30) return;
        if (j == c_index) {
          if (touchMoveX > startX) //右滑
            chile_list_out[j].isTouchMove = false
          else //左滑
            chile_list_out[j].isTouchMove = true
        }
      }
    } else {
      var chile_list_in = list_in[p_index].items;
      for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
        chile_list_in[j].isTouchMove = false
        //滑动超过30度角 return
        if (Math.abs(angle) > 30) return;
        if (j == c_index) {
          if (touchMoveX > startX) //右滑
            chile_list_in[j].isTouchMove = false
          else //左滑
            chile_list_in[j].isTouchMove = true
        }
      }
    }
    //更新数据
    that.setData({
      outOrders: list_out,
      inOrders: list_in
    })
  },
  /**
   * 计算滑动角度
   * @param {Object} start 起点坐标
   * @param {Object} end 终点坐标
   */
  angle: function (start, end) {
    var _X = end.X - start.X,
      _Y = end.Y - start.Y
    //返回角度 /Math.atan()返回数字的反正切值
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  },

/**
 * 进入订单详情
 */
  gotoGetail:function(e){
    wx.navigateTo({
      url: '/pages/order/orderDetail/orderDetail',
    })
  },

  /**
   * 删除订单
   */
  delOrder:function(e){
    var that = this,
        nowTab = that.data.tabType,//当前页签
        p_index = e.currentTarget.dataset.pindex,//当前父索引
        c_index = e.currentTarget.dataset.cindex;//当前索引
    wx.showModal({
      title: '提示',
      content: '确认删除该订单？',
      success: function (res) {
        if (res.confirm) {
          if (1 == nowTab){
            that.data.outOrders[p_index].items.splice(c_index, 1)
            that.setData({
              outOrders: that.data.outOrders
            })
          }else{
            that.data.inOrders[p_index].items.splice(c_index, 1)
            that.setData({
              inOrders: that.data.inOrders
            })
          }
        } else if (res.cancel) {

        }
      }
    })
  }
})