@extends('layout.master')
@section('content')
    <form action=" {{route('products.update',['product'=>$product->id])}} " method="POST" enctype="multipart/form-data">
        @csrf

        <div class="form-group">
            <label for="simpleinput">Tên sản phẩm</label>
            <input type="text" id="name" class="form-control" placeholder="Tên" name="name" value=" {{$product->name}} ">
        </div>
        <label for="simpleinput">Nhà sản xuất</label>
        <select name="manufacture_id" id="" class="custom-select mb-3">
            @foreach ($arrManu as $value)
                <option value="{{ $value->id }}"> {{$value->name }} </option>
            @endforeach
        </select>

        <label for="simpleinput">Loại  sản phẩm</label>
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
            <textarea class="form-control" id="example-textarea" rows="5" name="description">{{$product->description}}</textarea>
        </div>
        <div class="form-group">
            <label for="example-textarea">Thông số kĩ thuật</label>
            <textarea class="form-control" id="example-textarea" rows="5" name="specs">{{$product->specs}}</textarea>
        </div>
        <label for="example-textarea">Giá</label>
        <div class="input-group mb-3 ">

            <input type="text" id="simpleinput" class="form-control" placeholder="Giá" name="price"  value=" {{$product->price}} ">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01">VND</label>
            </div>
        </div>

        <label for="simpleinput">Trạng thái</label>
        <select name="status" id="" class="custom-select mb-3">
                <option value="0"> Được phép bán </option>
                <option value="1"> Không được bán </option>
        </select>
        {{-- <input type="hidden" name="status" id="" value="0"> --}}
        <div class="form-group">
            <label for="simpleinput">Số lượng</label>
            <input type="text" id="simpleinput" class="form-control" placeholder="Số lượng" name="quantity"  value=" {{$product->quantity}} ">
        </div>

        <br>

        <button class="btn btn-success">Sửa</button>
    </form>
@endsection
