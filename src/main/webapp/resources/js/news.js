//查询事件
$("#search").on(
		"click",
		"button",
		function(event) {
			var newsType;
			event.stopPropagation();
			// 得到新闻类型
			switch ($("h3.box-title>span").text()) {
			case "新闻快讯":
				newsType = 0;
				break;
			case "教务处通知":
				newsType = 1;
				break;
			case "校方通知":
				newsType = 2;
				break;
			case "招聘就业":
				newsType = 3;
				break;
			case "本科招生":
				newsType = 4;
				break;
			case "研究生招生":
				newsType = 5;
				break;
			case "学工事务":
				newsType = 6;
				break;
			case "讲座预告":
				newsType = 7;
				break;
			case "教育教学":
				newsType = 8;
				break;
			case "学术研究":
				newsType = 9;
				break;
			case "网络通告":
				newsType = 10;
				break;
			case "行政办公":
				newsType = 11;
				break;
			case "人事公告":
				newsType = 12;
				break;
			case "外事交流":
				newsType = 13;
				break;
			case "生活琐事":
				newsType = 14;
				break;
			case "班车信息":
				newsType = 15;
				break;

			}
			var keyword = $("#search input").val();
			// 更新内容
			$.ajax({
				url : serverbaseUrl + "/news/news/newsList/" + newsType,
				data : {
					page : 1,
					keyword : keyword
				},
				type : "GET",
				dataType : "json",
				success : function(newsList) {
					var temp = "<tr><th>编号</th><th>标题</th><th>日期</th></tr>";
					$.each(newsList, function(index, val) {
						temp += "<tr><td>" + val.id + "</td><td><a href=\""
								+ val.url + "\" target=\"_blank\">" + val.title
								+ "</a></td><td>" + val.pubTime + "</td></tr>";
					});
					$("#news_table>tbody").empty().append(temp);
					temp = null;
				}
			});
			// 更新页码
			$.ajax({
				url : serverbaseUrl + "/news/news/num/" + newsType,
				data : {
					keyword : keyword
				},
				type : "GET",
				success : function(num) {
					// 总页数
					var totalPages = parseInt(num % 30 == 0 ? num / 30
							: num / 30 + 1);
					totalPages = totalPages < 1 ? 1 : totalPages;
					$("h3.box-title").find("strong").each(
							function(index, element) {
								// 当前页数
								if (index == 0)
									$(element).text(1);
								// 总页数
								else if (index == 1)
									$(element).text(totalPages);
							});
					// 更新页号索引
					temp = " <li><a href=\"#\" title=\"第一页\">«</a></li>";
					var startPageIndex = 1;
					var endPageIndex = totalPages > 3 ? 3 : totalPages;
					for (var i = startPageIndex; i <= endPageIndex; i++) {
						temp += "<li><a href=\"#\">" + i + "</a></li>"
					}
					temp += "<li><a href=\"#\">»</a></li>"
					var ul = $("div#news_index ul");
					ul.empty().append(temp);
				}
			});
		});

// 翻页事件
$("#news_index").on(
		"click",
		"a",
		function(event) {
			var newsType;
			event.stopPropagation();
			// 得到新闻类型
			switch ($("h3.box-title>span").text()) {
			case "新闻快讯":
				newsType = 0;
				break;
			case "教务处通知":
				newsType = 1;
				break;
			case "校方通知":
				newsType = 2;
				break;
			case "招聘就业":
				newsType = 3;
				break;
			case "本科招生":
				newsType = 4;
				break;
			case "研究生招生":
				newsType = 5;
				break;
			case "学工事务":
				newsType = 6;
				break;
			case "讲座预告":
				newsType = 7;
				break;
			case "教育教学":
				newsType = 8;
				break;
			case "学术研究":
				newsType = 9;
				break;
			case "网络通告":
				newsType = 10;
				break;
			case "行政办公":
				newsType = 11;
				break;
			case "人事公告":
				newsType = 12;
				break;
			case "外事交流":
				newsType = 13;
				break;
			case "生活琐事":
				newsType = 14;
				break;
			case "班车信息":
				newsType = 15;
				break;

			}
			var keyword = $("#search input").val();
			var requestPage;
			var maxPage = $("h3.box-title strong:nth-of-type(2)");
			switch ($(this).text()) {
			case "«":
				requestPage = 1;
				break;
			case "»":
				requestPage = parseInt(maxPage.text());
				break;
			default:
				requestPage = parseInt($(this).text());
			}

			// 获取新闻列表
			$.ajax({
				url : serverbaseUrl + "/news/news/newsList/" + newsType,
				data : {
					page : requestPage,
					keyword : keyword
				},
				type : "GET",
				dataType : "json",
				success : function(newsList) {
					// 当前页号显示更新
					$("h3.box-title strong:nth-of-type(1)").text(requestPage);
					// 显示获得的数据
					var temp = "<tr><th>编号</th><th>标题</th><th>日期</th></tr>";
					$.each(newsList, function(index, val) {
						temp += "<tr><td>" + val.id + "</td><td><a href=\""
								+ val.url + "\" target=\"_blank\">" + val.title
								+ "</a></td><td>" + val.pubTime + "</td></tr>";
					});
					$("#news_table>tbody").empty().append(temp);
					// 更新页号
					temp = " <li><a href=\"#\" title=\"第一页\">«</a></li>";
					var startPageIndex = requestPage - 1;
					var endPageIndex = requestPage + 1;
					if (startPageIndex == 0)
						endPageIndex += 1;
					if (endPageIndex > maxPage.text())
						startPageIndex -= 1;
					startPageIndex = startPageIndex < 1 ? 1 : startPageIndex;
					endPageIndex = endPageIndex > maxPage.text() ? maxPage
							.text() : endPageIndex;
					for (var i = startPageIndex; i <= endPageIndex; i++) {
						temp += "<li><a href=\"#\">" + i + "</a></li>"
					}
					temp += "<li><a href=\"#\">»</a></li>"
					var ul = $("div#news_index ul");
					ul.empty().append(temp);
				}
			});
		});