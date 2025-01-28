<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
        <a class="sidebar-brand brand-logo" href="#"><img src="assets/images/logo.svg" alt="logo"/></a>
        <a class="sidebar-brand brand-logo-mini" href="#"><img src="assets/images/logo-mini.svg" alt="logo"/></a>
    </div>
    <ul class="nav">
        <li class="nav-item profile">
            <div class="profile-desc">
                <div class="profile-pic">
                    <div class="count-indicator">
                        <img class="img-xs rounded-circle " src="assets/images/faces/face15.jpg" alt="">
                        <span class="count bg-success"></span>
                    </div>
                    <div class="profile-name">
                        <h5 class="mb-0 font-weight-normal">Henry Klein</h5>
                        <span>Gold Member</span>
                    </div>
                </div>
            </div>
        </li>
        <li class="nav-item nav-category">
            <span class="nav-link">Navigation</span>
        </li>

        <%--Dashboard--%>
        <li class="nav-item menu-items">
            <a class="nav-link" href="pages/forms/basic_elements.html">
        <span class="menu-icon">
          <i class="mdi mdi-playlist-play"></i>
        </span>
                <span class="menu-title">Dashboard</span>
            </a>
        </li>

        <%--Component --%>
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
        <span class="menu-icon">
          <i class="mdi mdi-memory"></i>
        </span>
                <span class="menu-title">Component</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="#">Insertion</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">List</a></li>
                    <li class="nav-item"><a class="nav-link" href="./componentRecommandedServlet">Insert Recommanded</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="./componentRecommandedServlet?mode=list">List Recommanded</a></li>
                    <li class="nav-item"><a class="nav-link" href="./HistoPrixServlet">Insert Prix</a></li>
                    <li class="nav-item"><a class="nav-link" href="./HistoPrixServlet?mode=list">List Historique Prix</a></li>

                </ul>
            </div>
        </li>

        <%--Repair--%>
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#repair" aria-expanded="false" aria-controls="ui-basic">
        <span class="menu-icon">
          <i class="mdi mdi-memory"></i>
        </span>
                <span class="menu-title">Repair</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="repair">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href=".//repaireServlet?mode=insert">Insertion</a></li>
                    <li class="nav-item"><a class="nav-link" href="./repaireServlet">List</a></li>
                    <li class="nav-item"><a class="nav-link" href="./commissionServlet">Commission</a></li>
                </ul>
            </div>
        </li>

        <%--Retour--%>
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#retour" aria-expanded="false" aria-controls="ui-basic">
        <span class="menu-icon">
          <i class="mdi mdi-memory"></i>
        </span>
                <span class="menu-title">Retour</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="retour">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="./retourServlet">Insertion</a></li>
                    <li class="nav-item"><a class="nav-link" href="./retourServlet?mode=L">List</a></li>
                    <li class="nav-item"><a class="nav-link" href="./retourServlet?mode=SCL">List Client</a></li>

                </ul>
            </div>
        </li>

    </ul>
</nav>