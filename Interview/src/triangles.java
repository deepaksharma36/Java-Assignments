import java.io.*;
import java.util.*;

public class triangles {
	
	
	public static void main(String args[]) throws Exception {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int sides[] = new int[3];
		int temp;
		String str[] = new String[n];
		boolean flag = false;
		if ((1 - n) * (5000 - n) <= 0) {

			for (int arr_i = 0; arr_i < n; arr_i++) {
				sides[0] = in.nextInt();
				sides[1] = in.nextInt();
				sides[2] = in.nextInt();
				flag = false;
				if ((1 - sides[0]) * (2000 - sides[0]) <= 0) {
					if ((1 - sides[1]) * (2000 - sides[1]) <= 0) {
						if ((1 - sides[2]) * (2000 - sides[2]) <= 0) {
							if (sides[0] > sides[1]) {
								temp = sides[0];
								sides[0] = sides[1];
								sides[1] = temp;
							}
							if (sides[1] > sides[2]) {
								temp = sides[1];
								sides[1] = sides[2];
								sides[2] = temp;
							}

							if (sides[0] + sides[1] > sides[2]) {
								flag = true;
							}
						}
					}
				}

				if (flag) {
					if ((sides[0] == sides[1]) && (sides[1] == sides[2])) {
						str[arr_i] = "Equilateral";
						// System.out.println("Equilateral");
					} else if ((sides[0] == sides[1]) || (sides[1] == sides[2])
							|| (sides[0] == sides[2])) {
						str[arr_i] = "Isoceles";
						// System.out.println("Isoceles");
					}
					else {
						str[arr_i] = "None of these";
						// System.out.println("None of these");
					}

				} else {
					str[arr_i] = "None of these";
				}

			}
			for (int arr_i = 0; arr_i < n; arr_i++) {
				System.out.println(str[arr_i]);
			}

		}
	}
}