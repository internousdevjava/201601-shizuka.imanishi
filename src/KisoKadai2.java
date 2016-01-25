/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int rnd = new java.util.Random().nextInt(100);
		int mondai = rnd + 1;
		int input = 0;
		int count = 0;
		String massage = "数字をいれて下さい。";
		System.out.println("1~100までの数字を10回以内に当てて下さい。\nヒント：「あと少し」と出たら5以内、「おしい」とでたら2以内に正解があります。");
		for(int i=1;i<=10;i++){
			System.out.println(massage);
			input = new java.util.Scanner(System.in).nextInt();
			count++;
			if(mondai==input){
				System.out.println("正解!!");
				break;
			}else if( (mondai-2) <=input && input <= (mondai+2)){
				System.out.println("おしい。");
			}else if((mondai-5) <=input && input <= (mondai+5)){
				System.out.println("あと少し。");
			}else if(input < (mondai+5) ){
				System.out.println("答えはそれより大きいです。");
			}else if(input > (mondai-5)){
				System.out.println("答えはそれより小さいです。");
			}
		}

		if(count==10){
			System.out.println("Game Over(´･ω･`)\n次は頑張ってください。");
		}else if(count>=1 && count<=2){
			System.out.println("あなたは" + count +"回で正解しました。\n運がいいですね。");
		}else if(count>=3 && count<=6){
			System.out.println("お疲れ様でした。\nあなたは" + count +"回で正解しました。\n優秀ですね。");
		}else if(count>6){
			System.out.println("お疲れ様でした。\nあなたは" + count +"回で正解しました。\n普通ですね。");
		}

	}

}
