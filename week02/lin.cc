#include <fstream>
#include <iostream>

using std::cout;
using std::cerr;		// for writing out errors; different to cout
using std::endl;
using std::ifstream;
using std::string;

#include <math.h>
#include <stdlib.h>

int count(char, string);

int main(int argc, char *argv[])
{
  int n = atoi(argv[1]);
  int arr[25];
  char ch;
 
  for (int i = 0; i < n; i++)
  {
    ch = '1';

    // now count no. of times character, c, occurs in the
    //   file specified
    int occurs = count(ch, "blah.txt");

   // cout<< "No. of occurrences of "<< ch<< " in file: "<< occurs<< endl;
  }
}

int count(char watch, string fname)
{
  const char * f = fname.c_str(); // convert string to "raw characters"
  ifstream fin(f);		  // constructor requires fname in raw form
  if (fin == NULL)
  {
    cerr<< "Couldn't open file \""<< fname<< "\"; is it in your directory?\n";
    exit(-1);
  }

  char curr;
  int count = 0;		// keep track of no. of occurs of 'watch'
  while (fin>> curr)
  {
    if (curr == watch)
      count++;
  }

  fin.close();		// good programming hygiene

  return count;
}
