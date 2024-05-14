package pers.xyj.modules.accountKeeper.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.vo.SmartKeepRecordVo;
import pers.xyj.modules.accountKeeper.domain.vo.SmartKeepVo;
import pers.xyj.modules.accountKeeper.service.SmartKeepService;
import pers.xyj.modules.common.utils.MultipartFileToFile;
import pers.xyj.modules.common.utils.ShoppingReceipt;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
public class SmartKeepServiceImpl implements SmartKeepService {

    @Autowired
    private ShoppingReceipt shoppingReceipt;
    @Override
    public ResponseResult smartKeepByShoppingReceipt(MultipartFile receipt) {
        File file = null;
        try {
            file = MultipartFileToFile.multipartFileToFile(receipt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String resultStr = shoppingReceipt.shoppingReceipt(file);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        ArrayList<SmartKeepRecordVo> recordList = new ArrayList<>();
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        for (int i = 0; i < words_result.size(); i++) {
            JSONObject object = words_result.getJSONObject(i);
            String consumption_date = object.getJSONArray("consumption_date").getJSONObject(0).getObject("word", String.class);
            String shop_name = object.getJSONArray("shop_name").getJSONObject(0).getObject("word", String.class);
            JSONArray table = object.getJSONArray("table");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date createDate = null;
            try {
                createDate = format.parse(consumption_date);
            } catch (ParseException e) {
                log.info("小票日期信息缺失");
                createDate = new Date();
            }
            for (int j = 0; j < table.size(); j++) {
                JSONObject productObject = table.getJSONObject(j);
                String productName = productObject.getJSONObject("product").getObject("word", String.class);
                int index = productName.indexOf("/");
                if (index != -1){
                    productName = productName.substring(index + 1);
                }
                String price = productObject.getJSONObject("unit_price").getObject("word", String.class);
                Double amount = 0.0;
                if (isDouble(price)){
                    amount = Double.parseDouble(price);
                }
                SmartKeepRecordVo smartKeepRecordVo = new SmartKeepRecordVo(amount, shop_name + productName, createDate);
                recordList.add(smartKeepRecordVo);
            }
        }
        SmartKeepVo smartKeepVo = new SmartKeepVo(recordList.size(), recordList);
        return ResponseResult.okResult(smartKeepVo);
    }
    private boolean isDouble(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
