package java.security;


import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class Base64Test {

    public static void main(String[] args) throws Exception {
//        final BASE64Encoder encoder = new BASE64Encoder();
//        final BASE64Decoder decoder = new BASE64Decoder();
//        final String text ="Java深入";
//        final byte[] textByte = text.getBytes("UTF-8");
//        //编码
//        final String encodedText = encoder.encode(textByte);
//        System.out.println("编码后：" + encodedText);//编码后：SmF2Yea3seWFpQ==
//        //解码
//        System.out.println("解码后：" + new String(decoder.decodeBuffer(encodedText),"UTF-8"));//解码后：Java深入

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "Java深入";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println("编码后：" + encodedText);//编码后：SmF2Yea3seWFpQ==
        //解码
        System.out.println("解码后：" + new String(decoder.decode(encodedText), "UTF-8"));//解码后：Java深入

        //Basic编码
        String basicEncoded = Base64.getEncoder().encodeToString("subjects?parm=abcd".getBytes("UTF-8"));
        System.out.println("basic encoder: " + basicEncoded);//basic encoder: c3ViamVjdHM/cGFybT1hYmNk
        //URL编码
        String urlEncoded = Base64.getUrlEncoder().encodeToString("subjects?parm=abcd".getBytes("UTF-8"));
        System.out.println("url encoder: " + urlEncoded);//url encoder: c3ViamVjdHM_cGFybT1hYmNk
        //MIME编码
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 10; ++t) {
            sb.append(UUID.randomUUID().toString());
        }
        byte[] toEncode = sb.toString().getBytes("UTF-8");
        String mimeEncoded = Base64.getMimeEncoder().encodeToString(toEncode);
        System.out.println("mime encoder: " + mimeEncoded);

        try{
            wrapping();
        }catch (IOException ex){
        }
    }


    public static void wrapping() throws IOException {
        String src = "This is the content of any resource read from somewhere" +
                " into a stream. This can be text, image, video or any other stream.";

        // 编码器封装OutputStream, 文件/tmp/buff-base64.txt的内容是BASE64编码的形式
        try (OutputStream os = Base64.getEncoder().wrap(new FileOutputStream("/tmp/buff-base64.txt"))) {
            os.write(src.getBytes("UTF-8"));
        }

        // 解码器封装InputStream, 以及以流的方式解码, 无需缓冲
        // is being consumed. There is no need to buffer the content of the file just for decoding it.
        try (InputStream is = Base64.getDecoder().wrap(new FileInputStream("/tmp/buff-base64.txt"))) {
            int len;
            byte[] bytes = new byte[100];
            while ((len = is.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len, "UTF-8"));
            }
        }
    }
}
