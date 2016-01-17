package Java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Base64Implementation {


    public static void main(String[] args) {

        final String HTTP_WWW_GOOGLE_COM = "http://www.google.com";
        byte[] MIME_DATA = null;
        try {
            MIME_DATA = HTTP_WWW_GOOGLE_COM.getBytes("utf-8");
        } catch (UnsupportedEncodingException ignored) {
        }

        //basic type
        System.out.println("=========Basic=========");
        System.out.println("encoded basic: " + new String(Base64.getEncoder().encode(HTTP_WWW_GOOGLE_COM.getBytes())));
        System.out.println("encoded basic: " + Base64.getEncoder().encodeToString(HTTP_WWW_GOOGLE_COM.getBytes()));
        System.out.println("encoded basic: " + Base64.getEncoder().withoutPadding().encodeToString(HTTP_WWW_GOOGLE_COM.getBytes()));
        System.out.println("decoded basic: " + new String(Base64.getDecoder().decode("aHR0cDovL3d3dy5nb29nbGUuY29t")));

        //mime type
        System.out.println("=========Mime Type=========");
        if (MIME_DATA != null) {
            System.out.println("encoded mime type: " + Base64.getMimeEncoder().encodeToString(MIME_DATA));
            System.out.println("encoded mime type: " + Base64.getMimeEncoder().withoutPadding().encodeToString(MIME_DATA));
            System.out.println("decoded mime type: " + new String(Base64.getMimeDecoder().decode("aHR0cDovL3d3dy5nb29nbGUuY29t")));
        }

        //mime type
        System.out.println("=========Url and File Type=========");
        if (MIME_DATA != null) {
            System.out.println("encoded file and url type: " + Base64.getUrlEncoder().encodeToString(MIME_DATA));
        }

    }
}
