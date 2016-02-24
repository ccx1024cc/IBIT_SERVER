//当前数据页面临时数据
var currentPage = {};

// 用户模块
$("ul#sidebar-menu a#user_module")
		.click(
				function(e) {
					currentPage.page = 1;// 当前页面
					currentPage.module = "user";// 当前模块
					// 获取总页数
					$.ajax({
						url : "../../user/admin/userNum",
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

					// 激活父元素菜单
					$(this).parent().siblings().each(function(index, ele) {
						$(ele).removeAttr("class");
					});
					$(this).parent().attr("class", "active");

					// 更新目录
					$("ol#breadcrumb").empty().append(
							"<li class=\"active\">用户管理</li>")
					// 更新标题
					.prev().empty().append(
							"用户管理 <small>点击功能菜单中选项可进行更改权限等额外操作</small>");

					// 添加功能列表
					$("ul#function_list")
							.empty()
							.append(
									"<li><a id=\"user_search\"><i class=\"fa fa-inbox\"></i>搜索 </a></li>")
							.append(
									"<li><a id=\"user_auth_change\"><i class=\"fa fa-envelope-o\"></i> 授权更改</a></li>")
							.append(
									"<li><a id=\"user_info_change\"><i class=\"fa fa-file-text-o\"></i>基本信息更改</a></li>")
							.append(
									"<li><a id=\"user_add_user\"><i class=\"fa fa-filter\"></i> 添加用户 </a></li>");

					// 为每一个功能注册事件
					// 搜索功能
					$("a#user_search").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						searchEvent(this, e);
					});
					// 授权更改
					$("a#user_auth_change").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						authEvent(this, e);
					});
					// 用户数据更改
					$("a#user_info_change").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						updateUserEvent(this, e);
					});
					// 添加用户
					$("a#user_add_user").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						addUserEvent(this, e);
					});

					// 数据列表提示
					$("h3#box-header").text("Order By Time").siblings()
							.remove();

					// 翻页链接添加
					var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
					var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
					prev.click(function(e) {
						prevPage(this, e);
					});
					next.click(function(e) {
						nextPage(this, e);
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
											+ "<table class=\"table no-margin\" id=\"user_list\">"
											+ "<thead></thead>"
											+ "<tbody></tbody>" + "</table>"
											+ "</div>");
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

					// 获取，并展示用户列表
					$.ajax({
						url : "../../user/admin/userList",
						type : "get",
						data : {
							page : currentPage.page
						},
						success : function(data) {
							user_data_fill(data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							/*
							 * console.log(XMLHttpRequest.status);
							 * console.log(XMLHttpRequest.readyState);
							 * console.log(textStatus);
							 * console.log(XMLHttpRequest.responseText);
							 */
						}
					});
				});

// 校车模块
$("ul#sidebar-menu a#bus_module")
		.click(
				function(e) {
					currentPage.page = 1;// 当前页面
					currentPage.module = "bus";// 当前模块

					// 获取总页数
					$.ajax({
						url : "../../bus/admin/busNewsNum",
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

					// 激活父元素菜单
					$(this).parent().siblings().each(function(index, ele) {
						$(ele).removeAttr("class");
					});
					$(this).parent().attr("class", "active");

					// 更新目录
					$("ol#breadcrumb").empty().append(
							"<li class=\"active\">校车管理</li>")
					// 更新标题
					.prev().empty().append(
							"校车管理 <small>点击功能菜单中选项可进行更改权限等额外操作</small>");

					// 添加功能列表
					$("ul#function_list")
							.empty()
							.append(
									"<li><a id=\"bus_temp_bus_list\"><i class=\"fa fa-inbox\"></i>临时班车列表 </a></li>")
							.append(
									"<li><a id=\"bus_bus_list\"><i class=\"fa fa-inbox\"></i>定时班车列表 </a></li>")
							.append(
									"<li><a id=\"bus_add_bus\"><i class=\"fa fa-envelope-o\"></i> 添加校车信息</a></li>");
					// 为每一个功能注册事件
					// 临时班车列表功能
					$("a#bus_temp_bus_list").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						tempBusListEvent(this, e);
					});
					// 定时班车列表功能
					$("a#bus_bus_list").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						regularBusListEvent(this, e)
					});
					// 添加班车功能
					$("a#bus_add_bus").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						addBusEvent(this, e)
					});

					// 数据列表提示
					$("h3#box-header").text("请根据下列班车链接对班车信息进行维护").siblings()
							.remove();

					// 翻页链接添加
					var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
					var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
					prev.click(function(e) {
						bus_prev_page(this, e);
					});
					next.click(function(e) {
						bus_next_page(this, e);
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
											+ "<table class=\"table no-margin\" id=\"bus_news_list\">"
											+ "<thead></thead>"
											+ "<tbody></tbody>" + "</table>"
											+ "</div>");
					// 添加用户列表表头
					var thead = $("table#bus_news_list thead").first();
					thead.empty();
					thead.append("<th>Bus News ID</th>");
					thead.append("<th>Bus News Title</th>");
					thead.append("<th>Bus News Url</th>");
					thead.append("<th>Bus News Pubtime</th>");
					thead = null;

					// 获取，并展示校车新闻列表
					$.ajax({
						url : "../../bus/admin/busNewsList",
						type : "get",
						data : {
							page : currentPage.page
						},
						success : function(data) {
							bus_data_fill(data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
						}
					});
				});

// 版本控制模块
$("ul#sidebar-menu a#version_module")
		.click(
				function(e) {
					currentPage.page = 1;// 当前页面
					currentPage.module = "version";// 当前模块

					// 获取总页数
					$.ajax({
						url : "../../version/admin/versionNum",
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

					// 激活父元素菜单
					$(this).parent().siblings().each(function(index, ele) {
						$(ele).removeAttr("class");
					});
					$(this).parent().attr("class", "active");

					// 更新目录
					$("ol#breadcrumb").empty().append(
							"<li class=\"active\">版本管理</li>")
					// 更新标题
					.prev().empty().append(
							"版本管理 <small>点击功能菜单中选项可进行发行版本等额外操作</small>");

					// 添加功能列表
					$("ul#function_list")
							.empty()
							.append(
									"<li><a id=\"version_addVersion\"><i class=\"fa fa-inbox\"></i>发行版本 </a></li>");
					// 为每一个功能注册事件
					// 发行版本功能
					$("a#version_addVersion").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						addVersionEvent(this, e);
					});

					// 数据列表提示
					$("h3#box-header").text("请根据下列班车链接对班车信息进行维护").siblings()
							.remove();

					// 翻页链接添加
					var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
					var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
					prev.click(function(e) {
						version_prev_page(this, e);
					});
					next.click(function(e) {
						version_next_page(this, e);
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
											+ "<table class=\"table no-margin\" id=\"version_list\">"
											+ "<thead></thead>"
											+ "<tbody></tbody>" + "</table>"
											+ "</div>");
					// 添加用户列表表头
					var thead = $("table#version_list thead").first();
					thead.empty();
					thead.append("<th>ID</th>");
					thead.append("<th>Version Number</th>");
					thead.append("<th>PubTime</th>");
					thead.append("<th>Publisher</th>");
					thead.append("<th>Description</th>");
					thead.append("<th>Current Version</th>")
					thead = null;
					// 获取，并展示版本列表
					$.ajax({
						url : "../../version/admin/versionList",
						type : "get",
						data : {
							page : currentPage.page
						},
						success : function(data) {
							version_data_fill(data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
						}
					});
				});
// 反馈管理模块
$("ul#sidebar-menu a#feedback_module")
		.click(
				function(e) {
					currentPage.page = 1;// 当前页面
					currentPage.module = "feedback";// 当前模块

					// 获取总页数
					$.ajax({
						url : "../../feedback/admin/feedbackTotalNum",
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

					// 激活父元素菜单
					$(this).parent().siblings().each(function(index, ele) {
						$(ele).removeAttr("class");
					});
					$(this).parent().attr("class", "active");

					// 更新目录
					$("ol#breadcrumb").empty().append(
							"<li class=\"active\">反馈管理</li>")
					// 更新标题
					.prev().empty().append(
							"反馈管理 <small>点击功能菜单中选项可进行关闭反馈等额外操作</small>");

					// 添加功能列表
					$("ul#function_list")
							.empty()
							.append(
									"<li><a id=\"feedback_openFeedbackList\"><i class=\"fa fa-inbox\"></i> 未关闭的反馈列表</a></li>")
							.append(
									"<li><a id=\"feedback_closedFeedbackList\"><i class=\"fa fa-inbox\"></i> 已经关闭的反馈列表</a></li>");

					// 为每一个功能注册事件
					// 尚未解决的反馈列表
					$("a#feedback_openFeedbackList").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						closingFeedbackEvent(this, e);
					});
					// 已经解决的反馈列表
					$("a#feedback_closedFeedbackList").click(function(e) {
						// 激活父元素菜单
						$(this).parent().siblings().each(function(index, ele) {
							$(ele).removeAttr("class");
						});
						$(this).parent().attr("class", "active");
						closedFeedbackEvent(this, e);
					});
					
					// 数据列表提示
					$("h3#box-header").text("最新的反馈").siblings()
							.remove();

					// 翻页链接添加
					var prev = $("<a id=\"prev_page\" class=\"btn btn-sm btn-default btn-flat pull-left\">上一页</a>");
					var next = $("<a id=\"next_page\" class=\"btn btn-sm btn-default btn-flat pull-right\">下一页</a>");
					prev.click(function(e) {
						feedback_index_prev_page(this, e);
					});
					next.click(function(e) {
						feedback_index_next_page(this, e);
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
											+ "<table class=\"table no-margin\" id=\"feedback_list\">"
											+ "<thead></thead>"
											+ "<tbody></tbody>" + "</table>"
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
						url : "../../feedback/admin/latestFeedback",
						type : "get",
						data : {
							page : currentPage.page
						},
						success : function(data) {
							feedback_index_data_fill(data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
						}
					});
				});
// 登出
$("a#logOut").click(function(e) {
	$.ajax({
		url : "../../user/admin/login",
		type : "delete",
		success : function() {
			location.href = "../../resources/pages/login.html";
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("sign out failed");
		}
	});
});
