<#include "header.ftl"/>
<#include "nav-bar.ftl"/>
<#include "side-bar.ftl"/>
<#include "footer.ftl"/>
<#include "right-bar.ftl"/>
<!DOCTYPE html>

<html>

<!-- 头部 -->
<@header/>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <@navbar/>
  <!-- Left side column. contains the logo and sidebar -->
  <@sidebar/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        最近新闻
        <small>今日<strong>${newsNumToday}</strong>条新闻</small>
      </h1>
      <ol class="breadcrumb" id="current_index">
        <li><a href="#"><i class="fa fa-dashboard"></i> 新闻</a></li>
        <li><a href="#"> ${theme}</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- News table -->
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <!-- box-header -->
            <div class="box-header">
              <h3 class="box-title">
              	<span>${theme}</span>
              	<small>
              		(当前处于第
              		<strong>1</strong>
              		页,共
              		<strong>${pageNum}</strong>
              		页)
              	</small>
              </h3>

              <div class="box-tools" id="search">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <input id="searchKeyWord" name="table_search" class="form-control pull-right" type="text" placeholder="search">

                  <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">提交<i class="fa fa-search"></i></button>
                  </div>
                </div>
              </div>
            </div>
            <!-- end box-header -->

            <!-- box-body -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover" id="news_table">
                <tbody>
	                <tr>
	                  <th>编号</th>
	                  <th>标题</th>
	                  <th>日期</th>
	               	</tr>
	               	<#list newsList as news>
	               		<tr>
	               			<td>${news.id}</td>
	               			<td><a href="${news.url}" target="_blank">${news.title}</a></td>
	               			<td>${news.pubTime?string('yyyy.MM.dd HH:mm:ss')}</td>
	               		</tr>
	               	</#list>
                </tbody>
               </table>
            </div>
            <!-- end box-body -->

            <!-- body-footer -->
            <div class="box-footer clearfix" id="news_index">
              <ul class="pagination pagination-sm no-margin pull-right">
                <li><a href="#" title="第一页">«</a></li>
                <#list 1..maxPage as page>
                	<li><a href="#">${page}</a></li>
                </#list>
                <li><a href="#" title="最后一页">»</a></li>
              </ul>
            </div>
            <!-- end body-footer -->
          </div>
        </div>
      </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- main footer -->
  <@footer/>

  <!-- Control Sidebar -->
  <@rightbar/>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.1.4 -->
<script src="../resources/js/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="../resources/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="../resources/js/app.min.js"></script>
<!-- customed js -->
<script src="../resources/js/index.js"></script>
<!-- customed js -->
<script src="../resources/js/news.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
