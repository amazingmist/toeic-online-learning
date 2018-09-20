package vn.myclass.core.web.command;

import java.util.List;

public class AbstractCommand<T> {
    protected T pojo;
    private String crudAction;
    private List<T> listResult;
    private String tableId = "tableList";
    private int maxPageItems = 5;
    private int totalItems = 0;
    private int firstItem = 0;
    private String sortExpression;
    private String sortDirection;
    private String[] checkList;
    private String messageResponse;
    private int page = 1;
    private String urlType;
    private int totalPages;

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public String getCrudAction() {
        return crudAction;
    }

    public void setCrudAction(String crudAction) {
        this.crudAction = crudAction;
    }
}
