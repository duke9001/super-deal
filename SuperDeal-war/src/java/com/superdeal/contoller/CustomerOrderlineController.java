package com.superdeal.contoller;

import com.superdeal.contoller.util.JsfUtil;
import com.superdeal.contoller.util.PaginationHelper;
import com.superdeal.entity.CustomerOrder;
import com.superdeal.entity.CustomerOrderline;
import com.superdeal.model.CustomerOrderFacade;
import com.superdeal.model.CustomerOrderlineFacade;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
@ManagedBean(name = "customerOrderlineController")
@SessionScoped
public class CustomerOrderlineController implements Serializable {

    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private CustomerOrderlineFacade ejbFacade;
    List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
    List<CustomerOrderline> customerOrderLineList = new ArrayList<CustomerOrderline>();
    private CustomerOrderline current;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private CustomerOrder customerOrder = new CustomerOrder();

    public CustomerOrderlineController() {
    }

    //set Customer Order Amount = Sum of all Customer Order Line Amounts
    public void addAmountCustomerOrder() {


        double total;

        customerOrderList = customerOrderFacade.findAll();
        for (int i = 0; i < customerOrderList.size(); i++) {
            customerOrder = customerOrderList.get(i);
            if (customerOrder.getOrderNo().equals(current.getOrderNo())) {
                total = customerOrder.getAmount() + current.getAmount();
                customerOrder.setAmount(total);
                this.customerOrderFacade.edit(customerOrder);
            }

        }
    }

    public CustomerOrderline getSelected() {
        if (current == null) {
            current = new CustomerOrderline();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CustomerOrderlineFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (CustomerOrderline) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CustomerOrderline();
        selectedItemIndex = -1;

        return "Create";
    }

    public String create() {

        try {

            getFacade().create(current);
            addAmountCustomerOrder();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CustomerOrderlineCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CustomerOrderline) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CustomerOrderlineUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CustomerOrderline) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CustomerOrderlineDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = CustomerOrderline.class)
    public static class CustomerOrderlineControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomerOrderlineController controller = (CustomerOrderlineController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customerOrderlineController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CustomerOrderline) {
                CustomerOrderline o = (CustomerOrderline) object;
                return getStringKey(o.getLineNo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CustomerOrderline.class.getName());
            }
        }
    }
}
