@if(session()->has('mess'))
    {{ session()->get('mess') }}
@endif

<form action=" {{route('processLogin')}} " method="POST">
    @csrf
    Email
    <input type="text" name="email"><br>
    Mat khau
    <input type="password" name="password" id=""><br>
    <button>Dang nhap</button>
</form>
