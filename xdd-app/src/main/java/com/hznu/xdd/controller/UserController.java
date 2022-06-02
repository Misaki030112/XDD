package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Dto.TokenDto;
import com.hznu.xdd.domain.Dto.UserDto;
import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.*;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Transactional(rollbackFor = Exception.class)
public class UserController {
    private final UserService userService;
    private final UserInfoUtil userInfoUtil;
    private final ClientDetailsService clientDetailsService;
    private final AuthorizationServerTokenServices authorizationServerTokenServices;
    private final OAuth2RequestFactory oAuth2RequestFactory;

    public UserController(UserService userService, UserInfoUtil userInfoUtil, ClientDetailsService clientDetailsService, AuthorizationServerTokenServices authorizationServerTokenServices) {
        this.userService = userService;
        this.userInfoUtil = userInfoUtil;
        this.clientDetailsService = clientDetailsService;
        this.authorizationServerTokenServices = authorizationServerTokenServices;
        this.oAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
    }


    @PostMapping(value="/post/refreshToken",produces = { "application/json;charset=UTF-8" })
    public Result refreshToken(@RequestBody TokenDto tokenDto){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("client_id","wx");
        parameters.put("client_secret","secret");
        parameters.put("refresh_token",tokenDto.getRefreshToken());
        parameters.put("grant_type","refresh_token");
        parameters.put("scope","all");
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(parameters.get("client_id"));
        TokenRequest tokenRequest = oAuth2RequestFactory.createTokenRequest(parameters, authenticatedClient);
        tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
        OAuth2AccessToken token;
        try {
            token = authorizationServerTokenServices.refreshAccessToken(parameters.get("refresh_token"), tokenRequest);
        } catch (InvalidGrantException e) {
            e.printStackTrace();
            return new Result(20003,"请重新登录");
        }
        return Result.ok(token,"刷新Token成功");
    }


    @PostMapping(value= "/post/user/info", produces = { "application/json;charset=UTF-8" })
    public Result userInfo(@RequestBody UserDto userDto,
                            Authentication authentication){
        try {
            UserDO userDO = userService.initUserInfoByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication),
                    userDto.getEncryptedData(), userDto.getIv(), userInfoUtil.getSessionKey(authentication));
            return Result.ok(userDO,"登录成功！");
        } catch (InvalidAlgorithmParameterException | BadPaddingException | NoSuchAlgorithmException | InvalidParameterSpecException e) {
            e.printStackTrace();
            return new Result(StatusCode.PARAMS_CHECK_FAILED);
        } catch (IllegalBlockSizeException|InvalidKeyException e) {
            e.printStackTrace();
            return new Result(StatusCode.INVALID_PARAMS);
        }
    }

    @PostMapping(value = "/post/user/login",produces = { "application/json;charset=UTF-8" })
    public Result login(Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(userDO==null)
            return new Result(StatusCode.NO_EXIST);
        else{
            UserInfoVO userInfoVO=new UserInfoVO();
            BeanUtils.copyProperties(userDO,userInfoVO);
            return Result.ok(userInfoVO,"登录成功");
        }
    }


    @GetMapping(value = "/get/user/info",produces = { "application/json;charset=UTF-8" })
    public Result getUserInfo(@RequestParam(value = "id")Integer id){
        UserDO userDO = userService.getUserById(id);
        if(userDO==null)
            return new Result(StatusCode.NO_EXIST);
        else {

            UserFocusInfoVO userFocusInfoVO = new UserFocusInfoVO(new UserInfoVO(userDO));
            userFocusInfoVO.setIs_focus(userService.isIFocusSomePeople(id));
            return Result.ok(userFocusInfoVO,"登录成功");
        }

    }


    @GetMapping(value="/get/user",produces = { "application/json;charset=UTF-8" })
    public Result searchUserByName(@RequestParam("key") String key,@RequestParam("page") Integer page,@RequestParam("offset") Integer offset){
        List<UserDO> userDOS = userService.searchUserByNickName(key,page,offset);
        List<UserFocusInfoVO> userFocusInfoVOS = new ArrayList<>();
        userDOS.forEach(u->{userFocusInfoVOS.add(new UserFocusInfoVO(new UserInfoVO(u)));});
        return Result.ok(new ListVO(userFocusInfoVOS),"获取成功");
    }


    @PostMapping(value = "/post/user/report",produces = { "application/json;charset=UTF-8" })
    public Result reportUser(@RequestBody reportDto reportDto){
        boolean flag = userService.reportUser(reportDto);
        if(flag) return new Result(StatusCode.SUCCESS.getCode(),"提交成功");
        else return new Result(StatusCode.INVALID_PARAMS);
    }


    @PostMapping(value = "/post/user/info/change",produces = { "application/json;charset=UTF-8" })
    public Result changeUserInfo(@RequestBody UserDto userDto,Authentication authentication){
        UserDO user = userService.changeUserInfo(userInfoUtil.getWxOpenIdXiaododoMini(authentication),
                userDto.getNickname(),
                userDto.getAvatar(),
                userDto.getSignature(),
                userDto.getBirthday(),
                userDto.getProvince(),
                userDto.getCity(),
                userDto.getDistrict(),
                userDto.getGender()==null?null:Short.valueOf(userDto.getGender()));
        if(user!=null){
            return Result.ok(user,"修改成功！");
        }else{
            return new Result(StatusCode.INVALID_PARAMS.getCode(),"操作失败");
        }
    }

    @PostMapping(value="/post/user/my/voted")
    public Result getVoteUgcLog(@RequestBody UserDto userDto,Authentication authentication){
        UserPageVO voteUgcLog = userService.getVoteUgcLog(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getPage(), userDto.getOffset());
        return Result.ok(voteUgcLog,"获取成功");
    }

    @PostMapping(value = "/post/user/my/commented")
    public Result getCommentLog(@RequestBody UserDto userDto,Authentication authentication){
        UserPageVO commentUgcLog = userService.getCommentUgcLog(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getPage(), userDto.getOffset());
        return Result.ok(commentUgcLog,"获取成功");
    }

    @PostMapping(value = "/post/user/my/collected")
    public Result getCommentUgcLog(@RequestBody UserDto userDto,Authentication authentication){
        UserPageVO collectUgcLog = userService.getCollectUgcLog(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getPage(), userDto.getOffset());
        return Result.ok(collectUgcLog,"获取成功");
    }


    @PostMapping(value = "/post/user/focus")
    public Result getFocuseUser(@RequestBody UserDto userDto,Authentication authentication){
        UserPageVO focusUser = userService.getFocusUser(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getPage(), userDto.getOffset());
        return Result.ok(focusUser,"获取成功");
    }

    @PostMapping(value = "/post/user/focused")
    public Result getFocusedUser(@RequestBody UserDto userDto,Authentication authentication){
        UserPageVO focusedUser = userService.getFocusedUser(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getPage(), userDto.getOffset());
        return Result.ok(focusedUser,"获取成功");
    }

    @PostMapping(value = "/post/user/action/focus")
    public Result focusUser(Authentication authentication,@RequestBody UserDto userDto){
        boolean flag = userService.FocusUser(userInfoUtil.getWxOpenIdXiaododoMini(authentication), userDto.getUser_id(), userDto.getStatus());
        if(flag){
            return Result.ok(null,"操作成功");
        }else return new Result(20004,"操作非法");
    }

    @PostMapping(value="/post/user/session_key/update")
    public Result updateSessionKey(Authentication authentication,@RequestBody UserDto userDto){
        boolean flag = userService.UpdateSessionKey(authentication, userDto.getCode());
        if(flag){
            return Result.ok(null,"更新成功");
        }else return new Result(20004,"操作失败");
    }

    @GetMapping(value = "/get/user/official_account/focus")
    public Result official_account(Authentication authentication){
        UserVO userVO = new UserVO();
        userVO.setFocus(userService.isFocusWxOffical(userInfoUtil.getWxOpenIdXiaododoMini(authentication)));
        return Result.ok(userVO,"获取成功");
    }


}
