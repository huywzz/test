#include <iostream>

using namespace std;
void nhap(int a[],int n){
    for(int i=0;i<n;i++){
        cout<<"Nhap phan tu thu "<<i<<":";cin>>a[i];
    }
}
void mangmoi(int a[],int n, int b[]){
    
    for(int i=0;i<n;i++){
        a[i]=a[i]*b[i];
    }
}
int main()
{
    int a[100],b[100];
    int n;
    cout<<"Nhap n";cin>>n;
    cout<<"Nhap phan tu cua mang a"<<endl;
    nhap(a,n);
    cout<<"Nhap phan tu cua mang b"<<endl;
    nhap(b,n);
    mangmoi(a,n,b);
    for(int i=0;i<n;i++){
        cout<<a[i]<<endl;
    }
    return 0;
}
