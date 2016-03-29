#include <fstream>
#include <iostream>
using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::ofstream;
using std::stringstream;
using std::string;

#include <math.h>
#include <stdlib.h>
#include <sstream>
#include <list>
#include <string>

using std::list;

//class definition

// class definition
class NonZeroes {
   
  private: 
   

  public:
    NonZeroes(int colPos,double colVal);
     int colPos; 
     double colVal;

    void setValues(int colPos, double colVal);
    int getcolPos();
    double getColVal();

};

NonZeroes::NonZeroes(int colPos,double colVal){
  setValues(colPos,colVal);
}
void NonZeroes::setValues (int x, double y) {
  colPos = x;
  colVal = y;
}
int NonZeroes::getcolPos(){
   return colPos;
}
double NonZeroes::getColVal(){
  return colVal;
}

// forward declaration
void compressMat(double epsilon, list<NonZeroes> *rowOfNonzeroes,list< list<NonZeroes> > *rows);
//forward Declaration

void readList(list<NonZeroes> *rowOfNonzeroes);

void getList(list< list<NonZeroes> > *rows);


void readList(list<NonZeroes> *rowOfNonzeroes){
 
   for (std::list<NonZeroes>::iterator it=rowOfNonzeroes->begin(); it!=rowOfNonzeroes->end(); ++it)
   // lstream<<it.getcolPos()<<""<<it.colVal();
    cout<<"Tets";


}

void getList(list< list<NonZeroes> > *rows){
 
   stringstream lstream;
  
   list< list<NonZeroes> >::iterator row;
   list<NonZeroes>::iterator col;

   for (row=rows->begin(); row !=rows->end(); ++row)   

      for (std::list<NonZeroes>::iterator col=row->begin(); col !=row->end(); ++col)
        cout<<"Test";
      //lstream<<(col)->getcolPos()<<""<<(col)->getColVal();
}

void compressMat(double epsilon, list<NonZeroes> *rowOfNonzeroes,list< list<NonZeroes> > *rows)
{

  cout<<&rowOfNonzeroes<<" "<<&rows;
  // init counters
int ctr_r=0;
  // string to hold each line
  string line;
  // first loop iterate through whole file line by line
  while (getline(cin, line)) {
     // collumn count keeps track of indices
  int ctr_c=0; 
  // row count not needed but may need it later
  
    // stream string into variable 
    std::istringstream lstream(line);
    // initialize val to hold double value from string  
    double val;
     // get each value from string
    while (lstream >> val)
    {
     
     // chech has user provided a custom target value
      if(epsilon ==NULL){
        // if not.............

        // check if val is 0
        if(val == 0){
          // do nothing
        }
        // other wise .............
        else{
           // print out index and value
           cout<<ctr_c+1<< " "<<val<<" ";
           NonZeroes *nz = new NonZeroes(ctr_c+1,val);
           // rowOfNonzeroes->push_back(*nz);
        }
      }
      // user has custom value for compression
      else{
        // check absolute value of val gainst target value
        // if smaller
        if(fabs(val) <= epsilon){
          // do nothing
        }
        // other wise .....
        else{
          // print out the index and its corresponding value
          cout<<ctr_c+1<< " "<<val<<" ";
          NonZeroes *nz = new NonZeroes(ctr_c+1,val);
          rowOfNonzeroes->push_back(*nz);
        }
      }
      // increment index after ech iteration through string
      ctr_c++;
      //(rows)->push_back(*rowOfNonzeroes);
    }
    // set collumns back to zero
    ctr_c=0;
    // cosmetics break line
    cout<<"\n";  
    // increment row count
    ctr_r++;
  }
}
int main(int argc, char *argv[])
{
  
  list<NonZeroes> *rowOfNonzeroes;
  list< list<NonZeroes> > *rows;
  double epsilon;
   
  if (argc > 1 && string(argv[1]) == "-e"){
     epsilon = fabs(strtod(argv[2], 0));
     compressMat(epsilon,rowOfNonzeroes,rows);
   }
   else{
     compressMat(NULL,rowOfNonzeroes,rows);
   }
   //getList(rows);
}

