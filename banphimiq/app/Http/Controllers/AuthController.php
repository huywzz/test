<?php

namespace App\Http\Controllers;

use App\Http\Requests\StoreUserRequest;
use App\Models\User;
use Illuminate\Http\Request;

class AuthController extends Controller
{
    public function login()
    {
        return view('auth.login');
    }
    public function processLogin(Request $request)
    {
        try {
           $user= User::query()
            ->where('email',$request->get('email'))
            ->where('password',$request->get('password'))
            ->firstOrFail();

            session()->put('id',$user->id);
            session()->put('email',$user->email);
            session()->put('password',$user->password);
            session()->put('level',$user->level);

            return redirect()->route('root');
        } catch (\Throwable $th) {
            //throw $th;
            return redirect()->route('login')->with(['mess'=>'sai tk mk']);
        }
    }
    public function logout()
    {
       session()->flush();
       return  redirect()->route('root');
    }
    public function register()
    {
        return view('auth.register');
    }
    public function processRegister(StoreUserRequest $request)
    {
        $level=1;
      User::create([
        'name'=>$request->get('name'),
        'phone'=>$request->get('phone'),
        'gender'=>$request->get('gender'),
        'email'=>$request->get('email'),
        'password'=>$request->get('password'),
        'level'=>$level,
      ]);
    }
}
