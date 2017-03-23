package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.common.dict.bean.ZtreeObj;
import com.harmonywisdom.dshbcbp.office.bean.ManualCatalog;
import com.harmonywisdom.dshbcbp.office.service.ManualCatalogService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.List;

public class ManualCatalogAction extends BaseAction<ManualCatalog, ManualCatalogService> {
    @AutoService
    private ManualCatalogService manualCatalogService;

    @Override
    protected ManualCatalogService getService() {
        return manualCatalogService;
    }

    public void getManualCatalogTree(){
        String id=request.getParameter("id");
        //List<ZNodeDTO> levels = getService().getManualCatalog(id);
        List<ZtreeObj> list = getService().getManualCatalogZtree();
        write(list);
    }

    @Override
    public void save() {
        List<ManualCatalog> catalogs=getService().findAll();
        String manualId=request.getParameter("manualId");
        String name=request.getParameter("name");
        ManualCatalog catalog=new ManualCatalog();
        if(catalogs.size()>0){
            catalog.setManualId(manualId);
            catalog.setName(name);
            manualCatalogService.save(catalog);
        }else{
            catalog.setManualId("root");
            catalog.setName(name);
            manualCatalogService.save(catalog);
        }
        write(catalog);
    }
    //查询手册
    public void findByZtreeId(){
        String id=request.getParameter("manualId");
        List<ManualCatalog> list=getService().findSelectTreeId(id);
        write(list);
    }

    @Override
    public void delete() {
        super.delete();
        write(true);
    }
}