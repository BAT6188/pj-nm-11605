<%@ page import="java.io.File" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bussinessId = request.getParameter("bussinessId");
    String qquuid = request.getParameter("qquuid");
    File file = new File("C:\\temp\\upload\\"+bussinessId);
    File[] fileDirs = file.listFiles();
    File deleteFile = null;
    for(File tempFile : fileDirs){
        if(tempFile.isDirectory()){
            continue;
        }
        if(tempFile.getName().indexOf(qquuid)==0){
            deleteFile = tempFile;
            break;
        }
    }
    JSONObject jsonObject = new JSONObject();
    if(deleteFile != null) {
        deleteFile.delete();
        jsonObject.put("success", true);
    }else {
        jsonObject.put("success", false);
        jsonObject.put("error","没有发现文件");
    }
    jsonObject.put("reset",false);
    jsonObject.put("preventRetry",true);
    jsonObject.put("reset",false);
    System.out.println(jsonObject.toJSONString());
    out.write(jsonObject.toJSONString());
%>