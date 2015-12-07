var jsonResult;

// 显示更新\添加结果，callBackFunction回调函数，needClose：0不需要关闭
function optResult(dialogName, args, flag, callBackFunction, needClose) {
	var show = 0;
	if (args == null || args == "undefined") {
		show = 0;
	} else {
		show = args.result;
	}
	var str;
	if (show == 1) {
		if (flag == 0) {
			alert("添加成功！");
		} else if (flag == 1) {
			alert("修改成功！");
		} else if (flag == 2) {
			alert("删除成功！");
		} else if (flag == 3) {
			alert("提交成功！");
		} else if (flag == 4) {
			alert("复制成功！");
		} else if (flag == 5) {
			alert("发送成功！");
		} else if (flag == 9) {
			alert("换班成功！");
		} 
		str = dialogName + ".hide()";
	} else {
		if (show == -1) {
			alert("数据有误，操作失败！");
		}
		str = dialogName + ".show()";
	}
	if (needClose != 0) {
		eval(str);
	}
	if (show == 1) {
		if (callBackFunction != null && callBackFunction != "") {
			jsonResult = args;
			var s = callBackFunction + "()";
			eval(s);
		}
	}
}
function optResult2(dialogName, show, flag, callBackFunction, needClose) {
	var str;
	if (show == 1) {
		if (flag == 0) {
			alert("添加成功！");
		} else if (flag == 1) {
			alert("修改成功！");
		} else if (flag == 2) {
			alert("删除成功！");
		} else if (flag == 3) {
			alert("提交成功！");
		} else if (flag == 4) {
			alert("复制成功！");
		} else if (flag == 5) {
			alert("发送成功！");
		} else if (flag == 9) {
			alert("换班成功！");
		} 
		str = dialogName + ".hide()";
	} else {
		if (show == -1) {
			alert("数据有误，操作失败！");
		}
		str = dialogName + ".show()";
	}
	if (needClose != 0) {
		eval(str);
	}
	if (show == 1) {
		if (callBackFunction != null && callBackFunction != "") {
			jsonResult = args;
			var s = callBackFunction + "()";
			eval(s);
		}
	}
}

// 显示结果
function optResultAlert(args, flag, callBackFunction) {
	var show = 0;
	if (args == null || args == "undefined") {
		show = 0;
	} else {
		show = args.result;
	}
	if (show == 1) {
		if (flag == 0) {
			alert("添加成功！");
		} else if (flag == 1) {
			alert("修改成功！");
		} else if (flag == 2) {
			alert("删除成功！");
		} else if (flag == 3) {
			alert("提交成功！");
		} else if (flag == 4) {
			alert("复制成功！");
		} else if (flag == 5) {
			alert("发送成功！");
		} else if (flag == 6) {
			alert("同步成功！");
		} else if (flag == 8) {
			alert("退班成功！");
		} else if (flag == 9) {
			alert("生成成功！");
		}
	} else {
		if (show == -1) {
			alert("数据有误，操作失败！");
		}
	}
	if (show == 1) {
		if (callBackFunction != null && callBackFunction != "") {
			jsonResult = args;
			var s = callBackFunction + "()";
			eval(s);
		}
	}
}

// 显示结果，并触发单击事件
function optResultClick(args, flag, id) {
	var show = 0;
	if (args == null || args == "undefined") {
		show = 0;
	} else {
		show = args.result;
	}
	if (show == 1) {
		if (flag == 0) {
			alert("添加成功！");
		} else if (flag == 1) {
			alert("修改成功！");
		} else if (flag == 2) {
			alert("删除成功！");
		} else if (flag == 3) {
			alert("提交成功！");
		} else if (flag == 4) {
			alert("复制成功！");
		} else if (flag == 5) {
			alert("发送成功！");
		} else if (flag == 6) {
			alert("同步成功！");
		} else if (flag == 7) {
			alert("分班成功！");
		} else if (flag == 8) {
			alert("退班成功！");
		}
		jQuery("#"+id).click();
	} else {
		if (show == -1) {
			alert("数据有误，操作失败！");
		}
	}
}

// 是否删除
function confirmDel() {
	return (confirm("确认删除么？"));
}

// 确认对话框
function confirmDia(str) {
	return (confirm(str));
}

// 导出Excel
function exportXls(args) {
	if (args.result == 1) {
		$("#searchForm\\:exportXlsBtn").click();
	}
}

// 自动完成说明
function autoInfo() {
	alert("如果是火狐浏览器，在每次输入完中文后，按Ctrl键可进行提示！");
}

function myReload(){
	window.location.reload();
}

//子页面打开页签
function loadTabPage4Child(url,name){
	jQuery("#formHidden\\:currentNodeType",window.parent.parent.document).val("leaf");
	jQuery("#formHidden\\:currentNodeUrl",window.parent.parent.document).val(url);
	jQuery("#formHidden\\:currentNodeName",window.parent.parent.document).val(name);
	window.parent.parent.loadTabPage();
}

//关闭菜单栏
$(document).bind("click", function () {
    if($(".ui-tieredmenu",window.parent.parent.document).css("display")=="block"){
    	parent.parent.window.menuBtnClick();
    }
});