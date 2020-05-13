//登录、注册界面表单验证的JS
/*异步验证用户名的输入格式以及是否存在*/
function CheckUsername(){
	/*取到用户名输入框*/
	var nametxt = document.getElementById("username");
	/*获取输入的用户名值*/
	var username = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("usernameError");
	var regex = /^[a-z0-9]{4,12}$/i;
	/*创建异步函数*/
	var xhr = createXmlHttp();
	/*设置监听*/
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				/*为信息提示框赋文本*/
				sp.innerHTML = xhr.responseText;
				/*为信息提示框赋样式*/
				sp.className = "infoError";
			}
		}
	}
	/*打开链接 · 不加时间戳则会引起浏览器的缓存*/
	xhr.open("GET", "${pageContext.request.contextPath}/user_FindByName.action?time="+new Date().getTime()+"&username="+username, true);
	/*发送*/
	xhr.send();
}

function createXmlHttp(){
	var xmlHttp;
	try{//Firefox, Opera8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch(e){
		try {//IE
			xmlHttp = new ActiveXObject("Msxm12.XMLHTTP");
		} catch (e) {}
	}
	return xmlHttp;
}

/*注册时验证账号*/
function validateUsername(){
	/*取到姓名输入框*/
	var nametxt = document.getElementById("username");
	/*获取输入的姓名值*/
	var username = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("usernameError");
	var regex = /^[a-z0-9]{4,12}$/i;
	if(!(regex.test(username))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "用户名由4~12位字母、数字组成!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	CheckUsername();
	/*输入正确，信息提示框无文本、无样式,调用异步查询函数*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*登录时验证账号*/
function validateUser(){
	/*取到姓名输入框*/
	var nametxt = document.getElementById("username");
	/*获取输入的姓名值*/
	var username = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("usernameError");
	var regex = /^[a-z0-9]{4,12}$/i;
	if(!(regex.test(username))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "用户名格式有误!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式,调用异步查询函数*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*注册时验证密码*/
function validatePsw(){
	/*取到密码输入框*/
	var nametxt = document.getElementById("password");
	/*获取输入的密码值*/
	var password = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("passwordError");
	var regex = /^[a-z0-9]{5,12}$/i;
	if(!(regex.test(password))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "密码应由5~12位数字、字母组成！";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	/*为信息提示框赋样式*/
	sp.className = "";
	return true;
}

/*登录时验证密码*/
function CheckPsw(){
	/*取到密码输入框*/
	var nametxt = document.getElementById("password");
	/*获取输入的密码值*/
	var password = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("passwordError");
	var regex = /^[a-z0-9]{5,12}$/i;
	if(!(regex.test(password))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "密码格式有误！";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	/*为信息提示框赋样式*/
	sp.className = "";
	return true;
}

/*确认密码*/
function validateRepsw(){
	/*取到两次密码输入框*/
	var password = document.getElementById("password").value;
	var repsw = document.getElementById("repsw");
	/*获取确认密码值*/
	var repassword = repsw.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("RepswError");
	if(password == ""){
		/*为信息提示框赋文本*/
		sp.innerHTML = "密码不能为空！";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	if(password != repassword){
		/*为信息提示框赋文本*/
		sp.innerHTML = "两次密码不一致！";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*验证邮箱*/
function validateEmail(){
	/*取到姓名输入框*/
	var nametxt = document.getElementById("email");
	/*获取输入的姓名值*/
	var cert = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("emailError");
	var regex = /^\w+@\w+(\.\w+)+$/;
	if(!(regex.test(cert))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "邮箱格式错误!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*验证姓名*/
function validateName(){
	/*取到姓名输入框*/
	var nametxt = document.getElementById("name");
	/*获取输入的姓名值*/
	var name = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("nameError");
	var regex = /[\u4E00-\u9FA5]{2,8}/g;
	if(!(regex.test(name))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "请输入真实姓名!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*验证联系电话*/
function validatePhone(){
	/*取到联系电话输入框*/
	var nametxt = document.getElementById("phone");
	/*获取输入的联系电话值*/
	var phone = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("phoneError");
	var regex = /^\w{11}$/;
	if(!(regex.test(phone))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "请输入11位手机号码!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*验证地址信息*/
function validateAddr(){
	/*取到地址输入框*/
	var nametxt = document.getElementById("address");
	/*获取输入的地址值*/
	var mem = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("addressError");
	var regex = /[\u4E00-\u9FA5a-z0-9]{3,70}/g;
	if(!(regex.test(mem))){
		/*为信息提示框赋文本*/
		sp.innerHTML = "地址信息不够详细!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确，信息提示框无文本、无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*验证码不能为空*/
function validateCode(){
	/*取到姓名输入框*/
	var nametxt = document.getElementById("checkcode");
	/*获取输入的姓名值*/
	var code = nametxt.value;
	/*获取到装错误信息的span框*/
	var sp = document.getElementById("codeError");
	if(code == ""){
		/*为信息提示框赋文本*/
		sp.innerHTML = "请输入验证码!";
		/*为信息提示框赋样式*/
		sp.className = "infoError";
		/*输入错误返回false*/
		return false;
	}
	/*输入正确则无样式*/
	sp.innerHTML = "";
	sp.className = "";
	return true;
}

/*注册界面表单验证*/
function validateForm(){
	/*短路与运算*/
	return validateUsername() && validatePsw() && validateRepsw() && validateEmail() && validateName() && validatePhone() && validateAddr() && validateCode();
}

/*登录界面表单验证*/
function CheckLogin(){
	/*短路与运算*/
	return validateUser() && CheckPsw();
}
