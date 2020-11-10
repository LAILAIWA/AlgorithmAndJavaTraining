package java.base.a12ionio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-21
 */
public class WebClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer1 = ByteBuffer.allocate(128);
            ByteBuffer writeBuffer2 = ByteBuffer.allocate(128);
            ByteBuffer readBuffer = ByteBuffer.allocate(16);
            writeBuffer1.put("hello ".getBytes());
            writeBuffer2.put("world".getBytes());

            writeBuffer1.flip();
            writeBuffer2.flip();
            ByteBuffer[] bufferArray = {writeBuffer1, writeBuffer2};
            while (true){
                writeBuffer1.rewind();
                writeBuffer2.rewind();
                socketChannel.write(bufferArray);
                readBuffer.clear();
                socketChannel.read(readBuffer);
                //socketChannel.close();
            }
        } catch (IOException e) {
        }
    }
}
