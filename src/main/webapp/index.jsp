<html>
<head>
    <title>Fourth Task</title>
</head>
<body>
    <form action="ControlServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file"><!--<input type="text" name="file_name" value="">-->
        <table>
            <tr>
                <td>
                    <input type="submit" name="button" value="DOM">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="button" value="SAX">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="button" value="StAX">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>