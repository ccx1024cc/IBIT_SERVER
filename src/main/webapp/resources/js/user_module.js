//搜索功能
function searchEvent(element, e) {
	currentPage.page = 1;// 当前页面

	// 数据列表提示
	$("h3#box-header").text("筛选条件(不需要的条件可以不填写)").siblings().remove();

	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 用户管理</li>").append(
			"<li class=\"active\"> 搜索</li>");

	// 显示搜索条件表单
	var form = $(" <form role=\"form\" id=\"search_condition\"></form>");
	form
			// 添加查询条件用户ID
			.append(
					"<div class=\"form-group\">"
							+ "<label>User ID</label>"
							+ "<input type=\"text\" name=\"userId\" class=\"form-control\" placeholder=\"User ID ...\">"
							+ "</div>")
			// 添加查询条件用户名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Name</label>"
							+ "<input type=\"text\" name=\"name\"  class=\"form-control\" placeholder=\"Name ...\">"
							+ "</div>")
			// 添加查询条件性别
			.append(
					"<div class=\"form-group\">" + "<label>Gender</label>"
							+ "<select class=\"form-control\" name=\"gender\">"
							+ "<option value=\"2\">未选择</option>"
							+ "<option value=\"0\">男</option>"
							+ "<option value=\"1\">女</option>" + "</select>"
							+ "</div>")
			// 添加查询条件手机号
			.append(
					"<div class=\"form-group\">"
							+ "<label>Phone</label>"
							+ "<input type=\"text\" name=\"phone\" class=\"form-control\" placeholder=\"Phone ...\">"
							+ "</div>")
			// 添加查询条件邮箱
			.append(
					"<div class=\"form-group\">"
							+ "<label>Email</label>"
							+ "<input type=\"email\"  name=\"email\" class=\"form-control\" placeholder=\"Email ...\">"
							+ "</div>")
			// 添加查询条件权限
			.append(
					"<div class=\"form-group\">"
							+ "<label>Authority</label>"
							+ "<select class=\"form-control\" name=\"authority\">"
							+ "<option value=\"not_choose\">未选择</option>"
							+ "<option value=\"admin\">admin</option>"
							+ "</select>" + "</div>");
	// 重置
	var reset = $("<a id=\"reset\" class=\"btn btn-sm btn-default btn-flat pull-left\">重置</a>");
	var search = $("<a id=\"search\" class=\"btn btn-sm btn-default btn-flat pull-right\">搜索</a>");

	reset.click(function(e) {
		$("form#search_condition")[0].reset();
	});
	search
			.click(function(e) {
				// 获取参数信息
				var fieldArray = $("form#search_condition").serializeArray();
				var dataObj = {};
				$.each(fieldArray, function(index, item) {
					if (item.name == "authority") {
						if (item.value != "not_choose")
							dataObj[item.name] = item.value;
					} else if (item.name == "gender") {
						if (item.value != "2")
							dataObj[item.name] = item.value;
					} else if (item.value != "") {
						dataObj[item.name] = item.value;
					}
				});
				currentPage.searchCondition = dataObj;
				currentPage.page = 1;

				// 获取总页数
				$.ajax({
					url : "../../user/admin/searchNum",
					type : "get",
					success : function(data) {
						currentPage.totalPage = Math.ceil(data / 30);
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
				// 添加数据table
				$("div#display_body")
						.empty()
						.append(
								"<div class=\"table-responsive\">"
										+ "<table class=\"table no-margin\" id=\"user_list\">"
										+ "<thead></thead>" + "<tbody></tbody>"
										+ "</table>" + "</div>");
				// 添加用户列表表头
				var thead = $("table#user_list thead").first();
				thead.empty();
				thead.append("<th>User ID</th>");
				thead.append("<th>Name</th>");
				thead.append("<th>Phone</th>");
				thead.append("<th>Email</th>");
				thead.append("<th>Authority</th>");
				thead.append("<th></th>");
				thead = null;

				// 翻页链接添加
				var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
				var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
				prev.click(function(e) {
					search_prev_page(this, e, currentPage.searchCondition);
				});
				next.click(function(e) {
					search_next_page(this, e, currentPage.searchCondition);
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

				// 更新提示信息
				$("h3#box-header").text("搜索结果如下");
				// 搜索并填入数据
				start_search(this, e, currentPage.searchCondition);
			});

	// 添加搜索条件表单
	$("div#display_body").empty().append(form)
	// 将翻页链接删除
	.next().empty()
	// 添加重置和搜索
	.append(reset).append(search);
	form = null;
	reset = null;
	search = null;
}

// 授权更改

function authEvent(element, e) {
	currentPage.searchId = null;
	// 数据列表提示
	$("h3#box-header")
			.text("操作请慎重")
			.parent()
			// 添加搜索工具
			.append(
					"<div class=\"box-tools\">"
							+ "<div class=\"input-group input-group-sm\" style=\"width: 150px;\">"
							+ "<input type=\"text\" name=\"table_search\" class=\"form-control pull-right\" placeholder=\"User ID\">"
							+ "<div class=\"input-group-btn\">"
							+ "<button id=\"searchAuths\" class=\"btn btn-default\"><i class=\"fa fa-search\"></i></button>"
							+ "</div></div></div>");
	$("button#searchAuths").click(function(e) {
		var userId = $(this).parent().prev().val();
		currentPage.searchId = userId;
		// 获取用名称
		$.ajax({
			url : "../../user/admin/username",
			type : "get",
			data : {
				userId : userId
			},
			success : function(data) {
				if (typeof (data) == "undefined") {
					$("h3#box-header").text("用户不存在");
					$("div#addAuthority button").attr("disabled", "true");
				} else {
					$("h3#box-header").text(data);
					$("div#addAuthority button").removeAttr("disabled");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
			}
		});
		// 获取，并展示用户列表
		$.ajax({
			url : "../../user/admin/personalAuths",
			type : "get",
			data : {
				userId : userId
			},
			success : function(data) {
				auth_data_fill(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 用户管理</li>").append(
			"<li class=\"active\"> 授权更改</li>");
	// 添加数据table
	$("div#display_body").empty().append(
			"<div class=\"table-responsive\">"
					+ "<table class=\"table no-margin\" id=\"auth_list\">"
					+ "<thead></thead>" + "<tbody></tbody>" + "</table>"
					+ "</div>");
	// 添加用户列表表头
	var thead = $("table#auth_list thead").first();
	thead.empty();
	thead.append("<th>Authority ID</th>");
	thead.append("<th>User ID</th>");
	thead.append("<th>Authority</th>");
	thead.append("<th>Grand User</th>");
	thead.append("<th>Grand Time</th>");
	thead.append("<th></th>");
	thead = null;

	// 删除翻页标签
	$("div#box-footer")
			.empty()
			// 添加增加权限按钮
			.append(
					"<div id=\"addAuthority\" class=\"input-group input-group-sm\">"
							+ "<select class=\"form-control\">"
							+ "<option value=\"admin\">admin</option>"
							+ "</select>"
							+ "<span class=\"input-group-btn\">"
							+ "<button type=\"button\" class=\"btn btn-flat\" disabled=\"true\">添加</button>"
							+ "</span></div>");
	$("div#addAuthority button").click(function(e) {
		$.ajax({
			url : "../../user/admin/authority",
			type : "put",
			data : {
				userId : currentPage.searchId,
				authority : $(this).parent().prev().val()
			},
			success : function(data) {
				// 刷新列表
				$.ajax({
					url : "../../user/admin/personalAuths",
					type : "get",
					data : {
						userId : currentPage.searchId
					},
					success : function(data) {
						auth_data_fill(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}
		});
	});
};

// 信息更改事件
function updateUserEvent(element, e) {
	currentPage.searchId = null;
	// 清空展示部分
	$("div#display_body").empty().next().empty();
	// 数据列表提示
	$("h3#box-header")
			.text("操作请慎重")
			.parent()
			// 添加搜索工具
			.append(
					"<div class=\"box-tools\">"
							+ "<div class=\"input-group input-group-sm\" style=\"width: 150px;\">"
							+ "<input type=\"text\" name=\"table_search\" class=\"form-control pull-right\" placeholder=\"User ID\">"
							+ "<div class=\"input-group-btn\">"
							+ "<button id=\"searchUserInfo\" class=\"btn btn-default\"><i class=\"fa fa-search\"></i></button>"
							+ "</div></div></div>");
	$("button#searchUserInfo").click(function(e) {
		var userId = $(this).parent().prev().val();
		currentPage.searchId = userId;
		// 获取用名称
		$.ajax({
			url : "../../user/admin/username",
			type : "get",
			data : {
				userId : userId
			},
			success : function(data) {
				if (typeof (data) == "undefined") {
					$("h3#box-header").text("用户不存在");
				} else {
					$("h3#box-header").text(data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
			}
		});
		// 获取，并展示用户信息
		$.ajax({
			url : "../../user/admin/userDetail",
			type : "get",
			data : {
				userId : userId
			},
			success : function(data) {
				if (typeof (data) != "undefined") {
					user_detail_fill(data);
				} else {
					// 清除展示部分
					$("div#display_body").empty()
					// 将翻页链接删除
					.next().empty();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});

}

// 添加用户事件
function addUserEvent(element, e) {
	// 填写字段的合格情况
	currentPage.isValid = {
		email : 1,
		phone : 1
	};

	// 数据列表提示
	$("h3#box-header").text("填写用户信息").siblings().remove();
	// 更新目录
	$("ol#breadcrumb").empty().append(
			"<li><i class=\"fa fa-dashboard\"></i> 用户管理</li>").append(
			"<li class=\"active\"> 添加用户</li>");
	// 显示搜索条件表单
	var form = $(" <form role=\"form\" id=\"user_form\" enctype=\"multipart/form-data\"></form>");
	form
			// 添加用户名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Name</label>"
							+ "<input type=\"text\" name=\"name\"  class=\"form-control\" placeholder=\"Name ...\">"
							+ "</div>")
			// 添加密码
			.append(
					"<div class=\"form-group\">"
							+ "<label>Password</label>"
							+ "<input type=\"password\" name=\"password\"  class=\"form-control\" placeholder=\"Password ...\">"
							+ "</div>")
			// 添加性别
			.append(
					"<div class=\"form-group\">" + "<label>Gender</label>"
							+ "<select class=\"form-control\" name=\"gender\">"
							+ "<option value=\"0\">男</option>"
							+ "<option value=\"1\">女</option>" + "</select>"
							+ "</div>")
			// 添加手机号
			.append(
					"<div class=\"form-group\">"
							+ "<label>Phone</label>"
							+ "<input type=\"text\" name=\"phone\" class=\"form-control\" placeholder=\"Phone ...\">"
							+ "</div>")
			// 添加邮箱
			.append(
					"<div class=\"form-group\">"
							+ "<label>Email</label>"
							+ "<input type=\"email\"  name=\"email\" class=\"form-control\" placeholder=\"Email ...\">"
							+ "</div>")
			// 添加签名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Autograph</label>"
							+ "<input type=\"text\"  name=\"autograph\" class=\"form-control\" placeholder=\"Autograph ...\">"
							+ "</div>")
			// 添加头像
			.append(
					"<div class=\"form-group\">"
							+ "<label>Icon</label>"
							+ "<input type=\"file\"  name=\"icon\" class=\"form-control\" >"
							+ "</div>")
	// 重置
	var reset = $("<a id=\"reset\" class=\"btn btn-sm btn-default btn-flat pull-left\">重置</a>");
	var addUser = $("<a id=\"addUser\" class=\"btn btn-sm btn-default btn-flat pull-right\">添加</a>");

	reset.click(function(e) {
		$("form#user_form")[0].reset();
	});
	addUser.click(function(e) {
		$.ajax({
			url : "../../user/admin/addUser",
			type : "put",
			processData : false,
			contentType : false,
			data : new FormData($("form#user_form")[0]),
			success : function(data) {
				alert("添加成功，用户ID = " + data);
			},
			error : function() {
				alert("添加失败");
			}
		});
	});

	// 添加搜索条件表单
	$("div#display_body").empty().append(form)
	// 将翻页链接删除
	.next().empty()
	// 添加重置和添加
	.append(reset).append(addUser);
	form = null;
	reset = null;
	addUser = null;

	// 数据校验
	$("input[name=\"email\"]").blur(
			function(e) {
				$.ajax({
					url : "../../user/admin/userNumByEmail",
					type : "get",
					data : {
						email : $(this).val()
					},
					success : function(data) {
						if (data == 0) {
							currentPage.isValid.email = 1;
						} else {
							currentPage.isValid.email = 0;
						}
						if ($("input[name=\"email\"]").val() == "root@IBIT") {
							currentPage.isValid.email = 0;
						}

						if (currentPage.isValid.email == 1
								&& currentPage.isValid.phone == 1) {
							$("a#addUser").removeAttr("disabled");
							$("input[name=\"email\"]").prev().text("Email");
						} else {
							$("a#addUser").attr("disabled", "disabled");
							$("input[name=\"email\"]").prev().text(
									"拥有该邮箱的用户已经存在");
						}
					}
				});
			});
	$("input[name=\"phone\"]").blur(
			function(e) {
				$.ajax({
					url : "../../user/admin/userNumByPhone",
					type : "get",
					data : {
						phone : $(this).val()
					},
					success : function(data) {
						if (data == 0) {
							currentPage.isValid.email = 1;
						} else {
							currentPage.isValid.email = 0;
						}
						if (currentPage.isValid.email == 1
								&& currentPage.isValid.phone == 1) {
							$("a#addUser").removeAttr("disabled");
							$("input[name=\"phone\"]").prev().text("Phone");
						} else {
							$("a#addUser").attr("disabled", "disabled");
							$("input[name=\"phone\"]").prev().text(
									"拥有该手机号的用户已经存在");
						}
					}
				});
			});
}

// 用户数据填充函数
function user_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#user_list tbody").first();
	tbody.empty();
	$.each(data, function(index, item) {
		var tr = $("<tr></tr>");
		tr.append("<td>" + item.id + "</td>");
		tr.append("<td>" + item.name + "</td>");
		tr.append("<td>" + item.phone + "</td>");
		tr.append("<td>" + item.email + "</td>");

		var authList = "";
		$.each(item.auths, function(index, auth) {
			authList += auth.name;
		});
		tr.append("<td>" + authList + "</td>");

		tr.appendTo(tbody);
		tr = null;
	});
}

// 搜索函数
function start_search(element, e, condition) {
	condition.page = 1;
	$.ajax({
		url : "../../user/admin/search",
		type : "get",
		data : condition,
		success : function(data) {
			user_data_fill(data);
		}
	});
}

// 搜索结果向前翻页
function search_prev_page(element, e, condition) {
	currentPage.page -= 1;
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
		url : "../../user/admin/search",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			user_data_fill(data);
		}
	});
}

// 搜索结果向后翻页
function search_next_page(element, e, condition) {
	currentPage.page += 1;
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
		url : "../../user/admin/search",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			user_data_fill(data);
		}
	});
}

// 获取用户的所有权限
function getAuthorityList(userId) {
	$.ajax({
		url : "../../user/admin/personalAuths",
		type : "get",
		data : {
			userId : userId
		},
		success : function(data) {
			user_data_fill(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			/*
			 * console.log(XMLHttpRequest.status);
			 * console.log(XMLHttpRequest.readyState); console.log(textStatus);
			 * console.log(XMLHttpRequest.responseText);
			 */
		}
	});
}

// 权限数据填充函数
function auth_data_fill(data) {
	// 显示获取的数据
	var tbody = $("table#auth_list tbody").first();
	tbody.empty();
	$
			.each(
					data,
					function(index, item) {
						var tr = $("<tr></tr>");
						tr.append("<td>" + item.id + "</td>");
						tr.append("<td>" + item.userId + "</td>");
						tr.append("<td>" + item.name + "</td>");
						tr.append("<td>" + item.grandUser + "</td>");
						tr.append("<td>" + item.grandTime + "</td>")
						tr
								.append("<button id=\"removeAuthority\" type=\"button\" class=\"btn btn-block btn-warning\">删除</button>");
						tr.appendTo(tbody);
						tr = null;
					});
	// 添加事件委托
	tbody.delegate("button", "click", function(e) {
		$.ajax({
			url : "../../user/admin/authority",
			type : "delete",
			data : {
				authId : $(this).parent().children(":first").text()
			},
			success : function() {
				// 刷新列表
				$.ajax({
					url : "../../user/admin/personalAuths",
					type : "get",
					data : {
						userId : currentPage.searchId
					},
					success : function(data) {
						auth_data_fill(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			},
			error : function() {
				alert("您没有足有的权限");
			}
		});
	})
}

// 填写用户详细信息
function user_detail_fill(data) {
	// 显示数据表单
	var form = $(" <form role=\"form\" id=\"user_info\"></form>");
	form
			// 添加用户ID
			.append(
					"<div class=\"form-group\">"
							+ "<label>User ID</label>"
							+ "<input type=\"text\" name=\"userId\" class=\"form-control\" placeholder=\"User ID ...\" value=\""
							+ data.id + "\" disabled>" + "</div>")
			// 添加用户名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Name</label>"
							+ "<input type=\"text\" name=\"name\"  class=\"form-control\" placeholder=\"Name ...\" value=\""
							+ data.name + "\">" + "</div>")
			// 添加密码
			.append(
					"<div class=\"form-group\">"
							+ "<label>Password</label>"
							+ "<input type=\"text\" name=\"password\"  class=\"form-control\" placeholder=\"Password ...\" value=\""
							+ data.password + "\">" + "</div>")
			// 添加性别
			.append(
					"<div class=\"form-group\">"
							+ "<label>Gender</label>"
							+ "<select class=\"form-control\" name=\"gender\" id=\"gender\">"
							+ "<option value=\"0\">男</option>"
							+ "<option value=\"1\">女</option>"
							+ "</select></div>")
			// 添加手机号
			.append(
					"<div class=\"form-group\">"
							+ "<label>Phone</label>"
							+ "<input type=\"text\" name=\"phone\" class=\"form-control\" placeholder=\"Phone ...\" value=\""
							+ data.phone + "\">" + "</div>")
			// 添加邮箱
			.append(
					"<div class=\"form-group\">"
							+ "<label>Email</label>"
							+ "<input type=\"text\"  name=\"email\" class=\"form-control\" placeholder=\"Email ...\" value=\""
							+ data.email + "\">" + "</div>")
			// 添加个性签名
			.append(
					"<div class=\"form-group\">"
							+ "<label>Autograph</label>"
							+ "<input type=\"text\"  name=\"autograph\" class=\"form-control\" placeholder=\"Autograph ...\" value=\""
							+ data.autograph + "\">" + "</div>")
			// 添加日期
			.append(
					"<div class=\"form-group\">"
							+ "<label>Registe Date</label>"
							+ "<input type=\"text\"  name=\"date\" class=\"form-control\" placeholder=\"Email ...\" value=\""
							+ data.date + "\" disabled>" + "</div>")

	// 重置
	var reset = $("<a id=\"reset\" class=\"btn btn-sm btn-default btn-flat pull-left\">重置</a>");
	var update = $("<a id=\"updateUser\" class=\"btn btn-sm btn-default btn-flat pull-right\">更新</a>");
	reset.click(function(e) {
		$("form#user_info")[0].reset();
	});
	// 更新用户信息
	update.click(function(e) {
		// 获取参数信息
		var fieldArray = $("form#user_info").serializeArray();
		var dataObj = {};
		$.each(fieldArray, function(index, item) {
			dataObj[item.name] = item.value;
		});
		// 加入userId
		dataObj.userId = $("input[name=\"userId\"]").val();

		$.ajax({
			url : "../../user/admin/userDetail",
			type : "post",
			data : $.param(dataObj),
			success : function() {
				// 刷新数据
				$.ajax({
					url : "../../user/admin/userDetail",
					type : "get",
					data : {
						userId : currentPage.searchId
					},
					success : function(data) {
						user_detail_fill(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}
		});
	});

	// 添加搜索条件表单
	$("div#display_body").empty().append(form)
	// 将翻页链接删除
	.next().empty()
	// 添加重置和搜索
	.append(reset).append(update);
	form = null;
	reset = null;
	update = null;

	// 数据校验
	$("input[name=\"email\"]").blur(function(e) {
		$.ajax({
			url : "../../user/admin/userExistByEmail",
			type : "get",
			data : {
				email : $(this).val(),
				userId : $("input[name=\"userId\"]").val()
			},
			success : function(data) {
				if (data == 0) {
					$("input[name=\"email\"]").prev().text("Email");
				} else {
					$("input[name=\"email\"]").prev().text("拥有该邮箱的用户已经存在");
				}
			}
		});
	});
	$("input[name=\"phone\"]").blur(function(e) {
		$.ajax({
			url : "../../user/admin/userExistByPhone",
			type : "get",
			data : {
				phone : $(this).val(),
				userId : $("input[name=\"userId\"]").val()
			},
			success : function(data) {
				if (data == "0") {
					$("input[name=\"phone\"]").prev().text("Phone");
				} else {
					$("input[name=\"phone\"]").prev().text("拥有该手机号的用户已经存在");
				}
			}
		});
	});
	// 正确显示性别
	if (data.gender == '0') {
		$("select#gender option[value=\"0\"]").attr('selected', 'selected');
	} else {
		$("select#gender option[value=\"1\"]").attr('selected', 'selected');
	}
}

// 用户模块主页用户列表向前翻页事件
function prevPage(element, e) {
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
		url : "../../user/admin/userList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			user_data_fill(data);
		}
	});
}
// 用户模块主页向后翻页事件
function nextPage(element, e) {
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
		url : "../../user/admin/userList",
		type : "get",
		data : {
			page : currentPage.page
		},
		success : function(data) {
			user_data_fill(data);
		}
	});
}
