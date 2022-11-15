<form action="{{route('processRegister')}} " method="POST">
    @csrf
    Ten
    <input type="text" name="name"><br>
    Giới tính
    <br>
    Nam
    <input type="radio" name="gender" value="0" checked><br>
    Nữ
    <input type="radio" name="gender" value="1"><br>
    SDt
    <input type="text" name="phone"><br>
    Email
    <input type="text" name="email"><br>
    Mat khau
    <input type="password" name="password" id=""><br>
    <button>Dang ki</button>
</form>
