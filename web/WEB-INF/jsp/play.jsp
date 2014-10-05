<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kodlak: Game in Progress</title>
    <%@include file="include_common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sidebar/action_section.js"></script>
</head>
<body>
    <div id="main">
        <div id="sidebarContainer"></div>
        <div id="playerCanvasContainer"></div>
    </div>
    <script>
        var playerCanvas = new PlayerCanvas(false);
        var canvas = playerCanvas.create();
        $('#playerCanvasContainer').append(canvas);
        playerCanvas.refresh();

        var sidebar = new Sidebar();
        sidebar.addSection(
                new ActionSection(
                        function() {
                            alert('lol')
                        },
                        'Advance phase'
                )
        );
        var sidebarContent = sidebar.create();
        $('#sidebarContainer').append(sidebarContent);
    </script>
</body>
</html>
