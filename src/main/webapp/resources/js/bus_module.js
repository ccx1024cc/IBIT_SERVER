//校车模块主页向后翻页事件
function bus_next_page(element, e) {
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
		url : "../../bus/admin/busNewsList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			bus_data_fill(data);
		}
	});
}

// 校车模块主页向前翻页时间
function bus_prev_page(element, e) {
	if (currentPage.page > 1) {
		currentPage.page -= 1;
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
		url : "../../bus/admin/busNewsList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			bus_data_fill(data);
		}
	});
}

// 填充校车数据
function bus_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#bus_news_list tbody").first();
	tbody.empty();
	$.each(data, function(index, item) {
		var tr = $("<tr></tr>");
		tr.append("<td>" + item.id + "</td>");
		tr.append("<td>" + item.title + "</td>");
		tr.append("<td><a href=\"" + item.url
				+ "\" target=\"_blank\">点击获取详细信息</a></td>");
		tr.append("<td>" + item.pubTime + "</td>");

		tr.appendTo(tbody);
		tr = null;
	});
}

// 获取校车列表
function tempBusListEvent(element, e) {
	currentPage.page = 1;// 当前页面
	// 获取总页数
	$.ajax({
		url : "../../bus/admin/tempBusNum",
		type : "get",
		success : function(data) {
			currentPage.totalPage = Math.ceil(data / 20);
			if (currentPage.totalPage == 0) {
				currentPage.totalPage = 1;
			}
			if (currentPage.page == 1) {
				$("a#prev_page").attr("disabled", "true");
			} else {
				$("a#prev_page").removeAttr("disabled");
			}
			if (currentPage.page == currentPage.totalPage) {
				$("a#next_page").attr("disabled", "true");
			} else {
				$("a#next_page").removeAttr("disabled");
			}
		}
	});
	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 校车管理</li>").append(
			"<li class=\"active\"> 临时班车列表</li>");
	// 数据列表提示
	$("h3#box-header").text("Order By Time").siblings().remove();
	// 翻页链接添加
	var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
	var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
	prev.click(function(e) {
		busInfo_prev_page();
	});
	next.click(function(e) {
		busInfo_next_page();
	});
	$("div#box-footer").empty().append(prev).append(next);
	if (currentPage.page == 1) {
		prev.attr("disabled", "true");
	}
	if (currentPage.totalPage == currentPage.page) {
		next.attr("disabled", "true");
	}
	prev = null;
	next = null;

	// 添加数据table
	$("div#display_body").empty().append(
			"<div class=\"table-responsive\">"
					+ "<table class=\"table no-margin\" id=\"bus_info_list\">"
					+ "<thead></thead>" + "<tbody></tbody>" + "</table>"
					+ "</div>");
	// 添加用户列表表头
	var thead = $("table#bus_info_list thead").first();
	thead.empty();
	thead.append("<th>ID</th>");
	thead.append("<th>StartPoint</th>");
	thead.append("<th>AimPoint</th>");
	thead.append("<th>SeatMeasage</th>");
	thead.append("<th>ModifyTime</th>");
	thead.append("<th>StartTime</th>");
	thead.append("<th>MidStation</th>");
	thead.append("<th>Charger</th>");
	thead.append("<th></th>");
	thead = null;

	// 获取，并展示临时校车列表
	$.ajax({
		url : "../../bus/admin/tempBusList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			bus_info_data_fill(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

// 获取定时校车列表
function regularBusListEvent(element, e) {
	currentPage.page = 1;// 当前页面
	// 获取总页数
	$.ajax({
		url : "../../bus/admin/busNum",
		type : "get",
		success : function(data) {
			currentPage.totalPage = Math.ceil(data / 20);
			if (currentPage.totalPage == 0) {
				currentPage.totalPage = 1;
			}
			if (currentPage.page == 1) {
				$("a#prev_page").attr("disabled", "true");
			} else {
				$("a#prev_page").removeAttr("disabled");
			}
			if (currentPage.page == currentPage.totalPage) {
				$("a#next_page").attr("disabled", "true");
			} else {
				$("a#next_page").removeAttr("disabled");
			}
		}
	});
	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 校车管理</li>").append(
			"<li class=\"active\"> 定时班车列表</li>");
	// 数据列表提示
	$("h3#box-header").text("Order By Time").siblings().remove();
	// 翻页链接添加
	var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
	var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
	prev.click(function(e) {
		regular_busInfo_prev_page();
	});
	next.click(function(e) {
		regular_busInfo_next_page();
	});
	$("div#box-footer").empty().append(prev).append(next);
	if (currentPage.page == 1) {
		prev.attr("disabled", "true");
	}
	if (currentPage.totalPage == currentPage.page) {
		next.attr("disabled", "true");
	}
	prev = null;
	next = null;

	// 添加数据table
	$("div#display_body")
			.empty()
			.append(
					"<div class=\"table-responsive\">"
							+ "<table class=\"table no-margin\" id=\"regular_bus_info_list\">"
							+ "<thead></thead>" + "<tbody></tbody>"
							+ "</table>" + "</div>");
	// 添加用户列表表头
	var thead = $("table#regular_bus_info_list thead").first();
	thead.empty();
	thead.append("<th>ID</th>");
	thead.append("<th>StartPoint</th>");
	thead.append("<th>AimPoint</th>");
	thead.append("<th>SeatMeasage</th>");
	thead.append("<th>ModifyTime</th>");
	thead.append("<th>StartTime</th>");
	thead.append("<th>MidStation</th>");
	thead.append("<th>Charger</th>");
	thead.append("<th></th>");
	thead = null;

	// 获取，并展示临时校车列表
	$.ajax({
		url : "../../bus/admin/busList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			regular_bus_info_data_fill(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

// 添加班车信息
function addBusEvent(element, e) {
	// 数据列表提示
	$("h3#box-header").text("为了数据的完整性，请不要有空项").siblings().remove();

	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 校车管理</li>").append(
			"<li class=\"active\"> 添加校车信息</li>");

	// 显示搜索条件表单
	var form = $(" <form role=\"form\" id=\"bus_form\"></form>");
	form
			// 添加校车类型
			.append(
					"<div class=\"form-group\">" + "<div class=\"col-xs-12\">"
							+ "<label>Type</label>"
							+ "<select class=\"form-control\" name=\"type\">"
							+ "<option value=\"0\">临时班车</option>"
							+ "<option value=\"1\">定时班车</option>"
							+ "</div></div>")
			// 添加出发地点
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>StartPoint</label>"
							+ "<select class=\"form-control\" name=\"startPoint\">"
							+ "<option value=\"良乡\">良乡</option>"
							+ "<option value=\"中关村\">中关村</option>"
							+ "</div></div>")
			// 添加中转站
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>MidStation</label>"
							+ "<select class=\"form-control\" name=\"midStation\">"
							+ "<option value=\"无\">无</option>"
							+ "<option value=\"回龙观\">回龙观</option>"
							+ "</div></div>")
			// 添加终点地点
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>AimPoint</label>"
							+ "<select class=\"form-control\" name=\"aimPoint\">"
							+ "<option value=\"良乡\">良乡</option>"
							+ "<option value=\"中关村\">中关村</option>"
							+ "</div></div>")
			// 添加座位数
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>Seat Number</label>"
							+ "<input type=\"text\" name=\"seatMeasage\" class=\"form-control\" value=\"50\" placeholder=\"Number ...\">"
							+ "</div></div>")
			// 添加发车时间
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-6\">"
							+ "<label>StartTime</label>"
							+ "<input type=\"text\" name=\"startTime_d\" class=\"form-control\" placeholder=\"yyyy-mm-dd\" data-mask></div>"
							+ "<div class=\"col-xs-6\">"
							+ "<label>StartTime</label>"
							+ "<input type=\"text\" name=\"startTime_t\" class=\"form-control\" placeholder=\"hh:mm:ss\" data-mask>"
							+ "</div></div>")
			// 添加日期类型
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>dateType</label>"
							+ "<select class=\"form-control\" name=\"dateType\">"
							+ "<option value=\"0\">工作日</option>"
							+ "<option value=\"1\">周五</option>"
							+ "<option value=\"2\">周末</option>"
							+ "<option value=\"3\">节假日</option>"
							+ "</div></div>")
			// 添加负责人
			.append(
					"<div class=\"form-group\">"
							+ "<div class=\"col-xs-12\">"
							+ "<label>Charger</label>"
							+ "<input type=\"text\"  name=\"charger\" class=\"form-control\" placeholder=\"请填写真实姓名 ...\">"
							+ "</div></div>");
	// 重置
	var reset = $("<a id=\"reset\" class=\"btn btn-sm btn-default btn-flat pull-left\">重置</a>");
	var add = $("<a id=\"add\" class=\"btn btn-sm btn-default btn-flat pull-right\">添加</a>");

	reset.click(function(e) {
		$("form#bus_form")[0].reset();
	});
	add.click(function(e) {
		// 获取参数信息
		var fieldArray = $("form#bus_form").serializeArray();
		var dataObj = {};
		$.each(fieldArray, function(index, item) {
			dataObj[item.name] = item.value;
		});
		dataObj.startTime = dataObj.startTime_d + " " + dataObj.startTime_t;
		delete dataObj.startTime_t;
		delete dataObj.startTime_d;
		$.ajax({
			url : "../../bus/admin/bus",
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

	// 设置日期数据格式
	// $("input[name=\"startTime_d\"]").inputmask("yyyy-mm-dd", {
	// "placeholder" : "yyyy-mm-dd"
	// });
	// $("input[name=\"startTime_t\"]").inputmask("hh:mm:ss", {
	// "placeholder" : "hh:mm:ss"
	//	});
	//$("[data-mask]").inputmask();
}

// 校车信息向后翻页
function busInfo_next_page() {
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
		url : "../../bus/admin/tempBusList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			bus_info_data_fill(data);
		}
	});
}

// 校车信息向前翻页
function busInfo_prev_page() {
	if (currentPage.page > 1) {
		currentPage.page -= 1;
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
		url : "../../bus/admin/tempBusList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			bus_info_data_fill(data);
		}
	});
}

// 填充临时校车信息
function bus_info_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#bus_info_list tbody").first();
	tbody.empty();
	$
			.each(
					data,
					function(index, item) {
						var tr = $("<tr></tr>");
						tr.append("<td>" + item.id + "</td>");
						tr.append("<td>" + item.startPoint + "</td>");
						tr.append("<td>" + item.aimPoint + "</td>");
						tr.append("<td>" + item.seatMeasage + "</td>");
						tr.append("<td>" + item.modifyTime + "</td>");
						tr.append("<td>" + item.startTime + "</td>");
						if (typeof (item.midStation) == "undefined") {
							tr.append("<td>" + "无" + "</td>");
						} else {
							tr.append("<td>" + item.midStation + "</td>");
						}
						tr.append("<td>" + item.charger + "</td>");
						tr
								.append("<td><a class=\"btn btn-sm btn-default btn-flat\">删除</a></td>");

						tr.appendTo(tbody);
						tr = null;

						// 删除事件委托
						tbody.delegate("a", "click", function(e) {
							$.ajax({
								url : "../../bus/admin/bus",
								type : "delete",
								data : {
									busId : $(this).parent().parent().children(
											":first").text()
								},
								success : function() {
									// 刷新列表
									$.ajax({
										url : "../../bus/admin/tempBusList",
										type : "get",
										data : {
											page : currentPage.page
										},
										success : function(data) {
											bus_info_data_fill(data);
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
										}
									});
								},
								error : function() {
									alert("您没有足有的权限");
								}
							});
						});
					});
}

// 定时班车信息向后翻页
function regular_busInfo_next_page() {
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
		url : "../../bus/admin/busList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			regular_bus_info_data_fill(data);
		}
	});
}

// 定时班车信息向前翻页
function regular_busInfo_prev_page() {
	if (currentPage.page > 1) {
		currentPage.page -= 1;
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
		url : "../../bus/admin/busList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			regular_bus_info_data_fill(data);
		}
	});
}

// 定时班车信息填充
function regular_bus_info_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#regular_bus_info_list tbody").first();
	tbody.empty();
	$
			.each(
					data,
					function(index, item) {
						var tr = $("<tr></tr>");
						tr.append("<td>" + item.id + "</td>");
						tr.append("<td>" + item.startPoint + "</td>");
						tr.append("<td>" + item.aimPoint + "</td>");
						tr.append("<td>" + item.seatMeasage + "</td>");
						tr.append("<td>" + item.modifyTime + "</td>");
						tr.append("<td>" + item.startTime + "</td>");
						if (typeof (item.midStation) == "undefined") {
							tr.append("<td>" + "无" + "</td>");
						} else {
							tr.append("<td>" + item.midStation + "</td>");
						}
						tr.append("<td>" + item.charger + "</td>");
						tr
								.append("<td><a class=\"btn btn-sm btn-default btn-flat\">删除</a></td>");

						tr.appendTo(tbody);
						tr = null;

						// 删除事件委托
						tbody.delegate("a", "click", function(e) {
							$.ajax({
								url : "../../bus/admin/bus",
								type : "delete",
								data : {
									busId : $(this).parent().parent().children(
											":first").text()
								},
								success : function() {
									// 刷新列表
									$.ajax({
										url : "../../bus/admin/busList",
										type : "get",
										data : {
											page : currentPage.page
										},
										success : function(data) {
											regular_bus_info_data_fill(data);
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
										}
									});
								},
								error : function() {
									alert("您没有足有的权限");
								}
							});
						});
					});
}