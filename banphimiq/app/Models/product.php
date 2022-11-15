<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class product extends Model
{
    protected $fillable=[
        'name',
        'description',
        'specs',
        'price',
        'status',
        'quantity',
        'manufacture_id',
        'manufactures_name',
        'categories_id',
        'category_name',
        'product_image',
    ];
    use HasFactory;
    public function getStatus()
    {
        return ($this->status===0)?'Còn hàng':'Hết hàng';
    }
    public function getDate()
    {
       return $this->created_at->format('Y-m-d H:i:s' );
    }
}
