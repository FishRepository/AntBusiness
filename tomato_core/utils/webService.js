const HOST = 'http://120.24.49.36';
// const HOST = 'http://127.0.0.1:80';

//发送验证码请求
const SEND_CODE_URL = '/mapi/account/sendcode.do';

//手机登录
const LOGIN_PHONE_URL = '/mapi/account/login.do';

//重置密码
const FORGET_PASSWD_URL = '/mapi/account/resetpwd.do';

//手机密码注册
const MOBILE_REGIST_URL = '/mapi/account/reg.do';

//手机端标识
const ACCOUNT_IMEI = '111111';

//用户缓存标识
const USERINFO_STORAGE_KEY = '_USRINFO';

module.exports = {
  ACCOUNT_IMEI: ACCOUNT_IMEI,
  HOST: HOST,//要引用的函数 xx:xx
  SEND_CODE_URL: SEND_CODE_URL,
  LOGIN_PHONE_URL: LOGIN_PHONE_URL,
  FORGET_PASSWD_URL: FORGET_PASSWD_URL,
  MOBILE_REGIST_URL: MOBILE_REGIST_URL,
  USERINFO_STORAGE_KEY: USERINFO_STORAGE_KEY
}
