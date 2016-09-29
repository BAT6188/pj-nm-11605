<%@ page import="java.io.File" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bussinessId = request.getParameter("bussinessId");
    File file = new File("C:\\temp\\upload\\"+bussinessId);
    File[] fileDirs = file.listFiles();
    List<Map<String,Object>> files = new ArrayList<>();
    for(File tempFile : fileDirs){
        if(tempFile.isDirectory()){
            continue;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("uuid",tempFile.getName().split("_")[0]);
        map.put("name",tempFile.getName().split("_")[1]);
        map.put("size",tempFile.length());
        files.add(map);
    }
    out.write(JSON.toJSONString(files));
%>