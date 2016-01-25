/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("九九表の縦軸最大値を入れてください");
		int x = new java.util.Scanner( System.in).nextInt();
		System.out.println("九九表の横軸最大値を入れてください");
		int y = new java.util.Scanner( System.in).nextInt();

		for(int i=1; i<=x; i++){
			for(int j=1; j<=y; j++){
				if(i*j<=9){
					System.out.print(" ");
				}
				if(i*j<=99){
					System.out.print(" ");
				}
				System.out.print(i*j + " ");
			}
			System.out.println();
		}
	}

}
