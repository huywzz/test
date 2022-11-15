<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\CartController;
use App\Http\Controllers\CategoriesController;
use App\Http\Controllers\ManufactureController;
use App\Http\Controllers\ProductController;
use App\Models\product;
use Illuminate\Support\Facades\Route;




Route::get('/', function () {
    $title = 'Homepage';
    return view('root.index')->with(['title' => $title]);
})->name('root');
Route::get('/login', [AuthController::class, 'login'])->name('login');
Route::post('/login', [AuthController::class, 'processLogin'])->name('processLogin');
Route::get('/register', [AuthController::class, 'register'])->name('register');
Route::post('/register', [AuthController::class, 'processRegister'])->name('processRegister');


Route::get('/products/show/{product}',[ProductController::class,'show'])->name('products.show');
Route::post('/addcart',[CartController::class,'add_Cart'])->name('cart.add');
Route::get('/showcart',[CartController::class,'showCart'])->name('cart.show');
Route::get('/deletecart',[CartController::class,'deleteCart'])->name('cart.delete');
Route::get('/increaseQuantityCart',[CartController::class,'increaseQuantityCart'])->name('cart.increase');
Route::get('/decreaseQuantityCart',[CartController::class,'decreaseQuantityCart'])->name('cart.decrease');

Route::group([
    'middleware' => 'checkLogin'
], function () {

    Route::group(
        [
            'middleware' => 'checkAdmin'
        ],
        function () {
            //products
            Route::get('/products',[ProductController::class,'index'])->name('products.index');
            Route::get('/products/create', [ProductController::class, 'create'])->name('products.create');
            Route::post('/products/store',[ProductController::class,'store'])->name('products.store');
            Route::get('/products/edit/{product}',[ProductController::class,'edit'])->name('products.edit');
            Route::post('/products/update/{product}',[ProductController::class,'update'])->name('products.update');
            Route::delete('/products/destroy/{product}',[ProductController::class,'destroy'])->name('products.destroy');


            //categories
            Route::get('/categories', [CategoriesController::class, 'index'])->name('categories.index');
            Route::get('/categories/create', [CategoriesController::class, 'create'])->name('categories.create');
            Route::post('/categories/store', [CategoriesController::class, 'store'])->name('categories.store');
            Route::get('/categories/edit/{categories}', [CategoriesController::class, 'edit'])->name('categories.edit');
            Route::post('/categories/update/{categories}', [CategoriesController::class, 'update'])->name('categories.update');
            Route::delete('/categories/destroy/{categories}', [CategoriesController::class, 'destroy'])->name('categories.destroy');


            //manufactures
            Route::get('manufactures', [ManufactureController::class, 'index'])->name('manufactures.index');
            Route::get('/manufactures/create', [ManufactureController::class, 'create'])->name('manufactures.create');
            Route::post('/manufactures/store', [ManufactureController::class, 'store'])->name('manufactures.store');
            Route::get('/manufactures/edit/{manufacture}', [ManufactureController::class, 'edit'])->name('manufactures.edit')    ;
            Route::post('/manufactures/update/{manufacture}', [ManufactureController::class, 'update'])->name('manufactures.update');
            Route::delete('/manufactures/destroy/{manufacture}', [ManufactureController::class, 'destroy'])->name('manufactures.destroy');


            Route::get('/getdata', [CategoriesController::class, 'getdata'])->name('categories.getdata');
            Route::get('/getdataManu', [ManufactureController::class, 'getdata'])->name('manufactures.getdata');
            Route::get('/getdataProduct',[ProductController::class,'getdata'])->name('products.getdata');
            Route::get('/getProductName',[ProductController::class,'getdataName'])->name('products.getdata.name');
        }
    );
});
Route::get('/logout', [AuthController::class, 'logout'])->name('logout');
