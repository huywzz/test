@extends('layoutClient.masterClient')
@section('content')
    <div class="row">
        <div class="col-12">
            <div class="page-title-box">
                <div class="page-title-right">
                    <ol class="breadcrumb m-0">
                        <li class="breadcrumb-item"><a href="javascript: void(0);">Hyper</a></li>
                        <li class="breadcrumb-item"><a href="javascript: void(0);">eCommerce</a></li>
                        <li class="breadcrumb-item active">Product Details</li>
                    </ol>
                </div>
                {{-- <h4 class="page-title">{{ $title }} - {{ $product->name }}</h4> --}}
            </div>
        </div>
    </div>
    <!-- end page title -->

    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-5">
                            <!-- Product image -->
                            <a href="javascript: void(0);" class="text-center d-block mb-4">
                                <img src=" {{ Storage::url($product->product_image) }} " class="img-fluid"
                                    style="max-width: 280px;" alt="Product-img">
                            </a>

                            <div class="d-lg-flex d-none justify-content-center">
                                <a href="javascript: void(0);">
                                    <img src="assets/images/products/product-1.jpg" class="img-fluid img-thumbnail p-2"
                                        style="max-width: 75px;" alt="Product-img">
                                </a>
                                <a href="javascript: void(0);" class="ml-2">
                                    <img src="assets/images/products/product-6.jpg" class="img-fluid img-thumbnail p-2"
                                        style="max-width: 75px;" alt="Product-img">
                                </a>
                                <a href="javascript: void(0);" class="ml-2">
                                    <img src="assets/images/products/product-3.jpg" class="img-fluid img-thumbnail p-2"
                                        style="max-width: 75px;" alt="Product-img">
                                </a>
                            </div>
                        </div> <!-- end col -->
                        <div class="col-lg-7">
                            <form class="pl-lg-4" action=" {{route('cart.add')}} " method="POST" enctype="multipart/form-data">
                                @csrf
                                <!-- Product title -->
                                <h3 class="mt-0">{{ $product->name }} <a href="javascript: void(0);" class="text-muted"><i
                                            class="mdi mdi-square-edit-outline ml-2"></i></a> </h3>
                                <input type="hidden" name="product_id" value=" {{ $product->id }} " id="id">
                                <input type="hidden" name="product_name" value=" {{ $product->name }} " id="name">
                                <input type="hidden" name="product_price" value=" {{ $product->price }} " id="price">
                                <input type="hidden" name="product_image" value=" {{ $product->product_image }} " id="image">

                                <p class="font-16">
                                    <span class="text-warning mdi mdi-star"></span>
                                    <span class="text-warning mdi mdi-star"></span>
                                    <span class="text-warning mdi mdi-star"></span>
                                    <span class="text-warning mdi mdi-star"></span>
                                    <span class="text-warning mdi mdi-star"></span>
                                </p>

                                <!-- Product stock -->
                                <div class="mt-3">
                                    <h4><span class="badge badge-success-lighten">{{ $product->status }}</span></h4>
                                </div>

                                <!-- Product description -->
                                <div class="mt-4">
                                    <h6 class="font-14">Giá:</h6>
                                    <h3> {{ $product->price }} đ</h3>
                                </div>

                                <!-- Quantity -->
                                <div class="mt-4">
                                    <h6 class="font-14">Số lượng</h6>
                                    <div class="d-flex">
                                        <input type="number" min="1" value="1" class="form-control"
                                            placeholder="Qty" style="width: 90px;" name="quantity" id="quantity">
                                        <button type="button" class="btn btn-danger ml-2 add-to-cart" name="add-to-cart"
                                            data-id=" {{ $product->id }} "><i class="mdi mdi-cart mr-1"></i> Thêm vào giỏ
                                            hàng</button>
                                    </div>
                                </div>

                                <!-- Product description -->
                                <div class="mt-4">
                                    <h6 class="font-14">Mô tả:</h6>
                                    <p> {{ $product->description }} </p>
                                </div>
                                <div class="mt-4">
                                    <h6 class="font-14">Thông số:</h6>
                                    <p> {{ $product->specs }} </p>
                                </div>

                                <!-- Product information -->


                            </form>
                        </div> <!-- end col -->
                    </div> <!-- end row-->
                </div> <!-- end card-body-->
            </div> <!-- end card-->
        </div> <!-- end col-->
    </div>
@endsection
@push('js')
    <script>
        $(function() {
            $(document).ready(function() {
                $('.add-to-cart').click(function () {
                let form = $(this).parents('form');
                   $.ajax({
                    type:'POST',
                    url: '{{route('cart.add')}}' ,
                    data:form.serialize(),
                    success: function (data) {
                        console.log("ss");
                        $('#cart_products').empty();
                        $('#cart_products').html(data);
                        // alert(data->quantity);
                    },
                    error: function() {
                        console.log("error");
                    }
                   });
                });
            });

        });
    </script>
@endpush
