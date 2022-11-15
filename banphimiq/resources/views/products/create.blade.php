@extends('layout.master')
@section('content')
    <form action=" {{route('products.store')}} " method="POST" enctype="multipart/form-data">
        @csrf
        <div class="form-group">
            <label for="simpleinput">Tên sản phẩm</label>
            <input type="text" id="name" class="form-control" placeholder="Tên" name="name">
        </div>
        Nhà sản xuất

        <select name="manufacture_id" id="" class="custom-select mb-3">
            @foreach ($arrManu as $value)
                <option value="{{ $value->id }}"> {{$value->name }} </option>
            @endforeach
        </select>

        Loại sản phẩm
        <select name="categories_id" id="" class="custom-select mb-3">
            @foreach ($arrCate as $value)
                <option value=" {{ $value->id }} "> {{$value->name }} </option>
            @endforeach
        </select>
        <div class="form-group">
            <label for="example-fileinput">Ảnh đại diện của sản phẩm</label>
            <input type="file" id="example-fileinput" class="form-control-file" name="product_img">
        </div>
        <div class="form-group">
            <label for="example-textarea">Mô tả</label>
            <textarea class="form-control" id="example-textarea" rows="5" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="example-textarea">Thông số kĩ thuật</label>
            <textarea class="form-control" id="example-textarea" rows="5" name="specs"></textarea>
        </div>

        <div class="input-group mb-3 ">
            <input type="text" id="simpleinput" class="form-control" placeholder="Giá" name="price">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01">VND</label>
            </div>
        </div>
        <input type="hidden" name="status" id="" value="0">
        <div class="form-group">
            <label for="simpleinput">Số lượng</label>
            <input type="text" id="simpleinput" class="form-control" placeholder="Số lượng" name="quantity">
        </div>

        <br>

        <button class="btn btn-success">Thêm sản phẩm</button>
    </form>
@endsection
