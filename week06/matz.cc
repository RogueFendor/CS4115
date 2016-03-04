#include <fstream>
#include <iostream>
using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::ofstream;
using std::stringstream;

#include <math.h>
#include <stdlib.h>
#include <sstream>
#include <list>
// Read in array function
void readArr(int, int, double **);
void compressMat(int, double **, int, double **);

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

void compressMat(int ar, double **A, int ac, double **C)
{
  
  // ar is Array A Row size
  // bc is Array B collumn size
  // br is Array B rowsize
  // we don't need ac because we know its size needs to be equal to b
  int ctr=0;
  int val=0;
  std::list<double> listOfDoubles;
  stringstream output;
  for(int i=0;i<ar;i++)
  {
   
    for(int j=0;j<ac;j++)
    {
      //cout<<A[i][j]<<" ";
      if(A[i][j] != 0){
        ctr++;
       listOfDoubles.push_back(j+1);
       listOfDoubles.push_back(A[i][j]);
      }
    }
    listOfDoubles.push_front(ctr);
    for (std::list<double>::iterator it=listOfDoubles.begin(); it!=listOfDoubles.end(); ++it)
           cout<< *it<<' ';
    listOfDoubles.clear();
    val +=ctr;
    ctr=0;
    cout<<"\n";
  }
  cout<<val<<"\n";
}


int main(int argc, char *argv[])
{
  int ar = atoi(argv[1]);
  int ac = atoi(argv[2]);
  if (argv[1] ==NULL || argv[2] ==NULL )
  {
    cerr<< "This Program will need some args.\n";
    exit(-1);
  }
  else{
    ar = atoi(argv[1]);
    ac = atoi(argv[2]);
  }
  // reserve space for the three arrays
  double **A = new double*[ar];	// each el. of this points to a row of A
  for (int i = 0; i < ar; i++)
    A[i] = new double[ac];	// a row of 'ac' floats

  double **C = new double*[ar];	// each el. of this points to a row of C
  //fill in code here to allocate C	// C has 'ar' rows and 'bc' columns
  for (int i = 0; i < ar; i++)
    C[i] = new double[ac];

  //second line to fill in	// a row of 'bc' floats
  // now that sufficient storage is allocated, start reading from kbd
  // end result will be "ar" arg1 X  bc arg4 matrix
  readArr(ar, ac, A);
  cout<<ar<<"\n";
  compressMat(ar, A, ac, C);

  // now print out answer, nice and plainly
  /*
  for(int i=0;i<ar;i++){
   
    for(int j=0;j<bc;j++){
      //std::cout<<C[i][j]<<" ";
    }
    if(i < ar- 1){
     //std::cout<<"\n" ;
    }

  }
  */
}

