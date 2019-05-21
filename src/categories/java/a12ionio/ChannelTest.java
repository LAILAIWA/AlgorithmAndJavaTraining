package categories.java.a12ionio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-26
 */
public class ChannelTest {
    public static void main(String[] args) {
        try {
//            Path path = Paths.get("D:/test.txt");
//            FileChannel channel = FileChannel.open(path);
//            FileLock lock1 = channel.lock();
//            FileLock lock2 = channel.tryLock();
//
//            //创建SocketChannel和Selector对象
//            SocketChannel socketChannel = SocketChannel.open();
//            Selector selector = Selector.open();
//
//            //配置socketChannel为非阻塞模式
//            socketChannel.configureBlocking(false);
//
//            //注册socketChannel到selector，channel必须继承AbstractSelectableChannel
//            //SelectionKey表示通道对象和选择器对象之间的注册关系
//            SelectionKey key = socketChannel.register(selector,SelectionKey.OP_READ);

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1",8000));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector,SelectionKey.OP_ACCEPT);

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("received".getBytes());
            writeBuffer.flip();

            while (true){
                int ready = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();

                    if(key.isAcceptable()){
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuffer.clear();
                        socketChannel.read(readBuffer);

                        readBuffer.flip();
                        System.out.println("received : " + new String(readBuffer.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }else if(key.isWritable()){
                        writeBuffer.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
