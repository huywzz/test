<div class="topnav shadow-sm">
    <div class="container-fluid">
        <nav class="navbar navbar-light navbar-expand-lg topnav-menu">

            <div class="collapse navbar-collapse active" id="topnav-menu-content">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-dashboards" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="uil-dashboard mr-1"></i>Dashboards <div class="arrow-down"></div>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="topnav-dashboards">
                            <a href="dashboard-analytics.html" class="dropdown-item">Analytics</a>
                            <a href="dashboard-crm.html" class="dropdown-item">CRM</a>
                            <a href="index.html" class="dropdown-item">Ecommerce</a>
                            <a href="dashboard-projects.html" class="dropdown-item">Projects</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-apps" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="uil-apps mr-1"></i>Apps <div class="arrow-down"></div>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-pages" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="uil-copy-alt mr-1"></i>Pages <div class="arrow-down"></div>
                        </a>

                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-components" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="uil-package mr-1"></i>Components <div class="arrow-down"></div>
                        </a>

                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-layouts" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="uil-window mr-1"></i>Layouts <div class="arrow-down"></div>
                        </a>

                       <div class="dropdown-menu" aria-labelledby="topnav-dashboards" id="cart_products">
                        @if (session()->get('cart')!=null)
                        @foreach (session()->get('cart') as $key)
                        <a href="dashboard-analytics.html" class="dropdown-item"> {{ $key['name'] }} -
                            {{ $key['quantity'] }} </a>
                        @endforeach
                        @endif


                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
