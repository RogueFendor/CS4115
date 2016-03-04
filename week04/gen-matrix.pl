#!/usr/bin/perl -w

use Getopt::Std;

use strict;
use warnings;

sub tailof;

my (@args) = @ARGV;
my ($prog) = tailof $0;
my ($usage) = "usage:\t$prog [-h] [-n rows (columns)] [-r range] [-o]\n";

my %args;
getopts("hn:or:", \%args);

exists $args{'h'} && die "$usage\n";;

my $n =		$args{'n'} || 10;
my $range=	$args{'r'} || 5.0;
my $octave=	$args{'o'} || 0;

my (@arr);


# print out random array (maybe in pseudo-octave notation)
#  array will be symmetrical
for (my $i = 0; $i < $n; $i++)
{
    for (my $j = $i; $j < $n; $j++)
    {
	$arr[$i][$j] = $arr[$j][$i] = rand($range); # random value <= $range
    }
}

$octave && print "A = [";
for (my $i = 0; $i < $n; $i++)
{
    for (my $j = 0; $j < $n; $j++)
    {
	 printf "%.5f ", $arr[$i][$j];
    }
    if ($octave)
    {
	if ($i < $n-1) {
	    print ";";
	} else {
	    print "]";
	}
    }
    print "\n";
}

$octave && print "
rank(A)
[v,d] = eig(A)
v'
v*v'
";

###### subs here

sub tailof {
    $_[0] =~ m!([^/]*)$!;
    $1;
}
