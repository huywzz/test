<?php

namespace App\Http\Controllers;

use App\Models\Manufacture;
use App\Http\Requests\StoreManufactureRequest;
use App\Http\Requests\UpdateManufactureRequest;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\View;
use Yajra\Datatables\Datatables;
class ManufactureController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    private $model;
    private $title;
    private $arrProductType;
    public function __construct() {
        $this->model=new Manufacture();
        $routeName=Route::currentRouteName();

        $arr=explode('.',$routeName);
        $arr=array_map('ucfirst',$arr);
        $routeName=implode('-',$arr);
        $this->title=$routeName;
        $this->arrProductType=['1'=>'Ban phim','2'=>'Loa','3'=>'Case'];
        View::share('title',$this->title);
        View::share('arrProductType',$this->arrProductType);
    }
    public function index()
    {
       return view('manufactures.index');
    }
    public function getdata()
    {
        return Datatables::of($this->model::query())
        ->editColumn('created_at',function ($object){
            return $object->getDate();
         })
         ->editColumn('productType',function ($object){
            return $object->getProductType();
         })
        ->addColumn('edit', function ($user) {
            return route('manufactures.edit', ['manufacture' => $user->id]);
        })
        ->addColumn('destroy', function ($user) {
            return route('manufactures.destroy', ['manufacture' => $user->id]);
        })
        ->make(true);
    }

    public function create()
    {
        return view('manufactures.create');
    }

    public function store(StoreManufactureRequest $request)
    {
       Manufacture::create($request->validated());
       return redirect()->route('manufactures.index');
    }


    public function show(Manufacture $manufacture)
    {
        //
    }


    public function edit(Manufacture $manufacture)
    {
        return view('manufactures.edit',[
            'manufacture' => $manufacture,
        ]);
    }


    public function update(UpdateManufactureRequest $request, Manufacture $manufacture)
    {

        $obj=$this->model->find($manufacture->id);
        $obj->name=$request->get('name');
        $obj->address=$request->get('address');
        $obj->phone=$request->get('phone');
        $obj->email=$request->get('email');
        $obj->productType=$request->get('productType');
        $obj->save();
        return redirect()->route('manufactures.index');
    }


    public function destroy(Manufacture $manufacture)
    {
        $this->model->find($manufacture->id)->delete();

        $arr            = [];
        $arr['status']  = true;
        $arr['message'] = '';

        return response($arr, 200);
    }
}
