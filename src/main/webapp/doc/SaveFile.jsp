<%@ page language="java" import="java.util.*" pageEncoding="UTF-16BE" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordreader.*" %>
<%
    response.setCharacterEncoding("gb2312");
    FileSaver fs = new FileSaver(request, response);
    fs.saveToFile(request.getSession().getServletContext().getRealPath("doc/") + "/" + fs.getFileName());
    fs.close();
%>
