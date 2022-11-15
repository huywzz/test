<?php

namespace App\Http\Controllers;

use App\Models\categories;
use App\Http\Requests\StorecategoriesRequest;
use App\Http\Requests\UpdatecategoriesRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Yajra\Datatables\Datatables;
use Illuminate\Support\Facades\View;

class CategoriesController extends Controller
{
    private $model;
    private $title;
    public function __construct() {
        $this->model=new categories();
        $routeName=Route::currentRouteName();

        $arr=explode('.',$routeName);
        $arr=array_map('ucfirst',$arr);
        $routeName=implode('-',$arr);
        $this->title=$routeName;
        View::share('title',$this->title);
    }
    public function index()
    {
        return view('categories.index');
    }

    public function getdata()
    {
        return Datatables::of($this->model::query())
        ->editColumn('created_at',function ($object){
            return $object->getDate();
         })
        ->addColumn('edit', function ($user) {
            return route('categories.edit', ['categories' => $user->id]);
        })
        ->addColumn('destroy', function ($user) {
            return route('categories.destroy', ['categories' => $user->id]);
        })
        ->make(true);
    }

    public function create()
    {
       return view('categories.create');
    }


    public function store(StorecategoriesRequest $request)
    {
        categories::create($request->validated());
       return redirect()->route('categories.index');
    }


    public function show(categories $categories)
    {
        //
    }

    public function edit(categories $categories)
    {
       return view('categories.edit',[
        'categories'=>$categories,
       ]);
    }


    public function update(UpdatecategoriesRequest $request, categories $categories)
    {

        $obj=$this->model->find($categories->id);

        $obj->name=$request->get('name');
        $obj->save();

        return redirect()->route('categories.index');
    }


    public function destroy(categories $categories)
    {
        $this->model->find($categories->id)->delete();

        $arr            = [];
        $arr['status']  = true;
        $arr['message'] = '';

        return response($arr, 200);
    }
}
