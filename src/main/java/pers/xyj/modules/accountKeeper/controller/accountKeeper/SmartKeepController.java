package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.SmartKeepService;

@Api(value = "ShareCodeControllerApi",tags={"分享账本接口"})
@RestController
@RequestMapping("smartKeep")
public class SmartKeepController {

    @Autowired
    private SmartKeepService smartKeepService;

    @ApiOperation(value="上传小票")
    @PostMapping("/shoppingReceipt")
    public ResponseResult smartKeepByShoppingReceipt(MultipartFile receipt){
        return smartKeepService.smartKeepByShoppingReceipt(receipt);
    }
}
