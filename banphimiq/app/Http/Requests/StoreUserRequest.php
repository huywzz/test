<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;
class StoreUserRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, mixed>
     */
    public function rules()
    {
        return [
            'name'=>[
                'required',
                'string',
                'max:50',
                'min:2'
            ],
            'phone' =>[
                'required',
                'string',
            ],
            'gender'=>[
                Rule::in(['0','1']),
            ],

            'email'=>[
                'required',
                'email',
                'string',
                'max:200',
                'min:2'
            ],
            'password'=>[
                'required',
                'string',
                'max:200',
                'min:2'
            ],
            // 'address'=>[
            //     'required',
            //     'string',
            //     'max:200',
            //     'min:2'
            // ],
            // 'avatar'=>'string',
        ];
    }
}
