//反馈模块主页向前翻页事件
function feedback_index_prev_page(element, e) {
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
		url : "../../feedback/admin/latestFeedback",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_index_data_fill(data);
		}
	});
}

// 反馈模块主页向后翻页事件
function feedback_index_next_page(element, e) {
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
		url : "../../feedback/admin/latestFeedback",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_index_data_fill(data);
		}
	});
}

// 主页数据填充函数
function feedback_index_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#feedback_list tbody").first();
	tbody.empty();
	$.each(data, function(index, item) {
		var tr = $("<tr></tr>");
		tr.append("<td>" + item.id + "</td>");
		if (item.platForm == "0") {
			tr.append("<td>Android</td>");
		} else if (item.platForm == "1") {
			tr.append("<td>IOS</td>");
		} else {
			tr.append("<td>PC</td>");
		}
		tr.append("<td>" + item.content + "</td>");
		if (item.status == "0") {
			tr.append("<td><span class=\"badge bg-red\">待处理</span></td>");
		} else if (item.status == "1") {
			tr.append("<td><span class=\"badge bg-green\">已解决</span></td>");
		} else {
			tr.append("<td><span class=\"badge bg-yellow\">设计如此</span></td>");
		}
		tr.append("<td>" + item.pubTime + "</td>");
		if (typeof (item.fixedTime) == "undefined") {
			tr.append("<td></td>");
		} else {
			tr.append("<td>" + item.fixedTime + "</td>");
		}
		if (typeof (item.fixedPerson) == "undefined") {
			tr.append("<td></td>");
		} else {
			tr.append("<td>" + item.fixedPerson + "</td>");
		}
		tr.appendTo(tbody);
		tr = null;
	});
}

// 未关闭的反馈列表相关功能
function closingFeedbackEvent(element, e) {
	currentPage.page = 1;// 当前页面
	// 数据列表提示
	$("h3#box-header").text("尚未解决的反馈列表").siblings().remove();

	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 反馈管理</li>").append(
			"<li class=\"active\"> 待解决的反馈列表</li>");

	// 获取总页数
	$.ajax({
		url : "../../feedback/admin/openedFeedbackNum",
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

	// 翻页链接添加
	var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
	var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
	prev.click(function(e) {
		feedback_closingFeedback_prev_page(this, e);
	});
	next.click(function(e) {
		feedback_closingFeedback_next_page(this, e);
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
					+ "<table class=\"table no-margin\" id=\"feedback_list\">"
					+ "<thead></thead>" + "<tbody></tbody>" + "</table>"
					+ "</div>");
	// 添加用户列表表头
	var thead = $("table#feedback_list thead").first();
	thead.empty();
	thead.append("<th>ID</th>");
	thead.append("<th>Platform</th>");
	thead.append("<th>Content</th>");
	thead.append("<th>PubTime</th>");
	thead.append("<th>Operation</th>");
	thead = null;
	// 获取，并展示反馈列表
	$.ajax({
		url : "../../feedback/admin/openedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closingFeedback_data_fill(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

// 待解决的反馈列表向前翻页函数
function feedback_closingFeedback_prev_page(element, e) {
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
		url : "../../feedback/admin/openedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closingFeedback_data_fill(data);
		}
	});
}
// 待解决的反馈列表向后翻页函数
function feedback_closingFeedback_next_page(element, e) {
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
		url : "../../feedback/admin/openedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closingFeedback_data_fill(data);
		}
	});
}

// 待解决的反馈列表数据填充函数
function feedback_closingFeedback_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#feedback_list tbody").first();
	tbody.empty();
	$
			.each(
					data,
					function(index, item) {
						var tr = $("<tr></tr>");
						tr.append("<td>" + item.id + "</td>");
						if (item.platForm == "0") {
							tr.append("<td>Android</td>");
						} else if (item.platForm == "1") {
							tr.append("<td>IOS</td>");
						} else {
							tr.append("<td>PC</td>");
						}
						tr.append("<td>" + item.content + "</td>");
						tr.append("<td>" + item.pubTime + "</td>");
						tr
								.append("<td><a class=\"btn btn-sm btn-default btn-success btn-flat\" data-closeType=\"1\">已解决</a>"
										+ "<a class=\"btn btn-sm btn-default btn-warning btn-flat\" data-closeType=\"2\">设计如此</a></td>");
						tr.appendTo(tbody);
						tr = null;
					});
	// 以“已解决”的方式关闭feedback事件
	tbody.delegate("a[data-closeType=\"1\"]", "click", function(e) {
		$.ajax({
			url : "../../feedback/admin/ClosingFeedback",
			type : "post",
			data : {
				feedbackId : $(this).parent().parent().children(":first")
						.text(),
				status : $(this).attr("data-closeType")
			},
			success : function() {
				// 刷新列表
				$.ajax({
					url : "../../feedback/admin/openedFeedbackList",
					type : "get",
					data : {
						page : currentPage.page
					},
					success : function(data) {
						feedback_closingFeedback_data_fill(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			},
			error : function() {
				alert("您没有足有的权限");
			}
		});
	});
	// 以“设计如此”的方式关闭feedback事件
	tbody.delegate("a[data-closeType=\"2\"]", "click", function(e) {
		$.ajax({
			url : "../../feedback/admin/ClosingFeedback",
			type : "post",
			data : {
				feedbackId : $(this).parent().parent().children(":first")
						.text(),
				status : $(this).attr("data-closeType")
			},
			success : function() {
				// 刷新列表
				$.ajax({
					url : "../../feedback/admin/openedFeedbackList",
					type : "get",
					data : {
						page : currentPage.page
					},
					success : function(data) {
						feedback_closingFeedback_data_fill(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			},
			error : function() {
				alert("您没有足有的权限");
			}
		});
	});
}

// 已经关闭的反馈列表
function closedFeedbackEvent(element, e) {
	currentPage.page = 1;// 当前页面
	// 数据列表提示
	$("h3#box-header").text("已经关闭的反馈列表").siblings().remove();

	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 反馈管理</li>").append(
			"<li class=\"active\"> 已经关闭的反馈列表</li>");

	// 获取总页数
	$.ajax({
		url : "../../feedback/admin/closedFeedbackNum",
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

	// 翻页链接添加
	var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
	var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
	prev.click(function(e) {
		feedback_closedFeedback_prev_page(element, e);
	});
	next.click(function(e) {
		feedback_closedFeedback_next_page(element, e);
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
					+ "<table class=\"table no-margin\" id=\"feedback_list\">"
					+ "<thead></thead>" + "<tbody></tbody>" + "</table>"
					+ "</div>");
	// 添加用户列表表头
	var thead = $("table#feedback_list thead").first();
	thead.empty();
	thead.append("<th>ID</th>");
	thead.append("<th>Platform</th>");
	thead.append("<th>Content</th>");
	thead.append("<th>Status</th>");
	thead.append("<th>PubTime</th>");
	thead.append("<th>FixedTime</th>");
	thead.append("<th>FixedPerson</th>");
	thead = null;
	// 获取，并展示反馈列表
	$.ajax({
		url : "../../feedback/admin/closedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closedFeedback_data_fill(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

// 已经关闭的反馈列表向前翻页事件
function feedback_closedFeedback_prev_page(element, e) {
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
		url : "../../feedback/admin/closedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closedFeedback_data_fill(data);
		}
	});
}

// 已经关闭的反馈列表向后翻页事件
function feedback_closedFeedback_next_page(element, e) {
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
		url : "../../feedback/admin/closedFeedbackList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			feedback_closedFeedback_data_fill(data);
		}
	});
}

//已经关闭的反馈列表数据填充函数
function feedback_closedFeedback_data_fill(data){
	// 显示获取的数据
	var tbody = $("table#feedback_list tbody").first();
	tbody.empty();
	$.each(data, function(index, item) {
		var tr = $("<tr></tr>");
		tr.append("<td>" + item.id + "</td>");
		if (item.platForm == "0") {
			tr.append("<td>Android</td>");
		} else if (item.platForm == "1") {
			tr.append("<td>IOS</td>");
		} else {
			tr.append("<td>PC</td>");
		}
		tr.append("<td>" + item.content + "</td>");
		if (item.status == "0") {
			tr.append("<td><span class=\"badge bg-red\">待处理</span></td>");
		} else if (item.status == "1") {
			tr.append("<td><span class=\"badge bg-green\">已解决</span></td>");
		} else {
			tr.append("<td><span class=\"badge bg-yellow\">设计如此</span></td>");
		}
		tr.append("<td>" + item.pubTime + "</td>");
		if (typeof (item.fixedTime) == "undefined") {
			tr.append("<td></td>");
		} else {
			tr.append("<td>" + item.fixedTime + "</td>");
		}
		if (typeof (item.fixedPerson) == "undefined") {
			tr.append("<td></td>");
		} else {
			tr.append("<td>" + item.fixedPerson + "</td>");
		}
		tr.appendTo(tbody);
		tr = null;
	});
}
