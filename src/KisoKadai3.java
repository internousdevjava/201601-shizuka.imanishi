import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		menu();

	}

	public static void menu(){
		int menu = 0;
		boolean end = true;
		String cdir=null;
		while ( end ){
			System.out.println("テキストファイルに入出力します。以下のメニューを見て、1~3の数字を入力してください。");
			System.out.println("【メインメニュー】\n1 : ファイルを出力します。\n2 : ファイルに入力します。\n3 : ファイルを新規作成します。\n4 : 終了します。");
			menu = new java.util.Scanner(System.in).nextInt();
			switch(menu){
				case 1 :{
					System.out.println("読み込むファイルの選択を行います。");
					cdir=selectfile();
					System.out.println("出力が完了しました。\nメインメニューに戻ります。");
					break;
				}
				case 2 :{
					System.out.println("書き込むファイルを選択します。");
					cdir = selectfile();
					if(cdir == null){
						System.out.println("ファイルが選択されませんでした。メインメニューに戻ります。");
					}
					wmenu(cdir);
					break;
				}
				case 3 :{
					System.out.println("ファイルを新規作成するフォルダを選びます。");
					cdir = makefile2();
					break;
				}

				case 4 :{
					System.out.println("終了します。");
					end = false;
					break;
				}
			default : {
				System.out.println("メニューにはない数字です。");
				}
			}
		}
	}

	//ファイル選択メソッド
	public static String selectfile(){
		String cdir;
		boolean sfend = true;
		String modori= null;

		while(true){
			System.out.println("使用するフォルダまたは、ファイルのアドレスを入力してください。\\は2つ入力してください。\n表示したリストから使用するフォルダ、ファイルを選べます。"
					+ "\n0を入力すると、現在のフォルダにファイルを作成します（メインメニューに戻れます）。\n1を入力するとローカルディスクCに移動します。");
			cdir = new java.util.Scanner(System.in).nextLine();
			if(cdir.equals("0")){
				System.out.println("ファイルを新規作成します。");//メイクファイルメソッドに移動
				modori  = makefile(cdir);
				sfend = false;
				break;
			}
			if(cdir.equals("1")){
				cdir = "c:\\";
				break;
			}
			File cdirectory = new File(cdir);

			//Fileチェック1：フォルダなら下に、ファイルならリードメソッド実行しメニューに、存在しない場合はMakeに移動する。
			if(cdirectory.exists()){
				if(cdirectory.isDirectory()){
					break;
				}else if(cdirectory.canRead()){
					readfile(cdir);
					modori = cdir;
					sfend=false;
					break;
				}else{
					System.out.println("そのファイルは読み込みできません。入力しなおしてください。");
				}
			}else{
				System.out.println("入力されたファイルは存在しません。現在のフォルダに新規作成しますか？");
				System.out.println("1       : はい、新規作成します。\n1以外の整数 : いいえ、パスを入力しなおします。");
				int sin= new java.util.Scanner(System.in).nextInt();
				if(sin==1){
					System.out.println("ファイルを新規作成します。");
					modori = makefile(cdir);
					sfend=false;
					break;
				}
			}
		}

		//選択がフォルダの場合は、リスト化→選択→
		while(sfend){
			//Fileチェック機構　ファイルならReadに、読めないなら入力しなおし
			//dirリスト化
			File cdirectory = new File(cdir);
			String filelist[] = cdirectory.list();
			for(int i=0; i<=(filelist.length-1) ; i++){
				System.out.println((i+1) + " : " +filelist[i]);
			}

			System.out.println("現在のフォルダは"+cdir+"です。");

			//Fileを番号で選択する。
			System.out.println("上記リストから使用するフォルダまたはファイルの番号を選び、入力してください。\n-1を入力すると一つ上のファイルに戻ります。\n0を入力するとファイルを新規作成します。");
			int selectfile = new java.util.Scanner(System.in).nextInt();
			if(selectfile == 0){
				System.out.println("ファイルを新規作成します。");
				modori  = makefile(cdir);
				break;																	//makefile(cdir)メソッド、break
			}else if(selectfile == -1){ //１つ上のファイルに戻る。
				cdir = cdir  + "\\..";
				cdirectory = new File(cdir);
				cdir = cdirectory.getAbsolutePath();
			}else if(selectfile>filelist.length){
				System.out.println("リストに存在しない番号です。");
				continue;
			}else{
				//ディレクトリの再定義
				if(cdir.equals("c:\\")){
					cdir = cdir  + filelist[selectfile-1];
				}else{
					cdir = cdir + "\\" + filelist[selectfile-1];
				}
				cdirectory = new File(cdir);

				//選ばれたものがファイルかどうか判断、読めるファイルならブレークし出力、読めないなら1つ前に、フォルダならコンティニュー
				if( cdirectory.isFile()){
					if(cdirectory.canRead()){
						readfile(cdir); //ファイルを読み込む
						modori = cdir;
						break;
					}else{
						System.out.println("そのファイルは読み込めません。1つ前のフォルダに戻ります。");
						cdir = cdir  + "\\..";
						cdirectory = new File(cdir);
						cdir = cdirectory.getAbsolutePath();
					}
				}

			}
		}
		return modori;
	}

	//ファイル作成メソッド
	public static String makefile(String cdir){
		int make=0;//ファイルを作成しますかの選択
		String name;//cdirとinput(ファイル名)の結合
		int change ;
		String modori=null;

		while (true){
			System.out.println("ファイルを作成しますか?以下のリストから1か2を選んでください。\n1 : はい\n2 : いいえ（メインメニューに戻ります）");
			make = new java.util.Scanner(System.in).nextInt();
			if(make==1 ||make==2){
				break;
			}else{
				System.out.println("入力された数字は無効です。選び直してください。");
			}
		}

		if(make==1){
			while (true){
				System.out.println("作成するファイル名を入力してください。\nファイル名には拡張子をつけてください(例 : text.txt)。");
				String input = new java.util.Scanner(System.in).nextLine();
				if(cdir.equals("0")){
					name = input;
				}else{
					name = cdir +"\\" + input;
				}
				File newfile = new File(name);

				try{
					if(newfile.createNewFile()){
						System.out.println(name+"ファイルの作成に成功しました。");//ファイル作成時の終わり
						modori = name;//ファイル作成時の終わり,戻り値はファイル名。
						break;
					}else{
						System.out.println("ファイルの作成に失敗しました\nファイル名を変えて作成しますか？\n"
								+ "1 : はい\n1以外の任意の整数 : いいえ");
						change = new java.util.Scanner(System.in).nextInt();
						if(change==1){
							continue;
						}else{
							//ファイル作成しないend1
							break;
						}
					}
				}catch(IOException e){
					//何もしない（上で処理済)
				}
			}
        //ファイルを作成しない場合
		}else{
			//ファイル作成しないend1
		}

		return modori;
	}

	//ファイル出力メソッド
	public static void  readfile(String cdir){
		System.out.println("----------ここから出力結果----------");

		try{
			File file = new File(cdir);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String str = br.readLine();
			while(str != null){
				System.out.println(str);
				str=br.readLine();
			}

			br.close();
			System.out.println("----------------------------------");
		}catch(FileNotFoundException e){
			System.out.println("ファイルがありません。");
		}catch(IOException e){
			System.out.println("出力エラーです。");
		}
	}

	//ファイル書き込みメニュー
	public static void wmenu(String cdir){
		System.out.println("ファイルに書き込みます。以下のメニューを見て1~3の半角数字を入力しいてください。");
		System.out.println("【書き込みメニュー】\n1 :ファイルに上書きします。\n2 : ファイルに追記します。\n3 : メインメニューに戻ります。");
		boolean wend = true;

		while(wend){
			int wmenu = new java.util.Scanner(System.in).nextInt();
			switch(wmenu){
				case 1 :{
					write(cdir,1);
					System.out.println("現在のファイルに書き込みを続けますか？\n【書き込みメニュー】\n1 :ファイルに上書きします。\n2 : ファイルに追記します。\n3 : メインメニューに戻ります。");
					break;
				}
				case 2 :{
					write(cdir,2);
					System.out.println("現在のファイルに書き込みを続けますか？\n【書き込みメニュー】\n1 :ファイルに上書きします。\n2 : ファイルに追記します。\n3 : メインメニューに戻ります。");
					break;
				}
				case 3 :{
					System.out.println("メインメニューに戻ります。");
					wend = false;
					break;
				}
				default : {
					System.out.println("メニューにはない数字です。");
				}
			}
		}

	}

		//書き込みメソッド
		public static void write(String cdir, int wmenu) {

		//書き込みファイルの宣言と確認、PrintWriterの作成

		File wfile = new File(cdir);
		try{
			if(wfile.isFile() && wfile.canWrite()){
				if(wmenu==1){
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(wfile)));
					System.out.println("ファイルに書き込む内容を入力してください。\n終了する場合は改行して、半角でxyzと入力してください。");
					while(true){
						String input = new java.util.Scanner(System.in).nextLine();
						if(input.equals("xyz")){
							break;
						}
						pw.println(input);
					}
					pw.close();
				}else{
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(wfile,true)));
					System.out.println("ファイルに書き込む内容を入力してください。\n終了する場合は改行して、半角でxyzと入力してください。");
					while(true){
						String input = new java.util.Scanner(System.in).nextLine();
						if(input.equals("xyz")){
							break;
						}
						pw.println(input);
					}
					pw.close();
					System.out.println("以下の内容で書き込みました。");
					readfile(cdir);
				}
			}else{
				System.out.println("このファイルには書き込みできません。");
			}
		}catch(IOException e){
			System.out.println("エラーが発生しました");
		}
	}
		public static String makefile2(){
			String cdir;
			boolean sfend = true;
			String modori= null;

			while(true){
				System.out.println("新規作成を行うフォルダのアドレスを入力してください。\\は2つ入力してください。\n表示したリストから使用するフォルダ、ファイルを選べます。\n"
						+ "0を入力すると、現在のフォルダにファイルを作成します（メインメニューに戻れます）。\n1を入力するとローカルディスクCに移動します。");
				cdir = new java.util.Scanner(System.in).nextLine();

				if(cdir.equals("0")){
					System.out.println("現在のフォルダにファイルを新規作成します。");//メイクファイルメソッドに移動
					modori  = makefile(cdir);
					sfend = false;
					break;
				}
				if(cdir.equals("1")){
					cdir = "c:\\";
					break;
				}
				File cdirectory = new File(cdir);

				//Fileチェック1：フォルダなら下に、ファイルならリードメソッド実行しメニューに、存在しない場合はMakeに移動する。
				if(cdirectory.exists()){
					if(cdirectory.isDirectory()){
						System.out.println("入力されたフォルダに新規作成します。");
						modori  = makefile(cdir);
						sfend = false;
						break;
					}else if(cdirectory.isFile()){
						System.out.println("それはファイルです。入力しなおしてください。");
					}
				}else{
					System.out.println("入力されたフォルダは存在しません。現在のフォルダにファイルを新規作成しますか？");
					System.out.println("1       : はい、新規作成します。\n1以外の整数 : いいえ、パスを入力しなおします。");
					int sin= new java.util.Scanner(System.in).nextInt();
					if(sin==1){
						System.out.println("ファイルを新規作成します。");
						modori = makefile(cdir);
						sfend=false;
						break;
					}
				}
			}


			return modori;
		}

}
