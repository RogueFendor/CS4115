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


// forward declaration
void compressMat();

void compressMat(double epsilon)
{



  /*
     this function just takes if provided by user a target value
     prodided from commandline as a paramter reads data file line by line.
     the first while loops first reads through the whole file line by line.
     the second while loop iterates through the line value by value and 
     checks the value against either zero or provided target value.  

  */

  // init counters

  // collumn count keeps track of indices
  int ctr_c=0; 
  // row count not needed but may need it later
  int ctr_r=0;

  // string to hold each line
  string line;
  // first loop iterate through whole file line by line
  while (getline(cin, line)) {
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
        }
      }
      // increment index after ech iteration through string
      ctr_c++;
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
  
  double epsilon;
  if (argc > 1 && string(argv[1]) == "-e"){
     epsilon = fabs(strtod(argv[2], 0));
     compressMat(epsilon);
   }
   else{
    compressMat(NULL);
   }
}

