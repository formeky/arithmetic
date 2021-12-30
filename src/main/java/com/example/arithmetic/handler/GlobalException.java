package com.example.arithmetic.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.arithmetic.pojo.enums.StatusEnum;
import com.example.arithmetic.pojo.vo.BaseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author zcw
 */
@ControllerAdvice
public class GlobalException {
//    private Logger logger = LoggerFactory.getLogger(GlobalException.class);
    /**
     * @param e
     * @param request
     * @param response
     * @return
     * @throws Exception
     * 全局异常拦截（拦截项目中的所有异常）
     */
    @ResponseBody
    @ExceptionHandler
    public BaseVo handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        System.out.println("全局异常---------------检测到非法访问");

        // 不同异常返回不同状态码
        BaseVo re = null;

        if (e instanceof NotLoginException) {
            // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            re = new BaseVo(StatusEnum.UN_LOGIN.getCode(),"未登录");
        } else if(e instanceof NotRoleException) {
            // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            re = new BaseVo(StatusEnum.PASSWORD_WRONG.getCode(), "身份异常");
        } else if(e instanceof NotPermissionException) {
            // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            re = new BaseVo(StatusEnum.TOKEN_ERROR.getCode(), "权限异常");
        } else {	// 普通异常, 输出：500 + 异常信息
            re = new BaseVo(StatusEnum.ERROR.getCode(), "未知错误，亲稍后再试呢");
        }

        // 返回给前端
        return re;
    }

}
