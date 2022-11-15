@extends('layout.master')
@section('content')
    <form action=" {{route('categories.update',['categories'=>$categories->id])}} " method="POST">

        @csrf
        <div class="form-group">
            <label for="simpleinput">Tên danh mục</label>
            <input type="text" id="name" class="form-control">
        </div>
        <button>Sua</button>
    </form>
@endsection
