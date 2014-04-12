package nss.leidos.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Csc {

	public static void main(String[] args) throws IOException {
		String fileName = "/home/antony/words.txt";
		BufferedReader br = null;
		List<String> dictionary = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(fileName));
			String word;

			while ((word = br.readLine()) != null) {
				dictionary.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Encryption encryption = new Encryption();
		Hash hash = new Hash();

		//String encrypted = "ndnkEusWpSN5QzlFSrTVTg=="; // Key - Leidos    Message - "This is a test"
		String encrypted = "OzqFvnCGzBrzRJTxSVa5Gbn1x6uS/sVKMkSl2v5TfagfxL84EbPErhshp3WMW5Vi4OwlCYQpPGOfsCmtnM1Yn24uOk6Puqw2f6/yaVsYybKJpFsu+D3kUohst0Qs3UmZYTRH+tWHCgrTRmqxxIFJ5NWeJkJA4NIZ";
		for (String word : dictionary) {
			String decrypted = encryption.decrypt(hash.getHashedString(word),
					encrypted);
			if (decrypted.matches("^[\\u0000-\\u007F]*$")) {
				System.out
						.println("word: " + word + "   message: " + decrypted);
			}
			// System.out.println("word: " + word + "   message: " + decrypted);
		}
	}
}
