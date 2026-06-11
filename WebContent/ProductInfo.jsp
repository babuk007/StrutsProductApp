<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Product Information</title>
</head>
<body>
    <h1>Product Details</h1>
    <p><b>Name:</b> <s:property value="name"/></p>
    <p><b>Price:</b> $<s:property value="price"/></p>
    <p><b>Description:</b> <s:property value="description"/></p>
</body>
</html>
