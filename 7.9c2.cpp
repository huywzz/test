#include <iostream>

using namespace std;
void xoa(int a[],int &n,int vitri){
  
        for(int i=vitri;i<n;i++){
            a[i]=a[i+1];
        }
        n--;
}
void phantutrung(int a[],int &n){
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            if(a[i]==a[j]){
                xoa(a,n,j);
                i--;
            }
        }
    }
}
int main()
{
    int a[100];
    int n;
    cout<<"Nhap n";cin>>n;
    
    for(int i=0;i<n;i++){
        cout<<"Nhap phan tu thu "<<i<<":";cin>>a[i];
    }
    phantutrung(a,n);
    for(int i=0;i<n;i++){
        cout<<a[i]<<endl;
    }
    return 0;
}
