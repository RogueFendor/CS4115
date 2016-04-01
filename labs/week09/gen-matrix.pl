#!/usr/bin/perl -w

use Getopt::Std;

use strict;
use warnings;

sub tailof;

my (@args) = @ARGV;
my ($prog) = tailof $0;
my ($usage) = "usage:\t$prog [-d density] [-h] [-n rows (columns)] [-r range] [-s(ymmetric)] [-o]\n";

my %args;
getopts("d:hn:or:s", \%args);

exists $args{'h'} && die "$usage\n";;

my $density =	$args{'d'} || 1;
my $n =		$args{'n'} || 10;
my $range=	$args{'r'} || 5.0;
my $symm=	$args{'s'} || 0;
my $octave=	$args{'o'} || 0;

my (@arr);

die "Invalid density: $density\n" unless (0 <= $density && $density <= 1);
my $msize = ($symm == 0) ? $n * $n : $n * ($n+1) / 2;
my $thresh = $density * $msize;

# print out random array (maybe in pseudo-octave notation)
#  array will be symmetrical
for (my $i = 0; $i < $n; $i++)
{
    for (my $j = 0; $j < $n; $j++)
    {
	my $val = (rand($msize) < $thresh) ? rand($range) : 0; # random value <= $range
	$arr[$i][$j] = $val;
	if ($symm == 1) { $arr[$j][$i] = $val; }
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
