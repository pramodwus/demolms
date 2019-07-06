<html>
<head>
</head>
	<body>
	    <#include "header.ftl">
		<p>Dear</p>
		
		<p>${message}</p>
		<br/>
		<p>Please setup your account to start ${typename}.</p>
		<br/>
		<a href="${link}" style='padding:7px 23px;font-size:14px;background-color:#00A65A;color:#fff;text-align:center;border:1px solid #00A65A;border-radius:2px;text-decoration:none;display:inline-block;background-image:linear-gradient(#00A65A 40%,#00A65A 100%)'> Setup Password</a>
		<br/>
		<br/>
        <#include "footer.ftl">       
	
	</body>

</html>