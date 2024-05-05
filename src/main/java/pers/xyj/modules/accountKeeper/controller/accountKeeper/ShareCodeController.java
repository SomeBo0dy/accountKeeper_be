package pers.xyj.modules.accountKeeper.controller.accountKeeper;



import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.JoinBookDto;
import pers.xyj.modules.accountKeeper.service.ShareCodeService;
import pers.xyj.modules.common.annotation.SystemLog;


@Api(value = "ShareCodeControllerApi",tags={"分享账本接口"})
@RestController
@RequestMapping("shareCode")
public class ShareCodeController{

    @Autowired
    private ShareCodeService shareCodeService;

    @ApiOperation(value="获取邀请码")
    @SystemLog(businessName = "获取邀请码")
    @GetMapping("/{bookId}")
    public ResponseResult getShareCode(@PathVariable("bookId") Integer bookId){
        return shareCodeService.getShareCode(bookId);
    }
    @ApiOperation(value="加入账本")
    @SystemLog(businessName = "加入账本")
    @PostMapping("/cooperation")
    public ResponseResult joinBook(@RequestBody JoinBookDto bookDto){
        return shareCodeService.joinBook(bookDto.getCode());
    }
    @ApiOperation(value="退出账本")
    @SystemLog(businessName = "退出账本")
    @DeleteMapping("/cooperation/{bookId}")
    public ResponseResult quitBook(@PathVariable("bookId") Integer bookId){
        return shareCodeService.quitBook(bookId);
    }
}