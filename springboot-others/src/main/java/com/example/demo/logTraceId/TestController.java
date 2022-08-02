package com.example.demo.logTraceId;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.logTraceId.intrceptor.TraceIdInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: TestController.java
 * @Date: 2022/5/3 0003  18:06
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("traceId")
    public void test() {
        LOGGER.info("test");
    }

    @RequestMapping("sync")
    public void sync() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                LOGGER.info("sync..{}", Thread.currentThread().getId());
            }, String.valueOf(i)).start();
        }
    }


    public static final long BB = 35;
    public static void main(String[] args) {
        String out = "{\"data\":[{\n" +
                "\t\t\"triggered_count\": \"1\",\n" +
                "\t\t\"manual_confirm_datetime\": null,\n" +
                "\t\t\"entrust_prop\": \"0\",\n" +
                "\t\t\"entrust_price\": null,\n" +
                "\t\t\"ymd_id\": \"115152\",\n" +
                "\t\t\"multi_trigger\": \"0\",\n" +
                "\t\t\"current_base_price\": null,\n" +
                "\t\t\"error_info\": \"触发后结束\",\n" +
                "\t\t\"buy_unit\": \"100\",\n" +
                "\t\t\"update_datetime\": \"2022-05-06 14:57:45\",\n" +
                "\t\t\"fund_account\": \"88001204\",\n" +
                "\t\t\"invalid_reason\": \"触发后结束\",\n" +
                "\t\t\"price_step\": \"1\",\n" +
                "\t\t\"exchange_type\": \"1\",\n" +
                "\t\t\"entrust_amount_type\": \"1\",\n" +
                "\t\t\"invalid_flag\": \"3\",\n" +
                "\t\t\"expiry_datetime\": \"2022-05-13 16:00:00\",\n" +
                "\t\t\"search_status\": \"3\",\n" +
                "\t\t\"create_datetime\": \"2022-05-06 14:57:39\",\n" +
                "\t\t\"entrust_price_type\": \"1\",\n" +
                "\t\t\"stock_code\": \"508000\",\n" +
                "\t\t\"code_type\": \"285770760\",\n" +
                "\t\t\"cost_price\": \"\",\n" +
                "\t\t\"entrust_bs\": \"2\",\n" +
                "\t\t\"trigger_condition\": \"股价达到9949.999时止损\",\n" +
                "\t\t\"stock_name\": \"华安张江光大REIT\",\n" +
                "\t\t\"manual_confirm\": \"0\",\n" +
                "\t\t\"strategy_parameters\": {\n" +
                "\t\t\t\"stop_profit_price\": null,\n" +
                "\t\t\t\"stop_loss_price\": \"9949.999\",\n" +
                "\t\t\t\"base_price\": \"9999.999\",\n" +
                "\t\t\t\"stop_loss_rate\": \"0.50\"\n" +
                "\t\t},\n" +
                "\t\t\"stock_account\": \"A163733485\",\n" +
                "\t\t\"entrust_amount\": \"100\",\n" +
                "\t\t\"error_no\": \"0\",\n" +
                "\t\t\"stock_type\": \"r\",\n" +
                "\t\t\"hs_openid\": \"104088001204\",\n" +
                "\t\t\"cep_type\": \"3\",\n" +
                "\t\t\"delete_datetime\": null,\n" +
                "\t\t\"strategy_id\": \"35\",\n" +
                "\t\t\"strategy_name\": \"止盈止损\",\n" +
                "\t\t\"entrust_price_mode\": \"BuyPrice1\"\n" +
                "\t}]}";
        JSONObject outobject = JSONObject.parseObject(out);
        JSONArray array = outobject.getJSONArray("data");
        JSONObject result = array.getJSONObject(0);
        if (BB == result.getLong("strategy_id")) {
            JSONObject parm = result.getJSONObject("strategy_parameters");
            parm.remove("stop_loss_price");
            parm.remove("base_price");
//            result.put("strategy_parameters", parm);
        }
        System.out.println(result.toJSONString());
    }

}
