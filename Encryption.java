package nss.leidos.com;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryption {
	
	private String charsetName = "UTF-8";
	private String algorithm = "DES";
    private String algorithmModePadding = "DES/ECB/NoPadding";

    public String getCharsetName() {
        return charsetName;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithmModePadding() {
		return algorithmModePadding;
	}

	public void setAlgorithmModePadding(String algorithmModePadding) {
		this.algorithmModePadding = algorithmModePadding;
	}

	public String encrypt(String key, String data) {
        if (key == null || data == null)
            return null;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            byte[] dataBytes = data.getBytes(charsetName);
            Cipher cipher = Cipher.getInstance(getAlgorithmModePadding());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(dataBytes));
        } catch (Exception e) {
        	System.out.println("Exception during encrypt : " + e.getMessage());
            return null;
        }
    }

    public String decrypt(String key, String data) {
        if (key == null || data == null)
            return null;
        try {
            byte[] dataBytes = Base64.decodeBase64(data);
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(getAlgorithmModePadding());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
            return new String(dataBytesDecrypted);
        } catch (Exception e) {
        	System.out.println("Exception during decrypt : " + e.getMessage());
            return null;
        }
    }
}
