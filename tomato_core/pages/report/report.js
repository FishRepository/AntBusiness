// pages/report/report.js
import * as echarts from '../../ec-canvas/echarts';
const WebService = require('../../utils/webService.js');
var Base64 = require('../../utils/base64.modified.js'); 
const app = getApp();
var chartLine= null;

function getOption(sevendayList, saleList) {
  var option = {
    color: ["#F50057"],
    grid: {
      left: "5%",
      right: "10%",
      bottom: "5%",
      containLabel: true,
      top: "20%"
    },
    tooltip: {
      show: true,
      trigger: 'axis',
      position: ['50%', '50%'],
      axisPointer:{
        type:'shadow'
      },
      backgroundColor:'#e64663',
      textStyle:{
        color:'#fff'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: sevendayList,
      axisLabel: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontSize: 10
      },
      axisLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      splitArea: {
        show: false
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      name: '七日收益趋势(元)',
      nameTextStyle: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontWeight: 'bold',
        padding: [10, 0, 10, 20]
      },
      type: 'value',
      axisLabel: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontSize: 10
      },
      axisLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      splitArea: {
        show: false
      },
      splitLine: {
        show: false
      }
    },
    series: [{
      type: 'line',
      smooth: true,
      showSymbol:false,
      lineStyle: {
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [{
            offset: 0,
            color: '#e64663'
          }, {
            offset: 1,
            color: '#fbdbdb'
          }],
          global: false
        }
      },
      areaStyle:{
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,  
          y2: 1,
          colorStops: [{
            offset: 0,
            color: '#e64663'
          }, {
            offset: 1,
              color: '#fff'
          }],
          global: false
        }
      },
      data: saleList
    }]
  };
  return option;
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    todayData: {
      xiaoshou: '',
      shouyi: ''
    },
    star: '******',
    see: true,
    leiji: {
      xiaoshou: '',
      shouyi: ''
    },
    // {
    // customer_username: '王小明',
    // customer_icon: 'http://p1.music.126.net/Jxp3emWQRhmrTu351qdIog==/109951163556000475.jpg?param=180y180',
    // rank: 1
    // }
    users: [],
    pros: [],
    words: [],
    ec: {
      onInit: function (canvas, width, height) {
        //初始化echarts元素，绑定到全局变量，方便更改数据
        chartLine = echarts.init(canvas, null, {
          width: width,
          height: height
        });
        canvas.setChart(chartLine);
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var _that = this;
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: WebService.HOST + WebService.USER_REPORT_URL,
      data: {
        account_id: app.globalData.userInfo.account_id,
        account_userphone: Base64.encode(app.globalData.userInfo.account_userphone)
      },
      success: function (res) {
        wx.hideLoading();
        const totalprofit = res.data.totalprofit;
        if (totalprofit && totalprofit.code == 0) {
          //今日销售  累计销售
          _that.setData({
            todayData: {
              xiaoshou: totalprofit.today_sales,
              shouyi: totalprofit.today_profit
            },
            leiji: {
              xiaoshou: totalprofit.total_sales,
              shouyi: totalprofit.total_profit
            },
          });
          //七日销售数据
          const sevendayList = res.data.sevendayList;
          const saleList = res.data.saleList;
        }
        //销售榜用户
        const customerTop = res.data.customerTop;
        if (customerTop && customerTop.code == 0) {
          if (customerTop.list && customerTop.list.length > 0) {
            for (var i = 0; i < 3; i++) {
              customerTop.list[i].rank = i + 1;
            }
          }
          _that.setData({
            users: customerTop.list
          });
        }
        //本月商品销售排行
        const goodsTop = res.data.goodsTop;
        if (goodsTop && goodsTop.code == 0) {
          if (goodsTop.list && goodsTop.list.length > 0) {
            for (var i = 0; i < 3; i++) {
              goodsTop.list[i].rank = i + 1;
            }
          }
          _that.setData({
            pros: goodsTop.list
          });
        }
        //心灵鸡汤
        const noticeList = res.data.noticeList;
        if (noticeList && noticeList.code == 0) {
          _that.setData({
            words: noticeList.list
          });
        }
        //收益趋势
        const sevendayList = res.data.sevendayList;
        const saleList = res.data.saleList;
        if (sevendayList && saleList.length>0 && saleList && saleList.length>0){
          var option = getOption(sevendayList, saleList);
          chartLine.setOption(option);
        }

      },
      fail: function () {
        wx.hideLoading();
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  switchSee: function() {
    var _that = this
    if (_that.data.see) {
      _that.setData({
        see: false
      })
    } else {
      _that.setData({
        see: true
      })
    }
  },
})