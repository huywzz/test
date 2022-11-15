<?php

namespace App\Http\Controllers;

use App\Models\product;
use App\Http\Requests\StoreproductRequest;
use App\Http\Requests\UpdateproductRequest;
use App\Models\categories;
use App\Models\Manufacture;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\View;
use Illuminate\Support\Facades\Storage;
use Yajra\DataTables\DataTables;

class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    private $model;
    private $title;
    private $arrCategories;
    private $arrManufactures;
    public function __construct()
    {
        $this->model = new product();
        $routeName = Route::currentRouteName();
        $arr = explode('.', $routeName);
        $arr = array_map('ucfirst', $arr);
        $routeName = implode(' - ', $arr);
        $this->title = $routeName;
        $this->arrCategories = categories::all();
        $this->arrManufactures = Manufacture::all();
        View::share('title', $this->title);
        View::share('arrCate', $this->arrCategories);
        View::share('arrManu', $this->arrManufactures);
    }

    public function index()
    {
        return view('products.index');
    }

    public function getdata()
    {
        return DataTables::of($this->model::query())
            ->editColumn('status', function ($object) {
                return $object->getStatus();
            })
            ->editColumn('created_at', function ($object) {
                return $object->getDate();
            })
            ->addColumn('name', function ($user) {
                $arr=[];
                $arr['link']=route('products.show', ['product' => $user]);
                $arr['name']=$user->name;
                return $arr;
            })
            ->addColumn('edit', function ($user) {
                // $arr=[];
                // $arr['link']=route('products.edit', ['product' => $user]);
                // $arr['name']=$user->name;
                return route('products.edit', ['product' => $user]);
            })
            ->addColumn('destroy', function ($user) {
                return route('products.destroy', ['product' => $user->id]);
            })
            ->filterColumn('name', function($query, $keyword) {
                if ($keyword !== 'null') {
                    $query->where('id',$keyword);
                }
            })
            ->filterColumn('status', function($query, $keyword) {
                if ($keyword!== '2') {
                    $query->where('status',$keyword);
                }

            })
            ->make(true);
    }
    public function getdataName(Request $request)
    {
        return $this->model
        ->where('name','like','%'.$request->get('a').'%')
        ->get(
            [
                'id',
                'name'
            ]
        );
    }
    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('products.create');
    }


    public function store(StoreproductRequest $request)
    {
        $nameManu = Manufacture::find($request->get('manufacture_id'))->name;
        $nameCate = categories::find($request->get('categories_id'))->name;

        $path          = Storage::disk('public')->put('avatars', $request->file('product_img'));
        $this->model::query()->create(
            [
                'name' => $request->get('name'),
                'description' => $request->get('description'),
                'specs' => $request->get('specs'),
                'price' => $request->get('price'),
                'status' => $request->get('status'),
                'quantity' => $request->get('quantity'),
                'manufacture_id' => $request->get('manufacture_id'),
                'manufactures_name' => $nameManu,
                'categories_id' => $request->get('categories_id'),
                'category_name' => $nameCate,
                'product_image' => $path,
            ]
        );
        return redirect()->route('products.index');
    }


    public function show(product $product)
    {
        $product->status=$product->getStatus();
        return view('products.productDetail',['product'=>$product]);
    }


    public function edit(product $product)
    {
        return view('products.edit', ['product' => $product]);
    }


    public function update(UpdateproductRequest $request, product $product)
    {
        $obj = $this->model->find($product->id);
        $nameManu = Manufacture::find($obj->manufacture_id)->name;
        $nameCate = categories::find($obj->categories_id)->name;
        // dd($request->file('product_img'));

        $path = $obj->product_image;
        // dd(!$request->hasFile('product_img'));
        if ($request->file('product_img') != null) {
            Storage::disk('public')->delete($path);
            $path          = Storage::disk('public')->put('avatars', $request->file('product_img'));
            // dd($path);
        }
        // dd($path);
        $obj->update([
            'name' => $request->get('name'),
            'description' => $request->get('description'),
            'specs' => $request->get('specs'),
            'price' => $request->get('price'),
            'status' => $request->get('status'),
            'quantity' => $request->get('quantity'),
            'manufacture_id' => $request->get('manufacture_id'),
            'manufactures_name' => $nameManu,
            'categories_id' => $request->get('categories_id'),
            'category_name' => $nameCate,
            'product_image' => $path,
        ]);
        return redirect()->route('products.index');
    }


    public function destroy(product $product)
    {
        $this->model->find($product->id)->delete();

        $arr            = [];
        $arr['status']  = true;
        $arr['message'] = '';

        return response($arr, 200);
    }
}
