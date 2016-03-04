#include <fstream>
#include <iostream>
using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::ofstream;

#include <math.h>
#include <stdlib.h>


// Read in array function
void readArr(int, int, double **);
void multArrs(int, double **, int, double **, int, double **);

// read from kbd into this array, row by row
//  for a total of r x c entries;
//  WARNING: space for array must have been reserved beforehand
void readArr(int r, int c, double **arr)
{
  // code for reading in r rows of c elements each goes here
   
   for(int i=0; i<r; i++){
    //std::cout <<"enter the "<<i+1 <<"st Row of "<< c<< " numbers\n";
     for(int j=0; j<c; j++){
       std::cin >> arr[i][j];
     }
   }
}

void multArrs(int ar, double **A, int bc, double **B, int br, double **C)
{
  
  // ar is Array A Row size
  // bc is Array B collumn size
  // br is Array B rowsize
  // we don't need ac because we know its size needs to be equal to br
  for(int i=0;i<ar;i++)
  {
    for(int j=0;j<bc;j++)
    {
        C[i][j]=0;
        for(int k=0;k<br;k++)
        {
            C[i][j]=C[i][j]+A[i][k]*B[k][j];
        }
    }
  }
}


int main(int argc, char *argv[])
{
  int ar = atoi(argv[1]);
  int ac = atoi(argv[2]);
  int br = atoi(argv[3]);
  int bc = atoi(argv[4]);

  if (ac != br)
  {
    cerr<< "Matrix dimensions mismatch; exiting.\n";
    exit(-1);
  }

  // reserve space for the three arrays
  double **A = new double*[ar];	// each el. of this points to a row of A
  for (int i = 0; i < ar; i++)
    A[i] = new double[ac];	// a row of 'ac' floats

  double **B = new double*[br];
  for (int i = 0; i < br; i++)
    B[i] = new double[bc];	// a row of 'bc' floats

  double **C = new double*[ar];	// each el. of this points to a row of C
  //fill in code here to allocate C	// C has 'ar' rows and 'bc' columns

  for (int i = 0; i < ar; i++)
    C[i] = new double[bc];
  //second line to fill in	// a row of 'bc' floats
  // now that sufficient storage is allocated, start reading from kbd
  
  // end result will be "ar" arg1 X  bc arg4 matrix
  
  readArr(ar, ac, A);
  readArr(br, bc, B);

  multArrs(ar, A, bc, B, ac, C);

  // now print out answer, nice and plainly
  for(int i=0;i<ar;i++){
   
    for(int j=0;j<bc;j++){
      //std::cout<<C[i][j]<<" ";
    }
    if(i < ar- 1){
     //std::cout<<"\n" ;
    }
  }
}

