#include <iostream>

using std::cout;
using std::endl;

#include <stdlib.h>

int main(int argc, char *argv[])
{
  int n = atoi(argv[1]);
  int arr[25];

  for (int i = 0; i < n; i++)
  {
    for (int j = 0; j < n; j++)
    {
      int k = j / (j+1);	// k always 0, but a compiler is unlikely
      arr[k] = i+j;		//    to know this, so division is always done
    }
  }
}
