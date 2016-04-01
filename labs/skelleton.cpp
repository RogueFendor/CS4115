






#include <iostream>
#include <string>
#include <sstream>
#include <list>
#include <vector>

using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::string;
using std::list;
using std::vector;
using std::istringstream;
using std::ostream;

#include <math.h>
#include <stdlib.h>

class nz {      // one instance for every non-zero entry in matrix
public:
  nz() { ind = 0; val = 0.0; }
  nz(int i, double v) { // constructor when given the two items of data
    ind = i;
    val = v;
  }
  int getInd() const { return ind; } // access to ind class member
  double getVal() const { return val; } // access to val class member
  friend istringstream& operator>>(istringstream& is, nz& base);
  friend ostream& operator<<(ostream &os, const nz& base);

private:
  int ind;
  double val;
};

typedef list<nz> sparseRow;
typedef vector<sparseRow> sparseMat;

void readMat(sparseMat&, int&); // read from stdin
void transpMat(const sparseMat, sparseMat&); // first arg is mat to transpose;
               //  second is passed by reference
               //  so this will be the transpose
double epsilon = 0.0;

int main(int argc, char *argv[])
{
  string line;

  int nzct;
  sparseMat rows;
  readMat(rows, nzct);

  vector<sparseRow> transp;
  transpMat(rows, transp);

  // now write out
  //    :
}

void readMat(sparseMat& rows, int& nzct)
{
  nzct = 0;
  string line;
  while (getline(cin, line))  // get next full line of text; NB: text
  {
    istringstream lstream(line) ;
    sparseRow row;

    // :
  }
}

void transpMat(const sparseMat rows, sparseMat& transp)
{
  for (unsigned int c = 0; c < rows.size(); c++)  // square matrix
  {
    sparseRow row;
    transp.push_back(row);  // initialise every row of transpose
  }

  // :
}
