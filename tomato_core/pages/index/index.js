//index.js
//获取应用实例
const app = getApp()
var WebService = require('../../utils/webService.js');
Page({
  data: {
    brands: [],//品牌集合
    orderTypeName: '出货订单',//订单类型名称
    totalPrice: 0,//总价格(减去额外费用后的数目)
    nowLevel: '',//当前显示的代理层级
    nowLevelList: [],//当前选中的品牌代理层级集合
    outPrice: 0,//额外费用
    selbrandIdx: 0,//当前选中的品牌index
    selgoodsIdx: 0,//当前选中的商品index
    selBandNum: 0,//已选择品牌数
    selGoodsNum: 0,//已选择商品总数量
  },
  onLoad: function () {
    var _that = this;
    wx.request({
      url: WebService.HOST + '/mapi/goodsV2/queryBrandAndGoods.do',
      data: {
        account_id: app.globalData.userInfo.account_id
      },
      success: function (res) {
        if (res.data.code === 0 && res.data.list) {
          console.log(res.data.list)
          var _brandList = res.data.list,
            _firstagentlevellist = _brandList[0].agentlevellist,
            _nowLevel = _firstagentlevellist[_firstagentlevellist.length - 1]
          //当前代理层级赋值
          _that.setData({
            brands: _brandList,
            nowLevel: _nowLevel,
            nowLevelList: _firstagentlevellist
          })
        }
      }
    })
  },
  /**
   * 切换品牌列表
   */
  swapTabTable: function (e) {
    var selIndex = e.currentTarget.id,
      _that = this,
      _brandList = _that.data.brands,
      _firstagentlevellist = _brandList[selIndex].agentlevellist,
      _nowLevel = _firstagentlevellist[_firstagentlevellist.length - 1];
    this.setData({
      selbrandIdx: selIndex,
      nowLevel: _nowLevel,
      nowLevelList: _brandList[selIndex].agentlevellist
    })
  },
  /**
   * 减数量
   */
  subProNum: function (e) {
    var _that = this,
      selidx = e.currentTarget.dataset.pindex,
      idx = e.currentTarget.dataset.cindex,
      list = _that.data.brands,
      c_list = list[selidx].goodsAndGoodsPricelist,
      alNum = c_list[idx].num;
    c_list[idx].num = alNum - 1;
    var new_totalNum = _that.data.selGoodsNum - 1;
    var newT_price = _that.data.totalPrice - c_list[idx].goods_price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: newT_price,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
  },
  /**
   * 加数量
   */
  addProNum: function (e) {
    var _that = this,
      selidx = e.currentTarget.dataset.pindex,
      idx = e.currentTarget.dataset.cindex,
      list = _that.data.brands,
      c_list = list[selidx].goodsAndGoodsPricelist,
      alNum = c_list[idx].num;
    if (alNum) {
      c_list[idx].num = alNum + 1;
    } else {
      c_list[idx].num = 1 * 1;
    }
    var new_totalNum = _that.data.selGoodsNum + 1;
    var newT_price = _that.data.totalPrice + c_list[idx].goods_price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num && child[j].num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: newT_price,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
  },
  /**
   * 清空按钮操作
   */
  clearEmpty: function (e) {
    var _that = this,
      list = _that.data.brands;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].goodsAndGoodsPricelist
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        child[j].num = 0
      }
    }
    _that.setData({
      brands: list,
      totalPrice: 0,
      selBandNum: 0,
      selGoodsNum: 0,
      maskVisual: false
    })
    _that.cascadeDismiss();
  },
  /**
   * 弹出数量弹窗
   */
  showNumModalDialog: function (e) {
    var _that = this,
      idx = e.currentTarget.dataset.index;
    _that.setData({
      showNumModal: true,
      selgoodsIdx: idx
    })
  },
  /**
     * 隐藏数量弹窗
     */
  hideNumModalDialog: function () {
    this.setData({
      showNumModal: false
    });
  },
  /**
   * 弹出框蒙层截断touchmove事件
   */
  preventTouchMove: function () {
  },
  /**
   * 数量弹窗输入数量事件
   */
  brandItemNumChange: function (e) {
    var _that = this,
      prtIdx = _that.data.selbrandIdx,
      idx = e.currentTarget.dataset.index,
      list = _that.data.brands;
    list[prtIdx].goodsAndGoodsPricelist[idx].num = e.detail.value;
    _that.setData({
      brands: list
    });
    var t_price = 0;
    var t_selbanNum = 0;
    var t_totalNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var c_list = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = c_list.length; j < j_len; ++j) {
        var num = c_list[j].num
        var price = c_list[j].goods_price
        if (num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
          t_price += num * price
          t_totalNum += num * 1
        }
      }
      if (is_selBand) {
        t_selbanNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: t_price,
      selBandNum: t_selbanNum,
      selGoodsNum: t_totalNum
    })
  },

  /**
   * 弹出层级弹窗
   */
  showLevelModalDialog: function (e) {
    this.setData({
      showLevelModal: true
    })
    var _that = this,
      _nowLevel = _that.data.nowLevel,
      list = _that.data.nowLevelList;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (_nowLevel.agentlevel_id == list[i].agentlevel_id) {
        list[i].checked = true;
      } else {
        list[i].checked = false;
      }
    }
    this.setData({
      nowLevelList: list
    });
  },
  /**
     * 隐藏层级弹窗
     */
  hideLevelModalDialog: function () {
    this.setData({
      showLevelModal: false
    });
  },
  /**
   * 选择代理层级
   */
  switchSelectLevel: function (e) {
    var _that = this,
      idx = e.currentTarget.dataset.index,
      list = _that.data.nowLevelList;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (idx == i) {
        list[i].checked = true;
      } else {
        list[i].checked = false;
      }
    }
    this.setData({
      nowLevelList: list,
      nowLevel: list[idx]
    });
  },

  /**
   * 弹出额外费用弹窗
   */
  showPriceModalDialog: function (e) {
    var _that = this,
      nowOutPrice = _that.data.outPrice;
    if (nowOutPrice >= 0) {
      this.setData({
        outPriceSymbol: '+'
      })
    } else {
      this.setData({
        outPriceSymbol: '-'
      })
    }
    this.setData({
      showPriceModal: true,
      modalOutPrice: Math.abs(nowOutPrice)
    })
  },
  /**
     * 隐藏额外费用弹窗
     */
  hidePriceModalDialog: function () {
    this.setData({
      showPriceModal: false
    });
  },
  /**
   * 额外费用输入监听事件
   */
  brandOutPriceChange: function (e) {
    var _that = this,
      nowSymbol = _that.data.outPriceSymbol;
    if (nowSymbol == '+') {
      this.setData({
        outPrice: (e.detail.value * 1)
      })
    } else {
      this.setData({
        outPrice: (0 - e.detail.value * 1)
      })
    }
  },
  /**
   * 切换额外费用正负
   */
  switchOurPriceSymbol: function (e) {
    var _that = this,
      nowSymbol = _that.data.outPriceSymbol,
      nowOutPrice = _that.data.outPrice;
    if (nowSymbol == '+') {
      nowSymbol = '-';
      nowOutPrice = 0 - Math.abs(nowOutPrice) * 1
    } else {
      nowSymbol = '+'
      nowOutPrice = Math.abs(nowOutPrice) * 1
    }
    this.setData({
      outPriceSymbol: nowSymbol,
      outPrice: nowOutPrice
    })
  },


  /**
   * 切换购物车开与关
   */
  cascadeToggle: function () {

    var that = this
    if (that.data.maskVisual) {
      that.cascadeDismiss();
    } else {
      that.cascadePopup();
    }
    // if (that.data.totalPrice > 0) {

    // } else {
    //   that.cascadeDismiss();
    // }
  },
  /**
   * 购物车打开动画
   */
  cascadePopup: function () {
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: "linear",
      delay: 0
    });
    this.animation = animation;
    animation.translateY(400).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: true
    });
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation.export()
      })
    }.bind(this), 100)
  },
  /**
   * 购物车关闭动画
   */
  cascadeDismiss: function () {
    this.setData({
      maskVisual: false
    });
  },


  /**
     * 立即下单弹窗
     */
  showOrderSureDialog: function (e) {
    if (this.data.totalNum > 0) {
      this.setData({
        showOrderSure: true,
        maskVisual: false
      })
    } else {
      wx.showToast({
        title: '当前订单无商品',
        icon: 'none',
        duration: 2000
      })
    }

  },
  /**
     * 隐藏立即下单弹窗
     */
  hideOrderSureDialog: function () {
    this.setData({
      showOrderSure: false
    });
  }
})
