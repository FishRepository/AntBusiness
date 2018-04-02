//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    brands: [{
      name: 'A.T',
      items: [{
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }, {
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }, {
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }]
    },
    {
      name: '香奈儿',
      items: [{
        proName: '香奈儿妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      },
      {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      }]
    },
    {
      name: '施华洛世奇水晶',
      items: [{
        proName: '施华洛世奇水晶妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '施华洛世奇水晶妆品【小】',
        price: 399.00,
        num: 0
      },
      {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      }]
    },
    {
      name: '雅诗兰黛',
      items: [{
        proName: '雅诗兰黛妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '雅诗兰黛妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: 'Mircel',
      items: [{
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }]
    }],
    selIdx: 0,
    totalPrice: 0,
    selBandNum: 0,
    totalNum: 0,
    nowLevel: '我要进货',
    outPrice: 0,
    levelList: [{
      name: '零售价'
    }, {
      name: '我要进货'
    }, {
      name: 'VIP'
    }, {
      name: 'SVIP'
    }, {
      name: '官方'
    }, {
      name: '总代'
    }],
    maskVisual: false,
    animationData: {}
  },
  onLoad: function () {
    var _that = this
    var list = _that.data.brands
    var t_price = 0;
    var t_selbanNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var c_list = list[i].items
      var is_selBand = false
      for (var j = 0, j_len = c_list.length; j < j_len; ++j) {
        var num = c_list[j].num
        var price = c_list[j].price
        if (num > 0) {
          is_selBand = true
          t_price += num * price
        }
      }
      if (is_selBand) {
        t_selbanNum++
      }
    }
    _that.setData({
      totalPrice: t_price
    })
  },
  /**
   * 切换品牌列表
   */
  swapTabTable: function (e) {
    var selIndex = e.currentTarget.id
    this.setData({
      selIdx: selIndex
    })
  },
  /**
   * 减数量
   */
  subProNum: function (e) {
    var _that = this
    var selidx = e.currentTarget.dataset.pindex
    var idx = e.currentTarget.dataset.cindex
    var list = _that.data.brands
    var c_list = list[selidx].items
    var alNum = c_list[idx].num
    c_list[idx].num = alNum - 1
    var new_totalNum = _that.data.totalNum - 1;
    var newT_price = _that.data.totalPrice - c_list[idx].price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].items
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
      totalNum: new_totalNum
    })
  },
  /**
   * 加数量
   */
  addProNum: function (e) {
    var _that = this
    var selidx = e.currentTarget.dataset.pindex
    var idx = e.currentTarget.dataset.cindex
    var list = _that.data.brands
    var c_list = list[selidx].items
    var alNum = c_list[idx].num
    c_list[idx].num = alNum + 1
    var new_totalNum = _that.data.totalNum + 1;
    var newT_price = _that.data.totalPrice + c_list[idx].price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].items
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
      totalNum: new_totalNum
    })
  },
  /**
   * 清空按钮操作
   */
  clearEmpty: function (e) {
    var _that = this
    var list = _that.data.brands
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].items
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        child[j].num = 0
      }
    }
    _that.setData({
      brands: list,
      totalPrice: 0,
      selBandNum: 0,
      totalNum: 0,
      maskVisual: false
    })
    _that.cascadeDismiss();
  },
  /**
   * 弹出数量弹窗
   */
  showNumModalDialog: function (e) {
    var _that = this,
      prtIdx = _that.data.selIdx,
      idx = e.currentTarget.dataset.index,
      prtBrand = _that.data.brands[prtIdx],
      brandItem = prtBrand.items[idx];
    this.setData({
      showNumModal: true,
      selBrand: prtBrand,
      selBrandItem: brandItem,
      selBrandItemIndex: idx
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
      prtIdx = _that.data.selIdx,
      idx = e.currentTarget.dataset.index,
      list = _that.data.brands;
    list[prtIdx].items[idx].num = e.detail.value
    _that.setData({
      brands: list
    });
    var t_price = 0;
    var t_selbanNum = 0;
    var t_totalNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var c_list = list[i].items
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = c_list.length; j < j_len; ++j) {
        var num = c_list[j].num
        var price = c_list[j].price
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
      totalNum: t_totalNum
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
      nowLevelName = _that.data.nowLevel,
      list = _that.data.levelList
    for (var i = 0, len = list.length; i < len; ++i) {
      if (nowLevelName == list[i].name) {
        list[i].checked = true;
      } else {
        list[i].checked = false;
      }
    }
    this.setData({
      levelList: list
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
      list = _that.data.levelList;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (idx == i) {
        list[i].checked = true;
      } else {
        list[i].checked = false;
      }
    }
    this.setData({
      levelList: list,
      nowLevel: list[idx].name
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
    console.log(nowSymbol)
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
    if (this.data.totalNum>0){
      this.setData({
        showOrderSure: true,
        maskVisual: false
      })
    }else{
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
