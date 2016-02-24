let odd x =
	3 * x +1;;

let even x =
	x / 2;;

let rec func x len =
	if x = 1 then len + 1
	else if x mod 2 = 0 then func (even(x)) (len +1)
	else(
		func (odd(x)) (len +1) 
		);
;;

let rec run x big =
	if x = 1000001 then big	
	else if func x 0 > func big 0 then run (x+1) x
	else(
				run (x+1) big
		);
;;

print_string "The number that produces the biggest chanin is: ";
print_int (run 1 1);
print_string "\nThe chain has a depth of: ";
print_int (func (run 1 1) 0);
