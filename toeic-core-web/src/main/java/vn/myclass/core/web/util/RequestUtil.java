package vn.myclass.core.web.util;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import vn.myclass.core.web.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBean(HttpServletRequest req, AbstractCommand command){
        if (command != null){
//            get parameter from request url
            String sortExpression = req.getParameter(new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = req.getParameter( new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String pageStr = req.getParameter(new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

//            parse page if it existed
            int page = 1;
            try {
                page = Integer.parseInt(pageStr);
            }catch (Exception ignore){

            }

            int firstItem = (page - 1) * command.getMaxPageItems();

//            set properties for command
            command.setPage(page);
            command.setSortExpression(sortExpression);
            command.setSortDirection(sortDirection);
            command.setFirstItem(firstItem);
        }
    }
}
