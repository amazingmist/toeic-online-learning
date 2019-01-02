package vn.myclass.core.web.util;

import org.apache.commons.lang.StringUtils;
import vn.myclass.core.web.common.WebConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebCommonUtil {
    public static void addRedirectMessage(HttpServletRequest req, String crudAction, Map<String, String> messageMap) {
        if (StringUtils.isNotBlank(crudAction)) {
            req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
            switch (crudAction) {
                case WebConstant.REDIRECT_INSERT:
                    req.setAttribute(WebConstant.MESSAGE_RESPONSE, messageMap.get(WebConstant.REDIRECT_INSERT));
                    break;
                case WebConstant.REDIRECT_UPDATE:
                    req.setAttribute(WebConstant.MESSAGE_RESPONSE, messageMap.get(WebConstant.REDIRECT_UPDATE));
                    break;
                case WebConstant.REDIRECT_DELETE:
                    req.setAttribute(WebConstant.MESSAGE_RESPONSE, messageMap.get(WebConstant.REDIRECT_DELETE));
                    break;
                case WebConstant.REDIRECT_ERROR:
                    req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                    req.setAttribute(WebConstant.MESSAGE_RESPONSE, messageMap.get(WebConstant.REDIRECT_ERROR));
                    break;
            }
        }
    }
}
