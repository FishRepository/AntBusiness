// const HOST = 'http://120.24.49.36';
const HOST = 'http://127.0.0.1:80';

//发送验证码请求
const SEND_CODE_URL = '/mapi/account/sendcode.do?account_userphone=MTM5MTExMTExMTE=&type=1';

const LOGIN_PHONE_URL = '/mapi/account/login.do';

const ACCOUNT_IMEI = '111111';

module.exports = {
  ACCOUNT_IMEI: ACCOUNT_IMEI,
  HOST: HOST,//要引用的函数 xx:xx
  SEND_CODE_URL: SEND_CODE_URL,
  LOGIN_PHONE_URL: LOGIN_PHONE_URL

}
