#include <iostream>

using namespace std;

void mang(int a[], int n, int vitri, int gt){
  cout<<"Nhap so phan tu cua mang : ";
  cin>>n;
  for(int i=0; i<n; i++){
    cout<<"Nhap gia tri a["<<i<<"] : ";
    cin>>a[i];
  }
  cout<<"Nhap vi tri can chen : ";
  cin>>vitri;
  if(vitri>=n){
  	cout<<"vi tri chen kh phu hop";
  }else {
  cout<<"Nhap gia tri can chen : ";
  cin>>gt;
  for(int i=n; i>vitri; i--){
    a[i]=a[i-1];
  }
  a[vitri]=gt;
  n++;
  cout<<"Mang sau khi chen la : ";
  for(int i=0; i<n; i++){
    cout<<a[i]<<" ";
  }
  }
}

int main(){
  int n;
  int a[100];
  int vitri;
  int gt;
  mang(a, n, vitri, gt);
  return 0;
}
