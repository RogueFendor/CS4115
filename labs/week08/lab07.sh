echo "Start Test"

./$1 < ~cs4115/labs/week07/m0.in>m0.out

./$1 < ~cs4115/labs/week07/m1.in>m1.out



diff ~cs4115/labs/week07/m0.out m0.out > diff1.txt
diff ~cs4115/labs/week07/m0.out m0.out > diff2.txt

