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
          // console.log(res.data.list)
          var _brandList = res.data.list,
            _firstagentlevellist = _brandList[0].agentlevellist,
            _nowLevel = _firstagentlevellist[_firstagentlevellist.length - 1];
          //加入默认的代理层级
          _firstagentlevellist.splice(_firstagentlevellist.length, 0, {
            agentlevel_id: -1,
            agentlevel_name: '我要进货'
          }, {
              agentlevel_id: -2,
              agentlevel_name: '进货价出售'
            });
          for (var i in _brandList[0].goodsAndGoodsPricelist) {
            var _goodsPriceAgentList = _brandList[0].goodsAndGoodsPricelist[i].goodspricelist;
            //先给商品加入默认代理层级
            _goodsPriceAgentList.splice(_goodsPriceAgentList.length, 0, {
              agentlevel_id: -1,
              agentlevel_name: '我要进货',
              goods_price: _brandList[0].goodsAndGoodsPricelist[i].goods_price
            }, {
                agentlevel_id: -2,
                agentlevel_name: '进货价出售',
                goods_price: _brandList[0].goodsAndGoodsPricelist[i].goods_price
              });
            for (var j in _goodsPriceAgentList) {
              if (_goodsPriceAgentList[j].agentlevel_id === _nowLevel.agentlevel_id) {
                _brandList[0].goodsAndGoodsPricelist[i].goods_price = _goodsPriceAgentList[j].goods_price
              }
            }
          }
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
    //加入默认的代理层级
    _firstagentlevellist.splice(_firstagentlevellist.length, 0, {
      agentlevel_id: -1,
      agentlevel_name: '我要进货'
    }, {
        agentlevel_id: -2,
        agentlevel_name: '进货价出售'
      });
    this.setData({
      selbrandIdx: selIndex,
      nowLevel: _nowLevel,
      nowLevelList: _firstagentlevellist
    })
    for (var i in _brandList[selIndex].goodsAndGoodsPricelist) {
      var _goodsPriceAgentList = _brandList[selIndex].goodsAndGoodsPricelist[i].goodspricelist;
      //先给商品加入默认代理层级
      _goodsPriceAgentList.splice(_goodsPriceAgentList.length, 0, {
        agentlevel_id: -1,
        agentlevel_name: '我要进货',
        goods_price: _brandList[selIndex].goodsAndGoodsPricelist[i].goods_price
      }, {
          agentlevel_id: -2,
          agentlevel_name: '进货价出售',
          goods_price: _brandList[selIndex].goodsAndGoodsPricelist[i].goods_price
        });
      for (var j in _goodsPriceAgentList) {
        if (_goodsPriceAgentList[j].agentlevel_id === _nowLevel.agentlevel_id) {
          _brandList[selIndex].goodsAndGoodsPricelist[i].goods_price = _goodsPriceAgentList[j].goods_price
        }
      }
    }
    _that.setData({
      brands: _brandList
    });
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
      _brandList = _that.data.brands,
      selBrandIdx = _that.data.selbrandIdx,
      idx = e.currentTarget.dataset.index,
      list = _that.data.nowLevelList;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (idx == i) {
        list[i].checked = true;
      } else {
        list[i].checked = false;
      }
    }
    _that.setData({
      nowLevelList: list,
      nowLevel: list[idx]
    });
    var _nowLevel = list[idx];
    for (var i in _brandList[selBrandIdx].goodsAndGoodsPricelist) {
      for (var j in _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist) {
        if (_brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist[j].agentlevel_id === _nowLevel.agentlevel_id) {
          _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goods_price = _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist[j].goods_price
        }
      }
    }
    _that.setData({
      brands: _brandList
    });
    var _orderTypeName = ''
    if (_nowLevel.agentlevel_id === -1) {
      _orderTypeName = '进货订单';
    } else {
      _orderTypeName = '出货订单';
    }
    _that.setData({
      orderTypeName: _orderTypeName
    })
    //重新计算订单数量和价格
    var _outPrice = _that.data.outPrice,
      new_selBandNum = 0,
      new_totalNum = 0,
      new_totalPrice = 0;
    for (var i = 0, len = _brandList.length; i < len; ++i) {
      var child = _brandList[i].goodsAndGoodsPricelist
      var is_selBand = false
      _brandList[i].hasSelectItem = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num && child[j].num > 0 && child[j].goods_price > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
          new_totalNum = new_totalNum + child[j].num
          new_totalPrice = new_totalPrice + child[j].num * child[j].goods_price
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    new_totalPrice = new_totalPrice + _outPrice
    _that.setData({
      totalPrice: new_totalPrice,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
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
    var thisOutPrice = 0;
    if (nowSymbol == '+') {
      thisOutPrice = e.detail.value * 1
    } else {
      thisOutPrice = 0 - e.detail.value * 1
    }
    _that.setData({
      outPrice: thisOutPrice
    })
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
    _that.setData({
      outPriceSymbol: nowSymbol,
      outPrice: nowOutPrice
    })
  },
  /**
   * 额外费用弹框确认
   */
  outPriceConfirm: function (e) {
    var _that = this,
      _brandList = _that.data.brands,
      _outPrice = _that.data.outPrice;
    var nowTotalPrice = 0;
    for (var i = 0, len = _brandList.length; i < len; ++i) {
      var child = _brandList[i].goodsAndGoodsPricelist
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          nowTotalPrice = nowTotalPrice + child[j].num * child[j].goods_price
        }
      }
    }
    _that.setData({
      showPriceModal: false,
      totalPrice: nowTotalPrice + _outPrice
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
