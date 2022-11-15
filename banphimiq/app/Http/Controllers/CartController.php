<?php

namespace App\Http\Controllers;

use App\Models\product;
use Illuminate\Http\Request;

class CartController extends Controller
{
    public function totalCart()
    {
        $total=0;
        if (session()->get('cart')!=null) {
            foreach (session()->get('cart') as $key) {
                $total=$key['price']*$key['quantity'] +$total;
            }
         }
         return $total;
    }
    public function add_Cart(Request $request)
    {
        // session()->forget('cart');
        $id=$request->get('product_id');
         $product=product::find($id);
         $cart=session()->get('cart');
         if(isset($cart[$id])){
            $cart[$id]['quantity']=$cart[$id]['quantity']+1;

         }else{
            $cart[$id]=[
                'id'=>$id,
                'name'=> $product->name,
                'product_image'=>$product->product_image,
                'quantity'=>1,
                'price'=>$product->price,
            ];

         }
         session()->put('cart',$cart);
        //  echo "<pre>";
        //  print_r(session()->get('cart'));
       return view('products.cart');
    }
    public function showCart()
    {
        $total=$this->totalCart();

        return view('products.cartDetail',['total'=>$total]);
    }
    public function deleteCart(Request $request)
    {
        $id=$request->get('id');
        $total=0;
        if (session()->get('cart')!=null) {
           $cart=session()->get('cart');
            unset($cart[$id]);
            session()->put('cart',$cart);
        }
        $total=$this->totalCart();
        $arr=[];
        $arr['id']=$id;
        $arr['total']=$total;
        return $arr;
    }
    public function increaseQuantityCart(Request $request)
    {
        $id=$request->get('id');
        $total=0;
        if (session()->get('cart')!=null) {
           $cart=session()->get('cart');
           $cart[$id]['quantity']=  $cart[$id]['quantity']+1;
           session()->put('cart',$cart);
        }
        $total=$this->totalCart();
        $arr=[];
        $arr['id']=$id;
        $arr['quantity']=$cart[$id]['quantity'];
        $arr['totalDetail']=$cart[$id]['quantity']*$cart[$id]['price'];
        $arr['total']=$total;
        return $arr;
    }
    public function decreaseQuantityCart(Request $request)
    {
        $id=$request->get('id');
        $total=0;
        if (session()->get('cart')!=null) {
           $cart=session()->get('cart');
           $cart[$id]['quantity']=  $cart[$id]['quantity']-1;
           session()->put('cart',$cart);
        }
        $total=$this->totalCart();
        $arr=[];
        $arr['id']=$id;
        $arr['quantity']=$cart[$id]['quantity'];
        $arr['totalDetail']=$cart[$id]['quantity']*$cart[$id]['price'];
        $arr['total']=$total;
        return $arr;
    }

}
