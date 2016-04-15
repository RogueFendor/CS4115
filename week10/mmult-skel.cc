
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
using std::stringstream;
using std::ostream;

#include <math.h>
#include <stdlib.h>

// *************  class definition
class NonZeroes {
  #include <iostream>
  #include <sstream>



  private:


  public:
    /* overload string stream operators */
    friend std::istream& operator >>(std::istream &is,NonZeroes *obj);
    friend std::ostream& operator <<(std::ostream &os,const NonZeroes &obj);

    // copy constructor
    NonZeroes( const NonZeroes &obj);

    NonZeroes(int colPos,double colVal);
    NonZeroes();
     int colPos;
     double colVal;
    ~NonZeroes(void);

    void setValues(int colPos, double colVal);
    int getcolPos();
    double getColVal();
    void setColPos(int pos);
    void setColVal(double val);


};
// ***********************************

/**
 * @brief NonZeroes::NonZeroes copy constructor
 * @param obj
 */
NonZeroes::NonZeroes( const NonZeroes &obj){

    this->setValues(obj.colPos,obj.colVal);
}
/**
 * @brief NonZeroes::~NonZeroes
 */
NonZeroes::~NonZeroes(){

}
/**
 * @brief NonZeroes::NonZeroes
 *
 * No Arg Constructor
 */
NonZeroes::NonZeroes(){

}
/**
 * @brief NonZeroes::NonZeroes
 * @param colPos
 * @param colVal
 *
 * Constructor with colVal and colVal as parameters
 */
NonZeroes::NonZeroes(int colPos,double colVal){
  setValues(colPos,colVal);
}

/**
 * @brief NonZeroes::setValues
 * @param x
 * @param y
 *
 * Setter for colVal and colPos
 */
void NonZeroes::setValues (int x, double y) {
  colPos = x;
  colVal = y;
}
/**
 * @brief NonZeroes::setColPos
 * @param pos
 *
 * Setter for colPos
 */
void NonZeroes::setColPos(int pos){
    this->colPos = pos;
}
/**
 * @brief NonZeroes::setColVal
 * @param val
 *
 * setter for colVal
 */
void NonZeroes::setColVal(double val){
    this->colVal = val;
}
/**
 * @brief NonZeroes::getcolPos
 * @return column position
 */
int NonZeroes::getcolPos(){
   return colPos;
}
/**
 * @brief NonZeroes::getColVal
 * @return column value
 */
double NonZeroes::getColVal(){
  return colVal;
}

/**

   **** Over load the istream oerator >>
 * @brief operator >>
 * @param is
 * @param obj
 * @return
 */

std::istream& operator >>(std::istream &is,NonZeroes *obj)
{
  is>>obj->colPos;
  is>>obj->colVal;
  return is;
}

/**
  **** overload the ostream oerator <<
 * @brief operator <<
 * @param os
 * @param obj
 * @return
 */

std::ostream& operator <<(std::ostream &os,const NonZeroes *obj)
{
 os<<obj->colPos<<" "<<
     obj->colVal<<" ";
  return os;
}

//  Forward declarations
typedef list<NonZeroes> sparseRow;

typedef vector<sparseRow> sparseMat;


void readMat(sparseMat&, int&); // read from stdin
void transpMat(const sparseMat, sparseMat&); // first arg is mat to transpose;
void multMat(const sparseMat, const sparseMat, sparseMat&);
double dotProd(const sparseRow, const sparseMat);
void outMat(const sparseMat);
//  second is passed by reference
//  so this will be the transpose

double epsilon = 0.0;

/**
      Main Mwethode initializes all operations
*/

int main(int argc, char *argv[])
{
  //int n, m;
  //cin>> n>> m;      
  int nzct; 
  sparseMat rows; // vector of sparseRows
  readMat(rows, nzct); // function call back to read in values

  
  sparseMat prod;
  multMat(rows, rows, prod);

  outMat(prod);
}

//////////////// functions below here ///////////////////

void readMat(sparseMat& rows, int& nzct)
{
  nzct = 0;
  int tmp=0;
  string line;
  while (getline(cin, line))  // get next full line of text; NB: text
  {

    stringstream lstream(line) ;
    sparseRow row;
    NonZeroes nz2;
    while (lstream>>&nz2){
      row.push_back(nz2);
    }
  tmp=nz2.getcolPos();
  rows.push_back(row);
  row.clear();
  if(tmp>nzct){
    nzct=tmp;
  }
  }
}

void transpMat(const sparseMat rows, sparseMat& transp)
{
  for (unsigned int c = 0; c < rows.size(); c++)  // square matrix
  {
          sparseRow row;
          transp.push_back(row);  // initialise every row of transpose

  }
  for (unsigned int c = 0; c < rows.size(); c++)  // square matrix
  {
      sparseRow r= rows[c];
      sparseRow::iterator nonZeroObj;
      for (nonZeroObj=r.begin(); nonZeroObj !=r.end(); ++nonZeroObj){
          double val = nonZeroObj->getColVal();
          NonZeroes nz(c+1,val);
          transp[nonZeroObj->colPos -1].push_back(nz);
      }
   }
}

void multMat(const sparseMat m1, const sparseMat m2, sparseMat& res)
{
  // remove every row of result, just in case anything there...
  res.clear();

  // now, initialise (install blank) every row of result
  for (unsigned int c = 0; c < m1.size(); c++)
  {
    sparseRow row;
    res.push_back(row);	
  }
 vector<sparseRow> transp; // new vector for transpose spars matrix
 transpMat(m2, transp);  // transpose matrix
 for (unsigned int c = 0; c < m2.size(); c++){
      sparseRow row = transp[c];
      sparseRow::iterator nonZeroObj;
      for (nonZeroObj=row.begin(); nonZeroObj !=row.end(); ++nonZeroObj){
          cout<<nonZeroObj->colPos<<" "<<nonZeroObj->colVal<<" ";
      }
      cout<<"\n";
  }
   sparseRow row;

  for (unsigned int c = 0; c < m1.size(); c++)
  {
    NonZeroes nz;
  /**

  need to iterate row by row but each row of A needs to be multiplied
  by every row of transpose parse transpose and do this again.
  **/

    sparseRow mat  = m1[c];
    double val = dotProd(mat,transp);
    nz.setColPos(c);
    nz.setColVal(val);
    row.push_back(nz);
  }

  res.push_back(row);
  row.clear();
}

double dotProd(sparseRow r1, sparseMat r1)
{
  
  double val =0;
  sparseRow::iterator rowMatB;
  NonZeroes nz1;
  sparseRow::iterator rowMatA;
  int ctr1=0;
  int ctr=0;
   for (int i = 0;i< r1.size();i++)
   {
     nz1.setValues(rowMatA->getcolPos(),rowMatA->getColVal());
    
     for (rowMatB = r2.begin(); rowMatB != r2.end(); rowMatB++)
     {
       /*if(nz1.getcolPos() == rowMatB->getcolPos()){
         val = val + nz1.getColVal() * rowMatB->getColVal();
       
       }
       */   
       ctr1++;  
     }

     ctr++;
  }
  return val;
}

void outMat(const sparseMat mat)
{
  // compute non-zeros
  // compute non-zeros
  int nzct = 0;
  sparseMat::iterator rit; // we only want to /read/ list
 for (unsigned int c = 0; c < mat.size(); c++){
    sparseRow row = mat[c];
    // iterate over this row, only /read/
    sparseRow::iterator nzit;
    for (nzit = row.begin(); nzit != row.end(); nzit++)
    {
      cout<<nzit->getcolPos()<<" "<<nzit->getColVal()<<" ";
    }
    cout<<"\n";
  }


}

// http://www.codersource.net/c/c-miscellaneous/overloading-stream-operators.aspx
