package simulator.security;

import simulator.scenario.InvalidContentException;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Parser {

    public static final String MD_5 = "MD5";
    public static final String MD_5_REGEX = "^[a-f0-9]{32}$";

    public static void validateFile(String fileName, String inputMd5Hash) throws IOException, MD5Exception, InvalidContentException {
        String fileMd5Hash = getMd5Hash(fileName);
        if (!inputMd5Hash.matches(MD_5_REGEX)) {
            throw new MD5Exception("Формант хэшсуммы не верный для MD5");
        }
        if (!fileMd5Hash.equals(inputMd5Hash)) {
            throw new MD5Exception("Содержимое файла не надежно, хэшсуммы не совпали");
        }
    }

    public static String getMd5Hash(String fileName) throws IOException, InvalidContentException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[(int) file.length()];
        if (fis.read(data) == 0) {
            fis.close();
            throw new InvalidContentException("Файл со сценарием пуст");
        } else {
            fis.close();
        }
        try {
            MessageDigest md = MessageDigest.getInstance(MD_5);
            md.update(data);
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
