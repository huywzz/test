<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Manufacture extends Model
{
    protected $fillable=[
        'name',
        'address',
        'phone',
        'email',
        'productType'
    ];
    use HasFactory;
    public function getDate()
    {
       return $this->created_at->format('Y-m-d H:i:s' );
    }
    public function getProductType()
    {
       if($this->productType===1){
        return 'Bàn phím';
       }
       if($this->productType===2){
        return 'Loa';
       }
       if($this->productType===3){
        return 'Case';
       }
    }

}
