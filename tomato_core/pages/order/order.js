// pages/order/order.js
const util = require('../../utils/util.js');
var app = getApp();
var WebService = require('../../utils/webService.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabType: 1,
    outOrders: [],
    inOrders: [],
    startX: 0,
    startY: 0,
    outCurrentPage: 1,
    inCurrentPage: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function () {
    var _that = this;
    wx.getSystemInfo({
      success: (res) => { // 用这种方法调用，this指向Page
        this.setData({
          winH: res.windowHeight
        });
      }
    })
    wx.showLoading({
      title: '加载出货订单...',
      mask: true
    })
    //查询出货订单
    wx.request({
      url: WebService.HOST+'/mapi/order/listPageAllOrder.do',
      data: {
        account_id: app.globalData.userInfo.account_id,
        type: 3,
        currentPage: _that.data.outCurrentPage,
        showCount: 10
      },
      success: function (res) {
        wx.hideLoading();
        // console.log(res.data)
        if (res.data.code == 0 && res.data.list) {
          var list_out = res.data.list;
          for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
            var chile_list_out = list_out[i].orderlist;
            for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
              chile_list_out[j].order_time = util.formatTimeStamp(chile_list_out[j].create_time, 'M-D')
            }
          }
          _that.setData({
            outOrders: list_out
          })
        }
      }
    })
    wx.showLoading({
      title: '加载进货订单...',
      mask: true
    })
    //查询进货订单
    wx.request({
      url: WebService.HOST +'/mapi/order/listPageAllOrder.do',
      data: {
        account_id: app.globalData.userInfo.account_id,
        type: 4,
        currentPage: _that.data.inCurrentPage,
        showCount: 10
      },
      success: function (res) {
        // console.log(res.data)
        if (res.data.code == 0 && res.data.list) {
          wx.hideLoading();
          var list_in = res.data.list;
          for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
            var chile_list_in = list_in[i].orderlist;
            for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
              chile_list_in[j].order_time = util.formatTimeStamp(chile_list_in[j].create_time, 'M-D')
            }
          }
          _that.setData({
            inOrders: list_in
          })
        }
      }
    })
  },

  searchScrollLower: function () {
    var _that = this,
      _outOrders = _that.data.outOrders,
      _inOrders = _that.data.inOrders,
      _outNowPage = _that.data.outCurrentPage,
      _inNowPage = _that.data.inCurrentPage,
      nowTab = _that.data.tabType;//当前页签
    if (1 == nowTab) {
      wx.showLoading({
        title: '',
        mask: true
      })
      //出货订单下滑翻页
      wx.request({
        url: WebService.HOST + '/mapi/order/listPageAllOrder.do',
        data: {
          account_id: app.globalData.userInfo.account_id,
          type: 3,
          currentPage: _outNowPage * 1 + 1,
          showCount: 10
        },
        success: function (res) {
          // console.log(res.data)
          wx.hideLoading()
          if (res.data.code == 0 && res.data.list) {
            _that.setData({
              // isOutAll: true,
              outCurrentPage: _outNowPage*1 + 1
            })
            var list_out = res.data.list;
            for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
              var chile_list_out = list_out[i].orderlist;
              for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
                chile_list_out[j].order_time = util.formatTimeStamp(chile_list_out[j].create_time, 'M-D')
              }
              _outOrders.splice(_outOrders.length, 0, list_out[i])
            }
            _that.setData({
              outOrders: _outOrders
            })
          }else{
            wx.showToast({
              title: '无更多记录！',
              icon: 'none',
              duration: 2000
            })
          }
        }
      })
    } else {
      wx.showLoading({
        title: '',
        mask: true
      })
      //进货订单下滑翻页
      wx.request({
        url: WebService.HOST + '/mapi/order/listPageAllOrder.do',
        data: {
          account_id: app.globalData.userInfo.account_id,
          type: 4,
          currentPage: _inNowPage*1 + 1,
          showCount: 10
        },
        success: function (res) {
          // console.log(res.data)
          wx.hideLoading()
          if (res.data.code == 0 && res.data.list) {
            _that.setData({
              // isInAll: true,
              inCurrentPage: _inNowPage*1 + 1
            })
            var list_in = res.data.list;
            for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
              var chile_list_in = list_in[i].orderlist;
              for (var j = 0, j_len = chile_list_in.length; j < j_len; ++j) {
                chile_list_in[j].order_time = util.formatTimeStamp(chile_list_in[j].create_time, 'M-D')
              }
              _inOrders.splice(_inOrders.length, 0, list_in[i])
            }
            _that.setData({
              inOrders: _inOrders
            })
          }else{
            wx.showToast({
              title: '无更多记录！',
              icon: 'none',
              duration: 2000
            })
          }
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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
  tabSwitchOut: function (e) {
    var _that = this
    _that.setData({
      tabType: 1
    })
    //重置所有滑块
    var list_out = this.data.outOrders
    for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
      var chile_list_out = list_out[i].orderlist;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        if (chile_list_out[j].isTouchMove) {
          chile_list_out[j].isTouchMove = false;
        }
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].orderlist;
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

  /**
     * 切换页签
     */
  tabSwitchIn: function (e) {
    var _that = this
    _that.setData({
      tabType: 2
    })
    //重置所有滑块
    var list_out = this.data.outOrders
    for (var i = 0, i_len = list_out.length; i < i_len; ++i) {
      var chile_list_out = list_out[i].orderlist;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        if (chile_list_out[j].isTouchMove) {
          chile_list_out[j].isTouchMove = false;
        }
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].orderlist;
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
      var chile_list_out = list_out[i].orderlist;
      for (var j = 0, j_len = chile_list_out.length; j < j_len; ++j) {
        if (chile_list_out[j].isTouchMove) {
          chile_list_out[j].isTouchMove = false;
        }
      }
    }
    var list_in = this.data.inOrders
    for (var i = 0, i_len = list_in.length; i < i_len; ++i) {
      var chile_list_in = list_in[i].orderlist;
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
      var chile_list_out = list_out[p_index].orderlist;
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
      var chile_list_in = list_in[p_index].orderlist;
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
  gotoGetail: function (e) {
    var _that = this,
      nowTab = _that.data.tabType,//当前页签
      p_index = e.currentTarget.dataset.pindex,//当前父索引
      c_index = e.currentTarget.dataset.cindex;//当前索引
    var order_id;
    if (1 == nowTab) {
      order_id = _that.data.outOrders[p_index].orderlist[c_index].order_id
    } else {
      order_id = _that.data.inOrders[p_index].orderlist[c_index].order_id
    }
    wx.navigateTo({
      url: '/pages/order/orderDetail/orderDetail?order_id=' + order_id
    })
  },

  /**
   * 删除订单
   */
  delOrder: function (e) {
    var that = this,
      nowTab = that.data.tabType,//当前页签
      p_index = e.currentTarget.dataset.pindex,//当前父索引
      c_index = e.currentTarget.dataset.cindex;//当前索引
    wx.showModal({
      title: '提示',
      content: '确认删除该订单？',
      success: function (res) {
        if (res.confirm) {
          wx.showLoading({
            title: '',
            mask: true
          })
          var _orderId;
          if (1 == nowTab) {
            _orderId = that.data.outOrders[p_index].orderlist[c_index].order_id
            that.data.outOrders[p_index].orderlist.splice(c_index, 1)
            that.setData({
              outOrders: that.data.outOrders
            })
          } else {
            _orderId = that.data.inOrders[p_index].orderlist[c_index].order_id
            that.data.inOrders[p_index].orderlist.splice(c_index, 1)
            that.setData({
              inOrders: that.data.inOrders
            })
          }
          wx.request({
            url: WebService.HOST +'/mapi/order/deleteOrder.do',
            data: {
              account_id: app.globalData.userInfo.account_id,
              order_id: _orderId
            },
            success(res) {
              wx.hideLoading();
            }
          })
        } else if (res.cancel) {

        }
      }
    })
  }
})