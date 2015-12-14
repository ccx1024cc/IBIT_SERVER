//声明一些全局变量
var serverbaseUrl = "http://localhost:8080/IBIT";

// 新闻列表时间监听
$("#news_list").on(
		"click",
		"a",
		function(event) {
			var newsType;
			event.stopPropagation();
			// 得到新闻类型
			switch ($(this).text()) {
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

			location.href = serverbaseUrl + "/display/news.html?newsType="
					+ newsType + "&newsTypeName=" + $(this).text();
		});