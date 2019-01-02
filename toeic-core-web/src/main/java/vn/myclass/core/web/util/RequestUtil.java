package vn.myclass.core.web.util;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import vn.myclass.core.web.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBean(HttpServletRequest req, AbstractCommand bean){
        if (bean != null){
//            get parameter from request url
            String sortExpression = req.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = req.getParameter( new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String pageStr = req.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

//            parse page if it existed
            int page = 1;
            try {
                page = Integer.parseInt(pageStr);
            }catch (Exception ignore){

            }

            int firstItem = (page - 1) * bean.getMaxPageItems();

//            set properties for bean
            bean.setPage(page);
            bean.setSortExpression(sortExpression);
            bean.setSortDirection(sortDirection);
            bean.setFirstItem(firstItem);
        }
    }
}
