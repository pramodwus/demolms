<html>
<head>
</head>
	<body>
	    <#include "header.ftl">
		<p>Dear ${name},</p>
		
		<p>We heard you need a password reset. Click the button below and you'll be redirected to a secure site from which you can set a new password.  </p>
	    <br/>
		<a href="${link}" style='padding:7px 23px;font-size:14px;background-color:#00A65A;color:#fff;text-align:center;border:1px solid #00A65A;border-radius:2px;text-decoration:none;display:inline-block;background-image:linear-gradient(#00A65A 40%,#00A65A 100%)'>RESET PASSWORD </a>
		<br/>
		<br/>
        <#include "footer.ftl">       
	
	</body>

</html>