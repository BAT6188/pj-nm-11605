#JAVA源码目录结构：
    com/harmonywisdom/$PROJECT
                        /module1
                            /action
                            /bean
                            /dao
                            /service
                                /impl
                        /module2
                        ...
                        /moduleN

#Resources目录结构
    META-INF/
        jdbc.properties  -> 数据库连接
        persistence.xml  -> hibernate jpa配置文件
    spring/
        applicationContext.xml  -> Spring主配置文件
        ...                     -> 其它Spring配置文件
    license.xml                 -> 序列号
    struts.xml                  -> Struts2配置文件

#各类继承的父类
* bean - 没有父类或自行创建父类继承
* dao  - com.harmonywisdom.framework.dao.DefaultDAO<Entity, PK_CLASS>
* service - com.harmonywisdom.framework.service.IBaseService<Entity, PK_ClASS>
* service.impl - com.harmonywisdom.framework.service.BaseService<Entity, PK_CLASS>
* action  - com.harmonywisdom.framework.action.BaseAction<Entity, Service>
