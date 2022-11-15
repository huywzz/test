

<h1>Trang chủ</h1>
<br>


<h1> {{session()->get('level')}} </h1>

    @if (session()->get('level')===0)
        <a href=" {{route('categories.index')}} ">Trang admin</a>
        <a href="{{route('logout')}}">Dang xuat</a>
    @endif
    @if (session()->get('level')==='1')
        <h1>Chao mung</h1>
        <a href=" {{route('logout')}} ">Dang xuat</a>
    @endif
    @if (!session()->has('level') )
        <a href=" {{route('login')}} ">Đăng nhập</a><br>
        <a href="{{route('register')}}">Đăng kí</a><br>
    @endif
