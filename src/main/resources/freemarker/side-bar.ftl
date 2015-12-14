<#macro sidebar>
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">我们的服务</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>通知新闻</span> <i class="fa fa-angle-left pull-right"></i></a>
          <ul class="treeview-menu" id="news_list">
            <li><a href="#">新闻快讯</a></li>
            <li><a href="#">教务处通知</a></li>
            <li><a href="#">校方通知</a></li>
            <li><a href="#">招聘就业</a></li>
            <li><a href="#">本科招生</a></li>
            <li><a href="#">研究生招生</a></li>
            <li><a href="#">学工事务</a></li>
            <li><a href="#">讲座预告</a></li>
            <li><a href="#">教育教学</a></li>
            <li><a href="#">学术研究</a></li>
            <li><a href="#">网络通告</a></li>
            <li><a href="#">行政办公</a></li>
            <li><a href="#">人事公告</a></li>
            <li><a href="#">外事交流</a></li>
            <li><a href="#">生活琐事</a></li>
            <li><a href="#">班车信息</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
</#macro>