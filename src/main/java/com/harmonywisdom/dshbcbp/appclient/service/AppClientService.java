package com.harmonywisdom.dshbcbp.appclient.service;

import com.harmonywisdom.dshbcbp.appclient.bean.AppClient;
import com.harmonywisdom.framework.service.IBaseService;

public interface AppClientService extends IBaseService<AppClient, String> {

    AppClient findNewestApk();
}