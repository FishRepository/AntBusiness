package com.backend.common;

public class AlipayConfig {
    public static final String APPID = "2019062965759225";

    /** 支付宝网关*/
    public static final String GATE = "https://openapi.alipay.com/gateway.do";

    /** (应用私钥)*/
    public static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzLULUjL7fhK4rlBsiu99fLsbPFpO8wN3xYxrQxpHUC0VYFIAXg3us9rRrqIxXmKYQB/zNNYFvpfWbF6YGPvMaHkzMUAUqblfXVSFKCU6Ntcth4P+Ky1w3MCkEwI9FRzvONIOwSjenqxzyy333TLrN1FVXv7qoiE7KFQw+64z8MD2HIAY/EJn9WkuzyYkvpdR0W8AmsoRJCxyq/8B3JY8xteSJDIMqpnSe5PxajPC7OT31eBmERzj5uUFlUcdzOqjSVZCpA08mzBAGl42JwGc9eOujYUoMAzf2c8zatoERPJkaJRKd3howthueI+N/rFHPXl7aVK/Rg48jUP902CWfAgMBAAECggEAAYaLISApp+GLUhequ3r6BedyDeVVULAbs6hyXAC00SdfacaS6KBX9zsKgjgnKb/E+jH3NxATkA3Lr4xd/4OdskU7ximQQbI60TyKUgUORCqeFrFBu77LhDTGxKGXFrHqknDkBiY7nxPX5UFSSz6+AG6kY2Dgh/rRIOtY9VgwFR2YYSlgBIQPQqgVmhzArJgdqBAvsEukIytw7uYUSU6+z02tQQyHs76JDzSaDnmuzZH6fAZ2WDYbLJ6esIY5EdNBLM7cSc822q2KOWlphZCqzfClt6B091MoDb51JLld/AmG8BSEWI/IXzTXOa6vZs+rA1u2qmJ7oPval6CjmbofgQKBgQDjgEortDDU7E/jhDVgd695O6mKWKn3AyCSE/etyXBbF9KkAMKbdA+WquXpQrFZ9hsIq9W65n1OeAYROhr8y8uqEN/q+r+NmpxPIy6eIR+HUSD8WcFs3gW9WTdbs65LI6gLBylk4bLT4hswHwkAiujGtKCyz6FLCOmH5fSoYcTqfwKBgQDJn0NrkUL4HxrN8fB42KHTVww+wmmdas/ihSIjednaKFhxv3EECCS4c7FrmkVwkzYKKRXQFchc3nfdEC+jLT+9lHuRL96FCbY4pwR+GaGfak0UyCsPvuio/y5FfCGTyu/dKiwdLs4sKVioRkHYLSl5My6FdYdYLrA+iAkyNDv04QKBgQDdT83XmiDu4TwOLmzmfUfcfee+R+Z//zDOKlKFYzLDyvcEyCCIrW0EuxeRxWlWBI3j75WpjdNUZHoyg/4A8hgcRvItWuTBUijA647MEZjkQE4Abyg1Sf73ZjTuJmtleTheEgd4tf8LFCgytd5CAt/TfZ/ekxFrNy5HH7LToti27QKBgBzzEzkkuFrsDGs5q++OG14/B3ETdRq3NXYu64HwgwlB9cEPXejYSn15jyYGFqZdXMsEry8bkZNUFh3VryFc4+QSp9Nm7ipoBJQJ1ylMANrk5oBvZU9zd/CcA0bS1fdkkiBNZ9wg1lWc8i/nNpkVkvjmEm3zPCW7Pm8cFUXyHhHBAoGATGBwIYhCPr5qwIPn2NDQMfRI9CgJusMZByuazhrFBZjsQsEd0lXXaXWyoDKnBAIfz6kqvrAUOIYr0KLK6w0h0m9BFYQPqBABAeW+L7STnT3Pf4oJh51ljhFCKIMY6WiL1E6rxenEceWYwM9RdbZbmv1CStfHUS0LPHcsuZoC1Tc=";

    /** （应用公钥）*/
    public static final String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsy1C1Iy+34SuK5QbIrvfXy7GzxaTvMDd8WMa0MaR1AtFWBSAF4N7rPa0a6iMV5imEAf8zTWBb6X1mxemBj7zGh5MzFAFKm5X11UhSglOjbXLYeD/istcNzApBMCPRUc7zjSDsEo3p6sc8st990y6zdRVV7+6qIhOyhUMPuuM/DA9hyAGPxCZ/VpLs8mJL6XUdFvAJrKESQscqv/AdyWPMbXkiQyDKqZ0nuT8Wozwuzk99XgZhEc4+blBZVHHczqo0lWQqQNPJswQBpeNicBnPXjro2FKDAM39nPM2raBETyZGiUSnd4aMLYbniPjf6xRz15e2lSv0YOPI1D/dNglnwIDAQAB";

    /**  支付宝公钥 */
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoTOFVvc5TfjLheQ3Cir6zla9FF8vCk3AEwywPS/d4wPpaZ3ZdoN5dnY+a+4pX4GEGET2L/bCx+EfE6+i1XZwL/uJ0u8nPnbxsGQWWdG0JWt5pIcTF4xRXucqKwFp6bW+voBBu8TzpwD2y3UzeUqsEVq4mW1j7CvBABxZVEhtAR6zqIujLuNPiyRRo8OAxIiw0zf+m4JlT4dstuADwZg20sEg91z5eRlR9tStDdYo/bnLzikddW/kXlQT1in0Nq9fsaM4uqghaImiZzCUezScmB29ptG2RI6xUK5svPC/O6VQZsDWnOZ7R/0bXWRNlgBhsqYKHjL3v/XvsoU64tMrBQIDAQAB";

    /** 合作伙伴ID（支付宝给定） */
    public static final String SELLERID = "2088721372623632";

    /** 商户账户 */
    public static final String SELLER = "此处为商户账号";

    /** 编码方式 */
    public static final String CHARSET = "utf-8";

    /** sign方式*/
    public static final String SIGN_TYPE = "RSA";

    /** 支付结束*/
    public static final String TRADE_FINISHED = "TRADE_FINISHED";

    /** 支付成功*/
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    public AlipayConfig() {

    }

}
