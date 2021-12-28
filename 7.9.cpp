#include <iostream>

using namespace std;

void mang(int a[], int n, bool kt[]){
  cout<<"Nhap so phan tu cua mang : ";
  cin>>n;
  for(int i=0; i<n; i++){
    cout<<"Nhap gia tri a["<<i<<"] : ";
    cin>>a[i];
  }
  for(int i=0; i<n; i++){
    kt[i]=true;
  }
  for(int i=0; i<n-1; i++){
    for(int j=i+1; j<n; j++){
      if(a[i]==a[j] && kt[j]==true){
          kt[j]=false;
      }
    }
  }
  for(int i=0; i<n; i++){
    if(kt[i]==true){
      cout<<a[i];
    }
  }
}

int main(){
  int n;
  int a[100];
  bool kt[100];
  mang(a,n,kt);
  return 0;
}
