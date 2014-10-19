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

        if ('NIGHT' === "${phase}") {
            canvas.addClass('night');
        } else {
            canvas.removeClass('night');
        }

        var sidebar = new Sidebar();
        sidebar.addSection(
            new ActionSection(
                function() {
                    $.post(
                        'advancePhase.do',
                        function(/** {phase: string} */data) {
                            var phase = data.phase;
                            if ('NIGHT' === phase) {
                                canvas.addClass('night');
                            } else {
                                canvas.removeClass('night');
                            }
                            flashBackground(this._control, '#20f020');
                        }.bind(this)
                    ).fail(function() {
                        flashBackground(this._control, '#f03020');
                    }.bind(this));
                },
                'Advance phase'
            )
        );
        var sidebarContent = sidebar.create();
        $('#sidebarContainer').append(sidebarContent);
    </script>
</body>
</html>
