/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	YodleJuggleFest.java, Mar 26, 2014, 2:43:27 PM	
 *  Yodle, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
import java.util.*;
import java.io.*;

public class YodleJuggleFest {

	
	public static void main(String[] args) {
		// TODO code application logic here
		// TODO code application logic here
		long startTime = System.currentTimeMillis();

		try {

			File file = new File("jugglefest.txt");
			int circuit_count = 0;
			int juggle_count = 0;

			if (file.exists()) {
				String input_str;
				/*
				 * FileReader l_fr = new FileReader(file); LineNumberReader
				 * l_lnr = new LineNumberReader(l_fr); int linenumber = 0;
				 * 
				 * while ((input_str = l_lnr.readLine()) != null) { if
				 * (!input_str.isEmpty()) { if (input_str.charAt(0) == ‘C’) {
				 * circuit_count++; } else if (input_str.charAt(0) == ‘J’) {
				 * juggle_count++; } } } l_lnr.close ();
				 */

				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);

				ArrayList<Circuit> circuit_list = new ArrayList<Circuit>();
				ArrayList<Juggler> juggle_list = new ArrayList<Juggler>();

				circuit_count = 0;
				juggle_count = 0;

				// now read the input file
				while ((input_str = lnr.readLine()) != null) {
					if (!input_str.isEmpty()) {
						String[] values = input_str.split("[ ,]+");

						int size = values.length;
						if (size < 5) {
							throw new IllegalArgumentException(
									"Invalid Input File incorrect number of input per line");
						} else {
							// C C0 H:10 E:8 P:10
							String flag = values[0];
							int n = Integer.parseInt(values[1].substring(1));
							int h = Integer.parseInt(values[2].substring(2));
							int e = Integer.parseInt(values[3].substring(2));
							int p = Integer.parseInt(values[4].substring(2));

							if (flag.equals("C")) {
								if (circuit_count == n) {
									circuit_list.add(new Circuit(h, e, p, n));
								} else {
									throw new IllegalArgumentException(
											"Invalid Input File Circuit index wrong");
								}
								circuit_count++;
							} else if (flag.equals("J")) {
								if (juggle_count == n) {
									Juggler j = new Juggler(h, e, p, n);
									// int size = values.length;
									j.set_Pref_Listsize(size - 5);
									for (int i = 0; i < size - 5; i++) {
										int tmp = Integer
												.parseInt(values[5 + i]
														.substring(1));
										if (tmp == circuit_list.get(tmp)
												.getIndex()) {
											j.addpref_Circuit(tmp);
										} else {
											throw new IllegalArgumentException(
													"Invalid Input File Juggle index wrong");
										}
									}
									juggle_list.add(j);
								} else {
									throw new IllegalArgumentException(
											"Invalid Input File Juggle index wrong");
								}
								juggle_count++;
							} else {
								throw new IllegalArgumentException(
										"Invalid Input File not C or J");
							}
						}
					}
				}// end of while loop
				lnr.close();

				// trim the two arraylist
				circuit_list.trimToSize();
				juggle_list.trimToSize();

				// we assume Num_Per_Circuit is an integer
				int num_per_c = juggle_count / circuit_count;
				int result;
				Circuit tmp_circuit;
				Juggler tmp_juggler;

				// based on the previous input file, init the circuit and
				// juggler’s list info
				// and calculate the dot product value
				for (int i = 0; i < circuit_count; i++) {
					tmp_circuit = circuit_list.get(i);

					tmp_circuit.circuit_init_list(num_per_c, juggle_count);
					tmp_circuit.init_Juggler_List(juggle_list);

					for (int j = 0; j < juggle_count; j++) {
						tmp_juggler = juggle_list.get(j);

						result = tmp_circuit.get_E() * tmp_juggler.get_E()
								+ tmp_circuit.get_H() * tmp_juggler.get_H()
								+ tmp_circuit.get_P() * tmp_juggler.get_P();

						tmp_circuit.add_Jvalue(tmp_juggler.get_Juggler_Index(),
								result);
					}
				}

				for (int j = 0; j < juggle_count; j++) {
					tmp_juggler = juggle_list.get(j);
					tmp_juggler.set_Circuit_List(circuit_list);
				}
				// Now we have finished retrieving informatin from the iputfile,
				// time to solve the problem
				Find_Solution f_solution = new Find_Solution(circuit_list,
						juggle_list);

				f_solution.solve_Problem();

			} else {
				System.out.println("File does not exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Run for " + totalTime);
	}
}

/*
 * this class hold the current solution information including a array of
 * currently find solution list (circuit, juggler) a array of for jugglers
 * showing whether a juggler is taken or not, if it is not taken, the value is
 * -1, if it is taken, then the index number of the circuit an array of counter
 * for each circuit’s remaining spots
 */
class Current_Solution_Info {
	// this two dimensianl array holds the information for assigned jugglers for
	// each circuit
	private int[][] circuit_j_list;

	// this is a array holds the information for number of remainning spot for
	// each circuit
	private int[] circuit_left_spots;

	// this is an array that hold each juggler’s information. If it is not
	// taken, -1, if it is taken the circuit index
	// it is assigned to
	private int[] juggler_taken_list;

	private int circuit_count;
	private int juggle_count;

	private int number_circuit_left;

	// why 1970? someone was born 1970? I like 1990 way better, caz that is the
	// year my gf was born
	private static final int yodle_email_magic_num = 1970;

	private ArrayList<Circuit> circuit_list;
	private ArrayList<Juggler> juggle_list;

	public Current_Solution_Info(ArrayList<Circuit> c_list,
			ArrayList<Juggler> j_list) {
		circuit_list = c_list;
		juggle_list = j_list;
		circuit_count = circuit_list.size();
		juggle_count = j_list.size();
		Init_Solution_Info();
	}

	private void Init_Solution_Info() {
		int j_per_c = juggle_count / circuit_count;
		circuit_j_list = new int[circuit_count][j_per_c];

		circuit_left_spots = new int[circuit_count];
		for (int i = 0; i < circuit_count; i++)
			circuit_left_spots[i] = j_per_c;

		juggler_taken_list = new int[juggle_count];
		for (int i = 0; i < juggle_count; i++)
			juggler_taken_list[i] = -1;
	}

	public int get_Circuit_Remain_Spots(int c_index) {
		return circuit_left_spots[c_index];
	}

	public int get_Juggle_Asign_Circuit(int j_num) {
		return juggler_taken_list[j_num];
	}

	public boolean is_Solution_Find() {
		if (number_circuit_left <= 0)
			return true;
		else
			return false;
	}

	// return true if the input j is a bitter fit for some juggler in this c
	// circuit
	// only get called when this c circuit is full
	public boolean check_juggler_Better_Fit(int c, int j) {
		// if (c == 1495 && j == 8786)
		// {
		// System.out.print("test");
		// }
		int input_j_value = circuit_list.get(c).return_Product_Value(j);
		// always start check from bottom
		for (int i = juggle_count / circuit_count - 1; i >= 0; i--) {
			int check_j_num = circuit_j_list[c][i];
			int check_j_value = circuit_list.get(c).return_Product_Value(
					check_j_num);

			if (input_j_value > check_j_value)
				return true;
		}
		return false;
	}

	// add juggler j into circuit c
	public void add_New_Sol(int c, int j) {
		// if ( c == 1495)
		// System.out.println ("Add "+ j + " juggler into " + c + " circuit");
		int index = juggle_count / circuit_count - circuit_left_spots[c];
		// //every insert new c, we need to make sure the list of juggler
		// remain sorted
		int input_j_value = circuit_list.get(c).return_Product_Value(j);

		int insert_index = index;

		for (int i = index - 1; i >= 0; i--) {
			int cur_j_index = circuit_j_list[c][i];
			int cur_j_value = circuit_list.get(c).return_Product_Value(
					cur_j_index);
			if (cur_j_value < input_j_value)
				insert_index = i;
		}

		if (index > insert_index) {
			// copy the previous juggler one index back
			for (int i = index - 1; i >= insert_index; i--)
				circuit_j_list[c][i + 1] = circuit_j_list[c][i];
		}
		circuit_j_list[c][insert_index] = j;
		juggler_taken_list[j] = c;
		circuit_left_spots[c]--;

		if (circuit_left_spots[c] == 0) {
			number_circuit_left--;
		}
	}

	// kick the last one from the list
	// get out of here, loser! you odnt belong here. GTFO!
	public int remove_Circuit_Last_Jugger(int c) {
		int index = juggle_count / circuit_count - 1;
		int j = circuit_j_list[c][index];
		// System.out.println ("Remove "+ j + " juggler from " + c +
		// " circuit");
		circuit_j_list[c][index] = -1;
		juggler_taken_list[j] = -1;
		number_circuit_left++;
		circuit_left_spots[c]++;
		return j;
	}

	public void debugSolution(int c_index) {

		int num = juggle_count / circuit_count - circuit_left_spots[c_index];
		for (int i = 0; i < num; i++)
			System.out.print(" " + circuit_j_list[c_index][i]);

		System.out.println("");

	}

	// make sure the found solution is corect
	public boolean verifySolution() {
		for (int k = 0; k < juggle_count; k++) {
			int asign_c = juggler_taken_list[k];
			if (asign_c == -1)
				return false;
			Juggler input_juggler = juggle_list.get(k);
			int size = input_juggler.get_PrefList_Size();
			for (int i = 0; i < size; i++) {
				int cur_pref = input_juggler.get_PrefIndex(i);
				if (cur_pref == asign_c)
					break;

				int my_pref_value = circuit_list.get(cur_pref)
						.return_Product_Value(k);
				// now check all the selected jugglers for this cricuit cur_pref
				int j_count = juggle_count / circuit_count;
				for (int j = 0; j < j_count; j++) {
					int check_j_num = circuit_j_list[cur_pref][j];

					int check_value = circuit_list.get(cur_pref)
							.return_Product_Value(check_j_num);

					if (check_value < my_pref_value)
						return false;
				}
			}
		}
		return true;
	}

	public void printSolution() {
		try {
			FileWriter outFile = new FileWriter("output.txt");
			PrintWriter out = new PrintWriter(outFile);

			// Write text to file
			int j_per_c = juggle_count / circuit_count;
			for (int i = 0; i < circuit_count; i++) {
				out.println("\nFor circuit " + i + ": ");
				for (int j = 0; j < j_per_c; j++)
					out.println(circuit_j_list[i][j] + " ");

			}

			int sum = 0;

			// make sure this magic number is still valid
			if (yodle_email_magic_num < circuit_count) {
				// find out the circuit C1970′s total sum
				for (int i = 0; i < j_per_c; i++) {
					sum += circuit_j_list[yodle_email_magic_num][i];
					System.out.println(" "
							+ circuit_j_list[yodle_email_magic_num][i]);
				}
			} else {
				System.out.println("Yodle email magic number too big!");
			}

			System.out.println("Email address is " + sum + "@yodle.com");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Find_Solution {
	private ArrayList<Circuit> circuit_list;
	private ArrayList<Juggler> juggle_list;
	private int circuit_count;
	private int juggle_count;
	private static int recursive_call_num = 0;

	public Find_Solution(ArrayList<Circuit> c_list, ArrayList<Juggler> j_list) {
		circuit_list = c_list;
		juggle_list = j_list;
		circuit_count = c_list.size();
		juggle_count = juggle_list.size();
		/*
		 * //before we start, sort the juggler product value for each circuit
		 * for (int i= 0; i <circuit_count; i++ ) {
		 * circuit_list.get(i).sort_Jvalue(); }
		 */
	}

	public void solve_Problem() {
		Current_Solution_Info cur_sul = new Current_Solution_Info(circuit_list,
				juggle_list);
		attemtp_Find_Solution(juggle_list, cur_sul);

		System.out.println("Solution Find! ");
		System.out.println("Function called " + recursive_call_num + " times!");
		// show_NumofCall();
		// show_NumofCall ();
		if (cur_sul.verifySolution()) {
			cur_sul.printSolution();
		} else {
			System.out.println("This Solution is not valid! ");
		}
	}

	static private int partition(int arr[], int left, int right,
			int index_array[]) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];

		while (i <= j) {
			while (arr[i] > pivot) {
				i++;
			}
			while (arr[j] < pivot) {
				j--;
			}
			if (i <= j) {

				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				// arr[i], arr[j] switched place
				// so should the recording array list
				tmp = index_array[i];
				index_array[i] = index_array[j];
				index_array[j] = tmp;
				i++;
				j--;
			}
		}
		;

		return i;
	}

	// sort the both arr and index_array based on the value of arr
	// i chose to not to use java collection sort for convinience, speed and
	// space
	static public void quickSort(int arr[], int left, int right,
			int index_array[]) {
		int index = partition(arr, left, right, index_array);
		if (left < index - 1) {
			quickSort(arr, left, index - 1, index_array);
		}
		if (index < right) {
			quickSort(arr, index, right, index_array);
		}
	}

	private void attemtp_Find_Solution(ArrayList<Juggler> input_juggle_list,
			Current_Solution_Info cur_sul) {
		int tmp_sort_list[];
		// first get the number of available juggerls
		int avail_j_num = input_juggle_list.size();

		System.out.println("Now " + avail_j_num + "jugglers remain");

		int tmp_sort_value[];
		ArrayList<Juggler> remove_juggle_list = new ArrayList<Juggler>();
		// sort the available jugger’s based on the current circuit and pref

		tmp_sort_list = new int[avail_j_num];
		// tmp_sort_value = new int[avail_j_num];

		int tmp_count = 0;
		for (int j = 0; j < avail_j_num; j++) {

			int j_num = input_juggle_list.get(j).get_Juggler_Index();
			tmp_sort_list[tmp_count] = j_num;
			tmp_count++;

		}
		// then do a sort
		// quickSort(tmp_sort_value, 0, avail_j_num – 1, tmp_sort_list);

		// starting from top of sort list
		for (int j = 0; j < avail_j_num; j++) {
			// System.out.println("attempting " + j +" juggler for " + i +
			// " ciruit");
			// System.out.println("Current juggler is " + j );
			int attempt_j_num = tmp_sort_list[j];// circuit_list.get(i).return_Sort_List_Index
													// (j);
			// try the circuits for this juggler in the following order
			// first select from the preference list,the first, then second,
			// then third then based on the product dot value for each circuit

			int attempt_num = juggle_list.get(attempt_j_num)
					.get_Prev_Circuit_Attempt_Num();
			int attempt_c_num = juggle_list.get(attempt_j_num)
					.get_CurrentTopPickCircuit(attempt_num);
			attempt_num++;
			boolean insert_j_flag = false;
			while (attempt_c_num != -1) {
				// check to see if the attempt circuit is full
				if (cur_sul.get_Circuit_Remain_Spots(attempt_c_num) > 0) {
					// just add the juggler straight into the circuit if it is
					// not full
					cur_sul.add_New_Sol(attempt_c_num, attempt_j_num);
					// always set the number of attempts on circuit, so if this
					// juggler got kicked
					// next time he know where to start
					juggle_list.get(attempt_j_num)
							.set_Prev_Circuit_Attempt_Num(attempt_num);
					insert_j_flag = true;
					break;
				} else {
					// this circuit is full
					// remember all the juggers are already sorted in the
					// circuit, so we start from the last one
					// then check all the jugger already asigned to this
					// circuit, if there is any juggler perform worse than me,
					// kick worst one and pick me!
					if (cur_sul.check_juggler_Better_Fit(attempt_c_num,
							attempt_j_num)) {
						// we need to remove the last one and add the new
						// juggler into the solution
						int removed_j = cur_sul
								.remove_Circuit_Last_Jugger(attempt_c_num);

						remove_juggle_list.add(juggle_list.get(removed_j));
						// add the new juggler, what a sad day for the one who
						// got kicked
						cur_sul.add_New_Sol(attempt_c_num, attempt_j_num);
						// same thing, set the number of attempts
						juggle_list.get(attempt_j_num)
								.set_Prev_Circuit_Attempt_Num(attempt_num);
						insert_j_flag = true;
						break;
					}
				}
				// fail at this preference, try next one
				attempt_c_num = juggle_list.get(attempt_j_num)
						.get_CurrentTopPickCircuit(attempt_num);
				attempt_num++;
			}// end of while loop

			if (!insert_j_flag) {
				// if none of this juggler’s preference was selected. just pick
				// any circuit base on the product dot
				attempt_num--;
				attempt_c_num = juggle_list.get(attempt_j_num)
						.get_Rest_Non_Pref_PickCircuit(attempt_num);
				attempt_num++;
				while (attempt_c_num != -1) {
					if (cur_sul.get_Circuit_Remain_Spots(attempt_c_num) > 0) {
						cur_sul.add_New_Sol(attempt_c_num, attempt_j_num);
						juggle_list.get(attempt_j_num)
								.set_Prev_Circuit_Attempt_Num(attempt_num);

						insert_j_flag = true;
						break;
					}
					attempt_c_num = juggle_list.get(attempt_j_num)
							.get_Rest_Non_Pref_PickCircuit(attempt_num);
					attempt_num++;
				}
			}
		}// end of for loop

		if (remove_juggle_list.isEmpty())
			return;
		else {
			recursive_call_num++;
			attemtp_Find_Solution(remove_juggle_list, cur_sul);
		}
	}
}

class Circuit {
	private int H_value;
	private int E_value;
	private int P_value;
	private int c_index;

	// this is a array of jugglers’s dot product for this circuit
	private int[] juggle_dot_product_list;
	// this is a sort array of juggler’s index based on the dot product
	// private int [] juggle_sort_list;
	private int total_jugglers_num;
	private int max_dot_product_value = 0;

	private ArrayList<Juggler> juggle_list;

	public Circuit(int h, int e, int p, int n) {
		H_value = h;
		E_value = e;
		P_value = p;
		c_index = n;
	}

	public void add_Jvalue(int j, int value) {
		// juggle_vlist.add (new Circuit_Juggle_Value(j, value));
		juggle_dot_product_list[j] = value;
		// juggle_sort_list[j] = j;

		if (value > max_dot_product_value)
			max_dot_product_value = value;

		juggle_list.get(j).update_New_Dot_Prodcut(value);

	}

	public void init_Juggler_List(ArrayList<Juggler> j_list) {
		juggle_list = j_list;
		return;
	}

	public int return_Product_Value(int j) {
		return juggle_dot_product_list[j];
	}

	public int return_Max_Product_Value(int j) {
		return max_dot_product_value;
	}

	/*
	 * public int return_Sort_List_Index (int i) { return juggle_sort_list[i]; }
	 */
	public void circuit_init_list(int jum_per_c, int t) {
		// juggle_sort_vlist = new long[total_jugglers_num];
		// juggle_vlist = new
		// ArrayList<Circuit_Juggle_Value>(total_jugglers_num);
		total_jugglers_num = t;
		juggle_dot_product_list = new int[total_jugglers_num];
		// juggle_sort_list = new int[total_jugglers_num];
	}

	public int getIndex() {
		return c_index;
	}

	public int get_H() {
		return H_value;
	}

	public int get_E() {
		return E_value;
	}

	public int get_P() {
		return P_value;
	}
}

/* A class that hold information for each Juggler */
class Juggler {
	private int H_value;
	private int E_value;
	private int P_value;

	private int juggler_index;
	private ArrayList<Integer> pref_list;
	private int pref_list_size;
	private static int max_pref_list_size = 0;
	private int max_dot_product = 0;
	// private static int totalmax_dot_product = 0;
	private static ArrayList<Circuit> circuit_list;
	// an aray of circuit index sorted based on the following order:
	// first prefence, second, and so on, and then the product dot value
	// private int circuit_index_list [];
	private int prev_circuit_attempt_num;

	// private boolean taken;

	public Juggler(int h, int e, int p, int n) {
		H_value = h;
		E_value = e;
		P_value = p;
		juggler_index = n;
		pref_list_size = 0;
		prev_circuit_attempt_num = 0;
	}

	public int get_Prev_Circuit_Attempt_Num() {
		return prev_circuit_attempt_num;
	}

	public void set_Prev_Circuit_Attempt_Num(int attempt_num) {
		prev_circuit_attempt_num = attempt_num;
	}

	public int get_Juggler_Index() {
		return juggler_index;
	}

	public void set_Circuit_List(ArrayList<Circuit> c_list) {
		circuit_list = c_list;
		/*
		 * int size = circuit_list.size(); //circuit_index_list = new int[size];
		 * int ciruit_value_list [] = new int [size];
		 * 
		 * //we sort the circuit in following way, first prefence, second, and
		 * so on, and then the product dot value for ( int i = 0; i < size; i++)
		 * { //circuit_index_list[i] = i; ciruit_value_list[i] =
		 * max_dot_product* get_Circuit_PrefBonus(i)
		 * +circuit_list.get(i).return_Product_Value(juggler_index); }
		 * 
		 * //Find_Solution.quickSort(ciruit_value_list, 0, size – 1,
		 * circuit_index_list);
		 */
	}

	public void update_New_Dot_Prodcut(int value) {
		if (value > max_dot_product) {
			max_dot_product = value;
		}
	}

	public int return_Max_Dot_Product() {
		return max_dot_product;
	}

	public void set_Pref_Listsize(int size) {
		pref_list = new ArrayList<Integer>(size);
	}

	private boolean is_Inside_Preference_List(int c) {
		for (int i = 0; i < pref_list_size; i++) {
			if (pref_list.get(i) == c)
				return true;
		}

		return false;
	}

	// this function is used when all the circuits in the juggler’s preference
	// list have been selected
	public int get_Rest_Non_Pref_PickCircuit(int num_attempt) {
		int count = 0;
		int v = num_attempt - pref_list_size;

		for (int i = 0; i < circuit_list.size(); i++) {
			if (is_Inside_Preference_List(i)) {
				continue;
			}
			if (count == v)
				return i;
			count++;
		}

		return -1;
	}

	// get the juggler’s prefered circuit from his preference list based on the
	// number of attempt
	public int get_CurrentTopPickCircuit(int num_attempt) {
		if (num_attempt >= 0 && num_attempt < pref_list_size)
			return pref_list.get(num_attempt);
		else
			return -1;
	}

	/*
	 * //return this jugger’s current top prefer circui for the current solution
	 * public int get_FirstPref (Current_Solution_Info cur_sul) { //retur -1 if
	 * there is no pref if (pref_list.isEmpty()) return -1; else { for (int i =
	 * 0; i < pref_list_size; i++) { int c = pref_list.get(i); if
	 * (cur_sul.get_Circuit_Remain_Spots(c) > 0) return c; } return -1; } }
	 */

	// based on the previous attempt of selection of circuit
	// get the juggelers current top choice for circuit
	public int get_CurrentPref() {
		// retur -1 if there is no pref
		if (pref_list.isEmpty())
			return -1;
		else {
			if (prev_circuit_attempt_num < pref_list_size)
				return pref_list.get(prev_circuit_attempt_num);// circuit_index_list[prev_circuit_attempt_num];
			else
				return -1;
		}
	}

	// the the preference priroity for the input c_index circuit
	public int get_Circuit_PrefBonus(int c_index) {
		int i = 0;
		for (i = 0; i < pref_list_size; i++) {
			if (pref_list.get(i) == c_index)
				break;
		}

		return pref_list_size - i;
	}

	// add circuit n into juggler’s preference list
	// will remove if it is a duplicate
	public void addpref_Circuit(int n) {
		boolean duplicate = false;
		for (int i = 0; i < pref_list_size; i++) {
			if (pref_list.get(i) == n)
				duplicate = true;
		}
		// make sure no duplicate preference is added into the list
		if (!duplicate) {
			pref_list.add(n);
			pref_list_size++;
			if (pref_list_size > max_pref_list_size)
				max_pref_list_size = pref_list_size;
		}
	}

	public int get_PrefList_Size() {
		return pref_list_size;
	}

	public int get_PrefIndex(int num) {
		return pref_list.get(num);
	}

	public int get_H() {
		return H_value;
	}

	public int get_E() {
		return E_value;
	}

	public int get_P() {
		return P_value;
	}
}