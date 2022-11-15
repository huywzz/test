@extends('layout.master')
@section('content')
    <form action=" {{route('categories.store')}} " method="POST">
        @csrf

        <div class="form-group">
            <label for="simpleinput">Tên danh mục</label>
            <input type="text" id="name" class="form-control">
        </div>
        <br>
        <button class="btn btn-success">Them</button>
    </form>
@endsection
