<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>模板列表</title>
    <jsp:include page="/common/common.jsp" flush="true"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/scripts/attachment.js"></script>
    <script type="text/javascript">
        var rootPath = '${pageContext.request.contextPath}';
    </script>
    <script type="text/hwui-template" id="localeDialog">
        <!-- 编辑模板 -->
        <table width="100%">
            <tr>
                <td class="editor-label">
                    <label for="id">id:</label>
                </td>
                <td class="editor-field" >
                    <div data-container-for="id"></div>
                </td>

            </tr>
            <tr>
                <td class="editor-label">
                    <label for="name">名称:</label>
                </td>
                <td class="editor-field" >
                    <div data-container-for="name"></div>
                </td>
            </tr>
            <tr>
                <td class="editor-label">
                    <label for="filePath">存储路径:</label>
                </td>
                <td class="editor-field" colspan="3">
                    <div data-container-for="filePath"></div>
                </td>
            </tr>
            <tr>
                <td class="editor-label">
                    <label for="fileName">模板文件名:</label>
                </td>
                <td class="editor-field" >
                    <div data-container-for="fileName"></div>
                </td>
            </tr>
            <tr>
                <td class="editor-label">
                    <label for="dataFileName">模板数据名:</label>
                </td>
                <td class="editor-field" >
                    <div data-container-for="dataFileName"></div>
                </td>
            </tr>
            <tr>
                <td class="editor-label">
                    <label for="attachmentId">附&nbsp;&nbsp;&nbsp;&nbsp;件:&nbsp;&nbsp;&nbsp;</label>
                </td>
                <td class="editor-field" colspan="3">
                    <div data-container-for="attachmentId"></div>
                </td>
            </tr>
        </table>

    </script>
    <script type="text/javascript" src="scripts/template_list.js"></script>
</head>
<body>
<!-- 查询区域 -->
<div id="templateSearchArea">
    <table width="100%">
        <tr>
            <td>
                <a class="k-button k-button-icontext k-button-search" href="#">
                    <span class="k-icon k-i-search"></span>查询
                </a>
                <a class="k-button k-button-icontext k-button-reset" href="#">
                    <span class="k-icon k-i-refresh"></span>重置
                </a>
            </td>
        </tr>
    </table>
</div>

<!-- 列表区域 -->
<div id="templateGrid"></div>

</body>
</html>