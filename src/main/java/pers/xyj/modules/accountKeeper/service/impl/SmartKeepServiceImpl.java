package pers.xyj.modules.accountKeeper.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.SmartKeepService;
import pers.xyj.modules.common.utils.MultipartFileToFile;
import pers.xyj.modules.common.utils.ShoppingReceipt;

import java.io.File;
@Service
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
        //TODO
        JSONObject shop_name = jsonObject.getJSONObject("words_result").getJSONObject("shop_name");
        return ResponseResult.okResult(shop_name);
    }
}
