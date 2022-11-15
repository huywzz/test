@extends('layoutClient.masterClient')
@section('content')
    <div class="row">
        <div class="col-12">
            <div class="page-title-box">
                <div class="page-title-right">
                    <ol class="breadcrumb m-0">
                        <li class="breadcrumb-item"><a href="javascript: void(0);">Hyper</a></li>
                        <li class="breadcrumb-item"><a href="javascript: void(0);">eCommerce</a></li>
                        <li class="breadcrumb-item active">Shopping Cart</li>
                    </ol>
                </div>
                <h4 class="page-title">Shopping Cart</h4>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="table-responsive">
                                <table class="table table-borderless table-centered mb-0">
                                    <thead class="thead-light">
                                        <tr>
                                            <th>Product</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th style="width: 50px;"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="cart">
                                        @if (session()->get('cart') != null)
                                            @foreach (session()->get('cart') as $key)
                                                <tr id="{{ $key['id'] }}">
                                                    <td>
                                                        <img src=" {{ Storage::url($key['product_image']) }} "
                                                            alt="contact-img" title="contact-img" class="rounded mr-3"
                                                            height="64">
                                                        <p class="m-0 d-inline-block align-middle font-16">
                                                            <a href="apps-ecommerce-products-details.html"
                                                                class="text-body"> {{ $key['name'] }} </a>
                                                            <br>

                                                        </p>
                                                    </td>
                                                    <td>
                                                        {{ $key['price'] }}
                                                    </td>
                                                    <td>
                                                        <a type="button" class="decrease"
                                                            value="{{ $key['quantity'] }}" data-id="{{ $key['id'] }}">-</a>
                                                        <input type="number" min="1" value="{{ $key['quantity'] }}"
                                                            class="form-control" placeholder="Qty" style="width: 90px;"
                                                            id="quantity_{{ $key['id'] }}">
                                                        <a type="button" class="increase"
                                                            data-id="{{ $key['id'] }}">+</a>
                                                    </td>
                                                    <td id="price_{{ $key['id'] }}">
                                                        {{ $key['quantity'] * $key['price'] }}
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="delete action-icon"
                                                            data-id=" {{ $key['id'] }} ">Xoa</a>
                                                    </td>
                                                </tr>
                                            @endforeach
                                        @endif




                                    </tbody>
                                </table>
                            </div> <!-- end table-responsive-->

                            <!-- Add note input-->
                            <div class="mt-3">
                                <label for="example-textarea">Add a Note:</label>
                                <textarea class="form-control" id="example-textarea" rows="3" placeholder="Write some note.."></textarea>
                            </div>

                            <!-- action buttons-->
                            <div class="row mt-4">
                                <div class="col-sm-6">
                                    <a href="apps-ecommerce-products.html"
                                        class="btn text-muted d-none d-sm-inline-block btn-link font-weight-semibold">
                                        <i class="mdi mdi-arrow-left"></i> Continue Shopping </a>
                                </div> <!-- end col -->
                                <div class="col-sm-6">
                                    <div class="text-sm-right">
                                        <a href="apps-ecommerce-checkout.html" class="btn btn-danger">
                                            <i class="mdi mdi-cart-plus mr-1"></i> Checkout </a>
                                    </div>
                                </div> <!-- end col -->
                            </div> <!-- end row-->
                        </div>
                        <!-- end col -->

                        <div class="col-lg-4">
                            <div class="border p-3 mt-4 mt-lg-0 rounded">
                                <h4 class="header-title mb-3">Order Summary</h4>

                                <div class="table-responsive">
                                    <table class="table mb-0">
                                        <tbody>
                                            <tr>
                                                <td>Grand Total :</td>
                                                <td>$1571.19</td>
                                            </tr>
                                            <tr>
                                                <td>Discount : </td>
                                                <td>-$157.11</td>
                                            </tr>
                                            <tr>
                                                <td>Shipping Charge :</td>
                                                <td>$25</td>
                                            </tr>
                                            <tr>
                                                <td>Estimated Tax : </td>
                                                <td>$19.22</td>
                                            </tr>
                                            <tr>
                                                <th>Total :</th>
                                                <th id="total"> {{ $total }} </th>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- end table-responsive -->
                            </div>

                            <div class="alert alert-warning mt-3" role="alert">
                                Use coupon code <strong>HYPBM</strong> and get 10% discount !
                            </div>

                            <div class="input-group mt-3">
                                <input type="text" class="form-control form-control-light" placeholder="Coupon code"
                                    aria-label="Recipient's username">
                                <div class="input-group-append">
                                    <button class="btn btn-light" type="button">Apply</button>
                                </div>
                            </div>

                        </div> <!-- end col -->

                    </div> <!-- end row -->
                </div> <!-- end card-body-->
            </div> <!-- end card-->
        </div> <!-- end col -->
    </div>
@endsection
@push('js')
    <script>
        $(function() {
            $(document).ready(function() {

                $('.delete').click(function() {
                    // let form = $(this).parents('form');
                    let id = $(this).data('id');
                    // alert(id);
                    $.ajax({
                        type: 'GET',
                        url: '{{ route('cart.delete') }}',
                        data: {
                            id: id,
                        },
                        success: function(data) {
                            console.log("ss");
                            $('#' + data['id']).remove();
                            $('#total').text(data['total']);

                        },
                        error: function() {
                            console.log("error");
                        }
                    });

                });

                $('.increase').click(function() {

                    let id = $(this).data('id');

                    $.ajax({
                        type: 'GET',
                        url: '{{ route('cart.increase') }}',
                        data: {
                            id: id,
                        },
                        success: function(data) {
                            console.log("ss");
                            $('#quantity_' + data['id']).attr('value', data[
                                'quantity']);
                            $('#total').text(data['total']);
                            $('#price_' + data['id']).text(data['totalDetail']);
                        },
                        error: function() {
                            console.log("error");
                        }
                    });

                });
                var a = 1;
                var input = document.querySelector('#quantity_' + a);
                console.log(input);
                //
                $('.decrease').click(function() {
                    var id = $(this).data('id');

                    var quantity = $('#quantity_' + id).val();

                    console.log(quantity);
                    // console.log(id);
                    if (quantity != 1) {
                        $.ajax({
                            type: 'GET',
                            url: '{{ route('cart.decrease') }}',
                            data: {
                                id: id,
                            },
                            success: function(data) {
                                console.log("ss");
                                $('#quantity_' + data['id']).attr('value', data[
                                    'quantity']);
                                $('#total').text(data['total']);
                                $('#price_' + data['id']).text(data['totalDetail']);
                            },
                            error: function() {
                                console.log("error");
                            }
                        });
                    } else {
                        $.ajax({
                            type: 'GET',
                            url: '{{ route('cart.delete') }}',
                            data: {
                                id: id,
                            },
                            success: function(data) {
                                console.log("ss");
                                $('#' + data['id']).remove();
                                $('#total').text(data['total']);

                            },
                            error: function() {
                                console.log("error");
                            }
                        });

                    }


                });
            });

        });
    </script>
@endpush
