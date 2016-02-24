//版本模块主页向前翻页事件
function version_prev_page(element, e) {
	var prev = $("a#prev_page").first();
	var next = $("a#next_page").first();
	if (currentPage.page > 1) {
		currentPage.page -= 1;
	}
	if (currentPage.page == 1) {
		prev.attr("disabled", "true");
	} else {
		prev.removeAttr("disabled");
	}
	if (currentPage.page == currentPage.totalPage) {
		next.attr("disabled", "true");
	} else {
		next.removeAttr("disabled");
	}
	$.ajax({
		url : "../../version/admin/versionList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			version_data_fill(data);
		}
	});
}

// 版本模块主页向后翻页事件
function version_next_page(element, e) {
	if (currentPage.page < currentPage.totalPage) {
		currentPage.page += 1;
	}
	var prev = $("a#prev_page").first();
	var next = $("a#next_page").first();
	if (currentPage.page == 1) {
		prev.attr("disabled", "true");
	} else {
		prev.removeAttr("disabled");
	}
	if (currentPage.page == currentPage.totalPage) {
		next.attr("disabled", "true");
	} else {
		next.removeAttr("disabled");
	}
	$.ajax({
		url : "../../version/admin/versionList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			version_data_fill(data);
		}
	});
}

// 版本模块主页填充数据
function version_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#version_list tbody").first();
	tbody.empty();
	$.each(data, function(index, item) {
		var tr = $("<tr></tr>");
		tr.append("<td>" + item.id + "</td>");
		tr.append("<td>" + item.number + "</td>");
		tr.append("<td>" + item.pubTime + "</td>");
		tr.append("<td>" + item.publisher + "</td>");
		tr.append("<td>" + item.description + "</td>");
		if(item.isNew == 1){
			tr.append("<td><span class=\"badge bg-red\">YES</span></td>");
		}
		else{
			tr.append("<td><span class=\"badge bg-yellow\">NO</span></td>");
		}
		tr.appendTo(tbody);
		tr = null;
	});
}

// 发行新版本
function addVersionEvent(element, e) {
	// 数据列表提示
	$("h3#box-header").text("发行新版本").siblings().remove();

	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 版本管理</li>").append(
			"<li class=\"active\"> 发行新版本</li>");
	// 显示搜索条件表单
	var form = $(" <form role=\"form\" id=\"version_form\"></form>");
	form
			// 添加版本号
			.append(
					"<div class=\"form-group\">"
							+ "<label>Version Number</label>"
							+ "<input type=\"text\" name=\"number\" class=\"form-control\" placeholder=\"Number ...\">"
							+ "</div>")
			// 添加发布者姓名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Publisher</label>"
							+ "<input type=\"text\" name=\"publisher\"  class=\"form-control\" placeholder=\"请填写真实姓名 ...\">"
							+ "</div>")
			// 添加版本描述
			.append(
					"<div class=\"form-group\">"
							+ "<label>Description</label>"
							+ "<textarea name=\"description\" class=\"form-control\" rows=\"5\" placeholder=\"版本描述信息 ...\"></textarea>"
							+"</div>");
	// 重置
	var reset = $("<a id=\"reset\" class=\"btn btn-sm btn-default btn-flat pull-left\">重置</a>");
	var add = $("<a id=\"add\" class=\"btn btn-sm btn-default btn-flat pull-right\">添加</a>");
	reset.click(function(e) {
		$("form#version_form")[0].reset();
	});
	add.click(function(e) {
		// 获取参数信息
		var fieldArray = $("form#version_form").serializeArray();
		var dataObj = {};
		$.each(fieldArray, function(index, item) {
			dataObj[item.name] = item.value;
		});
		$.ajax({
			url : "../../version/admin/version",
			type : "put",
			contentType : "application/json",
			data : JSON.stringify(dataObj),
			success : function(e) {
				alert("添加成功");
			},
			error : function() {
				alert("添加失败");
			}
		});
	});
	// 添加提交表单
	$("div#display_body").empty().append(form)
	// 将翻页链接删除
	.next().empty()
	// 添加重置和提交
	.append(reset).append(add);
	form = null;
	reset = null;
	add = null;
}