<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kodlak: Game Setup</title>
    <%@include file="include_common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add_player_control.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/role_control.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sidebar/role_section.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sidebar/link_section.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add_role_control.js"></script>
</head>
<body>
    <div id="main">
        <div id="sidebarContainer"></div>
        <div id="playerCanvasContainer"></div>
    </div>
    <script>
        var roleSection = new RoleSection();

        var playerCanvas = new PlayerCanvas(true, roleSection);
        var canvas = playerCanvas.create();
        $('#playerCanvasContainer').append(canvas);
        playerCanvas.refresh();

        var sidebar = new Sidebar();
        sidebar.addSection(roleSection);
        sidebar.addSection(new LinkSection('/play', 'Finish setup'));
        var sidebarContent = sidebar.create();
        $('#sidebarContainer').append(sidebarContent);
    </script>
</body>
</html>
